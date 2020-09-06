package com.skillone.springboot.controller;

import com.skillone.designpattern.adapter.classadapter.Phone;
import com.skillone.designpattern.adapter.classadapter.VoltageAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/")
    public String test() {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
        return "test";
    }
}
