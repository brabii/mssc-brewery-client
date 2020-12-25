package com.brewery.service.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brewery.service.web.model.BeerDto;

@SpringBootTest
public class BreweryClientTest {

	@Autowired
	BreweryClient breweryClient;

	@Test
	public void testSaveNewBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("rabii").build();
		URI uri = breweryClient.saveNewBeer(beerDto);
		assertNotNull(uri);
		System.out.println(uri.toString());
	}

	@Test
	public void getBeerById() {
		BeerDto dto = breweryClient.getBeerById(UUID.randomUUID());
		assertNotNull(dto);
	}
}
