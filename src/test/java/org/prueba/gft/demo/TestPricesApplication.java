package org.prueba.gft.prices;

import org.springframework.boot.SpringApplication;

public class TestPricesApplication {

	public static void main(String[] args) {
		SpringApplication.from(PricesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
