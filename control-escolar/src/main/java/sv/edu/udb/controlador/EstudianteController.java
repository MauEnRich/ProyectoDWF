package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.dto.NotaDTO;
import sv.edu.udb.dto.ProfesorDTO;
import sv.edu.udb.dto.HorarioDTO;
import sv.edu.udb.modelo.Inscripcion;
import sv.edu.udb.modelo.Nota;
import sv.edu.udb.repositorio.InscripcionRepository;
import sv.edu.udb.repositorio.NotaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudiante")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private InscripcionRepository inscripcionRepo;

    @Autowired
    private NotaRepository notaRepo;


    @GetMapping("/{id}/materias")
    public ResponseEntity<?> getMateriasPorEstudiante(@PathVariable Long id) {
        List<Inscripcion> inscripciones = inscripcionRepo.findByEstudianteId(id);

        List<MateriaDTO> materiasDTO = inscripciones.stream()
                .map(inscripcion -> {
                    var m = inscripcion.getMateria();
                    return new MateriaDTO(
                            m.getId(),
                            m.getNombre(),
                            m.getDescripcion(),
                            new ProfesorDTO(
                                    m.getProfesor().getId(),
                                    m.getProfesor().getNombre(),
                                    m.getProfesor().getApellido(),
                                    m.getProfesor().getEmail()
                            )
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(materiasDTO);
    }


    @GetMapping("/{estudianteId}/materias/{materiaId}/notas")
    public ResponseEntity<?> getNotasPorMateria(
            @PathVariable Long estudianteId,
            @PathVariable Long materiaId) {


        List<Inscripcion> inscripciones = inscripcionRepo.findByEstudianteIdAndMateriaId(estudianteId, materiaId);
        if (inscripciones.isEmpty()) {
            return ResponseEntity.badRequest().body("El estudiante no está inscrito en esta materia.");
        }


        List<Nota> notas = notaRepo.findByEstudianteIdAndMateriaId(estudianteId, materiaId);


        List<NotaDTO> notasDTO = notas.stream()
                .map(n -> new NotaDTO(
                        n.getEvaluacion().getNombre(),
                        n.getEvaluacion().getFecha(),
                        n.getNota()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(notasDTO);
    }


    @GetMapping("/{id}/horario")
    public ResponseEntity<?> getHorarioPorEstudiante(@PathVariable Long id) {
        List<Inscripcion> inscripciones = inscripcionRepo.findByEstudianteId(id);

        List<HorarioDTO> horarios = inscripciones.stream()
                .flatMap(inscripcion -> inscripcion.getMateria().getHorarios().stream()
                        .map(h -> new HorarioDTO(
                                inscripcion.getMateria().getNombre(),
                                h.getDiaSemana(),
                                h.getHoraInicio(),
                                h.getHoraFin()
                        ))
                ).collect(Collectors.toList());

        return ResponseEntity.ok(horarios);
    }
}
