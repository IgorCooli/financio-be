package com.io.financio.dataprovider.repository;

import com.io.financio.dataprovider.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    UserDocument findByUsernameAndPassword(String username, String password);

}
