package sv.edu.udb.dto;

public class MateriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private ProfesorDTO profesor;

    public MateriaDTO(Long id, String nombre, String descripcion, ProfesorDTO profesor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
    }


    public MateriaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = null;
        this.profesor = null;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ProfesorDTO getProfesor() {
        return profesor;
    }
}
