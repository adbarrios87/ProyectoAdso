function filterByYear() {
    const selector = document.getElementById('yearFilter');
    if (!selector) return;
    
    const year = selector.value;
    const rows = document.querySelectorAll('.general-table tbody tr');

    rows.forEach(row => {
        // En este diseño las filas tienen un atributo data-year, de no ser así se leería del texto
        const rowYear = row.getAttribute('data-year');
        
        if (year === 'all' || rowYear === year) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

// Hacerla global para el evento onchange
window.filterByYear = filterByYear;

document.addEventListener('DOMContentLoaded', () => {
    const downloadBtns = document.querySelectorAll('.btn-download');
    downloadBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
             e.preventDefault();
             alert('Iniciando descarga del reporte de calificación en PDF...');
        });
    });
});
