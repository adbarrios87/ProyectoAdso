document.addEventListener('DOMContentLoaded', () => {
    cargarAlertasVencimiento();
});

async function cargarAlertasVencimiento() {
    try {
        // 1. Obtener todas las notificaciones
        const notifResponse = await fetch(`${CONFIG.API_BASE_URL}/notificaciones`);
        if (!notifResponse.ok) throw new Error("Error al cargar notificaciones");
        const notifResult = await notifResponse.json();
        
        // Filtrar solo las de tipo VEN (vencida) y PRX (próximo a vencer)
        const alertas = (notifResult.data || []).filter(n => n.codigoTipo === 'VEN' || n.codigoTipo === 'PRX');
        
        // 2. Obtener los proveedores para cruzar los datos
        const provResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores`);
        if (!provResponse.ok) throw new Error("Error al cargar proveedores");
        const provResult = await provResponse.json();
        const proveedores = provResult.data || [];
        
        const tbody = document.getElementById('alerts-body');
        if (!tbody) return;
        
        tbody.innerHTML = '';
        
        let criticos = 0;
        let urgentes = 0;
        let pendientes = 0;

        const hoy = new Date();

        alertas.forEach(alerta => {
            // Buscar el proveedor correspondiente usando idUsuario
            const prov = proveedores.find(p => p.idUsuario === alerta.idUsuario);
            if (!prov) return; // Si no hay proveedor asociado, omitir
            
            // Asumimos que la fecha de vencimiento es 1 año después de la aprobación (o creación)
            let fechaBase = prov.fechaAprobacion ? new Date(prov.fechaAprobacion) : new Date(prov.fechaCreado);
            const fechaVencimiento = new Date(fechaBase);
            fechaVencimiento.setDate(fechaVencimiento.getDate() + 365);
            
            const diasRestantes = Math.ceil((fechaVencimiento - hoy) / (1000 * 60 * 60 * 24));
            
            let badgeClass = 'info';
            let rowClass = 'pending-row';
            let estadoText = alerta.codigoTipo === 'VEN' ? 'VENCIDO' : 'PRÓXIMO';
            
            if (diasRestantes <= 0) {
                badgeClass = 'danger';
                rowClass = 'critical-row';
                estadoText = 'VENCIDO';
                criticos++;
            } else if (diasRestantes <= 15) {
                badgeClass = 'danger';
                rowClass = 'critical-row';
                estadoText = 'CRÍTICO';
                criticos++;
            } else if (diasRestantes <= 30) {
                badgeClass = 'warning';
                rowClass = 'warning-row';
                estadoText = 'URGENTE';
                urgentes++;
            } else {
                badgeClass = 'info';
                rowClass = 'pending-row';
                estadoText = 'PENDIENTE';
                pendientes++;
            }
            
            const tr = document.createElement('tr');
            tr.className = rowClass;
            tr.innerHTML = `
                <td>${prov.razonSocial || (prov.nombres + ' ' + prov.apellidos)}</td>
                <td>${prov.numeroIdentificacion}</td>
                <td>Expediente</td>
                <td>${fechaVencimiento.toLocaleDateString('es-ES')}</td>
                <td><span>${diasRestantes > 0 ? diasRestantes + ' días' : Math.abs(diasRestantes) + ' días vencido'}</span></td>
                <td><span class="badge ${badgeClass}">${estadoText}</span></td>
                <td>
                  <button class="action-btn" title="Notificar"><i class="fa-solid fa-bell"></i></button>
                  <button class="action-btn" title="Ver perfil" onclick="window.location.href='buyer_supplier_profile.html?id=${prov.idProveedor}'"><i class="fa-solid fa-eye"></i></button>
                </td>
            `;
            tbody.appendChild(tr);
        });
        
        // Si no hay alertas
        if (alertas.length === 0) {
            tbody.innerHTML = '<tr><td colspan="7" style="text-align:center;">No hay notificaciones de vencimiento registradas en la BD.</td></tr>';
        }

        // Actualizar KPIs si existen
        const elsCriticos = document.querySelectorAll('.alert-kpi-card.critical .kpi-value');
        if (elsCriticos.length > 0) elsCriticos[0].textContent = criticos;
        const elsUrgentes = document.querySelectorAll('.alert-kpi-card.warning .kpi-value');
        if (elsUrgentes.length > 0) elsUrgentes[0].textContent = urgentes;
        const elsPendientes = document.querySelectorAll('.alert-kpi-card.pending .kpi-value');
        if (elsPendientes.length > 0) elsPendientes[0].textContent = pendientes;

    } catch (e) {
        console.error(e);
        const tbody = document.getElementById('alerts-body');
        if(tbody) tbody.innerHTML = '<tr><td colspan="7" style="color:red; text-align:center;">Error cargando alertas</td></tr>';
    }
}
