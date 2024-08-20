package com.zeroskill.cms_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zeroskill.common", "com.zeroskill.cms_api"})
public class CmsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApiApplication.class, args);
    }

}
