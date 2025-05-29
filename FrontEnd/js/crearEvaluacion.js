document.addEventListener('DOMContentLoaded', () => {
    const profesorId = sessionStorage.getItem('profesorId');

    if (!profesorId) {
        alert('Debes iniciar sesión como profesor.');
        window.location.href = 'login.html';
        return;
    }

    cargarMaterias();
    cargarEvaluaciones();

    document.getElementById('evaluacionForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const nombre = document.getElementById('nombre').value;
        const fecha = document.getElementById('fecha').value;
        const materiaId = parseInt(document.getElementById('materiaId').value);

        const evaluacionDTO = {
            nombre: nombre,
            fecha: fecha,
            materiaId: materiaId
        };

        fetch('http://localhost:8080/api/profesor/evaluaciones', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(evaluacionDTO)
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('resultado').innerHTML = `
            
            `;
            document.getElementById('evaluacionForm').reset();
            cargarEvaluaciones();
        })
        .catch(error => {
            document.getElementById('resultado').textContent = `Error: ${error.message}`;
        });
    });
});

function cargarMaterias() {
    const profesorId = sessionStorage.getItem('profesorId');

    if (!profesorId) {
        console.error('No se encontró el ID del profesor en sessionStorage');
        return;
    }

    fetch(`http://localhost:8080/api/profesor/${profesorId}/materias`)
        .then(response => {
            if (!response.ok) throw new Error('Error al obtener materias del profesor');
            return response.json();
        })
        .then(materias => {
            const select = document.getElementById('materiaId');
            select.innerHTML = ''; // Limpiar opciones anteriores

            materias.forEach(materia => {
                const option = document.createElement('option');
                option.value = materia.id;
                option.textContent = materia.nombre;
                select.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar materias del profesor:', error);
        });
}


function cargarEvaluaciones() {
    const profesorId = sessionStorage.getItem('profesorId');

    if (!profesorId) {
        console.error('No se encontró el ID del profesor en sessionStorage');
        return;
    }

    fetch(`http://localhost:8080/api/profesor/evaluaciones/profesor/${profesorId}`)
    .then(response => {
        if (!response.ok) throw new Error('No se pudieron cargar las evaluaciones');
        return response.json();
    })
    .then(evaluaciones => {
        const tbody = document.querySelector('#tablaEvaluaciones tbody');
        tbody.innerHTML = '';

        evaluaciones.forEach(evaluacion => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${evaluacion.nombre}</td>
                <td>${evaluacion.fecha}</td>
                <td>${evaluacion.materiaNombre}</td>
                <td>
                    <button class="btn-calificar" onclick="abrirModalNota(${evaluacion.id}, '${evaluacion.nombre}')">
                        Calificar
                    </button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    })
    .catch(error => {
        console.error('Error al cargar evaluaciones:', error);
    });
}


let evaluacionIdActual = null;

function abrirModalNota(evaluacionId, evaluacionNombre) {
    evaluacionIdActual = evaluacionId;
    document.getElementById('modalEvaluacionNombre').textContent = evaluacionNombre;
    document.getElementById('estudianteId').value = '';
    document.getElementById('notaInput').value = '';
    document.getElementById('mensajeNota').textContent = '';
    document.getElementById('modalNota').style.display = 'flex';
}

function cerrarModalNota() {
    document.getElementById('modalNota').style.display = 'none';
}

function enviarNota() {
    const estudianteId = parseInt(document.getElementById('estudianteId').value);
    const nota = parseFloat(document.getElementById('notaInput').value);

    if (!estudianteId || isNaN(nota)) {
        alert('Por favor, ingresa un ID de estudiante válido y una nota válida.');
        return;
    }

    const notaDTO = {
        estudianteId: estudianteId,
        evaluacionId: evaluacionIdActual,
        nota: nota
    };

    fetch('http://localhost:8080/api/profesor/notas', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(notaDTO)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('mensajeNota').style.color = 'green';
        document.getElementById('mensajeNota').textContent = `Nota guardada: ${data.nota} para estudiante ID ${data.estudianteId}`;
        cerrarModalNota();
        cargarEvaluaciones();
    })
    .catch(error => {
        document.getElementById('mensajeNota').style.color = 'red';
        document.getElementById('mensajeNota').textContent = `Error: ${error.message}`;
    });
}

  document.getElementById('btnCerrarSesion').addEventListener('click', () => {
    sessionStorage.clear();
    window.location.href = 'index.html';
  });

