package com.cami.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cami.modelos.Artista;
import com.cami.modelos.Cancion;
import com.cami.servicios.ServicioArtistas;
import com.cami.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {

    private final ServicioCanciones servicioCanciones;
    private final ServicioArtistas servicioArtistas;

    @Autowired
    public ControladorCanciones(
            ServicioCanciones servicioCanciones,
            ServicioArtistas servicioArtistas) {

        this.servicioCanciones = servicioCanciones;
        this.servicioArtistas = servicioArtistas;
    }

    // Mostrar todas las canciones
    @GetMapping("/canciones")
    public String desplegarCanciones(Model modelo) {

        List<Cancion> listaCanciones =
                this.servicioCanciones.obtenerTodasLasCanciones();

        modelo.addAttribute(
                "listaCanciones",
                listaCanciones
        );

        return "canciones";
    }

    // Mostrar el detalle de una canción
    @GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(
            @PathVariable("idCancion") Long idCancion,
            Model modelo) {

        Cancion cancion =
                this.servicioCanciones
                        .obtenerCancionPorId(idCancion);

        modelo.addAttribute("cancion", cancion);

        return "detalleCancion";
    }

    // Mostrar el formulario para agregar una canción
    @GetMapping("/canciones/formulario/agregar/{idCancion}")
    public String formularioAgregarCancion(
            @PathVariable("idCancion") Long idCancion,
            @ModelAttribute("cancion") Cancion cancion,
            Model modelo) {

        List<Artista> listaArtistas =
                this.servicioArtistas
                        .obtenerTodosLosArtistas();

        modelo.addAttribute(
                "listaArtistas",
                listaArtistas
        );

        return "agregarCancion";
    }

    // Procesar el formulario para agregar una canción
    @PostMapping("/canciones/procesa/agregar")
    public String procesarAgregarCancion(
            @Valid
            @ModelAttribute("cancion") Cancion cancionNueva,
            BindingResult validaciones,
            @RequestParam("idArtista") Long idArtista,
            Model modelo) {

        Artista artistaSeleccionado =
                this.servicioArtistas
                        .obtenerArtistaPorId(idArtista);

        cancionNueva.setArtista(artistaSeleccionado);

        if (validaciones.hasErrors()) {

            List<Artista> listaArtistas =
                    this.servicioArtistas
                            .obtenerTodosLosArtistas();

            modelo.addAttribute(
                    "listaArtistas",
                    listaArtistas
            );

            return "agregarCancion";
        }

        this.servicioCanciones
                .agregarCancion(cancionNueva);

        return "redirect:/canciones";
    }

    // Mostrar el formulario para editar una canción
    @GetMapping("/canciones/formulario/editar/{idCancion}")
    public String formularioEditarCancion(
            @PathVariable("idCancion") Long idCancion,
            Model modelo) {

        Cancion cancionActual =
                this.servicioCanciones
                        .obtenerCancionPorId(idCancion);

        List<Artista> listaArtistas =
                this.servicioArtistas
                        .obtenerTodosLosArtistas();

        modelo.addAttribute(
                "cancion",
                cancionActual
        );

        modelo.addAttribute(
                "listaArtistas",
                listaArtistas
        );

        return "editarCancion";
    }

    // Procesar la actualización de una canción
    @PutMapping("/canciones/procesa/editar/{idCancion}")
    public String procesarEditarCancion(
            @Valid
            @ModelAttribute("cancion") Cancion cancionInformacion,
            BindingResult validaciones,
            @PathVariable("idCancion") Long idCancion,
            @RequestParam("idArtista") Long idArtista,
            Model modelo) {

        cancionInformacion.setId(idCancion);

        Artista artistaSeleccionado =
                this.servicioArtistas
                        .obtenerArtistaPorId(idArtista);

        cancionInformacion.setArtista(artistaSeleccionado);

        if (validaciones.hasErrors()) {

            List<Artista> listaArtistas =
                    this.servicioArtistas
                            .obtenerTodosLosArtistas();

            modelo.addAttribute(
                    "listaArtistas",
                    listaArtistas
            );

            return "editarCancion";
        }

        Cancion cancionActual =
                this.servicioCanciones
                        .obtenerCancionPorId(idCancion);

        cancionInformacion.setFechaCreacion(
                cancionActual.getFechaCreacion()
        );

        this.servicioCanciones
                .actualizaCancion(cancionInformacion);

        return "redirect:/canciones";
    }

    // Eliminar una canción
    @DeleteMapping("/canciones/eliminar/{idCancion}")
    public String procesarEliminarCancion(
            @PathVariable("idCancion") Long idCancion) {

        this.servicioCanciones
                .eliminaCancion(idCancion);

        return "redirect:/canciones";
    }
}