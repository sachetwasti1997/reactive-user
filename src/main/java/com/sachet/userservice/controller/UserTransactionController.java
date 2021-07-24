package com.sachet.userservice.controller;

import com.sachet.userservice.dto.TransactionRequestDto;
import com.sachet.userservice.dto.TransactionResponseDto;
import com.sachet.userservice.service.ReactiveTransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
