package com.vsks.dao.impl;

import com.vsks.dao.UserDAO;
import com.vsks.db.DBConnection;
import com.vsks.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    //@Autowired
    private DBConnection dbConnection;

    private static final List<User> ALL_USERS;

    static {
        ALL_USERS = Arrays.asList(
                new User(1L, "Sai Kiran Kommoju", "saikiran_kommoju@vsks.com", "Back-End Developer", Arrays.asList("DEV", "UI"), "saikiran123", true),
                new User(2L, "Linga Kuruba", "linga_kuruba@vsks.com", "Front-End Developer", Arrays.asList("UI", "UX"), "linga456", true),
                new User(3L, "Sudheer Podilapu", "sudheer_podilapu@vsks.com", "DB Developer", Arrays.asList("DB", "DBA"), "sudheer789", true)
        );
    }

    @Override
    public List<User> loadAllUsers() {
        return ALL_USERS;
    }

    @Override
    public Optional<User> findUser(String emailId, String password) {
        return ALL_USERS.stream().filter(it -> emailId.trim().equalsIgnoreCase(it.getEmailId()) && password.equals(it.getPassword())).findFirst();
    }

    @Override
    public Optional<User> findUserByEmailId(String emailId) {
        return ALL_USERS.stream().filter(it -> emailId.trim().equalsIgnoreCase(it.getEmailId())).findFirst();
    }
}
