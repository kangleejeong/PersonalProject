package com.example.demo.controller.request;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Data
public class MemberRequest {
    private String id;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private String auth;

    public MemberRequest (String id, String password, String name, String birth, String phone, String auth){
        this.id=id;
        this.password=password;
        this.name=name;
        this.birth=birth;
        this.phone=phone;
        this.auth=auth;
    }
}