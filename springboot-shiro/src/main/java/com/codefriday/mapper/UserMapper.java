package com.codefriday.mapper;

import com.codefriday.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author codefriday
 * @data 2021/4/5
 */
@Mapper
@Repository
public interface UserMapper {
    public User getUserByName(String name);
}
