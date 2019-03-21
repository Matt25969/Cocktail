package com.qa.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.domain.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>  {


}