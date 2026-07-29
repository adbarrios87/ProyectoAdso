# Plan de Integración: Endpoints Backend ↔ Frontend

> **Proyecto:** Parere GRC — Validación Ética de Proveedores  
> **Última actualización:** 25 de Junio de 2026  
> **Autores:** Juan Cadena, Adriana Barrios

---

## 1. Resumen de Estado General

El Backend cuenta con **33 controladores** (CRUD estándar + lógica especial). El avance actual de integración con el Frontend se detalla a continuación:

* **Conectados al Backend (✅):** 82 endpoints (~51.2% del total del Backend).
* **Simulados en Frontend (🔶):** Flujos visuales con `localStorage` o datos estáticos listos para ser conectados.
* **Pendientes de Interfaz (❌):** Endpoints del backend sin uso actual en las vistas del cliente.

---

## 2. Inventario de Endpoints e Integración

### 2.1 Usuarios y Autenticación
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `POST` | `/usuarios/login` | `login.html` / `login.js` | Inicio de sesión con correo. |
| ✅ | `POST` | `/usuarios` | `user_new.html` / `user_new.js` | Crear usuario e inicializar alertas. |
| ✅ | `GET` | `/usuarios` | `user_list.html` / `user_list.js` | Tabla de usuarios con búsqueda. |
| ✅ | `GET` | `/usuarios/{id}` | `user_profile.html` / `user_profile.js` | Detalle del perfil y preferencias. |
| ✅ | `PUT` | `/usuarios/{id}` | `user_profile.html` / `user_profile.js` | Editar usuario y notificaciones. |
| ✅ | `PATCH`| `/usuarios/{id}/estado`| `user_list.html` / `user_list.js` | Borrado lógico (activo = false). |
| ✅ | `PATCH`| `/usuarios/{id}/foto` | `user_profile.html` / `user_profile.js` | Carga de foto de perfil (Base64). |

### 2.2 Proveedores y Datos de Perfil (Contactos, Socios y Ubicaciones)
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `POST` | `/proveedores/registro-completo` | `supplier_form.html` / `supplier_form.js` | Envía proveedor + contactos + socios. |
| ✅ | `GET` | `/proveedores` | `buyer_supplier_list.html` | Tabla general de proveedores. |
| ✅ | `GET` | `/proveedores/{id}` | `buyer_supplier_profile.html` | Perfil completo del proveedor. |
| ✅ | `GET` | `/proveedores/by-email` | `supplier_dashboard.js` | Carga perfil del proveedor logueado. |
| ✅ | `DELETE`| `/proveedores/{id}` | `buyer_supplier_profile.js` | Borrado lógico (activo = false). |
| ✅ | `GET` | `/contacto` | `buyer_supplier_profile.js` | Visualización, creación y edición. |
| ✅ | `GET` | `/ubicacion` | `buyer_supplier_list.js` | Sincronización geográfica. |
| ✅ | `GET` | `/pais` \| `/departamento` \| `/municipio` | `supplier_form.js` | Selectores en cascada dinámicos. |

### 2.3 Documentación
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `POST` | `/documentos/upload` | `supplier_upload_documents.js` | Carga real física a Google Drive. |
| ✅ | `GET` | `/documentos/proveedor/{id}` | `supplier_upload_documents.js` | Consulta de documentos cargados. |

### 2.4 Calificaciones y Evaluaciones de Desempeño
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `POST` | `/evaluacion_proveedor` | `buyer_first_evaluation.js` | Guarda evaluaciones en BD. |
| ✅ | `GET` | `/evaluacion_proveedor/{id}` | `buyer_first_evaluation.js` | Detalle para re-calificación. |
| ✅ | `PUT` | `/evaluacion_proveedor/{id}` | `buyer_first_evaluation.js` | Actualiza la evaluación. |
| ✅ | `DELETE`| `/evaluacion_proveedor/{id}` | `supplier_qualification_history.js` | Borra evaluación del historial. |
| ✅ | `GET` | `/evaluacion_proveedor/proveedor/{id}` | `supplier_certification.js` | Genera reportes PDF firmados con jsPDF. |

### 2.5 Validación y Riesgos
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `POST` | `/validacion-final` | `buyer_supplier_validation.js`, `compliance_officer_review.js`, `risk_review.js` | Guarda la validación final (Compras, Riesgos, Oficial) y enlaza las validaciones individuales. |
| ✅ | `POST` \| `PUT` | `/validacion` | `buyer_supplier_validation.js`, `risk_review.js` | Crea/modifica estados de validación de campo individuales. |
| ✅ | `PATCH`| `/proveedores/{id}/estado` | `compliance_officer_review.js`, `risk_review.js` | Actualización parcial y segura del estado del proveedor y auditor. |
| ✅ | `GET` | `/campo_validacion` | `risk_review.js` | Lista campos de validación del sistema. |

### 2.6 Configuración y Catálogos Maestros (CRUD Administrador)
Administrados de forma global e interactiva a través del **Configurador del Sistema** (`admin_config.html` y `admin_config.js`).

| Estado | Tipo CRUD | Endpoint | Notas |
| :---: | :---: | :--- | :--- |
| ✅ | Completo | `/calificacion` | Gestión de calificaciones y estados del semáforo. |
| ✅ | Completo | `/estado_proveedor` | Gestión de estados de proveedor (ej: Activo, Pendiente). |
| ✅ | Completo | `/roles` | Gestión de roles de usuario (Admin, Auditor, etc.). |
| ✅ | Completo | `/tipo_documento` | Definición de RUT, Cámara, Certificación, etc. |
| ✅ | Completo | `/tipo_identificacion` | Tipos de documentos aceptados (CC, NIT, CE, PAS). |
| ✅ | Completo | `/tipo_notificacion` | Canales de alertas del sistema. |
| ✅ | Completo | `/tipo_pago` | Configuración de tipos de pagos. |
| ✅ | Completo | `/tipo_persona` | Tipos de personas (Natural, Jurídica). |
| ✅ | Completo | `/tipo_telefono` | Tipos de teléfono (Celular, Fijo, etc.). |

### 2.7 Firma Digital (Token y Formulario)
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| ✅ | `GET` | `/firmas/validar` | `sign_form.js` | Valida el token de firma enviado por correo. |
| ✅ | `POST` | `/firmas/firmar` | `sign_form.js` | Registra la aceptación digital y firma del formulario. |

### 2.8 Auditoría y Logs
| Estado | Método | Endpoint | Frontend (HTML/JS) | Descripción / Notas |
| :---: | :---: | :--- | :--- | :--- |
| 🔶 | `GET` | `/historial_usuario` | `admin_audit_logs.html` | Visualización completa de auditoría (actualmente estático). |

---

## 3. Ruta Sugerida de Desarrollo (Próximos Pasos)

Para continuar con el cierre de funcionalidades y lograr una integración del 100%, se sugiere el siguiente orden de desarrollo priorizado según el impacto en la operación del negocio:

### 1. Conexión de Evaluación de Riesgos (Analista de Riesgo) ✅ **COMPLETADO**
* **Endpoints integrados:** `POST /validacion-final` y `PATCH /proveedores/{id}/estado`.
* **Archivos Frontend:** `risk_list.js`, `risk_review.js`, `risk_historial.js` y `approval_history.js`.
* **Resultado:** Se integró la matriz interactiva de riesgos, el guardado de decisiones de analista (Aprobación/Rechazo) en `validacion-final` con actualización parcial mediante `PATCH` del estado del proveedor, y la modificación con auditoría de cambios (`fechaModificado`, `modificadoPor`).

### 2. Conexión de Decisiones de Cumplimiento (Oficial de Cumplimiento)
* **Objetivo:** Registrar las decisiones formales de aprobación o rechazo del oficial en la base de datos.
* **Endpoints:** `POST /validacion` y `GET /validacion`.
* **Archivos Frontend:** `compliance_officer_review.js` y `compliance_officer_dashboard.js`.
* **Impacto:** Alto. Cierra el flujo de validación ético permitiendo el cambio oficial del estado del proveedor.

### 3. Trazabilidad y Auditoría Real (Administrador)
* **Objetivo:** Visualizar el historial de acciones y eventos del sistema reales.
* **Endpoints:** `GET /historial_usuario`.
* **Archivos Frontend:** Crear e integrar `admin_audit_logs.js` con la vista `admin_audit_logs.html` para reemplazar los registros mock estáticos.
* **Impacto:** Medio-Bajo. Requerido para cumplimiento regulatorio y auditoría interna del sistema.

### 4. Automatización de Notificaciones y Alertas de Vencimiento
* **Objetivo:** Dinamizar el envío preventivo de recordatorios de documentos próximos a vencer.
* **Endpoints:** `/notificaciones`.
* **Archivos Frontend:** Conectar `expiration_alerts.js` para consumir el endpoint real del backend.
* **Impacto:** Bajo. Automatiza alertas y facilita la proactividad del administrador sobre documentos obsoletos.

---

## 4. Endpoints Pendientes de Conexión (Aún no Conectados)

A continuación se detallan los endpoints del Backend que están expuestos en los controladores pero que actualmente no están integrados ni consumidos por las interfaces del Frontend (y que justifican el ~48.8% pendiente de integración):

### 4.1 Entidades y Relaciones de Proveedor
* **Representantes y Socios:**
  * `GET` / `POST` / `PUT` / `DELETE` | `/representante_legal` (Gestión individual de representantes).
  * `GET` / `POST` / `PUT` / `DELETE` | `/representante_proveedor` (Relación representante-proveedor).
  * `GET` / `POST` / `PUT` / `DELETE` | `/socios_proveedor` (Gestión individual de accionistas/socios).
  * `GET` / `POST` / `PUT` / `DELETE` | `/documentos_socios` (Documentos de soporte de socios).
* **Contactos y Relaciones:**
  * `GET` / `POST` / `PUT` / `DELETE` | `/proveedor_contacto` (Relación contacto-proveedor).
  * `POST` / `PUT` / `DELETE` | `/contacto` (Creación y edición individual de contactos desde backend).
* **Ubicaciones del Proveedor:**
  * `POST` / `PUT` / `DELETE` | `/ubicacion` (Modificaciones de ubicaciones físicas).

### 4.2 Notificaciones y Seguridad
* **Notificaciones del Sistema:**
  * `GET` / `POST` / `PUT` / `DELETE` | `/notificaciones` (Gestión y alertas preventivas de vencimiento).
* **Seguridad y Estados de Usuario:**
  * `GET` / `POST` / `PUT` / `DELETE` | `/estado_usuario` (Gestión de estados del usuario, Ej: Bloqueado, Activo).
* **Auditoría:**
  * `POST` / `PUT` / `DELETE` | `/historial_usuario` (Inserción manual o modificaciones del registro de logs).

### 4.3 Endpoints Administrativos y de Catálogos (CRUD)
Aunque las vistas leen los catálogos en peticiones `GET` para poblar los formularios y filtros, las operaciones de escritura (creación, edición y borrado de catálogos) que corresponden al panel del Administrador no han sido conectadas en el Frontend:
* **Controladores de Catálogos Completos (`POST` / `PUT` / `DELETE`):**
  * `/calificacion` (Umbrales de semáforo).
  * `/estado_proveedor` (Estados del flujo del proveedor).
  * `/roles` (Roles del sistema).
  * `/tipo_documento` (Tipos de archivos requeridos).
  * `/tipo_identificacion` (Tipos de documento de identidad).
  * `/tipo_notificacion` (Canales de alerta).
  * `/tipo_pago` (Formas de transacciones).
  * `/tipo_persona` (Naturaleza jurídica).
  * `/tipo_telefono` (Clasificación de números).
  * `/origen_dato` (Orígenes de información externa - completamente desconectado).
  * `/forma_de_pago` (Métodos de desembolso).

