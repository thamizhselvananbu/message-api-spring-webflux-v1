package com.rakuten.mobile.messageapiv1.repository;

import com.rakuten.mobile.messageapiv1.model.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {
}
