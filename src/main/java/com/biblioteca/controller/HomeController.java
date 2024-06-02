package com.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String home(Model model) {
        // Aquí puedes agregar lógica para cargar datos o realizar acciones antes de mostrar la página
        return "home";
    }
	
}
