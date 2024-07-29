package org.archadu.core.repository;

import org.archadu.core.model.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepo extends MongoRepository<Cover, String> {


    Cover findByRelease(String release);

}
