document.addEventListener('DOMContentLoaded', () => {
    // Escuchar cambios en las calificaciones para validar que estén entre 1 y 5
    const calificacionInputs = document.querySelectorAll('input[type="number"]');
    
    calificacionInputs.forEach(input => {
        input.addEventListener('change', function() {
            let valor = parseInt(this.value);
            if (valor < 1) this.value = 1;
            if (valor > 5) this.value = 5;
        });
    });

    const saveButton = document.querySelector('.actions .btn');
    if (saveButton) {
        saveButton.addEventListener('click', (e) => {
            alert('Evaluación guardada exitosamente. Total promedio: ' + calcularPromedio());
        });
    }

    function calcularPromedio() {
        let suma = 0;
        calificacionInputs.forEach(input => {
            suma += parseInt(input.value) || 0;
        });
        const promedio = suma / calificacionInputs.length;
        return promedio.toFixed(1);
    }
});
