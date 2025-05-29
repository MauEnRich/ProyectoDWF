const estudianteId = sessionStorage.getItem('estudianteId');

async function cargarMaterias() {
    try {
        const response = await fetch('http://localhost:8080/api/materias');
        if (!response.ok) throw new Error('Error al obtener materias');

        const materias = await response.json();
        console.log(materias);
        const tbody = document.querySelector('#tablaMaterias tbody');
        tbody.innerHTML = '';
        document.getElementById('mensaje').textContent = '';

        if (materias.length === 0) {
            document.getElementById('mensaje').textContent = 'No hay materias disponibles.';
            return;
        }

        materias.forEach(materia => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${materia.nombre}</td>
                <td>${materia.descripcion}</td>
                <td><button class="btnInscribir" onclick="inscribirMateria(${materia.id})">Inscribir</button></td>
            `;
            tbody.appendChild(fila);
        });

    } catch (error) {
        document.getElementById('mensaje').textContent = error.message;
    }
}

async function inscribirMateria(materiaId) {
    try {
        const response = await fetch('http://localhost:8080/api/inscripciones', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                estudianteId: estudianteId,
                materiaId: materiaId
            })
        });

        if (response.ok) {
            alert('Materia inscrita con éxito');
            cargarMaterias();
        } else {
            const errMsg = await response.text();
            alert('Error al inscribir: ' + errMsg);
        }
    } catch (error) {
        alert('Error al inscribir: ' + error.message);
    }
}

document.getElementById('btnVolver').addEventListener('click', () => {
    window.location.href = 'estudiante.html';
});


document.getElementById('btnCerrarSesion').addEventListener('click', () => {
        sessionStorage.clear(); 
        window.location.href = 'index.html';
    });

window.onload = cargarMaterias;

