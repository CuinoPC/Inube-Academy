package com.inube.inube_academy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inube.inube_academy.model.RegistroDiario;

public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {

    @Query(value = "SELECT * FROM VW_REGISTRO_DIARIO", nativeQuery = true)
    List<RegistroDiario> findAllRegistros();

    @Query(value = "SELECT * FROM VW_REGISTRO_DIARIO WHERE FECHA_REGISRO =:fecha", nativeQuery = true)
    List<RegistroDiario> findByFecha(LocalDate fecha);

}
