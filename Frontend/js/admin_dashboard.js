document.addEventListener('DOMContentLoaded', async () => {
    const totalUsersEl = document.getElementById('stat-total-users');
    const totalSuppliersEl = document.getElementById('stat-total-suppliers');
    const noveltySuppliersEl = document.getElementById('stat-novelty-suppliers');

    try {
        // 1. Fetch total users
        const usersRes = await fetch(`${CONFIG.API_BASE_URL}/usuarios`);
        const usersData = await usersRes.json();
        const usersList = usersData.data || [];

        if (totalUsersEl) {
            totalUsersEl.textContent = usersList.length;
        }

        // 2. Fetch total users with role "proveedor" (ID 3 or name matches 'proveedor')
        const suppliersUsersCount = usersList.filter(user => 
            user.idRol === 3 || 
            (user.nombreRol && user.nombreRol.toLowerCase().includes('proveedor'))
        ).length;

        if (totalSuppliersEl) {
            totalSuppliersEl.textContent = suppliersUsersCount;
        }

        // 3. Fetch suppliers to count those with status "validado con novedad" / "revisado con novedad" (ID 9 or 10)
        const provsRes = await fetch(`${CONFIG.API_BASE_URL}/proveedores`);
        const provsData = await provsRes.json();
        const provsList = provsData.data || [];

        // Count suppliers in state 10 (REVISADO_CON_NOVEDAD) or 9 (EN_REVISION_CON_NOVEDAD)
        const noveltyCount = provsList.filter(p => 
            p.idEstadoProveedor === 10 || 
            p.idEstadoProveedor === 9
        ).length;

        if (noveltySuppliersEl) {
            noveltySuppliersEl.textContent = noveltyCount;
        }

    } catch (error) {
        console.error("Error loading dashboard statistics:", error);
        if (totalUsersEl) totalUsersEl.textContent = "Error";
        if (totalSuppliersEl) totalSuppliersEl.textContent = "Error";
        if (noveltySuppliersEl) noveltySuppliersEl.textContent = "Error";
    }
});
