package com.inube.inube_academy.service;

import com.inube.inube_academy.model.Actividad;
import com.inube.inube_academy.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

    public List<Actividad> findActivas() {
        return actividadRepository.findByEstatus('A');
    }

    public List<Actividad> findInactivas() {
        return actividadRepository.findByEstatus('I');
    }
    
    public Optional<Actividad> findById(Long id) {
        return actividadRepository.findById(id);
    }

    public Actividad save(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public Actividad update(Long id, Actividad data) {
        return actividadRepository.findById(id).map(a -> {

            a.setUsuario(data.getUsuario());
            a.setSala(data.getSala());
            a.setNombreActividad(data.getNombreActividad());
            a.setHoraEntrada(data.getHoraEntrada());
            a.setHoraSalida(data.getHoraSalida());
            a.setEstatus(data.getEstatus());

            return actividadRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Actividad no encontrada: " + id));
    }

    public void desactivar(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido " + id));
        
        actividad.setEstatus('I');
        actividadRepository.save(actividad);
    }

    public void activar(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido " + id));
        
        actividad.setEstatus('A');
        actividadRepository.save(actividad);
    }
}
