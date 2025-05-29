package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.modelo.Materia;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    List<Materia> findByProfesorId(Long profesorId);

}
