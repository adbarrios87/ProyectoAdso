# Guía de Ejecución - ProyectoAdso

Esta guía detalla los pasos necesarios para poner en marcha el sistema completo (Backend + Frontend).

## 1. Backend (Java / Spring Boot)

El servidor de datos está desarrollado en Java con Spring Boot.

### Requisitos
*   **JDK 21** (Configurado automáticamente en el wrapper del proyecto).
*   Acceso a internet (para la base de datos remota en Clever Cloud).

### Pasos para iniciar:
1.  Abre una terminal en la carpeta raíz del backend:
    ```powershell
    cd "Backend/proveedores/proveedores"
    ```
2.  Ejecuta el servidor:
    ```powershell
    ./mvnw spring-boot:run
    ```
3.  Espera a que veas el mensaje: `Started ProveedoresApplication in ... seconds`.
4.  El backend estará escuchando en: `http://localhost:8080`

> [!NOTE]
> **Solución a errores de Lombok:** Si encuentras un error "Fatal error compiling" relacionado con Lombok al compilar, significa que tu sistema está usando JDK 25 (o superior). Debes asegurarte de tener **JDK 17 o 21** instalado y configurar tu variable de entorno `JAVA_HOME` apuntando a esa versión para que el proyecto compile correctamente.

---

## 2. Frontend (Interfaz Web)

La interfaz se compone de archivos HTML, CSS y JS estáticos. Se recomienda usar un servidor local para evitar problemas de CORS.

### Opción A: VS Code Live Server (Recomendado)
1.  Abre el archivo `login.html`.
2.  Haz clic derecho y selecciona **"Open with Live Server"**.
3.  El sistema abrirá tu navegador en `http://127.0.0.1:5500/login.html`.

### Opción B: Terminal (Node.js)
1.  Abre una terminal en la raíz del proyecto (`ProyectoAdso`).
2.  Ejecuta:
    ```powershell
    npx serve .
    ```
3.  Accede a: `http://localhost:3000/login.html`

---

## 3. Verificación
Una vez que ambos estén corriendo, puedes probar el inicio de sesión. Las peticiones del frontend se dirigirán automáticamente al puerto `8080` del backend.
