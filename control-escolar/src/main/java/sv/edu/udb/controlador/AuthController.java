package sv.edu.udb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.modelo.Estudiante;
import sv.edu.udb.modelo.Profesor;
import sv.edu.udb.repositorio.EstudianteRepository;
import sv.edu.udb.repositorio.ProfesorRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private ProfesorRepository profesorRepo;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String contrasena = datos.get("contrasena");

        Estudiante estudiante = estudianteRepo.findByEmailAndContrasena(email, contrasena);
        if (estudiante != null) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("tipo", "estudiante");
            respuesta.put("id", estudiante.getId());
            respuesta.put("nombre", estudiante.getNombre());
            return ResponseEntity.ok(respuesta);
        }

        Profesor profesor = profesorRepo.findByEmailAndContrasena(email, contrasena);
        if (profesor != null) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("tipo", "profesor");
            respuesta.put("id", profesor.getId());
            respuesta.put("nombre", profesor.getNombre());
            return ResponseEntity.ok(respuesta);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }


    @PostMapping("/registro/estudiante")
    public ResponseEntity<Estudiante> registrarEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevo = estudianteRepo.save(estudiante);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/registro/profesor")
    public ResponseEntity<Profesor> registrarProfesor(@RequestBody Profesor profesor) {
        Profesor nuevo = profesorRepo.save(profesor);
        return ResponseEntity.ok(nuevo);
    }
}
