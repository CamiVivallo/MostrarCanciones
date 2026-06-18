package com.cami.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cami.modelos.Cancion;
import com.cami.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
    
    private final RepositorioCanciones repositorioCanciones;

    @Autowired
    public ServicioCanciones(
            RepositorioCanciones repositorioCanciones) {

        this.repositorioCanciones = repositorioCanciones;
    }

    public List<Cancion> obtenerTodasLasCanciones() {
        return this.repositorioCanciones.findAll();
    }

    public Cancion obtenerCancionPorId(Long id) {
        return this.repositorioCanciones
                .findById(id)
                .orElse(null);
    }

    public Cancion agregarCancion(Cancion cancionNueva) {
        return this.repositorioCanciones.save(cancionNueva);
    }
}