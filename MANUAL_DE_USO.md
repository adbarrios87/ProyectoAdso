# MANUAL DE USO DEL SISTEMA PARERE GRC
### Plataforma de Gestión de Gobernanza, Riesgo y Cumplimiento de Proveedores

> **Proyecto:** Análisis y Desarrollo de Software (ADSO) — SENA  
> **Autores:** Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz  
> **Versión:** 1.0 — Junio 2026

---

## TABLA DE CONTENIDO

1. [Introducción](#1-introducción)
2. [Descripción General del Sistema](#2-descripción-general-del-sistema)
3. [Requisitos del Sistema](#3-requisitos-del-sistema)
4. [Arquitectura Técnica](#4-arquitectura-técnica)
5. [Roles de Usuario](#5-roles-de-usuario)
6. [Acceso al Sistema — Inicio de Sesión](#6-acceso-al-sistema--inicio-de-sesión)
7. [Módulo Administrador](#7-módulo-administrador)
8. [Módulo Comprador](#8-módulo-comprador)
9. [Módulo Analista de Riesgos](#9-módulo-analista-de-riesgos)
10. [Módulo Oficial de Cumplimiento](#10-módulo-oficial-de-cumplimiento)
11. [Módulo Proveedor](#11-módulo-proveedor-portal-de-autogestión)
12. [Notificaciones y Alertas](#12-módulo-de-notificaciones-y-alertas)
13. [Preguntas Frecuentes](#13-preguntas-frecuentes-faq)
14. [Diccionario de Términos](#14-diccionario-de-términos)

---

## 1. INTRODUCCIÓN

### 1.1 Propósito del Manual

Este documento es el **Manual de Uso Oficial** del sistema **Parere GRC**, aplicación web desarrollada como proyecto de grado del programa **ADSO** del SENA. Su objetivo es guiar a los usuarios en el correcto uso de todas las funcionalidades del sistema.

### 1.2 Alcance

El manual cubre el **100% de las interfaces** del sistema, describiendo paso a paso cada funcionalidad para los cinco (5) roles definidos.

| Rol | Módulo Principal |
|-----|-----------------|
| Administrador | Centro de Control Maestro |
| Comprador | Gestión y Evaluación de Proveedores |
| Analista de Riesgos | Análisis SARLAFT/LAFT |
| Oficial de Cumplimiento | Revisión y Aprobación Legal |
| Proveedor | Portal de Autogestión |

---

## 2. DESCRIPCIÓN GENERAL DEL SISTEMA

**Parere GRC** (del latín *parere*: "satisfacer, cumplir") es una plataforma integral de **Gobernanza, Riesgo y Cumplimiento (GRC)** diseñada para garantizar la integridad y calidad de la cadena de suministro de una organización.

### 2.1 Problema que Resuelve

Las organizaciones que trabajan con proveedores externos enfrentan riesgos como:

- **Lavado de activos (LAFT):** Vinculación con proveedores que participan en actividades ilícitas.
- **Incumplimiento documental:** Documentos vencidos que generan sanciones legales.
- **Falta de trazabilidad:** Imposibilidad de rastrear el historial de evaluaciones.

### 2.2 Flujo General del Sistema

```
[Registro Proveedor] → [Validación Documental] → [Evaluación Técnica]
   → [Análisis de Riesgo LAFT] → [Revisión de Cumplimiento]
      → [Aprobación / Rechazo] → [Seguimiento Continuo]
```

### 2.3 Características Principales

- ✅ Gestión integral del ciclo de vida del proveedor
- ✅ Análisis de riesgo SARLAFT con cuestionario LAFT integrado
- ✅ Control automático de vencimiento de documentos
- ✅ Historial de evaluaciones y auditoría de eventos
- ✅ Menú dinámico según rol del usuario autenticado
- ✅ Portal de autogestión para el proveedor
- ✅ Generación de certificados de vinculación comercial
- ✅ Sistema de notificaciones en tiempo real

---

## 3. REQUISITOS DEL SISTEMA

### 3.1 Requisitos del Servidor (Backend)

| Componente | Requisito |
|-----------|-----------|
| Java | JDK 17 o superior |
| Framework | Spring Boot 3.x |
| Base de Datos | MySQL 8.0 o superior |
| Puerto | 8080 (por defecto) |
| IDE Recomendado | IntelliJ IDEA |

### 3.2 Requisitos del Cliente (Frontend)

| Componente | Requisito |
|-----------|-----------|
| Navegador | Google Chrome 100+ / Firefox 95+ / Edge 100+ |
| Resolución | Mínima: 1280 x 720 px |
| Conexión | Red local o Internet con acceso al backend |
| JavaScript | Habilitado (obligatorio) |

### 3.3 Dependencias Externas

| Recurso | Tipo | Uso |
|---------|------|-----|
| Font Awesome 6.4.2 | CDN | Iconografía del sistema |
| Google Fonts (Poppins, Open Sans) | CDN | Tipografía |
| MySQL Connector | Backend JAR | Conexión a base de datos |

---

## 4. ARQUITECTURA TÉCNICA

### 4.1 Arquitectura por Capas

```
┌─────────────────────────────────────┐
│      FRONTEND (Cliente/Navegador)   │
│  HTML5 + CSS3 + JavaScript (ES6+)  │
└────────────────┬────────────────────┘
                 │ HTTP REST (JSON)
                 │ Puerto 8080
┌────────────────┴────────────────────┐
│      BACKEND (Spring Boot 3)        │
│  Controladores → Servicios          │
│  Repositorios  → Base de Datos      │
└────────────────┬────────────────────┘
                 │ JPA / JDBC
┌────────────────┴────────────────────┐
│      BASE DE DATOS                  │
│      MySQL — Esquema: parere_grc    │
└─────────────────────────────────────┘
```

### 4.2 Estructura del Frontend

```
ProyectoAdso/
├── login.html                  ← Punto de entrada al sistema
├── src/
│   ├── assets/                 ← Logos y recursos gráficos
│   ├── css/                    ← Estilos por módulo + template.css
│   ├── js/                     ← Lógica JavaScript
│   │   ├── config.js           ← URL base del API
│   │   ├── main.js             ← Lógica global: menú, header, sesión
│   │   └── login.js            ← Autenticación
│   ├── db/                     ← Scripts SQL de base de datos
│   └── sheets/                 ← 30 vistas HTML del sistema
```

### 4.3 Estructura del Backend

```
Backend/proveedores/src/main/java/proyecto/ADSO/proveedores/
├── controllers/     ← 31 Endpoints REST
├── services/        ← Lógica de negocio
├── repositories/    ← Acceso a datos (Spring Data JPA)
├── entites/         ← 31 Modelos de base de datos
├── dtos/            ← Objetos de transferencia de datos
└── config/          ← Configuración CORS
```

### 4.4 Principales Endpoints de la API REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/usuarios/login` | Autenticación de usuario |
| GET | `/usuarios/menu/{idRol}` | Menú dinámico por rol |
| GET | `/proveedores` | Listar todos los proveedores |
| GET | `/proveedores/{id}` | Detalle de un proveedor |
| POST | `/proveedores` | Crear nuevo proveedor |
| PUT | `/proveedores/{id}` | Actualizar proveedor |
| DELETE | `/proveedores/{id}` | Eliminar proveedor |
| POST | `/proveedores/registro-completo` | Registro con todos los datos |
| PATCH | `/usuarios/{id}/foto` | Actualizar foto de perfil |
| PATCH | `/usuarios/{id}/estado` | Activar/Desactivar usuario |

### 4.5 Sistema de Diseño (UI/UX)

El sistema utiliza un diseño minimalista con las siguientes características:

| Variable | Valor | Descripción |
|----------|-------|-------------|
| `--background-navbar_btn` | `#1E1E2F` | Azul oscuro del sidebar |
| `--accent-color` | `#D4A373` | Tono arena/bronce para acentos |
| `--text-primary` | `#333333` | Texto principal grafito |
| Fuente principal | Poppins | Google Fonts |
| Fuente secundaria | Open Sans | Google Fonts |

---

## 5. ROLES DE USUARIO

### 5.1 Definición de Roles

| ID | Rol | Descripción | Dashboard de acceso |
|----|-----|-------------|---------------------|
| 1 | **Administrador** | Control total del sistema | `admin_dashboard.html` |
| 2 | **Comprador** | Gestión del ciclo del proveedor | `buyer_dashboard.html` |
| 3 | **Proveedor** | Portal de autogestión | `supplier_dashboard.html` |
| 4 | **Analista de Riesgos** | Análisis SARLAFT/LAFT | `risk_dashboard.html` |
| 5 | **Oficial de Cumplimiento** | Aprobación final legal | `compliance_officer_dashboard.html` |

### 5.2 Permisos por Funcionalidad

| Funcionalidad | Admin | Comprador | Analista | Oficial | Proveedor |
|---------------|:-----:|:---------:|:--------:|:-------:|:---------:|
| Gestión de Usuarios | ✅ | ❌ | ❌ | ❌ | ❌ |
| Logs de Auditoría | ✅ | ❌ | ❌ | ❌ | ❌ |
| Lista de Proveedores | ✅ | ✅ | ✅ | ✅ | ❌ |
| Validación Documental | ❌ | ✅ | ❌ | ❌ | ❌ |
| Primera/Segunda Evaluación | ❌ | ✅ | ❌ | ❌ | ❌ |
| Análisis LAFT | ❌ | ❌ | ✅ | ❌ | ❌ |
| Revisión Cumplimiento | ❌ | ❌ | ❌ | ✅ | ❌ |
| Perfil Propio | ❌ | ❌ | ❌ | ❌ | ✅ |
| Carga de Documentos | ❌ | ❌ | ❌ | ❌ | ✅ |
| Certificado Vinculación | ❌ | ❌ | ❌ | ❌ | ✅ |
| Alertas de Vencimiento | ✅ | ✅ | ❌ | ❌ | ❌ |
| Reportes | ✅ | ✅ | ✅ | ✅ | ❌ |

---

## 6. ACCESO AL SISTEMA — INICIO DE SESIÓN

### 6.1 Pantalla de Login

La pantalla principal (`login.html`) se divide en dos secciones:

**Lado Izquierdo — Branding:**
- Logo oficial de **Parere GRC**
- Eslogan: *"Validación Ética de Proveedores"*
- Íconos de funcionalidades: 🛡️ Gestión de Riesgo · ⚖️ Cumplimiento · ✅ Validación

**Lado Derecho — Formulario:**
- Campo de **Correo Electrónico**
- Campo de **Contraseña**
- Casilla "Recordarme"
- Enlace "¿Olvidaste tu contraseña?"
- Botón **Iniciar Sesión**

### 6.2 Pasos para Iniciar Sesión

1. Abrir el navegador y dirigirse a la URL del sistema.
2. Escribir el **correo electrónico** registrado en el sistema.
3. Escribir la **contraseña** asignada.
4. Hacer clic en **"Iniciar Sesión"**.
5. El sistema valida las credenciales con el backend (`POST /usuarios/login`).
6. Si son correctas, redirige automáticamente al dashboard del rol correspondiente.

### 6.3 Mensajes de Error

| Situación | Mensaje del sistema | Solución |
|-----------|---------------------|----------|
| Campos vacíos | "Por favor, ingresa tu correo electrónico y contraseña." | Completar ambos campos |
| Datos incorrectos | "Usuario o contraseña incorrectos" | Verificar datos ingresados |
| Backend apagado | "No se pudo conectar con el servidor." | Iniciar Spring Boot en IntelliJ |

### 6.4 Recuperación de Contraseña

1. Hacer clic en **"¿Olvidaste tu contraseña?"**.
2. El sistema abre `password_recovery.html`.
3. Seguir las instrucciones en pantalla.
4. Si el problema persiste, contactar al Administrador del sistema.

### 6.5 Cierre de Sesión

Desde cualquier pantalla del sistema:
1. Hacer clic en el **avatar/nombre de usuario** en la esquina superior derecha.
2. Se despliega el menú de usuario.
3. Hacer clic en **"Cerrar Sesión"**.
4. Confirmar en el diálogo emergente.
5. El sistema borra la sesión y redirige al login.

---

## 7. MÓDULO ADMINISTRADOR

El **Administrador** tiene control total sobre el sistema: gestión de usuarios, auditoría y configuración global.

### 7.1 Dashboard del Administrador (`admin_dashboard.html`)

Al iniciar sesión como administrador, se muestra el **Panel de Control Maestro** con:

- **Estadísticas Globales:** Total de proveedores, usuarios activos, documentos próximos a vencer, evaluaciones pendientes.
- **Actividad Reciente:** Últimos eventos registrados en el sistema.
- **Accesos Rápidos:** Botones directos a funciones frecuentes.

**Menú de Navegación (Sidebar):**
- Inicio
- Gestión de Usuarios → Lista de Usuarios / Nuevo Usuario
- Auditoría del Sistema
- Notificaciones
- Alertas de Vencimiento

### 7.2 Lista de Usuarios (`user_list.html`)

Tabla con todos los usuarios registrados en el sistema.

**Columnas de la tabla:**

| Columna | Descripción |
|---------|-------------|
| Nombre | Nombre completo del usuario |
| Correo | Correo electrónico (usado para login) |
| Rol | Rol asignado en el sistema |
| Estado | Activo ✅ / Inactivo ❌ |
| Fecha de Creación | Cuándo fue registrado |
| Acciones | Ver / Editar / Activar-Desactivar / Eliminar |

**Funcionalidades disponibles:**
- **Buscar** en tiempo real por nombre o correo.
- **Filtrar** por rol o estado.
- **Activar/Desactivar** usuario sin eliminarlo.
- **Editar** información del usuario.
- **Eliminar** (acción irreversible, solicita confirmación).

**Para desactivar un usuario:**
1. Localizar el usuario en la lista.
2. Hacer clic en el interruptor de estado o el botón de acción.
3. Confirmar en el diálogo emergente.
4. El API actualiza el estado (`PATCH /usuarios/{id}/estado`).

### 7.3 Crear Nuevo Usuario (`user_new.html`)

Formulario para registrar nuevos usuarios en el sistema.

**Campos del formulario:**

| Campo | Tipo | Obligatorio | Descripción |
|-------|------|:-----------:|-------------|
| Nombre Completo | Texto | ✅ | Nombre del usuario |
| Cargo | Texto | ✅ | Cargo en la organización |
| Correo Electrónico | Email | ✅ | Será el nombre de usuario |
| Contraseña | Password | ✅ | Mínimo 8 caracteres |
| Rol | Selección | ✅ | Uno de los 5 roles |
| Foto de Perfil | URL | ❌ | Imagen de perfil |

**Pasos:**
1. Ir a **Gestión de Usuarios → Nuevo Usuario**.
2. Completar todos los campos obligatorios.
3. Seleccionar el rol en el desplegable.
4. Hacer clic en **"Guardar Usuario"**.
5. El sistema confirma el registro exitoso.

### 7.4 Perfil de Usuario (`user_profile.html`)

Vista de perfil accesible para cualquier rol (cada usuario ve el suyo propio).

**Funcionalidades:**
- Ver y editar información personal.
- Actualizar foto de perfil.
- Cambiar contraseña.
- Configurar preferencias de notificaciones:

| Preferencia | Descripción |
|------------|-------------|
| `notif_status` | Cambios de estado de proveedores |
| `notif_docs` | Nuevos documentos cargados |
| `notif_expiry` | Alertas de vencimiento |
| `notif_news` | Noticias del sistema |

### 7.5 Logs de Auditoría (`admin_audit_logs.html`)

Registro cronológico de **todos los eventos** del sistema para garantizar la trazabilidad.

**Información por registro de auditoría:**

| Columna | Descripción |
|---------|-------------|
| Fecha y Hora | Marca de tiempo exacta |
| Usuario | Quién realizó la acción |
| Tipo de Evento | Login, creación, modificación, eliminación |
| Módulo | Qué parte del sistema fue afectada |
| Detalle | Descripción completa de la acción |
| IP | Dirección IP del usuario |

**Filtros disponibles:**
- Por rango de fechas
- Por tipo de evento
- Por usuario específico
- Búsqueda por texto libre

---

## 8. MÓDULO COMPRADOR

El **Comprador** gestiona el ciclo completo de vida del proveedor: validación, evaluación y seguimiento.

### 8.1 Dashboard del Comprador (`buyer_dashboard.html`)

**KPIs (Indicadores Clave):**
- Proveedores en proceso de validación
- Proveedores aprobados en el mes
- Evaluaciones pendientes
- Alertas de vencimiento activas

### 8.2 Lista de Proveedores (`buyer_supplier_list.html`)

Vista maestra de todos los proveedores.

**Columnas de la tabla:**

| Columna | Descripción |
|---------|-------------|
| NIT / Identificación | Número único del proveedor |
| Razón Social / Nombre | Nombre legal |
| Tipo de Persona | Natural o Jurídica |
| Estado | Activo / Pendiente / Suspendido / Rechazado |
| Fecha de Registro | Fecha de creación en el sistema |
| Acciones | Ver perfil / Editar / Evaluar |

**Funcionalidades:**
- Búsqueda por nombre, NIT o estado.
- Filtros por estado del proveedor.
- Ordenar por cualquier columna.
- Exportar listado.

### 8.3 Perfil del Proveedor (`buyer_supplier_profile.html`)

Vista detallada completa de un proveedor.

**Secciones del perfil:**
1. **Datos Principales:** Identificación, razón social, tipo de persona, CIIU.
2. **Información de Contacto:** Teléfono, correo, página web, dirección.
3. **Información Financiera:** Activos, pasivos, patrimonio, ingresos, gastos, banco de referencia.
4. **Documentos:** Lista de documentos con estado y fecha de vencimiento.
5. **Historial de Evaluaciones:** Evaluaciones previas del proveedor.
6. **Cuestionario LAFT:** Respuestas a las 5 preguntas de riesgo.

### 8.4 Validación de Documentos (`buyer_supplier_validation.html`)

El comprador revisa la documentación presentada por el proveedor.

**Proceso de validación:**
```
1. Seleccionar proveedor a validar
2. Por cada documento requerido:
   a. Revisar el documento cargado
   b. Marcar: ✅ Aprobado | ❌ Rechazado | ⏳ Pendiente
   c. Agregar comentarios si es necesario
3. Registrar resultado global de la validación
4. El estado del proveedor se actualiza automáticamente
```

**Documentos típicos a validar:**
- Cámara de Comercio actualizada (vigencia 30 días)
- RUT vigente
- Estados financieros certificados
- Certificados de calidad (ISO u otros)
- Pólizas de seguros vigentes
- Formulario LAFT diligenciado y firmado

### 8.5 Primera Evaluación (`buyer_first_evaluation.html`)

Evaluación técnica y comercial inicial del proveedor.

**Criterios de evaluación:**

| Criterio | Peso | Descripción |
|----------|:----:|-------------|
| Capacidad Financiera | 25% | Solidez económica demostrada |
| Experiencia | 20% | Trayectoria en el sector |
| Calidad | 25% | Certificaciones y estándares |
| Cumplimiento Documental | 20% | Documentos completos y vigentes |
| Referencias Comerciales | 10% | Clientes y proveedores previos |

**Pasos:**
1. Acceder al perfil del proveedor.
2. Seleccionar **"Iniciar Primera Evaluación"**.
3. Completar el formulario de criterios con la calificación (0-100 puntos).
4. Agregar observaciones.
5. Enviar para revisión del Analista de Riesgos.

### 8.6 Segunda Evaluación (`buyer_second_evaluation.html`)

Re-evaluación periódica de proveedores ya aprobados. Misma estructura que la primera evaluación, aplicada como control de calidad continuo.

### 8.7 Aprobación del Proveedor (`buyer_supplier_approve.html`)

Vista de aprobación intermedia del comprador antes de pasar al Oficial de Cumplimiento. Permite revisar y confirmar la recomendación de aprobación.

### 8.8 Historial de Aprobaciones (`approval_history.html`)

Registro de todas las decisiones tomadas sobre proveedores:
- Fecha de aprobación/rechazo
- Usuario que tomó la decisión
- Justificación
- Estado anterior y posterior

### 8.9 Alertas de Vencimiento (`expiration_alerts.html`)

Control automático de documentos próximos a vencer.

**Semáforo de alertas:**

| Color | Tiempo Restante | Acción Recomendada |
|:-----:|:--------------:|-------------------|
| 🔴 Rojo | Vencido o < 7 días | Notificar al proveedor de inmediato |
| 🟡 Amarillo | 8 a 30 días | Enviar aviso preventivo |
| 🟢 Verde | > 30 días | Sin acción requerida |

**Funcionalidades:**
- Filtrar por tipo de documento
- Exportar reporte de vencimientos
- Notificar directamente al proveedor desde la alerta

### 8.10 Reportes (`buyer_reports.html`)

Módulo de análisis con gráficas interactivas.

**Reportes disponibles:**
- Estado general de la cartera de proveedores
- Distribución por tipo de persona (natural/jurídica)
- Proveedores por nivel de riesgo
- Tendencia mensual de aprobaciones
- Documentos por estado de vencimiento

---

## 9. MÓDULO ANALISTA DE RIESGOS

El **Analista de Riesgos** evalúa el nivel de riesgo de cada proveedor bajo el marco SARLAFT/LAFT.

### 9.1 Dashboard del Analista (`risk_dashboard.html`)

- Proveedores pendientes de revisión de riesgo
- Estadísticas de niveles de riesgo (alto / medio / bajo)
- Alertas de proveedores de riesgo crítico

### 9.2 Revisión de Riesgo (`risk_review.html`)

**Cuestionario LAFT — 5 Preguntas Clave:**

| # | Campo en BD | Pregunta |
|---|-------------|---------|
| 1 | `laft_p1` | ¿El proveedor o socios figuran en listas restrictivas internacionales? |
| 2 | `laft_p2` | ¿Opera en sectores de alto riesgo identificados por la UIAF? |
| 3 | `laft_p3` | ¿Ha sido sancionado por entes de control en los últimos 5 años? |
| 4 | `laft_p4` | ¿Sus transacciones son consistentes con su actividad económica? |
| 5 | `laft_p5` | ¿Tiene vínculos conocidos con organizaciones investigadas? |

**Proceso de revisión:**
1. Seleccionar proveedor asignado.
2. Revisar información financiera y documental.
3. Responder el cuestionario LAFT (Sí/No por cada pregunta).
4. El sistema calcula el **Nivel de Riesgo** automáticamente.
5. Agregar justificación técnica.
6. Enviar resultado al Oficial de Cumplimiento.

**Resultado del análisis:**

| Nivel | Criterio | Acción |
|-------|----------|--------|
| 🔴 Riesgo Alto | Una o más respuestas críticas positivas | Rechazar o revisión especial |
| 🟡 Riesgo Medio | Indicadores de precaución moderada | Requiere seguimiento |
| 🟢 Riesgo Bajo | Sin señales de alerta | Continuar proceso normal |

### 9.3 Historial de Evaluaciones de Riesgo (`risk_historial.html`)

Registro completo de evaluaciones LAFT realizadas con:
- Proveedor evaluado
- Fecha de evaluación
- Analista responsable
- Nivel de riesgo asignado
- Detalle del cuestionario respondido
- Observaciones del analista

**Filtros:** por período, nivel de riesgo, nombre de proveedor.

---

## 10. MÓDULO OFICIAL DE CUMPLIMIENTO

El **Oficial de Cumplimiento** toma la decisión final de aprobación o rechazo del proveedor, revisando los resultados de todas las etapas anteriores.

### 10.1 Dashboard del Oficial (`compliance_officer_dashboard.html`)

- Proveedores en espera de aprobación final
- Estadísticas de aprobados y rechazados en el período
- Calendario de revisiones programadas

### 10.2 Revisión de Cumplimiento (`compliance_officer_review.html`)

El oficial revisa el **expediente consolidado** del proveedor:

1. Resultado de la validación documental (del Comprador)
2. Calificación de la evaluación técnica (del Comprador)
3. Nivel de riesgo LAFT asignado (del Analista)
4. Observaciones de todas las etapas previas

**Decisiones posibles:**

| Decisión | Estado Resultante | Notificación Automática |
|----------|:-----------------:|:-----------------------:|
| ✅ **Aprobar** | "Aprobado Activo" | Proveedor y Comprador |
| 🔄 **Devolver** | "En Corrección" | Indica qué corregir |
| ❌ **Rechazar** | "Rechazado" | Proveedor con justificación |

**Pasos para aprobar un proveedor:**
1. Revisar el expediente completo en pantalla.
2. Verificar que todos los módulos anteriores muestren aprobación.
3. Agregar comentario/observación final (opcional).
4. Hacer clic en **"Aprobar Proveedor"**.
5. Confirmar en el diálogo emergente.
6. El sistema actualiza el estado y genera notificaciones.

### 10.3 Historial del Oficial (`compliance_officer_history.html`)

Registro de todas las decisiones tomadas con:
- Fecha y hora de la decisión
- Proveedor afectado
- Decisión tomada
- Justificación
- Documentos revisados

---

## 11. MÓDULO PROVEEDOR (PORTAL DE AUTOGESTIÓN)

El proveedor gestiona su propia información, documentos y certificados de manera autónoma.

### 11.1 Dashboard del Proveedor (`supplier_dashboard.html`)

Panel de control personal que muestra:
- **Estado actual** de vinculación (Pendiente / En Evaluación / Aprobado / Rechazado)
- **Puntaje** obtenido en la última evaluación
- **Documentos pendientes** por cargar o actualizar
- **Próximos vencimientos** de documentos
- **Notificaciones** recientes del proceso

**Accesos rápidos:** Actualizar datos · Cargar documentos · Descargar certificado

### 11.2 Formulario de Registro/Actualización (`supplier_form.html`)

Formulario maestro con toda la información del proveedor (el más completo del sistema).

**Sección 1 — Identificación:**

| Campo | Descripción |
|-------|-------------|
| Tipo de Persona | Natural o Jurídica |
| Tipo de Identificación | NIT, CC, CE, Pasaporte, etc. |
| Número de Identificación | Número único |
| Dígito de Verificación | Para NIT |
| Razón Social / Nombre y Apellido | Nombre legal |

**Sección 2 — Contacto:**

| Campo | Descripción |
|-------|-------------|
| Teléfono Principal | Con tipo (fijo/celular) |
| Correo Principal | Correo de contacto comercial |
| Página Web | URL corporativa |
| Dirección | País → Departamento → Municipio |

**Sección 3 — Información Financiera:**

| Campo | Descripción |
|-------|-------------|
| Activos | Total de activos en pesos |
| Pasivos | Total de pasivos |
| Patrimonio | Activos menos Pasivos |
| Total Ingresos | Ingresos del período |
| Total Gastos | Gastos del período |
| Banco de Referencia | Entidad bancaria principal |

**Sección 4 — Cuestionario LAFT:** 5 preguntas Sí/No. Obligatorio y con firma electrónica.

**Sección 5 — Representante Legal:** Para personas jurídicas.

**Sección 6 — Socios/Accionistas:** Con porcentaje de participación de cada uno.

**Pasos para actualizar el formulario:**
1. Navegar a **"Actualizar Mi Información"**.
2. El formulario se precarga con los datos actuales.
3. Modificar los campos necesarios.
4. Hacer clic en **"Guardar Cambios"** al final de cada sección.

### 11.3 Carga de Documentos (`supplier_upload_documents.html`)

**Tipos de documentos:**

| Documento | Vigencia Típica | Obligatorio |
|-----------|:--------------:|:-----------:|
| Cámara de Comercio | 30 días | ✅ |
| RUT | Siempre vigente | ✅ |
| Estados Financieros | Anual | ✅ |
| Certificado de Calidad | Según certificación | ❌ |
| Póliza de Responsabilidad Civil | Anual | ✅ |
| Formulario LAFT Firmado | Por proceso | ✅ |

**Proceso de carga:**
1. Navegar a **"Mis Documentos"**.
2. Clic en **"Cargar Documento"** junto al tipo correspondiente.
3. Seleccionar el archivo PDF desde el computador.
4. Especificar la **fecha de vencimiento**.
5. Clic en **"Confirmar Carga"**.
6. El estado cambia a "Pendiente de revisión".

**Estados de los documentos:**

| Estado | Significado |
|--------|-------------|
| 🟡 Pendiente | En espera de revisión del comprador |
| 🟢 Aprobado | Documento válido y aceptado |
| 🔴 Rechazado | El comprador solicitó reemplazarlo |
| ⚫ Vencido | Fecha de vigencia superada |

### 11.4 Certificado de Vinculación (`supplier_certification.html`)

Disponible cuando el proveedor está en estado **"Aprobado"**.

**Contenido del certificado:**
- Nombre/Razón Social del proveedor
- Número de identificación
- Estado de vinculación: Activo
- Fecha de aprobación
- Puntaje obtenido
- Sello del sistema

**Para obtener el certificado:**
1. Verificar que el estado sea **"Aprobado"**.
2. Ir a **"Mi Certificado"**.
3. Clic en **"Generar Certificado"**.
4. Clic en **"Descargar"** para guardar en el computador.

### 11.5 Historial de Calificaciones (`supplier_qualification_history.html`)

- Fecha de cada evaluación
- Puntaje obtenido
- Evaluador asignado
- Observaciones recibidas
- Evolución del puntaje en el tiempo (gráfica)

---

## 12. MÓDULO DE NOTIFICACIONES Y ALERTAS

### 12.1 Centro de Notificaciones (`notifications.html`)

Disponible para todos los roles. Centraliza todas las alertas y mensajes del sistema.

**Tipos de notificaciones:**

| Tipo | Descripción | A quién llega |
|------|-------------|:-------------:|
| Cambio de Estado | Estado del proveedor modificado | Comprador, Proveedor |
| Documento Nuevo | El proveedor cargó un documento | Comprador |
| Próximo Vencimiento | Documento vence en < 30 días | Comprador, Admin |
| Aprobación/Rechazo | Decisión del Oficial | Proveedor, Comprador |
| Nueva Evaluación | Evaluación asignada | Analista, Oficial |

### 12.2 Configurar Preferencias de Notificaciones

Desde **Mi Perfil → Configuración de Notificaciones**, usar los interruptores para activar/desactivar cada tipo.

---

## 13. PREGUNTAS FRECUENTES (FAQ)

**P: ¿Qué hago si olvidé mi contraseña?**
> Clic en "¿Olvidaste tu contraseña?" en el login. Si persiste el problema, contactar al Administrador.

**P: ¿Por qué no puedo acceder al sistema?**
> Verificar: (1) el Backend esté iniciado en IntelliJ IDEA, (2) JavaScript habilitado en el navegador, (3) URL de acceso correcta.

**P: ¿Por qué el menú lateral aparece vacío?**
> El menú se carga dinámicamente desde el Backend. Verificar que la conexión al servidor en `http://localhost:8080` esté activa.

**P: ¿Cuánto tiempo dura la sesión?**
> La sesión se mantiene mientras el navegador esté abierto. Al cerrar, expira. La opción "Recordarme" en el login ayuda a mantenerla.

**P: ¿Cómo sé el estado de mi proveedor?**
> Desde el Dashboard del Proveedor (si eres proveedor) o la Lista de Proveedores (si eres comprador). El estado se muestra como badge de color en tiempo real.

**P: ¿Puedo exportar los reportes?**
> Sí. En el módulo de Reportes y en la Lista de Proveedores existen botones de exportación.

**P: ¿Qué formato deben tener los documentos al cargarlos?**
> Se recomienda **PDF**. El sistema también puede aceptar imágenes (JPG, PNG) según la configuración del servidor.

**P: ¿Cómo se calcula el puntaje del proveedor?**
> Es una ponderación de: Capacidad Financiera (25%) + Experiencia (20%) + Calidad (25%) + Cumplimiento Documental (20%) + Referencias Comerciales (10%).

**P: ¿Puede haber dos sesiones abiertas al mismo tiempo?**
> No se recomienda. El sistema usa `localStorage` del navegador por pestaña, lo que puede generar conflictos en múltiples pestañas o navegadores distintos.

---

## 14. DICCIONARIO DE TÉRMINOS

### A

**ADSO**
*Análisis y Desarrollo de Software.* Programa de formación tecnológica del SENA orientado al diseño, construcción y mantenimiento de aplicaciones de software.

**Analista de Riesgos**
Usuario del sistema encargado de evaluar el perfil de riesgo de los proveedores mediante el análisis SARLAFT/LAFT. Identificado con Rol ID = 4.

**API (Application Programming Interface)**
Interfaz de programación que permite la comunicación entre el Frontend y el Backend. En Parere GRC, son los endpoints REST disponibles en `http://localhost:8080`.

**Activos**
Todos los bienes y derechos económicos de los que dispone una empresa. Campo `activos` en la entidad `Proveedor`.

**Auditoría**
Registro cronológico de todas las acciones importantes del sistema para garantizar trazabilidad y cumplimiento regulatorio. Disponible en `admin_audit_logs.html`.

### B

**Backend**
Parte del sistema que se ejecuta en el servidor. En Parere GRC, es la aplicación **Spring Boot 3** escrita en Java 17, que procesa las peticiones y accede a la base de datos.

**Badge**
Etiqueta visual pequeña y colorida que indica el estado de un elemento. Ejemplo: badge verde "Aprobado", badge rojo "Rechazado".

**Base de Datos**
Sistema organizado de almacenamiento de información. El proyecto usa **MySQL** con el esquema `parere_grc`, que contiene 31 tablas relacionadas.

### C

**CIIU**
*Clasificación Industrial Internacional Uniforme.* Código numérico que identifica la actividad económica principal de una empresa. Ejemplo: 4711 = Comercio al por menor en establecimientos no especializados.

**Comprador**
Rol del sistema encargado de gestionar el ciclo completo de vida del proveedor: validación documental, evaluación técnica, reportes y alertas. ID Rol = 2.

**CORS (Cross-Origin Resource Sharing)**
Mecanismo de seguridad del navegador. La clase `CorsConfig.java` del Backend permite que el Frontend haga peticiones al API sin ser bloqueado por el navegador.

**CSS (Cascading Style Sheets)**
Lenguaje de hojas de estilo para diseñar la apariencia visual de las páginas HTML. El proyecto centraliza el diseño en `template.css`.

### D

**Dashboard**
Página principal de un rol que presenta indicadores clave (KPIs), gráficas y accesos rápidos a las funciones más relevantes.

**Debida Diligencia**
Proceso exhaustivo de verificación de un proveedor antes de establecer relación comercial. Incluye validación documental, financiera y de riesgo LAFT.

**DDL (Data Definition Language)**
Instrucciones SQL para definir la estructura de la base de datos (CREATE TABLE, ALTER TABLE). Contenidas en `src/db/create_tables.sql`.

**DML (Data Manipulation Language)**
Instrucciones SQL para manipular los datos (INSERT, UPDATE, DELETE). Contenidas en `src/db/insert_data.sql`.

**DTO (Data Transfer Object)**
Clase Java usada para transferir datos entre capas del sistema, sin lógica de negocio. Ejemplo: `LoginResponseDto.java` transporta los datos de sesión del usuario.

### E

**Endpoint**
URL específica de la API que acepta un tipo de petición HTTP. Ejemplo: `POST /usuarios/login` es el endpoint de autenticación del sistema.

**Entidad (Entity)**
Clase Java anotada con `@Entity` que representa una tabla de la base de datos. Ejemplo: `ProveedorEntity.java` mapea la tabla `proveedor` con sus 30 columnas.

**Estado del Proveedor**
Situación actual del proveedor en el proceso: Pendiente, En Validación, En Evaluación, Aprobado, Rechazado, Suspendido.

### F

**Font Awesome**
Biblioteca de iconos web en formato vectorial, usada en toda la interfaz del sistema para los íconos del menú, botones y badges. Se integra vía CDN.

**Frontend**
Parte del sistema ejecutada en el navegador del usuario, compuesta por archivos HTML5, CSS3 y JavaScript ES6+.

### G

**GRC (Governance, Risk and Compliance)**
Marco de gestión empresarial que integra Gobernanza, Gestión de Riesgos y Cumplimiento Regulatorio. Es el concepto central de **Parere GRC**.

**Gobernanza**
Conjunto de políticas y controles que garantizan que una organización opera de manera ética, transparente y alineada con sus objetivos estratégicos.

### H

**HTTP (HyperText Transfer Protocol)**
Protocolo de comunicación de la web. El sistema usa los métodos: `GET` (consultar), `POST` (crear), `PUT` (actualizar completo), `PATCH` (actualizar parcial), `DELETE` (eliminar).

### I

**ID (Identificador)**
Número único generado automáticamente por la base de datos para cada registro. En Java, se define con `@Id @GeneratedValue(strategy = GenerationType.IDENTITY)`.

**IntelliJ IDEA**
Entorno de desarrollo (IDE) recomendado para ejecutar el Backend. Es donde se inicia el servidor Spring Boot del proyecto.

### J

**Java**
Lenguaje de programación orientado a objetos usado para el Backend del sistema (versión 17 LTS).

**JPA (Java Persistence API)**
Especificación Java para el mapeo objeto-relacional (ORM). Permite trabajar con la base de datos usando objetos Java en lugar de consultas SQL directas.

**JSON (JavaScript Object Notation)**
Formato de intercambio de datos entre el Frontend y el Backend. Ejemplo de respuesta de login: `{"data": {"idUsuario": 1, "idRol": 2, "nombreUsuario": "Juan"}}`.

### K

**KPI (Key Performance Indicator)**
Indicador clave de rendimiento. Son las tarjetas numéricas en los dashboards que muestran métricas relevantes como proveedores activos, documentos vencidos, etc.

### L

**LAFT**
*Lavado de Activos y Financiación del Terrorismo.* Actividades ilegales que buscan dar apariencia legal a dinero ilícito. El sistema incluye un cuestionario LAFT con 5 preguntas para evaluar el riesgo de cada proveedor.

**localStorage**
Mecanismo de almacenamiento del navegador que el sistema usa para mantener la sesión del usuario. Almacena: `userRole`, `userId`, `userName`, `userEmail`, `idRol`, `userPhoto`.

**Lombok**
Biblioteca Java que genera código repetitivo automáticamente. Se usa con anotaciones como `@Data` (getters y setters), `@Builder` (patrón builder) en todas las entidades.

### M

**Menú Dinámico**
Menú lateral que cambia automáticamente según el rol del usuario autenticado. Se carga desde el Backend mediante `GET /usuarios/menu/{idRol}`.

**Modal**
Ventana emergente que aparece sobre la página para solicitar confirmación o mostrar información. Bloquea la interacción con el contenido de fondo hasta ser cerrada.

**MySQL**
Sistema de gestión de bases de datos relacional (RDBMS) usado por el proyecto. El esquema se llama `parere_grc` y contiene las tablas, vistas y datos del sistema.

### N

**NIT**
*Número de Identificación Tributaria.* Identificador fiscal colombiano asignado por la DIAN a personas jurídicas (empresas). Incluye un dígito de verificación al final.

### O

**Oficial de Cumplimiento**
Usuario del sistema responsable de la revisión legal final y la decisión de aprobación o rechazo de un proveedor. ID Rol = 5.

**ORM (Object-Relational Mapping)**
Técnica que mapea automáticamente tablas de base de datos a clases Java. Spring Data JPA es la implementación ORM del proyecto.

### P

**Parere GRC**
Nombre del sistema desarrollado. Del latín *parere* ("satisfacer, cumplir"). Plataforma de Gobernanza, Riesgo y Cumplimiento creada como proyecto ADSO del SENA.

**Pasivos**
Deudas y obligaciones financieras de una empresa. Campo `pasivos` en la entidad `Proveedor`.

**Patrimonio**
Valor neto de una empresa: Activos menos Pasivos. Indica la solidez financiera real del proveedor.

**Persona Jurídica**
Empresa o sociedad legalmente constituida, con personería jurídica propia. Se identifica con NIT.

**Persona Natural**
Individuo que actúa en nombre propio para actividades económicas. Se identifica con CC, CE, Pasaporte, etc.

**Poppins**
Fuente tipográfica principal del sistema, cargada desde Google Fonts. Aporta modernidad, profesionalismo y lecturabilidad a toda la interfaz.

**Proveedor**
Empresa o persona natural que ofrece bienes o servicios a la organización. En el sistema, también es un rol de usuario (ID Rol = 3) para acceder al portal de autogestión.

### R

**Repositorio (Repository)**
Capa del Backend que se comunica directamente con la base de datos. Implementa interfaces que extienden `JpaRepository` de Spring Data JPA.

**REST (Representational State Transfer)**
Estilo de arquitectura para diseñar APIs web. Define cómo los recursos son accedidos con métodos HTTP estándar (GET, POST, PUT, DELETE, PATCH).

**RUT**
*Registro Único Tributario.* Documento emitido por la DIAN que identifica a personas y entidades que realizan actividades económicas en Colombia.

**Rol**
Define el nivel de acceso y las funcionalidades disponibles para cada usuario. El sistema tiene 5 roles con menus y paneles distintos.

### S

**SARLAFT**
*Sistema de Administración del Riesgo de Lavado de Activos y Financiación del Terrorismo.* Marco regulatorio colombiano exigido por la Superintendencia Financiera para prevenir el uso de entidades en actividades ilícitas.

**SENA**
*Servicio Nacional de Aprendizaje.* Institución pública colombiana que provee formación técnica y tecnológica gratuita.

**Sesión**
Período en que un usuario está autenticado. Se almacena en `localStorage` y se destruye al hacer clic en "Cerrar Sesión" o al limpiar el navegador.

**Sidebar**
Barra de navegación lateral del sistema que contiene el menú de opciones, el logo de Parere GRC y el menú inferior (Salida y Ayuda). Fondo oscuro `#1E1E2F`.

**Spring Boot**
Framework Java que simplifica la creación de aplicaciones backend robustas y auto-configuradas. El Backend de Parere GRC usa la versión 3.x.

**SQL (Structured Query Language)**
Lenguaje estándar para gestionar bases de datos relacionales. El proyecto incluye: `create_tables.sql`, `insert_data.sql` y `vistas.sql`.

### T

**Template.css**
Hoja de estilo maestra que define todos los tokens de diseño del sistema: colores, tipografía, espaciados y componentes base. Ubicada en `src/css/template.css`.

**Token de Diseño**
Variable CSS con un valor de diseño reutilizable. Ejemplo: `--accent-color: #D4A373` (dorado/arena usado en botones y acentos).

**Trazabilidad**
Capacidad de rastrear el historial completo de cambios y acciones sobre un registro. Garantizada por el módulo de Auditoría del sistema.

### U

**UIAF**
*Unidad de Información y Análisis Financiero.* Entidad colombiana que recibe y analiza información para combatir el lavado de activos y financiación del terrorismo.

**URL (Uniform Resource Locator)**
Dirección web de un recurso. La URL base del API se configura en `config.js`: `API_BASE_URL: 'http://localhost:8080'`.

### V

**Validación Documental**
Proceso por el cual el Comprador verifica que los documentos del proveedor sean auténticos, vigentes y completos antes de continuar con la evaluación.

**Vista (View)**
Archivo HTML que representa una pantalla del sistema. El proyecto tiene 30 vistas en la carpeta `src/sheets/`.

### W

**WebApp**
Aplicación que se ejecuta en el navegador web sin necesidad de instalación. Parere GRC es una WebApp que sólo requiere un navegador moderno.

---

## INFORMACIÓN DEL PROYECTO

| Dato | Descripción |
|------|-------------|
| **Nombre** | Parere GRC |
| **Programa** | Análisis y Desarrollo de Software (ADSO) |
| **Institución** | SENA |
| **Año** | 2025 – 2026 |
| **Autores** | Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz |
| **Frontend** | HTML5 · CSS3 · JavaScript ES6+ |
| **Backend** | Java 17 · Spring Boot 3 · Spring Data JPA |
| **Base de Datos** | MySQL 8.0 — Esquema: `parere_grc` |
| **Versionamiento** | Git / GitHub |
| **Correos** | adbarrios87@gmail.com · espriggan123@gmail.com |

---

*© 2026 — Parere GRC. Proyecto desarrollado para el programa de formación ADSO — SENA.*
