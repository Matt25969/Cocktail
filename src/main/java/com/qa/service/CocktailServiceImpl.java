package com.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qa.domain.Cocktail;
import com.qa.repository.CocktailRepository;

@Component
public class CocktailServiceImpl implements CocktailService {

	private CocktailRepository cocktailRepository;

	public CocktailServiceImpl() {

	}

	@Autowired
	public CocktailServiceImpl(CocktailRepository cocktailRepository) {
		this.cocktailRepository = cocktailRepository;
	}

	@Override
	public List<Cocktail> findAll() {
		return cocktailRepository.findAll();
	}

	@Override
	public Cocktail createCocktail(Cocktail cocktail) {
		return cocktailRepository.save(cocktail);

	}

	@Override
	public String updateCocktail(Cocktail cocktail) {

		cocktailRepository.deleteById(cocktail.getId());

		cocktailRepository.save(cocktail);

		return cocktail.toString();
	}

	public String deleteCocktail(Cocktail cocktail) {
		cocktailRepository.delete(cocktail);
		return "Cocktail Successfully deleted";
	}

}
