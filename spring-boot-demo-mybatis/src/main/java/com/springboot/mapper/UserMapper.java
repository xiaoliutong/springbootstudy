package com.springboot.mapper;

import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
public interface UserMapper {
  List<User> findAll();
  @Select(" SELECT * FROM orm_user")
   List<User> getAll();

  List<User> findOne();
}
