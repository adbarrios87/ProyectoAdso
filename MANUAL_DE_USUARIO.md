# MANUAL DE USUARIO DEL SISTEMA PARERE GRC
### Plataforma de Gestión de Gobernanza, Riesgo y Cumplimiento de Proveedores

> **Proyecto:** Análisis y Desarrollo de Software (ADSO) — SENA
> **Autores:** Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz
> **Versión:** 1.0 — Julio 2026
> **Evidencia:** Manual de usuario de acuerdo con las funcionalidades del software

---

## TABLA DE CONTENIDO

1. [Introducción](#1-introducción)
2. [¿Qué es Parere GRC?](#2-qué-es-parere-grc)
3. [Roles de Usuario](#3-roles-de-usuario)
4. [Acceso al Sistema — Inicio de Sesión](#4-acceso-al-sistema--inicio-de-sesión)
5. [Módulo Administrador](#5-módulo-administrador)
6. [Módulo Comprador](#6-módulo-comprador)
7. [Módulo Analista de Riesgos](#7-módulo-analista-de-riesgos)
8. [Módulo Oficial de Cumplimiento](#8-módulo-oficial-de-cumplimiento)
9. [Módulo Proveedor](#9-módulo-proveedor-portal-de-autogestión)
10. [Notificaciones y Alertas](#10-módulo-de-notificaciones-y-alertas)
11. [Preguntas Frecuentes](#11-preguntas-frecuentes-faq)
12. [Glosario de Términos](#12-glosario-de-términos)

---

## 1. INTRODUCCIÓN

### 1.1 Propósito del Manual

Este documento es el **Manual de Usuario** del sistema **Parere GRC**. Su objetivo es guiar a cualquier usuario, sin necesidad de conocimientos técnicos, en el correcto uso de todas las funcionalidades disponibles según su rol.

### 1.2 Alcance

El manual cubre el **100% de las pantallas** del sistema, explicando paso a paso cada funcionalidad para los cinco (5) roles definidos.

| Rol | Módulo Principal |
|-----|-------------------|
| Administrador | Centro de Control Maestro |
| Comprador | Gestión y Evaluación de Proveedores |
| Analista de Riesgos | Análisis SARLAFT/LAFT |
| Oficial de Cumplimiento | Revisión y Aprobación Legal |
| Proveedor | Portal de Autogestión |

> Si necesita conocer cómo está construido el sistema (arquitectura, base de datos, API), consulte la [Documentación Técnica](DOCUMENTACION_TECNICA.md).

---

## 2. ¿QUÉ ES PARERE GRC?

**Parere GRC** (del latín *parere*: "satisfacer, cumplir") es una plataforma que ayuda a la organización a vincular y hacer seguimiento a sus proveedores de forma segura y ordenada.

### 2.1 Problema que Resuelve

Las organizaciones que trabajan con proveedores externos enfrentan riesgos como:

- **Lavado de activos (LAFT):** vinculación con proveedores que participan en actividades ilícitas.
- **Incumplimiento documental:** documentos vencidos que generan sanciones legales.
- **Falta de trazabilidad:** imposibilidad de rastrear el historial de evaluaciones.

### 2.2 Flujo General del Proceso

```
[Registro Proveedor] → [Validación Documental] → [Evaluación Técnica]
   → [Análisis de Riesgo LAFT] → [Revisión de Cumplimiento]
      → [Aprobación / Rechazo] → [Seguimiento Continuo]
```

### 2.3 Qué Puede Hacer con el Sistema

- Gestionar el ciclo de vida completo del proveedor.
- Evaluar el riesgo con el cuestionario LAFT integrado.
- Controlar automáticamente el vencimiento de documentos.
- Consultar el historial de evaluaciones y auditoría.
- Ver un menú de opciones que cambia según su rol.
- Cargar documentos y generar el certificado de vinculación (si es proveedor).
- Recibir notificaciones en tiempo real.

---

## 3. ROLES DE USUARIO

### 3.1 Definición de Roles

| Rol | Descripción | Pantalla principal |
|-----|-------------|---------------------|
| **Administrador** | Control total del sistema | Panel de Control Maestro |
| **Comprador** | Gestión del ciclo del proveedor | Dashboard del Comprador |
| **Proveedor** | Portal de autogestión | Dashboard del Proveedor |
| **Analista de Riesgos** | Análisis SARLAFT/LAFT | Dashboard del Analista |
| **Oficial de Cumplimiento** | Aprobación final legal | Dashboard del Oficial |

### 3.2 Permisos por Funcionalidad

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

## 4. ACCESO AL SISTEMA — INICIO DE SESIÓN

### 4.1 Pantalla de Login

La pantalla principal se divide en dos secciones:

**Lado Izquierdo — Branding:**

- Logo oficial de **Parere GRC**.
- Eslogan: *"Validación Ética de Proveedores"*.
- Íconos de funcionalidades: 🛡️ Gestión de Riesgo · ⚖️ Cumplimiento · ✅ Validación.

**Lado Derecho — Formulario:**

- Campo de **Correo Electrónico**.
- Campo de **Contraseña**.
- Casilla "Recordarme".
- Enlace "¿Olvidaste tu contraseña?".
- Botón **Iniciar Sesión**.

### 4.2 Pasos para Iniciar Sesión

1. Abrir el navegador y dirigirse a la dirección del sistema.
2. Escribir el **correo electrónico** registrado.
3. Escribir la **contraseña** asignada.
4. Hacer clic en **"Iniciar Sesión"**.
5. El sistema valida las credenciales.
6. Si son correctas, redirige automáticamente al panel correspondiente a su rol.

### 4.3 Mensajes de Error

| Situación | Mensaje del sistema | Solución |
|-----------|---------------------|----------|
| Campos vacíos | "Por favor, ingresa tu correo electrónico y contraseña." | Completar ambos campos |
| Datos incorrectos | "Usuario o contraseña incorrectos" | Verificar datos ingresados |
| Servidor no disponible | "No se pudo conectar con el servidor." | Contactar al administrador del sistema |

### 4.4 Recuperación de Contraseña

1. Hacer clic en **"¿Olvidaste tu contraseña?"**.
2. El sistema abre la pantalla de recuperación.
3. Seguir las instrucciones en pantalla.
4. Si el problema persiste, contactar al Administrador del sistema.

### 4.5 Cierre de Sesión

Desde cualquier pantalla del sistema:
1. Hacer clic en el **avatar/nombre de usuario** en la esquina superior derecha.
2. Se despliega el menú de usuario.
3. Hacer clic en **"Cerrar Sesión"**.
4. Confirmar en el diálogo emergente.
5. El sistema cierra la sesión y regresa al login.

---

## 5. MÓDULO ADMINISTRADOR

El **Administrador** tiene control total sobre el sistema: gestión de usuarios, auditoría y configuración global.

### 5.1 Panel de Control Maestro

Al iniciar sesión como administrador, se muestra el panel principal con:

- **Estadísticas Globales:** total de proveedores, usuarios activos, documentos próximos a vencer, evaluaciones pendientes.
- **Actividad Reciente:** últimos eventos registrados en el sistema.
- **Accesos Rápidos:** botones directos a funciones frecuentes.

**Menú de Navegación (Sidebar):**

- Inicio
- Gestión de Usuarios → Lista de Usuarios / Nuevo Usuario
- Auditoría del Sistema
- Notificaciones
- Alertas de Vencimiento

### 5.2 Lista de Usuarios

Tabla con todos los usuarios registrados en el sistema.

**Columnas de la tabla:**

| Columna | Descripción |
|---------|-------------|
| Nombre | Nombre completo del usuario |
| Correo | Correo electrónico (usado para iniciar sesión) |
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

### 5.3 Crear Nuevo Usuario

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

### 5.4 Perfil de Usuario

Vista de perfil accesible para cualquier rol (cada usuario ve el suyo propio).

**Funcionalidades:**

- Ver y editar información personal.
- Actualizar foto de perfil.
- Cambiar contraseña.
- Configurar preferencias de notificaciones:

| Preferencia | Descripción |
|------------|-------------|
| Cambios de estado | Notifica cuando cambia el estado de un proveedor |
| Nuevos documentos | Notifica cuando se carga un documento nuevo |
| Vencimientos | Alertas de vencimiento de documentos |
| Noticias del sistema | Novedades generales |

### 5.5 Logs de Auditoría

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

**Filtros disponibles:** por rango de fechas, por tipo de evento, por usuario específico, búsqueda por texto libre.

---

## 6. MÓDULO COMPRADOR

El **Comprador** gestiona el ciclo completo de vida del proveedor: validación, evaluación y seguimiento.

### 6.1 Dashboard del Comprador

**Indicadores clave (KPIs):**

- Proveedores en proceso de validación
- Proveedores aprobados en el mes
- Evaluaciones pendientes
- Alertas de vencimiento activas

### 6.2 Lista de Proveedores

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

**Funcionalidades:** búsqueda por nombre, NIT o estado; filtros por estado; ordenar por cualquier columna; exportar listado.

### 6.3 Perfil del Proveedor

Vista detallada completa de un proveedor.

**Secciones del perfil:**
1. **Datos Principales:** identificación, razón social, tipo de persona, actividad económica (CIIU).
2. **Información de Contacto:** teléfono, correo, página web, dirección.
3. **Información Financiera:** activos, pasivos, patrimonio, ingresos, gastos, banco de referencia.
4. **Documentos:** lista de documentos con estado y fecha de vencimiento.
5. **Historial de Evaluaciones:** evaluaciones previas del proveedor.
6. **Cuestionario LAFT:** respuestas a las 5 preguntas de riesgo.

### 6.4 Validación de Documentos

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

### 6.5 Primera Evaluación

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

### 6.6 Segunda Evaluación

Re-evaluación periódica de proveedores ya aprobados. Sigue la misma estructura que la primera evaluación, aplicada como control de calidad continuo.

### 6.7 Aprobación del Proveedor

Vista de aprobación intermedia del comprador antes de pasar al Oficial de Cumplimiento. Permite revisar y confirmar la recomendación de aprobación.

### 6.8 Historial de Aprobaciones

Registro de todas las decisiones tomadas sobre proveedores: fecha de aprobación/rechazo, usuario que tomó la decisión, justificación, estado anterior y posterior.

### 6.9 Alertas de Vencimiento

Control automático de documentos próximos a vencer.

**Semáforo de alertas:**

| Color | Tiempo Restante | Acción Recomendada |
|:-----:|:--------------:|-------------------|
| 🔴 Rojo | Vencido o menos de 7 días | Notificar al proveedor de inmediato |
| 🟡 Amarillo | Entre 8 y 30 días | Enviar aviso preventivo |
| 🟢 Verde | Más de 30 días | Sin acción requerida |

**Funcionalidades:** filtrar por tipo de documento, exportar reporte de vencimientos, notificar directamente al proveedor desde la alerta.

### 6.10 Reportes

Módulo de análisis con gráficas interactivas.

**Reportes disponibles:**

- Estado general de la cartera de proveedores
- Distribución por tipo de persona (natural/jurídica)
- Proveedores por nivel de riesgo
- Tendencia mensual de aprobaciones
- Documentos por estado de vencimiento

---

## 7. MÓDULO ANALISTA DE RIESGOS

El **Analista de Riesgos** evalúa el nivel de riesgo de cada proveedor bajo el marco SARLAFT/LAFT.

### 7.1 Dashboard del Analista

- Proveedores pendientes de revisión de riesgo
- Estadísticas de niveles de riesgo (alto / medio / bajo)
- Alertas de proveedores de riesgo crítico

### 7.2 Revisión de Riesgo

**Cuestionario LAFT — 5 Preguntas Clave:**

| # | Pregunta |
|---|---------|
| 1 | ¿El proveedor o sus socios figuran en listas restrictivas internacionales? |
| 2 | ¿Opera en sectores de alto riesgo identificados por la UIAF? |
| 3 | ¿Ha sido sancionado por entes de control en los últimos 5 años? |
| 4 | ¿Sus transacciones son consistentes con su actividad económica? |
| 5 | ¿Tiene vínculos conocidos con organizaciones investigadas? |

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

### 7.3 Historial de Evaluaciones de Riesgo

Registro completo de evaluaciones LAFT realizadas con: proveedor evaluado, fecha de evaluación, analista responsable, nivel de riesgo asignado, detalle del cuestionario respondido, observaciones del analista.

**Filtros:** por período, nivel de riesgo, nombre de proveedor.

---

## 8. MÓDULO OFICIAL DE CUMPLIMIENTO

El **Oficial de Cumplimiento** toma la decisión final de aprobación o rechazo del proveedor, revisando los resultados de todas las etapas anteriores.

### 8.1 Dashboard del Oficial

- Proveedores en espera de aprobación final
- Estadísticas de aprobados y rechazados en el período
- Calendario de revisiones programadas

### 8.2 Revisión de Cumplimiento

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

### 8.3 Historial del Oficial

Registro de todas las decisiones tomadas con: fecha y hora de la decisión, proveedor afectado, decisión tomada, justificación, documentos revisados.

---

## 9. MÓDULO PROVEEDOR (PORTAL DE AUTOGESTIÓN)

El proveedor gestiona su propia información, documentos y certificados de manera autónoma.

### 9.1 Dashboard del Proveedor

Panel de control personal que muestra:
- **Estado actual** de vinculación (Pendiente / En Evaluación / Aprobado / Rechazado)
- **Puntaje** obtenido en la última evaluación
- **Documentos pendientes** por cargar o actualizar
- **Próximos vencimientos** de documentos
- **Notificaciones** recientes del proceso

**Accesos rápidos:** Actualizar datos · Cargar documentos · Descargar certificado

### 9.2 Formulario de Registro/Actualización

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

**Sección 5 — Representante Legal:** para personas jurídicas.

**Sección 6 — Socios/Accionistas:** con porcentaje de participación de cada uno.

**Pasos para actualizar el formulario:**
1. Navegar a **"Actualizar Mi Información"**.
2. El formulario se precarga con los datos actuales.
3. Modificar los campos necesarios.
4. Hacer clic en **"Guardar Cambios"** al final de cada sección.

### 9.3 Carga de Documentos

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

### 9.4 Certificado de Vinculación

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

### 9.5 Historial de Calificaciones

- Fecha de cada evaluación
- Puntaje obtenido
- Evaluador asignado
- Observaciones recibidas
- Evolución del puntaje en el tiempo (gráfica)

---

## 10. MÓDULO DE NOTIFICACIONES Y ALERTAS

### 10.1 Centro de Notificaciones

Disponible para todos los roles. Centraliza todas las alertas y mensajes del sistema.

**Tipos de notificaciones:**

| Tipo | Descripción | A quién llega |
|------|-------------|:-------------:|
| Cambio de Estado | Estado del proveedor modificado | Comprador, Proveedor |
| Documento Nuevo | El proveedor cargó un documento | Comprador |
| Próximo Vencimiento | Documento vence en menos de 30 días | Comprador, Admin |
| Aprobación/Rechazo | Decisión del Oficial | Proveedor, Comprador |
| Nueva Evaluación | Evaluación asignada | Analista, Oficial |

### 10.2 Configurar Preferencias de Notificaciones

Desde **Mi Perfil → Configuración de Notificaciones**, usar los interruptores para activar/desactivar cada tipo.

---

## 11. PREGUNTAS FRECUENTES (FAQ)

**P: ¿Qué hago si olvidé mi contraseña?**
> Clic en "¿Olvidaste tu contraseña?" en el login. Si persiste el problema, contactar al Administrador.

**P: ¿Por qué no puedo acceder al sistema?**
> Verificar que: (1) el sistema esté disponible, (2) JavaScript esté habilitado en el navegador, (3) la dirección de acceso sea correcta.

**P: ¿Por qué el menú lateral aparece vacío?**
> El menú se carga según su rol. Verificar la conexión con el servidor o contactar al Administrador.

**P: ¿Cuánto tiempo dura la sesión?**
> La sesión se mantiene mientras el navegador esté abierto. Al cerrar, expira. La opción "Recordarme" en el login ayuda a mantenerla.

**P: ¿Cómo sé el estado de mi proveedor?**
> Desde el Dashboard del Proveedor (si es proveedor) o la Lista de Proveedores (si es comprador). El estado se muestra como una etiqueta de color en tiempo real.

**P: ¿Puedo exportar los reportes?**
> Sí. En el módulo de Reportes y en la Lista de Proveedores existen botones de exportación.

**P: ¿Qué formato deben tener los documentos al cargarlos?**
> Se recomienda **PDF**. También se pueden aceptar imágenes (JPG, PNG) según la configuración del sistema.

**P: ¿Cómo se calcula el puntaje del proveedor?**
> Es una ponderación de: Capacidad Financiera (25%) + Experiencia (20%) + Calidad (25%) + Cumplimiento Documental (20%) + Referencias Comerciales (10%).

**P: ¿Puedo tener dos sesiones abiertas al mismo tiempo?**
> No se recomienda, ya que puede generar conflictos si abre el sistema en varias pestañas o navegadores distintos a la vez.

---

## 12. GLOSARIO DE TÉRMINOS

**ADSO** — *Análisis y Desarrollo de Software.* Programa de formación tecnológica del SENA orientado al diseño, construcción y mantenimiento de aplicaciones de software.

**Analista de Riesgos** — Usuario del sistema encargado de evaluar el perfil de riesgo de los proveedores mediante el análisis SARLAFT/LAFT.

**Auditoría** — Registro cronológico de todas las acciones importantes del sistema para garantizar trazabilidad y cumplimiento regulatorio.

**Badge** — Etiqueta visual pequeña y colorida que indica el estado de un elemento. Ejemplo: badge verde "Aprobado", badge rojo "Rechazado".

**CIIU** — *Clasificación Industrial Internacional Uniforme.* Código que identifica la actividad económica principal de una empresa.

**Comprador** — Rol encargado de gestionar el ciclo completo de vida del proveedor: validación documental, evaluación técnica, reportes y alertas.

**Dashboard** — Página principal de un rol que presenta indicadores clave, gráficas y accesos rápidos a las funciones más relevantes.

**Debida Diligencia** — Proceso exhaustivo de verificación de un proveedor antes de establecer relación comercial. Incluye validación documental, financiera y de riesgo LAFT.

**Estado del Proveedor** — Situación actual del proveedor en el proceso: Pendiente, En Validación, En Evaluación, Aprobado, Rechazado, Suspendido.

**GRC (Governance, Risk and Compliance)** — Marco de gestión empresarial que integra Gobernanza, Gestión de Riesgos y Cumplimiento Regulatorio. Es el concepto central de Parere GRC.

**Gobernanza** — Conjunto de políticas y controles que garantizan que una organización opera de manera ética, transparente y alineada con sus objetivos estratégicos.

**KPI (Key Performance Indicator)** — Indicador clave de rendimiento. Son las tarjetas numéricas en los dashboards que muestran métricas relevantes.

**LAFT** — *Lavado de Activos y Financiación del Terrorismo.* Actividades ilegales que buscan dar apariencia legal a dinero ilícito. El sistema incluye un cuestionario con 5 preguntas para evaluar el riesgo de cada proveedor.

**Modal** — Ventana emergente que aparece sobre la página para solicitar confirmación o mostrar información.

**NIT** — *Número de Identificación Tributaria.* Identificador fiscal colombiano asignado por la DIAN a personas jurídicas (empresas), con un dígito de verificación al final.

**Oficial de Cumplimiento** — Usuario responsable de la revisión legal final y la decisión de aprobación o rechazo de un proveedor.

**Parere GRC** — Nombre del sistema. Del latín *parere* ("satisfacer, cumplir"). Plataforma de Gobernanza, Riesgo y Cumplimiento del proyecto ADSO del SENA.

**Pasivos** — Deudas y obligaciones financieras de una empresa.

**Patrimonio** — Valor neto de una empresa: Activos menos Pasivos. Indica la solidez financiera real del proveedor.

**Persona Jurídica** — Empresa o sociedad legalmente constituida, con personería jurídica propia. Se identifica con NIT.

**Persona Natural** — Individuo que actúa en nombre propio para actividades económicas. Se identifica con CC, CE, Pasaporte, etc.

**Proveedor** — Empresa o persona natural que ofrece bienes o servicios a la organización. También es un rol de usuario que accede al portal de autogestión.

**RUT** — *Registro Único Tributario.* Documento emitido por la DIAN que identifica a personas y entidades que realizan actividades económicas en Colombia.

**Rol** — Define el nivel de acceso y las funcionalidades disponibles para cada usuario. El sistema tiene 5 roles con menús y paneles distintos.

**SARLAFT** — *Sistema de Administración del Riesgo de Lavado de Activos y Financiación del Terrorismo.* Marco regulatorio colombiano para prevenir el uso de entidades en actividades ilícitas.

**SENA** — *Servicio Nacional de Aprendizaje.* Institución pública colombiana que provee formación técnica y tecnológica gratuita.

**Sesión** — Período en que un usuario está autenticado en el sistema.

**Sidebar** — Barra de navegación lateral del sistema que contiene el menú de opciones.

**Trazabilidad** — Capacidad de rastrear el historial completo de cambios y acciones sobre un registro, garantizada por el módulo de Auditoría.

**UIAF** — *Unidad de Información y Análisis Financiero.* Entidad colombiana que recibe y analiza información para combatir el lavado de activos y financiación del terrorismo.

**Validación Documental** — Proceso por el cual el Comprador verifica que los documentos del proveedor sean auténticos, vigentes y completos antes de continuar con la evaluación.

---

## INFORMACIÓN DEL PROYECTO

| Dato | Descripción |
|------|-------------|
| **Nombre** | Parere GRC |
| **Programa** | Análisis y Desarrollo de Software (ADSO) |
| **Institución** | SENA |
| **Año** | 2025 – 2026 |
| **Autores** | Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz |
| **Correos** | adbarrios87@gmail.com · espriggan123@gmail.com |

---

*© 2026 — Parere GRC. Manual de usuario elaborado para el programa de formación ADSO — SENA.*
