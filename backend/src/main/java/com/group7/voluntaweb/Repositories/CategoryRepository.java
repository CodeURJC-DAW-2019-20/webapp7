package com.group7.voluntaweb.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group7.voluntaweb.Models.Category;

import antlr.collections.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	ArrayList<Category> findAll();

}