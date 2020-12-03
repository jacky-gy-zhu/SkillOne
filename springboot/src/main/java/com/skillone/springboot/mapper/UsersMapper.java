package com.skillone.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skillone.springboot.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper extends BaseMapper<Users> {
}
