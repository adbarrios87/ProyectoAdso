# Plan de Integración: Endpoints del Backend ↔ Frontend GUI

> **Proyecto:** Parere GRC — Validación Ética de Proveedores  
> **Fecha:** 09 de Mayo de 2026  
> **Autores:** Juan Cadena, Adriana Barrios

---

## 1. Consolidado de Endpoints del Backend

El Backend cuenta con **31 controllers** que exponen un total de **157 endpoints** (CRUD estándar + endpoints especiales). A continuación se presenta el inventario completo.

### 1.1 Endpoints de Autenticación y Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/usuarios` | Crear usuario (Incluye inicialización de notificaciones) |
| `GET` | `/usuarios` | Listar todos los usuarios |
| `GET` | `/usuarios/{id}` | Obtener detalle (Incluye preferencias de notif) |
| `PUT` | `/usuarios/{id}` | Actualizar usuario (Validación de pass actual + notif) |
| `DELETE` | `/usuarios/{id}` | Eliminar un usuario |
| `PATCH` | `/usuarios/{id}/estado` | Activar/Desactivar usuario |
| `POST` | `/usuarios/login` | Iniciar sesión (autenticación vía Correo Electrónico) |
| `PATCH` | `/usuarios/{id}/foto` | Actualizar foto de perfil (Base64) |

### 1.2 Endpoints de Proveedor (Singular)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/proveedor` | Crear proveedor |
| `GET` | `/proveedor` | Listar todos los proveedores |
| `GET` | `/proveedor/{id}` | Obtener detalle de un proveedor |
| `PUT` | `/proveedor/{id}` | Actualizar proveedor |
| `DELETE` | `/proveedor/{id}` | Eliminar proveedor |
| `POST` | `/proveedor/registro-completo` | Registro completo de proveedor (datos + contactos + socios) |

### 1.3 Endpoints de Catálogos Maestros

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| CRUD | `/roles` | Gestión de roles del sistema |
| CRUD | `/tipo_identificacion` | Tipos de identificación (CC, NIT, etc.) |
| CRUD | `/tipo_persona` | Tipos de persona (Natural, Jurídica) |
| CRUD | `/tipo_documento` | Tipos de documentos del proveedor |
| CRUD | `/tipo_notificacion` | Tipos de notificaciones |
| CRUD | `/tipo_pago` | Tipos de pago |
| CRUD | `/tipo_telefono` | Tipos de teléfono |
| CRUD | `/forma_de_pago` | Formas de pago (Efectivo, Crédito, etc.) |
| CRUD | `/estado_proveedor` | Estados del proveedor |
| CRUD | `/estado_usuario` | Estados del usuario |
| CRUD | `/origen_dato` | Orígenes de datos |

> **Nota:** CRUD = `POST`, `GET`, `GET/{id}`, `PUT/{id}`, `DELETE/{id}` (5 endpoints por tabla).

### 1.4 Endpoints de Ubicación Geográfica

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| CRUD | `/pais` | Gestión de países |
| CRUD | `/departamento` | Gestión de departamentos |
| CRUD | `/municipio` | Gestión de municipios |
| CRUD | `/ubicacion` | Ubicaciones de proveedores |

### 1.5 Endpoints de Contactos y Representantes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| CRUD | `/contacto` | Personas de contacto |
| CRUD | `/proveedor_contacto` | Relación proveedor ↔ contacto |
| CRUD | `/representante_legal` | Representantes legales |
| CRUD | `/representante_proveedor` | Relación proveedor ↔ representante |
| CRUD | `/socios_proveedor` | Socios del proveedor |
| CRUD | `/documentos_socios_proveedor` | Documentos de socios |

### 1.6 Endpoints de Evaluación y Riesgo

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| CRUD | `/evaluacion_proveedor` | Evaluaciones de proveedores |
| CRUD | `/evaluacion_riesgos` | Evaluaciones de riesgo |
| CRUD | `/calificacion` | Calificaciones |
| CRUD | `/validacion` | Validaciones |
| CRUD | `/campo_validacion` | Campos de validación |

### 1.7 Endpoints de Documentos y Notificaciones

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| CRUD | `/documentos` | Gestión de documentos |
| CRUD | `/notificaciones` | Gestión de notificaciones |
| CRUD | `/historial_usuario` | Historial de acciones del usuario |

---

## 2. Checklist de Implementación Frontend ↔ Backend

### Leyenda
- ✅ = Implementado y conectado al Backend
- 🔶 = Parcialmente implementado (funcionalidad simulada en el Frontend sin conexión real)
- ❌ = No implementado (pendiente de conexión)

### 2.1 Módulo de Autenticación

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| ✅ | `POST /usuarios/login` | `login.html` | `src/js/login.js` | Conectado. Usa Email para autenticación. |
| ✅ | `POST /usuarios` | `user_new.html` | `user_new.js` | **Premium UI**. Crea registro automático en tabla `proveedor` si el rol es Proveedor. |
| ✅ | `PATCH /usuarios/{id}/foto` | `user_profile.html` | `user_profile.js` | **Premium UI**. Conectado y estable (Usa `LONGTEXT` en BD) |
| ✅ | `GET /usuarios` | `user_list.html` | `user_list.js` | **Premium UI**. Tabla dinámica con búsqueda instantánea y márgenes ajustados |
| ✅ | `PUT /usuarios/{id}` | `user_list.html` | `user_list.js` | **Premium UI (Modal)**. Persistencia total con **Validación de Pass en Tiempo Real** |
| ✅ | `DELETE /usuarios/{id}` | `user_list.html` | `user_list.js` | **Premium UI**. Eliminación persistente con botones globales |
| ✅ | `PATCH /usuarios/{id}/estado` | `user_list.html` | `user_list.js` | **Premium UI**. Cambio de estado dinámico con iconos unificados |
| ✅ | `GET /usuarios/{id}` | `user_prof### 2.2 Módulo de Proveedores

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| ✅ | `POST /proveedores/registro-completo` | `supplier_form.html` | `supplier_form.js` | Conectado. Soporta Due Diligence LAFT, Financiera, Socios y Suplentes dinámicos. |
| 🔶 | `GET /proveedores` | `buyer_supplier_list.html` | `buyer_supplier_list.js` | Tabla con datos estáticos |
| 🔶 | `GET /proveedores/{id}` | `buyer_supplier_profile.html` | `buyer_supplier_profile.js` | Perfil con datos estáticos |
| ❌ | `PUT /proveedores/{id}` | — | — | No hay formulario de edición de proveedor |
| ❌ | `DELETE /proveedores/{id}` | — | — | Sin funcionalidad de eliminación |

### 2.3 Módulo de Evaluación y Riesgo

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| 🔶 | `POST /evaluacion_proveedor` | `buyer_first_evaluation.html` | `buyer_first_evaluation.js` | Formulario simulado, no envía al Backend |
| 🔶 | `POST /evaluacion_riesgos` | `risk_review.html` | `risk_review.js` | Evaluación simulada con `localStorage` |
| 🔶 | `GET /evaluacion_riesgos` | `risk_historial.html` | `risk_historial.js` | Historial con datos de `localStorage` |
| 🔶 | `POST /calificacion` | `buyer_second_evaluation.html` | — | Calificación simulada |
| 🔶 | `POST /validacion` | `buyer_supplier_validation.html` | `buyer_supplier_validation.js` | Validación simulada |
| ❌ | CRUD `/campo_validacion` | — | — | Sin interfaz |

### 2.4 Módulo de Cumplimiento

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| 🔶 | — | `compliance_officer_dashboard.html` | `compliance_officer_dashboard.js` | Dashboard con datos estáticos |
| 🔶 | — | `compliance_officer_review.html` | `compliance_officer_review.js` | Revisión simulada con `localStorage` |
| 🔶 | — | `compliance_officer_history.html` | `compliance_officer_history.js` | Historial simulado con `localStorage` |

### 2.5 Catálogos Maestros (Carga Dinámica en Formularios)

| Estado | Endpoint | Usado en | Notas |
|--------|----------|----------|-------|
| ✅ | `GET /tipo_identificacion` | `user_new.html`, `supplier_form.html` | Carga dinámica vía `main.js` → `cargarTiposIdentificacion()` |
| ✅ | `GET /roles` | `user_new.html` | Carga dinámica vía `main.js` → `cargarRoles()` |
| ✅ | `GET /tipo_persona` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarTiposPersona()` |
| ✅ | `GET /tipo_documento` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarTiposIdentificacion()` (Empresa, Contactos, Suplentes, Socios) |
| ✅ | `GET /departamento` | `supplier_form.html` | Carga dinámica dependiente del País |
| ✅ | `GET /municipio` | `supplier_form.html` | Carga dinámica dependiente del Departamento |
| ✅ | `GET /forma_de_pago` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarFormasPago()` |
| ✅ | `GET /tipo_telefono` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarTiposTelefono()` |
| ✅ | `GET /tipo_pago` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarTiposPago()` |
| ✅ | `GET /pais` | `supplier_form.html` | Carga dinámica vía `main.js` → `cargarPaises()` |
| ❌ | `GET /estado_proveedor` | — | Sin uso en el frontend |
| ❌ | `GET /estado_usuario` | — | Sin uso en el frontend |
| ❌ | `GET /origen_dato` | — | Sin uso en el frontend |
| ❌ | `GET /tipo_notificacion` | — | Sin uso en el frontend |

### 2.6 Módulo de Documentos

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| 🔶 | `POST /documentos` | `supplier_upload_documents.html` | `supplier_upload_documents.js` | Carga simulada, no conectada al Backend |
| ❌ | `GET /documentos` | — | — | Sin listado de documentos desde la BD |

### 2.7 Módulo de Notificaciones

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| ✅ | `GET /usuarios/{id}` | `user_profile.html` | `user_profile.js` | Integrado en la tabla Usuarios (Campos `notif_*`) |
| ✅ | `PUT /usuarios/{id}` | `user_profile.html` | `user_profile.js` | Guardado directo en el objeto Usuario |

### 2.8 Otros Módulos

| Estado | Endpoint | Archivo Frontend (HTML) | Archivo Frontend (JS) | Notas |
|--------|----------|------------------------|----------------------|-------|
| 🔶 | — | `admin_dashboard.html` | — | KPIs con datos estáticos |
| 🔶 | — | `buyer_dashboard.html` | `buyer_dashboard.js` | Dashboard simulado |
| 🔶 | — | `supplier_dashboard.html` | `supplier_dashboard.js` | Dashboard simulado |
| 🔶 | — | `risk_dashboard.html` | — | Dashboard simulado |
| 🔶 | — | `supplier_certification.html` | `supplier_certification.js` | Certificaciones simuladas |
| 🔶 | — | `supplier_qualification_history.html` | `supplier_qualification_history.js` | Historial simulado |
| 🔶 | — | `buyer_reports.html` | — | Reportes con datos estáticos |
| 🔶 | — | `admin_audit_logs.html` | — | Logs estáticos |
| 🔶 | — | `approval_history.html` | — | Historial de aprobaciones estático |
| 🔶 | — | `password_recovery.html` | `password_recovery.js` | Recuperación simulada |
| ❌ | CRUD `/historial_usuario` | `admin_audit_logs.html` | — | Sin conexión al Backend |

---

## 3. Resumen Estadístico

| Categoría | Cantidad |
|-----------|----------|
| **Total de endpoints en Backend** | 158 |
| **Endpoints conectados al Frontend** ✅ | 20 (12.6%) |
| **Funcionalidades simuladas sin Backend** 🔶 | ~19 pantallas |
| **Endpoints sin interfaz** ❌ | ~115 endpoints |

---

## 4. Oportunidades de Mejora

### 4.1 🔴 Crítico: URLs de `localhost` Hardcodeadas

Los siguientes archivos aún tienen `http://localhost:8080` escrito directamente en el código. Deben migrar a `${CONFIG.API_BASE_URL}`:

| `src/js/supplier_form.js` | 151 | ✅ Migrado a `${CONFIG.API_BASE_URL}` |

### 4.2 🔴 Crítico: Datos Estáticos en HTML (Deben ser Dinámicos)

Las siguientes páginas muestran datos directamente en el HTML que deberían cargarse desde la base de datos:

| Página | Problema | Endpoint a consumir |
|--------|----------|---------------------|
| `user_list.html` | ✅ Tabla dinámica conectada | `GET /usuarios` |
| `buyer_supplier_list.html` | Lista de proveedores estática | `GET /proveedores` |
| `buyer_supplier_profile.html` | Perfil estático | `GET /proveedores/{id}` |
| `admin_dashboard.html` | KPIs inventados | `GET /usuarios` + `GET /proveedores` |
| `admin_audit_logs.html` | Logs estáticos | `GET /historial_usuario` |

### 4.3 🟡 Importante: Formulario de Proveedores (`supplier_form.html`)

Este formulario es el más grande del sistema y tiene **7 selectores** que ahora son dinámicos:

| Selector | Función en `main.js` | Endpoint | Estado |
|----------|----------------------|----------|--------|
| Tipo de persona | `cargarTiposPersona()` | `GET /tipo_persona` | ✅ Conectado |
| Tipo de documento | `cargarTiposIdentificacion()` | `GET /tipo_documento` | ✅ Conectado |
| Departamento | `cargarDepartamentos()` | `GET /departamento` | ✅ Conectado |
| Municipio | `cargarMunicipios(idDepto)` | `GET /municipio` | ✅ Conectado |
| Forma de pago | `cargarFormasPago()` | `GET /forma_de_pago` | ✅ Conectado |
| Tipo teléfono | `cargarTiposTelefono()` | `GET /tipo_telefono` | ✅ Conectado |
| Tipo pago | `cargarTiposPago()` | `GET /tipo_pago` | ✅ Conectado |

### 4.4 🟡 Importante: Operaciones CRUD sin Backend

Las siguientes acciones del usuario solo modifican la interfaz visual (DOM) pero **no persisten en la base de datos**:

| Acción del usuario | Página | Lo que debería hacer |
|--------------------|--------|---------------------|
| Eliminar usuario (botón 🗑️) | `user_list.html` | ✅ Conectado a `DELETE /usuarios/{id}` |
| Editar usuario (botón ✏️) | `user_list.html` | ✅ Conectado a `PUT /usuarios/{id}` |
| Activar/Desactivar usuario | `user_list.html` | ✅ Conectado a `PATCH /usuarios/{id}/estado` |
| Aprobar proveedor | `buyer_supplier_approve.html` | Llamar a `PUT /proveedores/{id}` |
| Evaluar riesgo | `risk_review.html` | Llamar a `POST /evaluacion_riesgos` |
| Decisión de cumplimiento | `compliance_officer_review.html` | Crear endpoint o usar `POST /validacion` |

### 4.5 🟢 Mejora: Arquitectura del `login.html`

El bloque `<script>` del login debería extraerse a un archivo `login.js` dedicado para mantener la coherencia con el patrón usado en el resto del sistema (un `.js` por cada `.html`).

### 4.6 🟢 Mejora: Agregar `config.js` a Todos los HTML

Verificar que **todos** los archivos HTML del proyecto incluyan `config.js` **antes** de `main.js` en el orden correcto:

```html
<script src="../js/config.js"></script>
<script src="../js/main.js"></script>
<script src="../js/[pagina_especifica].js"></script>
```

### 4.7 🟢 Mejora: Campos de Auditoría Consistentes

Asegurar que **todas** las operaciones de escritura (POST, PUT, DELETE) incluyan los campos de auditoría:
- ✅ **Implementado en Usuarios**: `creadoPor`, `modificadoPor` y `estado_usuario` ya son consistentes.
- [ ] Pendiente en el resto de entidades (Proveedores, Documentos, etc.).

Esto debe implementarse tanto en el Frontend (enviar el `userId` de la sesión) como en el Backend (asignar la fecha con `LocalDateTime.now()`).

---

### 4.8 🟢 Mejora: Validación de Seguridad y UX
- ✅ **Checklist de Contraseña**: Implementada en tiempo real (Iconos ✅/❌) en perfil y creación.
- ✅ **Política de Complejidad**: 8 caracteres, Mayúscula, Número y Símbolo obligatorios.
- ✅ **Ajuste de Campos**: Sustitución de "Teléfono" por "Cargo" en el perfil (con lectura de BD).

---

### 4.9 🟡 Mejora: Refactorización de CSS (`template.css`)

El archivo `src/css/template.css` presenta una alta cantidad de código duplicado y selectores redundantes (ej: botones de submit/reset, estilos de avatares, selectores de tablas). Se recomienda:
- Crear clases utilitarias (`.btn-primary`, `.avatar-circle`, etc.) para centralizar estilos comunes.
- Limpiar selectores antiguos que ya no se usan tras la implementación del header global.
- Organizar el archivo por módulos (Layout, Componentes, Vistas).

---

## 5. Orden de Prioridad Recomendado

### Fase 1: Estabilización (Inmediato)
1. [x] Reemplazar `localhost:8080` en `user_profile.js` por `${CONFIG.API_BASE_URL}`
2. [x] Reemplazar `localhost:8080` en `supplier_form.js` por `${CONFIG.API_BASE_URL}`
3. [x] Verificar que `config.js` esté incluido en todos los HTML
4. [x] Extraer `<script>` del `login.html` a un archivo `login.js`
5. [ ] Refactorizar `template.css` para eliminar duplicidad y código muerto.

### Fase 2: Catálogos Dinámicos (Completada)
5. [x] Crear `cargarTiposPersona()` en `main.js` → Usar en `supplier_form.html`
6. [x] Crear `cargarTiposDocumento()` en `main.js` → Usar en `supplier_form.html`
7. [x] Crear `cargarDepartamentos()` en `main.js` → Usar en `supplier_form.html`
8. [x] Crear `cargarMunicipios(idDepto)` en `main.js` → Selector dependiente
9. [x] Crear `cargarFormasPago()` en `main.js` → Usar en `supplier_form.html`

### Fase 3: Listados Dinámicos (Prioridad Alta)
10. [x] Conectar `user_list.html` con `GET /usuarios` → Tabla dinámica
11. [ ] Conectar `buyer_supplier_list.html` con `GET /proveedores` → Tabla dinámica
12. [ ] Conectar `admin_dashboard.html` con conteos reales desde la BD

### Fase 4: Operaciones CRUD Completas (Prioridad Media)
13. [x] Conectar botón "Eliminar" en `user_list.html` con `DELETE /usuarios/{id}`
14. [x] Conectar modal "Editar" en `user_list.html` con `PUT /usuarios/{id}`
15. [x] Conectar toggle "Activar/Desactivar" con `PATCH /usuarios/{id}/estado`
16. [ ] Conectar perfil de proveedor con `GET /proveedores/{id}`

### Fase 5: Flujos de Negocio (Prioridad Media)
17. [ ] Conectar evaluación de proveedores con `POST /evaluacion_proveedor`
18. [ ] Conectar evaluación de riesgos con `POST /evaluacion_riesgos`
19. [ ] Conectar validación de cumplimiento con `POST /validacion`
20. [ ] Conectar carga de documentos con `POST /documentos`

### Fase 6: Módulos Complementarios (Prioridad Baja)
21. [ ] Conectar notificaciones con `GET /notificaciones`
22. [ ] Conectar historial de auditoría con `GET /historial_usuario`
23. [ ] Conectar certificaciones con datos reales
24. [ ] Conectar reportes del comprador con datos reales

---


## 6. Mapa de Archivos del Proyecto

### Backend (Controllers)
```
Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/controllers/
├── UsuariosController.java          → /usuarios (+ /login, + /{id}/foto)
├── ProveedorController.java         → /proveedor (+ /registro-completo)
├── RolesController.java             → /roles
├── TipoIdentificacionController.java→ /tipo_identificacion
├── TipoPersonaController.java       → /tipo_persona
├── TipoDocumentoController.java     → /tipo_documento
├── DepartamentoController.java      → /departamento
├── MunicipioController.java         → /municipio
├── FormaDePagoController.java       → /forma_de_pago
├── ContactoController.java          → /contacto
├── RepresentanteLegalController.java→ /representante_legal
├── EvaluacionProveedorController.java→ /evaluacion_proveedor
├── EvaluacionRiesgosController.java → /evaluacion_riesgos
├── NotificacionesController.java    → /notificaciones
├── DocumentosController.java        → /documentos
├── ... (16 controllers más)
```

### Frontend (Páginas y Scripts)
```
src/
├── js/
│   ├── config.js                    → Configuración global (API_BASE_URL)
│   ├── main.js                      → Control de accesos + menú + funciones reutilizables
│   ├── user_new.js                  → Formulario de registro de usuarios
│   ├── user_list.js                 → Gestión de lista de usuarios
│   ├── supplier_form.js             → Formulario de registro de proveedores
│   ├── user_profile.js             → Configuración de cuenta (foto, contraseña)
│   ├── buyer_dashboard.js           → Dashboard del comprador
│   ├── buyer_supplier_list.js       → Lista de proveedores (vista comprador)
│   ├── buyer_supplier_profile.js    → Perfil del proveedor
│   ├── buyer_first_evaluation.js    → Primera evaluación
│   ├── risk_review.js               → Revisión de riesgo
│   ├── compliance_officer_review.js → Revisión de cumplimiento
│   └── ... (10 archivos más)
│
├── sheets/
│   ├── user_new.html                → Registro de usuarios
│   ├── user_list.html               → Lista de usuarios
│   ├── supplier_form.html           → Formulario de proveedores
│   ├── admin_dashboard.html         → Dashboard administrador
│   ├── buyer_dashboard.html         → Dashboard comprador
│   ├── supplier_dashboard.html      → Dashboard proveedor
│   ├── risk_dashboard.html          → Dashboard analista de riesgo
│   ├── compliance_officer_dashboard.html → Dashboard oficial de cumplimiento
│   └── ... (22 páginas más)
│
└── login.html                       → Página de inicio de sesión (raíz del proyecto)
```

---

> **Última actualización:** 09 de Mayo de 2026 (Migración a Tabla Proveedor y Login por Email)  
> **Progreso general:** ~21.5% de endpoints conectados al Frontend ✅ (Módulo de Usuarios y Registro Base de Proveedor 100% Funcional)
