function buscarProveedores() {
    const nombre = document.getElementById('nombre').value.toLowerCase();
    const nit = document.getElementById('nit').value.toLowerCase();
    const estado = document.getElementById('estado').value.toLowerCase();
    const fechaInicio = document.getElementById('fechaInicio').value;
    const fechaFin = document.getElementById('fechaFin').value;
    
    const rows = document.querySelectorAll('#resultados tr');

    rows.forEach(row => {
        const rowName = row.cells[0].textContent.toLowerCase();
        const rowNit = row.cells[1].textContent.toLowerCase();
        const rowEstado = row.cells[6].textContent.toLowerCase();

        const matchNombre = nombre === '' || rowName.includes(nombre);
        const matchNit = nit === '' || rowNit.includes(nit);
        
        // Manejo especial para "Todos"
        let matchEstado = true;
        if (estado !== '' && estado !== 'todos') {
            matchEstado = rowEstado.includes(estado);
        }

        // Filtro por rango de fechas
        let matchFecha = true;
        if (fechaInicio || fechaFin) {
            // Nota: La columna de fecha de registro es la 7 (índice 6 en 0-based)
            // Ajusta este índice según la estructura real de tu tabla
            const rowFecha = row.cells[7]?.textContent || '';
            if (rowFecha) {
                const fechaRegistro = new Date(rowFecha.split('/').reverse().join('-'));
                const inicio = fechaInicio ? new Date(fechaInicio) : null;
                const fin = fechaFin ? new Date(fechaFin) : null;
                
                if (inicio && fechaRegistro < inicio) matchFecha = false;
                if (fin && fechaRegistro > fin) matchFecha = false;
            }
        }

        if (matchNombre && matchNit && matchEstado && matchFecha) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

// Asegurarse de que la función esté disponible en el objeto window para el onclick en HTML
window.buscarProveedores = buscarProveedores;
