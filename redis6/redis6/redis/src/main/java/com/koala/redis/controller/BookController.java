package com.koala.redis.controller;

import com.koala.redis.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2021-05-11 22:12
 */
public class BookController
{
    @Resource
    private UserService userService;
}
