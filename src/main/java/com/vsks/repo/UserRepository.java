package com.vsks.repo;

import com.vsks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.emailId = :emailId AND u.active = true")
    Optional<User> findUserByEmailId(@Param("emailId") String emailId);
}
