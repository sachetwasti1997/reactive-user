package com.sachet.userservice.repository;

import com.sachet.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Modifying
    @Query(
            "update user"
            +" set balance = balance - :amount "
            +"where id = :userId "
            +"and balance >= :amount"
    )
    Mono<Boolean> updateUserBalance(int userId, int amount);

}
