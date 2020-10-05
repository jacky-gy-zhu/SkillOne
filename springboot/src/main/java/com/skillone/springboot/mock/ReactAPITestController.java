package com.skillone.springboot.mock;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class ReactAPITestController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiReturn login(@RequestBody Login login) {
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
        ApiReturn apiReturn = new ApiReturn();
        login.set_id(UUID.randomUUID().toString());
        login.setCreateTime(Timestamp.valueOf(LocalDateTime.now()).getTime());
        login.set__v(0);
        login.setRole(new Role());
        apiReturn.setStatus(0);
        apiReturn.setData(login);
        return apiReturn;
    }

    @GetMapping(value = "/manage/category/list")
    public ApiReturn login(@RequestParam final long parentId) throws Exception {
        ApiReturn apiReturn = new ApiReturn();
        List<Category> categoryList = Lists.newArrayList();
        Category category = new Category();
        category.set_id(UUID.randomUUID().toString());
        category.set__v(0);
        category.setParentId(0L);
        category.setName("家用电器");
        apiReturn.setStatus(0);
        categoryList.add(category);
        categoryList.add(category.clone().withName("电脑"));
        categoryList.add(category.clone().withName("图书"));
        apiReturn.setData(categoryList);
        return apiReturn;
    }

}
