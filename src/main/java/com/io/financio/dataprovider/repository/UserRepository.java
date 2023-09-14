package com.io.financio.dataprovider.repository;

import com.io.financio.dataprovider.model.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByUsernameAndPassword(String username, String password);

}
