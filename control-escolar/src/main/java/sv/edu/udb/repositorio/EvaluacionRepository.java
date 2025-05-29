package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.modelo.Evaluacion;
import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByMateriaProfesorId(Long profesorId);
}

