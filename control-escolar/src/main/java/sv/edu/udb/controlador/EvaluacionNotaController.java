package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.*;
import sv.edu.udb.modelo.Evaluacion;
import sv.edu.udb.modelo.Materia;
import sv.edu.udb.modelo.Nota;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.repositorio.EvaluacionRepository;
import sv.edu.udb.repositorio.MateriaRepository;
import sv.edu.udb.repositorio.NotaRepository;
import sv.edu.udb.repositorio.EstudianteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
public class EvaluacionNotaController {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;


    @PostMapping("/evaluaciones")
    public ResponseEntity<?> crearEvaluacion(@RequestBody EvaluacionCrearDTO dto) {
        Materia materia = materiaRepository.findById(dto.getMateriaId()).orElse(null);
        if (materia == null) {
            return ResponseEntity.badRequest().body("Materia no encontrada");
        }

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre(dto.getNombre());
        evaluacion.setFecha(dto.getFecha());
        evaluacion.setMateria(materia);

        Evaluacion saved = evaluacionRepository.save(evaluacion);


        EvaluacionRespuestaDTO respuesta = new EvaluacionRespuestaDTO();
        respuesta.setId(saved.getId());
        respuesta.setNombre(saved.getNombre());
        respuesta.setFecha(saved.getFecha());
        respuesta.setMateriaNombre(materia.getNombre());

        return ResponseEntity.ok(respuesta);
    }


    @PostMapping("/notas")
    public ResponseEntity<?> crearNota(@RequestBody NotaCrearDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId()).orElse(null);
        if (estudiante == null) {
            return ResponseEntity.badRequest().body("Estudiante no encontrado");
        }

        Evaluacion evaluacion = evaluacionRepository.findById(dto.getEvaluacionId()).orElse(null);
        if (evaluacion == null) {
            return ResponseEntity.badRequest().body("Evaluación no encontrada");
        }

        List<Nota> notasExistentes = notaRepository.findByEstudianteIdAndEvaluacionId(dto.getEstudianteId(), dto.getEvaluacionId());

        Nota nota;
        if (notasExistentes.isEmpty()) {
            nota = new Nota();
        } else {
            nota = notasExistentes.get(0);
        }

        nota.setEstudiante(estudiante);
        nota.setEvaluacion(evaluacion);
        nota.setNota(dto.getNota());

        Nota saved = notaRepository.save(nota);


        NotaRespuestaDTO respuesta = new NotaRespuestaDTO();
        respuesta.setId(saved.getId());
        respuesta.setNota(saved.getNota());
        respuesta.setEstudianteId(estudiante.getId());
        respuesta.setEstudianteNombre(estudiante.getNombre());
        respuesta.setEvaluacionId(evaluacion.getId());
        respuesta.setEvaluacionNombre(evaluacion.getNombre());
        respuesta.setMateriaNombre(evaluacion.getMateria().getNombre());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/materias")
    public ResponseEntity<List<MateriaDTO>> listarMaterias() {
        List<Materia> materias = materiaRepository.findAll();


        List<MateriaDTO> listaDTO = materias.stream()
                .map(m -> new MateriaDTO(m.getId(), m.getNombre()))
                .toList();

        return ResponseEntity.ok(listaDTO);
    }



    @GetMapping("/evaluaciones/profesor/{profesorId}")
    public ResponseEntity<List<EvaluacionRespuestaDTO>> listarEvaluacionesPorProfesor(@PathVariable Long profesorId) {
        List<Evaluacion> evaluaciones = evaluacionRepository.findByMateriaProfesorId(profesorId);

        List<EvaluacionRespuestaDTO> listaDTO = evaluaciones.stream()
                .map(ev -> {
                    EvaluacionRespuestaDTO dto = new EvaluacionRespuestaDTO();
                    dto.setId(ev.getId());
                    dto.setNombre(ev.getNombre());
                    dto.setFecha(ev.getFecha());
                    dto.setMateriaNombre(ev.getMateria() != null ? ev.getMateria().getNombre() : "Sin materia");
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(listaDTO);
    }


}
