package com.brewery.service.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brewery.service.web.model.BeerDto;
import com.brewery.service.web.model.CustomerDto;

@SpringBootTest
public class BreweryClientTest {

	@Autowired
	BreweryClient breweryClient;

	@Test
	public void testDeleteBeer() {
		breweryClient.deleteBeer(UUID.randomUUID());
	}

	@Test
	public void testUpdateBeerById() {
		BeerDto dto = BeerDto.builder().beerName("rabii").build();
		breweryClient.updateBeerById(UUID.randomUUID(), dto);
	}

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
		System.err.println(dto);
		assertNotNull(dto);
	}

	@Test
	public void testGetCustomerById() {
		CustomerDto dto = breweryClient.getCustomerById(UUID.randomUUID());
		assertNotNull(dto);

	}

//	@Test
//	void testDeleteCustomerById() throws Exception {
//		breweryClient.deleteCustomerById(UUID.randomUUID());
//	}

	@Test
	void testUpdateCustomerById() {
		CustomerDto dto = CustomerDto.builder().customerName("rabii").build();
		breweryClient.updateCustomerById(UUID.randomUUID(), dto);
	}

	@Test
	void testSaveNewCustomer() {
		CustomerDto dto = CustomerDto.builder().customerName("rabii").build();
		URI uri = breweryClient.saveNewCustomer(dto);
		assertNotNull(uri);
		System.out.println(uri.toString());

	}
}
