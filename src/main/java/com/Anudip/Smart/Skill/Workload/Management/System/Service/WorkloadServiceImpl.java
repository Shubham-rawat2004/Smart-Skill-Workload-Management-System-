package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Dto.WorkloadResponse;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.TaskAssignment;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.TaskStatus;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.User;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.ResourceNotFoundException;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.TaskAssignmentRepository;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final UserRepository userRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;

    public WorkloadServiceImpl(
            UserRepository userRepository,
            TaskAssignmentRepository taskAssignmentRepository
    ) {
        this.userRepository = userRepository;
        this.taskAssignmentRepository = taskAssignmentRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Override
    public WorkloadResponse getUserWorkload(Long userId) {

        // 1. Validate user
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id " + userId
                        )
                );

        // 2. Get all assignments for user
        List<TaskAssignment> assignments =
                taskAssignmentRepository.findByUser_Id(userId);

        // 3. Filter active tasks
        List<Task> activeTasks = assignments.stream()
                .map(TaskAssignment::getTask)
                .filter(task ->
                        task.getTaskStatus() == TaskStatus.ASSIGNED ||
                                task.getTaskStatus() == TaskStatus.IN_PROGRESS
                )
                .collect(Collectors.toList());

        // 4. Calculate workload
        int totalAssignedHours = activeTasks.stream()
                .mapToInt(Task::getRequiredEffortHours)
                .sum();

        boolean overloaded =
                totalAssignedHours > user.getAvailabilityHours();

        // 5. Build response
        return new WorkloadResponse(
                user.getId(),
                user.getName(),
                totalAssignedHours,
                user.getAvailabilityHours(),
                overloaded,
                activeTasks
        );
    }
}
