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

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import reactor.core.publisher.Mono;




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



	//@RateLimiter(name = "someRemoteSvc",fallbackMethod = "getIngredient_fallbackMethod_by_RateLimiter")
	//@Bulkhead(name = "someRemoteSvc")
	//@Retry(name = "someRemoteSvc", fallbackMethod = "getIngredient_fallbackMethod_by_Retry")
	//@CircuitBreaker(name = "someRemoteSvc", fallbackMethod = "getIngredient_fallbackMethod_by_CircuitBreaker")
	
	//This TimeLImiter not work at this time. This flag only works when you use TimeLimiter to decorate a method which returns a Future.
	//@TimeLimiter(name = "someRemoteSvc",fallbackMethod = "getIngredient_fallbackMethod_by_TimeLimiter" )
	
	
	@GetMapping(path = "/{ingredientId}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<Ingredient> getIngredient (@PathVariable Long ingredientId) {
		//Ingredient ingredient = restTemplate.getForObject("http://eurekaClient-1/public/ingredient/{id}", Ingredient.class,ingredientId);
		//Uncoment below to test Hystrix.
		//Ingredient ingredient = restTemplate.getForObject("http://eurekaClient-1/public/ingredient/{id}", Ingredient.class,ingredientId);
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Ingredient ingredient = new Ingredient(ingredientId,"Some name","Some desc");
		return Mono.just(ingredient);
		
	}
	
	public Mono<Ingredient> getIngredient_fallbackMethod_by_TimeLimiter (@PathVariable Long ingredientId,Exception e) {
		
		Ingredient ingredient = new Ingredient(999L, "Default name By @TimeLimiter", "Default_desc");
		
		return Mono.just(ingredient);
		
	}
	
	
	public Mono<Ingredient> getIngredient_fallbackMethod_by_CircuitBreaker (@PathVariable Long ingredientId,Exception e) {
		
		Ingredient ingredient = new Ingredient(999L, "Default name By @CircuitBreaker", "Default_desc");
		
		return Mono.just(ingredient);
		
	}
	
	public Mono<Ingredient> getIngredient_fallbackMethod_by_RateLimiter (@PathVariable Long ingredientId,Exception e) {
		
		Ingredient ingredient = new Ingredient(999L, "Default name By @RateLimiter", "Default_desc");
		
		return Mono.just(ingredient);
		
	}
	
	
	public Mono<Ingredient> getIngredient_fallbackMethod_by_Retry (@PathVariable Long ingredientId,Exception e) {
		
		Ingredient ingredient = new Ingredient(999L, "Default name By @Retry", "Default_desc");
		
		return Mono.just(ingredient);
		
	}
	
	

}
