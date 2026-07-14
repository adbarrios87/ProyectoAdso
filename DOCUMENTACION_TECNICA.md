# DOCUMENTACIÓN TÉCNICA DEL SOFTWARE
### Sistema Parere GRC — Plataforma de Gobernanza, Riesgo y Cumplimiento de Proveedores

> **Proyecto:** Análisis y Desarrollo de Software (ADSO) — SENA
> **Autores:** Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz
> **Versión:** 1.0 — Julio 2026
> **Evidencia:** Elaboración de documentos técnicos de software

---

## TABLA DE CONTENIDO

1. [Introducción](#1-introducción)
2. [Descripción General del Sistema](#2-descripción-general-del-sistema)
3. [Alcance y Objetivos del Software](#3-alcance-y-objetivos-del-software)
4. [Requisitos del Sistema](#4-requisitos-del-sistema)
5. [Arquitectura Técnica](#5-arquitectura-técnica)
6. [Modelo de Datos](#6-modelo-de-datos)
7. [Especificación de la API REST](#7-especificación-de-la-api-rest)
8. [Roles y Modelo de Seguridad](#8-roles-y-modelo-de-seguridad)
9. [Sistema de Diseño (UI/UX)](#9-sistema-de-diseño-uiux)
10. [Instalación y Puesta en Marcha](#10-instalación-y-puesta-en-marcha)
11. [Control de Versiones y Estructura del Repositorio](#11-control-de-versiones-y-estructura-del-repositorio)
12. [Diccionario Técnico](#12-diccionario-técnico)
13. [Información del Proyecto](#13-información-del-proyecto)

---

## 1. INTRODUCCIÓN

### 1.1 Propósito del Documento

Este documento técnico describe la arquitectura, el modelo de datos, la especificación de servicios (API) y los estándares de construcción del sistema **Parere GRC**, desarrollado como proyecto de grado del programa **Análisis y Desarrollo de Software (ADSO)** del SENA. Está dirigido a desarrolladores, evaluadores técnicos e instructores que necesiten comprender **cómo está construido** el software, a diferencia del [Manual de Usuario](MANUAL_DE_USUARIO.md), que explica **cómo se usa**.

### 1.2 Audiencia

| Audiencia | Uso de este documento |
|-----------|------------------------|
| Instructores / evaluadores SENA | Verificar el cumplimiento técnico de la evidencia de aprendizaje |
| Desarrolladores que continúen el proyecto | Entender la arquitectura antes de modificar código |
| Equipo de soporte técnico | Diagnosticar fallas de infraestructura o despliegue |

---

## 2. DESCRIPCIÓN GENERAL DEL SISTEMA

**Parere GRC** (del latín *parere*: "satisfacer, cumplir") es una plataforma web de **Gobernanza, Riesgo y Cumplimiento (GRC)** que automatiza el ciclo de vida de vinculación y seguimiento de proveedores: registro, validación documental, evaluación técnica, análisis de riesgo LAFT/SARLAFT y aprobación de cumplimiento.

### 2.1 Flujo General del Proceso de Negocio

```
[Registro Proveedor] → [Validación Documental] → [Evaluación Técnica]
   → [Análisis de Riesgo LAFT] → [Revisión de Cumplimiento]
      → [Aprobación / Rechazo] → [Seguimiento Continuo]
```

Este flujo se implementa como una máquina de estados sobre la entidad `proveedor`, controlada por acciones de los distintos roles (Comprador, Analista de Riesgos, Oficial de Cumplimiento).

---

## 3. ALCANCE Y OBJETIVOS DEL SOFTWARE

### 3.1 Objetivo General

Sustituir el control manual y disperso (hojas de cálculo, correos, carpetas físicas) por una plataforma centralizada que garantice trazabilidad, control automático de vencimientos documentales y evaluación estructurada de riesgo.

### 3.2 Objetivos Específicos

- Digitalizar el registro y la actualización de información de proveedores (identificación, contacto, información financiera, socios, representantes legales).
- Automatizar el control de vigencia de documentos obligatorios.
- Estandarizar la evaluación técnica y comercial mediante criterios ponderados.
- Incorporar un cuestionario de riesgo LAFT y calcular el nivel de riesgo del proveedor.
- Registrar en auditoría cada acción relevante del sistema para trazabilidad regulatoria.
- Ofrecer un portal de autogestión para que el proveedor cargue y actualice su propia información.

### 3.3 Alcance Funcional

El sistema cubre 5 roles (Administrador, Comprador, Analista de Riesgos, Oficial de Cumplimiento, Proveedor), 30 vistas de interfaz y 31 endpoints REST sobre un modelo de datos de 31 tablas. El detalle funcional por rol está desarrollado en el [Manual de Usuario](MANUAL_DE_USUARIO.md).

---

## 4. REQUISITOS DEL SISTEMA

### 4.1 Requisitos del Servidor (Backend)

| Componente | Requisito |
|-----------|-----------|
| Lenguaje | Java 17 (LTS) |
| Framework | Spring Boot 3.4.1 |
| Persistencia | Spring Data JPA / Hibernate |
| Base de Datos | MySQL 8.0 o superior |
| Generación de código | Lombok 1.18.36 |
| Gestor de dependencias | Maven |
| Puerto por defecto | 8080 |
| IDE recomendado | IntelliJ IDEA |

### 4.2 Requisitos del Cliente (Frontend)

| Componente | Requisito |
|-----------|-----------|
| Navegador | Google Chrome 100+ / Firefox 95+ / Edge 100+ |
| Resolución mínima | 1280 x 720 px |
| JavaScript | Habilitado (obligatorio) |
| Conexión | Red local o Internet con acceso al backend |

### 4.3 Dependencias Externas

| Recurso | Tipo | Uso |
|---------|------|-----|
| Font Awesome 6.4.2 | CDN | Iconografía del sistema |
| Google Fonts (Poppins, Open Sans) | CDN | Tipografía |
| MySQL Connector/J | Librería Java (Maven) | Driver JDBC de conexión a MySQL |
| spring-boot-starter-validation | Librería Java (Maven) | Validación de DTOs de entrada |

---

## 5. ARQUITECTURA TÉCNICA

### 5.1 Arquitectura por Capas

```
┌──────────────────────────────────────┐
│      FRONTEND (Cliente/Navegador)    │
│   HTML5 + CSS3 + JavaScript (ES6+)   │
└────────────────┬─────────────────────┘
                  │ HTTP REST (JSON)
                  │ Puerto 8080
┌────────────────┴─────────────────────┐
│      BACKEND (Spring Boot 3)         │
│  Controllers → Services → DTOs       │
│  Repositories (Spring Data JPA)      │
└────────────────┬─────────────────────┘
                  │ JPA / JDBC (Hibernate)
┌────────────────┴─────────────────────┐
│      BASE DE DATOS                   │
│      MySQL — Esquema: parere_grc     │
└───────────────────────────────────────┘
```

El backend sigue una arquitectura en capas clásica de Spring Boot: **Controller** (expone el endpoint REST) → **Service** (lógica de negocio) → **Repository** (acceso a datos vía `JpaRepository`) → **Entity** (mapeo objeto-relacional). La comunicación entre capas usa **DTOs** para no exponer las entidades JPA directamente en la API.

### 5.2 Estructura del Frontend

```
ProyectoAdso/
├── login.html                  ← Punto de entrada al sistema
├── src/
│   ├── assets/                 ← Logos y recursos gráficos
│   ├── css/                    ← Estilos por módulo + template.css (sistema de diseño)
│   ├── js/
│   │   ├── config.js           ← URL base del API (API_BASE_URL)
│   │   ├── main.js             ← Lógica global: menú dinámico, header, sesión
│   │   └── login.js            ← Autenticación contra el backend
│   ├── db/                     ← Scripts SQL (DDL/DML) de la base de datos
│   └── sheets/                 ← 30 vistas HTML del sistema, una por pantalla/rol
```

El frontend es una aplicación **multi-página** (no SPA): cada archivo `.html` en `src/sheets/` es una vista independiente que consume el API mediante `fetch`, sin framework de frontend (HTML5 + CSS3 + JavaScript ES6+ nativos).

### 5.3 Estructura del Backend

```
Backend/proveedores/proveedores/src/main/java/proyecto/ADSO/proveedores/
├── controllers/     ← 31 controladores REST (uno por recurso)
├── services/        ← Lógica de negocio
├── repositories/     ← Interfaces JpaRepository (acceso a datos)
├── entites/         ← 31 entidades JPA (@Entity), una por tabla
├── dtos/            ← Objetos de transferencia de datos (entrada/salida de la API)
└── config/          ← Configuración CORS (CorsConfig.java)
```

### 5.4 Configuración de Ejecución

El backend se configura en `application.properties`:

| Propiedad | Descripción |
|-----------|-------------|
| `spring.datasource.url` | Cadena de conexión JDBC a MySQL |
| `spring.jpa.hibernate.ddl-auto=update` | Hibernate actualiza el esquema automáticamente a partir de las entidades |
| `spring.jpa.show-sql=true` | Registra en consola las sentencias SQL generadas (modo desarrollo) |
| `spring.servlet.multipart.max-file-size` / `max-request-size` | Límite de 10 MB para carga de documentos y fotos en Base64 |

> **Nota de seguridad:** las credenciales de la base de datos en `application.properties` están en texto plano dentro del repositorio. Para un entorno de producción se recomienda externalizarlas mediante variables de entorno o un gestor de secretos, y rotar las credenciales actuales.

---

## 6. MODELO DE DATOS

### 6.1 Motor y Esquema

El sistema usa **MySQL** con el esquema `parere_grc`, compuesto por **31 tablas**. Los scripts de base de datos se encuentran en `src/db/`:

| Script | Contenido |
|--------|-----------|
| `create_tables.sql` | DDL — Definición de las 31 tablas, llaves primarias/foráneas |
| `insert_data.sql` | DML — Datos iniciales (catálogos: roles, países, tipos de documento, etc.) |
| `vistas.sql` | Vistas SQL de consulta para reportes |

### 6.2 Entidades Principales

| Tabla | Propósito |
|-------|-----------|
| `usuario` | Usuarios del sistema y sus credenciales |
| `rol` | Los 5 roles del sistema |
| `proveedor` | Entidad central: datos de identificación, financieros y de estado del proveedor |
| `contacto` / `proveedor_contacto` | Información de contacto y su relación con el proveedor |
| `documento` | Documentos cargados por el proveedor, con vigencia y estado |
| `representante_legal` / `representante_proveedor` | Representación legal de personas jurídicas |
| `socio_proveedor` / `documento_socio_proveedor` | Socios/accionistas y su documentación |
| `evaluacion_proveedor` | Resultado de las evaluaciones técnicas (primera/segunda) |
| `evaluacion_riesgo` | Resultado del cuestionario LAFT y nivel de riesgo calculado |
| `validacion` / `campo_validacion` | Resultado de la validación documental por campo |
| `notificacion` / `tipo_notificacion` | Notificaciones del sistema |
| `historial_usuario` | Auditoría de eventos por usuario |
| `estado_proveedor` / `estado_usuario` | Catálogos de estados |
| `pais` / `departamento` / `municipio` / `ubicacion` | Catálogo geográfico |
| `tipo_identificacion` / `tipo_persona` / `tipo_documento` / `tipo_pago` / `tipo_telefono` / `forma_de_pago` / `origen_dato` / `calificacion` | Catálogos de referencia (maestros) |

### 6.3 Persistencia Objeto-Relacional

Cada tabla tiene su contraparte como clase Java anotada con `@Entity` en el paquete `entites/`, usando anotaciones **Lombok** (`@Data`, `@Builder`) para reducir código repetitivo (getters, setters, constructores). El mapeo objeto-relacional lo gestiona **Hibernate** a través de **Spring Data JPA**, con `spring.jpa.hibernate.ddl-auto=update` sincronizando el esquema desde las entidades.

---

## 7. ESPECIFICACIÓN DE LA API REST

### 7.1 Convenciones

- Formato de intercambio: **JSON**.
- Base URL configurada en `src/js/config.js` (`API_BASE_URL`), por defecto `http://localhost:8080`.
- Verbos HTTP estándar: `GET` (consultar), `POST` (crear), `PUT` (actualizar completo), `PATCH` (actualizar parcial), `DELETE` (eliminar).
- CORS habilitado mediante `config/CorsConfig.java` para permitir peticiones del frontend servido en un origen distinto.

### 7.2 Recursos Expuestos (31 controladores)

| Recurso base | Controlador |
|--------------|-------------|
| `/usuarios` | `UsuariosController` |
| `/proveedores` | `ProveedorController` |
| `/roles` | `RolesController` |
| `/contacto` | `ContactoController` |
| `/proveedor_contacto` | `ProveedorContactoController` |
| `/documentos` | `DocumentosController` |
| `/documentos_socios_proveedor` | `DocumentosSociosProveedorController` |
| `/socios_proveedor` | `SociosProveedorController` |
| `/representante_legal` | `RepresentanteLegalController` |
| `/representante_proveedor` | `RepresentanteProveedorController` |
| `/validacion` | `ValidacionController` |
| `/campo_validacion` | `CampoValidacionController` |
| `/evaluacion_proveedor` | `EvaluacionProveedorController` |
| `/evaluacion_riesgos` | `EvaluacionRiesgosController` |
| `/notificaciones` | `NotificacionesController` |
| `/tipo_notificacion` | `TipoNotificacionController` |
| `/historial_usuario` | `HistorialUsuarioController` |
| `/estado_proveedor` | `EstadoProveedorController` |
| `/estado_usuario` | `EstadoUsuarioController` |
| `/calificacion` | `CalificacionController` |
| `/forma_de_pago` | `FormaDePagoController` |
| `/tipo_pago` | `TipoPagoController` |
| `/tipo_documento` | `TipoDocumentoController` |
| `/tipo_identificacion` | `TipoIdentificacionController` |
| `/tipo_persona` | `TipoPersonaController` |
| `/tipo_telefono` | `TipoTelefonoController` |
| `/origen_dato` | `OrigenDatoController` |
| `/pais` | `PaisController` |
| `/departamento` | `DepartamentoController` |
| `/municipio` | `MunicipioController` |
| `/ubicacion` | `UbicacionController` |

### 7.3 Endpoints Destacados

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/usuarios/login` | Autenticación de usuario, retorna datos de sesión (rol, id, nombre) |
| `GET` | `/usuarios/menu/{idRol}` | Menú lateral dinámico según el rol autenticado |
| `PATCH` | `/usuarios/{id}/foto` | Actualiza la foto de perfil (Base64) |
| `PATCH` | `/usuarios/{id}/estado` | Activa/Desactiva un usuario |
| `GET` | `/proveedores` | Lista todos los proveedores |
| `GET` | `/proveedores/{id}` | Detalle de un proveedor |
| `POST` | `/proveedores` | Crea un proveedor |
| `PUT` | `/proveedores/{id}` | Actualiza un proveedor completo |
| `DELETE` | `/proveedores/{id}` | Elimina un proveedor |
| `POST` | `/proveedores/registro-completo` | Registro transaccional con todos los datos relacionados (contacto, financiero, LAFT, socios) |

### 7.4 Formato de Respuesta

Las respuestas exitosas siguen un envoltorio JSON con la clave `data`, por ejemplo la respuesta de login:

```json
{
  "data": {
    "idUsuario": 1,
    "idRol": 2,
    "nombreUsuario": "Juan"
  }
}
```

---

## 8. ROLES Y MODELO DE SEGURIDAD

### 8.1 Roles del Sistema

| ID Rol | Rol | Dashboard asociado |
|:------:|-----|---------------------|
| 1 | Administrador | `admin_dashboard.html` |
| 2 | Comprador | `buyer_dashboard.html` |
| 3 | Proveedor | `supplier_dashboard.html` |
| 4 | Analista de Riesgos | `risk_dashboard.html` |
| 5 | Oficial de Cumplimiento | `compliance_officer_dashboard.html` |

### 8.2 Mecanismo de Sesión

La sesión se gestiona en el **cliente** mediante `localStorage` del navegador, almacenando: `userRole`, `userId`, `userName`, `userEmail`, `idRol`, `userPhoto`. El menú lateral se reconstruye en cada carga de página consultando `GET /usuarios/menu/{idRol}`, de modo que cada rol solo ve las opciones autorizadas.

> **Nota técnica:** el control de acceso actual es de **presentación** (oculta/muestra opciones de menú según el rol almacenado en el cliente); no hay un mecanismo de autorización a nivel de token (JWT/OAuth) que proteja los endpoints del backend directamente. Se recomienda incorporar Spring Security con autenticación basada en token antes de un despliegue en producción expuesto a Internet.

### 8.3 Matriz de Permisos Funcionales

| Funcionalidad | Admin | Comprador | Analista | Oficial | Proveedor |
|---------------|:-----:|:---------:|:--------:|:-------:|:---------:|
| Gestión de Usuarios | ✅ | ❌ | ❌ | ❌ | ❌ |
| Logs de Auditoría | ✅ | ❌ | ❌ | ❌ | ❌ |
| Lista de Proveedores | ✅ | ✅ | ✅ | ✅ | ❌ |
| Validación Documental | ❌ | ✅ | ❌ | ❌ | ❌ |
| Evaluación Técnica | ❌ | ✅ | ❌ | ❌ | ❌ |
| Análisis LAFT | ❌ | ❌ | ✅ | ❌ | ❌ |
| Revisión de Cumplimiento | ❌ | ❌ | ❌ | ✅ | ❌ |
| Carga de Documentos Propios | ❌ | ❌ | ❌ | ❌ | ✅ |
| Certificado de Vinculación | ❌ | ❌ | ❌ | ❌ | ✅ |

---

## 9. SISTEMA DE DISEÑO (UI/UX)

El frontend centraliza sus tokens de diseño en `src/css/template.css`, usando variables CSS (`:root`) definidas en HSL/LAB.

### 9.1 Paleta de Colores

| Variable | Valor | Descripción |
|----------|-------|-------------|
| `--background-main` | crema muy claro | Fondo base |
| `--background-card` | `#FFFFFF` | Tarjetas y secciones |
| `--background-navbar_btn` | `#1E1E2F` | Azul oscuro del sidebar |
| `--accent-color` / `--primary-color` | `#D4A373` | Acento arena/bronce |
| `--text-primary` | `#333333` | Texto principal |
| `--text-secondary` | `#666666` | Texto complementario |
| `--border-color` | `#E0E0E0` | Bordes |
| `--shadow-card` | `0 4px 12px rgba(0,0,0,0.05)` | Elevación de tarjetas |

### 9.2 Estados y Badges

| Estado | Fondo | Texto |
|--------|-------|-------|
| Success | `#E8F5E9` | `#2E7D32` |
| Warning | `#FFF8E1` | `#D3BF0C` |
| Danger | `#FFEBEE` | `#C62828` |
| Info | `#E3F2FD` | `#1565C0` |

### 9.3 Tipografía y Layout

- Fuente principal: **Poppins** (encabezados) — h1 28px/600, h2 20px, h3 16px.
- Fuente secundaria: **Open Sans** (cuerpo de texto, 14px).
- Sidebar: 200px de ancho, fondo `--background-navbar_btn`, íconos de 18px.
- Contenedor principal: `height: 100vh`, scroll independiente en `main` (`overflow-y: auto`).

### 9.4 Componentes Estándar

- **Botones (`.btn`):** fondo oscuro, texto blanco, hover a fondo claro con texto acento, micro-animación `translateY(2px)` al clic.
- **Tablas (`.general-table`):** encabezado en mayúsculas, filas intercaladas, hover por fila, padding de 16px.
- **Buscador (`.search-box`):** ícono de lupa absoluto, bordes redondeados 8px, foco con borde color acento.
- **Menús desplegables (`.dropdown-menu`):** aparecen al hover del sidebar, sombra `0 8px 24px`, bordes de 12px.

Documento de referencia completo: [diseño.md](diseño.md).

---

## 10. INSTALACIÓN Y PUESTA EN MARCHA

### 10.1 Backend

1. Abrir la carpeta `Backend/proveedores/proveedores` en IntelliJ IDEA (proyecto Maven).
2. Configurar `src/main/resources/application.properties` con las credenciales de la base de datos MySQL a usar.
3. Ejecutar los scripts `src/db/create_tables.sql` e `src/db/insert_data.sql` sobre el esquema `parere_grc`.
4. Ejecutar la clase principal Spring Boot (`ProveedoresApplication`) o `mvn spring-boot:run`.
5. Verificar que el servidor quede escuchando en `http://localhost:8080`.

### 10.2 Frontend

1. Servir la carpeta raíz del proyecto con cualquier servidor de archivos estáticos (Live Server de VS Code, `npx serve`, etc.) o abrir `login.html` directamente en el navegador.
2. Verificar que `src/js/config.js` apunte a la URL correcta del backend (`API_BASE_URL`).
3. Acceder a `login.html` e iniciar sesión con un usuario existente en la base de datos.

### 10.3 Dependencias de Node (proyecto raíz)

El `package.json` de la raíz gestiona dependencias auxiliares de tooling del proyecto (no del runtime de la aplicación). Instalar con `npm install` si el flujo de trabajo las requiere.

---

## 11. CONTROL DE VERSIONES Y ESTRUCTURA DEL REPOSITORIO

- **Sistema:** Git / GitHub.
- **Ramas principales:** `main` (estable) y `feature/*` (desarrollo por módulo, p. ej. `feature/Java` para el backend).
- **Estructura raíz del repositorio:**

```
ProyectoAdso/
├── Backend/proveedores/proveedores/   ← Proyecto Spring Boot (Maven)
├── src/                                ← Frontend (vistas, css, js, scripts SQL)
├── login.html                          ← Entrada al sistema
├── diseño.md                           ← Estándares de diseño UI/UX
├── DOCUMENTACION_TECNICA.md            ← Este documento
├── MANUAL_DE_USUARIO.md                ← Manual de usuario funcional
└── MANUAL_USUARIO_INTERACTIVO.html     ← Versión navegable del manual de usuario
```

---

## 12. DICCIONARIO TÉCNICO

**API (Application Programming Interface)** — Interfaz de programación que permite la comunicación entre el Frontend y el Backend mediante los endpoints REST expuestos en `http://localhost:8080`.

**CORS (Cross-Origin Resource Sharing)** — Mecanismo de seguridad del navegador. La clase `CorsConfig.java` habilita que el frontend consuma el API sin ser bloqueado por el navegador.

**DDL (Data Definition Language)** — Instrucciones SQL para definir la estructura de la base de datos (`CREATE TABLE`, `ALTER TABLE`), contenidas en `create_tables.sql`.

**DML (Data Manipulation Language)** — Instrucciones SQL para manipular datos (`INSERT`, `UPDATE`, `DELETE`), contenidas en `insert_data.sql`.

**DTO (Data Transfer Object)** — Clase Java usada para transferir datos entre capas sin exponer las entidades JPA directamente. Ejemplo: `LoginResponseDto.java`.

**Endpoint** — URL específica del API que acepta un tipo de petición HTTP, p. ej. `POST /usuarios/login`.

**Entidad (Entity)** — Clase Java anotada con `@Entity` que representa una tabla de la base de datos, p. ej. `ProveedorEntity.java`.

**Hibernate** — Implementación de JPA usada por Spring Data JPA para el mapeo objeto-relacional.

**JPA (Java Persistence API)** — Especificación Java para el mapeo objeto-relacional (ORM).

**JSON (JavaScript Object Notation)** — Formato de intercambio de datos entre frontend y backend.

**Lombok** — Biblioteca Java que genera código repetitivo (getters/setters, builders) mediante anotaciones como `@Data` y `@Builder`.

**Maven** — Gestor de dependencias y construcción del proyecto backend (`pom.xml`).

**ORM (Object-Relational Mapping)** — Técnica que mapea tablas de base de datos a clases Java; implementada aquí por Spring Data JPA + Hibernate.

**Repositorio (Repository)** — Capa del backend que se comunica con la base de datos mediante interfaces que extienden `JpaRepository`.

**REST (Representational State Transfer)** — Estilo de arquitectura de la API, basado en recursos accedidos con métodos HTTP estándar.

**Spring Boot** — Framework Java (versión 3.4.1) que auto-configura y simplifica la construcción del backend.

**Spring Data JPA** — Módulo de Spring que implementa el patrón repositorio sobre JPA/Hibernate.

---

## 13. INFORMACIÓN DEL PROYECTO

| Dato | Descripción |
|------|-------------|
| **Nombre** | Parere GRC |
| **Programa** | Análisis y Desarrollo de Software (ADSO) |
| **Institución** | SENA |
| **Año** | 2025 – 2026 |
| **Autores** | Adriana Gineth Barrios Aponte · Juan Carlos Cadena Muñoz |
| **Frontend** | HTML5 · CSS3 · JavaScript ES6+ |
| **Backend** | Java 17 · Spring Boot 3.4.1 · Spring Data JPA · Lombok |
| **Base de Datos** | MySQL 8.0 — Esquema `parere_grc` (31 tablas) |
| **Versionamiento** | Git / GitHub |
| **Correos** | adbarrios87@gmail.com · espriggan123@gmail.com |

---

*© 2026 — Parere GRC. Documentación técnica elaborada para el programa de formación ADSO — SENA.*
