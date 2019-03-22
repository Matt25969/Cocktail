package com.qa.rest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Cocktail;
import com.qa.repository.CocktailRepository;
import com.qa.rest.CocktailController;
import com.qa.service.CocktailService;

@RunWith(SpringRunner.class)
@WebMvcTest(CocktailController.class)
@AutoConfigureMockMvc
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CocktailService service;
	
	@MockBean
	private RestTemplate restTemplate;

	private static final Cocktail MOCK_COCKTAIL_1 = new Cocktail(1L, "Vodka somthing");
	private static final Cocktail MOCK_COCKTAIL_2 = new Cocktail(2L, "Whiskey Doo-dah");

	@Test
	public void getAllTest() throws Exception {

		List<Cocktail> MOCK_LIST = new ArrayList<>();
		MOCK_LIST.add(MOCK_COCKTAIL_1);
		MOCK_LIST.add(MOCK_COCKTAIL_2);

		when(service.findAll()).thenReturn(MOCK_LIST);

		mockMvc.perform(get("/getAllCocktails")).andExpect(content().string(containsString("Vodka somthing")));
	}

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Test
	public void createCocktailTest() throws Exception {
		
		 String postValue = OBJECT_MAPPER.writeValueAsString(MOCK_COCKTAIL_1);
		 
//		 when(service.createCocktail(MOCK_COCKTAIL_1)).thenReturn(MOCK_COCKTAIL_1);
		 
		 doReturn(MOCK_COCKTAIL_1).when(service).createCocktail(MOCK_COCKTAIL_1);
		 
		mockMvc.perform(MockMvcRequestBuilders
                .post("/createCocktail")
                .contentType(MediaType.APPLICATION_JSON).content(postValue))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
	
	}
}