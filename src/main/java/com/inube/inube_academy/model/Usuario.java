package com.inube.inube_academy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(
            name = "usuario_seq",
            sequenceName = "SEQ_USUARIO",
            allocationSize = 1
    )
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Column(name = "CORREO", nullable = false, length = 150)
    private String correo;

    @Column(name = "ESTATUS", nullable = false)
    private Character estatus = 'A';

    @Column(name = "FECHA_ALTA")
    private LocalDate fechaAlta = LocalDate.now();
}
