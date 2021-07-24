package com.sachet.userservice.repository;

import com.sachet.userservice.entity.User;
import com.sachet.userservice.entity.UserTransaction;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

    Flux<UserTransaction> findByUserId(Integer id);

}
