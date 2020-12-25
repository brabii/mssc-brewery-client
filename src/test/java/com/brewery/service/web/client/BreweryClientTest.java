package com.brewery.service.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brewery.service.web.model.BeerDto;

@SpringBootTest
public class BreweryClientTest {

	@Autowired
	BreweryClient BreweryClient;

	@Test
	public void getBeerById() {
		BeerDto dto = BreweryClient.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}
}
