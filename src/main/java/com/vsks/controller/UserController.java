package com.vsks.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.vsks.dao.UserDAO;
import com.vsks.dto.User;
import com.vsks.service.UserService;
import com.vsks.vo.ResponseVO;
import com.vsks.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @PostMapping(value = "/findUser", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String findUser(@RequestBody UserVO userVO) {
        Optional<User> user = userDAO.findUser(userVO.getEmailId(), userVO.getPassword());
        String responseData = user.map(value -> "Hello " + value.getName()).orElse("Unable to find you at us, please check your email Id & password");
        return "{\"data\": \"" + responseData + "\"}";
    }

    @PostMapping(value = "/consumeServiceA", produces = APPLICATION_JSON_VALUE)
    public ResponseVO consumeServiceA() {
        System.out.println("Consuming Service-A");
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceA();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service A");
        return responseVO;
    }

    @PostMapping(value = "/consumeServiceB", produces = APPLICATION_JSON_VALUE)
    public ResponseVO consumeServiceB() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceB();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service B");
        return responseVO;
    }

    @PostMapping(value = "/consumeServiceC", produces = APPLICATION_JSON_VALUE)
    public ResponseVO consumerServiceC() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceC();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service C");
        return responseVO;
    }

    @PostMapping(value = "/consumeServiceD", produces = APPLICATION_JSON_VALUE)
    public ResponseVO consumerServiceD() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceD();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service D");
        return responseVO;
    }

    @PostMapping(value = "/consumeServiceE", produces = APPLICATION_JSON_VALUE)
    public ResponseVO consumerServiceE() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceE();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service E");
        return responseVO;
    }
}