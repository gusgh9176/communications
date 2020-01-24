package com.example.communications.domain.pvps;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.stream.Stream;

public interface PvpsRepository extends MongoRepository<Pvps, Long> {

    Stream<Pvps> findAllBy();
    Optional<Pvps> findByName(String name);

}
