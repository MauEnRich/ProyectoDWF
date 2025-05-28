document.addEventListener('DOMContentLoaded', () => {
  const tablaBody = document.querySelector('#tablaMaterias tbody');
  const mensaje = document.getElementById('mensaje');
  const profesorId = sessionStorage.getItem('profesorId');

  if (!profesorId) {
    mensaje.textContent = "No se encontró el profesor autenticado.";
    return;
  }

  const url = `http://localhost:8080/api/profesor/${profesorId}/materias`;

  fetch(url)
    .then(response => {
      if (!response.ok) throw new Error('Error al obtener las materias');
      return response.json();
    })
    .then(data => {
      if (!data || data.length === 0) {
        mensaje.textContent = "No se encontraron materias asignadas.";
        return;
      }

      data.forEach(materia => {
        const fila = document.createElement('tr');

        // Nombre de la materia
        const celdaNombre = document.createElement('td');
        celdaNombre.textContent = materia.nombre;
        fila.appendChild(celdaNombre);

        // Descripción de la materia
        const celdaDescripcion = document.createElement('td');
        celdaDescripcion.textContent = materia.descripcion;
        fila.appendChild(celdaDescripcion);

        // Nombre completo del profesor
        const celdaProfesor = document.createElement('td');
        celdaProfesor.textContent = `${materia.profesor.nombre} ${materia.profesor.apellido}`;
        fila.appendChild(celdaProfesor);

    const celdaEstudiantes = document.createElement('td');
const btnVer = document.createElement('button');
btnVer.textContent = "Ver estudiantes";
btnVer.classList.add('btnEstilo'); // agrega la clase aquí

btnVer.addEventListener('click', () => {
  sessionStorage.setItem('materiaId', materia.id);
  window.location.href = 'verEstudiantes.html';
});

celdaEstudiantes.appendChild(btnVer);
fila.appendChild(celdaEstudiantes);


        tablaBody.appendChild(fila);
      });
    })
    .catch(error => {
      mensaje.textContent = "Error al cargar las materias.";
      console.error(error);
    });

  // Botón cerrar sesión
  document.getElementById('btnCerrarSesion').addEventListener('click', () => {
    sessionStorage.clear();
    window.location.href = 'index.html';
  });
});
