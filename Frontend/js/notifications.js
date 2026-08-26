document.addEventListener('DOMContentLoaded', () => {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        window.location.href = '../../login.html?v=1';
        return;
    }
    cargarNotificaciones(userId);
});

async function cargarNotificaciones(idUsuario) {
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/notificaciones/usuario/${idUsuario}`);
        if (!response.ok) {
            throw new Error('Error al cargar notificaciones');
        }
        
        const data = await response.json();
        const notificaciones = data.data;
        const tbody = document.getElementById('notifications-body');
        
        if (!notificaciones || notificaciones.length === 0) {
            tbody.innerHTML = '<tr><td colspan="4" style="text-align:center;">No tienes notificaciones activas.</td></tr>';
            return;
        }

        tbody.innerHTML = '';
        notificaciones.forEach(n => {
            const tr = document.createElement('tr');
            
            // Fecha
            const fecha = new Date(n.fechaNotificacion).toLocaleDateString('es-ES');
            
            // Icono según tipo
            let iconClass = 'fa-bell';
            if (n.codigoTipo === 'VEN' || n.codigoTipo === 'PRX') iconClass = 'fa-clock';
            else if (n.codigoTipo === 'EST') iconClass = 'fa-arrow-right-arrow-left';
            else if (n.codigoTipo === 'REG') iconClass = 'fa-file-signature';
            else if (n.codigoTipo === 'APR') iconClass = 'fa-check-circle';
            else if (n.codigoTipo === 'REC') iconClass = 'fa-times-circle';

            tr.innerHTML = `
                <td>${fecha}</td>
                <td><i class="fa-solid ${iconClass}"></i> ${n.descripcionTipo || 'Notificación'}</td>
                <td>${n.mensaje}</td>
                <td class="number">
                    <button class="btn-read" onclick="marcarComoLeida(${n.idNotificacion})">
                        <i class="fa-solid fa-check"></i> Marcar vista
                    </button>
                </td>
            `;
            tbody.appendChild(tr);
        });

    } catch (error) {
        console.error(error);
        const tbody = document.getElementById('notifications-body');
        tbody.innerHTML = '<tr><td colspan="4" style="text-align:center; color:red;">Error al cargar las notificaciones.</td></tr>';
    }
}

async function marcarComoLeida(idNotificacion) {
    try {
        const response = await fetch(`${CONFIG.API_BASE_URL}/notificaciones/${idNotificacion}/desactivar`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' }
        });
        if (!response.ok) {
            throw new Error('Error al desactivar');
        }
        
        // Recargar la tabla
        const userId = localStorage.getItem('userId');
        if (userId) {
            cargarNotificaciones(userId);
            // También deberíamos actualizar la campana global si existe
            if (typeof window.actualizarCampanaGlobal === 'function') {
                window.actualizarCampanaGlobal();
            }
        }
    } catch (error) {
        console.error(error);
        alert("Hubo un error al marcar la notificación.");
    }
}
