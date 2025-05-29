package sv.edu.udb.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

public class EvaluacionNotaDTO {
    private String nombreEvaluacion;
    private LocalDate fechaEvaluacion;
    private BigDecimal nota;
    private String nombreMateria;

    public EvaluacionNotaDTO(String nombreEvaluacion, LocalDate fechaEvaluacion, BigDecimal nota, String nombreMateria) {
        this.nombreEvaluacion = nombreEvaluacion;
        this.fechaEvaluacion = fechaEvaluacion;
        this.nota = nota;
        this.nombreMateria = nombreMateria;
    }


    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
}
