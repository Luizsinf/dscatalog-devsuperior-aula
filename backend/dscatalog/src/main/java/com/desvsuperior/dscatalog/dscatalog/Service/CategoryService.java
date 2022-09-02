package com.desvsuperior.dscatalog.dscatalog.Service;

import java.util.Optional;


import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desvsuperior.dscatalog.dscatalog.Service.exceptions.DatabaseException;
import com.desvsuperior.dscatalog.dscatalog.Service.exceptions.ResourceNotFoundException;
import com.desvsuperior.dscatalog.dscatalog.dto.CategoryDTO;
import com.desvsuperior.dscatalog.dscatalog.entities.Category;
import com.desvsuperior.dscatalog.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly =true)
		public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
			Page<Category> list = repository.findAll(pageRequest);
			return  list.map(X ->  new CategoryDTO(X));
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
		@SuppressWarnings("deprecation")
		Category entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
		catch(EntityExistsException e) {
			throw new ResourceNotFoundException("Id not found"+id);
			
		}
	
       }


	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found"+id);

		}
		
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity viodation");
		
	}
	}
}
