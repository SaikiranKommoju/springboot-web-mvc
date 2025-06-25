package com.vsks.controller;

import com.vsks.dao.UserDAO;
import com.vsks.dto.User;
import com.vsks.service.UserService;
import com.vsks.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user/api/v1")
public class UserController {

    public UserController() {
        System.out.println("UserController :: no-param cons");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/findUser", method = RequestMethod.POST, consumes = "application/json")
    public String findUser(@RequestBody UserVO userVO) {
        Optional<User> user = userDAO.findUser(userVO.getEmailId(), userVO.getPassword());
        String responseData = user.map(value -> "Hello " + value.getName()).orElse("Unable to find you at us, please check your email Id & password");
        return responseData;
    }

    @RequestMapping(value = "/consumeServiceA", method = RequestMethod.POST)
    public String consumeServiceA() {
        System.out.println("Controller :: Consuming Service A ...");
        return "Consumed Service A";
    }

    @RequestMapping(value = "/consumeServiceB", method = RequestMethod.POST)
    public String consumeServiceB() {
        System.out.println("Controller :: Consuming Service B ...");
        return "Consumed Service A";
    }

    @RequestMapping(value = "/consumeServiceC", method = RequestMethod.POST, produces = "application/json")
    public String consumerServiceC() {
        System.out.println("Controller :: Consuming Service C ...");
        return "Consuming Service C";
    }

    @RequestMapping(value = "/consumeServiceD", method = RequestMethod.POST)
    public String consumerServiceD() {
        System.out.println("Controller :: Consuming Service D ...");
        return "Consuming Service D";
    }

    @RequestMapping(value = "/consumeServiceE", method = RequestMethod.POST)
    public String consumerServiceE() {
        System.out.println("Controller :: Consuming Service E ...");
        return "Consuming Service E";
    }

}
