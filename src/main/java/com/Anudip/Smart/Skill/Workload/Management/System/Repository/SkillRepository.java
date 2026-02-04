package com.Anudip.Smart.Skill.Workload.Management.System.Repository;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name);
}
