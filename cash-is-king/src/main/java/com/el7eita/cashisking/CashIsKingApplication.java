package com.el7eita.cashisking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CashIsKingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashIsKingApplication.class, args);
	}

}
