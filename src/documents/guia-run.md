# Guía de Ejecución - ProyectoAdso

Esta guía detalla los pasos necesarios para poner en marcha el sistema completo (Backend + Frontend).

## 1. Backend (Java / Spring Boot)

El servidor de datos está desarrollado en Java con Spring Boot.

### Requisitos
*   **JDK 21** (Configurado automáticamente en el wrapper del proyecto).
*   Acceso a internet (para la base de datos remota en Clever Cloud).

### Pasos para iniciar:

#### Opción A: IntelliJ IDEA (Recomendado)
1. Abre IntelliJ y selecciona **Open**.
2. Busca la carpeta `Backend/proveedores/proveedores` y selecciona el archivo **`pom.xml`**.
3. Elige **Open as Project** y luego **Trust Project**.
4. **Configura el SDK:** Presiona `Ctrl + Alt + Shift + S`, en **Project** asegúrate de seleccionar **Java 21** (o el `jbr-21` que viene con IntelliJ).
5. **Habilita Lombok:** Ve a `Settings > Build, Execution, Deployment > Compiler > Annotation Processors` y marca **Enable annotation processing**.
6. Busca la clase `ProveedoresApplication` y haz clic en el botón de **Play verde**.

#### Opción B: Terminal (PowerShell)
1. Abre una terminal en la carpeta raíz del backend:
   ```bash
   cd "Backend/proveedores/proveedores"
   ```
2. Ejecuta el servidor:
   ```bash
   ./mvnw clean spring-boot:run
   ```
3. El backend estará listo cuando veas: `Started ProveedoresApplication in ... seconds`.

> [!IMPORTANT]
> **Si usas la Terminal y ves errores de "NoClassDefFoundError":**
> Esto sucede si tu terminal usa la versión de Java de DBeaver por error. Corrígelo con:
> ```powershell
> $env:JAVA_HOME = "C:\Program Files\JetBrains\IntelliJ IDEA 2025.3.2\jbr"
> $env:Path = "$env:JAVA_HOME\bin;$env:Path"
> ./mvnw clean spring-boot:run
> ```

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

## 3. Verificación y Solución de Errores

### ⚠️ Importante: Ejecución Simultánea
Para que el sistema funcione, **AMBOS** (Backend y Frontend) deben estar corriendo al mismo tiempo. Si cierras IntelliJ o la terminal del Backend, el Frontend mostrará un error de "No se pudo conectar con el servidor".

### 🔒 Problemas de Conexión (CORS)
Si usas la **Opción B** (`npx serve`), el sistema corre en `localhost:3000`. He configurado el Backend para permitir esta conexión. Si recibes un error de conexión estando el Backend encendido:
1.  Detén el Backend en IntelliJ.
2.  Vuelve a iniciarlo (esto aplica la configuración de CORS).
3.  Refresca la página en el navegador.

### 🌐 URL de Acceso
Dependiendo de la opción que elijas, la URL será:
*   **Opción A:** `http://127.0.0.1:5500/login.html`
*   **Opción B:** `http://localhost:3000/login.html`
