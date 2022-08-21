package com.desvsuperior.dscatalog.dscatalog.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.desvsuperior.dscatalog.dscatalog.Service.exceptions.ResourceNotFoundException;
import com.desvsuperior.dscatalog.dscatalog.dto.CategoryDTO;
import com.desvsuperior.dscatalog.dscatalog.entities.Category;
import com.desvsuperior.dscatalog.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly =true)
	public List<CategoryDTO>findAll(){
		List<Category>list = repository.findAll();
		List<CategoryDTO>listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		
		return listDto;
		
	}

	
    @Transactional(readOnly =true)
	public CategoryDTO findById(Long id) {
    	Optional<Category> obj =repository.findById(id);
    	Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CategoryDTO(entity);
	}

    @Transactional
	public CategoryDTO insert(CategoryDTO dto) {
    	Category entity = new Category();
    	entity.setName(dto.getName());
    	entity = repository.save(entity);
    	return new CategoryDTO(entity);
		
	
	}


	public CategoryDTO update(Long id, CategoryDTO dto) {
		
		try {
		Category entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
		catch(EntityExistsException e) {
			throw new ResourceNotFoundException("Id not found"+id);
			
		}
	
       }
	}
