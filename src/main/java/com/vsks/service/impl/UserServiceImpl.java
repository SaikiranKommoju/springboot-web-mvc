package com.vsks.service.impl;

import com.vsks.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public void consumeServiceA() {
        System.out.println("Service :: Consuming Service A ...");
    }

    public void consumeServiceB() {
        System.out.println("Service :: Consuming Service B ...");
    }

    public void consumeServiceC() {
        System.out.println("Service :: Consuming Service C ...");
    }

    public void consumeServiceD() {
        System.out.println("Service :: Consuming Service D ...");
    }

    public void consumeServiceE() {
        System.out.println("Service :: Consuming Service E ...");
    }

}
