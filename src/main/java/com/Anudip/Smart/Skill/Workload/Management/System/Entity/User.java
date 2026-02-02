package com.Anudip.Smart.Skill.Workload.Management.System.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @Email
    private String email;

    private int availablityHours;

    @Enumerated(EnumType.STRING)
    private Role role;

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSkill> skill;
}
