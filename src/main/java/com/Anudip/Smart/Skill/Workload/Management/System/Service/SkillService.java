package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Skill;

import java.util.List;


public interface SkillService {

    Skill createSkill(Skill skill);

    List<Skill> getAllSkills();

    Skill getSkillById(Long skillId);

    void deleteSkill(Long skillId);
}
