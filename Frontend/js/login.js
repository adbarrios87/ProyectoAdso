// Limpiamos sesión por seguridad al cargar el login
localStorage.removeItem('userRole');
localStorage.removeItem('userName');
localStorage.removeItem('userEmail');
localStorage.removeItem('userId');
localStorage.removeItem('userPhoto');
localStorage.removeItem('idRol');

/**
 * Función principal para procesar el inicio de sesión
 * Conecta con el endpoint POST /usuarios/login del Backend
 */
async function simularLogin() {
  const usuario = document.getElementById('usuario').value.trim();
  const contrasena = document.getElementById('password').value.trim();

  if (!usuario || !contrasena) {
    alert("Por favor, ingresa tu correo electrónico y contraseña.");
    return;
  }

  try {
    const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ usuario, contrasena })
    });

    const result = await response.json();

    if (result.data && result.data.successful) {
      const userData = result.data;

      // Lógica de redirección y asignación de nombre de rol según ID
      let nextPage = '';
      let roleString = '';

      switch (userData.idRol) {
        case 1: // Admin
          roleString = 'admin';
          nextPage = 'Frontend/sheets/admin_dashboard.html';
          break;
        case 3: // Proveedor
          roleString = 'proveedor';
          nextPage = 'Frontend/sheets/supplier_dashboard.html';
          break;
        case 2: // Comprador
          roleString = 'comprador';
          nextPage = 'Frontend/sheets/buyer_dashboard.html';
          break;
        case 4: // Analista de Riesgo
          roleString = 'analista';
          nextPage = 'Frontend/sheets/risk_list.html';
          break;
        case 5: // Oficial de Cumplimiento
          roleString = 'oficial';
          nextPage = 'Frontend/sheets/compliance_officer_dashboard.html';
          break;
        default:
          roleString = 'admin';
          nextPage = 'Frontend/sheets/admin_dashboard.html';
      }

      // Guardar información real en el navegador (usamos roleString para compatibilidad con main.js)
      localStorage.setItem('userRole', roleString);
      localStorage.setItem('userName', userData.nombreUsuario);
      localStorage.setItem('userEmail', userData.correoUsuario);
      localStorage.setItem('userId', userData.idUsuario);
      localStorage.setItem('idRol', userData.idRol);

      // Guardar la foto de perfil (si no tiene, guardamos un string vacío)
      localStorage.setItem('userPhoto', userData.fotoUrl || '');

      // Redirección a la página correspondiente
      window.location.href = nextPage;
    } else {
      alert("Error: " + (result.data ? result.data.message : "Usuario o contraseña incorrectos"));
    }
  } catch (error) {
    console.error("Error al conectar con el servidor:", error);
    alert("No se pudo conectar con el servidor. Verifica que el Backend esté encendido en IntelliJ.");
  }
}
