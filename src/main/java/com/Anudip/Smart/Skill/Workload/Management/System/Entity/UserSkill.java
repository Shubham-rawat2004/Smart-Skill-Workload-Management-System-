package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Data
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSkillId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;


    @ManyToOne // foreign key on many's  side
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private int proficiencyLevel;
}
