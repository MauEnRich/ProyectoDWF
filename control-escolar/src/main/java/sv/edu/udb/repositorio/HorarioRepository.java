package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.dto.HorarioDTO;
import sv.edu.udb.modelo.Inscripcion;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Inscripcion, Long> {

    @Query("""
        SELECT new sv.edu.udb.dto.HorarioDTO(
            m.nombre,
            h.diaSemana,
            h.horaInicio,
            h.horaFin
        )
        FROM Inscripcion i
        JOIN i.materia m
        JOIN m.horarios h
        WHERE i.estudiante.id = :estudianteId
    """)
    List<HorarioDTO> obtenerHorarioPorEstudiante(@Param("estudianteId") Long estudianteId);
}
