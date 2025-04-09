package com.pndev.transacao_simpls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransacaoSimplsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoSimplsApplication.class, args);
	}

}
