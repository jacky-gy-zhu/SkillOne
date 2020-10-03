package com.skillone.springboot.mock;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ReactAPITestController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginReturn login(@RequestBody Login login) {
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
        LoginReturn loginReturn = new LoginReturn();
        login.set_id(UUID.randomUUID().toString());
        login.setCreateTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
        login.set__v(0);
        login.setRole(new Role());
        loginReturn.setStatus(0);
        loginReturn.setData(login);
        return loginReturn;
    }

}
