package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Skill;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.TaskStatus;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.BadRequestException;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.ResourceNotFoundException;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.SkillRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final SkillRepository skillRepository;

    public TaskServiceImp(TaskRepository taskRepository,
                          SkillRepository skillRepository) {
        this.taskRepository = taskRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Task createTask(Task task) {

        task.setTaskStatus(TaskStatus.PENDING);

        if (task.getRequiredSkills() != null) {
            task.getRequiredSkills().forEach(taskSkill -> {

                // attach task (owning side)
                taskSkill.setTask(task);

                // load managed Skill entity
                Long skillId = taskSkill.getSkill().getId();
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Skill not found with id " + skillId
                                )
                        );

                taskSkill.setSkill(skill);
            });
        }

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found with id " + id
                        )
                );
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getPendingTask() {
        return taskRepository.findByTaskStatus(TaskStatus.PENDING);
    }

    @Override
    public Task updateTaskStatus(Long taskId, String status) {
        Task task = getTaskById(taskId);

        try {
            TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
            task.setTaskStatus(taskStatus);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid task status: " + status);
        }

        return taskRepository.save(task);
    }
}
