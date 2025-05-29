package sv.edu.udb.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(mappedBy = "materia")
    private List<Inscripcion> inscripciones;

    @OneToMany(mappedBy = "materia")
    private List<Asistencia> asistencias;

    @OneToMany(mappedBy = "materia")
    private List<Evaluacion> evaluaciones;

    @OneToMany(mappedBy = "materia")
    private List<Horario> horarios;
}
