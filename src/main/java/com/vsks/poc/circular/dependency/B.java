package com.vsks.poc.circular.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class B {

    {
        System.out.println("B instantiated");
    }

    private A a;

    public B(A a) {
        System.out.println("B :: B = " + this);
        System.out.println("B :: A = " + a);
        this.a = a;
    }

    @PostConstruct
    public void init() {
        System.out.println("B :: init");
    }

    public void printHello() {
        System.out.println("Hello");
    }
}
