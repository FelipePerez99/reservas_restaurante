package com.app.reservasR.domain.repository;

import com.app.reservasR.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
