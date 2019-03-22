package com.qa.rest;

import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qa.domain.Cocktail;
import com.qa.service.CocktailService;

@RunWith(SpringRunner.class)

public class MockitoTest {

	@InjectMocks
	CocktailController controller;

	@Mock
	CocktailService service;

	@Mock
	RestTemplate restTemp;

	private static final Cocktail MOCK_COCKTAIL_1 = new Cocktail(1L, "Vodka somthing");
	private static final Cocktail MOCK_COCKTAIL_2 = new Cocktail(2L, "Whiskey Doo-dah");
	private static final String MOCK_DELETE_RESPONSE = "Cocktail Successfully deleted";
	private static final String MOCK_URL = "http://localhost:8081/getMicro";
	private static final String MOCK_MICRO_RESPONSE = "Micro Service Hit";

	private static final ResponseEntity<String> MOCK_MICRO_BODY_RESPONSE = new ResponseEntity<>("Cocktail",  HttpStatus.OK);

	@Test
	public void getCocktailsTest() {
		List<Cocktail> MOCK_LIST = new ArrayList<>();
		MOCK_LIST.add(MOCK_COCKTAIL_1);
		MOCK_LIST.add(MOCK_COCKTAIL_2);
		Mockito.when(service.findAll()).thenReturn(MOCK_LIST);
		assertEquals(MOCK_LIST, controller.getAllCocktails());
		Mockito.verify(service).findAll();
	}

	@Test
	public void deleteCocktailTest() {
		Mockito.when(service.deleteCocktail(MOCK_COCKTAIL_1)).thenReturn(MOCK_DELETE_RESPONSE);
		assertEquals(MOCK_DELETE_RESPONSE, controller.deleteCocktail(MOCK_COCKTAIL_1));
		Mockito.verify(service).deleteCocktail(MOCK_COCKTAIL_1);
	}

	@Test
	public void updateCocktailTest() {
		Mockito.when(service.updateCocktail(MOCK_COCKTAIL_1)).thenReturn(MOCK_COCKTAIL_1.toString());

		assertEquals(MOCK_COCKTAIL_1.toString(), controller.updateCocktail(MOCK_COCKTAIL_1));
		Mockito.verify(service).updateCocktail(MOCK_COCKTAIL_1);
	}

	@Test
	public void createCocktailTest() {

		Mockito.when(service.createCocktail(MOCK_COCKTAIL_1)).thenReturn(MOCK_COCKTAIL_1);

		assertEquals(MOCK_COCKTAIL_1, controller.createCocktail(MOCK_COCKTAIL_1).getBody());
		Mockito.verify(service).createCocktail(MOCK_COCKTAIL_1);
	}

	@Test

	public void microTest() {
		
		Mockito.when(restTemp.exchange(MOCK_URL, HttpMethod.GET, null,  String.class)).thenReturn(MOCK_MICRO_BODY_RESPONSE);
		
		assertEquals(MOCK_MICRO_BODY_RESPONSE.getBody(), controller.getMicro());
		
		Mockito.verify(restTemp).exchange(MOCK_URL, HttpMethod.GET, null,  String.class);

	}

}