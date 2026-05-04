# Estándares de Diseño y Estilos - Proyecto Parere

Este documento describe los estándares de estilos y diseño utilizados en el proyecto Parere para mantener una estética profesional y de alta calidad en todas las interfaces de usuario (GUI). Los estándares se basan en el archivo `template.css`, que centraliza el sistema de diseño.

## 1. Introducción

El objetivo es asegurar una experiencia visual coherente y de alta calidad. El diseño utiliza una paleta de colores desaturada con acentos cálidos y una tipografía moderna.

- **Archivo base**: `src/css/template.css`
- **Variables CSS**: Definidas en `:root` utilizando HSL y LAB para mayor precisión cromática.
- **Fuentes**: 'Poppins' (fuente principal) y 'Open Sans'.
- **Aesthetics**: Minimalismo, sombras suaves y micro-animaciones.

## 2. Paleta de Colores (Design Tokens)

| Variable | Valor | Descripción |
|----------|-------|-------------|
| `--background-main` | lab(97% ...) | Fondo base crema muy claro (casi blanco) |
| `--background-card` | #FFFFFF | Fondo de tarjetas y secciones |
| `--background-header` | #FFFFFF | Barra superior de usuario |
| `--background-table-header`| #F2F2F2 | Fondo de encabezados de tabla (gris sutil) |
| `--background-navbar_btn` | #1E1E2F | Azul oscuro profundo para sidebar y botones |
| `--text-primary` | #333333 | Texto principal (gris grafito) |
| `--text-secondary` | #666666 | Texto complementario |
| `--text-muted` | #999999 | Texto de menor relevancia |
| `--text-inverse` | #FFFFFF | Texto sobre fondos oscuros |
| `--accent-color` | #D4A373 | Tono arena (bronce) para acentos y botones |
| `--primary-color` | #D4A373 | Color primario unificado al acento |
| `--border-color` | #E0E0E0 | Bordes sutiles |
| `--border-light` | #F0F0F0 | Bordes casi invisibles |
| `--shadow-card` | 0 4px 12px rgba(0,0,0,0.05) | Sombra suave para elevación |

## 3. Estados y Badges (Pastel Desaturados)

| Estado | Fondo (BG) | Texto |
|--------|------------|-------|
| Success | #E8F5E9 | #2E7D32 |
| Warning | #FFF8E1 | #D3BF0C |
| Danger | #FFEBEE | #C62828 |
| Info | #E3F2FD | #1565C0 |

## 4. Tipografía

- **Familia**: 'Poppins', sans-serif.
- **Encabezados**:
  - h1: 28px (Bold 600)
  - h2: 20px
  - h3: 16px
- **Cuerpo de texto**: 14px

## 5. Layout Maestro

- **Sidebar (Navegación)**: Ancho de 200px. Fondo `--background-navbar_btn` (#1E1E2F).
  - Íconos: Tamaño 18px, color `--sliderbar-icons`.
  - Items: Espaciado de 12px vertical, hover con transparencia blanca (0.1).
- **Contenedor (.content)**: Ocupa el resto del ancho, `height: 100vh`, `overflow: hidden`.
- **Header (.user-section)**: Fondo blanco, sombra sutil inferior, alineación a la derecha.
- **Área Principal (main)**: Scroll independiente (`overflow-y: auto`), espaciado interno generoso.

## 6. Componentes Estándar

### 6.1 Botones (.btn)
- **General**: Fondo oscuro (#1E1E2F), texto blanco. Al hacer hover, cambian a fondo gris claro con texto color acento.
- **Efectos**: Micro-animación de hundimiento (`translateY(2px)`) al hacer clic.
- **Especiales**: Botones de tipo `submit` y `reset` siguen el mismo patrón de diseño consistente.

### 6.2 Tablas (.general-table)
- **Header**: Texto en mayúsculas, espaciado de 0.5px entre letras, fondo gris muy claro.
- **Filas**: Intercalado sutil (`--background-table-odd`). Efecto hover en fila.
- **Celdas**: Padding de 16px para mayor legibilidad (aire).

### 6.3 Cuadro de Búsqueda (.search-box)
- **Diseño**: Icono de lupa posicionado de forma absoluta a la izquierda.
- **Input**: Bordes redondeados (8px), fondo gris muy claro, transición de borde a color acento en focus.

### 6.4 Menús Desplegables (.dropdown-menu)
- **Posición**: Aparece a la derecha del sidebar al hacer hover.
- **Aesthetics**: Sombras profundas (0 8px 24px), bordes muy redondeados (12px), items con hover gris claro.

## 7. Inventario de Vistas Actuales

El proyecto cuenta con las siguientes vistas funcionales:

### 7.1 Administración y Usuarios
- `admin_dashboard.html`: Panel de control maestro.
- `admin_audit_logs.html`: Trazabilidad de eventos del sistema.
- `user_list.html`: Gestión de usuarios.
- `user_new.html`: Registro de nuevos usuarios.
- `configuration.html`: Ajustes de cuenta y sistema.

### 7.2 Comprador y Gestión de Proveedores
- `buyer_dashboard.html`: Vista principal del comprador.
- `buyer_supplier_list.html`: Maestro de proveedores.
- `buyer_supplier_profile.html`: Detalle de perfil de proveedor.
- `buyer_reports.html`: Reportes y analítica avanzada.
- `expiration_alerts.html`: Control de vencimientos documentales.

### 7.3 Riesgos y Cumplimiento
- `risk_dashboard.html`: Dashboard para analista de riesgos.
- `compliance_officer_dashboard.html`: Vista del oficial de cumplimiento.
- `risk_historial.html`: Histórico de evaluaciones de riesgo.
- `compliance_officer_history.html`: Histórico de aprobaciones legales.

### 7.4 Portal del Proveedor
- `supplier_dashboard.html`: Panel de control para el proveedor.
- `supplier_form.html`: Formulario de actualización de datos.
- `supplier_upload_documents.html`: Carga de documentación.

---
*Este documento debe actualizarse cada vez que se modifiquen los tokens de diseño en `template.css`.*
