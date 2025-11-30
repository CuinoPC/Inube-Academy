package com.inube.inube_academy.repository;

import com.inube.inube_academy.model.Sala;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    List<Sala> findByEstatus(Character estatus);

}
