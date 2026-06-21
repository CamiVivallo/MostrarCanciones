package com.cami.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cami.modelos.Artista;
import com.cami.repositorios.RepositorioArtistass;

@Service
public class ServicioArtistas {

    private final RepositorioArtistass repositorioArtistass;

    @Autowired
    public ServicioArtistas(
            RepositorioArtistass repositorioArtistass) {

        this.repositorioArtistass = repositorioArtistass;
    }

    public List<Artista> obtenerTodosLosArtistas() {
        return this.repositorioArtistass.findAll();
    }

    public Artista obtenerArtistaPorId(Long idArtista) {
        return this.repositorioArtistass
                .findById(idArtista)
                .orElse(null);
    }

    public Artista agregarArtista(Artista artistaNuevo) {
        return this.repositorioArtistass.save(artistaNuevo);
    }
}