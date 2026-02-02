package com.Anudip.Smart.Skill.Workload.Management.System.Repository;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    List<TaskAssignment> findByUserId(Long userId);
}
