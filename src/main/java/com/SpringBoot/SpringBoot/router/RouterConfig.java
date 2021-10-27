package com.SpringBoot.SpringBoot.router;

import com.SpringBoot.SpringBoot.handler.UserHandler;
import com.SpringBoot.SpringBoot.handler.UserStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private UserHandler userHandler;

    @Autowired
    private UserStreamHandler userStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
         return RouterFunctions.route()
                 .GET("/router/users", userHandler::loadUsers )
                 .GET("/router/users/stream", userStreamHandler::getUserStream )
                 .GET("/router/users/{userId}", userHandler::findUser )
                 .POST("/router/users/save", userHandler::saveUser )
                 .build();
    }
}
