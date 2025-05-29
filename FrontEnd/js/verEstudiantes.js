document.addEventListener('DOMContentLoaded', () => {
  const tablaBody = document.querySelector('#tablaEstudiantes tbody');
  const mensaje = document.getElementById('mensaje');
  const materiaId = sessionStorage.getItem('materiaId');

  if (!materiaId) {
    mensaje.textContent = "No se encontró ninguna materia seleccionada.";
    return;
  }

  const url = `http://localhost:8080/api/profesor/materia/${materiaId}/estudiantes`;

  fetch(url)
    .then(response => {
      if (!response.ok) throw new Error('Error al obtener los estudiantes');
      return response.json();
    })
    .then(data => {
      if (!data || data.length === 0) {
        mensaje.textContent = "No hay estudiantes inscritos en esta materia.";
        return;
      }

      data.forEach(estudiante => {
        const fila = document.createElement('tr');

        const tdNombre = document.createElement('td');
        tdNombre.textContent = estudiante.nombre;
        fila.appendChild(tdNombre);

        const tdApellido = document.createElement('td');
        tdApellido.textContent = estudiante.apellido;
        fila.appendChild(tdApellido);

        const tdEmail = document.createElement('td');
        tdEmail.textContent = estudiante.email;
        fila.appendChild(tdEmail);

        tablaBody.appendChild(fila);
      });
    })
    .catch(error => {
      mensaje.textContent = "Error al cargar los estudiantes.";
      console.error(error);
    });

  document.getElementById('btnRegresar').addEventListener('click', () => {
    window.location.href = 'profesor.html';
  });
});
