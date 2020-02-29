package com.group7.voluntaweb.Repositories;


import java.util.List;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.Category;


public interface CategoryRepository extends JpaRepository <Category,Long> {

	List<Category> findAll();
	Category  findById(long id);
	
}

import antlr.collections.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	ArrayList<Category> findAll();

}

