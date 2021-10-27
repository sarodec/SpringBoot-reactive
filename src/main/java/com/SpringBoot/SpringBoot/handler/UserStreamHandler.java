package com.SpringBoot.SpringBoot.handler;

import com.SpringBoot.SpringBoot.entity.User;
import com.SpringBoot.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserStreamHandler {
    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> getUserStream(ServerRequest serverRequest){

        Flux<User> userFlux=userRepository.getUsersStream();

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(userFlux,User.class);

    }
}
