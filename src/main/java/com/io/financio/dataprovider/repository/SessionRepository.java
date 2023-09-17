package com.io.financio.dataprovider.repository;

import com.io.financio.dataprovider.model.SessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<SessionDocument, String> {
}
