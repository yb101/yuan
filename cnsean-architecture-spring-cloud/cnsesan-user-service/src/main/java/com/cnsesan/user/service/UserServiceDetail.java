package com.cnsesan.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnsesan.user.component.AuthServiceClient;
import com.cnsesan.user.component.JWT;
import com.cnsesan.user.entity.User;
import com.cnsesan.user.entity.UserLoginDTO;
import com.cnsesan.user.exception.UserLoginException;
import com.cnsesan.user.jpa.UserDao;
import com.cnsesan.user.util.BPwdEncoderUtil;

@Service
public class UserServiceDetail {

    @Autowired
    private UserDao userRepository;
    
    @Autowired
    AuthServiceClient client;
    
    public User insertUser(String username,String password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }
    
    public UserLoginDTO login(String username,String password){
        User user=userRepository.findUserByUsername(username);
        if(user==null){
            throw new UserLoginException("用户不存在");
        }
        if(!BPwdEncoderUtil.matches(password, user.getPassword())){
            throw new UserLoginException("用户密码不对");
        }
        //dXNlci1zZXJ2aWNlOjEyMzQ1Ng== 是 user-service:123456的 base64编码
        JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==", "password", username, password);
        if(jwt==null){
            throw new RuntimeException("用户Token有问题");
        }
        UserLoginDTO dto=new UserLoginDTO();
        dto.setUser(user);
        dto.setJwt(jwt);
        
        return dto;
    }
    
}