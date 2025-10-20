package com.taskmanagement.app.repository;

import com.taskmanagement.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.jobs")
    List<User> findAllWithJobs();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.jobs WHERE u.id = :id")
    Optional<User> findByIdWithJobs(@Param("id") Integer id);
}