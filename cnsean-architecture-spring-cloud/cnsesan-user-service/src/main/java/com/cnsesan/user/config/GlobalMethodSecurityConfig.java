package com.cnsesan.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法级别安全验证?
public class GlobalMethodSecurityConfig {
}