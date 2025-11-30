package com.inube.inube_academy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SALA")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_seq")
    @SequenceGenerator(
        name = "sala_seq",
        sequenceName = "SEQ_SALA",
        allocationSize = 1
    )
    @Column(name = "ID_SALA", nullable = false)
    private Long idSala;

    @Column(name = "NOMBRE_SALA", nullable = false, length = 100)
    private String nombreSala;

    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;

    @Column(name = "ESTATUS", nullable = false)
    private Character estatus = 'A';
    
    @Column(name = "FECHA_ALTA")
    private LocalDate fechaAlta = LocalDate.now();

}
