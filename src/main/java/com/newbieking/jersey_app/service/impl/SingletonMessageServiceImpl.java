package com.newbieking.jersey_app.service.impl;

import com.newbieking.jersey_app.service.MessageService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;


@Singleton
@Service
public class SingletonMessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "Singleton Message Service";
    }
}
