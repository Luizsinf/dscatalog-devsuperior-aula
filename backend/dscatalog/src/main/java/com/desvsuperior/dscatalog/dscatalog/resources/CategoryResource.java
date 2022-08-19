package com.desvsuperior.dscatalog.dscatalog.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desvsuperior.dscatalog.dscatalog.Service.CategoryService;
import com.desvsuperior.dscatalog.dscatalog.dto.CategoryDTO;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	@GetMapping(value ="/{id}")
	public ResponseEntity<CategoryDTO> findAll(@PathVariable Long id) {
		CategoryDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);

	}

}
