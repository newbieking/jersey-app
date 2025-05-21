package com.newbieking.jersey_app.resource;


import com.newbieking.jersey_app.service.MessageService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello") // 定义路径：http://localhost:8080/your-app/hello
public class HelloResource {

    @Inject
    private MessageService messageService;

    @GET // HTTP GET请求
    @Produces(MediaType.TEXT_PLAIN) // 返回文本格式
    public String sayHello() {
        return "Hello, Jersey!";
    }

    @GET // HTTP GET请求
    @Path("msg")
    @Produces(MediaType.TEXT_PLAIN) // 返回文本格式
    public String message() {
        return messageService.getMessage();
    }
}
