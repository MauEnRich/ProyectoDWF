package sv.edu.udb.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaDTO {
    private String evaluacion;
    private LocalDate fecha;
    private BigDecimal nota;

    public NotaDTO(String evaluacion, LocalDate fecha, BigDecimal nota) {
        this.evaluacion = evaluacion;
        this.fecha = fecha;
        this.nota = nota;
    }


    public String getEvaluacion() { return evaluacion; }
    public void setEvaluacion(String evaluacion) { this.evaluacion = evaluacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
}
