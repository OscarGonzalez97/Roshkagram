package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoService implements FotoServicioInterface{
    @Autowired
    public FotoRepository fotoRepository;

    public List<Foto> getFotos(Long idAlbum) {
        Album album = new Album();
        album.setId_album(idAlbum);
        return fotoRepository.findAllByAlbum(album);
    }

    public Optional<Foto> findById(Long id) {
        return fotoRepository.findById(id);
    }

    @Override
    public Foto guardarFoto(Album album, String pathRelativo) {
        Foto foto = new Foto();
        foto.setRuta(pathRelativo);
        foto.setDescripcion("");
        foto.setAlbum(album);
        Foto fotoGuardada = fotoRepository.save(foto);
        return fotoGuardada;
    }

    public void delete(long id) {
        fotoRepository.deleteById(id);
    }
}
