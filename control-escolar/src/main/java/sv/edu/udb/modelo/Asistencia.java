package sv.edu.udb.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private LocalDate fecha;
    private Boolean presente;
}

