package com.example.batteryprice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BatteryPriceApplication {

	private static final String URL = "https://f98781a9-94fd-462d-8469-89b7b894e980.mock.pstmn.io/price";

	@Bean
	public WebClient createWebClient() {
		 return WebClient.builder().baseUrl(URL).build();
	}
	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BatteryPriceApplication.class, args);
	}

}
