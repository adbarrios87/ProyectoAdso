
// BLOQUE DE CONTROL DE ACCESOS
const userRole = localStorage.getItem('userRole');// Este código verifica qué usuario entró y si tiene una sesión válida.
const currentFile = location.pathname.substring(location.pathname.lastIndexOf("/") + 1).toLowerCase();
// Si no estamos en el login y hay un archivo cargando
if (currentFile !== 'login.html' && currentFile !== '') {
    // Si no ha iniciado sesión (no hay rol guardado), lo enviamos al login
    if (!userRole) {
        window.location.href = '../../login.html?v=1';
    }
}

// BLOQUE DE CONFIGURACION GLOBAL
document.addEventListener('DOMContentLoaded', () => {
    // 1. INYECTOR GLOBAL DEL HEADER MAESTRO
    function inyectarHeaderGlobal() {
        const contentDiv = document.querySelector('.content');
        // Si no hay contenedor content o ya existe el header maestro, no inyectamos
        if (!contentDiv || document.getElementById('master-global-header')) return;

        const headerElement = document.createElement('header');
        headerElement.id = 'master-global-header';

        headerElement.innerHTML = `
            <div></div> <!-- Espacio vacío para mantener el flex-justify-between -->
            
            <div style="display: flex; align-items: center; gap: 20px;">
                <div class="notifications-bell" style="position: relative; cursor: pointer; margin-right: 15px;" onclick="window.location.href='notifications.html'">
                    <i class="fa-solid fa-bell" style="font-size: 1.2rem; color: #4b5563;"></i>
                    <span id="global-notif-badge" class="badge danger hidden" style="position: absolute; top: -8px; right: -12px; font-size: 0.65rem; padding: 2px 6px; border-radius: 50%;">0</span>
                </div>

                <div class="user-identity-container" id="user-profile-trigger">
                <div class="user-info">
                    <span class="user-name" id="header-user-name">Cargando...</span>
                    <span class="user-role" id="header-user-role">
                        <i class="fa-solid fa-user-shield"></i> <span id="header-role-text">Cargando...</span>
                    </span>
                </div>
                <div class="user-image-container">
                    <img src="" alt="Foto de usuario" class="user-img hidden" id="header-user-img" />
                    <div class="user-initials hidden" id="header-initials">
                        <i class="fa-solid fa-user-gear"></i>
                    </div>
                </div>
                <div class="user-dropdown hidden" id="user-dropdown">
                    <div class="dropdown-header">Mi Cuenta</div>
                    <a href="user_profile.html" class="dropdown-item"><i class="fa-solid fa-user-gear"></i> Configurar cuenta</a>
                    <div class="dropdown-divider"></div>
                    <button class="dropdown-item logout-btn" id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Cerrar Sesión</button>
                </div>
                </div> <!-- Cierra el user-identity-container -->
            </div> <!-- Cierra el contenedor flex -->
        `;
        // Inyectamos al principio del contenedor .content
        contentDiv.insertBefore(headerElement, contentDiv.firstChild);
        activarEventosHeader();
    }

    // 2. ACTIVADOR DE EVENTOS
    function activarEventosHeader() {
        const trigger = document.getElementById('user-profile-trigger');
        const dropdown = document.getElementById('user-dropdown');
        const btnLogout = document.getElementById('logout-btn');

        if (trigger && dropdown) {
            trigger.addEventListener('click', (e) => {
                e.stopPropagation();
                dropdown.classList.toggle('hidden');
            });
            document.addEventListener('click', (e) => {
                if (!trigger.contains(e.target)) dropdown.classList.add('hidden');
            });
        }
        if (btnLogout) {
            btnLogout.addEventListener('click', (e) => {
                e.preventDefault();
                if (confirm('¿Cerrar sesión?')) {
                    localStorage.clear();
                    window.location.href = '../../login.html?v=1';
                }
            });
        }
    }


    //  Configuración Header dinámico
    function actualizarInterfazUsuario() {
        const storedRole = localStorage.getItem('userRole');
        const storedName = localStorage.getItem('userName');
        const storedPhoto = localStorage.getItem('userPhoto');

        const roleSpan = document.getElementById('header-user-role') || document.querySelector('.user-role');
        const nameSpan = document.getElementById('header-user-name') || document.querySelector('.user-name');
        const imgHeader = document.getElementById('header-user-img') || document.querySelector('.user-img');
        const initialsHeader = document.getElementById('header-initials');

        if (nameSpan && storedName) {
            nameSpan.textContent = storedName.toUpperCase();
        }

        if (roleSpan && storedRole) {
            let roleIcon = '<i class="fa-solid fa-user"></i>';
            let formattedRole = storedRole.charAt(0).toUpperCase() + storedRole.slice(1);

            if (storedRole === 'admin' || storedRole === 'administrador') {
                roleIcon = '<i class="fa-solid fa-user-gear"></i>';
                formattedRole = 'Administrador';
            } else if (storedRole === 'proveedor') {
                roleIcon = '<i class="fa-solid fa-truck"></i>';
            } else if (storedRole === 'comprador') {
                roleIcon = '<i class="fa-solid fa-user-tie"></i>';
            } else if (storedRole === 'analista') {
                roleIcon = '<i class="fa-solid fa-magnifying-glass-chart"></i>';
            } else if (storedRole === 'oficial') {
                roleIcon = '<i class="fa-solid fa-user-check"></i>';
            }

            roleSpan.innerHTML = `${roleIcon} ${formattedRole}`;
        }
    }

    // CONFIGURACION IDENTIDAD GLOBAL 
    async function configurarIdentidad() {
        const userId = localStorage.getItem('userId');
        console.log("DEBUG: Configurando identidad para userId:", userId);

        if (!userId) {
            console.warn("ADVERTENCIA: No hay userId en localStorage.");
            return;
        }
        actualizarInterfazUsuario();
        //  Sincronización con Base de Datos (en segundo plano)
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/${userId}`);
            const result = await response.json();
            console.log("DEBUG: Datos de identidad recibidos:", result);

            if (result.data) {
                const user = result.data;
                // Actualizamos la sesión con datos frescos
                localStorage.setItem('userName', user.nombreUsuario);
                localStorage.setItem('userPhoto', user.fotoUrl || '');
                // Re-renderizamos con los nuevos datos
                actualizarInterfazUsuario();
            }
        } catch (e) { console.error("Error sincronizando identidad:", e); }
    }



    // MENU DINAMICO  LATERAL
    async function cargarMenuDinamico() {
        const idRol = localStorage.getItem('idRol');
        const sidebarMenu = document.querySelector('.sidebar .menu');

        console.log("DEBUG: Cargando menú para idRol:", idRol); // Diagnóstico

        if (!sidebarMenu) {
            console.error("ERROR: No se encontró el elemento .sidebar .menu");
            return;
        }

        if (!idRol) {
            console.warn("ADVERTENCIA: No hay idRol en localStorage. Redirigiendo al login...");
            // Opcional: window.location.href = "../../login.html";
            return;
        }

        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/usuarios/menu/${idRol}`);
            if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

            const result = await response.json();
            console.log("DEBUG: Respuesta del servidor para el menú:", result); // Diagnóstico

            if (result.data && result.data.length > 0) {
                const logoLink = document.getElementById('logo-link');
                const roleString = localStorage.getItem('userRole');
                
                let dashboardUrl = "index.html";
                if (roleString === 'proveedor') dashboardUrl = 'supplier_dashboard.html';
                else if (roleString === 'comprador') dashboardUrl = 'buyer_dashboard.html';
                else if (roleString === 'admin' || roleString === 'administrador') dashboardUrl = 'admin_dashboard.html';
                else if (roleString === 'analista') dashboardUrl = 'risk_list.html';
                else if (roleString === 'oficial') dashboardUrl = 'compliance_officer_dashboard.html';

                if (logoLink) logoLink.href = dashboardUrl;

                let html = "";
                const hasInicio = result.data.some(item => item.titulo.toLowerCase() === "inicio");
                if (!hasInicio) {
                    html += `<li><i class="fa-solid fa-house"></i><a href="${dashboardUrl}">Inicio</a></li>`;
                }

                result.data.forEach(item => {
                    if (item.titulo.toLowerCase() === "inicio" && logoLink) {
                        logoLink.href = item.url;
                    }

                    if (item.submenus && item.submenus.length > 0) {
                        let subHtml = `<li class="dropdown"><i class="fa-solid ${item.icono || 'fa-folder'}"></i><span>${item.titulo}</span><ul class="submenu">`;
                        item.submenus.forEach(sub => {
                            subHtml += `<li><a href="${sub.url}">${sub.titulo}</a></li>`;
                        });
                        subHtml += `</ul></li>`;
                        html += subHtml;
                    } else {
                        html += `<li><i class="fa-solid ${item.icono || 'fa-circle'}"></i><a href="${item.url}">${item.titulo}</a></li>`;
                    }
                });
                sidebarMenu.innerHTML = html;

                // Re-inicializar eventos de dropdown después de cargar el menú
                inicializarEventosMenu();
                resaltarEnlaceActivo();
                verificarEstadoProveedorMenu();
            }
        } catch (error) {
            console.error("Error cargando menú dinámico:", error);
        }
    }

    function verificarEstadoProveedorMenu() {
        const roleString = localStorage.getItem('userRole');
        const userId = localStorage.getItem('userId');
        if (roleString && roleString.toLowerCase() === 'proveedor' && userId) {
            fetch(`${CONFIG.API_BASE_URL}/proveedores/by-userid?userId=${userId}`)
                .then(r => r.json())
                .then(res => {
                    if (res.data && res.data.idEstadoProveedor === 5) {
                        window.inhabilitarBotonActualizarInformacion();
                    }
                })
                .catch(err => console.error("Error al verificar estado de proveedor:", err));
        }
    }

    window.inhabilitarBotonActualizarInformacion = function() {
        // 1. Elementos del menú lateral (sidebar)
        const menuItems = document.querySelectorAll('.sidebar .menu li');
        menuItems.forEach(li => {
            const link = li.querySelector('a');
            const text = (li.textContent || '').toLowerCase();
            const href = link ? (link.getAttribute('href') || '').toLowerCase() : '';
            if (href.includes('supplier_form.html') || text.includes('actualizar información') || text.includes('editar perfil')) {
                li.classList.add('disabled-menu-item');
                li.setAttribute('title', 'El expediente se encuentra en revisión. La actualización de información está inhabilitada.');
                if (link) {
                    link.setAttribute('title', 'El expediente se encuentra en revisión. La actualización de información está inhabilitada.');
                    link.addEventListener('click', (e) => {
                        e.preventDefault();
                        e.stopPropagation();
                    });
                }
            }
        });

        // 2. Botones de acción rápida en el dashboard
        const quickBtns = document.querySelectorAll('.quick-actions a[href*="supplier_form.html"]');
        quickBtns.forEach(btn => {
            btn.classList.add('disabled-link');
            btn.setAttribute('title', 'El expediente se encuentra en revisión.');
            btn.addEventListener('click', (e) => {
                e.preventDefault();
                e.stopPropagation();
            });
        });
    };


    function inicializarEventosMenu() {
        const dropdowns = document.querySelectorAll('.menu li.dropdown');
        dropdowns.forEach(dropdown => {
            dropdown.addEventListener('click', function (e) {
                if (e.target.tagName !== 'A') {
                    this.classList.toggle('active');
                }
            });
        });
    }

    function resaltarEnlaceActivo() {
        const currentLocation = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
        if (currentLocation) {
            const menuLinks = document.querySelectorAll('.sidebar a');
            menuLinks.forEach(link => {
                if (link.getAttribute('href') === currentLocation) {
                    link.parentElement.classList.add('active');
                    const parentDropdown = link.closest('li.dropdown');
                    if (parentDropdown) parentDropdown.classList.add('active');
                }
            });
        }
    }

    window.actualizarCampanaGlobal = async function() {
        const userId = localStorage.getItem('userId');
        if (!userId) return;
        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/notificaciones/usuario/${userId}/conteo`);
            if (response.ok) {
                const result = await response.json();
                const badge = document.getElementById('global-notif-badge');
                if (badge) {
                    if (result.data > 0) {
                        badge.textContent = result.data > 99 ? '99+' : result.data;
                        badge.classList.remove('hidden');
                    } else {
                        badge.classList.add('hidden');
                    }
                }
            }
        } catch (e) {
            console.error("Error cargando notificaciones:", e);
        }
    };

    // LLAMADAS FINALES (Encendido del sistema)
    inyectarHeaderGlobal();
    configurarIdentidad();
    actualizarCampanaGlobal();
    setInterval(actualizarCampanaGlobal, 60000); // Polling cada 60s
    cargarMenuDinamico();

    // MENU INFERIOR (Salida y Ayuda)
    const bottomMenu = document.querySelector('.sidebar .bottom-menu');
    if (bottomMenu) {
        bottomMenu.innerHTML = `
            <li><i class="fa-solid fa-right-from-bracket"></i><a href="../../login.html?v=1">Salida</a></li>
            <li><i class="fa-solid fa-circle-question"></i><a href="help.html">Ayuda</a></li>
        `;
    }

    // --- SISTEMA DE CACHE PARA CATÁLOGOS ---
    const catalogCache = {};

    // Función interna privada para llenar un select con datos
    function llenarOpcionesSelect(selectId, data, propertyValue, propertyText) {
        const selectElement = document.getElementById(selectId);
        if (!selectElement) return;

        const firstOption = selectElement.options[0];
        selectElement.innerHTML = '';
        if (firstOption) {
            // Si la primera opción era "Cargando...", la cambiamos a algo más apropiado
            if (firstOption.textContent.toLowerCase().includes("cargando")) {
                firstOption.textContent = "Seleccione una opción...";
            }
            selectElement.appendChild(firstOption);
        }

        // Ordenar alfabéticamente por el texto que se muestra
        const sortedData = [...data].sort((a, b) => {
            const textA = (a[propertyText] || "").toString().toLowerCase();
            const textB = (b[propertyText] || "").toString().toLowerCase();
            return textA.localeCompare(textB, 'es', { sensitivity: 'base' });
        });

        sortedData.forEach(item => {
            const option = document.createElement("option");
            option.value = item[propertyValue];
            option.textContent = item[propertyText];
            selectElement.appendChild(option);
        });
    }

    /**
     * FUNCIÓN MAESTRA DE CATÁLOGOS
     * Sirve para cualquier tabla: pais, departamento, municipio, forma_pago, etc.
     */
    window.cargarCatalogo = async function (entidad, selectId, propValue, propText, filter = null) {
        let url = `${CONFIG.API_BASE_URL}/${entidad}`;
        if (filter) {
            // Manejo de filtros (ej: municipios?idDepartamento=1)
            const queryParam = Object.keys(filter)[0];
            url += `?${queryParam}=${filter[queryParam]}`;
        }

        // El cache usa la URL completa como llave
        if (catalogCache[url]) {
            llenarOpcionesSelect(selectId, catalogCache[url], propValue, propText);
            return;
        }

        try {
            const response = await fetch(url);
            const result = await response.json();
            if (result.data) {
                catalogCache[url] = result.data;
                llenarOpcionesSelect(selectId, result.data, propValue, propText);
            }
        } catch (error) {
            console.error(`Error cargando catálogo ${entidad} para ${selectId}:`, error);
        }
    }

    // --- ATAJOS (WRAPPERS) PARA TABLAS COMUNES ---
    window.cargarTiposIdentificacion = (id) => cargarCatalogo('tipo_identificacion', id, 'idTipoIdentificacion', 'descripcion');
    window.cargarRoles = (id) => cargarCatalogo('roles', id, 'idRol', 'rol');
    window.cargarTiposPersona = (id) => cargarCatalogo('tipo_persona', id, 'idTipoPersona', 'descripcion');
    window.cargarPaises = (id) => cargarCatalogo('pais', id, 'idPais', 'nombre');
    window.cargarDepartamentos = (id, idPais = null) => {
        const filter = idPais ? { idPais: idPais } : null;
        cargarCatalogo('departamento', id, 'idDepartamento', 'nombre', filter);
    };
    window.cargarFormasPago = (id) => cargarCatalogo('forma_de_pago', id, 'idFormaPago', 'descripcion');
    window.cargarEstadosProveedor = (id) => cargarCatalogo('estado_proveedor', id, 'idEstadoProveedor', 'descripcion');
    window.cargarTiposPago = (id) => cargarCatalogo('tipo_pago', id, 'idTipoPago', 'descripcion');
    window.cargarTiposTelefono = (id) => cargarCatalogo('tipo_telefono', id, 'idTipoTelefono', 'descripcion');
    window.cargarMunicipios = (idSelect, idDepto) => {
        cargarCatalogo('municipio', idSelect, 'idMunicipio', 'nombre', { idDepartamento: idDepto });
    };
});