package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.EvaluacionNotaDTO;
import sv.edu.udb.repositorio.NotaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
@CrossOrigin(origins = "*")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping("/{id}/notas")
    public ResponseEntity<List<EvaluacionNotaDTO>> getNotasYEvaluaciones(@PathVariable Long id) {
        List<EvaluacionNotaDTO> resultados = notaRepository.findEvaluacionesYNotasPorEstudiante(id);
        return ResponseEntity.ok(resultados);
    }
}
