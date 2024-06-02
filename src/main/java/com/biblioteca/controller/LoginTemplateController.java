package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.UsuarioRepository;


@Controller
@RequestMapping("")
public class LoginTemplateController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/home")
    public String homeController(Model model) {
        return "dashboard";
    }

    @GetMapping("/login")
    public String loginTemplate(Model model) {
        return "login-general";
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, 
                        @RequestParam("password") String password, 
                        Model model) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Autenticación exitosa
            return "redirect:/home";
        } else {
            // Autenticación fallida
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login-general";
        }
    }
}
