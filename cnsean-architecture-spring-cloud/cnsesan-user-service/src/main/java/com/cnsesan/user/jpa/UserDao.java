package com.cnsesan.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnsesan.user.entity.User;

//UserDao 类 是一个接口，使用 JPA 的方式
public interface UserDao extends JpaRepository<User, Long> {
	User findUserByUsername(String username);
}