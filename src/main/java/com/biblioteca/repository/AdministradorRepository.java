package com.biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.biblioteca.entity.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {
    Administrador findByUsername(String username);
}
