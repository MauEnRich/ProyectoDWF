package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.modelo.Inscripcion;
import sv.edu.udb.modelo.Materia;
import sv.edu.udb.repositorio.EstudianteRepository;
import sv.edu.udb.repositorio.InscripcionRepository;
import sv.edu.udb.repositorio.MateriaRepository;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private InscripcionRepository inscripcionRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private MateriaRepository materiaRepo;

    @PostMapping
    public ResponseEntity<String> inscribirEstudiante(@RequestBody Map<String, Long> datos) {
        Long estudianteId = datos.get("estudianteId");
        Long materiaId = datos.get("materiaId");

        Estudiante estudiante = estudianteRepo.findById(estudianteId).orElse(null);
        Materia materia = materiaRepo.findById(materiaId).orElse(null);

        if (estudiante == null || materia == null) {
            return ResponseEntity.badRequest().body("Estudiante o materia no encontrados");
        }

        boolean yaInscrito = inscripcionRepo.findByEstudianteId(estudianteId)
                .stream()
                .anyMatch(i -> i.getMateria().getId().equals(materiaId));

        if (yaInscrito) {
            return ResponseEntity.badRequest().body("Ya estás inscrito en esta materia");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setMateria(materia);
        inscripcion.setFechaInscripcion(LocalDate.now());

        inscripcionRepo.save(inscripcion);

        return ResponseEntity.ok("Inscripción completada");
    }
}
