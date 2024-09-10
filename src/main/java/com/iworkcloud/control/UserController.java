package com.iworkcloud.control;

import com.iworkcloud.pojo.Results;
import com.iworkcloud.pojo.User;
import com.iworkcloud.service.UserService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userUpdate")
    public Results UserUpdate(HttpServletRequest Request, @RequestBody Map<String, Object> request) {
        System.out.println("访问localhost:9000/UserController/userUpdate！");
        int id = 0;
        try {
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt=" + jwt);
            Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :" + id);

        } catch (Exception e) {

            return Results.Error("token过期，请重新登录！");
        }
        String oldpassword = (String) request.get("oldpassword");

        User user = userService.findByPrimaryKey(id);
        boolean result = userService.cheakUserPassword(user, oldpassword);
        System.out.println("原密码验证结果：" + result);
        if (result) {
            String name = (String) request.get("name");
            String account = (String) request.get("account");
            String email = (String) request.get("email");
            String newpassword = (String) request.get("newpassword");

            Map<String, String> map = userService.encryptPasswords(newpassword);
            String salt = map.get("salt");
            String password = map.get("password");

            user.setUserName(name);
            user.setUserAccount(account);
            user.setUserEmail(email);
            user.setUserPassword(password);
            user.setUserSalt(salt);

            System.out.println(user);
            userService.update(user);
            System.out.println(userService.findByPrimaryKey(id));
            return Results.Success("信息更改成功！");
        } else return Results.Error("原密码错误！");
    }

    //@RequestMapping("/selectStaff")
    public Results selectUser(HttpServletRequest Request) {
        System.out.println("访问localhost:9000/UserController/selectStaff！");
        int id = 0;
        try {
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt=" + jwt);
            Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :" + id);

        } catch (Exception e) {

            return Results.Error("token过期，请重新登录！");
        }
        User user = new User();
        user.setUserAuthority("员工");
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();

        List<User> list = userService.findUsersByUser(user);
        map.put("staffList", list);
        System.out.println(map);
        return Results.Success(map);

    }
}

