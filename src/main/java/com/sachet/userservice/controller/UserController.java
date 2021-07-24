package com.sachet.userservice.controller;

import com.sachet.userservice.dto.UserDto;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.response.Response;
import com.sachet.userservice.service.ReactiveUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

    private final ReactiveUserService userService;

    public UserController(ReactiveUserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public Mono<ResponseEntity<Response>> saveUser(@RequestBody Mono<UserDto> userDtoMono){
        return userService.saveUser(userDtoMono).map(ResponseEntity::ok);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Response>> getUserById(@PathVariable Integer id){
        return userService
                .getUser(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getUsers(){
        return userService
                .getAllUser();
    }


}
