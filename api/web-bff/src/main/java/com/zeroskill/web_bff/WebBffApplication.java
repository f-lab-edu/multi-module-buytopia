package com.zeroskill.web_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.zeroskill.web_bff.client")
public class WebBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBffApplication.class, args);
    }

}
