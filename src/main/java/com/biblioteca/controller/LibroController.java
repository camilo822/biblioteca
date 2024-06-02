package com.biblioteca.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.biblioteca.entity.Libro;
import com.biblioteca.repository.LibroRepository;

import java.util.List;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;
    


    @GetMapping
    public String listarLibros(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "libros/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeCrearLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/crear";
    }

    @PostMapping
    public String crearLibro(@ModelAttribute("libro") Libro libro) {
        libroRepository.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarLibro(@PathVariable String id, Model model) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + id));
        model.addAttribute("libro", libro);
        return "libros/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizarLibro(@PathVariable String id, @ModelAttribute("libro") Libro libro) {
        libro.setId(id);
        libroRepository.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable String id) {
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }

    @GetMapping("/reset-filtro")
    public String resetearFiltro(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "libros/lista";
    }

    @GetMapping("/reset-filtroVer")
    public String resetearFiltroVer(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "libros/verLibros";
    }

    @GetMapping("/filtrar")
    public String filtrarLibros(@RequestParam(required = false) String genero, @RequestParam(required = false) String autor, Model model) {
        List<Libro> libros;
        if (genero != null && !genero.isEmpty() && autor != null && !autor.isEmpty()) {
            libros = libroRepository.findByGeneroAndAutor(genero, autor);
        } else if (genero != null && !genero.isEmpty()) {
            libros = libroRepository.findByGenero(genero);
        } else if (autor != null && !autor.isEmpty()) {
            libros = libroRepository.findByAutor(autor);
        } else {
            libros = libroRepository.findAll();
        }
        model.addAttribute("libros", libros);
        return "libros/verLibros";
    }

    @GetMapping("/filtrarAdmin")
    public String filtrarLibrosAdmin(@RequestParam(required = false) String genero, @RequestParam(required = false) String autor, Model model) {
        List<Libro> libros;
        if (genero != null && !genero.isEmpty() && autor != null && !autor.isEmpty()) {
            libros = libroRepository.findByGeneroAndAutor(genero, autor);
        } else if (genero != null && !genero.isEmpty()) {
            libros = libroRepository.findByGenero(genero);
        } else if (autor != null && !autor.isEmpty()) {
            libros = libroRepository.findByAutor(autor);
        } else {
            libros = libroRepository.findAll();
        }
        model.addAttribute("libros", libros);
        return "libros/lista";
    }

    @GetMapping("/ver")
    public String verLibros(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "libros/verLibros";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalleLibro(@PathVariable String id, Model model) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + id));
        model.addAttribute("libro", libro);
        return "libros/detalleLibro";
    }

}
