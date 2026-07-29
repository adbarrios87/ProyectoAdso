# Parere GRC - Sistema de Gestión de Riesgo y Proveedores (Proyecto ADSO)

## 📄 Descripción del Proyecto
**Parere GRC** es una plataforma web de **Gobernanza, Riesgo y Cumplimiento** diseñada bajo el objetivo general de **construir un sistema de información web para asegurar el ciclo de vida de los proveedores mediante un flujo estructurado de roles**. El sistema permite centralizar el registro del proveedor, la validación de información, la escalación de análisis de riesgos y el monitoreo documental, asegurando un control riguroso y una segregación de funciones adecuada en cada etapa.

## 🎯 Objetivos del Proyecto

### Objetivo General
Construir un sistema de información web para asegurar el ciclo de vida de los proveedores mediante un flujo estructurado de roles.

### Objetivos Específicos
*   **Diseñar** la arquitectura técnica del sistema, el modelo de datos relacional y las interfaces de usuario para estructurar de manera óptima los requerimientos funcionales del ciclo de vida del proveedor.
*   **Desarrollar** los componentes interactivos del frontend y los servicios del backend para implementar de forma segura las funcionalidades de registro, validación y control de acceso del sistema.
*   **Realizar** pruebas de integración y validación del software para verificar la correcta comunicación de los datos, la transición fluida del flujo de aprobación y el cumplimiento de los estándares de calidad.

## 👥 Integrantes del Equipo
*   **Adriana Gineth Barrios Aponte**
    *   Rol: Desarrollador / Analista
    *   Correo: adbarrios87@gmail.com
*   **Juan Carlos Cadena Muñoz**
    *   Rol: Desarrollador / Analista
    *   Correo: espriggan123@gmail.com

## 🛠️ Tecnologías Utilizadas
*   **Frontend**: HTML5, JavaScript (ES6+), CSS3 Nativo.
*   **Estilos**: Sistema de diseño basado en variables CSS (`template.css`)y micro-animaciones.
*   **Recursos**: Font Awesome 6, Google Fonts (Poppins, Open Sans).
*   **Base de Datos**: MySQL (Modelo relacional normalizado con soporte para auditoría).
*   **Control de Versiones**: Git / GitHub.

## 📂 Estructura del Proyecto
```
/
├── Backend/                    # Código fuente del servidor (API REST Spring Boot)
├── Documentos/                 # Documentación y manuales (diagramas, requerimientos, historias y manuales de usuario)
├── Frontend/                   # Código de la interfaz de usuario
│   ├── assets/                 # Recursos gráficos (logos, iconos)
│   ├── css/                    # Hojas de estilo modulares
│   ├── db/                     # Scripts SQL (creación de tablas, vistas, inserts)
│   ├── js/                     # Lógica de componentes y conexión a la API
│   └── sheets/                 # Páginas HTML de las vistas por rol (Admin, Proveedor, Comprador, etc.)
├── login.html                  # Acceso principal al sistema (Inicio de sesión)
├── plan_endpoints.md           # Plan de integración y mapeo de endpoints
└── README.md                   # Documentación general de este repositorio
```

## 🚀 Instalación y Ejecución Local

### 1. Clonar el repositorio
```bash
git clone <URL_DEL_REPOSITORIO>
cd ProyectoAdsoLocal
```

### 2. Configurar variables de entorno (.env)
El archivo `.env` contiene credenciales confidenciales y no está incluido en el repositorio. Solicita el archivo al administrador del proyecto y colócalo en la siguiente ruta:
`Backend/proveedores/proveedores/.env`

*(Nota: En la misma carpeta tienes el archivo `.env.example` como referencia).*

### 3. Ejecutar el Backend (Spring Boot)
Asegúrate de tener instalado Java 17 o superior.
```bash
cd Backend/proveedores/proveedores
```
* **En Windows:**
  ```bash
  ./mvnw.cmd spring-boot:run
  ```
* **En Linux/macOS:**
  ```bash
  ./mvnw spring-boot:run
  ```

### 4. Ejecutar el Frontend
El frontend está desarrollado en tecnologías nativas (HTML/CSS/JS). Para visualizarlo localmente:
1. Abre el archivo `login.html` directamente en tu navegador favorito, o bien
2. Levanta un servidor local (por ejemplo, usando la extensión "Live Server" de VS Code o ejecutando `npx serve .` en la raíz del proyecto).

## ✨ Funcionalidades Principales

### 1. Centro de Control Maestro (Administrador)
*   **Dashboard Global**: Estadísticas de cumplimiento, riesgos críticos y actividad del sistema.
*   **Auditoría**: Registros detallados de eventos y acciones de usuarios (`admin_audit_logs.html`).
*   **Gestión de Usuarios**: Administración completa de roles y permisos.

### 2. Módulo de Compras y Evaluación
*   **Debida Diligencia**: Validación exhaustiva de documentación técnica y legal.
*   **Ciclo de Evaluación**: Flujos de primera evaluación, re-evaluación y perfiles detallados.
*   **Perfil de Proveedor Conectado**: Consulta dinámica en tiempo real de la información general del proveedor, contactos, representantes legales, socios/accionistas, métodos de pago unificados con información bancaria, documentos, calificaciones y riesgos.
*   **Alertas Tempranas**: Control automatizado de vencimientos documentales (`expiration_alerts.html`).

### 3. Gestión de Riesgos y Cumplimiento
*   **Análisis SARLAFT**: Herramientas para analistas de riesgos y oficiales de cumplimiento.
*   **Historial de Riesgo**: Trazabilidad de evaluaciones históricas de idoneidad.

### 4. Portal de Autogestión (Proveedor)
*   **Perfil Maestro**: Panel único para visualización de estado, puntajes y tareas pendientes.
*   **Gestión Documental**: Carga y actualización de documentos obligatorios.
*   **Certificación**: Generación autónoma de certificados de vinculación comercial.


## 🎨 Sistema de Diseño y Estética 

El proyecto sigue un sistema de diseño estandarizado y centralizado en [template.css](file:///c:/Users/camilo.nustes/Downloads/ProyectoAdsoLocal/Frontend/css/template.css), enfocado en una experiencia visual profesional, limpia, interactiva (UI/UX) y moderna.

### 1. Variables CSS (Design Tokens)

| Variable | Valor | Descripción |
| :--- | :--- | :--- |
| `--background-main` | `lab(97% ...)` | Fondo base crema muy claro |
| `--background-card` | `#FFFFFF` | Fondo de tarjetas y secciones |
| `--background-header` | `#FFFFFF` | Barra superior de usuario |
| `--background-table-header` | `#F2F2F2` | Fondo de encabezados de tabla (gris sutil) |
| `--background-navbar_btn` | `#1E1E2F` | Azul oscuro profundo para sidebar y botones |
| `--text-primary` | `#333333` | Texto principal (gris grafito) |
| `--text-secondary` | `#666666` | Texto complementario |
| `--text-muted` | `#999999` | Texto de menor relevancia |
| `--text-inverse` | `#FFFFFF` | Texto sobre fondos oscuros |
| `--accent-color` / `--primary-color` | `#D4A373` | Tono arena (bronce) para acentos y botones |
| `--border-color` | `#E0E0E0` | Bordes sutiles |
| `--border-light` | `#F0F0F0` | Bordes casi invisibles |
| `--shadow-card` | `0 4px 12px rgba(0,0,0,0.05)` | Sombra suave para elevación de componentes |

### 2. Estados y Badges (Pastel Desaturados)

| Estado | Fondo (BG) | Texto | Uso |
| :--- | :--- | :--- | :--- |
| **Success** (Éxito) | `#E8F5E9` | `#2E7D32` | Aprobado, Validado, Activo |
| **Warning** (Advertencia) | `#FFF8E1` | `#D3BF0C` | Pendiente de Revisión |
| **Danger** (Peligro) | `#FFEBEE` | `#C62828` | Rechazado, Vencido, Alto Riesgo |
| **Info** (Información) | `#E3F2FD` | `#1565C0` | En Proceso, Notificaciones |

### 3. Tipografía y Jerarquía

*   **Fuentes de Google Fonts**: `'Poppins'` (principal para encabezados) y `'Open Sans'` (para cuerpo de texto).
*   **Jerarquía de Tamaños**:
    *   `h1`: `28px` (Bold 600)
    *   `h2`: `20px`
    *   `h3`: `16px`
    *   Cuerpo de texto: `14px`

### 4. Layout Maestro y Componentes

*   **Sidebar (Navegación Lateral)**: Ancho de `200px`, fondo `#1E1E2F`. Íconos unificados con hover interactivo (transparencia de blanco al `10%`).
*   **Contenedor Principal**: Ocupa `100vh` con scroll independiente (`overflow-y: auto`) para evitar scrollbars dobles indeseados en el navegador.
*   **Botones (`.btn`)**: Fondo oscuro (`#1E1E2F`) y texto blanco por defecto. Al hacer hover, cambian a fondo gris claro con texto en color acento (`#D4A373`). Cuentan con una micro-animación de hundimiento interactivo (`translateY(2px)`) al hacer clic (`:active`).
*   **Tablas (`.general-table`)**: Celdas con padding de `16px` para mayor legibilidad y aire en el diseño, con colores intercalados de fila (`--background-table-odd`).
*   **Bordes Redondeados (Borders)**: Bordes de `8px` para inputs y botones, y de `12px` para menús desplegables y modales.

## 💾 Base de Datos
El proyecto utiliza el esquema `parere_grc` que integra:
*   **Maestros**: Países, monedas, tipos de documentos, catálogos de personas.
*   **Core**: Tablas de proveedores, contactos, sucursales y repositorio documental.
*   **Auditoría & Bitácora**: Historial de cambios, estados de evaluación y logs de seguridad (con el registro unificado de bitácora en la tabla `validacion_final`, habiendo depreciado y eliminado la tabla `evaluacion_riesgos`).



---
*Proyecto desarrollado para el programa de formación ADSO - SENA.*
