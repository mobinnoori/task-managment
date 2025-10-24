package com.taskmanagement.app.repository;

import com.taskmanagement.app.dto.UserStatisticsDTO;
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


    @Query("""
        SELECT new com.taskmanagement.app.dto.UserStatisticsDTO(
            u.id,
            u.name,
            COUNT(j),
            SUM(CASE WHEN j.status = 'COMPLETED' THEN 1 ELSE 0 END),
            SUM(CASE WHEN j.status = 'IN_PROGRESS' THEN 1 ELSE 0 END),
            SUM(CASE WHEN j.status = 'PENDING' THEN 1 ELSE 0 END)
        )
        FROM User u
        LEFT JOIN u.jobs j
        LEFT JOIN j.subJobs sj
        GROUP BY u.id, u.name
        """)
    List<UserStatisticsDTO> getUserJobStatistics();
}