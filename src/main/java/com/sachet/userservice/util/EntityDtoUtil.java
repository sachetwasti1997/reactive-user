package com.sachet.userservice.util;

import com.sachet.userservice.dto.TransactionRequestDto;
import com.sachet.userservice.dto.TransactionResponseDto;
import com.sachet.userservice.dto.TransactionStatus;
import com.sachet.userservice.dto.UserDto;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.entity.UserTransaction;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setBalance(user.getBalance());
        return userDto;
    }

    public static User toUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setBalance(userDto.getBalance());
        return user;
    }

    public static UserTransaction toUserTransactionEntity(TransactionRequestDto requestDto){
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setUserId(requestDto.getUserId());
        userTransaction.setAmount(requestDto.getAmount());
        userTransaction.setDateTime(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toResponseDto(TransactionRequestDto requestDto, TransactionStatus status){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setAmount(requestDto.getAmount());
        transactionResponseDto.setUserId(requestDto.getUserId());
        transactionResponseDto.setTransactionStatus(status);
        return transactionResponseDto;
    }

}
