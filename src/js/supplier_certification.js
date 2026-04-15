document.addEventListener('DOMContentLoaded', () => {
    const generateBtn = document.getElementById('generatePDF');
    if (generateBtn) {
        generateBtn.addEventListener('click', () => {
             const recipient = document.getElementById('recipient').value;
             if(recipient.trim() === '') {
                 alert('Por favor indica a quién va dirigida la certificación.');
                 return;
             }
             
             const includeRating = document.getElementById('includeRating').checked;
             let mensaje = `Generando PDF dirigido a: ${recipient}...`;
             if (includeRating) {
                 mensaje += '\n(Incluyendo la última calificación)';
             }
             
             alert(mensaje + '\nDescarga de certificado iniciada.');
             
             // Aquí iría la lógica real de generación de PDF.
             // window.print() o librerías como jsPDF.
        });
    }
});
