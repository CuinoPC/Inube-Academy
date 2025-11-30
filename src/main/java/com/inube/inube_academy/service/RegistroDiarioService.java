package com.inube.inube_academy.service;

import com.inube.inube_academy.model.RegistroDiario;
import com.inube.inube_academy.repository.RegistroDiarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroDiarioService {

    private final RegistroDiarioRepository registroDiarioRepository;

    public RegistroDiarioService(RegistroDiarioRepository registroDiarioRepository) {
        this.registroDiarioRepository = registroDiarioRepository;
    }

    // Obtener todos los registros diarios 
    public List<RegistroDiario> findAll() {
        return registroDiarioRepository.findAllRegistros();
    }

    public List<RegistroDiario> findByFecha(LocalDate fecha) {
        return registroDiarioRepository.findByFecha(fecha);
    }

}
