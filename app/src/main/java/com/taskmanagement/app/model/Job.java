package com.taskmanagement.app.model;

import com.taskmanagement.app.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_job_id", nullable = true)
    private Job parentJob;

    @OneToMany(mappedBy = "parentJob", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Job> subJobs = new HashSet<>();


    // User statistics
    // Filtering
    // Based on job status
}
