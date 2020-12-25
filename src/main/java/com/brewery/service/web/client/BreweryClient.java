package com.brewery.service.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.brewery.service.web.model.BeerDto;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sfg.brewery")
public class BreweryClient { // mssc-beer
	private final String API_PATH_V1 = "/api/v1/beer/";
	private String apihost;

	private final RestTemplate restTemplate;

	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	// put
	public void updateBeerById(UUID uuid, BeerDto beerDto) {
		restTemplate.put(apihost + API_PATH_V1 + uuid.toString(), beerDto);
	}

	// post
	public URI saveNewBeer(BeerDto beerDto) {
		URI uri = restTemplate.postForLocation(apihost + API_PATH_V1, beerDto);
		return uri;
	}

	// get
	public BeerDto getBeerById(UUID uuid) {
		return restTemplate.getForObject(apihost + API_PATH_V1 + uuid.toString(), BeerDto.class);
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}
}
