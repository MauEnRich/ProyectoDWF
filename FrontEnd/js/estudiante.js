const estudianteId = sessionStorage.getItem('estudianteId');

async function cargarMaterias() {
    try {
        const response = await fetch(`http://localhost:8080/api/estudiante/${estudianteId}/materias`);
        if (!response.ok) throw new Error('Error al obtener materias');

        const materias = await response.json();
        const tbody = document.querySelector('#tablaMaterias tbody');
        tbody.innerHTML = '';

        if (materias.length === 0) {
            document.getElementById('mensaje').textContent = 'No estás inscrito en ninguna materia.';
            return;
        }

        materias.forEach(materia => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${materia.nombre}</td>
                <td>${materia.descripcion}</td>
            `;
            tbody.appendChild(fila);
        });

    } catch (error) {
        document.getElementById('mensaje').textContent = error.message;
    }
}

document.getElementById('btnIrInscribir').addEventListener('click', () => {
    window.location.href = 'inscribir.html';
});

document.getElementById('btnCerrarSesion').addEventListener('click', () => {
    sessionStorage.clear();
    window.location.href = 'index.html'; 
});

window.onload = cargarMaterias;
