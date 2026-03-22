function buscarProveedores() {
    const nombre = document.getElementById('nombre').value.toLowerCase();
    const nit = document.getElementById('nit').value.toLowerCase();
    const estado = document.getElementById('estado').value.toLowerCase();
    
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

        if (matchNombre && matchNit && matchEstado) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

// Asegurarse de que la función esté disponible en el objeto window para el onclick en HTML
window.buscarProveedores = buscarProveedores;
