package com.biblioteca.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Libro;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {
	List<Libro> findByAutor(String autor);
	List<Libro> findByGenero(String genero);
	List<Libro> findByGeneroAndAutor(String genero, String autor);
}
