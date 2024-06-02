package com.biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.biblioteca.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    Usuario findByCorreo(String correo);
}
