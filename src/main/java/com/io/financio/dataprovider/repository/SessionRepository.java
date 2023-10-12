package com.io.financio.dataprovider.repository;

import com.io.financio.dataprovider.model.SessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<SessionDocument, String> {
    SessionDocument findByUsername(String username);
}
