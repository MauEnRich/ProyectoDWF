package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.dto.ProfesorDTO;
import sv.edu.udb.modelo.Materia;
import sv.edu.udb.modelo.Profesor;
import sv.edu.udb.repositorio.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepo;

    @GetMapping
    public List<MateriaDTO> getAllMaterias() {
        List<Materia> materias = materiaRepo.findAll();

        return materias.stream().map(m -> {
            Profesor p = m.getProfesor();
            ProfesorDTO profesorDTO = new ProfesorDTO(p.getId(), p.getNombre(), p.getApellido(), p.getEmail());
            return new MateriaDTO(m.getId(), m.getNombre(), m.getDescripcion(), profesorDTO);
        }).collect(Collectors.toList());
    }
}
