package com.example.communications.domain.users;



import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.stream.Stream;

public interface UsersRepository extends MongoRepository<Users, Long> {

    Stream<Users> findAllBy();

    Optional<Users> findByName(String name);

}
