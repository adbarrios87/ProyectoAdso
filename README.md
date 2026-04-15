# Golden Olds - Sistema de Gestión de Proveedores (Proyecto ADSO)

## 📄 Descripción del Proyecto
Golden Olds es un sistema de información basado en web diseñado para integrar y optimizar las etapas de **selección, evaluación y seguimiento de la documentación de proveedores**. El sistema facilita la gestión eficiente de la relación con proveedores, asegurando el cumplimiento normativo (ISO9001, SARLAFT, etc.) y la trazabilidad de la información.

## 👥 Integrantes del Equipo
*   **Adriana Gineth Barrios Aponte**
    *   Rol: Desarrollador / Analista
    *   Correo: adbarrios87@gmail.com
*   **Juan Carlos Cadena Muñoz**
    *   Rol: Desarrollador / Analista
    *   Correo: espriggan123@gmail.com

## 🛠️ Tecnologías Utilizadas
*   **Frontend**: HTML5, CSS3 (Diseño responsivo y personalizado).
*   **Estilos**: CSS nativo (`src/css`), Font Awesome (Iconos), Google Fonts (Open Sans, Poppins).
*   **Base de Datos**: MySQL (Esquema relacional normalizado).
*   **Control de Versiones**: Git.

## 📂 Estructura del Proyecto
El proyecto está organizado de la siguiente manera:

```
/
├── login.html                  # Página de inicio de sesión principal
├── Readme.txt                  # Archivo de información original
├── src/
│   ├── assets/                 # Imágenes y recursos estáticos (logos, fondos)
│   ├── css/                    # Hojas de estilo (globales y específicas por página)
│   ├── db/                     # Scripts SQL para la base de datos
│   │   ├── create_tables.sql   # Definición del esquema (DDL)
│   │   ├── insert_data.sql     # Datos semilla y de prueba (DML)
│   │   └── vistas.sql          # Vistas para reportes y consultas
│   └── sheets/                 # Vistas HTML del aplicativo
│       ├── admin_dashboard.html           # Panel del Administrador
│       ├── supplier_dashboard.html        # Panel del Proveedor
│       ├── buyer_*.html                   # Flujos de compras y evaluación
│       ├── risk_*.html                    # Gestión de riesgos y cumplimiento
│       └── user_*.html                    # Gestión de usuarios
```

## ✨ Funcionalidades Principales

### 1. Módulo del Proveedor
*   **Autogestión**: Actualización de información básica y carga de documentos (`supplier_upload_documents.html`).
*   **Certificaciones**: Generación de certificaciones comerciales (`supplier_certification.html`).
*   **Historial**: Visualización de histórico de calificaciones y estado.

### 2. Módulo del Comprador / Administrador
*   **Gestión de Proveedores**: 
    *   Búsqueda y listado de proveedores.
    *   Validación de documentación (`buyer_supplier_validation.html`).
    *   Evaluaciones de desempeño (Primera evaluación y Re-evaluación).
*   **Gestión de Riesgos**:
    *   Seguimiento por analista de riesgos y oficial de cumplimiento.
    *   Validaciones en listas restrictivas y prevención de lavado de activos.
*   **Administración del Sistema**:
    *   Gestión de usuarios y roles.
    *   Configuración de parámetros y notificaciones.
    *   Reportes y auditoría.

## 💾 Base de Datos
El proyecto cuenta con una base de datos robusta (`golden_odds`) que incluye:
*   **Catálogos Maestros**: Tipos de identificación, personas, países, monedas, etc.
*   **Tablas Transaccionales**: Usuarios, Proveedores, Contactos, Documentos (con soporte para almacenamiento local/S3/DB).
*   **Seguridad y Auditoría**: Tablas para historial de usuario, evaluaciones de riesgo y logs de cambios.

## 🚀 Estado Actual
*   ✅ **Frontend**: Prototipos de alta fidelidad implementados en HTML/CSS con navegación funcional.
*   ✅ **Base de Datos**: Esquema completo diseñado (`create_tables.sql`) y script de datos poblado.
*   🔄 **Backend/Lógica**: Pendiente de integración (actualmente la lógica es visual a través de navegación estática).

## 📚 Documentación Asociada
El proyecto se basa en una documentación formal de ingeniería de software que incluye:
*   Acta de definición.
*   Especificación de Requerimientos (SRS).
*   Diagramas UML (Casos de uso, Clases, Secuencia, Despliegue).
*   Modelo Entidad-Relación (MER).
