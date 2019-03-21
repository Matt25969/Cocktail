package com.qa.service;


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
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.qa.domain.Cocktail;
import com.qa.repository.CocktailRepository;
import com.qa.rest.CocktailController;
import com.qa.service.CocktailService;

import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
public class MockitoTest {

	@InjectMocks
	CocktailServiceImpl service;
	
	@Mock
	CocktailRepository repo;
	
	private static final Cocktail MOCK_COCKTAIL_1 = new Cocktail(1L, "Vodka somthing");
	private static final Cocktail MOCK_COCKTAIL_2 = new Cocktail(2L, "Whiskey Doo-dah");
	private static final String MOCK_CREATE_RESPONSE = "Cocktail Created";
	
	
	@Test
	
	public void findAllTest() {
		List<Cocktail> MOCK_LIST = new ArrayList<>();
		MOCK_LIST.add(MOCK_COCKTAIL_1);
		MOCK_LIST.add(MOCK_COCKTAIL_2);
		Mockito.when(repo.findAll()).thenReturn(MOCK_LIST);
		assertEquals(MOCK_LIST, service.findAll());
		Mockito.verify(repo).findAll();
	}

	@Test
	
	public void createCocktailTest() {

		Mockito.when(repo.save(MOCK_COCKTAIL_1)).thenReturn(MOCK_COCKTAIL_1);

		assertEquals(MOCK_COCKTAIL_1, service.createCocktail(MOCK_COCKTAIL_1));
		Mockito.verify(repo).save(MOCK_COCKTAIL_1);
	}
	
}