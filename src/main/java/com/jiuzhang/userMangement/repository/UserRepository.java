package com.jiuzhang.userMangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jiuzhang.userMangement.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    List<User> findByIsDeleted(Boolean isDeleted);
}
