package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.UserSkill;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.BadRequestException;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.ResourceNotFoundException;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.SkillRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.UserRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.UserSkillRepository;
import org.springframework.stereotype.Service;
@Service
public class UserSkillServIceImp implements UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public UserSkillServIceImp(
            UserSkillRepository userSkillRepository,
            UserRepository userRepository,
            SkillRepository skillRepository) {
        this.userSkillRepository = userSkillRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public UserSkill addUserSkill(UserSkill userSkill) {

        if (userSkill.getUser() == null || userSkill.getSkill() == null) {
            throw new BadRequestException("User and Skill must be provided");
        }

        Long userId = userSkill.getUser().getId();
        Long skillId = userSkill.getSkill().getId();

        userSkill.setUser(
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id " + userId))
        );

        userSkill.setSkill(
                skillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Skill not found with id " + skillId))
        );

        return userSkillRepository.save(userSkill);
    }
}
