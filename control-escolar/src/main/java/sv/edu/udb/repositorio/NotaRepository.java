package sv.edu.udb.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.udb.dto.EvaluacionNotaDTO;
import sv.edu.udb.modelo.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    @Query("SELECT n FROM Nota n WHERE n.estudiante.id = :estudianteId AND n.evaluacion.materia.id = :materiaId")
    List<Nota> findByEstudianteIdAndMateriaId(@Param("estudianteId") Long estudianteId, @Param("materiaId") Long materiaId);

    @Query("SELECT new sv.edu.udb.dto.EvaluacionNotaDTO(" +
            "n.evaluacion.nombre, " +
            "n.evaluacion.fecha, " +
            "n.nota, " +
            "n.evaluacion.materia.nombre) " +
            "FROM Nota n WHERE n.estudiante.id = :estudianteId")
    List<EvaluacionNotaDTO> findEvaluacionesYNotasPorEstudiante(@Param("estudianteId") Long estudianteId);

    List<Nota> findByEstudianteIdAndEvaluacionId(Long estudianteId, Long evaluacionId);


}


