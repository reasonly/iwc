package com.iworkcloud.control;

import java.util.List;

import com.iworkcloud.pojo.Result;
import com.iworkcloud.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iworkcloud.pojo.entity.User;
import com.iworkcloud.service.UserService;


/**
 * 
* Title: UserRestController
* Description: 
* 用户控制层
* Version:1.0.0  

 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public Result<List<User>> findByUser() {
    	System.out.println("开始查询...");
        return new Result<>(ResultCode.SUCCESS, userService.findAll());
//        return userService.findAll();
    }
    
}
