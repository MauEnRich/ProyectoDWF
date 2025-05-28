package sv.edu.udb.dto;

import java.time.LocalDate;

public class EvaluacionCrearDTO {
    private String nombre;
    private LocalDate fecha;
    private Long materiaId;


    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }
}
