package com.inube.inube_academy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actividad_seq")
    @SequenceGenerator(
        name = "actividad_seq",
        sequenceName = "SEQ_ACTIVIDAD",
        allocationSize = 1
    )
    @Column(name = "ID_ACTIVIDAD", nullable = false)
    private Long idActividad;

    // MANY TO ONE
    @ManyToOne
    @JoinColumn(
        name = "ID_USUARIO",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_ACT_USUARIO")
    )
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(
        name = "ID_SALA",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_ACT_SALA")
    )
    private Sala sala;

    @Column(name = "NOMBRE_ACTIVIDAD", nullable = false, length = 150)
    private String nombreActividad;

    @Column(name = "HORA_ENTRADA", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "HORA_SALIDA")
    private LocalDateTime horaSalida;

    @Column(name = "FECHA_REGISTRO")
    private LocalDate fechaRegistro = LocalDate.now();

    @Column(name = "ESTATUS", nullable = false)
    private Character estatus = 'A';

}
