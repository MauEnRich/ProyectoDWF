package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.EstudianteDTO;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.dto.ProfesorDTO;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.modelo.Materia;
import sv.edu.udb.repositorio.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
public class ProfesorController {

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping("/{profesorId}/materias")
    public ResponseEntity<?> getMateriasPorProfesor(@PathVariable Long profesorId) {
        List<Materia> materias = materiaRepository.findByProfesorId(profesorId);

        List<MateriaDTO> materiasDTO = materias.stream()
                .map(m -> new MateriaDTO(
                        m.getId(),
                        m.getNombre(),
                        m.getDescripcion(),
                        new ProfesorDTO(
                                m.getProfesor().getId(),
                                m.getProfesor().getNombre(),
                                m.getProfesor().getApellido(),
                                m.getProfesor().getEmail()
                        )
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/materia/{id}/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> obtenerEstudiantesDeMateria(@PathVariable Long id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia == null) {
            return ResponseEntity.notFound().build();
        }

        List<EstudianteDTO> estudiantes = materia.getInscripciones().stream()
                .map(inscripcion -> {
                    Estudiante e = inscripcion.getEstudiante();
                    return new EstudianteDTO(e.getId(), e.getNombre(), e.getApellido(), e.getEmail());
                }).toList();

        return ResponseEntity.ok(estudiantes);
    }


}