package com.taskmanagement.app.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(Integer id) {
        super("Job not found with id: " + id);
        }
}