package com.desvsuperior.dscatalog.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desvsuperior.dscatalog.dscatalog.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
