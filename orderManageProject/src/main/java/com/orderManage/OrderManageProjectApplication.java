package com.orderManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OrderManageProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OrderManageProjectApplication.class, args);
	}

	/* 外部Tomcatで動作するために必要 20240901*/
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OrderManageProjectApplication.class);
    }
}
