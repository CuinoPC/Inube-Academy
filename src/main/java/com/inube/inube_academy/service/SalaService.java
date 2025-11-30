package com.inube.inube_academy.service;

import com.inube.inube_academy.model.Sala;
import com.inube.inube_academy.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    public List<Sala> findAll() {
        return salaRepository.findAll();
    }

    public List<Sala> findActivos() {
        return salaRepository.findByEstatus('A');
    }

    public List<Sala> findInactivas() {
        return salaRepository.findByEstatus('I');
    }

    public  Optional<Sala> findById(Long id) {
        return salaRepository.findById(id);
    }

    public Sala save(Sala sala) {
        return salaRepository.save(sala);
    }

    public Sala update(Long id, Sala data) {
        return salaRepository.findById(id).map(s -> {

            s.setNombreSala(data.getNombreSala());
            s.setDescripcion(data.getDescripcion());
            s.setEstatus(data.getEstatus());

            return salaRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Sala no encontrada" + id));
    }

    public void desactivar(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido" + id));

        sala.setEstatus('I');
        salaRepository.save(sala);
    }

    public void activar(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido" + id));

        sala.setEstatus('A');
        salaRepository.save(sala);
    }
    

}
