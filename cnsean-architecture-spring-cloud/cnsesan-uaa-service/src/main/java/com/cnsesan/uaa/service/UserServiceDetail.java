package com.cnsesan.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCrypt;

import com.cnsesan.uaa.jpa.UserDao;

//主要是负责用户信息获取的
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired private UserDao userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//String hash = BCrypt.hashpw("123456", BCrypt.gensalt() );
        return userRepository.findByUsername(username);
    }

}