package sv.edu.udb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.modelo.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByEmailAndContrasena(String email, String contrasena);
}
