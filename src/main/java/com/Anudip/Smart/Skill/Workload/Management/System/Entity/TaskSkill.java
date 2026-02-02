package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;

// third table for many to many relatioonship which contains foreign key of both table
@Entity
@Data
public class TaskSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private int minimumProficiency;

}
