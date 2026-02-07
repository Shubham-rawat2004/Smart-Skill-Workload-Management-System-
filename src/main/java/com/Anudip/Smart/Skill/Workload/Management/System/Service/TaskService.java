package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TaskService {

    @PreAuthorize("hasRole('ADMIN')")
    Task createTask(Task task);

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    List<Task> getPendingTask();

    Task updateTaskStatus(Long taskId, String status);
}
