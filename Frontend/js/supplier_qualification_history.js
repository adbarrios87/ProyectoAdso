let allEvaluaciones = [];
let providerId = null;

async function initHistory() {
    const historyTbody = document.getElementById('history-tbody');
    const urlParams = new URLSearchParams(window.location.search);
    providerId = urlParams.get('id');

    if (!providerId) {
        providerId = localStorage.getItem('selectedProviderId');
    }

    // Si aún no hay providerId, intentar buscar por email del usuario logueado
    if (!providerId) {
        const userEmail = localStorage.getItem('userEmail');
        if (userEmail) {
            try {
                const response = await fetch(`${CONFIG.API_BASE_URL}/proveedores/by-email?email=${userEmail}`);
                const result = await response.json();
                if (result.data) {
                    providerId = result.data.idProveedor;
                    localStorage.setItem('selectedProviderId', providerId);
                }
            } catch (err) {
                console.error("Error al buscar proveedor por email:", err);
            }
        }
    }

    if (!providerId) {
        if (historyTbody) {
            historyTbody.innerHTML = `<tr><td colspan="4" style="text-align: center; color: var(--danger-text);">Error: No se pudo determinar el proveedor.</td></tr>`;
        }
        return;
    }

    try {
        // Cargar catálogo de calificaciones para mapeo
        let califMap = {};
        try {
            const califResponse = await fetch(`${CONFIG.API_BASE_URL}/calificacion`);
            const califResult = await califResponse.json();
            if (califResult.data) {
                califResult.data.forEach(c => {
                    califMap[c.idCalificacion] = c.descripcion;
                });
            }
        } catch (err) {
            console.warn("Error cargando catálogo de calificaciones:", err);
        }

        // Obtener evaluaciones del proveedor
        const response = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_proveedor/proveedor/${providerId}`);
        const result = await response.json();

        if (result.data && result.data.length > 0) {
            allEvaluaciones = result.data;

            // Ordenar de más reciente a más antigua
            allEvaluaciones.sort((a, b) => {
                const dateA = a.fechaCreado ? new Date(a.fechaCreado) : new Date(0);
                const dateB = b.fechaCreado ? new Date(b.fechaCreado) : new Date(0);
                return dateB - dateA;
            });

            // Poblar dinámicamente el filtro de años
            poblarFiltroAnos(allEvaluaciones);

            // Renderizar tabla
            renderizarTabla(allEvaluaciones, califMap);
        } else {
            if (historyTbody) {
                historyTbody.innerHTML = `<tr><td colspan="4" style="text-align: center;">No hay historial de calificaciones registrado.</td></tr>`;
            }
        }
    } catch (error) {
        console.error("Error al cargar el historial:", error);
        if (historyTbody) {
            historyTbody.innerHTML = `<tr><td colspan="4" style="text-align: center; color: var(--danger-text);">Error al cargar datos desde el servidor.</td></tr>`;
        }
    }
}

function poblarFiltroAnos(evaluaciones) {
    const yearFilter = document.getElementById('yearFilter');
    if (!yearFilter) return;

    const uniqueYears = [...new Set(evaluaciones.map(ev => {
        return ev.fechaCreado ? new Date(ev.fechaCreado).getFullYear() : null;
    }).filter(Boolean))];

    uniqueYears.sort((a, b) => b - a);

    let options = '<option value="all">Todos los años</option>';
    uniqueYears.forEach(year => {
        options += `<option value="${year}">${year}</option>`;
    });
    yearFilter.innerHTML = options;
}

function renderizarTabla(evaluaciones, califMap) {
    const historyTbody = document.getElementById('history-tbody');
    if (!historyTbody) return;

    let html = '';
    evaluaciones.forEach(ev => {
        const fechaObj = ev.fechaCreado ? new Date(ev.fechaCreado) : null;
        const year = fechaObj ? fechaObj.getFullYear() : 'N/A';
        const periodo = fechaObj ? `I - ${year}` : 'N/A';
        const fechaTexto = fechaObj ? fechaObj.toLocaleDateString() : 'N/A';

        // Mapear el badge del nivel de confianza
        let badgeClass = 'warning';
        let descCalificacion = 'ALTERNATIVO';

        if (ev.idCalificacion === 1) {
            badgeClass = 'success';
            descCalificacion = 'CONFIABLE';
        } else if (ev.idCalificacion === 3) {
            badgeClass = 'danger';
            descCalificacion = 'NO CONFIABLE';
        } else {
            badgeClass = 'warning';
            descCalificacion = 'ALTERNATIVO';
        }

        const estrellas = ev.puntaje ? (ev.puntaje / 20).toFixed(1) : '0';

        const role = localStorage.getItem('userRole') || '';
        const isProveedor = role.toLowerCase() === 'proveedor';

        let accionesBtnHtml = '';
        if (isProveedor) {
            accionesBtnHtml = `
                <button class="icon-btn btn-pdf-download" data-id="${ev.idEvaluacion}" title="Descargar PDF"><i class="fa-solid fa-file-pdf"></i></button>
            `;
        } else {
            accionesBtnHtml = `
                <button class="icon-btn btn-edit-evaluation" data-id="${ev.idEvaluacion}" title="Editar Calificación"><i class="fa-solid fa-pen"></i></button>
                <button class="icon-btn btn-pdf-download" data-id="${ev.idEvaluacion}" title="Descargar PDF"><i class="fa-solid fa-file-pdf"></i></button>
                <button class="icon-btn btn-delete-evaluation" data-id="${ev.idEvaluacion}" title="Eliminar Calificación"><i class="fa-solid fa-trash"></i></button>
            `;
        }

        html += `
            <tr data-year="${year}">
                <td>${periodo}</td>
                <td>
                    <strong>${ev.puntaje || 0} / 100</strong>
                    <span style="font-size: 11px; color: var(--text-muted); display: block;">(${estrellas} ⭐)</span>
                </td>
                <td><span class="badge ${badgeClass}">${descCalificacion}</span></td>
                <td>
                    <div class="actions" style="justify-content: center; gap: 12px;">
                        ${accionesBtnHtml}
                    </div>
                </td>
            </tr>
        `;
    });

    historyTbody.innerHTML = html;

    // Agregar eventos a los botones
    historyTbody.querySelectorAll('.btn-edit-evaluation').forEach(btn => {
        btn.addEventListener('click', () => {
            const idEvaluacion = btn.getAttribute('data-id');
            localStorage.setItem('selectedEvaluationId', idEvaluacion);
            const targetPage = allEvaluaciones.length === 1 ? 'buyer_first_evaluation.html' : 'buyer_second_evaluation.html';
            window.location.href = `${targetPage}?idEvaluacion=${idEvaluacion}&id=${providerId}`;
        });
    });

    historyTbody.querySelectorAll('.btn-pdf-download').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = parseInt(btn.getAttribute('data-id'), 10);
            const evaluacion = allEvaluaciones.find(ev => ev.idEvaluacion === id);
            if (evaluacion) {
                descargarReportePDF(evaluacion);
            } else {
                alert("No se encontró la información de la calificación.");
            }
        });
    });

    historyTbody.querySelectorAll('.btn-delete-evaluation').forEach(btn => {
        btn.addEventListener('click', async () => {
            const id = btn.getAttribute('data-id');
            const confirmar = confirm("¿Está seguro de que desea eliminar esta calificación? Esta acción no se puede deshacer y el registro se borrará de la base de datos.");
            if (confirmar) {
                try {
                    const deleteResponse = await fetch(`${CONFIG.API_BASE_URL}/evaluacion_proveedor/${id}`, {
                        method: 'DELETE'
                    });
                    const deleteResult = await deleteResponse.json();
                    if (deleteResponse.ok && deleteResult.data && deleteResult.data.successful) {
                        alert("Calificación eliminada correctamente.");
                        initHistory(); // Recargar el historial
                    } else {
                        alert("No se pudo eliminar la calificación.");
                    }
                } catch (err) {
                    console.error("Error al eliminar la calificación:", err);
                    alert("Error de red al intentar eliminar la calificación.");
                }
            }
        });
    });
}

function filterByYear() {
    const selector = document.getElementById('yearFilter');
    if (!selector) return;
    
    const year = selector.value;
    const rows = document.querySelectorAll('#history-tbody tr');

    rows.forEach(row => {
        const rowYear = row.getAttribute('data-year');
        if (!rowYear) return; // Saltar filas sin data-year (ej: mensaje de error o carga)
        
        if (year === 'all' || rowYear === year) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}

window.filterByYear = filterByYear;

async function descargarReportePDF(evaluacion) {
    try {
        // Obtener datos del proveedor
        const provResp = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${evaluacion.idProveedor}`);
        const provResult = await provResp.json();
        const prov = provResult.data;
        const supplierName = prov.razonSocial ? prov.razonSocial : `${prov.nombres} ${prov.apellidos}`;
        const supplierDoc = `${prov.numeroIdentificacion}${prov.digitoVerificacion ? '-' + prov.digitoVerificacion : ''}`;

        // Cargar imagen de la firma
        const signatureImg = new Image();
        signatureImg.src = '../assets/Firma_de_Juan_C.png';
        await new Promise((resolve) => {
            signatureImg.onload = resolve;
            signatureImg.onerror = resolve;
        });

        const { jsPDF } = window.jspdf;
        const doc = new jsPDF({
            orientation: 'portrait',
            unit: 'mm',
            format: 'a4'
        });

        // Colores y Fuentes
        const primaryColor = [212, 163, 115]; // #D4A373
        const darkColor = [30, 30, 47]; // #1E1E2F

        // Encabezado decorativo
        doc.setFillColor(...darkColor);
        doc.rect(0, 0, 210, 40, 'F');

        doc.setTextColor(255, 255, 255);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(22);
        doc.text("REPORTE DE EVALUACIÓN DE PROVEEDOR", 20, 25);

        // Metadata de la evaluación
        const fechaObj = evaluacion.fechaCreado ? new Date(evaluacion.fechaCreado) : new Date();
        const fechaFormateada = fechaObj.toLocaleDateString('es-ES', {
            year: 'numeric', month: 'long', day: 'numeric'
        });
        const periodo = `I - ${fechaObj.getFullYear()}`;

        let y = 55;

        // Información General
        doc.setTextColor(...darkColor);
        doc.setFontSize(14);
        doc.setFont("helvetica", "bold");
        doc.text("Información General", 20, y);
        y += 8;

        // Tabla/Información alineada
        doc.setFontSize(11);
        doc.setFont("helvetica", "bold");
        doc.text("Proveedor:", 20, y);
        doc.setFont("helvetica", "normal");
        doc.text(supplierName, 55, y);
        y += 6;

        doc.setFont("helvetica", "bold");
        doc.text("Identificación:", 20, y);
        doc.setFont("helvetica", "normal");
        doc.text(supplierDoc, 55, y);
        y += 6;

        doc.setFont("helvetica", "bold");
        doc.text("Periodo Evaluado:", 20, y);
        doc.setFont("helvetica", "normal");
        doc.text(periodo, 55, y);
        y += 6;

        doc.setFont("helvetica", "bold");
        doc.text("Fecha Evaluación:", 20, y);
        doc.setFont("helvetica", "normal");
        doc.text(fechaFormateada, 55, y);
        y += 12;

        // Calificación General
        doc.setFillColor(250, 250, 250);
        doc.rect(20, y, 170, 24, 'F');
        doc.setDrawColor(230, 230, 230);
        doc.rect(20, y, 170, 24, 'S');

        let califTexto = "ALTERNATIVO";
        if (evaluacion.idCalificacion === 1) califTexto = "CONFIABLE";
        else if (evaluacion.idCalificacion === 3) califTexto = "NO CONFIABLE";

        doc.setFont("helvetica", "bold");
        doc.setFontSize(12);
        doc.text("PUNTAJE TOTAL:", 25, y + 10);
        doc.setFontSize(18);
        doc.setTextColor(...primaryColor);
        doc.text(`${evaluacion.puntaje || 0} / 100`, 25, y + 18);

        doc.setTextColor(...darkColor);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(12);
        doc.text("ESTADO:", 110, y + 10);
        doc.setFontSize(16);
        if (evaluacion.idCalificacion === 1) doc.setTextColor(46, 125, 50); // Verde
        else if (evaluacion.idCalificacion === 3) doc.setTextColor(198, 40, 40); // Rojo
        else doc.setTextColor(211, 191, 12); // Amarillo
        doc.text(califTexto, 110, y + 18);

        y += 36;

        // Desglose de Criterios
        doc.setTextColor(...darkColor);
        doc.setFontSize(14);
        doc.setFont("helvetica", "bold");
        doc.text("Desglose de Criterios (Calificación de 1 a 5)", 20, y);
        y += 8;

        const criterios = [
            { nombre: "1. Calidad del producto o servicio", valor: evaluacion.calidad, obs: evaluacion.obsCalidad },
            { nombre: "2. Cumplimiento de tiempos de entrega", valor: evaluacion.tiempo, obs: evaluacion.obsTiempo },
            { nombre: "3. Gestión documental", valor: evaluacion.documenta, obs: evaluacion.obsDocumental },
            { nombre: "4. Prevención SARLAFT", valor: evaluacion.sarlaft, obs: evaluacion.obsSarlaft },
            { nombre: "5. Aspectos comerciales", valor: evaluacion.comercial, obs: evaluacion.obsComercial },
            { nombre: "6. Responsabilidad social", valor: evaluacion.social, obs: evaluacion.obsSocial },
            { nombre: "7. Capacidad de innovación y mejora", valor: evaluacion.mejora, obs: evaluacion.obsMejora }
        ];

        criterios.forEach(crit => {
            if (y > 240) {
                doc.addPage();
                y = 25;
            }

            doc.setFontSize(10);
            doc.setFont("helvetica", "bold");
            doc.setTextColor(...darkColor);
            doc.text(crit.nombre, 20, y);

            doc.setFont("helvetica", "bold");
            doc.setTextColor(...primaryColor);
            doc.text(`${crit.valor || 0} / 5`, 175, y, { align: "right" });

            y += 5;
            if (crit.obs) {
                doc.setFont("helvetica", "italic");
                doc.setTextColor(100, 100, 100);
                const obsLines = doc.splitTextToSize(`Obs: ${crit.obs}`, 165);
                doc.text(obsLines, 22, y);
                y += (obsLines.length * 4.5) + 3;
            } else {
                y += 2;
            }
        });

        y += 6;

        if (evaluacion.observaciones) {
            if (y > 240) {
                doc.addPage();
                y = 25;
            }
            doc.setFont("helvetica", "bold");
            doc.setFontSize(11);
            doc.setTextColor(...darkColor);
            doc.text("Observaciones Generales:", 20, y);
            y += 6;
            doc.setFont("helvetica", "normal");
            doc.setTextColor(50, 50, 50);
            const obsGralLines = doc.splitTextToSize(evaluacion.observaciones, 170);
            doc.text(obsGralLines, 20, y);
            y += (obsGralLines.length * 5) + 8;
        }

        // Pie de página con la Firma
        if (y > 220) {
            doc.addPage();
            y = 25;
        }

        y += 10;
        doc.setFont("helvetica", "normal");
        doc.setFontSize(11);
        doc.setTextColor(...darkColor);
        doc.text("Atentamente,", 20, y);
        y += 5;

        // Dibujar la firma al final del documento
        try {
            if (signatureImg.complete && signatureImg.naturalWidth > 0) {
                doc.addImage(signatureImg, 'PNG', 20, y, 45, 15);
            }
        } catch (e) {
            console.error("Error agregando la firma al PDF:", e);
        }
        y += 20;

        doc.setFont("helvetica", "bold");
        doc.text("Juan Carlos Cadena", 20, y);
        doc.setFont("helvetica", "normal");
        doc.text("Jefe de Compras", 20, y + 5);
        doc.text("Parere GRC", 20, y + 10);
        doc.text("email: jcadena@pareregrc.com", 20, y + 15);

        // Guardar PDF
        doc.save(`calificacion_${supplierName.replace(/\s+/g, '_')}_${periodo.replace(/\s+/g, '')}.pdf`);

    } catch (err) {
        console.error("Error al generar el PDF de calificación:", err);
        alert("Error al intentar generar el PDF.");
    }
}

document.addEventListener('DOMContentLoaded', () => {
    initHistory();
});
