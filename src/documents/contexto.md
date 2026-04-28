# Resumen de documentos del proyecto

## Contexto general
El proyecto describe un sistema de información web para la gestión documental y verificación de antecedentes de proveedores de la empresa Golden Odds.

Objetivos principales:
- Centralizar la documentación de proveedores.
- Controlar vigencias y cumplimiento normativo.
- Generar notificaciones y alertas de documentos vencidos.
- Verificar antecedentes y aprobar o rechazar proveedores.
- Registrar trazabilidad y auditoría de acciones.

El sistema está orientado a cumplir normas de LAFT/SARLARFT y mejorar la transparencia en la selección y gestión de proveedores.

## 1 Formulación del proyecto de software.docx
Contenido clave:
- Planteamiento del problema: dispersión de documentación, bases de datos incompletas, falta de trazabilidad y ausencia de controles para proveedores.
- Justificación: necesidad de un sistema que centralice datos, reduzca errores humanos y facilite auditorías.
- Alcance: sistema web para registro, evaluación y seguimiento documental de proveedores.
- Objetivos:
  - General: construir una plataforma web para gestión documental y verificación de antecedentes.
  - Específicos: modelar requisitos, definir arquitectura, desarrollar entregas iterativas y validar funcionalidades.
- Riesgos: fallas técnicas, retrasos, errores de migración de datos, acceso no autorizado, saturación de servidores e incompatibilidades.
- Tecnología propuesta: software (Python/JavaScript, MySQL) y servidores en nube con alta disponibilidad.
- Impacto: reducción del consumo de papel, mayor transparencia, disminución de errores y mejor respuesta ante auditorías.
- Trabajo futuro: integración con tesorería, expansión a otros subprocesos y reevaluación anual de proveedores.

## 2 Especificacion de requerimientos.docx
Contenido clave:
- Historia y versión del documento.
- Introducción al sistema y su propósito en la gestión documental de proveedores.
- Diagrama del sistema y descripción del módulo de gestión de proveedores.
- Funciones principales:
  - Verificación de antecedentes.
  - Notificaciones de vencimientos.
  - Aprobación de proveedores.
  - Configuración de alertas.
  - Gestión de proveedores con novedades.
- Requisitos funcionales identificados (RF-002 a RF-006) relacionados con revisión documental, aprobaciones y alertas.
- Requisitos no funcionales y otros detalles de análisis.
- Glosario de acrónimos y definiciones.

## Modelos conceptual y lógico para el proyecto desarrollo de software.docx
Contenido clave:
- Descripción del universo del discurso y el contexto del proyecto.
- Objetivos del sistema: almacenamiento centralizado, trazabilidad documental, mitigación de riesgos y cumplimiento de auditorías.
- Funciones esperadas del sistema:
  - Gestión de proveedores.
  - Carga y almacenamiento de documentos.
  - Validación de datos extraídos.
  - Evaluación de riesgos.
  - Calificación de proveedores.
  - Notificaciones y alertas.
  - Registro de trazabilidad.
- Se indica que los diagramas entidad-relación y relacional están anexados en PDFs.
- Diccionario de bases de datos con tablas y atributos, incluyendo entidades como proveedor, municipio, departamento, país, documento, tipo de documento, contacto, representante legal, evaluación de riesgos, notificaciones, roles y usuarios.

## 4 Historias de Usuario.xlsx
Resumen:
- Documento de historias de usuario y épicas para el sistema.
- Incluye identificadores, roles, características funcionales, criterios de aceptación, escenarios y resultados esperados.
- Cubre procesos de gestión de proveedores, revisión documental, verificación de antecedentes, aprobación de proveedores, alertas de vencimiento y notificaciones.
- Refuerza la visión orientada a usuarios como compradores, proveedores y evaluadores de cumplimiento.

## Diagramas y modelos gráficos
### 3 Diagrama casos de uso.jpeg
- Use case con actores: Proveedor, Compras, Cumplimiento, DBA y Sistemas Externos.
- Casos de uso: Carga documental, búsqueda, revisión, verificación, aprobación, gestión de novedades y alertas.

### PDF de diagramas
- `Modelo Entidad relacion.pdf`: MER de la base de datos.
- `Modelo Relacional.pdf`: Estructura de tablas y llaves.
- `5 Diagrama de clases.pdf`: Modelo de objetos del sistema.
- `6 diagrama de despliegue.pdf`: Arquitectura de infraestructura.

## Estructura Técnica y Código Fuente
El proyecto cuenta con una implementación avanzada del frontend y la base de datos:

### Organización de Archivos
- `src/sheets/`: Contiene las vistas HTML organizadas por roles (admin, buyer, compliance, risk, supplier).
- `src/css/`: Hojas de estilo personalizadas para cada módulo.
- `src/js/`: Lógica de interacción y validaciones del lado del cliente.
- `src/db/`: Definición de la base de datos MySQL (`create_tables.sql`, `insert_data.sql`, `vistas.sql`).

### Módulos Implementados (Vistas)
1. **Paneles de Control (Dashboards)**:
   - Administrador, Comprador, Oficial de Cumplimiento, Riesgos y Proveedor.
2. **Gestión de Proveedores**:
   - Formulario de inscripción (`supplier_form.html`), carga de documentos y listado de proveedores.
3. **Flujo de Evaluación**:
   - Validación documental, evaluación inicial, segunda evaluación y revisión por cumplimiento/riesgos.
4. **Alertas e Historial**:
   - Alertas de vencimiento de documentos y registros históricos de aprobaciones y calificaciones.

### Base de Datos
- **Esquema**: Incluye gestión de usuarios, roles, proveedores, contactos, documentos y evaluaciones.
- **Datos**: Script de inserción con datos de prueba para validación de flujos.
- **Consultas**: Vistas SQL optimizadas para reportes y dashboards.

## Estado Actual
- **Frontend**: Prototipo funcional implementado con HTML/CSS/JS.
- **Base de Datos**: Diseño y scripts DDL/DML completados.
- **Backend**: Pendiente de integración para la persistencia de datos real.

## Observaciones adicionales
- El proyecto enfatiza la gestión preventiva de riesgos de proveedores, el cumplimiento normativo y la automatización de controles de documentación.
- La documentación técnica y los modelos gráficos están alineados con la implementación física en la carpeta `src`.
