package com.biblioteca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Libro;
import com.biblioteca.entity.Prestamo;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.repository.PrestamoRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public String listarPrestamos(Model model) {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        model.addAttribute("prestamos", prestamos);
        return "prestamos/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "prestamos/formulario";
    }

    @PostMapping("/nuevo")
    public String registrarPrestamo(@ModelAttribute("prestamo") Prestamo prestamo) {
        prestamoRepository.save(prestamo);
        return "redirect:/prestamos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") String id, Model model) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de préstamo inválido:" + id));
        model.addAttribute("prestamo", prestamo);
        return "prestamos/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPrestamo(@PathVariable("id") String id, @ModelAttribute("prestamo") Prestamo prestamo) {
        prestamo.setId(id);
        prestamoRepository.save(prestamo);
        return "redirect:/prestamos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable("id") String id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de préstamo inválido:" + id));
        prestamoRepository.delete(prestamo);
        return "redirect:/prestamos";
    }

    @GetMapping("/new/{libroId}")
    public String mostrarFormularioDePrestamo(@PathVariable String libroId, Model model) {
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + libroId));
        Prestamo prestamo = new Prestamo();
        prestamo.setTituloLibro(libro.getTitulo());
        prestamo.setAutorLibro(libro.getAutor());
        prestamo.setEditorialLibro(libro.getEditorial());
        prestamo.setIsbnLibro(libro.getIsbn());
        prestamo.setFechaSolicitud(LocalDate.now());
        model.addAttribute("prestamo", prestamo);
        return "prestamos/crear";
    }

    @PostMapping("/create")
    public String crearPrestamo(@ModelAttribute("prestamo") Prestamo prestamo) {
        prestamoRepository.save(prestamo);
        return "redirect:/home";
    }
}
