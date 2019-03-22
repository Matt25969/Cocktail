package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qa.domain.Cocktail;
import com.qa.service.CocktailService;

@RestController
public class CocktailController {
	@Autowired
	public CocktailController( CocktailService service, RestTemplate restTemplate) {
		this.service = service;
		this.restTemplate = restTemplate;
	}
	
	public CocktailController() {
		// TODO Auto-generated constructor stub
	}

	public CocktailService service;

	
	private RestTemplate restTemplate;

	@RequestMapping("/getAllCocktails")
	public List<Cocktail> getAllCocktails() {

		return service.findAll();
	}

	@PostMapping(value = "/createCocktail")
	public ResponseEntity<Cocktail> createCocktail(@RequestBody Cocktail cocktail) {
		Cocktail retVal = service.createCocktail(cocktail);
		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}

	@RequestMapping("/deleteCocktail")
	public String deleteCocktail(Cocktail cocktail) {

		return service.deleteCocktail(cocktail);

	}

	@RequestMapping("/updateCocktail")
	public String updateCocktail(Cocktail cocktail) {

		return service.updateCocktail(cocktail);

	}

	@GetMapping("/getMicro")
	public String getMicro() {

		ResponseEntity<String> exchangeCocktail = restTemplate.exchange("http://localhost:8081/getMicro",
				HttpMethod.GET, null, String.class);
		return exchangeCocktail.getBody();

	}

}
