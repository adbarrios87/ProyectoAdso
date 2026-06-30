document.addEventListener('DOMContentLoaded', async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');

    const loadingState = document.getElementById('loading-state');
    const errorState = document.getElementById('error-state');
    const signingState = document.getElementById('signing-state');
    const successState = document.getElementById('success-state');

    if (!token) {
        showError('Token ausente', 'No se ha provisto un token de seguridad para la firma del formulario.');
        return;
    }

    let providerId = null;

    // --- 1. Validar Token y Cargar Datos ---
    try {
        const validateRes = await fetch(`${CONFIG.API_BASE_URL}/firmas/validar?token=${token}`);
        if (!validateRes.ok) {
            const errData = await validateRes.json();
            throw new Error(errData.message || 'Token inválido o expirado');
        }
        const validateData = await validateRes.json();
        
        if (!validateData.data || validateData.data.utilizado) {
            throw new Error('El enlace de firma ya ha sido utilizado.');
        }

        providerId = validateData.data.idProveedor;

        // Cargar detalles del proveedor
        const detailsRes = await fetch(`${CONFIG.API_BASE_URL}/proveedores/${providerId}/detalle-completo`);
        if (!detailsRes.ok) throw new Error('Error al recuperar detalles del proveedor.');
        const detailsData = await detailsRes.json();
        
        if (detailsData.data) {
            const details = detailsData.data;
            const prov = details.proveedor;
            const rep = details.representanteLegal;

            document.getElementById('company-name').textContent = prov.razonSocial || `${prov.nombres || ''} ${prov.apellidos || ''}`.trim() || 'Sin Nombre';
            document.getElementById('company-nit').textContent = prov.numeroIdentificacion || 'Sin NIT';
            
            if (rep) {
                document.getElementById('rep-name').textContent = `${rep.nombres || ''} ${rep.apellidos || ''}`.trim() || 'Sin Nombre';
                document.getElementById('rep-id').textContent = rep.numeroIdentificacion || 'Sin ID';
            } else {
                document.getElementById('rep-name').textContent = 'No registrado';
                document.getElementById('rep-id').textContent = 'No registrado';
            }

            // Mostrar el formulario
            loadingState.style.display = 'none';
            signingState.style.display = 'block';
            
            // Inicializar firma
            initSignaturePad();
        } else {
            throw new Error('No se encontraron los datos del proveedor.');
        }

    } catch (e) {
        showError('Enlace inválido', e.message);
    }

    function showError(title, desc) {
        loadingState.style.display = 'none';
        signingState.style.display = 'none';
        successState.style.display = 'none';
        
        errorState.style.display = 'block';
        document.getElementById('error-title').textContent = title;
        document.getElementById('error-desc').textContent = desc;
    }

    // --- 2. Lógica del Canvas de Firma ---
    function initSignaturePad() {
        const canvas = document.getElementById('signature-canvas');
        const ctx = canvas.getContext('2d');
        const btnClear = document.getElementById('btn-clear');
        const btnSubmit = document.getElementById('btn-submit');
        const chkAccept = document.getElementById('chk-accept');

        // Ajustar tamaño real del canvas para evitar distorsión
        function resizeCanvas() {
            const rect = canvas.getBoundingClientRect();
            canvas.width = rect.width;
            canvas.height = rect.height;
            // Configurar estilos de pincel
            ctx.strokeStyle = '#000000';
            ctx.lineWidth = 2.5;
            ctx.lineCap = 'round';
            ctx.lineJoin = 'round';
        }
        resizeCanvas();
        window.addEventListener('resize', resizeCanvas);

        let drawing = false;
        let hasDrawn = false;

        function getMousePos(e) {
            const rect = canvas.getBoundingClientRect();
            const clientX = e.touches ? e.touches[0].clientX : e.clientX;
            const clientY = e.touches ? e.touches[0].clientY : e.clientY;
            return {
                x: clientX - rect.left,
                y: clientY - rect.top
            };
        }

        function startDrawing(e) {
            e.preventDefault();
            drawing = true;
            const pos = getMousePos(e);
            ctx.beginPath();
            ctx.moveTo(pos.x, pos.y);
        }

        function draw(e) {
            if (!drawing) return;
            e.preventDefault();
            const pos = getMousePos(e);
            ctx.lineTo(pos.x, pos.y);
            ctx.stroke();
            hasDrawn = true;
            validateForm();
        }

        function stopDrawing(e) {
            if (drawing) {
                ctx.closePath();
                drawing = false;
            }
        }

        // Mouse events
        canvas.addEventListener('mousedown', startDrawing);
        canvas.addEventListener('mousemove', draw);
        canvas.addEventListener('mouseup', stopDrawing);
        canvas.addEventListener('mouseleave', stopDrawing);

        // Touch events for mobile/tablet
        canvas.addEventListener('touchstart', startDrawing);
        canvas.addEventListener('touchmove', draw);
        canvas.addEventListener('touchend', stopDrawing);

        // Limpiar canvas
        btnClear.addEventListener('click', () => {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            hasDrawn = false;
            validateForm();
        });

        // Habilitar/deshabilitar botón firmar
        function validateForm() {
            const accepted = chkAccept.checked;
            btnSubmit.disabled = !(accepted && hasDrawn);
        }

        chkAccept.addEventListener('change', validateForm);

        // Enviar firma
        btnSubmit.addEventListener('click', async () => {
            btnSubmit.disabled = true;
            btnSubmit.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin"></i> Guardando...';

            try {
                const res = await fetch(`${CONFIG.API_BASE_URL}/firmas/firmar?token=${token}`, {
                    method: 'POST'
                });

                if (res.ok) {
                    signingState.style.display = 'none';
                    successState.style.display = 'block';
                } else {
                    const errData = await res.json();
                    throw new Error(errData.message || 'Error al firmar el formulario.');
                }
            } catch (err) {
                alert(err.message);
                btnSubmit.disabled = false;
                btnSubmit.innerHTML = '<i class="fa-solid fa-file-signature"></i> Firmar y Enviar';
            }
        });
    }
});
