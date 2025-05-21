package com.newbieking.jersey_app.service.impl;

//@Contract
public class MessageServiceImpl /*implements MessageService */{
//    @Override
    public String getMessage() {
        return "Hello from " + Thread.currentThread().getName();
    }
}
