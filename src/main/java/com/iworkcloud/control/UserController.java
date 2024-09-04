package com.iworkcloud.control;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Title: UserController
 * Description:
 * 用户控制层
 * Version:1.0.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user) {
        User user_result=userService.findByEntity(user);
        if(user_result!=null)
//            return "forword:/index";
            return "redirect:/index";
        else
            return "user/login";
    }


}