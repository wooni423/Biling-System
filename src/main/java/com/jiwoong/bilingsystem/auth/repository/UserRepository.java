package com.jiwoong.bilingsystem.auth.repository;

import com.jiwoong.bilingsystem.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {


}
