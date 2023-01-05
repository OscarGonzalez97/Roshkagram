package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.FotoReaccionAux;
import com.roshka.bootcamp.ProyectoJunio.model.ReaccionFoto;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.ReaccionFotoRepository;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaccionFotoService {
    @Autowired
    private ReaccionFotoRepository reaccionFotoRepository;

    public List<ReaccionFoto> list() {
        return reaccionFotoRepository.findAll();
    }

    public Optional<ReaccionFoto> findById(Long id) {
        return reaccionFotoRepository.findById(id);
    }

    public ReaccionFoto guardar(ReaccionFoto reaccionFoto) {
        return reaccionFotoRepository.save(reaccionFoto);
    }

    public void delete(ReaccionFoto reaccionFoto) {
        reaccionFotoRepository.deleteById(reaccionFoto.getId_reaccion_foto());
    }

    public List<FotoReaccionAux> obtenerReaccionesFoto(long idFoto) {   //solo lista los emojis de la foto
        /* filtrar todos las reacciones que permitezca a la foto */

        List<ReaccionFoto> reaccionesFoto = reaccionFotoRepository.findAll();
        List<FotoReaccionAux> rcsFoto = new ArrayList<>(); //reaccionesFoto.stream().filter( obj -> obj.getFoto().getId_foto() == id).collect(Collectors.toList());

        boolean nuevo = true; // pasar saber si es un nuevo usuario
        for(ReaccionFoto rf : reaccionesFoto) {
            if(rf.getFoto().getId_foto() == idFoto) {
                for(FotoReaccionAux rcsf: rcsFoto) {
                    if(rf.getReaccion() == rcsf.getReaccion()) {
                        boolean flag = rcsf.getUsuario().add(rf.getUsuario()); // acumula los usuarios y retorna true si se agrego al Set

                        if(!flag) {
                            /* flag = false, si el usuario ya reaccion a esa foto con la misma reaccion,
                             * por lo que para la segunda vez se elimina esa reaccion.
                             * */

                            /* NO FUNCIONA */
                            /*rcsFoto.remove(0);
                            reaccionFotoRepository.delete(rf);*/
                        }

                        nuevo = false;
                    }
                }
                if(nuevo) {
                    // agrega un nuevo usuario
                    FotoReaccionAux aux = new FotoReaccionAux();

                    aux.setFoto(rf.getFoto());
                    aux.setReaccion(rf.getReaccion());
                    aux.getUsuario().add(rf.getUsuario());

                    rcsFoto.add(aux);
                } else {
                    nuevo = true;
                }
            }
        }

        return rcsFoto;
    }

}
