package com.taskmanagement.app.model;

import com.taskmanagement.app.enums.JobRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "jobs")
@EqualsAndHashCode(exclude = "jobs")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Job> jobs = new HashSet<>();
}
