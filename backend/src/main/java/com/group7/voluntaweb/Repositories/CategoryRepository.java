package com.group7.voluntaweb.Repositories;


import java.util.List;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group7.voluntaweb.Models.Category;


public interface CategoryRepository extends JpaRepository <Category,Long> {

	ArrayList<Category> findAll();
	
	@Query(value= "SELECT * FROM categories LIMIT :n", nativeQuery = true)
	ArrayList<Category> findByQuantity(@Param("n") int n);
	
	Category findById(long id);
	
}



