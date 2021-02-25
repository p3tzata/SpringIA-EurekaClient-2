package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Ingredient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;




@RestController
@RequestMapping(value = "/public/ingredientConsumeAnotherService",produces = "application/json")
@CrossOrigin(origins="*")
public class IngredientConsumeAnotherService {
	
	
	
	private RestTemplate restTemplate;
	
	
	
	@Autowired
	public IngredientConsumeAnotherService(@LoadBalanced RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}



	//@RateLimiter(name = "someRemoteSvc")
	////@Bulkhead(name = "someRemoteSvc")
//	@Retry(name = "someRemoteSvc", fallbackMethod = "getIngredient_fallbackMethod")
//	@TimeLimiter(name = "someRemoteSvc")
	@CircuitBreaker(name = "someRemoteSvc", fallbackMethod = "getIngredient_fallbackMethod")
	@GetMapping(path = "/{ingredientId}")
	@ResponseStatus(value = HttpStatus.OK)
	public Ingredient getIngredient (@PathVariable Long ingredientId) {
		//Ingredient ingredient = restTemplate.getForObject("http://eurekaClient-1/public/ingredient/{id}", Ingredient.class,ingredientId);
		//Uncoment below to test Hystrix.
		Ingredient ingredient = restTemplate.getForObject("http://eurekaClient-1/public/ingredient999/{id}", Ingredient.class,ingredientId);
		return ingredient;
		
	}
	
	public Ingredient getIngredient_fallbackMethod (@PathVariable Long ingredientId,Exception e) {
		
		Ingredient ingredient = new Ingredient(999L, "Default name", "Default_desc");
		
		return ingredient;
		
	}
	
	

}
