# Parere GRC - Sistema de Gestión de Riesgo y Proveedores (Proyecto ADSO)

## 📄 Descripción del Proyecto
**Parere GRC** es una plataforma integral de **Gobernanza, Riesgo y Cumplimiento** diseñada para optimizar y asegurar el ciclo de vida de los proveedores. El sistema facilita la debida diligencia, la evaluación de riesgos (SARLAFT/LAFT) y el seguimiento documental continuo, asegurando que la cadena de suministro cumpla con los más altos estándares de integridad y calidad.

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
├── login.html                  # Acceso principal al sistema
├── diseño.md                   # Estándares de diseño y UI/UX
├── src/
│   ├── assets/                 # Recursos gráficos (logos, iconos)
│   ├── css/                    # Hojas de estilo modulares
│   ├── db/                     # Scripts DDL y DML de la base de datos
│   ├── documents/              # Contexto técnico y documentación del proyecto
│   ├── js/                     # Lógica de navegación y componentes dinámicos
│   └── sheets/                 # Vistas funcionales por rol (Admin, Comprador, Riesgos, Proveedor)
```

## ✨ Funcionalidades Principales

### 1. Centro de Control Maestro (Administrador)
*   **Dashboard Global**: Estadísticas de cumplimiento, riesgos críticos y actividad del sistema.
*   **Auditoría**: Registros detallados de eventos y acciones de usuarios (`admin_audit_logs.html`).
*   **Gestión de Usuarios**: Administración completa de roles y permisos.

### 2. Módulo de Compras y Evaluación
*   **Debida Diligencia**: Validación exhaustiva de documentación técnica y legal.
*   **Ciclo de Evaluación**: Flujos de primera evaluación, re-evaluación y perfiles detallados.
*   **Alertas Tempranas**: Control automatizado de vencimientos documentales (`expiration_alerts.html`).

### 3. Gestión de Riesgos y Cumplimiento
*   **Análisis SARLAFT**: Herramientas para analistas de riesgos y oficiales de cumplimiento.
*   **Historial de Riesgo**: Trazabilidad de evaluaciones históricas de idoneidad.

### 4. Portal de Autogestión (Proveedor)
*   **Perfil Maestro**: Panel único para visualización de estado, puntajes y tareas pendientes.
*   **Gestión Documental**: Carga y actualización de documentos obligatorios.
*   **Certificación**: Generación autónoma de certificados de vinculación comercial.

## 💾 Base de Datos
El proyecto utiliza el esquema `parere_grc` que integra:
*   **Maestros**: Países, monedas, tipos de documentos, catálogos de personas.
*   **Core**: Tablas de proveedores, contactos, sucursales y repositorio documental.
*   **Auditoría**: Historial de cambios, estados de evaluación y logs de seguridad.

## 🚀 Estado de Implementación
*   ✅ **Frontend (GUI)**: Interfaces de alta fidelidad 100% funcionales a nivel de navegación.
*   ✅ **Diseño UI/UX**: Sistema de diseño estandarizado y documentado.
*   ✅ **Base de Datos**: Esquema relacional completo diseñado y probado.
*   🔄 **Backend**: En fase de planificación para integración con lógica de negocio.

---
*Proyecto desarrollado para el programa de formación ADSO - SENA.*
