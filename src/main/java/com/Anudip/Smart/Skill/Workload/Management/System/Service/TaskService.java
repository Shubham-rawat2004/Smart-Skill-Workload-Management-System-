package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    List<Task> getPendingTask();

    Task updateTaskStatus(Long taskId, String status);
}
