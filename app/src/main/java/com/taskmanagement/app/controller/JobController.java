package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.JobDTO;
import com.taskmanagement.app.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public JobDTO createJob(@RequestBody JobDTO dto) {
        return service.createJob(dto);
    }

    @PostMapping("/create/sub-job/{parentJobId}")
    public JobDTO createSubJob(@PathVariable Integer parentJobId, @RequestBody JobDTO dto) {
        return service.createSubJob(dto, parentJobId);
    }

    // You can use single endpoint to manage both assignment and unassignment for a job for a user
    // if the user id is not provided it means that the job is being unassigned from the current user
    @PutMapping("/assign/{jobId}/{userId}")
    public JobDTO assignJobToUser(@PathVariable Integer jobId, @PathVariable Integer userId) {
        return service.assignJobToUser(jobId, userId);
    }


    @PutMapping("/unassign/{jobId}")
    public JobDTO unassignUser(@PathVariable Integer jobId) {
        return service.unassignUserFromJob(jobId);
    }


    @GetMapping("/all")
    public List<JobDTO> getAllJobs() {
        return service.getAllJobs();
    }


    @GetMapping("/{id}")
    public JobDTO getJob(@PathVariable Integer id) {
        return service.getJobById(id);
    }


    @PutMapping("/update/{id}")
    public JobDTO updateJob(@PathVariable Integer id, @RequestBody JobDTO dto) {
        return service.updateJob(id, dto);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteJob(@PathVariable Integer id) {
        service.deleteJob(id);
    }
}