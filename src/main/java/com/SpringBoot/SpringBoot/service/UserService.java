package com.SpringBoot.SpringBoot.service;

import com.SpringBoot.SpringBoot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringBoot.SpringBoot.repository.UserRepository;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> loadAllUsers(){
        Long startTime=System.currentTimeMillis();
        List<User> users= userRepository.getUsers();
        System.out.println("Time taken to pull data "+(System.currentTimeMillis()-startTime));
        return users;
    }

    public Flux<User> loadAllUsersStream(){
        Long startTime=System.currentTimeMillis();
        Flux<User> users= userRepository.getUsersStream();
        System.out.println("Time taken to pull data "+(System.currentTimeMillis()-startTime));
        return users;
    }


}
