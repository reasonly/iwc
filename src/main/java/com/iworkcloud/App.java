package com.iworkcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;


/**
 * 
* @Title: App
* @Description:
* 主程序入口 
* @Version:1.0.0  

 */
//去掉自动注册了
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class App 
{
    public static void main( String[] args )
    {
		System.out.println("程...");
		SpringApplication.run(App.class, args);
		System.out.println("程序正在运行...");
		System.out.println("程序正在运行...");
		System.out.println("程");
		System.out.println("程序正在运行...");
    }
}
