package com.cjufi.bookmanagement.repository;

import com.cjufi.bookmanagement.model.Role;
import com.cjufi.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User set role = :role where username = :username")// if it is a select query no need for modifying
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

}
