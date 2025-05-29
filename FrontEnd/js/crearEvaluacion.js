document.addEventListener('DOMContentLoaded', () => {
    const profesorId = sessionStorage.getItem('profesorId');

    if (!profesorId) {
        alert('Debes iniciar sesión como profesor.');
        window.location.href = 'login.html';
        return;
    }

    cargarMaterias(profesorId);
    cargarEvaluaciones(profesorId);

    document.getElementById('evaluacionForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const nombre = document.getElementById('nombre').value;
        const fecha = document.getElementById('fecha').value;
        const materiaId = parseInt(document.getElementById('materiaId').value);

        const evaluacionDTO = {
            nombre: nombre,
            fecha: fecha,
            materiaId: materiaId,
            profesorId: parseInt(profesorId)
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
            document.getElementById('resultado').innerHTML = `Evaluación creada: ${data.nombre}`;
            document.getElementById('evaluacionForm').reset();
            cargarEvaluaciones(profesorId);
        })
        .catch(error => {
            document.getElementById('resultado').textContent = `Error: ${error.message}`;
        });
    });
});

function cargarMaterias(profesorId) {
    fetch(`http://localhost:8080/api/profesor/${profesorId}/materias`)
        .then(response => {
            if (!response.ok) throw new Error('Error al obtener materias del profesor');
            return response.json();
        })
        .then(materias => {
            const select = document.getElementById('materiaId');
            select.innerHTML = '';
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

function cargarEvaluaciones(profesorId) {
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

  document.getElementById('btnCerrarSesion').addEventListener('click', () => {
    sessionStorage.clear();
    window.location.href = 'index.html';
  });

