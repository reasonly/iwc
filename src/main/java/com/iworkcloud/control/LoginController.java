package com.iworkcloud.control;

import com.iworkcloud.pojo.User;
import com.iworkcloud.service.UserService;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Results login(@RequestBody Map<String, Object> request) {
        String account = (String)request.get("account");
        String password = (String)request.get("password");
        System.out.println("输入account:"+account+" password:"+password);
        User user=userService.findUserByAccount(account);
        System.out.println("findUserByAccount:"+user);
        if(user!=null){

            if(userService.cheakUserPassword(user,password)){
                System.out.println("成功登录!");
                Map<String, Object> claims = new HashMap<>();

                claims.put("id", user.getUserId());
                claims.put("name", user.getUserName());
                claims.put("authority", user.getUserAuthority());
                claims.put("account", user.getUserAccount());

                String jwt = JwtUtils.GetStringJwt(claims);
                System.out.println("jwt令牌："+jwt);
                return Results.Success(jwt);
            }else return Results.Error("用户名或密码错误");
        }else return Results.Error("用户名不存在");
    }



}