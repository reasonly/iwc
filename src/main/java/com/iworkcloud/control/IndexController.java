package com.iworkcloud.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {



    @GetMapping("/personalCenter")
    public String personalCenter() {
        return "personalCenter";
    }

    @GetMapping("/page2")
    public String page2() {
        return "page2";
    }

    @GetMapping("/page3")
    public String page3() {
        return "page3";
    }

    @GetMapping("/page4")
    public String page4() {
        return "page4";
    }

    @GetMapping("/page5")
    public String page5() {
        return "page5";
    }

    @GetMapping("/page6")
    public String page6() {
        return "page6";
    }

}
