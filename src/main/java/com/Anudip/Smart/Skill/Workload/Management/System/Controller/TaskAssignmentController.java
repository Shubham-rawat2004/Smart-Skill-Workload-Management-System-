package com.Anudip.Smart.Skill.Workload.Management.System.Controller;

import com.Anudip.Smart.Skill.Workload.Management.System.Service.TaskAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/assignment")
public class TaskAssignmentController {

    private final TaskAssignmentService taskAssignmentService;


    public TaskAssignmentController(TaskAssignmentService taskAssignmentService) {
        this.taskAssignmentService = taskAssignmentService;
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<String> assignTask(@PathVariable Long taskId){
        taskAssignmentService.assignTask(taskId);
        return  ResponseEntity.ok("Task Assigned Successfully");

    }
}
