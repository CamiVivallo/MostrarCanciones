package com.cami.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cami.modelos.Artista;
import com.cami.servicios.ServicioArtistas;

import jakarta.validation.Valid;

@Controller
public class ControladorArtistas {

    private final ServicioArtistas servicioArtistas;

    @Autowired
    public ControladorArtistas(
            ServicioArtistas servicioArtistas) {

        this.servicioArtistas = servicioArtistas;
    }

    @GetMapping("/artistas")
    public String desplegarArtistas(Model modelo) {

        List<Artista> listaArtistas = this.servicioArtistas.obtenerTodosLosArtistas();

        modelo.addAttribute("listaArtistas", listaArtistas);

        return "artistas";
    }

    @GetMapping("/artistas/detalle/{idArtista}")
    public String desplegarDetalleArtista(
            @PathVariable("idArtista") Long idArtista,
            Model modelo) {

        Artista artista = this.servicioArtistas
                .obtenerArtistaPorId(idArtista);

        modelo.addAttribute("artista", artista);

        return "detalleArtista";
    }

    @GetMapping("/artistas/formulario/agregar/{idArtista}")
    public String formularioAgregarArtista(
            @PathVariable("idArtista") Long idArtista,
            @ModelAttribute("artista") Artista artista) {

        return "agregarArtista";
    }

    @PostMapping("/artistas/procesa/agregar")
    public String procesarAgregarArtista(
            @Valid @ModelAttribute("artista") Artista artistaNuevo,
            BindingResult validaciones) {

        if (validaciones.hasErrors()) {
            return "agregarArtista";
        }

        this.servicioArtistas
                .agregarArtista(artistaNuevo);

        return "redirect:/artistas";
    }
}