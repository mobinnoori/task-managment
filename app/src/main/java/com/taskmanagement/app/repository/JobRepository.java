package com.taskmanagement.app.repository;

import com.taskmanagement.app.enums.TaskStatus;
import com.taskmanagement.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query(value = """
    SELECT DISTINCT j.* FROM jobs j
    LEFT JOIN jobs sj ON sj.parent_job_id = j.id
    LEFT JOIN users u ON u.id = j.user_id
    WHERE (:status IS NULL OR j.status = :status)
      AND (:userId IS NULL OR u.id = :userId)
      AND (:title IS NULL OR j.title ILIKE CONCAT('%', :title, '%'))
""", nativeQuery = true)
    List<Job> filterJobsWithSubJobs(
            @Param("status") String status,
            @Param("userId") Integer userId,
            @Param("title") String title
    );
}