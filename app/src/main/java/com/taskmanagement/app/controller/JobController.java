package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.JobDTO;
import com.taskmanagement.app.dto.JobStatisticsDTO;
import com.taskmanagement.app.enums.TaskStatus;
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


    // Assign and Unassign
    @PutMapping("/set-user/{jobId}")
    public JobDTO setJobToUser (@PathVariable Integer jobId, @RequestParam(required = false) Integer userId) {
        return service.setJobToUser(jobId, userId);
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


    @GetMapping("/statistics")
    public List<JobStatisticsDTO> filterJobsWithStats(
            @RequestParam(required = false) Integer jobId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String title
    ) {
        return service.filterJobsWithStats(jobId, status, userId, title);
    }
}