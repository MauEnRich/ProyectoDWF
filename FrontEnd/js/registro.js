document.getElementById('registroForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const tipo = document.getElementById('tipo').value;
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const email = document.getElementById('email').value;
  const contrasena = document.getElementById('contrasena').value;

  const res = await fetch(`http://localhost:8080/api/auth/registro/${tipo}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, apellido, email, contrasena })
  });

  if (res.ok) {
    alert("Registro exitoso. Ahora inicia sesión.");
    window.location.href = "index.html";
  } else {
    alert("Error al registrar.");
  }
});
