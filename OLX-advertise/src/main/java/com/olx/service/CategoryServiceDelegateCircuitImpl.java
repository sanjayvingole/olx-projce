package com.olx.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CategoryServiceDelegateCircuitImpl implements CategoryServiceDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name="CATEGORY",fallbackMethod = "fallbackForGetCategory")
	public CategoryDTO getCategory(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<CategoryDTO> result = restTemplate.exchange("http://olx-masterdata/advertise/category/"+id,
                HttpMethod.GET, entity, new ParameterizedTypeReference<CategoryDTO>() {});

        return result.getBody();
		
	}

	@Override
	@CircuitBreaker(name="STATUS",fallbackMethod = "fallbackForGetStatus")
	public StatusDTO getStatus(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<StatusDTO> result1 = restTemplate.exchange("http://olx-masterdata/advertise/status/"+id,
		                                HttpMethod.GET, entity, new ParameterizedTypeReference<StatusDTO>() {});
		
		
		
		return result1.getBody();
	}
	
	public CategoryDTO fallbackForGetCategory(Integer id,Throwable throwable)
	{
		System.out.println("Category service failed:"+throwable);
		return null;
	}
	
	public StatusDTO fallbackForGetStatus(Integer id,Throwable throwable)
	{
		System.out.println("Status service failed:"+throwable);
		return null;
	}

	@Override
	public List<Map> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
