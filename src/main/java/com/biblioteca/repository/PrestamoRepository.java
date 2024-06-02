package com.biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Prestamo;

@Repository
public interface PrestamoRepository extends MongoRepository<Prestamo, String> {
}
