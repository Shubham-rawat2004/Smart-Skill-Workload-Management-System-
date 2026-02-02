package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSkillId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // foreign key on many's  side
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private int proficiencyLevel;
}
