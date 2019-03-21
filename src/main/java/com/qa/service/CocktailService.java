package com.qa.service;

import java.util.List;
import java.util.Map;

import com.qa.domain.Cocktail;

public interface CocktailService {

	List<Cocktail> findAll();

	Cocktail createCocktail(Cocktail cocktail);

	String deleteCocktail(Cocktail cocktail);

	String updateCocktail(Cocktail cocktail);

}