package com.brewery.service.web.client;

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

	public BeerDto getBeerById(UUID uuid) {
		// il va faire un get sur le path http://localhost:8080/api/v1/beer/445454 retourner un objet de type
		// BeerDto
		BeerDto dto = restTemplate.getForObject(apihost + API_PATH_V1 + uuid.toString(), BeerDto.class);
		return dto;
	}

	public void setApihost(String apihost) { 
		this.apihost = apihost;
	}
}
