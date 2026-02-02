package com.Anudip.Smart.Skill.Workload.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
        System.out.println("Let's begin building SmartSkillManagement Project");
	}
}


//  Relationships
//  1.) User ↔ Skill → Many-to-many (user_skills)
//  2.) Task ↔ Skill → Many-to-many (task_required_skills)
//  3.) Project → Task → One-to-many
//  4.) User → Task → One-to-many (assigned tasks)