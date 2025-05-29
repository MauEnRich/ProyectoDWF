document.getElementById('loginForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const email = document.getElementById('email').value;
  const contrasena = document.getElementById('contrasena').value;

  const res = await fetch('http://localhost:8080/api/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, contrasena })
  });

  if (res.ok) {
    const data = await res.json();
    if (data.tipo === 'estudiante') {
      
      sessionStorage.setItem('estudianteId', data.id);
      window.location.href = 'estudiante.html';
    } else if (data.tipo === 'profesor') {
      
      sessionStorage.setItem('profesorId', data.id);
      window.location.href = 'profesor.html';
    }
  } else {
    alert("Credenciales incorrectas");
  }
});
