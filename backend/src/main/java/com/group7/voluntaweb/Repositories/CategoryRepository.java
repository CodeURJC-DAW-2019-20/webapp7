package com.group7.voluntaweb.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.Categories;

public interface CategoryRepository extends JpaRepository <Categories,Long> {

	List<Categories> findAll();
	
}
