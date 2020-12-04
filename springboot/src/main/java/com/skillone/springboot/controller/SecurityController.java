package com.skillone.springboot.controller;

import com.google.common.collect.Lists;
import com.skillone.springboot.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("index")
    public String index() {
        return "hello index";
    }

    @GetMapping("update")
    @Secured({"ROLE_sales","ROLE_manager"})
    public String update() {
        return "hello update";
    }

    @GetMapping("update2")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String update2() {
        return "hello update2";
    }

    @GetMapping("update3")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String update3() {
        System.out.println("update....");
        return "hello update2";
    }

    @GetMapping("getAll")
    @PostAuthorize("hasAnyAuthority('admin')")
    @PostFilter("filterObject.username == 'admin1'")
    public List<Users> getAllUser() {
        List<Users> list = Lists.newArrayList();
        list.add(new Users(1, "admin1", "666"));
        list.add(new Users(2, "admin2", "333"));
        System.out.println(list);
        return list;
    }
}
