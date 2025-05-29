package sv.edu.udb.dto;

import java.time.LocalTime;

public class HorarioDTO {
    private String materia;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public HorarioDTO(String materia, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.materia = materia;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }


    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
