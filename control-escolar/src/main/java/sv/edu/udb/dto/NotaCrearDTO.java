package sv.edu.udb.dto;

import java.math.BigDecimal;

public class NotaCrearDTO {
    private Long estudianteId;
    private Long evaluacionId;
    private BigDecimal nota;


    public Long getEstudianteId() {
        return estudianteId;
    }

    public Long getEvaluacionId() {
        return evaluacionId;
    }

    public BigDecimal getNota() {
        return nota;
    }


    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public void setEvaluacionId(Long evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }
}
