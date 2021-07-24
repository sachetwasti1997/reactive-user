package com.sachet.userservice.service;

import com.sachet.userservice.dto.UserDto;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.repository.UserRepository;
import com.sachet.userservice.response.Response;
import com.sachet.userservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUserService {

    private final UserRepository userRepository;

    public ReactiveUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Mono<Response> saveUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(EntityDtoUtil::toUser)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto)
                .map(user -> new Response(user, "User with "+user.getId()+" created successfully!"));
    }

    public Mono<Response> getUser(Integer id){
        return userRepository
                .findById(id)
                .map(EntityDtoUtil::toDto)
                .map(userDto -> new Response(userDto, "User with "+id+" found successfully!"));
    }

    public Flux<Response> getAllUser(){
        return userRepository
                .findAll()
                .map(EntityDtoUtil::toDto)
                .map(Response::new);
    }

    public Mono<Response> updateProduct(Integer id, Mono<UserDto> userDtoMono){
        return userRepository
                .findById(id)
                .flatMap(user -> userDtoMono.map(EntityDtoUtil::toUser))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto)
                .map(Response::new);
    }

    public Mono<Void> deleteProduct(Integer id){
        return userRepository
                .findById(id)
                .flatMap(userRepository::delete);
    }

}

















