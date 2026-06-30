# Plan de Integración: Endpoints Backend ↔ Frontend

> **Proyecto:** Parere GRC — Validación Ética de Proveedores  
> **Última actualización:** 25 de Junio de 2026  
> **Autores:** Juan Cadena, Adriana Barrios

---

## 1. Resumen de Estado General

El Backend cuenta con **31 controladores** (CRUD estándar + lógica especial). El avance actual de integración con el Frontend se detalla a continuación:

* **Conectados al Backend (✅):** 78 endpoints (~49.3% del total del Backend).
* **Simulados en Frontend (🔶):** Flujos visuales con `localStorage` listos para ser conectados.
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
| ✅ | `POST` | `/validacion-final` | `buyer_supplier_validation.js` | Guarda la validación final, enlaza las validaciones individuales y actualiza el estado del proveedor (10/11). |
| ✅ | `POST` \| `PUT` | `/validacion` | `buyer_supplier_validation.js` | Crea/modifica estados de validación de campo individuales. |

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

---

## 3. Ruta Sugerida de Desarrollo (Próximos Pasos)

Para continuar con el cierre de funcionalidades simuladas (Fase 5), se recomienda conectar los siguientes flujos de negocio a sus endpoints reales:

1. **Evaluación de Riesgo Real (Analista de Riesgo):**
   * **Endpoints:** `POST /evaluacion_riesgos` y `GET /evaluacion_riesgos`
   * **Archivos:** Conectar `risk_review.js` y `risk_historial.js` para persistir las matrices de riesgo en la base de datos en lugar de usar `localStorage`.
2. **Validación de Cumplimiento Real (Oficial de Cumplimiento):**
   * **Endpoints:** `POST /validacion` y `GET /validacion`
   * **Archivos:** Conectar `compliance_officer_review.js` y `compliance_officer_dashboard.js` para registrar y guardar de forma persistente la decisión de cumplimiento del oficial en la tabla `validacion`.
