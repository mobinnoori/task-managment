package com.taskmanagement.app.repository;

import com.taskmanagement.app.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH p.members " +
            "LEFT JOIN FETCH p.jobs " +
            "WHERE p.id = :id")
    Optional<Project> findByIdWithDetails(@Param("id") Integer id);

    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH p.members " +
            "LEFT JOIN FETCH p.jobs")
    List<Project> findAllWithDetails();
}
