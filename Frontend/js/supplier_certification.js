document.addEventListener('DOMContentLoaded', () => {
    const generateBtn = document.getElementById('generatePDF');
    if (generateBtn) {
        generateBtn.addEventListener('click', async () => {
            const userEmail = localStorage.getItem('userEmail');
            if (!userEmail) {
                alert("No se encontró la sesión del usuario.");
                return;
            }

            const recipient = document.getElementById('recipient').value.trim();
            const includeRating = document.getElementById('includeRating').checked;

            const btn = document.getElementById('generatePDF');
            const originalText = btn.textContent;
            btn.disabled = true;
            btn.textContent = 'Generando...';

            try {
                // 1. Obtener datos del proveedor
                const provResponse = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${userEmail}`);
                const provResult = await provResponse.json();

                if (!provResult.data) {
                    alert("No se encontró información del proveedor.");
                    return;
                }

                const proveedor = provResult.data;
                const idProveedor = proveedor.idProveedor;
                const idTipoIdentificacion = proveedor.idTipoIdentificacion;
                const documentNumber = proveedor.numeroIdentificacion;

                // Nombre completo o razón social
                let supplierName = '';
                if (proveedor.razonSocial && proveedor.razonSocial.trim() !== '') {
                    supplierName = proveedor.razonSocial.trim();
                } else {
                    const nom = proveedor.nombres ? proveedor.nombres.trim() : '';
                    const ape = proveedor.apellidos ? proveedor.apellidos.trim() : '';
                    supplierName = `${nom} ${ape}`.trim();
                }

                // 2. Obtener tipo de identificación
                const idListResponse = await fetch(`${CONFIG.API_BASE_URL}/tipo_identificacion`);
                const idListResult = await idListResponse.json();
                const matchingId = idListResult.data ? idListResult.data.find(item => item.idTipoIdentificacion === idTipoIdentificacion) : null;
                const documentTypeDesc = matchingId ? matchingId.descripcion : 'Documento';

                // 3. Obtener última evaluación si está marcada
                let ratingText = '';
                if (includeRating) {
                    try {
                        const evalResponse = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_proveedor/proveedor/${idProveedor}`);
                        const evalResult = await evalResponse.json();

                        if (evalResult.data && evalResult.data.length > 0) {
                            const sortedEvals = evalResult.data.sort((a, b) => b.idEvaluacion - a.idEvaluacion);
                            const lastEval = sortedEvals[0];
                            const dateFormatted = formatSpanishDate(lastEval.fechaCreado);
                            ratingText = `Asimismo, se hace constar que en su última evaluación de desempeño, el proveedor obtuvo un puntaje de ${lastEval.puntaje}/100, registrada el ${dateFormatted}.`;
                        } else {
                            alert("Advertencia: No se encontraron evaluaciones para el proveedor. La certificación se generará sin incluir la calificación.");
                        }
                    } catch (evalErr) {
                        console.error("Error obteniendo evaluaciones:", evalErr);
                    }
                }

                // Formatear fechas
                const today = new Date();
                const currentCaliDate = `Cali, ${formatSpanishDate(today)}`;
                const supplierRegistrationDate = formatSpanishDate(proveedor.fechaCreado);

                // Cargar imagen de firma (PNG)
                const signatureImg = new Image();
                signatureImg.src = '../assets/Firma_de_Juan_C.png';
                await new Promise((resolve) => {
                    signatureImg.onload = resolve;
                    signatureImg.onerror = resolve;
                });

                // Generar PDF con jsPDF
                const { jsPDF } = window.jspdf;
                const doc = new jsPDF({
                    orientation: 'portrait',
                    unit: 'mm',
                    format: 'a4'
                });

                // Fuentes y Estilos
                doc.setFont("helvetica", "normal");

                let y = 40;

                // Fecha
                doc.setFontSize(11);
                doc.text(currentCaliDate, 190, y, { align: "right" });
                y += 25;

                // Destinatario
                const directedTo = recipient ? `${recipient}:` : 'A quien pueda interesar:';
                doc.setFont("helvetica", "bold");
                doc.text(directedTo, 20, y);
                y += 15;

                // Párrafos
                doc.setFont("helvetica", "normal");

                const paragraph1 = `Por medio de la presente, certifico que ${supplierName}, identificado(a) con ${documentTypeDesc} No. ${documentNumber}, es proveedor activo de nuestra compañía desde el ${supplierRegistrationDate}.`;
                const paragraph2 = "En el desarrollo de nuestras operaciones, ha demostrado ser un aliado comercial altamente confiable, caracterizándose por la calidad de sus productos, el cumplimiento estricto en los tiempos de entrega y su excelente servicio al cliente.";
                const paragraph3 = "Por su seriedad y responsabilidad corporativa, no dudamos en otorgarles esta referencia comercial, considerándolos una empresa idónea para establecer futuras relaciones de negocios.";
                const paragraph5 = "Esta constancia se expide a solicitud del interesado. Para cualquier validación adicional, pueden comunicarse a los canales de contacto detallados a continuación";

                const paragraphs = [paragraph1, paragraph2, paragraph3];
                if (ratingText !== '') {
                    paragraphs.push(ratingText);
                }
                paragraphs.push(paragraph5);

                paragraphs.forEach(text => {
                    const lines = doc.splitTextToSize(text, 170);
                    doc.text(lines, 20, y);
                    y += (lines.length * 6) + 8; // Altura dinámica basada en las líneas + espacio entre párrafos
                });

                y += 10;

                // Despedida
                doc.text("Atentamente,", 20, y);
                y += 5;

                // Dibujar la firma si cargó correctamente
                try {
                    if (signatureImg.complete && signatureImg.naturalWidth > 0) {
                        doc.addImage(signatureImg, 'PNG', 20, y, 45, 15);
                    }
                } catch (e) {
                    console.error("Error agregando la firma al PDF:", e);
                }
                y += 20;

                // Firma
                doc.setFont("helvetica", "bold");
                doc.text("Juan Carlos Cadena", 20, y);
                doc.setFont("helvetica", "normal");
                doc.text("Jefe de Compras", 20, y + 5);
                doc.text("Parere GRC", 20, y + 10);
                doc.text("email: jcadena@pareregrc.com", 20, y + 15);

                // Guardar PDF
                doc.save(`certificacion_${supplierName.replace(/\s+/g, '_')}.pdf`);

            } catch (error) {
                console.error("Error generando certificación:", error);
                alert("Error al generar la certificación.");
            } finally {
                btn.disabled = false;
                btn.textContent = originalText;
            }
        });
    }
});

function formatSpanishDate(dateVal) {
    if (!dateVal) return '';
    const date = new Date(dateVal);
    if (isNaN(date.getTime())) return dateVal.toString();
    const months = [
        "enero", "febrero", "marzo", "abril", "mayo", "junio",
        "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    ];

    let isIso = false;
    if (typeof dateVal === 'string') {
        isIso = dateVal.includes('T') || dateVal.includes('Z');
    }

    const day = isIso ? date.getUTCDate() : date.getDate();
    const month = months[isIso ? date.getUTCMonth() : date.getMonth()];
    const year = isIso ? date.getUTCFullYear() : date.getFullYear();
    return `${day} de ${month} de ${year}`;
}
