package sv.edu.udb.dto;

import java.time.LocalDate;

public class EvaluacionDTO {
    private Long id;
    private String nombre;
    private LocalDate fecha;
    private String materiaNombre;

    public EvaluacionDTO(Long id, String nombre, LocalDate fecha, String materiaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.materiaNombre = materiaNombre;
    }


    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
    public String getMateriaNombre() { return materiaNombre; }
}
