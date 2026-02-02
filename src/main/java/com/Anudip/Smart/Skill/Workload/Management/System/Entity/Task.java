package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private int requiredEffortHours;

    private String priority;
    private LocalDateTime deadline;

    private String projectName;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskSkill> requiredSkills;

}
