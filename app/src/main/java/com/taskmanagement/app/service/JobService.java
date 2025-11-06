package com.taskmanagement.app.service;

import com.taskmanagement.app.dto.JobDTO;
import com.taskmanagement.app.dto.JobStatisticsDTO;
import com.taskmanagement.app.enums.TaskStatus;
import com.taskmanagement.app.exception.JobNotFoundException;
import com.taskmanagement.app.exception.UserNotFoundException;
import com.taskmanagement.app.mapper.JobMapper;
import com.taskmanagement.app.model.Job;
import com.taskmanagement.app.model.User;
import com.taskmanagement.app.repository.JobRepository;
import com.taskmanagement.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, JobMapper jobMapper, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.userRepository = userRepository;
    }


    public JobDTO createJob(JobDTO dto) {
        Job job = jobMapper.toEntity(dto);
        Job saved = jobRepository.save(job);
        return jobMapper.toDTO(saved);
    }


    public JobDTO createSubJob(JobDTO dto, Integer parentJobId) {
        Job parent = jobRepository.findById(parentJobId)
                .orElseThrow(() -> new JobNotFoundException(parentJobId));

        Job subJob = jobMapper.toEntity(dto);
        subJob.setParentJob(parent);
        Job saved = jobRepository.save(subJob);
        return jobMapper.toDTO(saved);
    }


    public JobDTO setJobToUser(Integer jobId, Integer userId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));

        // Assign user
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            job.setUser(user);
        } else {
            // Unassign user
            job.setUser(null);
        }
        Job updated = jobRepository.save(job);
        return jobMapper.toDTO(updated);
    }


    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }


    public JobDTO getJobById(Integer id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        return jobMapper.toDTO(job);
    }


    public JobDTO updateJob(Integer id, JobDTO dto) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));

        existing.setTitle(dto.title());
        existing.setDescription(dto.description());
        existing.setStatus(dto.status());

        return jobMapper.toDTO(jobRepository.save(existing));
    }

    public void deleteJob(Integer id) {
        jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
        jobRepository.deleteById(id);
    }


    public List<JobStatisticsDTO> filterJobsWithStats(Integer jobId, TaskStatus status, Integer userId, String title, Integer projectId) {
        List<Job> jobs = jobRepository.filterJobsWithSubJobs(jobId, status, userId, title, projectId);

        return jobs.stream()
                .map(this::toStatiscsDTO)
                .toList();
    }

    private JobStatisticsDTO toStatiscsDTO(Job job) {
        int totalSubJobs = job.getSubJobs().size();

        return new JobStatisticsDTO(
                job.getId(),
                job.getTitle(),
                job.getStatus(),
                job.getUser() != null ? job.getUser().getId() : null,
                job.getUser() != null ? job.getUser().getName() : null,
                totalSubJobs



                
        );
    }
}