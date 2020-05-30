package com.cnsesan.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

//该类是配置OAuth2相关内容
//keytool -genkeypair -alias cnsesan-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=cnsesan,O=cnsesan,L=zurich,S=zurich,C=CH" -keypass cnsesan123 -keystore cnsesan-jwt.jks -storepass cnsesan123
//keytool -list -rfc --keystore cnsesan-jwt.jks | openssl x509 -inform pem -pubkey
@Configuration
@EnableAuthorizationServer // 开启授权服务功能
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    // 配置客户端基本信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("user-service")// 创建一个客户端Client 名字是"user-service"
                .secret("123456")// 客户端密码
                .scopes("service")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .accessTokenValiditySeconds(3600); // 设置 token 过期时间
    }

    // 配置授权 token 的节点和 token 服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
        		.tokenEnhancer(jwtTokenEnhancer())
        		//.userDetailsService(userServiceDetail)// 读取验证用户的信息
                .authenticationManager(authenticationManager); // 开启密码验证，来源于 WebSecurityConfigurerAdapter
    }

    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    private JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("cnsesan-jwt.jks"),
                "cnsesan123".toCharArray());// cnsesan123 为 password
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("cnsesan-jwt"));
        return converter;
    }
}