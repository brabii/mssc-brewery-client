package com.brewery.service.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.brewery.service.web.model.BeerDto;
import com.brewery.service.web.model.CustomerDto;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sfg.brewery")
public class BreweryClient { // mssc-beer
	private final String BEER_PATH_V1 = "/api/v1/beer/";
	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apihost;

	private final RestTemplate restTemplate;

	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public void deleteBeer(UUID uuid) {
		restTemplate.delete(apihost + BEER_PATH_V1 + uuid.toString());
	}

	public void updateBeerById(UUID uuid, BeerDto beerDto) {
		restTemplate.put(apihost + BEER_PATH_V1 + uuid.toString(), beerDto);
	}

	public URI saveNewBeer(BeerDto beerDto) {
		URI uri = restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
		return uri;
	}

	public BeerDto getBeerById(UUID uuid) {
		return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
	}

	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
	}

	public void deleteCustomerById(UUID uuid) {
		restTemplate.delete(apihost + CUSTOMER_PATH_V1 + uuid + toString());
	}

	public void updateCustomerById(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(apihost + CUSTOMER_PATH_V1 + uuid.toString(), customerDto);
	}

	public URI saveNewCustomer(CustomerDto customerDto) {
		URI uri = restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
		return uri;
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}
}
