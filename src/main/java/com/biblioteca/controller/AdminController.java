package com.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Administrador;
import com.biblioteca.repository.AdministradorRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping("/list")
    public String listAdmins(Model model) {
        List<Administrador> admins = administradorRepository.findAll();
        model.addAttribute("admins", admins);
        return "admin/list";
    }

    @GetMapping("/create")
    public String createAdminForm(Model model) {
        model.addAttribute("admin", new Administrador());
        return "admin/create";
    }

    @PostMapping("/create")
    public String createAdmin(@Valid @ModelAttribute("admin") Administrador admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/create";
        }
        administradorRepository.save(admin);
        return "redirect:/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String editAdminForm(@PathVariable String id, Model model) {
        Optional<Administrador> adminOpt = administradorRepository.findById(id);
        if (adminOpt.isPresent()) {
            model.addAttribute("admin", adminOpt.get());
            return "admin/edit";
        }
        return "redirect:/admin/list";
    }

    @PostMapping("/edit/{id}")
    public String editAdmin(@PathVariable String id, @Valid @ModelAttribute("admin") Administrador admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        admin.setId(id);
        administradorRepository.save(admin);
        return "redirect:/admin/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable String id) {
        administradorRepository.deleteById(id);
        return "redirect:/admin/list";
    }

    @GetMapping("/home")
    public String adminHome() {
        return "admin/home";
    }
}
