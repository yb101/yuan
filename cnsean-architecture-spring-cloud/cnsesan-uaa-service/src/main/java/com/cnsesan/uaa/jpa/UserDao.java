package com.cnsesan.uaa.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnsesan.uaa.entity.User;

//UserDao 类 是一个接口，使用 JPA 的方式
public interface UserDao extends JpaRepository<User, Long> {
	User findByUsername(String username);
}