package sv.edu.udb.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.modelo.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudianteId(Long estudianteId);
    List<Inscripcion> findByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);
}

