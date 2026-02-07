package com.Anudip.Smart.Skill.Workload.Management.System.Dto;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.Task;
import java.util.List;

public class WorkloadResponse {

    private Long userId;
    private String userName;
    private int totalAssignedHours;
    private int remainingAvailability;
    private boolean overloaded;
    private List<Task> assignedTasks;

    public WorkloadResponse(
            Long userId,
            String userName,
            int totalAssignedHours,
            int remainingAvailability,
            boolean overloaded,
            List<Task> assignedTasks
    ) {
        this.userId = userId;
        this.userName = userName;
        this.totalAssignedHours = totalAssignedHours;
        this.remainingAvailability = remainingAvailability;
        this.overloaded = overloaded;
        this.assignedTasks = assignedTasks;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getTotalAssignedHours() {
        return totalAssignedHours;
    }

    public int getRemainingAvailability() {
        return remainingAvailability;
    }

    public boolean isOverloaded() {
        return overloaded;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }
}
