package com.Anudip.Smart.Skill.Workload.Management.System.Controller;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.UserSkill;
import com.Anudip.Smart.Skill.Workload.Management.System.Service.UserSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-skills")
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping
    public ResponseEntity<UserSkill> addUserSkill(@RequestBody UserSkill userSkill) {
        UserSkill saved = userSkillService.addUserSkill(userSkill);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
