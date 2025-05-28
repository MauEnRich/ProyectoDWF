document.addEventListener('DOMContentLoaded', () => {
    const tablaBody = document.querySelector('#tablaEvaluaciones tbody');
    const mensaje = document.getElementById('mensaje');
    const promedioElem = document.getElementById('promedio');

    const estudianteId = sessionStorage.getItem('estudianteId');
    if (!estudianteId) {
        mensaje.textContent = "No se encontró el usuario autenticado.";
        return;
    }

    const url = `http://localhost:8080/api/estudiante/${estudianteId}/notas`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (!data || data.length === 0) {
                mensaje.textContent = "No se encontraron evaluaciones ni notas.";
                return;
            }

            let sumaNotas = 0;
            let cantidadNotas = 0;

            data.forEach(item => {
                const fila = document.createElement('tr');

                const celdaMateria = document.createElement('td');
                celdaMateria.textContent = item.nombreMateria;
                fila.appendChild(celdaMateria);

                const celdaEvaluacion = document.createElement('td');
                celdaEvaluacion.textContent = item.nombreEvaluacion;
                fila.appendChild(celdaEvaluacion);

                const celdaFecha = document.createElement('td');
                celdaFecha.textContent = new Date(item.fechaEvaluacion).toLocaleDateString();
                fila.appendChild(celdaFecha);

                const celdaNota = document.createElement('td');
                celdaNota.textContent = item.nota !== null ? item.nota : "Sin nota";
                fila.appendChild(celdaNota);

                tablaBody.appendChild(fila);

                if (item.nota !== null) {
                    sumaNotas += item.nota;
                    cantidadNotas++;
                }
            });

            if (cantidadNotas > 0) {
                const promedio = sumaNotas / cantidadNotas;
                promedioElem.textContent = `Promedio de notas: ${promedio.toFixed(2)}`;
            } else {
                promedioElem.textContent = "No hay notas para calcular promedio.";
            }
        })
        .catch(error => {
            mensaje.textContent = "Error al cargar las evaluaciones y notas.";
            console.error(error);
        });

    document.getElementById('btnCerrarSesion').addEventListener('click', () => {
        sessionStorage.clear(); 
        window.location.href = 'index.html';
    });
});
