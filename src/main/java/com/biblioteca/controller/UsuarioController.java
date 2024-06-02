package com.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.UsuarioRepository;



@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar formulario de registro de usuario
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarUsuario"; // nombre del archivo HTML sin extensión
    }

    // Procesar el formulario de registro de usuario
    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        // Otros procesamientos si es necesario
        usuarioRepository.save(usuario); // Esto guardará el usuario con el estado "activo" por defecto
        return "redirect:/login"; // redirige al formulario de login
    }


    // Listar todos los usuarios
    @GetMapping("/")
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/userlist";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/new")
    public String newUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/new";
    }

    // Guardar nuevo usuario
    @PostMapping("/save")
    public String saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/";
    }

    // Mostrar formulario para editar usuario por ID
    @GetMapping("/edit/{id}")
    public String editUsuarioForm(@PathVariable String id, Model model) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            model.addAttribute("usuario", optionalUsuario.get());
            return "usuarios/edit"; // Asegúrate de devolver el nombre correcto de la plantilla
        } else {
            return "redirect:/usuarios/";
        }
    }


    // Actualizar usuario por ID
    @PostMapping("/update/{id}")
    public String updateUsuario(@PathVariable String id, Usuario usuario) {
        usuario.setId(id); // Asegurar que el ID se mantenga al actualizar
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/";
    }

    // Eliminar usuario por ID
    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios/";
    }
}
