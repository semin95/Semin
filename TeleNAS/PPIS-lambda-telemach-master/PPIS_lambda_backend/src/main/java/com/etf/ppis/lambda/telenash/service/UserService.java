package com.etf.ppis.lambda.telenash.service;

import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.model.UserDto;

import java.util.List;

public interface UserService
{
    User save(UserDto user);
    List<User> findAll();
    User getByUserName(String userName);
}
