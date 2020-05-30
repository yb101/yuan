package com.cnsesan.user.component;

import org.springframework.stereotype.Component;

//AuthServiceHystrix是一个默认的处理方式
@Component
public class AuthServiceHystrix implements AuthServiceClient{

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

}