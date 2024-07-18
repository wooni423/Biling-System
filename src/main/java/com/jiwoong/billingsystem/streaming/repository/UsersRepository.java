package com.jiwoong.billingsystem.streaming.repository;

import com.jiwoong.billingsystem.streaming.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}
