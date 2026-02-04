package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private int requiredEffortHours;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDateTime deadline;
    private String projectName;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskSkill> requiredSkills;
}
