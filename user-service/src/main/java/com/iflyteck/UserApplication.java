package com.iflyteck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
// 使用该注解兼容性好，不仅支持eureka也支持zookeeper等，使用@EnableEurekaClient只能注册到eureka中，故官方文档也推荐@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.iflyteck.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
