package com.example.communications.domain.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u " +
            "FROM Users u " +
            "ORDER BY u.id DESC")
    Stream<Users> findAllDesc();

    Optional<Users> findByName(String name);

    @Modifying
    @Query(value=" update Users u " +
            " set u.name = :#{#users.name} " +
            " where u.id = :#{users.id}", nativeQuery = false)
    Stream<Users> update(@Param("users") Users user);
}
