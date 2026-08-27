DROP DATABASE IF EXISTS parere_grc;
CREATE DATABASE IF NOT EXISTS parere_grc CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE parere_grc;

-- Tablas catalogo
CREATE TABLE tipo_identificacion (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de identificacion',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo interno del tipo de documento (ej: NIT, CC, CE, PAS)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion completa del tipo de documento',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Catalogo de tipos de documentos de identificacion aceptados en el sistema';

CREATE TABLE tipo_persona (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de persona',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo interno (ej: NATURAL, JURIDICA)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del tipo de persona',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Clasificacion juridica de proveedores (Persona Natural o Juridica)';

CREATE TABLE tipo_telefono (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de telefono',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo interno (ej: CELULAR, FIJO, OFICINA)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del tipo de numero telefonico',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Catalogo de tipos de numeros telefonicos para contactos';

CREATE TABLE estado_proveedor (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del estado del proveedor',
  estado VARCHAR(50) NOT NULL UNIQUE COMMENT 'Nombre del estado (ej: ACTIVO, INACTIVO, PENDIENTE_VALIDACION)',
  descripcion VARCHAR(300) NOT NULL COMMENT 'Descripcion detallada del estado y sus implicaciones',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Estados posibles en el ciclo de vida de un proveedor';

CREATE TABLE pais (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del pais',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo abreviado del pais (ej: CO, US, MX)',
  nombre VARCHAR(150) NOT NULL COMMENT 'Nombre completo del pais',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Catalogo de paises para clasificacion geografica de proveedores';

CREATE TABLE departamento (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del departamento/estado',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo del departamento segun normativa local',
  nombre VARCHAR(150) NOT NULL COMMENT 'Nombre completo del departamento o estado',
  id_pais INT NOT NULL COMMENT 'Referencia al pais al que pertenece el departamento',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_departamento_pais FOREIGN KEY (id_pais) REFERENCES pais(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) ENGINE=InnoDB COMMENT='Divisiones administrativas de segundo nivel (departamentos, estados, provincias)';

CREATE TABLE tipo_pago (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de pago',
  codigo VARCHAR(30) NOT NULL UNIQUE COMMENT 'Codigo interno (ej: CONTADO, CREDITO_30, CREDITO_60)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion de las condiciones del tipo de pago',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Catalogo de metodos y condiciones de pago para proveedores';

CREATE TABLE origen_dato (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del origen de datos',
  codigo VARCHAR(20) UNIQUE COMMENT 'Codigo interno (ej: RUES, CAMARA, CONSULTA_DIRECTA)',
  descripcion VARCHAR(100) COMMENT 'Descripcion de la fuente de informacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Fuentes de informacion utilizadas para consultas de datos';

CREATE TABLE tipo_documento (
  id_tipo_documento INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de documento',
  codigo VARCHAR(20)NOT NULL UNIQUE COMMENT 'Codigo interno (ej: RUT, CAMARA, CERTIFICACION)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del tipo de documento y su proposito',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Catalogo de tipos de documentos soportados por el sistema';

CREATE TABLE calificacion (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la calificacion',
  codigo VARCHAR(20) NOT NULL UNIQUE COMMENT 'Codigo interno de la calificacion (ej: CONFIABLE, ALTERNATIVO, NO CONFIABLE)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del nivel de calificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Escalas de calificacion para evaluacion de desempeno de proveedores';

CREATE TABLE tipo_notificacion (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del tipo de notificacion',
  codigo VARCHAR(20) NOT NULL UNIQUE COMMENT 'Codigo interno (ej: ALERTA, RECORDATORIO, ACTUALIZACION)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del tipo de notificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Tipos de notificaciones que puede enviar el sistema';

CREATE TABLE estado_usuario (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del estado de usuario',
  codigo VARCHAR(20) NOT NULL UNIQUE COMMENT 'Codigo interno (ej: ACTIVO, INACTIVO, BLOQUEADO)',
  descripcion VARCHAR(100) NOT NULL COMMENT 'Descripcion del estado del usuario',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete'
) ENGINE=InnoDB COMMENT='Estados posibles de las cuentas de usuario del sistema';

CREATE TABLE municipio (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del municipio/ciudad',
  codigo VARCHAR(10) NOT NULL UNIQUE COMMENT 'Codigo del municipio segun normativa local',
  nombre VARCHAR(150) NOT NULL COMMENT 'Nombre completo del municipio o ciudad',
  id_departamento INT NOT NULL COMMENT 'Referencia al departamento al que pertenece el municipio',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  UNIQUE (nombre, id_departamento),
  CONSTRAINT fk_municipio_departamento FOREIGN KEY (id_departamento) REFERENCES departamento(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) ENGINE=InnoDB COMMENT='Divisiones administrativas de tercer nivel (municipios, ciudades)';

CREATE TABLE campo_validacion (
  id_campo_validacion INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del campo de validacion',
  id_tipo_documento INT NOT NULL COMMENT 'Tipo de documento al que aplica este campo',
  campo VARCHAR(100) NOT NULL COMMENT 'Nombre del campo que requiere validacion',
  obligatorio BOOLEAN DEFAULT FALSE COMMENT 'Indica si el campo es obligatorio (1) u opcional (0)',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_cv_tipo_doc FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id_tipo_documento)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) ENGINE=InnoDB COMMENT='Campos que requieren validacion especifica por tipo de documento';

-- Tablas trasaccionales
CREATE TABLE roles (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del rol de usuario',
  rol VARCHAR(100) NOT NULL COMMENT 'Nombre del rol (ej: ADMIN, AUDITOR, GESTOR_PROVEEDORES)',
  estado_rol BOOLEAN DEFAULT TRUE COMMENT 'Indica si el rol esta activo en el sistema (1) o inactivo (0)',
  descripcion TEXT NOT NULL COMMENT 'Descripcion detallada de permisos y responsabilidades del rol',
  fecha_creado DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del rol',
  creado_por INT NULL COMMENT 'Usuario que registro el rol',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion'
) ENGINE=InnoDB COMMENT='Roles de usuarios del sistema para control de acceso';

CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del usuario del sistema',
  nombre_usuario VARCHAR(100) NOT NULL COMMENT 'Nombre completo del usuario',
  cargo_usuario VARCHAR(100)  NOT NULL COMMENT 'Cargo o posicion del usuario en la organizacion', 
  correo_usuario VARCHAR(150) NOT NULL UNIQUE COMMENT 'Correo electronico unico para acceso al sistema',
  contrasena VARCHAR(255) NOT NULL COMMENT 'Contrasena hasheada para autenticacion',
  estado_usuario BOOLEAN DEFAULT TRUE COMMENT 'Estado del usuario: activo (1) o inactivo (0)',
  id_rol INT NOT NULL COMMENT 'Rol del usuario que define sus permisos en el sistema',
  foto_url LONGTEXT,
  notif_status BOOLEAN DEFAULT TRUE,
  notif_docs BOOLEAN DEFAULT TRUE,
  notif_expiry BOOLEAN DEFAULT TRUE,
  notif_news BOOLEAN DEFAULT TRUE,
  reset_token VARCHAR(255),
  reset_token_expiry DATETIME,
  ultimo_ingreso DATETIME NULL COMMENT 'Fecha y hora del ultimo acceso exitoso al sistema',
  fecha_creado DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creacion de la cuenta de usuario',
  creado_por INT NULL COMMENT 'Usuario que registro la creacion de la cuenta',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  CONSTRAINT fk_usuarios_rol FOREIGN KEY (id_rol) REFERENCES roles(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) ENGINE=InnoDB COMMENT='Usuarios autorizados para acceder al sistema de gestion de proveedores';

CREATE TABLE proveedor (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del proveedor',
  id_tipo_identificacion INT NOT NULL COMMENT 'Tipo de documento de identificación del proveedor',
  numero_identificacion VARCHAR(30) NOT NULL UNIQUE COMMENT 'Numero de identificacion unico del proveedor',
  digito_verificacion VARCHAR(1) NULL COMMENT 'Digito de verificacion para NIT (solo Colombia)',
  razon_social VARCHAR(200) NULL COMMENT 'Razon social (para personas juridicas)',
  nombres VARCHAR(100) NULL COMMENT 'Nombres del proveedor (para personas naturales)',
  apellidos VARCHAR(100) NULL COMMENT 'Apellidos del proveedor (para personas naturales)',
  id_tipo_persona INT NOT NULL COMMENT 'Tipo de persona: natural o juridica',
  telefono_principal VARCHAR(20) NOT NULL COMMENT 'Numero telefonico principal de contacto',
  id_tipo_telefono INT NOT NULL COMMENT 'Tipo del telefono principal',
  correo_principal VARCHAR(150) NOT NULL COMMENT 'Correo electronico principal de contacto',
  id_actualizacion_proveedor INT NULL COMMENT 'Identificador del último registro de actualización asociado al proveedor',
  requiere_actualizacion BOOLEAN DEFAULT FALSE COMMENT 'Indica si el proveedor requiere actualizacion de datos (1) o no (0)',
  descripcion TEXT COMMENT 'Descripcion de bienes o servicios que presta el proveedor',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del proveedor',
  creado_por INT NOT NULL COMMENT 'Usuario que registro el proveedor',
  fecha_modificado DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion de los datos',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  id_estado_proveedor INT DEFAULT 4 COMMENT 'Referencia al estado del proveedor',
  fecha_aprobacion DATETIME NULL COMMENT 'Fecha de aprobacion general del proveedor',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_prov_tipo_identificacion FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion(id) 
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_prov_tipo_persona FOREIGN KEY (id_tipo_persona) REFERENCES tipo_persona(id) 
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_prov_tipo_telefono FOREIGN KEY (id_tipo_telefono) REFERENCES tipo_telefono(id) 
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_prov_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_prov_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_prov_estado FOREIGN KEY (id_estado_proveedor) REFERENCES estado_proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE
) ENGINE=InnoDB COMMENT='Informacion principal de proveedores registrados en el sistema';

CREATE TABLE contacto (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del contacto',
  nombre_contacto VARCHAR(100) NOT NULL COMMENT 'Nombre completo de la persona de contacto',
  cargo_contacto VARCHAR(100) NOT NULL COMMENT 'Cargo o posicion del contacto en la organizacion',
  id_tipo_telefono INT NOT NULL COMMENT 'Tipo de numero telefonico del contacto',
  telefono_contacto VARCHAR(20) NOT NULL COMMENT 'Numero telefonico del contacto',
  correo_contacto VARCHAR(150) NOT NULL COMMENT 'Correo electronico del contacto',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del contacto',
  creado_por INT NOT NULL COMMENT 'Usuario que registro el contacto',
  fecha_modificado DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  id_validacion_final INT NULL COMMENT 'Validacion final agrupada',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_contacto_tipo_telefono FOREIGN KEY (id_tipo_telefono) REFERENCES tipo_telefono(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_contacto_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_contacto_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Personas de contacto que pueden estar asociadas a multiples proveedores';

CREATE TABLE forma_de_pago (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la forma de pago',
  id_proveedor INT NOT NULL COMMENT 'Proveedor al que aplica esta forma de pago',
  id_tipo_pago INT NOT NULL  COMMENT 'Tipo de pago acordado',
  monto DECIMAL(15,2) NULL COMMENT 'Monto base o limite para esta forma de pago',
  plazo INT NULL COMMENT 'Plazo en dias para el pago (ej: 30, 60, 90)',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro de la forma de pago',
  creado_por INT NOT NULL COMMENT 'Usuario que registro la forma de pago',
  fecha_modificado DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_fp_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_fp_tipo_pago FOREIGN KEY (id_tipo_pago) REFERENCES tipo_pago(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_fp_prov_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_fp_prov_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
    UNIQUE (id_proveedor, id_tipo_pago)
) ENGINE=InnoDB COMMENT='Condiciones y formas de pago acordadas con proveedores';

CREATE TABLE documento (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del documento',
  id_proveedor INT NOT NULL COMMENT 'Proveedor al que pertenece el documento',
  id_tipo_documento INT NOT NULL COMMENT 'Tipo de documento (RUT, camara, certificacion, etc.)',
  numero_documento VARCHAR(50) COMMENT 'Numero o identificador del documento',
  fecha_emision DATE NULL COMMENT 'Fecha de emision del documento',
  url_documento TEXT NOT NULL COMMENT 'URL o ruta donde esta almacenado el documento',
  almacenamiento ENUM('s3','local','db') DEFAULT 's3' COMMENT 'Tipo de almacenamiento: S3 cloud, local, o base de datos',
  archivo_blob LONGBLOB NULL COMMENT 'Contenido del documento en formato binario (si se almacena en DB)',
  hash VARCHAR(200) NULL COMMENT 'Hash del documento para verificacion de integridad',
  tamano_bytes BIGINT NULL COMMENT 'Tamano del documento en bytes',
  fecha_carga DATE NULL COMMENT 'Fecha en que se cargo el documento al sistema',
  estado_documento BOOLEAN DEFAULT TRUE COMMENT 'Estado del documento: activo (1) o inactivo (0)',
  validado BOOLEAN DEFAULT FALSE COMMENT 'Indica si el documento ha sido validado (1) o no (0)',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del documento',
  creado_por INT NOT NULL COMMENT 'Usuario que cargo el documento',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  CONSTRAINT fk_doc_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_doc_tipo FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id_tipo_documento)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
    CONSTRAINT fk_doc_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_doc_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Documentos digitales asociados a proveedores con multiples opciones de almacenamiento';

CREATE TABLE representante_legal (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del representante legal',
  id_tipo_identificacion INT NOT NULL COMMENT 'Tipo de documento del representante legal',
  id_documento INT COMMENT 'Documento que acredita la representacion legal',
  numero_identificacion VARCHAR(20) NOT NULL COMMENT 'Numero de identificacion del representante',
  nombres VARCHAR(100) NOT NULL COMMENT 'Nombres del representante legal',
  apellidos VARCHAR(100) NOT NULL COMMENT 'Apellidos del representante legal',
  id_tipo_telefono INT NULL COMMENT 'Tipo de telefono del representante',
  telefono VARCHAR(20) NULL COMMENT 'Numero telefonico del representante',
  correo VARCHAR(100) NOT NULL COMMENT 'Correo electronico del representante',
  id_usuario INT NULL COMMENT 'Usuario del sistema asociado al representante legal',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del representante',
  creado_por INT NOT NULL COMMENT 'Usuario que registro el representante',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_rl_tipo_identificacion FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_rl_tipo_telefono FOREIGN KEY (id_tipo_telefono) REFERENCES tipo_telefono(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_rl_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_rl_prov_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_rl_prov_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_rl_id_documento FOREIGN KEY (id_documento) REFERENCES documento(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Representantes legales autorizados de proveedores personas juridicas';

CREATE TABLE socio_proveedor (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del socio',
  id_proveedor INT NOT NULL COMMENT 'Proveedor al que pertenece el socio',
  nombres VARCHAR(100) NOT NULL COMMENT 'Nombres del socio o accionista',
  apellidos VARCHAR(100) NOT NULL COMMENT 'Apellidos del socio o accionista',
  id_tipo_identificacion INT NOT NULL COMMENT 'Tipo de documento del socio',
  numero_identificacion VARCHAR(50) NOT NULL UNIQUE COMMENT 'Numero de identificacion del socio',
  consulta INT NULL COMMENT 'Numero identificador de consulta externa realizada en listas restrictivas o fuentes externas, puede ser null si no se ha realizado consulta',
  fecha_consulta DATE NULL COMMENT 'Fecha en que se realizo la consulta',
  resultado_consulta BOOLEAN DEFAULT NULL COMMENT 'Resultado de la consulta: favorable (1) o desfavorable (0)',
  id_origen INT NOT NULL COMMENT 'Fuente de donde se obtuvo la informacion del socio',
  fecha_extraccion DATETIME NOT NULL COMMENT 'Fecha de extraccion de la informacion',
  validado BOOLEAN DEFAULT FALSE COMMENT 'Indica si la informacion del socio ha sido validada (1) o no (0)',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del socio',
  creado_por INT NOT NULL COMMENT 'Usuario que registro el socio',
  fecha_modificado DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_soc_prov FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_soc_tipo_identificacion FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_soc_origen FOREIGN KEY (id_origen) REFERENCES origen_dato(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_soc_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_soc_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Socios, accionistas o participes de proveedores personas juridicas';

CREATE TABLE validacion (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la validacion',
  id_usuario INT NOT NULL COMMENT 'Usuario que realizo la validacion',
  id_proveedor INT NOT NULL COMMENT 'Proveedor sobre el que se realiza la validacion',
  id_campo_validacion INT NOT NULL COMMENT 'Campo especifico que se esta validando',
  valor_web TEXT NULL COMMENT 'Valor obtenido de consultas web o fuentes externas',
  valor_documento TEXT NULL COMMENT 'Valor extraido del documento cargado',
  id_documento INT NULL COMMENT 'Documento utilizado para la validacion',
  resultado_validacion BOOLEAN DEFAULT NULL COMMENT 'Resultado: coincide (1) o no coincide (0)',
  fecha_validacion DATE NULL COMMENT 'Fecha en que se realizo la validacion',
  comentarios TEXT NULL COMMENT 'Observaciones o comentarios sobre la validacion',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro de la validacion',
  creado_por INT NOT NULL COMMENT 'Usuario que registro la validacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_val_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)     
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_campo FOREIGN KEY (id_campo_validacion) REFERENCES campo_validacion(id_campo_validacion)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_documento FOREIGN KEY (id_documento) REFERENCES documento(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_val_valfinal FOREIGN KEY (id_validacion_final) REFERENCES validacion_final(id_validacion_final)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Resultados de validacion de informacion de proveedores por campo especifico';

CREATE TABLE validacion_final (
  id_validacion_final INT AUTO_INCREMENT PRIMARY KEY,
  id_proveedor INT,
  comentario_final TEXT,
  estado_validacion VARCHAR(100),
  creado_por INT,
  fecha_creado DATETIME DEFAULT CURRENT_TIMESTAMP,
  modificado_por INT,
  fecha_modificado DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  activo BOOLEAN DEFAULT TRUE,
  CONSTRAINT fk_vf_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_vf_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_vf_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Unificacion de validaciones finales de proveedores';

CREATE TABLE evaluacion_proveedor (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la evaluacion',
  id_proveedor INT NOT NULL COMMENT 'Proveedor evaluado',
  id_usuario INT NOT NULL COMMENT 'Usuario que realizo la evaluacion',
  id_calificacion INT  NOT NULL COMMENT 'Calificacion asignada al proveedor',
  puntaje INT NOT NULL COMMENT 'Puntaje numerico de la evaluacion',
  observaciones TEXT NULL COMMENT 'Observaciones detalladas de la evaluacion',
  url_calificacion TEXT NOT NULL COMMENT 'URL o enlace a documentacion soporte de la calificacion',
  calidad INT NULL COMMENT 'Calificación del criterio de calidad (1-5)',
  obs_calidad TEXT NULL COMMENT 'Observación del criterio de calidad',
  tiempo INT NULL COMMENT 'Calificación del criterio de tiempo (1-5)',
  obs_tiempo TEXT NULL COMMENT 'Observación del criterio de tiempo',
  documenta INT NULL COMMENT 'Calificación del criterio documental (1-5)',
  obs_documental TEXT NULL COMMENT 'Observación del criterio documental',
  sarlaft INT NULL COMMENT 'Calificación del criterio SARLAFT (1-5)',
  obs_sarlaft TEXT NULL COMMENT 'Observación del criterio SARLAFT',
  comercial INT NULL COMMENT 'Calificación del criterio comercial (1-5)',
  obs_comercial TEXT NULL COMMENT 'Observación del criterio comercial',
  social INT NULL COMMENT 'Calificación del criterio de responsabilidad social (1-5)',
  obs_social TEXT NULL COMMENT 'Observación del criterio de responsabilidad social',
  mejora INT NULL COMMENT 'Calificación del criterio de innovación y mejora (1-5)',
  obs_mejora TEXT NULL COMMENT 'Observación del criterio de innovación y mejora',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro de la evaluacion',
  creado_por INT NOT NULL COMMENT 'Usuario que registro la evaluacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_eval_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_eval_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_eval_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_eval_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_eval_calificacion FOREIGN KEY (id_calificacion) REFERENCES calificacion(id)    
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Evaluaciones periodicas de desempeno y cumplimiento de proveedores';

CREATE TABLE notificaciones (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la notificacion',
  id_usuario INT NOT NULL COMMENT 'Usuario destinatario de la notificacion',
  id_tipo_notificacion INT NOT NULL COMMENT 'Tipo de notificacion',
  fecha_notificacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de envio de la notificacion',
  mensaje TEXT NOT NULL COMMENT 'Contenido del mensaje de notificacion',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro la notificacion',
  creado_por INT NOT NULL COMMENT 'Usuario que registro la notificacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_notif_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_notif_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_notif_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_notif_tipo FOREIGN KEY (id_tipo_notificacion) REFERENCES tipo_notificacion(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Notificaciones del sistema para usuarios (alertas, recordatorios, mensajes)';

CREATE TABLE historial_usuario (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico del registro historico',
  id_usuario INT NOT NULL COMMENT 'Usuario asociado al cambio de estado',
  id_estado_usuario INT NOT NULL  COMMENT 'Estado asignado al usuario',
  comentarios TEXT COMMENT 'Comentarios o justificacion del cambio de estado',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro del cambio de estado',
  creado_por INT NOT NULL COMMENT 'Usuario que registro el cambio de estado',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_hist_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_hist_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_hist_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_hist_estado FOREIGN KEY (id_estado_usuario) REFERENCES estado_usuario(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Historial de cambios de estado en cuentas de usuario para auditoria';


-- tablas de relacion muchos a muchos
CREATE TABLE proveedor_contacto (
  id_proveedor INT COMMENT 'Referencia al proveedor',
  id_contacto INT NOT NULL COMMENT 'Referencia al contacto',
  estado_contacto BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el contacto esta activo (1) o inactivo (0) para este proveedor',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de asignacion del contacto',
  creado_por INT NOT NULL COMMENT 'Usuario que realizo la asignacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  PRIMARY KEY (id_proveedor, id_contacto),
  CONSTRAINT fk_pc_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_pc_contacto FOREIGN KEY (id_contacto) REFERENCES contacto(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE,
    CONSTRAINT fk_pc_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_pc_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Relacion muchos-a-muchos entre proveedores y sus contactos';

CREATE TABLE ubicacion (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador unico de la ubicacion',
  id_proveedor INT NOT NULL COMMENT 'Proveedor al que pertenece esta ubicacion',
  id_municipio INT NOT NULL COMMENT 'Municipio/ciudad de la ubicacion',
  direccion TEXT NOT NULL COMMENT 'Direccion fisica completa',
   fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de asignacion de la ubicacion',
  creado_por INT NOT NULL COMMENT 'Usuario que realizo la asignacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  CONSTRAINT fk_ubicacion_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE,
  CONSTRAINT fk_ubicacion_municipio FOREIGN KEY (id_municipio) REFERENCES municipio(id)
    ON DELETE RESTRICT 
    ON UPDATE CASCADE,
  CONSTRAINT fk_ubicacion_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_ubicacion_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Direcciones fisicas y ubicaciones geograficas de proveedores';

CREATE TABLE representante_proveedor (
  id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Llave primaria subrogada para permitir historial',
  id_representante_legal INT NOT NULL COMMENT 'Representante legal',
  id_proveedor INT NOT NULL COMMENT 'Proveedor representado',
  cargo VARCHAR(150) NOT NULL COMMENT 'Cargo (ej: Gerente General, Suplente, Apoderado)',
  fecha_inicio DATE NOT NULL COMMENT 'Fecha legal en la que inicia la representación',
  fecha_fin DATE NULL COMMENT 'Fecha legal de fin. NULL indica indefinido/vigente',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  creado_por INT NOT NULL,
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  modificado_por INT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Soft delete lógico',
  CONSTRAINT fk_rp_rl FOREIGN KEY (id_representante_legal) 
    REFERENCES representante_legal(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_rp_prov FOREIGN KEY (id_proveedor) 
    REFERENCES proveedor(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_rp_creado FOREIGN KEY (creado_por) 
    REFERENCES usuarios(id),
  CONSTRAINT fk_rp_modif FOREIGN KEY (modificado_por) 
    REFERENCES usuarios(id)
) ENGINE=InnoDB COMMENT='Historial de asignaciones de representantes legales a proveedores';

CREATE TABLE documento_socio_proveedor (
  id_socio_proveedor INT COMMENT 'Socio al que pertenece el documento',
  id_documento INT COMMENT 'Documento que acredita al socio',
  fecha_creado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de asignacion del documento al socio',
  creado_por INT NOT NULL COMMENT 'Usuario que realizo la asignacion',
  fecha_modificado DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de ultima modificacion',
  modificado_por INT NULL COMMENT 'Usuario que realizo la ultima modificacion',
  activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Indica si el registro está activo (TRUE) o inactivo/eliminado (FALSE) para soft delete',
  PRIMARY KEY (id_socio_proveedor, id_documento),
  CONSTRAINT fk_dsp_socio FOREIGN KEY (id_socio_proveedor) REFERENCES socio_proveedor(id)
  ON DELETE RESTRICT 
  ON UPDATE CASCADE,
  CONSTRAINT fk_dsp_documento FOREIGN KEY (id_documento) REFERENCES documento(id)
  ON DELETE RESTRICT 
  ON UPDATE CASCADE,
  CONSTRAINT fk_dsp_creado_por FOREIGN KEY (creado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT fk_dsp_modificado_por FOREIGN KEY (modificado_por) REFERENCES usuarios(id)
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Relacion entre socios de proveedores y sus documentos acreditativos';


CREATE TABLE firma_token (
  id_token INT AUTO_INCREMENT PRIMARY KEY,
  id_proveedor INT NOT NULL,
  token VARCHAR(255) NOT NULL UNIQUE,
  fecha_expiracion DATETIME NOT NULL,
  utilizado BOOLEAN NOT NULL DEFAULT FALSE,
  fecha_firmado DATETIME,
  ip_firma VARCHAR(50),
  CONSTRAINT fk_ft_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id)
    ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB COMMENT='Tokens generados para firmas electronicas de documentos';

-- Indexes adicionales
CREATE INDEX idx_proveedores_numero_ident ON proveedor(numero_identificacion) COMMENT 'Indice para busquedas rapidas por numero de identificacion';
