package com.brewery.service.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "rest.template")
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	private int connManagerMaxTotal;
	private int connManagerDefaultMaxPerRoute;//=20
	private int requestTimeOut;
	private int socketTimeOut;

	public ClientHttpRequestFactory clientHttpRequestFactory(){
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(connManagerMaxTotal);// max connexion au serveur 100
		connectionManager.setDefaultMaxPerRoute(connManagerDefaultMaxPerRoute);
		
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectionRequestTimeout(requestTimeOut)//3000
				.setSocketTimeout(socketTimeOut)//3000
				.build();
		
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.build();
	return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());
	}

	public void setConnManagerMaxTotal(int connManagerMaxTotal) {
		this.connManagerMaxTotal = connManagerMaxTotal;
	}

	public void setConnManagerDefaultMaxPerRoute(int connManagerDefaultMaxPerRoute) {
		this.connManagerDefaultMaxPerRoute = connManagerDefaultMaxPerRoute;
	}

	public void setRequestTimeOut(int requestTimeOut) {
		this.requestTimeOut = requestTimeOut;
	}

	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}
	
}
