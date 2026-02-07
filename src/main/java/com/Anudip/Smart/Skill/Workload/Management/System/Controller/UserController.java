package com.Anudip.Smart.Skill.Workload.Management.System.Controller;

import com.Anudip.Smart.Skill.Workload.Management.System.Dto.WorkloadResponse;
import com.Anudip.Smart.Skill.Workload.Management.System.Entity.User;
import com.Anudip.Smart.Skill.Workload.Management.System.Service.UserService;
import com.Anudip.Smart.Skill.Workload.Management.System.Service.WorkloadService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final WorkloadService workloadService;
    private final UserService userService;

    public UserController(WorkloadService workloadService, UserService userService) {
        this.workloadService = workloadService;
        this.userService = userService;

    }
    @GetMapping("/{id}/workload")
    public ResponseEntity<WorkloadResponse> getUserWorkload(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(workloadService.getUserWorkload(id));
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }
@GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
}

    @PutMapping("/{id}/availability")
    public ResponseEntity<User> updateAvailability(
            @PathVariable Long id,
            @RequestParam int hours
    ) {
        return ResponseEntity.ok(userService.updateAvailability(id, hours));
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return  ResponseEntity.ok("User Deleted Successfully");
    }
}



