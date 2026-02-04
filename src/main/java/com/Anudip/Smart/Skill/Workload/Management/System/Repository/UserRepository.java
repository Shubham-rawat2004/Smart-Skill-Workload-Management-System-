package com.Anudip.Smart.Skill.Workload.Management.System.Repository;

import com.Anudip.Smart.Skill.Workload.Management.System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
