package com.zcm.server;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.*;

/**
 * @program: spring-cloud
 * @ClassName ServerMain
 * @Description
 * @Author zcm
 * @Date 2021/5/21 16:50
 * @Version V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }

}
