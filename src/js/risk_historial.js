document.addEventListener('DOMContentLoaded', () => {
    const filterForm = document.querySelector('.filter-form');
    if (filterForm) {
        filterForm.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const analyst = document.getElementById('filterAnalyst').value.toLowerCase();
            const name = document.getElementById('filterName').value.toLowerCase();
            const nit = document.getElementById('filterNIT').value.toLowerCase();
            
            const rows = document.querySelectorAll('.risk-history-table tbody tr');
            
            rows.forEach(row => {
                const rowNit = row.cells[1].textContent.toLowerCase();
                const rowName = row.cells[2].textContent.toLowerCase();
                const rowAnalyst = row.cells[4].textContent.toLowerCase();
                
                const matchAnalyst = analyst === "" || rowAnalyst.includes(analyst);
                const matchName = name === "" || rowName.includes(name);
                const matchNit = nit === "" || rowNit.includes(nit);
                
                if (matchAnalyst && matchName && matchNit) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
        
        filterForm.addEventListener('reset', () => {
            // Se ejecutará justo después del reset form, restablecemos todas las filas
            setTimeout(() => {
                const rows = document.querySelectorAll('.risk-history-table tbody tr');
                rows.forEach(row => row.style.display = '');
            }, 10);
        });
    }
});
