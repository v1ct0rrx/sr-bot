package com.vvelazquez.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableEncryptableProperties
public class SrBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrBotApplication.class, args);
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	}

}
