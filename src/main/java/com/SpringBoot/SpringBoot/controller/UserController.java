package com.SpringBoot.SpringBoot.controller;

import com.SpringBoot.SpringBoot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringBoot.SpringBoot.service.UserService;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.loadAllUsers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUsersStream(){
        return userService.loadAllUsersStream();
    }
}
