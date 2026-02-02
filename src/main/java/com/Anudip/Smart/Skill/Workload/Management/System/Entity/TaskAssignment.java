package com.Anudip.Smart.Skill.Workload.Management.System.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    private LocalDate assignedDate;

}
