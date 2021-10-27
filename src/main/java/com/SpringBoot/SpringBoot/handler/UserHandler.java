package com.SpringBoot.SpringBoot.handler;

import com.SpringBoot.SpringBoot.entity.User;
import com.SpringBoot.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> loadUsers(ServerRequest serverRequest){
        Flux<User> users= userRepository.getUsersList();
        return  ServerResponse.ok().body(users, User.class);
    }

    public Mono<ServerResponse> findUser(ServerRequest serverRequest){

        int userId = Integer.valueOf(serverRequest.pathVariable("userId"));
        Mono<User> users= userRepository.getUsersList()
                .filter(c->c.getUserId()==userId)
//                .take(1).single();
                .next();

        return  ServerResponse.ok().body(users, User.class);
    }
    public Mono<ServerResponse> saveUser(ServerRequest serverRequest){

        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        Mono<String> saveResponse = userMono.map(dto -> dto.getUserId() + " :: " + dto.getUserName());

        return  ServerResponse.ok().body(saveResponse, String.class);
    }
}


