package com.SpringBoot.SpringBoot.repository;

import com.SpringBoot.SpringBoot.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserRepository {

    public List<User> getUsers(){
        return  IntStream.rangeClosed(0,10)
                .peek(UserRepository::waitingTime )
                .peek(x-> System.out.println("Fetching .. "+x))
                .mapToObj(x->new User(x,"User-"+x))
                .collect(Collectors.toList());
    }

    private static void waitingTime(int i) {
        try {
            Thread.sleep(1000);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public Flux<User> getUsersStream(){
        return
                Flux.range(0,10)
                .doOnNext(UserRepository::waitingTime)
                .doOnNext(x-> System.out.println("Streaming .. "+x))
                .map(x->new User(x,"User-"+x));
    }

    public Flux<User> getUsersList(){
        return
                Flux.range(0,10)
                        .doOnNext(x-> System.out.println("Streaming .. "+x))
                        .map(x->new User(x,"User-"+x));
    }
}
