package com.olx.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;
import com.olx.exception.InvalidAdvertisementIdException;

@Service
public class CategoryServiceDelegateImpl implements CategoryServiceDelegate {

	@Autowired
	private RestTemplate restTemplate;
	/*
	 * @Autowired private CircuitBreakerFactory circuitBreakerFactory;
	 */

	@Override
	public List<Map> getAllCategories() {
		List<Map> categories = this.restTemplate.getForObject("http://olx-masterdata/category", List.class);
		return categories;
	}

	@Override
	public CategoryDTO getCategory(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
			ResponseEntity<CategoryDTO> result = restTemplate.exchange("http://olx-masterdata/category/" + id,
					HttpMethod.GET, entity, new ParameterizedTypeReference<CategoryDTO>() {
					});

			return result.getBody();

		} catch (Exception e) {
			throw new InvalidAdvertisementIdException("" + id);
		}
	}

	public StatusDTO getStatus(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		try {
			ResponseEntity<StatusDTO> result1 = restTemplate.exchange("http://olx-masterdata/advertise/status/" + id,
					HttpMethod.GET, entity, new ParameterizedTypeReference<StatusDTO>() {
					});

			return result1.getBody();

		} catch (Exception e) {
			throw new InvalidAdvertisementIdException("" + id);
		}
	}

	/*
	 * @Override public List<Map> getAllCategories() { CircuitBreaker circuitBreaker
	 * = circuitBreakerFactory.create("circuitbreaker"); List<Map> categories =
	 * circuitBreaker.run(
	 * ()->this.restTemplate.getForObject("http://localhost:9001/category",
	 * List.class), throwable -> categoryServiceFallback() ); return categories; }
	 */
	public List<Map> categoryServiceFallback() {
		System.out.println("CIRCUIT BREAKER ENABLED!!! No Response From Category Service at this moment. "
				+ " Service will be back shortly - " + LocalDate.now());
		return null;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
