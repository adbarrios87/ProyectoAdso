# Manual de Despliegue Técnico - Parere GRC

**Versión:** 1.0.0  
**Fecha:** Agosto 2026  
**Dirigido a:** Administradores de Sistemas / DevOps / Desarrolladores Backend

---

## 1. Propósito del Proyecto
**Parere GRC** es una plataforma integral para la Gestión de Riesgos y Cumplimiento, enfocada en la evaluación, validación y gestión del ciclo de vida de proveedores. Este sistema permite registrar empresas, someterlas a escrutinio (verificación de listas LAFT, documentos, finanzas) y firmar documentos, a través de flujos de aprobación orquestados por distintos roles (Comprador, Oficial de Cumplimiento).

---

## 2. Arquitectura General
El proyecto sigue una arquitectura distribuida tradicional cliente-servidor (Monolito frontend + Monolito backend RESTful API).

### A. Frontend (Capa de Presentación)
- **Tecnología:** HTML5, CSS3, Vanilla JavaScript.
- **Despliegue Recomendado:** **GitHub + Cloudflare Pages**. Al ser archivos puramente estáticos (`.html`, `.css`, `.js`), Cloudflare Pages se conecta directamente al repositorio de GitHub y despliega la página web de manera gratuita, rápida y con certificado SSL (HTTPS) automático.

### B. Backend (Capa de Negocio y API)
- **Tecnología:** Java 17 con el framework **Spring Boot 3.x**.
- **Despliegue Recomendado:** **GitHub + Render**. Render es una plataforma en la nube (PaaS) que se conecta a GitHub, compila automáticamente el código de Spring Boot usando Maven y expone la API RESTful. Es ideal porque cuenta con una capa gratuita.

### C. Base de Datos (Capa de Persistencia)
- **Tecnología:** **MySQL 8+** (En la nube, mediante servicios gratuitos como Clever Cloud, Aiven o add-ons de Render).
- **Modelo:** Relacional. Las tablas se auto-generan e inicializan por Spring Data JPA (vía Hibernate) basándose en las entidades Java (`spring.jpa.hibernate.ddl-auto=update`).
- **Conexión:** Se realiza mediante JDBC. En Render, se configuran las credenciales como **Variables de Entorno** (Environment Variables) para proteger la seguridad.

### D. Librerías / Dependencias Externas
- **Lombok:** Para reducir el boilerplate en Java.
- **Spring Data JPA & MySQL Connector:** Para orquestación de la DB.
- **Java Mail Sender:** Para envío de notificaciones por correo (SMTP).
- **Apache PDFBox:** Para la lectura, validación y manipulación de documentos PDF.
- **OpenPDF:** Para la generación dinámica y creación de documentos/certificados en formato PDF.
- **Librerías Frontend:** Fuentes de Google Fonts y FontAwesome (cargadas vía CDN).

---

## 3. Explicación de Módulos a Desplegar

1. **Módulo de Frontend (UI):** Provee todas las vistas, desde el registro inicial público de proveedores hasta los paneles de control internos (Dashboard de Comprador, Dashboard Oficial de Cumplimiento). Toda interacción lógica de las interfaces delega la persistencia al Backend vía llamadas `fetch()`.
2. **Microservicio Backend `proveedores`:** Contiene los controladores y la lógica para la evaluación de riesgos, manejo de notificaciones, usuarios, autenticación y la gestión de evidencias (documentos subidos en Base64).

---

## 4. Prerrequisitos y Configuración Pre-Despliegue

### 4.1. Variables de Entorno del Servidor Backend
Antes de arrancar el servidor Java, es estrictamente necesario setear en el sistema operativo del servidor las siguientes variables de entorno que apuntan a tu Base de Datos productiva:

- `DB_URL`: (Ej. `jdbc:mysql://host-de-tu-db:3306/nombre_base_datos`)
- `DB_USER`: (Tu usuario de DB)
- `DB_PASSWORD`: (Tu contraseña)

*Nota:* Las credenciales SMTP de correo (Gmail) se encuentran actualmente definidas en `application.properties`, pero se recomienda pasarlas a variables de entorno para mayor seguridad en producción.

### 4.2. Base de Datos (Recomendaciones Previas)
1. **Esquema Inicial:** Crear un esquema (base de datos) vacío en MySQL (ej. `CREATE DATABASE parere_grc;`). No es necesario ejecutar scripts DLL complejos a mano; el parámetro `spring.jpa.hibernate.ddl-auto=update` se encargará de mapear las tablas en la primera ejecución.
2. **Backups:** Si es una actualización sobre un entorno de producción que ya contiene datos, **SIEMPRE realizar un volcado (Dump) completo de la base de datos** (`mysqldump -u root -p parere_grc > backup_pre_deploy.sql`) antes de reemplazar el archivo `.jar`.

---

## 5. Instrucciones de Despliegue en la Nube (Método Recomendado y Gratuito)

Tu esposo tiene toda la razón. Para un entorno moderno, ágil y de costo cero (capa gratuita), el flujo ideal se basa en despliegues automatizados conectados directamente a GitHub (CI/CD básico).

### 5.1. Despliegue de Base de Datos (Cloud)
1. Crear una cuenta en un proveedor gratuito de bases de datos relacionales (ej. **Aiven**, **Clever Cloud** o a través de **Render**).
2. Crear un servicio de MySQL 8+.
3. El proveedor te entregará unas credenciales: `Host` (URL), `Port` (3306), `Database Name`, `User` y `Password`. **Guarda estos datos.**
4. *Recuerda:* La primera vez que el backend se conecte a esta base de datos, creará todas las tablas automáticamente gracias a Hibernate.

### 5.2. Despliegue de Backend (Render)
1. Ingresa a [Render.com](https://render.com) y vincula tu cuenta de GitHub.
2. Crea un nuevo **"Web Service"** y selecciona el repositorio de tu proyecto (`ProyectoAdso`).
3. Configura el entorno de ejecución:
   - **Language:** `Docker`
   - **Branch:** `main`
   - **Root Directory:** `Backend/proveedores/proveedores`
   - **Region:** `Oregon (US West)` (o cualquiera de USA)
   - **Build Command / Start Command:** *(Al seleccionar Docker, estas dos opciones desaparecen de la pantalla de Render, ya que el archivo Dockerfile se encarga de todo esto internamente)*.
4. Ve a la sección **"Environment Variables"** en Render y agrega tus credenciales de base de datos:
   - `DB_URL`: `jdbc:mysql://[HOST_DEL_PROVEEDOR_BD]:3306/[NOMBRE_BD]`
   - `DB_USER`: `[TU_USUARIO]`
   - `DB_PASSWORD`: `[TU_CONTRASEÑA]`
5. Haz clic en **Deploy**. Render compilará tu Java y te entregará una URL pública para tu API (ej. `https://parere-backend.onrender.com`).

### 5.3. Despliegue de Frontend (Cloudflare Pages)
1. Antes de subir a GitHub, asegúrate de que todas las variables en tus archivos Javascript (`js/`) que apuntaban a `http://localhost:8080/` apunten ahora a la URL pública que te dio Render (ej. `https://parere-backend.onrender.com/`).
2. Ingresa a tu cuenta de **Cloudflare**, ve al menú **Workers & Pages**, y selecciona **Create Application > Pages**.
3. Conecta Cloudflare con tu repositorio de GitHub y selecciona el repositorio `ProyectoAdso`.
4. En la configuración de **"Build settings"**:
   - **Framework preset:** `None`
   - **Build command:** (Déjalo vacío)
   - **Build output directory:** `/Frontend` (o la ruta donde estén tus archivos `index.html`, `css/`, `js/`).
5. Haz clic en **Deploy**. Cloudflare clonará el repositorio y te entregará una URL pública y segura con HTTPS para que ingreses a tu proyecto.

---

## 6. Consideraciones Posteriores y Monitoreo

- **Logs del Backend:** El backend imprime sus trazas estándar (`stdout`). En entornos productivos (Linux), se recomienda crear un servicio `systemd` que canalice los logs a un archivo, o redirigirlos con:
  ```bash
  java -jar proveedores.jar > /var/log/parere/backend_server.log 2>&1 &
  ```
  **Para revisar logs en vivo:** `tail -f /var/log/parere/backend_server.log`
- **Permisos de Archivos:** Asegurarse de que el usuario que corre el proceso de Java o Nginx tenga permisos de lectura/escritura en sus respectivos directorios, en especial si la aplicación procesa archivos temporalmente en el disco local antes de enviarlos a base de datos (Base64).

---

## 7. Test de Despliegue con Postman (Validación)

Una vez los servicios (Backend y DB) estén arriba, cualquier técnico puede comprobar la conectividad realizando una simple petición HTTP a la API REST, sin necesidad de usar el Frontend.

**1. Abrir Postman (o usar `cURL`)**
**2. Método:** `GET`
**3. URL:** `http://<IP-DEL-SERVIDOR>:8080/proveedores`

**Petición cURL de ejemplo:**
```bash
curl -X GET http://localhost:8080/proveedores -H "Accept: application/json"
```

**Respuesta Esperada (HTTP 200 OK):**
```json
{
    "status": 200,
    "message": "Operación exitosa",
    "data": [
        {
            "idProveedor": 1,
            "razonSocial": "Empresa Ejemplo S.A.S",
            "activo": true
        }
    ]
}
```
Si obtienes esta respuesta, certifica que **el backend está arriba y se ha conectado exitosamente a la base de datos MySQL**.

---
*Fin del documento.*
