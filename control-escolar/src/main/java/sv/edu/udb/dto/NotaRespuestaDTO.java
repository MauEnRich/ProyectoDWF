package sv.edu.udb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NotaRespuestaDTO {
    private Long id;
    private BigDecimal nota;

    private Long estudianteId;
    private String estudianteNombre;

    private Long evaluacionId;
    private String evaluacionNombre;

    private String materiaNombre;
}
