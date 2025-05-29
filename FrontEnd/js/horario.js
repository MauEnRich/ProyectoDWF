document.addEventListener('DOMContentLoaded', () => {
  const estudianteId = sessionStorage.getItem('estudianteId');
  if (!estudianteId) {
    alert('No se encontró el ID del estudiante en la sesión. Por favor, inicia sesión.');
    return;
  }

  fetch(`http://localhost:8080/api/estudiante/${estudianteId}/horario`)
    .then(response => {
      if (!response.ok) throw new Error('Error al obtener el horario');
      return response.json();
    })
    .then(horarios => {
      const tbody = document.querySelector('#tabla-horario tbody');
      horarios.forEach(h => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
          <td>${h.materia}</td>
          <td>${h.diaSemana}</td>
          <td>${h.horaInicio}</td>
          <td>${h.horaFin}</td>
        `;
        tbody.appendChild(fila);
      });
    })
    .catch(error => {
      console.error(error);
      alert('No se pudo cargar el horario');
    });
});

document.getElementById('btnCerrarSesion').addEventListener('click', () => {
        sessionStorage.clear(); 
        window.location.href = 'index.html';
    });
