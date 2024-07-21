package org.archadu.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.dev33.satoken.SaManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.archadu.core","org.archadu.core.service","org.archadu.core.controller","org.archadu.core.util", "org.archadu.core.config", "org.archadu.core.api"})
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
	}
}
