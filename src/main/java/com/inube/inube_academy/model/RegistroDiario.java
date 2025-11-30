package com.inube.inube_academy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "VW_REGISTRO_DIARIO")
public class RegistroDiario {

    @Id
    @Column(name = "ID_REGISTRO")
    private Long id;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "ACTIVIDAD")
    private String actividad;

    @Column(name = "SALA")
    private String sala;

    @Column(name = "HORA_ENTRADA")
    private LocalDateTime horaEntrada;

    @Column(name = "HORA_SALIDA")
    private LocalDateTime horaSalida;

    @Column(name = "FECHA_REGISTRO")
    private LocalDate fechaRegistro;
}
