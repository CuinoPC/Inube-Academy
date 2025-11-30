package com.inube.inube_academy.repository;

import com.inube.inube_academy.model.Actividad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository  extends JpaRepository<Actividad, Long> {

    List<Actividad> findByEstatus(Character estatus);

    List<Actividad> findByUsuarioIdUsuario(Long idUsuario); 
    
    List<Actividad> findBySalaIdSala(Long idSala); 


}
