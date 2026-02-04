package com.Anudip.Smart.Skill.Workload.Management.System.Service;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.User;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.BadRequestException;
import com.Anudip.Smart.Skill.Workload.Management.System.Exception.ResourceNotFoundException;
import com.Anudip.Smart.Skill.Workload.Management.System.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user.getAvailabilityHours() < 0) {
            throw new BadRequestException("Availability hours cannot be negative");
        }
        return userRepository.save(user);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateAvailability(Long userId, int hours) {
        if (hours < 0) {
            throw new BadRequestException("Availability hours cannot be negative");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id" + userId + "not found!"));
        user.setAvailabilityHours(hours);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
