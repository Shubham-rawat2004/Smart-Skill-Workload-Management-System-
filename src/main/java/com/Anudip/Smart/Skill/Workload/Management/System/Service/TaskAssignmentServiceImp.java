package com.Anudip.Smart.Skill.Workload.Management.System.Service;


import com.Anudip.Smart.Skill.Workload.Management.System.Entity.*;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.TaskAssignmentRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.TaskRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskAssignmentServiceImp implements TaskAssignmentService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;

    public TaskAssignmentServiceImp(
            TaskRepository taskRepository,
            UserRepository userRepository,
            TaskAssignmentRepository taskAssignmentRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskAssignmentRepository = taskAssignmentRepository;
    }

    @Override
    public void assignTask(Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (task.getTaskStatus() != TaskStatus.PENDING) {
            throw new IllegalStateException("Task is already assigned or completed");
        }

        List<User> users = userRepository.findAll();

        User bestUser = null;
        double bestScore = 0;

        for (User user : users) {

            if (!isUserEligible(user, task)) {
                continue;
            }

            double score = calculateScore(user, task);

            if (score > bestScore) {
                bestScore = score;
                bestUser = user;
            }
        }

        if (bestUser == null) {
            throw new RuntimeException("No suitable user found for task");
        }

        TaskAssignment assignment = new TaskAssignment();
        assignment.setTask(task);
        assignment.setUser(bestUser);
        assignment.setAssignedDate(LocalDate.now());

        task.setTaskStatus(TaskStatus.ASSIGNED);
        bestUser.setAvailabilityHours(
                bestUser.getAvailabilityHours() - task.getRequiredEffortHours()
        );

        taskAssignmentRepository.save(assignment);
    }

    // ---------- HELPER METHODS ----------

    private boolean isUserEligible(User user, Task task) {

        if (user.getAvailabilityHours() < task.getRequiredEffortHours()) {
            return false;
        }

        for (TaskSkill required : task.getRequiredSkills()) {

            boolean hasSkill = user.getSkills().stream()
                    .anyMatch(us ->
                            us.getSkill().getId().equals(required.getSkill().getId())
                                    && us.getProficiencyLevel() >= required.getMinimumProficiency()
                    );

            if (!hasSkill) {
                return false;
            }
        }
        return true;
    }

    private double calculateScore(User user, Task task) {

        int matchedSkills = 0;
        int totalProficiency = 0;

        for (TaskSkill required : task.getRequiredSkills()) {
            for (UserSkill us : user.getSkills()) {
                if (us.getSkill().getId().equals(required.getSkill().getId())) {
                    matchedSkills++;
                    totalProficiency += us.getProficiencyLevel();
                }
            }
        }

        double skillMatchRatio =
                (double) matchedSkills / task.getRequiredSkills().size();

        double avgProficiency =
                matchedSkills == 0 ? 0 : (double) totalProficiency / matchedSkills;

        double availabilityRatio =
                (double) user.getAvailabilityHours() / task.getRequiredEffortHours();

        return (skillMatchRatio * 0.5)
                + ((avgProficiency / 10) * 0.3)
                + (availabilityRatio * 0.2);
    }
}

