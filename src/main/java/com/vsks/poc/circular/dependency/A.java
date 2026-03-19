package com.vsks.poc.circular.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class A {

    {
        System.out.println("A instantiated, A: " + this);
    }

    private B b;

    public A(@Lazy B b) {
        System.out.println("A :: A = " + this);
        //System.out.println("A :: B = " + b); // throws BeanCurrentlyInCreationException if un-commented because we tried to use B as soon as A is created
        this.b = b;
        //System.out.println("A :: B = " + this.b); // throws BeanCurrentlyInCreationException if un-commented
    }

    @PostConstruct
    public void init() {
        System.out.println("A :: init");
    }

    public B getB() {
        return b;
    }
}
