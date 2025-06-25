package com.vsks.controller;

import com.vsks.dao.UserDAO;
import com.vsks.dto.User;
import com.vsks.service.UserService;
import com.vsks.vo.ResponseVO;
import com.vsks.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@Controller
@RequestMapping("/user/api/v1")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/findUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String findUser(@RequestBody UserVO userVO) {
        Optional<User> user = userDAO.findUser(userVO.getEmailId(), userVO.getPassword());
        String responseData = user.map(value -> "Hello " + value.getName()).orElse("Unable to find you at us, please check your email Id & password");
        return "{\"data\": \"" + responseData + "\"}";
    }

    @RequestMapping(value = "/consumeServiceA", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseVO consumeServiceA() {
        System.out.println("Consuming Service-A");
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceA();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service A");
        return responseVO;
    }

    @RequestMapping(value = "/consumeServiceB", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseVO consumeServiceB() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceB();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service B");
        return responseVO;
    }

    @RequestMapping(value = "/consumeServiceC", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseVO consumerServiceC() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceC();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service C");
        return responseVO;
    }

    @RequestMapping(value = "/consumeServiceD", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseVO consumerServiceD() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceD();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service D");
        return responseVO;
    }

    @RequestMapping(value = "/consumeServiceE", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseVO consumerServiceE() {
        ResponseVO responseVO = new ResponseVO();
        userService.consumeServiceE();
        responseVO.setStatus(200);
        responseVO.setMessage("Consumed Service E");
        return responseVO;
    }

}