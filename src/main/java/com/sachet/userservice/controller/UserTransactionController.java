package com.sachet.userservice.controller;

import com.sachet.userservice.dto.TransactionRequestDto;
import com.sachet.userservice.dto.TransactionResponseDto;
import com.sachet.userservice.entity.UserTransaction;
import com.sachet.userservice.service.ReactiveTransactionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    private final ReactiveTransactionService reactiveTransactionService;

    public UserTransactionController(ReactiveTransactionService reactiveTransactionService) {
        this.reactiveTransactionService = reactiveTransactionService;
    }

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> transactionRequest){
        return transactionRequest.flatMap(reactiveTransactionService::createTransaction);
    }

    @GetMapping("{userId}")
    public Flux<UserTransaction> getTransactionByUserId(@PathVariable Integer userId){
        return reactiveTransactionService.getTransactionByUserId(userId);
    }


}
