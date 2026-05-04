package com.example.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter, Long> {

    Optional<Counter> findByName(String name);

    @Modifying
    @Query("UPDATE Counter c SET c.value = c.value + 1, c.updatedAt = CURRENT_TIMESTAMP WHERE c.name = :name")
    int incrementByName(@Param("name") String name);
}
