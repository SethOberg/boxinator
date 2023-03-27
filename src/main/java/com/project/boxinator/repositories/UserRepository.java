package com.project.boxinator.repositories;

import com.project.boxinator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
