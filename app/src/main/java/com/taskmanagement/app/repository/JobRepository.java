package com.taskmanagement.app.repository;

import com.taskmanagement.app.enums.TaskStatus;
import com.taskmanagement.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("""
    SELECT DISTINCT j FROM Job j
    LEFT JOIN FETCH j.subJobs sj
    LEFT JOIN FETCH j.user u
    LEFT JOIN FETCH j.project p
    WHERE (:jobId IS NULL OR j.id = :jobId)
      AND (:status IS NULL OR j.status = :status)
      AND (:userId IS NULL OR u.id = :userId)
      AND (COALESCE(:title, '') = '' OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')))
      AND (:projectId IS NULL OR p.id = :projectId)
""")
    List<Job> filterJobsWithSubJobs(
            @Param("jobId") Integer jobId,
            @Param("status") TaskStatus status,
            @Param("userId") Integer userId,
            @Param("title") String title,
            @Param("projectId" ) Integer projectId
    );
}