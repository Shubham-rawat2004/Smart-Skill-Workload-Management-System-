package com.Anudip.Smart.Skill.Workload.Management.System.Repository;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskStatus(TaskStatus taskStatus);

}
