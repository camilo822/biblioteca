package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.entity.Administrador;
import com.biblioteca.repository.AdministradorRepository;

@Controller
public class AdminLoginController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping("/admin/login")
    public String loginForm() {
        return "/login";
    }

    @PostMapping("/admin/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Administrador admin = administradorRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            // Autenticación exitosa, redirigir a la página de inicio del administrador
            return "redirect:/admin/list";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "/login";
        }
    }

}