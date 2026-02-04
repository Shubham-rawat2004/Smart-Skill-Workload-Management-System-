package com.Anudip.Smart.Skill.Workload.Management.System.Repository;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

    List<UserSkill> findByUser_Id(Long userId);

    List<UserSkill> findBySkill_Id(Long skillId);
}

