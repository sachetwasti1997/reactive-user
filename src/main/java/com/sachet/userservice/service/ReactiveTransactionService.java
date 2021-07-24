package com.sachet.userservice.service;

import com.sachet.userservice.dto.TransactionRequestDto;
import com.sachet.userservice.dto.TransactionResponseDto;
import com.sachet.userservice.dto.TransactionStatus;
import com.sachet.userservice.entity.UserTransaction;
import com.sachet.userservice.repository.UserRepository;
import com.sachet.userservice.repository.UserTransactionRepository;
import com.sachet.userservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveTransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    public ReactiveTransactionService(UserRepository userRepository, UserTransactionRepository userTransactionRepository) {
        this.userRepository = userRepository;
        this.userTransactionRepository = userTransactionRepository;
    }

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto transactionRequestDto){

        return userRepository.updateUserBalance(transactionRequestDto.getUserId(), transactionRequestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toUserTransactionEntity(transactionRequestDto))
                .flatMap(userTransactionRepository::save)
                .map(userTransaction -> EntityDtoUtil.toResponseDto(transactionRequestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toResponseDto(transactionRequestDto, TransactionStatus.REJECTED));
    }

    public Flux<UserTransaction> getTransactionByUserId(Integer userId){
        return userTransactionRepository.findByUserId(userId);
    }



}
