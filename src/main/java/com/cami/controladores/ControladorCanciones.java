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

import com.cami.modelos.Cancion;
import com.cami.servicios.ServicioCanciones;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
public class ControladorCanciones {

    private final ServicioCanciones servicioCanciones;

    @Autowired
    public ControladorCanciones(
            ServicioCanciones servicioCanciones) {

        this.servicioCanciones = servicioCanciones;
    }

    @GetMapping("/canciones")
    public String desplegarCanciones(Model modelo) {

        List<Cancion> listaCanciones = this.servicioCanciones.obtenerTodasLasCanciones();

        modelo.addAttribute(
                "listaCanciones",
                listaCanciones);

        return "canciones";
    }

    @GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(
            @PathVariable("idCancion") Long idCancion,
            Model modelo) {

        Cancion cancion = this.servicioCanciones
                .obtenerCancionPorId(idCancion);

        modelo.addAttribute("cancion", cancion);

        return "detalleCancion";
    }

    @GetMapping("/canciones/formulario/agregar/{idCancion}")
    public String formularioAgregarCancion(
            @PathVariable("idCancion") Long idCancion,
            @ModelAttribute("cancion") Cancion cancion) {

        return "agregarCancion";
    }

    @PostMapping("/canciones/procesa/agregar")
    public String procesarAgregarCancion(
            @Valid @ModelAttribute("cancion") Cancion cancionNueva,
            BindingResult validaciones) {

        if (validaciones.hasErrors()) {
            return "agregarCancion";
        }

        this.servicioCanciones
                .agregarCancion(cancionNueva);

        return "redirect:/canciones";
    }

    @GetMapping("/canciones/formulario/editar/{idCancion}")
    public String formularioEditarCancion(
            @PathVariable("idCancion") Long idCancion,
            Model modelo) {

        Cancion cancionActual = this.servicioCanciones
                .obtenerCancionPorId(idCancion);

        modelo.addAttribute("cancion", cancionActual);

        return "editarCancion";
    }

    @PutMapping("/canciones/procesa/editar/{idCancion}")
    public String procesarEditarCancion(
            @Valid @ModelAttribute("cancion") Cancion cancionInformacion,
            BindingResult validaciones,
            @PathVariable("idCancion") Long idCancion) {

        cancionInformacion.setId(idCancion);

        if (validaciones.hasErrors()) {
            return "editarCancion";
        }

        Cancion cancionActual = this.servicioCanciones
                .obtenerCancionPorId(idCancion);

        cancionInformacion.setFechaCreacion(
                cancionActual.getFechaCreacion());

        this.servicioCanciones
                .actualizaCancion(cancionInformacion);

        return "redirect:/canciones";
    }
    
    @DeleteMapping("/canciones/eliminar/{idCancion}")
public String procesarEliminarCancion(
        @PathVariable("idCancion") Long idCancion) {

    this.servicioCanciones.eliminaCancion(idCancion);

    return "redirect:/canciones";
}

}