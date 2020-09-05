package com.skillone.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主工程中包含module的子工程需要在intellij中做到以下配置：
 * 1）在project structure中确保每一个module的jdk配置都是完全一致的
 * 2）在sources中分配正确的文件夹类型
 * 3）pom.xml右键添加到maven
 */
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);
	}

}
