# Estándares de Diseño y Estilos - Proyecto Golden

Este documento describe los estándares de estilos y diseño utilizados en el proyecto Golden para mantener una estética consistente en todas las interfaces de usuario (GUI). Los estándares se basan en el archivo `template.css`, que define variables CSS globales y estilos reutilizables.

## 1. Introducción

El objetivo es asegurar una experiencia visual coherente, profesional y accesible. Todos los componentes deben adherirse a estos estándares para evitar inconsistencias.

- **Archivo base**: `src/css/template.css`
- **Variables CSS**: Definidas en `:root` para facilitar cambios globales.
- **Fuentes**: 'Poppins' y 'Open Sans' (sans-serif como respaldo).
- **Enfoque**: Diseño limpio, minimalista con colores corporativos.

## 2. Paleta de Colores

Utiliza las variables CSS definidas para mantener consistencia. No uses colores hardcodeados.

| Variable | Valor | Descripción |
|----------|-------|-------------|
| `--background-main` | #f8f9fa | Fondo base general |
| `--background-card` | #ffffff | Fondo de tarjetas y secciones |
| `--background-header` | #e6e6e6 | Encabezados o barras superiores |
| `--background-table-header` | #606c38 | Encabezado de tablas |
| `--background-table-even` | #f1f1f1 | Filas pares de tablas |
| `--background-table-odd` | #ffffff | Filas impares de tablas |
| `--text-primary` | #212529 | Texto principal |
| `--text-secondary` | #555555 | Texto complementario |
| `--text-muted` | #777777 | Texto menos relevante |
| `--text-inverse` | #ffffff | Texto sobre fondos oscuros |
| `--text-color` | #333 | Texto general alternativo |
| `--primary-color` | #606c38 | Verde corporativo principal |
| `--primary-color-hover` | #828381 | Versión más oscura para hover |
| `--accent-color` | #c54b1c | Tono acento / advertencia suave |
| `--border-color` | #dcdcdc | Bordes sutiles y divisores |
| `--btn-background` | #ddd | Fondo de botones por defecto |
| `--btn-background-hover` | #d4663a | Fondo de botones al pasar el mouse |
| `--btn-text-color` | #000 | Texto de botones normal |
| `--btn-text-hover` | #fff | Texto de botones al pasar el mouse |
| `--shadow-light` | 0 0 4px rgba(0, 0, 0, 0.1) | Sombras ligeras |
| `--shadow-medium` | 0 2px 6px rgba(0, 0, 0, 0.15) | Sombras medias |
| `--shadow-card` | 0 2px 8px rgba(0,0,0,0.1) | Sombras para tarjetas |
| `--icon-color` | #556b2f | Color de iconos |

## 3. Tipografía

- **Familias de fuente**: 'Poppins', 'Open Sans', sans-serif.
- **Tamaños de encabezados**:
  - h1: 28px
  - h2: 20px
  - h3: 16px
  - h4: 12px
  - h5: 10px
  - h6: 8px
- **Párrafos**: 14px
- **Texto general**: 14px (en main y elementos comunes)

## 4. Layout General

- **Body**: Fondo `--background-main`, color `--text-primary`, display flex, height 100vh, overflow hidden.
- **Sidebar**: Ancho 175px, fondo #5a6230, color blanco, flex-direction column, height 100vh.
  - Logo: Altura 45px.
  - Menús: Padding 3px 20px, hover background #4d5524.
- **Content**: Flex 1, display flex column, height 100vh, overflow hidden.
- **User-section**: Flex-shrink 0, justify-content flex-end, background `--background-header`, padding 20px.
  - User-info: Text-align right, font-size 12px.
  - User-img: 50x50px, border-radius 50%, border 2px solid #c9d7c4.
- **Main**: Flex-grow 1, overflow-y auto, overflow-x hidden, font-size 14px.
- **Footer**: Flex-shrink 0, height 40px, text-align center, padding 10px 0, font-size 12px, italic.

## 5. Botones

- **Clase general .btn**:
  - Background: `--btn-background`
  - Color: `--btn-text-color`
  - Padding: 10px 18px
  - Border-radius: 8px
  - Cursor: pointer
  - Transition: background-color 0.3s, transform 0.1s, filter 0.1s
  - Box-shadow: `--shadow-light`
  - Hover: Background `--btn-background-hover`
  - Active: Transform translateY(2px), filter brightness(0.85)

- **Botones de reset (.btn-reset, button[type="reset"])**:
  - Background: `--btn-background-hover`
  - Color: #fff
  - Hover: Filter brightness(0.9)
  - Active: Transform translateY(2px), filter brightness(0.8)

## 6. Tablas

- **Contenedor .table-container**: Width 100%, overflow-x auto, margin-top 15px, border-radius 8px, border 1px solid `--border-color`, box-shadow `--shadow-card`.
- **Tabla .general-table**:
  - Width 100%, border-collapse collapse, box-shadow `--shadow-card`, font-size 14px.
  - Th: Background `--background-table-header`, color `--text-inverse`, padding 12px 10px, font-weight bold, text-align center, sticky top 0.
  - Td: Padding 12px 10px, text-align left.
  - Tr:nth-child(even): Background `--background-table-even`.
  - Columnas numéricas: Text-align center (clase .number).

## 7. Menús Desplegables

- **.menu-item**: Position relative.
- **.dropdown-menu**: Display none, position absolute left 160px top 0, background `--background-main`, padding 8px 0, border-radius 6px, box-shadow 0 4px 8px rgba(0,0,0,0.25), width 180px.
  - Li: Padding 8px 15px, text-align left.
  - A: Color #000, text-decoration none, font-size 13px.
  - A:hover: Color `--text-inverse`.
- Hover en .menu-item.dropdown muestra .dropdown-menu.

## 8. Badges

- **.badge**: Padding 4px 10px, border-radius 12px, font-size 14px, display inline-block, text-align center.
  - .success: Background #e6f5ea, color #2e7d32
  - .warning: Background #f2dec2, color #f0930f
  - .danger: Background #fde8e8, color #b71c1c
  - .info: Background #2980b9, color #fff

## 9. Botones de Icono

- **.actions**: Display flex, justify-content center, gap 8px, height 100%, align-items center.
- **.icon-btn**: Background none, border none, cursor pointer, font-size 14px, color `--text-primary`, transition color 0.2s.
  - Hover: Color `--accent-color`.

## 10. Iconos

- Color: `--icon-color` (#556b2f)
- Tamaño: Ajustar según contexto (ej. 20px en sidebar).

## 11. Recomendaciones Generales

- **Consistencia**: Siempre usa variables CSS en lugar de valores hardcodeados.
- **Accesibilidad**: Asegura contrastes adecuados, especialmente en texto sobre fondos.
- **Responsive**: Considera media queries para dispositivos móviles, aunque el diseño actual es desktop-first.
- **Modificaciones**: Cualquier cambio a estos estándares debe actualizarse en `template.css` y reflejarse aquí.
- **Uso**: Incluye `template.css` en todos los HTML para estilos base.

Este documento debe mantenerse actualizado con cualquier cambio en `template.css`.