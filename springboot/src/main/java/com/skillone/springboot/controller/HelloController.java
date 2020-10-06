package com.skillone.springboot.controller;

import com.skillone.springboot.selenium.Application;
import com.skillone.springboot.selenium.RdmApp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@ResponseBody
@Controller*/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/selenium")
    public String selenium() {
        RdmApp rdmApp = new RdmApp();
        rdmApp.testLogin();
        return "success";
    }

}
