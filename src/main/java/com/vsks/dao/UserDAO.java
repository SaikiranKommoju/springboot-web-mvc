package com.vsks.dao;

import com.vsks.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> loadAllUsers();

    Optional<User> findUser(String emailId, String password);

    Optional<User> findUserByEmailId(String emailId);
}
