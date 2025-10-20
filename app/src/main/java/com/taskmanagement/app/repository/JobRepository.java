package com.taskmanagement.app.repository;

import com.taskmanagement.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Integer> {}