USE golden_odds;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE golden_odds.calificacion;
TRUNCATE TABLE golden_odds.campo_validacion;
TRUNCATE TABLE golden_odds.contacto;
TRUNCATE TABLE golden_odds.departamento;
TRUNCATE TABLE golden_odds.documento;
TRUNCATE TABLE golden_odds.documento_socio_proveedor;
TRUNCATE TABLE golden_odds.estado_proveedor;
TRUNCATE TABLE golden_odds.estado_usuario;
TRUNCATE TABLE golden_odds.evaluacion_proveedor;
TRUNCATE TABLE golden_odds.evaluacion_riesgo;
TRUNCATE TABLE golden_odds.forma_de_pago;
TRUNCATE TABLE golden_odds.historial_usuario;
TRUNCATE TABLE golden_odds.municipio;
TRUNCATE TABLE golden_odds.notificacion;
TRUNCATE TABLE golden_odds.origen_dato;
TRUNCATE TABLE golden_odds.pais;
TRUNCATE TABLE golden_odds.proveedor_contacto;
TRUNCATE TABLE golden_odds.proveedor;
TRUNCATE TABLE golden_odds.representante_legal;
TRUNCATE TABLE golden_odds.representante_proveedor;
TRUNCATE TABLE golden_odds.rol;
TRUNCATE TABLE golden_odds.socio_proveedor;
TRUNCATE TABLE golden_odds.tipo_documento;
TRUNCATE TABLE golden_odds.tipo_identificacion;
TRUNCATE TABLE golden_odds.tipo_notificacion;
TRUNCATE TABLE golden_odds.tipo_pago;
TRUNCATE TABLE golden_odds.tipo_persona;
TRUNCATE TABLE golden_odds.tipo_telefono;
TRUNCATE TABLE golden_odds.ubicacion;
TRUNCATE TABLE golden_odds.usuario;
TRUNCATE TABLE golden_odds.validacion;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO tipo_identificacion (codigo, descripcion) VALUES 
('CC', 'Cédula de Ciudadanía'),
('CE', 'Cédula de Extranjería'),
('NIT', 'Número de Identificación Tributaria'),
('TI', 'Tarjeta de Identidad'),
('PP', 'Pasaporte'),
('RC', 'Registro Civil de Nacimiento'),
('TE', 'Tarjeta de Extranjería'),
('PEP', 'Permiso Especial de Permanencia'),
('AS', 'Carné de Adulto sin Identificación'),
('MS', 'Menor sin Identificación'),
('IE', 'Identificación de Extranjeros (No residentes)'),
('PPT', 'Permiso por Protección Temporal'),
('NUIP', 'Número Único de Identificación Personal');

INSERT INTO tipo_persona (codigo, descripcion) VALUES 
('NAT','Natural'),
('JUR','Juridica');

INSERT INTO tipo_telefono (codigo, descripcion) VALUES 
('MOV','Movil'),
('FIJ','Fijo'),
('OFI','Oficina');

INSERT INTO estado_proveedor (estado, descripcion) VALUES 
('ACTIVO','Proveedor activo'),
('INACTIVO','Proveedor inactivo'),
('SUSPENDIDO','Proveedor temporalmente suspendido');

INSERT INTO pais (codigo, nombre) VALUES 
('COL','Colombia');

INSERT INTO departamento (codigo, nombre, id_pais) VALUES 
('CO-AMA', 'Amazonas', 1),
('CO-ANT', 'Antioquia', 1),
('CO-ARA', 'Arauca', 1),
('CO-ATL', 'Atlántico', 1),
('CO-BOL', 'Bolívar', 1),
('CO-BOY', 'Boyacá', 1),
('CO-CAL', 'Caldas', 1),
('CO-CAQ', 'Caquetá', 1),
('CO-CAS', 'Casanare', 1),
('CO-CAU', 'Cauca', 1),
('CO-CES', 'Cesar', 1),
('CO-CHO', 'Chocó', 1),
('CO-COR', 'Córdoba', 1),
('CO-CUN', 'Cundinamarca', 1),
('CO-DC', 'Bogotá D.C.', 1),
('CO-GUA', 'Guainía', 1),
('CO-GUV', 'Guaviare', 1),
('CO-HUI', 'Huila', 1),
('CO-LAG', 'La Guajira', 1),
('CO-MAG', 'Magdalena', 1),
('CO-MET', 'Meta', 1),
('CO-NAR', 'Nariño', 1),
('CO-NSA', 'Norte de Santander', 1),
('CO-PUT', 'Putumayo', 1),
('CO-QUI', 'Quindío', 1),
('CO-RIS', 'Risaralda', 1),
('CO-SAN', 'Santander', 1),
('CO-SAP', 'San Andrés y Providencia', 1),
('CO-SUC', 'Sucre', 1),
('CO-TOL', 'Tolima', 1),
('CO-VAC', 'Valle del Cauca', 1),
('CO-VAU', 'Vaupés', 1),
('CO-VID', 'Vichada', 1);

INSERT INTO municipio (codigo, nombre, id_departamento) VALUES 
('LET', 'Leticia', 1),
('MED', 'Medellín', 2),
('ARA', 'Arauca', 3),
('BAQ', 'Barranquilla', 4),
('CTG', 'Cartagena de Indias', 5),
('TUN', 'Tunja', 6),
('MZL', 'Manizales', 7),
('FLO', 'Florencia', 8),
('YOP', 'Yopal', 9),
('POP', 'Popayán', 10),
('VUP', 'Valledupar', 11),
('QUI', 'Quibdó', 12),
('MTR', 'Montería', 13),
('SOA', 'Soacha', 14),
('BOG', 'Bogotá D.C.', 15),
('INI', 'Inírida', 16),
('SJG', 'San José del Guaviare', 17),
('NEI', 'Neiva', 18),
('RCH', 'Riohacha', 19),
('SMR', 'Santa Marta', 20),
('VVC', 'Villavicencio', 21),
('PSO', 'Pasto', 22),
('CUC', 'Cúcuta', 23),
('MOC', 'Mocoa', 24),
('ARM', 'Armenia', 25),
('PEI', 'Pereira', 26),
('BGA', 'Bucaramanga', 27),
('SAI', 'San Andrés', 28),
('SIN', 'Sincelejo', 29),
('IBG', 'Ibagué', 30),
('CLO', 'Cali', 31),
('MIT', 'Mitú', 32),
('PCR', 'Puerto Carreño', 33);

INSERT INTO tipo_pago (id, codigo, descripcion) VALUES 
(1, 'EFE', 'Efectivo'),
(2, 'TCR', 'Tarjeta de Crédito'),
(3, 'TDB', 'Tarjeta de Débito'),
(4, 'TRA', 'Transferencia Bancaria'),
(5, 'PSE', 'Pagos Seguros en Línea (PSE)'),
(6, 'CHQ', 'Cheque'),
(7, 'CON', 'Contra Entrega'),
(8, 'BON', 'Bono o Vale');

INSERT INTO origen_dato (codigo, descripcion) VALUES 
('MANUAL','Carga manual'),
('EXTERNO','Consulta externa');

INSERT INTO tipo_documento (id_tipo_documento, codigo, descripcion) VALUES 
(1, 'CAMARA', 'Certificado de Existencia y Representación Legal (Cámara de Comercio)'),
(2, 'RUT', 'Registro Único Tributario (RUT) actualizado'),
(3, 'ID_REP', 'Cédula de ciudadanía del representante legal'),
(4, 'EST_FIN', 'Estados financieros del último ejercicio'),
(5, 'RENTA', 'Declaración de renta del último año gravable'),
(6, 'CERT_BAN', 'Certificación bancaria (no mayor a 30 días)'),
(7, 'REF_COM', 'Referencia comercial'),
(8, 'COMP_ACC', 'Composición accionaria (Beneficiario Final > 5%)'),
(9, 'FORM_VINC', 'Formularios de conocimiento de la contraparte (Vinculación)'),
(10, 'ID_NAT', 'Fotocopia de la cédula de ciudadanía o extranjería'),
(11, 'CERT_ING', 'Certificación de ingresos o soporte de actividad económica');

INSERT INTO calificacion (id, codigo, descripcion) VALUES 
(1, 'CONFIABLE','APROBADO'),
(2, 'ALTERNATIVO','ALTERNATIVO'),
(3, 'NO CONFIABLE','NO CONFIABLE');

INSERT INTO tipo_notificacion (id, codigo, descripcion, activo) VALUES 
(1, 'SOL', 'Solicitud de Actualización de Datos', 1),
(2, 'APR', 'Aprobación de Documento', 1),
(3, 'REC', 'Rechazo/Devolución de Documento', 1),
(4, 'EST', 'Cambio de Estado del Proveedor', 1),
(5, 'EVA', 'Nueva Evaluación de Desempeño Disponible',  1),
(6, 'RGO', 'Alerta de Riesgo / Bloqueo', 1);

INSERT INTO estado_usuario (codigo, descripcion) VALUES 
('ACT','Activo'),
('INA','Inactivo');

INSERT INTO campo_validacion (id_campo_validacion, id_tipo_documento, campo, obligatorio, activo) VALUES
(1, 2, 'nit', TRUE, TRUE),
(2, 2, 'razon_social', TRUE, TRUE),
(3, 2, 'representante_legal', TRUE, TRUE),
(4, 2, 'composicion_accionaria', FALSE, TRUE),
(5, 1, 'nit', TRUE, TRUE),
(6, 1, 'razon_social', TRUE, TRUE),
(7, 1, 'representante_legal', TRUE, TRUE),
(8, 1, 'composicion_accionaria', TRUE, TRUE),
(9, 1, 'vigencia_camara', TRUE, TRUE),
(10, 6, 'nit', TRUE, TRUE),
(11, 6, 'razon_social', TRUE, TRUE),
(12, 6, 'vigencia_banco', TRUE, TRUE),
(13, 7, 'nit_refcom', TRUE, TRUE),
(14, 7, 'razon_social_refcom', TRUE, TRUE),
(15, 7, 'vigencia_refcom', TRUE, TRUE),
(16, 3, 'representante_legal', TRUE, TRUE);

-- Tablas trasaccionales

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO rol (id, rol, descripcion,estado_rol, creado_por) VALUES 
(1, 'ADMINISTRADOR', 'Acceso total a la gestión operativa, usuarios y configuración del sistema', 1, 1),
(2, 'JEFE_COMPRAS', 'Responsable de gestionar adquisiciones, órdenes de compra y negociación', 1, 1),
(3, 'PROVEEDOR', 'Usuario externo con acceso limitado para cargar documentos y ver estado de pagos', 1, 1),
(4, 'ANALISTA_RIESGOS', 'Encargado de validar antecedentes financieros y legales de los terceros', 1, 1),
(5, 'OFICIAL_CUMPLIMIENTO', 'Responsable de la prevención de lavado de activos y financiación del terrorismo', 1, 1),
(6, 'ANALISTA_CALIDAD', 'Auditor de procesos, certificaciones y cumplimiento de estándares de servicio', 1, 1);

INSERT INTO usuario (id, nombre_usuario, cargo_usuario, correo_usuario, contrasena, estado_usuario, id_rol, creado_por) VALUES 
(1, 'Super Administrador', 'Gerente General', 'admin@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 7);
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO usuario (id, nombre_usuario, cargo_usuario, correo_usuario, contrasena, estado_usuario, id_rol, creado_por) VALUES 
(2, 'Roberto Gomez', 'Jefe de Compras', 'roberto.gomez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 2, 1),
(3, 'Maria Rodriguez', 'Analista de Riesgos Senior', 'maria.rodriguez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 4, 1),
(4, 'Carlos Perez', 'Oficial de Cumplimiento', 'carlos.perez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 5, 1),
(5, 'Laura Martinez', 'Analista de Calidad', 'laura.martinez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 6, 1),
(6, 'Jorge Hernandez', 'Analista de Riesgos Junior', 'jorge.hernandez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 4, 1),
(7, 'Ana Torres', 'Auxiliar de Compras', 'ana.torres@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 2, 1),
(8, 'Luis Ramirez', 'Auditor Interno', 'luis.ramirez@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 5, 1),
(9, 'Sofia Vargas', 'Coordinadora de Calidad', 'sofia.vargas@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 6, 1),
(10, 'Diego Castro', 'Asistente Administrativo', 'diego.castro@goldenodds.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 1, 1),
(11, 'Andres Felipe', 'Representante Legal', 'gerencia@suministrosalpina.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(12, 'Camila Osorio', 'Gerente Comercial', 'ventas@tecnologiasdelsur.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(13, 'Fernando Ruiz', 'Director de Ventas', 'contacto@construccionesya.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(14, 'Paula Jimenez', 'Asesora Comercial', 'p.jimenez@insumosmedicos.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(15, 'Ricardo Morales', 'Gerente de Cuenta', 'rmorales@logisticaexpress.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(16, 'Valentina Rios', 'Ejecutiva de Ventas', 'vrios@papeleriaoficina.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(17, 'Mateo Gil', 'Director Comercial', 'mgil@servicioslimpieza.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(18, 'Daniela Herrera', 'Coordinadora de Proyectos', 'dherrera@consultoriait.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(19, 'Javier Lopez', 'Vendedor Senior', 'ventas@distribuidorajlo.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(20, 'Natalia Silva', 'Gerente Regional', 'nsilva@mueblesyenseres.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(21, 'Gabriel Mendoza', 'Gerente General', 'gmendoza@seguridadtotal.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(22, 'Isabella Rojas', 'Key Account Manager', 'irojas@solucionesweb.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(23, 'Samuel Ortega', 'Director de Operaciones', 'sortega@transportescol.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(24, 'Mariana Paredes', 'Representante de Ventas', 'mparedes@dotacionesind.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(25, 'Nicolas Fuentes', 'Gerente Comercial', 'nfuentes@agroinsumos.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(26, 'Lucia Navarro', 'Asistente Comercial', 'lnavarro@textilesdelvalle.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(27, 'Alejandro Mejia', 'Consultor Externo', 'amejia@asesoriaslegales.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(28, 'Valeria Espinoza', 'Directora de Marketing', 'vespinoza@publicidadcreativa.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(29, 'Tomas Pineda', 'Ingeniero de Soporte', 'tpineda@hardwaremasters.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(30, 'Sara Cabrera', 'Gerente Administrativa', 'scabrera@eventoscoorp.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(31, 'Emilio Cordoba', 'Distribuidor Autorizado', 'ecordoba@repuestosautos.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(32, 'Elena Ponce', 'Agente Comercial', 'eponce@segurosbolivar.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(33, 'Manuel Salinas', 'Contratista', 'msalinas@obrasciviles.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(34, 'Julia Montano', 'Proveedora de Servicios', 'jmontano@cateringpro.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(35, 'David Guzman', 'Socio Fundador', 'dguzman@innovaciontech.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(36, 'Carmen Villalobos', 'Gerente de Producto', 'cvillalobos@cosmeticosnat.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(37, 'Pablo Escobar', 'Distribuidor', 'pescobar@materialesferreos.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(38, 'Veronica Daza', 'Diseñadora', 'vdaza@imprentaexpress.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(39, 'Hugo Chavez', 'Proveedor Independiente', 'hchavez@mantenimientoyaa.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(40, 'Ximena Duque', 'Representante', 'xduque@enviosrapidos.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(41, 'Felipe Arias', 'Coordinador', 'farias@mensajeriaurbana.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(42, 'Gloria Trevi', 'Ventas Corporativas', 'gtrevi@uniformesyepp.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(43, 'Oscar Wilde', 'Consultor', 'owilde@redaccionpro.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(44, 'Diana Prince', 'Seguridad Privada', 'dprince@vigilancia247.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(45, 'Clark Kent', 'Periodista Freelance', 'ckent@dailyplanet.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(46, 'Bruce Wayne', 'CEO', 'bwayne@wayneenterprises.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(47, 'Tony Stark', 'Ingeniero Jefe', 'tstark@starkindustries.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(48, 'Natasha Romanoff', 'Consultora de Seguridad', 'nromanoff@shield.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(49, 'Steve Rogers', 'Capacitador', 'srogers@liderazgo.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(50, 'Peter Parker', 'Fotografo', 'pparker@freelance.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(51, 'Wanda Maximoff', 'Psicologa Organizacional', 'wmaximoff@bienestar.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(52, 'Stephen Strange', 'Medico Ocupacional', 'sstrange@saludtrabajo.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(53, 'Carol Danvers', 'Piloto', 'cdanvers@aeroenvios.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(54, 'Scott Lang', 'Tecnico de Seguridad', 'slang@pymtech.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2),
(55, 'Hope Van Dyne', 'Cientifica de Datos', 'hvandyne@pymtech.com', 'e10adc3949ba59abbe56e057f20f883e', 1, 3, 2);

INSERT INTO proveedor (
    id, id_tipo_identificacion, numero_identificacion, digito_verificacion, razon_social, 
    nombres, apellidos, id_tipo_persona, telefono_principal, id_tipo_telefono, 
    correo_principal, requiere_actualizacion, descripcion, creado_por, activo
) VALUES 
-- Proveedores Jurídicos (IDs 1-25)
(1, 2, '900123456', '1', 'Tecnología y Sistemas S.A.S.', NULL, NULL, 2, '6012345678', 2, 'contacto@tecnosistemas.com.co', 0, 'Venta de equipos de cómputo y licenciamiento de software', 1, 1),
(2, 2, '800987654', '3', 'Suministros de Oficina El Punto Ltda.', NULL, NULL, 2, '3109876543', 1, 'ventas@elpuntooficina.com', 0, 'Papelería y suministros de oficina al por mayor', 2, 1),
(3, 2, '901234567', '5', 'Aseo Total de Colombia S.A.', NULL, NULL, 2, '6025551234', 2, 'gerencia@aseototal.co', 1, 'Servicios de limpieza y mantenimiento locativo', 1, 1),
(4, 2, '890112233', '8', 'Distribuidora de Alimentos del Valle', NULL, NULL, 2, '3151234567', 1, 'pedidos@distrivalle.com', 0, 'Proveedores de insumos cafetería y alimentos', 3, 1),
(5, 2, '860000111', '2', 'Seguridad Privada El Escudo Ltda.', NULL, NULL, 2, '6044445566', 2, 'comercial@elescudo.com.co', 0, 'Servicios de vigilancia y seguridad física', 2, 1),
(6, 2, '900555666', '9', 'Transportes Rápidos S.A.S.', NULL, NULL, 2, '3001112233', 1, 'logistica@transportesrapidos.com', 0, 'Logística y transporte de mercancías', 1, 1),
(7, 2, '811222333', '4', 'Muebles y Diseños Modulares', NULL, NULL, 2, '6017778899', 2, 'proyectos@mueblesmodulares.co', 1, 'Fabricación y reparación de mobiliario de oficina', 4, 1),
(8, 2, '901888999', '0', 'Consultoría Financiera Integral S.A.S.', NULL, NULL, 2, '3205556677', 1, 'info@finanzasintegrales.com', 0, 'Auditoría y revisoría fiscal', 1, 1),
(9, 2, '830123456', '7', 'Ferretería Industrial La Tuerca', NULL, NULL, 2, '3114445566', 1, 'ventas@latuercaferreteria.com', 0, 'Materiales de construcción y herramientas', 2, 1),
(10, 2, '800444555', '6', 'Dotaciones Industriales de Occidente', NULL, NULL, 2, '6023334455', 2, 'comercial@dotacionesoccidente.com', 0, 'Uniformes y elementos de protección personal', 3, 1),
(11, 2, '900777111', '5', 'Impresos y Gráficas Digitales', NULL, NULL, 2, '3189990011', 1, 'produccion@impresosdigitales.co', 0, 'Servicios de imprenta, publicidad y papelería corporativa', 1, 1),
(12, 2, '899999000', '1', 'Servicios Eléctricos de la Costa', NULL, NULL, 2, '3012223344', 1, 'servicioalcliente@electricoscosta.com', 1, 'Mantenimiento de redes eléctricas y aire acondicionado', 5, 1),
(13, 2, '901000222', '3', 'Eventos y Banquetes Exclusivos', NULL, NULL, 2, '3104567890', 1, 'reservas@eventosexclusivos.com', 0, 'Catering y organización de eventos corporativos', 2, 1),
(14, 2, '822333444', '8', 'Constructora y Mantenimiento Vial', NULL, NULL, 2, '6018885522', 2, 'ingenieria@viascolombia.com', 0, 'Reparaciones locativas mayores y obra civil', 1, 1),
(15, 2, '900333444', '6', 'Inversiones Inmobiliarias El Roble', NULL, NULL, 2, '3123334455', 1, 'administracion@inmobiliariaelroble.com', 0, 'Arrendamiento de locales y oficinas', 4, 1),
(16, 2, '811555666', '9', 'Agencia de Viajes Corporativos Fly', NULL, NULL, 2, '3156667788', 1, 'tiquetes@flycorporativo.com', 0, 'Gestión de viajes y viáticos empresariales', 3, 1),
(17, 2, '901444555', '2', 'Soluciones de Marketing 360', NULL, NULL, 2, '6042223311', 2, 'marketing@soluciones360.com.co', 1, 'Publicidad digital y manejo de redes', 1, 1),
(18, 2, '800666777', '0', 'Laboratorio Clínico Empresarial', NULL, NULL, 2, '3009998877', 1, 'citas@laboratorioempresa.com', 0, 'Exámenes médicos de ingreso y periódicos', 2, 1),
(19, 2, '833444555', '4', 'Fumigaciones y Control de Plagas', NULL, NULL, 2, '3102221100', 1, 'contacto@fumigaciones.com', 0, 'Control de plagas y saneamiento ambiental', 1, 1),
(20, 2, '900888999', '7', 'Distribuidora de Papeles Higiénicos', NULL, NULL, 2, '6028889900', 2, 'ventas@papeleshigienicos.com', 0, 'Insumos de aseo para baños', 5, 1),
(21, 2, '800111222', '5', 'Cerrajería Master Key Ltda.', NULL, NULL, 2, '3134567890', 1, 'servicios@masterkey.com', 0, 'Servicios de cerrajería y seguridad electrónica', 3, 1),
(22, 2, '901555111', '8', 'Capacitaciones y Talento Humano', NULL, NULL, 2, '6015554433', 2, 'formacion@talentohumano.com', 0, 'Cursos y capacitaciones para el personal', 1, 1),
(23, 2, '822000111', '3', 'Servicios de Mensajería Express', NULL, NULL, 2, '3187776655', 1, 'despachos@mensajeriaexpress.com', 0, 'Mensajería urbana y nacional', 2, 1),
(24, 2, '900222888', '1', 'Software Contable Eficiente S.A.S.', NULL, NULL, 2, '6019998811', 2, 'soporte@softwarecontable.com', 1, 'Soporte y renovación de software contable', 4, 1),
(25, 2, '811999888', '6', 'Artesanías y Regalos Corporativos', NULL, NULL, 2, '3112223344', 1, 'ventas@artesaniascorp.com', 0, 'Regalos para clientes y empleados', 3, 1),

-- Proveedores Personas Naturales (IDs 26-50)
(26, 1, '79123456', NULL, NULL, 'Juan Carlos', 'Perez Garcia', 1, '3101112233', 1, 'juancarlos.perez@email.com', 0, 'Servicios profesionales de abogacía', 1, 1),
(27, 1, '52987654', NULL, NULL, 'Maria Alejandra', 'Rodriguez Lopez', 1, '3112223344', 1, 'mariarodriguez.conta@email.com', 0, 'Contadora independiente', 2, 1),
(28, 1, '10203040', NULL, NULL, 'Pedro Antonio', 'Gomez Diaz', 1, '3123334455', 1, 'pedrogomez.it@email.com', 0, 'Soporte técnico freelance', 1, 1),
(29, 1, '80123987', NULL, NULL, 'Luis Fernando', 'Martinez Ruiz', 1, '3134445566', 1, 'luismartinez.plomeria@email.com', 0, 'Servicios de plomería y fontanería', 3, 1),
(30, 1, '1030456789', NULL, NULL, 'Ana Beatriz', 'Hernandez Castro', 1, '3145556677', 1, 'ana.traducciones@email.com', 1, 'Servicios de traducción oficial', 4, 1),
(31, 1, '79555666', NULL, NULL, 'Jorge Eliecer', 'Ramirez Torres', 1, '3156667788', 1, 'jorge.ramirez.elec@email.com', 0, 'Electricista certificado', 2, 1),
(32, 1, '51888999', NULL, NULL, 'Claudia Patricia', 'Vargas Ortiz', 1, '3167778899', 1, 'claudiavargas.psico@email.com', 0, 'Consultoría en psicología organizacional', 1, 1),
(33, 1, '98765432', NULL, NULL, 'Andres Felipe', 'Castro Jimenez', 1, '3178889900', 1, 'andrescastro.arq@email.com', 0, 'Diseño arquitectónico y planos', 5, 1),
(34, 1, '1010222333', NULL, NULL, 'Sofia', 'Mendez Valencia', 1, '3189990011', 1, 'sofiamendez.diseno@email.com', 0, 'Diseño gráfico freelance', 2, 1),
(35, 1, '71222333', NULL, NULL, 'Ricardo', 'Silva Montoya', 1, '3190001122', 1, 'ricardosilva.transporte@email.com', 0, 'Transporte de carga liviana', 1, 1),
(36, 1, '31444555', NULL, NULL, 'Elena', 'Duarte Pineda', 1, '3201112233', 1, 'elena.catering@email.com', 0, 'Refrigerios y desayunos empresariales', 3, 1),
(37, 1, '1098765432', NULL, NULL, 'Camilo Andres', 'Rios Bernal', 1, '3212223344', 1, 'camilorios.dev@email.com', 1, 'Desarrollador de software web', 1, 1),
(38, 1, '88123123', NULL, NULL, 'Fernando', 'Lopez Osorio', 1, '3223334455', 1, 'fernandolopez.manto@email.com', 0, 'Mantenimiento de aires acondicionados', 4, 1),
(39, 1, '63456789', NULL, NULL, 'Gloria Ines', 'Suarez Mejia', 1, '3234445566', 1, 'gloriasuarez.aseo@email.com', 0, 'Servicios generales por días', 2, 1),
(40, 1, '94567890', NULL, NULL, 'Hector', 'Vega Cordoba', 1, '3245556677', 1, 'hectorvega.jardin@email.com', 0, 'Jardinería y paisajismo', 1, 1),
(41, 1, '1122334455', NULL, NULL, 'Isabel Cristina', 'Pena Lara', 1, '3256667788', 1, 'isabelpena.cm@email.com', 0, 'Community Manager freelance', 3, 1),
(42, 1, '75111222', NULL, NULL, 'Javier', 'Buitrago Salinas', 1, '3267778899', 1, 'javierbuitrago.photo@email.com', 0, 'Fotografía corporativa y de producto', 2, 1),
(43, 1, '32333444', NULL, NULL, 'Laura', 'Guzman Tovar', 1, '3278889900', 1, 'lauraguzman.eventos@email.com', 1, 'Coordinadora de eventos', 1, 1),
(44, 1, '1055666777', NULL, NULL, 'Manuel', 'Ortega Rojas', 1, '3289990011', 1, 'manuelortega.pintura@email.com', 0, 'Pintura y acabados locativos', 5, 1),
(45, 1, '93777888', NULL, NULL, 'Natalia', 'Cardenas Florez', 1, '3290001122', 1, 'nataliacardenas.coach@email.com', 0, 'Coaching empresarial', 4, 1),
(46, 1, '80222333', NULL, NULL, 'Oscar', 'Morales Gil', 1, '3001230987', 1, 'oscarmorales.seguridad@email.com', 0, 'Asesoría en seguridad industrial (SST)', 1, 1),
(47, 1, '52111000', NULL, NULL, 'Patricia', 'Henao Restrepo', 1, '3019876543', 1, 'patriciahenao.redaccion@email.com', 0, 'Redacción de contenidos y corrección de estilo', 2, 1),
(48, 1, '1088776655', NULL, NULL, 'Roberto', 'Navarro Cruz', 1, '3024567890', 1, 'robertonavarro.mensajero@email.com', 0, 'Diligencias y trámites notariales', 3, 1),
(49, 1, '71555444', NULL, NULL, 'Sandra Milena', 'Patiño Duque', 1, '3031112233', 1, 'sandrapatino.flores@email.com', 0, 'Arreglos florales para oficinas', 1, 1),
(50, 1, '98222111', NULL, NULL, 'Tomas', 'Uribe Velez', 1, '3042223344', 1, 'tomasuribe.musica@email.com', 0, 'Servicios de ambientación musical para eventos', 2, 1);

INSERT INTO contacto (
    nombre_contacto, cargo_contacto, id_tipo_telefono, telefono_contacto, 
    correo_contacto, creado_por, activo
) VALUES 
-- CONTACTOS PARA PROVEEDOR 1 (Tecnología y Sistemas)
-- Contacto Activo
('Camilo Torres', 'Gerente Comercial', 1, '3105551234', 'camilo.torres@tecnosistemas.com.co', 1, 1),
-- Contactos Inactivos (Históricos del mismo proveedor)
('Roberto Antiguo', 'Ex-Director de Ventas', 1, '3100000000', 'roberto.old@tecnosistemas.com.co', 1, 0),
('Laura Pasada', 'Ex-Asistente Administrativa', 2, '6015550000', 'laura.old@tecnosistemas.com.co', 1, 0),

-- CONTACTOS PARA PROVEEDORES JURÍDICOS (IDs 2-25)
('Ana Maria Velez', 'Jefe de Ventas', 1, '3111234567', 'ana.velez@elpuntooficina.com', 2, 1),
('Luis Fernando Diaz', 'Coordinador de Operaciones', 1, '3122345678', 'operaciones@aseototal.co', 1, 1),
('Marta Lucía Gomez', 'Asesora Comercial', 2, '6013334455', 'marta.gomez@distrivalle.com', 3, 1),
('Carlos Andres Ruiz', 'Jefe de Seguridad', 1, '3133456789', 'seguridad@elescudo.com.co', 2, 1),
('Jorge Eliecer Pinto', 'Gerente de Logística', 1, '3144567890', 'jorge.pinto@transportesrapidos.com', 1, 1),
('Sofia Vergara', 'Diseñadora Senior', 2, '6014445566', 'sofia.v@mueblesmodulares.co', 4, 1),
('Andres Felipe Arias', 'Auditor Senior', 1, '3155678901', 'andres.arias@finanzasintegrales.com', 1, 1),
('Pedro Pablo Leon', 'Administrador', 2, '6015556677', 'pedro.leon@latuercaferreteria.com', 2, 1),
('Maria Clara Jaramillo', 'Ejecutiva de Cuenta', 1, '3166789012', 'maria.jaramillo@dotacionesoccidente.com', 3, 1),
('Diego Fernando Castro', 'Jefe de Producción', 1, '3177890123', 'diego.castro@impresosdigitales.co', 1, 1),
('Elena Patricia Rojas', 'Coordinadora de Servicios', 2, '6016667788', 'elena.rojas@electricoscosta.com', 5, 1),
('Santiago Mejia', 'Chef Ejecutivo', 1, '3188901234', 'santiago.mejia@eventosexclusivos.com', 2, 1),
('Ingeniero Juan Borda', 'Director de Obras', 1, '3199012345', 'juan.borda@viascolombia.com', 1, 1),
('Patricia Rodriguez', 'Agente Inmobiliario', 1, '3200123456', 'patricia.r@inmobiliariaelroble.com', 4, 1),
('Valentina Espinoza', 'Consultora de Viajes', 2, '6017778899', 'valentina.e@flycorporativo.com', 3, 1),
('Felipe Noguera', 'Creative Manager', 1, '3211234567', 'felipe.n@soluciones360.com.co', 1, 1),
('Dra. Clara Ines Lopez', 'Bacterióloga Jefe', 2, '6018889900', 'clara.lopez@laboratorioempresa.com', 2, 1),
('Humberto Casas', 'Técnico Aplicador', 1, '3222345678', 'humberto.c@fumigaciones.com', 1, 1),
('Gloria Mayorga', 'Vendedora Mostrador', 2, '6019990011', 'gloria.m@papeleshigienicos.com', 5, 1),
('Victor Manuel Gil', 'Cerrajero Principal', 1, '3233456789', 'victor.gil@masterkey.com', 3, 1),
('Dra. Lucia Mendez', 'Psicóloga Organizacional', 1, '3244567890', 'lucia.mendez@talentohumano.com', 1, 1),
('Kevin Alexander Ortiz', 'Despachador', 1, '3255678901', 'kevin.ortiz@mensajeriaexpress.com', 2, 1),
('Ingeniera Diana Soler', 'Soporte Nivel 2', 2, '6010001122', 'diana.soler@softwarecontable.com', 4, 1),
('Rosalba Pineda', 'Artesana Líder', 1, '3266789012', 'rosalba.p@artesaniascorp.com', 3, 1),

-- CONTACTOS PARA PROVEEDORES PERSONAS NATURALES (IDs 26-50)
('Juan Carlos Perez', 'Abogado Titular', 1, '3101112233', 'juancarlos.perez@email.com', 1, 1),
('Asistente Sonia Lopez', 'Secretaria', 2, '6011112233', 'recepcion@mariarodriguez.conta', 2, 1),
('Pedro Antonio Gomez', 'Soporte Técnico', 1, '3123334455', 'pedrogomez.it@email.com', 1, 1),
('Luis Martinez', 'Plomero', 1, '3134445566', 'luismartinez.plomeria@email.com', 3, 1),
('Ana Beatriz Hernandez', 'Traductora', 1, '3145556677', 'ana.traducciones@email.com', 4, 1),
('Jorge Ramirez', 'Técnico Electricista', 1, '3156667788', 'jorge.ramirez.elec@email.com', 2, 1),
('Claudia Vargas', 'Consultora', 1, '3167778899', 'claudiavargas.psico@email.com', 1, 1),
('Arq. Andres Castro', 'Arquitecto', 1, '3178889900', 'andrescastro.arq@email.com', 5, 1),
('Sofia Mendez', 'Diseñadora', 1, '3189990011', 'sofiamendez.diseno@email.com', 2, 1),
('Ricardo Silva', 'Conductor', 1, '3190001122', 'ricardosilva.transporte@email.com', 1, 1),
('Elena Duarte', 'Cocinera Jefe', 1, '3201112233', 'elena.catering@email.com', 3, 1),
('Camilo Rios', 'Full Stack Dev', 1, '3212223344', 'camilorios.dev@email.com', 1, 1),
('Fernando Lopez', 'Técnico Aire', 1, '3223334455', 'fernandolopez.manto@email.com', 4, 1),
('Gloria Suarez', 'Auxiliar de Aseo', 1, '3234445566', 'gloriasuarez.aseo@email.com', 2, 1),
('Hector Vega', 'Jardinero', 1, '3245556677', 'hectorvega.jardin@email.com', 1, 1),
('Isabel Pena', 'Community Manager', 1, '3256667788', 'isabelpena.cm@email.com', 3, 1),
('Javier Buitrago', 'Fotógrafo', 1, '3267778899', 'javierbuitrago.photo@email.com', 2, 1),
('Laura Guzman', 'Planner', 1, '3278889900', 'lauraguzman.eventos@email.com', 1, 1),
('Manuel Ortega', 'Pintor', 1, '3289990011', 'manuelortega.pintura@email.com', 5, 1),
('Natalia Cardenas', 'Coach', 1, '3290001122', 'nataliacardenas.coach@email.com', 4, 1),
('Oscar Morales', 'Inspector SST', 1, '3001230987', 'oscarmorales.seguridad@email.com', 1, 1),
('Patricia Henao', 'Editora', 1, '3019876543', 'patriciahenao.redaccion@email.com', 2, 1),
('Roberto Navarro', 'Mensajero', 1, '3024567890', 'robertonavarro.mensajero@email.com', 3, 1),
('Sandra Patiño', 'Florista', 1, '3031112233', 'sandrapatino.flores@email.com', 1, 1),
('Tomas Uribe', 'Músico', 1, '3042223344', 'tomasuribe.musica@email.com', 2, 1),
('Mariana Pajon', 'Representante Legal', 1, '3053334455', 'mariana.rep@proveedorextra.com', 1, 1),
('Nairo Quintana', 'Gerente General', 1, '3064445566', 'nairo.gerencia@proveedorextra2.com', 2, 1),
('Egan Bernal', 'Director Comercial', 1, '3075556677', 'egan.comercial@proveedorextra3.com', 3, 1);

INSERT INTO forma_de_pago (id_proveedor, id_tipo_pago, monto, plazo, creado_por, modificado_por, activo
) VALUES 
(1, 4, 50000000.00, 30, 1, 1, 1),
(2, 1, 0.00, 0, 2, 2, 1),
(3, 4, 20000000.00, 60, 1, 1, 1),
(4, 4, 5000000.00, 30, 3, 3, 1),
(5, 4, 10000000.00, 30, 2, 2, 1),
(6, 7, 0.00, 0, 1, 1, 1),
(7, 5, 10000000.00, 0, 4, 4, 1),
(8, 4, 15000000.00, 30, 1, 1, 1),
(9, 1, 0.00, 0, 2, 2, 1),
(10, 4, 15000000.00, 60, 3, 3, 1),
(11, 4, 8000000.00, 30, 1, 1, 1),
(12, 6, 0.00, 0, 5, 5, 1),
(13, 5, 5000000.00, 0, 2, 2, 1),
(14, 4, 100000000.00, 60, 1, 1, 1),
(15, 4, 12000000.00, 5, 4, 4, 1),
(16, 2, 0.00, 0, 3, 3, 1),
(17, 4, 6000000.00, 30, 1, 1, 1),
(18, 4, 4500000.00, 30, 2, 2, 1),
(19, 1, 0.00, 0, 1, 1, 1),
(20, 4, 3000000.00, 30, 5, 5, 1),
(21, 1, 0.00, 0, 3, 3, 1),
(22, 5, 2500000.00, 0, 1, 1, 1),
(23, 4, 1000000.00, 30, 2, 2, 1),
(24, 4, 18000000.00, 0, 4, 4, 1),
(25, 1, 0.00, 0, 3, 3, 1),
(26, 4, 5000000.00, 15, 1, 1, 1),
(27, 4, 3500000.00, 15, 2, 2, 1),
(28, 5, 0.00, 0, 1, 1, 1),
(29, 1, 0.00, 0, 3, 3, 1),
(30, 6, 0.00, 0, 4, 4, 1),
(31, 1, 1000000.00, 0, 2, 2, 1),
(31, 4, 5000000.00, 15, 2, 2, 1),
(32, 5, 0.00, 0, 1, 1, 1),
(32, 6, 0.00, 0, 1, 1, 1),
(33, 5, 20000000.00, 0, 5, 5, 1),
(33, 4, 40000000.00, 30, 5, 5, 1),
(34, 5, 500000.00, 0, 2, 2, 1),
(34, 4, 2000000.00, 15, 2, 2, 1),
(35, 1, 0.00, 0, 1, 1, 1),
(35, 7, 500000.00, 0, 1, 1, 1),
(36, 5, 2000000.00, 0, 3, 3, 1),
(36, 7, 0.00, 0, 3, 3, 1),
(37, 4, 8000000.00, 15, 1, 1, 1),
(37, 5, 15000000.00, 0, 1, 1, 1),
(38, 1, 0.00, 0, 4, 4, 1),
(38, 4, 3000000.00, 30, 4, 4, 1),
(39, 1, 0.00, 0, 2, 2, 1),
(39, 6, 1000000.00, 5, 2, 2, 1),
(40, 1, 0.00, 0, 1, 1, 1),
(40, 4, 1500000.00, 15, 1, 1, 1),
(41, 5, 2000000.00, 0, 3, 3, 1),
(41, 4, 4000000.00, 30, 3, 3, 1),
(42, 5, 1000000.00, 0, 2, 2, 1),
(42, 1, 1000000.00, 0, 2, 2, 1),
(43, 5, 5000000.00, 0, 1, 1, 1),
(43, 4, 10000000.00, 30, 1, 1, 1),
(44, 1, 0.00, 0, 5, 5, 1),
(44, 7, 0.00, 0, 5, 5, 1),
(45, 5, 0.00, 0, 4, 4, 1),
(45, 4, 6000000.00, 30, 4, 4, 1),
(46, 4, 5000000.00, 30, 1, 1, 1),
(46, 6, 12000000.00, 60, 1, 1, 1),
(47, 5, 0.00, 0, 2, 2, 1),
(47, 4, 2000000.00, 15, 2, 2, 1),
(48, 1, 200000.00, 0, 3, 3, 1),
(48, 4, 1500000.00, 15, 3, 3, 1),
(49, 1, 0.00, 0, 1, 1, 1),
(49, 4, 1000000.00, 30, 1, 1, 1),
(50, 5, 2500000.00, 0, 2, 2, 1),
(50, 1, 2500000.00, 0, 2, 2, 1);

INSERT INTO documento (
    id_proveedor, id_tipo_documento, numero_documento, fecha_emision, 
    url_documento, almacenamiento, hash, tamano_bytes, fecha_carga, 
    validado, creado_por, modificado_por, estado_documento
) VALUES 
-- DOCUMENTO 1: RUT (Para TODOS los proveedores 1-50)
-- Todos los proveedores formales deben tener RUT
(1, 1, 'RUT-900123456', '2023-01-10', 's3://docs/prov_1/rut_2023.pdf', 's3', 'a1b2c3d4e5', 102400, '2023-01-15', 1, 1, 1, 1),
(2, 1, 'RUT-800987654', '2023-01-12', 's3://docs/prov_2/rut.pdf', 's3', 'f6g7h8i9j0', 204800, '2023-01-16', 1, 2, 2, 1),
(3, 1, 'RUT-901234567', '2023-02-01', 's3://docs/prov_3/rut_updated.pdf', 's3', 'k1l2m3n4o5', 150000, '2023-02-05', 1, 1, 1, 1),
(4, 1, 'RUT-890112233', '2023-01-20', 's3://docs/prov_4/rut.pdf', 's3', 'p6q7r8s9t0', 110000, '2023-01-25', 1, 3, 3, 1),
(5, 1, 'RUT-860000111', '2023-03-05', 's3://docs/prov_5/rut.pdf', 's3', 'u1v2w3x4y5', 130000, '2023-03-10', 1, 2, 2, 1),
(6, 1, 'RUT-900555666', '2023-01-10', 's3://docs/prov_6/rut.pdf', 's3', 'z6a7b8c9d0', 125000, '2023-01-12', 1, 1, 1, 1),
(7, 1, 'RUT-811222333', '2023-02-15', 's3://docs/prov_7/rut.pdf', 's3', 'e1f2g3h4i5', 145000, '2023-02-20', 0, 4, 4, 1),
(8, 1, 'RUT-901888999', '2023-04-01', 's3://docs/prov_8/rut.pdf', 's3', 'j6k7l8m9n0', 160000, '2023-04-05', 1, 1, 1, 1),
(9, 1, 'RUT-830123456', '2023-01-05', 's3://docs/prov_9/rut.pdf', 's3', 'o1p2q3r4s5', 105000, '2023-01-08', 1, 2, 2, 1),
(10, 1, 'RUT-800444555', '2023-05-10', 's3://docs/prov_10/rut.pdf', 's3', 't6u7v8w9x0', 190000, '2023-05-15', 1, 3, 3, 1),
(11, 1, 'RUT-900777111', '2023-02-28', 's3://docs/prov_11/rut.pdf', 's3', 'y1z2a3b4c5', 115000, '2023-03-05', 1, 1, 1, 1),
(12, 1, 'RUT-899999000', '2023-01-18', 's3://docs/prov_12/rut.pdf', 's3', 'd6e7f8g9h0', 135000, '2023-01-22', 1, 5, 5, 1),
(13, 1, 'RUT-901000222', '2023-03-15', 's3://docs/prov_13/rut.pdf', 's3', 'i1j2k3l4m5', 155000, '2023-03-20', 1, 2, 2, 1),
(14, 1, 'RUT-822333444', '2023-01-25', 's3://docs/prov_14/rut.pdf', 's3', 'n6o7p8q9r0', 170000, '2023-01-30', 1, 1, 1, 1),
(15, 1, 'RUT-900333444', '2023-04-10', 's3://docs/prov_15/rut.pdf', 's3', 's1t2u3v4w5', 120000, '2023-04-15', 0, 4, 4, 1),
(16, 1, 'RUT-811555666', '2023-02-05', 's3://docs/prov_16/rut.pdf', 's3', 'x6y7z8a9b0', 140000, '2023-02-10', 1, 3, 3, 1),
(17, 1, 'RUT-901444555', '2023-01-30', 's3://docs/prov_17/rut.pdf', 's3', 'c1d2e3f4g5', 130000, '2023-02-05', 1, 1, 1, 1),
(18, 1, 'RUT-800666777', '2023-03-22', 's3://docs/prov_18/rut.pdf', 's3', 'h6i7j8k9l0', 165000, '2023-03-25', 1, 2, 2, 1),
(19, 1, 'RUT-833444555', '2023-05-01', 's3://docs/prov_19/rut.pdf', 's3', 'm1n2o3p4q5', 110000, '2023-05-05', 1, 1, 1, 1),
(20, 1, 'RUT-900888999', '2023-01-11', 's3://docs/prov_20/rut.pdf', 's3', 'r6s7t8u9v0', 180000, '2023-01-15', 1, 5, 5, 1),
(21, 1, 'RUT-800111222', '2023-02-18', 's3://docs/prov_21/rut.pdf', 's3', 'w1x2y3z4a5', 125000, '2023-02-20', 1, 3, 3, 1),
(22, 1, 'RUT-901555111', '2023-04-20', 's3://docs/prov_22/rut.pdf', 's3', 'b6c7d8e9f0', 150000, '2023-04-25', 1, 1, 1, 1),
(23, 1, 'RUT-822000111', '2023-03-08', 's3://docs/prov_23/rut.pdf', 's3', 'g1h2i3j4k5', 135000, '2023-03-12', 1, 2, 2, 1),
(24, 1, 'RUT-900222888', '2023-01-08', 's3://docs/prov_24/rut.pdf', 's3', 'l6m7n8o9p0', 145000, '2023-01-12', 1, 4, 4, 1),
(25, 1, 'RUT-811999888', '2023-05-15', 's3://docs/prov_25/rut.pdf', 's3', 'q1r2s3t4u5', 160000, '2023-05-20', 1, 3, 3, 1),
-- Personas Naturales (IDs 26-50) también con RUT
(26, 1, 'RUT-79123456', '2023-01-10', 's3://docs/prov_26/rut.pdf', 's3', 'v6w7x8y9z0', 90000, '2023-01-15', 1, 1, 1, 1),
(27, 1, 'RUT-52987654', '2023-02-12', 's3://docs/prov_27/rut.pdf', 's3', 'a2b3c4d5e6', 95000, '2023-02-15', 1, 2, 2, 1),
(28, 1, 'RUT-10203040', '2023-03-20', 's3://docs/prov_28/rut.pdf', 's3', 'f7g8h9i0j1', 88000, '2023-03-25', 1, 1, 1, 1),
(29, 1, 'RUT-80123987', '2023-01-25', 's3://docs/prov_29/rut.pdf', 's3', 'k2l3m4n5o6', 92000, '2023-01-30', 1, 3, 3, 1),
(30, 1, 'RUT-1030456789', '2023-04-10', 's3://docs/prov_30/rut.pdf', 's3', 'p7q8r9s0t1', 98000, '2023-04-15', 1, 4, 4, 1),
(31, 1, 'RUT-79555666', '2023-02-05', 's3://docs/prov_31/rut.pdf', 's3', 'u2v3w4x5y6', 91000, '2023-02-10', 1, 2, 2, 1),
(32, 1, 'RUT-51888999', '2023-01-15', 's3://docs/prov_32/rut.pdf', 's3', 'z7a8b9c0d1', 93000, '2023-01-20', 1, 1, 1, 1),
(33, 1, 'RUT-98765432', '2023-05-01', 's3://docs/prov_33/rut.pdf', 's3', 'e2f3g4h5i6', 89000, '2023-05-05', 1, 5, 5, 1),
(34, 1, 'RUT-1010222333', '2023-03-10', 's3://docs/prov_34/rut.pdf', 's3', 'j7k8l9m0n1', 94000, '2023-03-15', 1, 2, 2, 1),
(35, 1, 'RUT-71222333', '2023-02-20', 's3://docs/prov_35/rut.pdf', 's3', 'o2p3q4r5s6', 96000, '2023-02-25', 1, 1, 1, 1),
(36, 1, 'RUT-31444555', '2023-01-05', 's3://docs/prov_36/rut.pdf', 's3', 't7u8v9w0x1', 90000, '2023-01-10', 1, 3, 3, 1),
(37, 1, 'RUT-1098765432', '2023-04-25', 's3://docs/prov_37/rut.pdf', 's3', 'y2z3a4b5c6', 97000, '2023-04-30', 1, 1, 1, 1),
(38, 1, 'RUT-88123123', '2023-03-01', 's3://docs/prov_38/rut.pdf', 's3', 'd7e8f9g0h1', 92000, '2023-03-05', 1, 4, 4, 1),
(39, 1, 'RUT-63456789', '2023-01-20', 's3://docs/prov_39/rut.pdf', 's3', 'i2j3k4l5m6', 91000, '2023-01-25', 1, 2, 2, 1),
(40, 1, 'RUT-94567890', '2023-05-10', 's3://docs/prov_40/rut.pdf', 's3', 'n7o8p9q0r1', 88000, '2023-05-15', 1, 1, 1, 1),
(41, 1, 'RUT-1122334455', '2023-02-28', 's3://docs/prov_41/rut.pdf', 's3', 's2t3u4v5w6', 95000, '2023-03-05', 1, 3, 3, 1),
(42, 1, 'RUT-75111222', '2023-01-12', 's3://docs/prov_42/rut.pdf', 's3', 'x7y8z9a0b1', 93000, '2023-01-16', 1, 2, 2, 1),
(43, 1, 'RUT-32333444', '2023-04-15', 's3://docs/prov_43/rut.pdf', 's3', 'c2d3e4f5g6', 96000, '2023-04-20', 1, 1, 1, 1),
(44, 1, 'RUT-1055666777', '2023-03-25', 's3://docs/prov_44/rut.pdf', 's3', 'h7i8j9k0l1', 92000, '2023-03-30', 1, 5, 5, 1),
(45, 1, 'RUT-93777888', '2023-02-10', 's3://docs/prov_45/rut.pdf', 's3', 'm2n3o4p5q6', 94000, '2023-02-15', 1, 4, 4, 1),
(46, 1, 'RUT-80222333', '2023-01-30', 's3://docs/prov_46/rut.pdf', 's3', 'r7s8t9u0v1', 90000, '2023-02-05', 1, 1, 1, 1),
(47, 1, 'RUT-52111000', '2023-05-05', 's3://docs/prov_47/rut.pdf', 's3', 'w2x3y4z5a6', 91000, '2023-05-10', 1, 2, 2, 1),
(48, 1, 'RUT-1088776655', '2023-03-15', 's3://docs/prov_48/rut.pdf', 's3', 'b7c8d9e0f1', 95000, '2023-03-20', 1, 3, 3, 1),
(49, 1, 'RUT-71555444', '2023-01-05', 's3://docs/prov_49/rut.pdf', 's3', 'g2h3i4j5k6', 89000, '2023-01-10', 1, 1, 1, 1),
(50, 1, 'RUT-98222111', '2023-04-01', 's3://docs/prov_50/rut.pdf', 's3', 'l7m8n9o0p1', 93000, '2023-04-05', 1, 2, 2, 1),
-- DOCUMENTO 2: CÁMARA DE COMERCIO (Para Proveedores Jurídicos 1-25)
(1, 2, 'CAM-001', '2023-01-10', 's3://docs/prov_1/camara.pdf', 's3', 'q2r3s4t5u6', 500000, '2023-01-15', 1, 1, 1, 1),
(2, 2, 'CAM-002', '2023-01-12', 's3://docs/prov_2/camara.pdf', 's3', 'v7w8x9y0z1', 480000, '2023-01-16', 1, 2, 2, 1),
(3, 2, 'CAM-003', '2023-02-01', 's3://docs/prov_3/camara.pdf', 's3', 'a3b4c5d6e7', 520000, '2023-02-05', 1, 1, 1, 1),
(4, 2, 'CAM-004', '2023-01-20', 's3://docs/prov_4/camara.pdf', 's3', 'f8g9h0i1j2', 510000, '2023-01-25', 1, 3, 3, 1),
(5, 2, 'CAM-005', '2023-03-05', 's3://docs/prov_5/camara.pdf', 's3', 'k3l4m5n6o7', 490000, '2023-03-10', 1, 2, 2, 1),
(6, 2, 'CAM-006', '2023-01-10', 's3://docs/prov_6/camara.pdf', 's3', 'p8q9r0s1t2', 505000, '2023-01-12', 1, 1, 1, 1),
(7, 2, 'CAM-007', '2023-02-15', 's3://docs/prov_7/camara.pdf', 's3', 'u3v4w5x6y7', 515000, '2023-02-20', 1, 4, 4, 1),
(8, 2, 'CAM-008', '2023-04-01', 's3://docs/prov_8/camara.pdf', 's3', 'z8a9b0c1d2', 500000, '2023-04-05', 1, 1, 1, 1),
(9, 2, 'CAM-009', '2023-01-05', 's3://docs/prov_9/camara.pdf', 's3', 'e3f4g5h6i7', 495000, '2023-01-08', 1, 2, 2, 1),
(10, 2, 'CAM-010', '2023-05-10', 's3://docs/prov_10/camara.pdf', 's3', 'j8k9l0m1n2', 530000, '2023-05-15', 1, 3, 3, 1),
(11, 2, 'CAM-011', '2023-02-28', 's3://docs/prov_11/camara.pdf', 's3', 'o3p4q5r6s7', 525000, '2023-03-05', 1, 1, 1, 1),
(12, 2, 'CAM-012', '2023-01-18', 's3://docs/prov_12/camara.pdf', 's3', 't8u9v0w1x2', 485000, '2023-01-22', 1, 5, 5, 1),
(13, 2, 'CAM-013', '2023-03-15', 's3://docs/prov_13/camara.pdf', 's3', 'y3z4a5b6c7', 540000, '2023-03-20', 1, 2, 2, 1),
(14, 2, 'CAM-014', '2023-01-25', 's3://docs/prov_14/camara.pdf', 's3', 'd8e9f0g1h2', 510000, '2023-01-30', 1, 1, 1, 1),
(15, 2, 'CAM-015', '2023-04-10', 's3://docs/prov_15/camara.pdf', 's3', 'i3j4k5l6m7', 500000, '2023-04-15', 1, 4, 4, 1),
(16, 2, 'CAM-016', '2023-02-05', 's3://docs/prov_16/camara.pdf', 's3', 'n8o9p0q1r2', 520000, '2023-02-10', 1, 3, 3, 1),
(17, 2, 'CAM-017', '2023-01-30', 's3://docs/prov_17/camara.pdf', 's3', 's3t4u5v6w7', 505000, '2023-02-05', 1, 1, 1, 1),
(18, 2, 'CAM-018', '2023-03-22', 's3://docs/prov_18/camara.pdf', 's3', 'x8y9z0a1b2', 490000, '2023-03-25', 1, 2, 2, 1),
(19, 2, 'CAM-019', '2023-05-01', 's3://docs/prov_19/camara.pdf', 's3', 'c3d4e5f6g7', 515000, '2023-05-05', 1, 1, 1, 1),
(20, 2, 'CAM-020', '2023-01-11', 's3://docs/prov_20/camara.pdf', 's3', 'h8i9j0k1l2', 500000, '2023-01-15', 1, 5, 5, 1),
(21, 2, 'CAM-021', '2023-02-18', 's3://docs/prov_21/camara.pdf', 's3', 'm3n4o5p6q7', 480000, '2023-02-20', 1, 3, 3, 1),
(22, 2, 'CAM-022', '2023-04-20', 's3://docs/prov_22/camara.pdf', 's3', 'r8s9t0u1v2', 530000, '2023-04-25', 1, 1, 1, 1),
(23, 2, 'CAM-023', '2023-03-08', 's3://docs/prov_23/camara.pdf', 's3', 'w3x4y5z6a7', 525000, '2023-03-12', 1, 2, 2, 1),
(24, 2, 'CAM-024', '2023-01-08', 's3://docs/prov_24/camara.pdf', 's3', 'b8c9d0e1f2', 540000, '2023-01-12', 1, 4, 4, 1),
(25, 2, 'CAM-025', '2023-05-15', 's3://docs/prov_25/camara.pdf', 's3', 'g3h4i5j6k7', 510000, '2023-05-20', 1, 3, 3, 1),
-- DOCUMENTO 2: COPIA DE CÉDULA (Para Proveedores Naturales 26-50)
(26, 4, 'CC-79123456', '2023-01-10', 's3://docs/prov_26/cedula.pdf', 's3', 'l8m9n0o1p2', 200000, '2023-01-15', 1, 1, 1, 1),
(27, 4, 'CC-52987654', '2023-02-12', 's3://docs/prov_27/cedula.pdf', 's3', 'q3r4s5t6u7', 210000, '2023-02-15', 1, 2, 2, 1),
(28, 4, 'CC-10203040', '2023-03-20', 's3://docs/prov_28/cedula.pdf', 's3', 'v8w9x0y1z2', 190000, '2023-03-25', 1, 1, 1, 1),
(29, 4, 'CC-80123987', '2023-01-25', 's3://docs/prov_29/cedula.pdf', 's3', 'a4b5c6d7e8', 205000, '2023-01-30', 1, 3, 3, 1),
(30, 4, 'CC-1030456789', '2023-04-10', 's3://docs/prov_30/cedula.pdf', 's3', 'f9g0h1i2j3', 195000, '2023-04-15', 1, 4, 4, 1),
(31, 4, 'CC-79555666', '2023-02-05', 's3://docs/prov_31/cedula.pdf', 's3', 'k4l5m6n7o8', 200000, '2023-02-10', 1, 2, 2, 1),
(32, 4, 'CC-51888999', '2023-01-15', 's3://docs/prov_32/cedula.pdf', 's3', 'p9q0r1s2t3', 215000, '2023-01-20', 1, 1, 1, 1),
(33, 4, 'CC-98765432', '2023-05-01', 's3://docs/prov_33/cedula.pdf', 's3', 'u4v5w6x7y8', 198000, '2023-05-05', 1, 5, 5, 1),
(34, 4, 'CC-1010222333', '2023-03-10', 's3://docs/prov_34/cedula.pdf', 's3', 'z9a0b1c2d3', 202000, '2023-03-15', 1, 2, 2, 1),
(35, 4, 'CC-71222333', '2023-02-20', 's3://docs/prov_35/cedula.pdf', 's3', 'e4f5g6h7i8', 210000, '2023-02-25', 1, 1, 1, 1),
(36, 4, 'CC-31444555', '2023-01-05', 's3://docs/prov_36/cedula.pdf', 's3', 'j9k0l1m2n3', 200000, '2023-01-10', 1, 3, 3, 1),
(37, 4, 'CC-1098765432', '2023-04-25', 's3://docs/prov_37/cedula.pdf', 's3', 'o4p5q6r7s8', 220000, '2023-04-30', 1, 1, 1, 1),
(38, 4, 'CC-88123123', '2023-03-01', 's3://docs/prov_38/cedula.pdf', 's3', 't9u0v1w2x3', 190000, '2023-03-05', 1, 4, 4, 1),
(39, 4, 'CC-63456789', '2023-01-20', 's3://docs/prov_39/cedula.pdf', 's3', 'y4z5a6b7c8', 205000, '2023-01-25', 1, 2, 2, 1),
(40, 4, 'CC-94567890', '2023-05-10', 's3://docs/prov_40/cedula.pdf', 's3', 'd9e0f1g2h3', 215000, '2023-05-15', 1, 1, 1, 1),
(41, 4, 'CC-1122334455', '2023-02-28', 's3://docs/prov_41/cedula.pdf', 's3', 'i4j5k6l7m8', 200000, '2023-03-05', 1, 3, 3, 1),
(42, 4, 'CC-75111222', '2023-01-12', 's3://docs/prov_42/cedula.pdf', 's3', 'n9o0p1q2r3', 210000, '2023-01-16', 1, 2, 2, 1),
(43, 4, 'CC-32333444', '2023-04-15', 's3://docs/prov_43/cedula.pdf', 's3', 's4t5u6v7w8', 195000, '2023-04-20', 1, 1, 1, 1),
(44, 4, 'CC-1055666777', '2023-03-25', 's3://docs/prov_44/cedula.pdf', 's3', 'x9y0z1a2b3', 205000, '2023-03-30', 1, 5, 5, 1),
(45, 4, 'CC-93777888', '2023-02-10', 's3://docs/prov_45/cedula.pdf', 's3', 'c4d5e6f7g8', 220000, '2023-02-15', 1, 4, 4, 1),
(46, 4, 'CC-80222333', '2023-01-30', 's3://docs/prov_46/cedula.pdf', 's3', 'h9i0j1k2l3', 190000, '2023-02-05', 1, 1, 1, 1),
(47, 4, 'CC-52111000', '2023-05-05', 's3://docs/prov_47/cedula.pdf', 's3', 'm4n5o6p7q8', 210000, '2023-05-10', 1, 2, 2, 1),
(48, 4, 'CC-1088776655', '2023-03-15', 's3://docs/prov_48/cedula.pdf', 's3', 'r9s0t1u2v3', 200000, '2023-03-20', 1, 3, 3, 1),
(49, 4, 'CC-71555444', '2023-01-05', 's3://docs/prov_49/cedula.pdf', 's3', 'w4x5y6z7a8', 215000, '2023-01-10', 1, 1, 1, 1),
(50, 4, 'CC-98222111', '2023-04-01', 's3://docs/prov_50/cedula.pdf', 's3', 'b9c0d1e2f3', 195000, '2023-04-05', 1, 2, 2, 1),
-- DOCUMENTO 3: CERTIFICACIÓN BANCARIA (Para algunos proveedores clave)
(1, 3, 'BANCOLOMBIA-001', '2023-05-01', 's3://docs/prov_1/cert_bancaria.pdf', 's3', 'g4h5i6j7k8', 50000, '2023-05-05', 1, 1, 1, 1),
(2, 3, 'DAVIVIENDA-002', '2023-05-02', 's3://docs/prov_2/cert_bancaria.pdf', 's3', 'l9m0n1o2p3', 55000, '2023-05-06', 1, 2, 2, 1),
(3, 3, 'BBVA-003', '2023-05-03', 's3://docs/prov_3/cert_bancaria.pdf', 's3', 'q4r5s6t7u8', 48000, '2023-05-07', 1, 1, 1, 1),
(14, 3, 'OCCIDENTE-014', '2023-05-04', 's3://docs/prov_14/cert_bancaria.pdf', 's3', 'v9w0x1y2z3', 52000, '2023-05-08', 1, 1, 1, 1),
(20, 3, 'BOGOTA-020', '2023-05-05', 's3://docs/prov_20/cert_bancaria.pdf', 's3', 'a5b6c7d8e9', 51000, '2023-05-09', 1, 5, 5, 1),
(26, 3, 'NEQUI-026', '2023-05-06', 's3://docs/prov_26/cert_bancaria.pdf', 's3', 'f0g1h2i3j4', 40000, '2023-05-10', 1, 1, 1, 1),
(33, 3, 'DAVIPLATA-033', '2023-05-07', 's3://docs/prov_33/cert_bancaria.pdf', 's3', 'k5l6m7n8o9', 42000, '2023-05-11', 1, 5, 5, 1),
(37, 3, 'NU-037', '2023-05-08', 's3://docs/prov_37/cert_bancaria.pdf', 's3', 'p0q1r2s3t4', 45000, '2023-05-12', 1, 1, 1, 1),
(43, 3, 'BANCOLOMBIA-043', '2023-05-09', 's3://docs/prov_43/cert_bancaria.pdf', 's3', 'u5v6w7x8y9', 49000, '2023-05-13', 1, 1, 1, 1),
(50, 3, 'CAJA-SOCIAL-050', '2023-05-10', 's3://docs/prov_50/cert_bancaria.pdf', 's3', 'z0a1b2c3d4', 47000, '2023-05-14', 1, 2, 2, 1);

INSERT INTO representante_legal (id_tipo_identificacion, numero_identificacion, nombres, apellidos, 
    id_tipo_telefono, telefono, correo, creado_por, activo
) VALUES 
-- REPRESENTANTES ÚNICOS (Para Proveedores Jurídicos 1-15)
(1, '79111222', 'Carlos Alberto', 'Montoya Ruiz', 1, '3001112233', 'gerencia@tecnosistemas.com.co', 1, 1),
(1, '52333444', 'Diana Marcela', 'Rojas Pinilla', 1, '3002223344', 'representante@elpuntooficina.com', 2, 1),
(1, '1010555666', 'Fernando', 'Gallo Ramirez', 1, '3003334455', 'legal@aseototal.co', 1, 1),
(1, '80444555', 'Gustavo Adolfo', 'Petrocelli Marin', 1, '3004445566', 'g.petrocelli@distrivalle.com', 3, 1),
(1, '41666777', 'Helena', 'Vasco Uribe', 1, '3005556677', 'hvasco@elescudo.com.co', 2, 1),
(3, '5050888999', 'John', 'Smitherson', 1, '3006667788', 'ceo@transportesrapidos.com', 1, 1), -- Cédula Extranjería
(1, '93000111', 'Kevin', 'Durango Zapata', 1, '3007778899', 'kevin.d@mueblesmodulares.co', 4, 1),
(1, '1020333444', 'Luisa Fernanda', 'Wong Osorio', 1, '3008889900', 'luisa.wong@finanzasintegrales.com', 1, 1),
(1, '71222333', 'Mario', 'Hernandez Lopez', 1, '3009990011', 'mario.h@latuercaferreteria.com', 2, 1),
(1, '31555444', 'Nancy', 'Pelaez Castro', 1, '3011112233', 'nancy.pelaez@dotacionesoccidente.com', 3, 1),
(1, '98666555', 'Oscar Ivan', 'Zuluaga Mendieta', 1, '3012223344', 'ozuluaga@impresosdigitales.co', 1, 1),
(1, '1030777888', 'Pablo Emilio', 'Escobar Gaviria', 1, '3013334455', 'pescobar@electricoscosta.com', 5, 1), -- Homónimo
(1, '52999000', 'Quiela', 'Restrepo Velez', 1, '3014445566', 'qrestrepo@eventosexclusivos.com', 2, 1),
(1, '88111222', 'Rodrigo', 'Lara Bonilla', 1, '3015556677', 'rlara@viascolombia.com', 1, 1),
(1, '63444333', 'Sara', 'Corrales Mejia', 1, '3016667788', 'sara.corrales@inmobiliariaelroble.com', 4, 1),
-- REPRESENTANTES DOBLES (Principal y Suplente) Para Proveedores Jurídicos 16-25
-- Para Proveedor 16 (Agencia de Viajes)
(1, '70888999', 'Tomas', 'Jaramillo Botero', 1, '3017778899', 'tomas.j@flycorporativo.com', 3, 1), -- Principal
(1, '1010222555', 'Ursula', 'Iguaran Buendia', 1, '3018889900', 'ursula.i@flycorporativo.com', 3, 1), -- Suplente
-- Para Proveedor 17 (Marketing 360)
(1, '80111000', 'Victor', 'Mallarino Santos', 1, '3019990011', 'victor.m@soluciones360.com.co', 1, 1),
(1, '52000111', 'Ximena', 'Duque Giraldo', 1, '3020001122', 'ximena.d@soluciones360.com.co', 1, 1),
-- Para Proveedor 18 (Laboratorio Clínico)
(1, '94222333', 'Yeferson', 'Cossio Perez', 1, '3021112233', 'gerencia@laboratorioempresa.com', 2, 1),
(1, '30333444', 'Zulma', 'Rey Tovar', 1, '3022223344', 'admon@laboratorioempresa.com', 2, 1),
-- Para Proveedor 19 (Fumigaciones)
(1, '1050666777', 'Alvaro', 'Uribe Velez', 1, '3023334455', 'auribe@fumigaciones.com', 1, 1), -- Homónimo
(1, '40777888', 'Beatriz', 'Pinzon Solano', 1, '3024445566', 'bpinzon@fumigaciones.com', 1, 1),
-- Para Proveedor 20 (Papeles Higiénicos)
(1, '75888999', 'Camilo', 'Sesto Garcia', 1, '3025556677', 'camilo.s@papeleshigienicos.com', 5, 1),
(1, '60999000', 'Dora', 'La Exploradora', 1, '3026667788', 'dora.e@papeleshigienicos.com', 5, 1), -- Nombre curioso
-- Para Proveedor 21 (Cerrajería)
(1, '98111222', 'Ernesto', 'Samper Pizano', 1, '3027778899', 'esamper@masterkey.com', 3, 1),
(1, '21222333', 'Francia', 'Marquez Mina', 1, '3028889900', 'fmarquez@masterkey.com', 3, 1),
-- Para Proveedor 22 (Capacitaciones)
(1, '88333444', 'Gabriel', 'Garcia Marquez', 1, '3029990011', 'gabriel.g@talentohumano.com', 1, 1),
(1, '34444555', 'Hassan', 'Nassar Perez', 1, '3030001122', 'hassan.n@talentohumano.com', 1, 1),
-- Para Proveedor 23 (Mensajería)
(1, '1090555666', 'Ignacio', 'Londoño Zabala', 1, '3031112233', 'nacho@mensajeriaexpress.com', 2, 1),
(1, '55666777', 'Jennifer', 'Lopez Rodriguez', 1, '3032223344', 'jlo@mensajeriaexpress.com', 2, 1),
-- Para Proveedor 24 (Software Contable)
(3, '6060111222', 'Bill', 'Gates', 1, '3033334455', 'bgates@softwarecontable.com', 4, 1),
(3, '7070222333', 'Elon', 'Musk', 1, '3034445566', 'emusk@softwarecontable.com', 4, 1),
-- Para Proveedor 25 (Artesanías)
(1, '1080888999', 'Karol', 'Giraldo Navarro', 1, '3035556677', 'karolg@artesaniascorp.com', 3, 1),
(1, '43000111', 'Lina', 'Tejeiro Prada', 1, '3036667788', 'lina.t@artesaniascorp.com', 3, 1);

INSERT INTO socio_proveedor (
    id, id_proveedor, nombres, apellidos, id_tipo_identificacion, numero_identificacion, 
    consulta, fecha_consulta, resultado_consulta, id_origen, fecha_extraccion, 
    validado, creado_por, modificado_por, activo
) VALUES 
-- PROVEEDOR 1: Tecnología y Sistemas (JUNTA GRANDE - 6 Socios)
(1, 1, 'Carlos', 'Ardila Lulle', 1, '1000001', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
(2, 1, 'Luis Carlos', 'Sarmiento', 1, '1000002', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
(3, 1, 'Alejandro', 'Santo Domingo', 1, '1000003', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
(4, 1, 'Jaime', 'Gilinski', 1, '1000004', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
(5, 1, 'David', 'Velez', 1, '1000005', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
(6, 1, 'Beatriz', 'Davila', 1, '1000006', 5001, '2023-01-15', 1, 1, NOW(), 1, 1, 1, 1),
-- PROVEEDOR 3: Aseo Total (JUNTA GRANDE - 6 Socios)
(7, 3, 'Grupo Inversionista', 'Alpha S.A.', 2, '900999888', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1), -- Socio Persona Jurídica
(8, 3, 'Pedro', 'Gomez Barrero', 1, '1910001', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1),
(9, 3, 'Ignacio', 'Perez', 1, '1910002', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1),
(10, 3, 'Consuelo', 'Araujo', 1, '1910003', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1),
(11, 3, 'Fondo de Empleados', 'Fecat', 2, '800111000', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1),
(12, 3, 'Julian', 'Domínguez', 1, '1910004', 5020, '2023-02-10', 1, 1, NOW(), 1, 1, 1, 1),
-- PROVEEDOR 5: Seguridad El Escudo (EMPRESA FAMILIAR - 4 Socios)
(13, 5, 'Humberto', 'De la Calle', 1, '70555111', 6001, '2023-03-01', 1, 2, NOW(), 1, 2, 2, 1),
(14, 5, 'Maria', 'De la Calle', 1, '70555112', 6001, '2023-03-01', 1, 2, NOW(), 1, 2, 2, 1),
(15, 5, 'Jose', 'De la Calle', 1, '70555113', 6001, '2023-03-01', 1, 2, NOW(), 1, 2, 2, 1),
(16, 5, 'Lucia', 'De la Calle', 1, '70555114', 6001, '2023-03-01', 1, 2, NOW(), 1, 2, 2, 1),
-- PROVEEDOR 24: Software Contable (SOCIEDAD LIMITADA - 3 Socios)
(17, 24, 'Steve', 'Wozniak', 3, '500100200', NULL, NULL, NULL, 1, NOW(), 0, 4, 4, 1), -- Extranjero, pendiente validación
(18, 24, 'Paul', 'Allen', 3, '500100201', NULL, NULL, NULL, 1, NOW(), 0, 4, 4, 1),
(19, 24, 'Larry', 'Ellison', 3, '500100202', 7005, '2023-06-01', 1, 1, NOW(), 1, 4, 4, 1),
-- PROVEEDOR 20: Papeles Higiénicos (CASO ESPECIAL - Alerta)
(20, 20, 'Pablo', 'Marmol', 1, '88888888', 9999, '2023-05-20', 0, 1, NOW(), 1, 5, 5, 1), -- Resultado 0 (Desfavorable/Lista Restrictiva)
(21, 20, 'Pedro', 'Picapiedra', 1, '77777777', 9999, '2023-05-20', 1, 1, NOW(), 1, 5, 5, 1),
-- RESTO DE PROVEEDORES JURÍDICOS (SOCIO ÚNICO O MAYORITARIO)
(22, 2, 'Roberto', 'Gomez Bolaños', 1, '1020001', 1001, '2023-01-01', 1, 1, NOW(), 1, 2, 2, 1),
(23, 4, 'Florinda', 'Meza', 1, '1020002', 1002, '2023-01-02', 1, 1, NOW(), 1, 3, 3, 1),
(24, 6, 'Ruben', 'Aguirre', 1, '1020003', 1003, '2023-01-03', 1, 1, NOW(), 1, 1, 1, 1),
(25, 7, 'Edgar', 'Vivar', 1, '1020004', 1004, '2023-01-04', 1, 1, NOW(), 1, 4, 4, 1),
(26, 8, 'Carlos', 'Villagran', 1, '1020005', 1005, '2023-01-05', 1, 1, NOW(), 1, 1, 1, 1),
(27, 9, 'Maria', 'Antonieta de las Nieves', 1, '1020006', 1006, '2023-01-06', 1, 1, NOW(), 1, 2, 2, 1),
(28, 10, 'Angelines', 'Fernandez', 1, '1020007', 1007, '2023-01-07', 1, 1, NOW(), 1, 3, 3, 1),
(29, 11, 'Ramon', 'Valdes', 1, '1020008', 1008, '2023-01-08', 1, 1, NOW(), 1, 1, 1, 1),
(30, 12, 'Horacio', 'Gomez', 1, '1020009', 1009, '2023-01-09', 1, 1, NOW(), 1, 5, 5, 1),
(31, 13, 'Raul', 'Chato Padilla', 1, '1020010', 1010, '2023-01-10', 1, 1, NOW(), 1, 2, 2, 1),
(32, 14, 'Cantinflas', 'Moreno', 1, '1020011', 1011, '2023-01-11', 1, 1, NOW(), 1, 1, 1, 1),
(33, 15, 'Gaspar', 'Henaine', 1, '1020012', 1012, '2023-01-12', 1, 1, NOW(), 1, 4, 4, 1),
(34, 16, 'Roberto', 'Carlos', 3, 'BR500500', 1013, '2023-01-13', 1, 1, NOW(), 1, 3, 3, 1),
(35, 17, 'Juan', 'Gabriel', 3, 'MX600600', 1014, '2023-01-14', 1, 1, NOW(), 1, 1, 1, 1),
(36, 18, 'Vicente', 'Fernandez', 3, 'MX700700', 1015, '2023-01-15', 1, 1, NOW(), 1, 2, 2, 1),
(37, 19, 'Celia', 'Cruz', 3, 'CU800800', 1016, '2023-01-16', 1, 1, NOW(), 1, 1, 1, 1),
(38, 21, 'Tito', 'Puente', 3, 'PR900900', 1017, '2023-01-17', 1, 1, NOW(), 1, 3, 3, 1),
(39, 22, 'Hector', 'Lavoe', 3, 'PR100100', 1018, '2023-01-18', 1, 1, NOW(), 1, 1, 1, 1),
(40, 23, 'Ruben', 'Blades', 3, 'PA200200', 1019, '2023-01-19', 1, 1, NOW(), 1, 2, 2, 1),
(41, 25, 'Willie', 'Colon', 3, 'PR300300', 1020, '2023-01-20', 1, 1, NOW(), 1, 3, 3, 1);

INSERT INTO validacion (
    id_usuario, id_proveedor, id_campo_validacion, valor_web, valor_documento, 
    id_documento, resultado_validacion, fecha_validacion, comentarios, creado_por, activo
) VALUES 
-- CASOS DE ÉXITO (COINCIDENCIA TOTAL)
-- Proveedor 1: Tecnología y Sistemas (Validación NIT vs RUT)
(1, 1, 1, '900123456', '900123456', 1, 1, '2023-01-16', 'NIT coincide perfectamente con el RUT digital.', 1, 1),
-- Proveedor 1: (Validación Razón Social vs Cámara de Comercio)
(1, 1, 2, 'Tecnología y Sistemas S.A.S.', 'Tecnología y Sistemas S.A.S.', 51, 1, '2023-01-16', 'Nombre exacto incluyendo el tipo de sociedad.', 1, 1),
-- Proveedor 2: Suministros (Validación Representante Legal vs Cámara)
(2, 2, 4, 'Carlos Alberto Montoya', 'Carlos Alberto Montoya Ruiz', 52, 1, '2023-01-18', 'Coincidencia aceptable, falta segundo apellido en web pero es válido.', 2, 1),
-- Proveedor 3: Aseo Total (Validación NIT vs RUT)
(1, 3, 1, '901234567', '901234567-5', 3, 1, '2023-02-06', 'Validado. El dígito de verificación en el documento es 5.', 1, 1),
-- Proveedor 5: Seguridad (Validación Estado Matrícula vs Cámara)
(2, 5, 6, 'ACTIVO', 'MATRICULA ACTIVA', 55, 1, '2023-03-11', 'La empresa se encuentra renovada a la fecha.', 2, 1),
-- Proveedor 26: Abogado Juan Carlos (Validación Cédula vs Copia Cédula)
(1, 26, 1, '79123456', '79.123.456', 76, 1, '2023-01-16', 'Documento legible y corresponde al número registrado.', 1, 1),
-- Proveedor 27: Contadora Maria (Validación Nombre vs RUT)
(2, 27, 2, 'Maria Alejandra Rodriguez', 'Maria Alejandra Rodriguez Lopez', 27, 1, '2023-02-16', 'Nombre completo validado en el RUT.', 2, 1),
-- Proveedor 10: Dotaciones (Validación Dirección vs RUT)
(3, 10, 3, 'Carrera 15 # 80-20', 'Cr 15 No 80 - 20 Of 301', 10, 1, '2023-05-16', 'Dirección corresponde a la sede principal.', 3, 1),
-- Proveedor 14: Constructora (Validación Actividad Económica vs RUT)
(1, 14, 5, '4111', '4111 - Construcción de edificios', 14, 1, '2023-01-31', 'Código CIIU principal coincide.', 1, 1),
-- Proveedor 33: Arquitecto (Validación Cuenta Bancaria vs Certificación)
-- Nota: Asumimos ID de campo 7 para 'Cuenta Bancaria' si existiera, o usamos 1 como validación de identidad bancaria
(5, 33, 1, 'Daviplata 3178889900', '3178889900', 107, 1, '2023-05-08', 'Certificación de producto digital validada.', 5, 1),
-- Proveedor 4: Alimentos (Validación Razón Social vs RUT)
(3, 4, 2, 'Distribuidora de Alimentos del Valle', 'Distribuidora de Alimentos del Valle S.A.', 4, 1, '2023-01-26', 'Coincide razón social.', 3, 1),
-- Proveedor 40: Jardinero (Validación Cédula vs Documento Identidad)
(1, 40, 1, '94567890', '94.567.890', 90, 1, '2023-05-16', 'Identidad verificada.', 1, 1),
-- CASOS DE FALLO (DISCREPANCIAS O ERRORES)
-- Proveedor 7: Muebles y Diseños (ERROR: Razón Social incompleta)
(4, 7, 2, 'Muebles Modulares', 'Muebles y Diseños Modulares Ltda.', 7, 0, '2023-02-21', 'El nombre en el sistema no coincide con la razón social legal del RUT.', 4, 1),
-- Proveedor 20: Papeles Higiénicos (ERROR: Dirección desactualizada)
(5, 20, 3, 'Calle 100 # 15-20', 'Avenida Calle 26 # 68B - 80', 20, 0, '2023-01-16', 'La dirección del RUT es diferente a la registrada en el perfil.', 5, 1),
-- Proveedor 15: Inmobiliaria (ERROR: Representante Legal cambiado)
(4, 15, 4, 'Sara Corrales', 'Pedro Perez (Suplente)', 65, 0, '2023-04-16', 'El documento Cámara de Comercio muestra otro representante legal principal.', 4, 1),
-- Proveedor 30: Traductora (ERROR: Cédula borrosa/no coincide)
(4, 30, 1, '1030456789', '1030456780', 80, 0, '2023-04-16', 'El número en la copia de cédula difiere en el último dígito.', 4, 1),
-- Proveedor 44: Pintor (ERROR: RUT Inactivo)
(5, 44, 6, 'ACTIVO', 'CANCELADO', 44, 0, '2023-03-26', 'El RUT aportado aparece con estado CANCELADO.', 5, 1),
-- Proveedor 12: Eléctricos (ERROR: NIT incorrecto en sistema)
(5, 12, 1, '899999000', '899999001-2', 12, 0, '2023-01-23', 'Error de digitación en el NIT registrado en base de datos.', 5, 1),
-- VALIDACIONES PENDIENTES O PARCIALES (COMENTARIOS)
-- Proveedor 18: Laboratorio (Validación con observación)
(2, 18, 3, 'Transversal 54 # 20-10', 'Tv 54 # 20-10 Piso 2', 18, 1, '2023-03-26', 'Coincide dirección aunque falta el detalle del piso en el sistema.', 2, 1),

-- Proveedor 50: Músico (Validación bancaria)
(2, 50, 2, 'Tomas Uribe Velez', 'Tomas Uribe', 110, 1, '2023-05-11', 'Nombre en cuenta bancaria coincide parcialmente, se acepta.', 2, 1),

(1, 6, 1, '900555666', '900555666-9', 6, 1, '2023-01-13', 'NIT Correcto.', 1, 1),
(1, 8, 1, '901888999', '901888999', 8, 1, '2023-04-06', 'NIT Correcto.', 1, 1),
(2, 9, 2, 'Ferretería Industrial La Tuerca', 'Ferretería Industrial La Tuerca S.A.S.', 59, 1, '2023-01-09', 'Razón social válida.', 2, 1),
(3, 11, 5, '1811', '1811 - Impresión', 11, 1, '2023-03-06', 'Actividad económica verificada.', 1, 1),
(5, 13, 1, '901000222', '901000222', 13, 1, '2023-03-21', 'NIT Validado.', 2, 1),
(3, 16, 4, 'Tomas Jaramillo', 'Tomas Jaramillo Botero', 66, 1, '2023-02-11', 'Representante legal verificado.', 3, 1),
(1, 17, 1, '901444555', '901444555', 17, 1, '2023-02-06', 'NIT OK.', 1, 1),
(2, 19, 2, 'Fumigaciones y Control de Plagas', 'Fumigaciones y Control de Plagas Ltda', 69, 1, '2023-05-06', 'Razón social OK.', 1, 1),
(3, 21, 3, 'Calle Cerrajería 1', 'Calle Cerrajería 1', 21, 1, '2023-02-21', 'Dirección OK.', 3, 1),
(1, 22, 1, '901555111', '901555111', 22, 1, '2023-04-26', 'Documento verificado.', 1, 1),
(2, 23, 6, 'ACTIVO', 'ACTIVA', 73, 1, '2023-03-13', 'Matrícula vigente.', 2, 1),
(4, 24, 1, '900222888', '900222888', 24, 1, '2023-01-13', 'NIT Verificado.', 4, 1),
(3, 25, 5, '4771', '4771 - Comercio al por menor', 25, 1, '2023-05-21', 'CIIU Correcto.', 3, 1),
(1, 28, 1, '10203040', '10.203.040', 78, 1, '2023-03-26', 'Cédula coincide.', 1, 1),
(2, 29, 2, 'Luis Fernando Martinez', 'Luis Fernando Martinez Ruiz', 79, 1, '2023-01-31', 'Nombre validado.', 3, 1),
(1, 31, 1, '79555666', '79.555.666', 81, 1, '2023-02-11', 'Cédula OK.', 2, 1),
(5, 32, 1, '51888999', '51.888.999', 82, 1, '2023-01-21', 'Cédula OK.', 1, 1),
(2, 34, 1, '1010222333', '1010222333', 84, 1, '2023-03-16', 'Documento legible y válido.', 2, 1),
(3, 35, 1, '71222333', '71.222.333', 85, 1, '2023-02-26', 'ID Validado.', 1, 1),
(4, 42, 1, '75111222', '75.111.222', 92, 1, '2023-01-17', 'ID Validado.', 2, 1);

INSERT INTO evaluacion_riesgo (
    id_usuario, id_proveedor, id_validacion, validacion_auditoria, 
    comentarios_auditoria, creado_por, modificado_por, activo
) VALUES 
-- CASO 1: BLOQUEO POR LISTA RESTRICTIVA (Socio: Pablo Marmol) El proveedor 20 tenía un socio con resultado_consulta = 0 (Desfavorable)
(5, 20, 14, 0, 'ALERTA CRÍTICA: El socio Pablo Marmol presenta coincidencia positiva en listas restrictivas (OFAC/Clinton). Se realizó validación biométrica y se confirma la identidad. PROVEEDOR RECHAZADO INMEDIATAMENTE por política LA/FT.', 5, 5, 1),
-- CASO 2: RUT CANCELADO (Bloqueo Operativo) El proveedor 44 tenía el RUT en estado "CANCELADO" en la tabla validacion (id_validacion 17)
(5, 44, 17, 0, 'El documento RUT aportado figura como CANCELADO en la base de datos de la DIAN. No es posible facturar ni contratar. Se rechaza hasta que regularice su situación tributaria.', 5, 5, 1),
-- CASO 3: DISCREPANCIA EN RAZÓN SOCIAL (Riesgo Medio - Aprobado con condición) El proveedor 7 tenía el nombre incompleto (id_validacion 13)
(4, 7, 13, 1, 'Se identifica discrepancia en la Razón Social (falta sigla Ltda). Sin embargo, el NIT y la Cámara de Comercio coinciden. Se aprueba la evaluación bajo la condición de actualizar el registro en la App antes del primer pago.', 4, 4, 1),
-- CASO 4: FALSO POSITIVO DE PEP (Persona Expuesta Políticamente) El proveedor 19 tiene un Rep. Legal "Alvaro Uribe". El sistema alerta posible PEP.
(1, 19, NULL, 1, 'ALERTA PEP: El nombre del Representante Legal coincide con una Persona Expuesta Políticamente. Se valida número de cédula y fecha de expedición, confirmando que se trata de un homónimo y NO de la persona pública. Se descarta riesgo. Aprobado.', 1, 1, 1),
-- CASO 5: DISCREPANCIA EN REPRESENTANTE LEGAL (Riesgo Alto de Fraude) El proveedor 15 tenía un suplente firmando como principal (id_validacion 15)
(2, 15, 15, 0, 'El documento de Cámara de Comercio indica un Representante Legal distinto al que firma los documentos en la plataforma. Riesgo de suplantación o falta de capacidad legal para contratar. Se solicita Acta de Asamblea reciente.', 2, 2, 1),
-- CASO 6: ERROR DE IDENTIFICACIÓN/NIT (Riesgo Operativo) El proveedor 12 digitó mal su NIT (id_validacion 18)
(3, 12, 18, 0, 'El NIT registrado en sistema no existe en el RUT adjunto. Error de digitación crítico que afecta reportes a medios magnéticos. Se devuelve el trámite para corrección del proveedor.', 3, 3, 1),
-- CASO 7: SOCIOS EXTRANJEROS (Debida Diligencia Intensificada) El proveedor 24 tiene socios extranjeros (Steve Wozniak, etc.)
(4, 24, NULL, 1, 'Proveedor con composición accionaria extranjera. Se realiza consulta en listas internacionales (World-Check y Interpol). No se hallan antecedentes negativos. Se solicita certificado de beneficiario final apostillado. Aprobado condicionado.', 4, 4, 1),
-- CASO 8: VALIDACIÓN BANCARIA CON OBSERVACIÓN El proveedor 50 (Músico) tenía nombre parcial en el banco (id_validacion 20)
(2, 50, 20, 1, 'La cuenta bancaria está a nombre de persona natural. Aunque el nombre no está completo en el reporte bancario, coincide el documento de identidad asociado a la cuenta. Se aprueba para pagos.', 2, 2, 1),
-- CASO 9: DUDA EN DIRECCIÓN (Riesgo Bajo) El proveedor 20 tenía otra dirección (id_validacion 14).
(1, 20, 14, 0, 'Además del bloqueo por listas, la dirección física no pudo ser georeferenciada correctamente y difiere de la Cámara de Comercio. Empresa de papel? Se mantiene el rechazo.', 1, 1, 1),
-- CASO 10: CEDULA ILEGIBLE O DUDOSA El proveedor 30 tenía cédula que no coincidía (id_validacion 16)
(3, 30, 16, 0, 'El documento de identidad cargado presenta inconsistencias numéricas y visuales (posible alteración digital). Se escala al área de seguridad física para validación documental.', 3, 3, 1);


INSERT INTO evaluacion_proveedor (
    id_proveedor, id_usuario, id_calificacion, puntaje, observaciones, 
    url_calificacion, fecha_creado, creado_por, modificado_por, activo
) VALUES 
-- PERIODO 1: JUNIO 2023 (Solo Proveedores Antiguos: IDs 1-10)
(1, 1, 1, 98, 'Cumplimiento perfecto en SLAs y soporte técnico.', 's3://evals/2023_1/prov_1.pdf', '2023-06-15 10:00:00', 1, 1, 1),
(2, 2, 1, 85, 'Buen servicio, aunque la facturación llegó tarde.', 's3://evals/2023_1/prov_2.pdf', '2023-06-16 09:30:00', 2, 2, 1),
(3, 1, 1, 90, 'Personal de aseo muy capacitado.', 's3://evals/2023_1/prov_3.pdf', '2023-06-18 14:00:00', 1, 1, 1),
(4, 3, 2, 75, 'Calidad de insumos aceptable, pero tiempos de entrega irregulares.', 's3://evals/2023_1/prov_4.pdf', '2023-06-20 11:00:00', 3, 3, 1),
(5, 2, 1, 95, 'Excelente esquema de seguridad implementado.', 's3://evals/2023_1/prov_5.pdf', '2023-06-22 08:00:00', 2, 2, 1),
(6, 1, 3, 55, 'Retrasos críticos en envíos. Mercancía averiada. Se inicia plan de mejora.', 's3://evals/2023_1/prov_6.pdf', '2023-06-25 16:00:00', 1, 1, 1), -- No Confiable (<60)
(7, 4, 2, 65, 'Muebles entregados con rayones leves. Garantía lenta.', 's3://evals/2023_1/prov_7.pdf', '2023-06-28 10:00:00', 4, 4, 1),
(8, 1, 1, 100, 'Auditoría impecable. Entregables antes de tiempo.', 's3://evals/2023_1/prov_8.pdf', '2023-06-30 09:00:00', 1, 1, 1),
(9, 2, 2, 78, 'Precios altos comparados con el mercado actual.', 's3://evals/2023_1/prov_9.pdf', '2023-06-10 11:00:00', 2, 2, 1),
(10, 3, 1, 88, 'Dotaciones de buena calidad y tallaje correcto.', 's3://evals/2023_1/prov_10.pdf', '2023-06-12 14:30:00', 3, 3, 1),
-- PERIODO 2: DICIEMBRE 2023 (Proveedores 1-10 + Medios 11-20)
(1, 1, 1, 96, 'Mantiene la excelencia en el servicio.', 's3://evals/2023_2/prov_1.pdf', '2023-12-10 10:00:00', 1, 1, 1),
(4, 3, 2, 79, 'Mejoraron tiempos de entrega, casi logran la meta de confiabilidad.', 's3://evals/2023_2/prov_4.pdf', '2023-12-12 11:00:00', 3, 3, 1),
(6, 1, 2, 62, 'Mejora leve tras el plan de acción, salió de zona crítica.', 's3://evals/2023_2/prov_6.pdf', '2023-12-15 16:00:00', 1, 1, 1), -- Pasó de 3 a 2
(11, 1, 1, 82, 'Primera evaluación: Buena calidad de impresión.', 's3://evals/2023_2/prov_11.pdf', '2023-12-18 09:00:00', 1, 1, 1),
(12, 5, 3, 40, 'No contestan llamadas de emergencia. Servicio pésimo.', 's3://evals/2023_2/prov_12.pdf', '2023-12-20 08:00:00', 5, 5, 1), -- Problema con Eléctricos
(13, 2, 1, 90, 'El evento de fin de año fue un éxito total.', 's3://evals/2023_2/prov_13.pdf', '2023-12-22 13:00:00', 2, 2, 1),
(14, 1, 2, 70, 'Obras civiles con retraso por clima, pero gestión administrativa regular.', 's3://evals/2023_2/prov_14.pdf', '2023-12-24 10:00:00', 1, 1, 1),
(15, 4, 2, 60, 'El local tiene problemas de humedad no resueltos.', 's3://evals/2023_2/prov_15.pdf', '2023-12-26 11:00:00', 4, 4, 1), -- Al límite de no confiable
(18, 2, 1, 85, 'Resultados de laboratorio entregados digitalmente a tiempo.', 's3://evals/2023_2/prov_18.pdf', '2023-12-28 09:30:00', 2, 2, 1),
(20, 5, 3, 30, 'No entregaron pedido. Proveedor con alertas de riesgo LA/FT.', 's3://evals/2023_2/prov_20.pdf', '2023-12-29 14:00:00', 5, 5, 1), -- Caso Crítico (Pablo Marmol)
-- PERIODO 3: JUNIO 2024 (Re-evaluación y Nuevos Ingresos 21-30)
(1, 1, 1, 99, 'Sin observaciones negativas.', 's3://evals/2024_1/prov_1.pdf', '2024-06-05 10:00:00', 1, 1, 1),
(2, 2, 2, 75, 'Ha bajado la calidad de atención al cliente.', 's3://evals/2024_1/prov_2.pdf', '2024-06-06 11:00:00', 2, 2, 1),
(12, 5, 3, 50, 'Sigue sin cumplir ANS. Se sugiere cambio de proveedor.', 's3://evals/2024_1/prov_12.pdf', '2024-06-08 09:00:00', 5, 5, 1), -- Reincidente
(21, 3, 1, 92, 'Servicio rápido de cerrajería en emergencia.', 's3://evals/2024_1/prov_21.pdf', '2024-06-10 15:00:00', 3, 3, 1),
(22, 1, 1, 88, 'Capacitaciones con buena metodología.', 's3://evals/2024_1/prov_22.pdf', '2024-06-12 10:00:00', 1, 1, 1),
(23, 2, 1, 81, 'Entregas a tiempo, apenas cumple el estándar de confiable.', 's3://evals/2024_1/prov_23.pdf', '2024-06-15 11:00:00', 2, 2, 1),
(24, 4, 1, 95, 'Software estable, soporte premium excelente.', 's3://evals/2024_1/prov_24.pdf', '2024-06-18 14:00:00', 4, 4, 1),
(26, 1, 1, 90, 'El abogado entregó los conceptos jurídicos impecables.', 's3://evals/2024_1/prov_26.pdf', '2024-06-20 09:00:00', 1, 1, 1),
(28, 1, 2, 65, 'Soporte técnico freelance bueno, pero disponibilidad limitada.', 's3://evals/2024_1/prov_28.pdf', '2024-06-22 16:00:00', 1, 1, 1),
(30, 4, 3, 55, 'Traducciones con errores gramaticales. No confiable.', 's3://evals/2024_1/prov_30.pdf', '2024-06-25 10:00:00', 4, 4, 1),
-- PERIODO 4: DICIEMBRE 2024 (Evaluación Global Reciente - Incluye Nuevos 31-50)
(1, 1, 1, 97, 'Proveedor estratégico clave. Excelente cierre de año.', 's3://evals/2024_2/prov_1.pdf', '2024-12-01 10:00:00', 1, 1, 1),
(3, 1, 1, 89, 'Buen servicio general.', 's3://evals/2024_2/prov_3.pdf', '2024-12-02 11:00:00', 1, 1, 1),
(7, 4, 1, 82, 'Mejoraron significativamente el servicio postventa.', 's3://evals/2024_2/prov_7.pdf', '2024-12-03 09:00:00', 4, 4, 1), -- Recuperado
(12, 5, 3, 20, 'PROVEEDOR BLOQUEADO. Incumplimiento total de contrato.', 's3://evals/2024_2/prov_12.pdf', '2024-12-04 08:00:00', 5, 5, 0), -- Inactivo
(31, 2, 1, 85, 'Electricista muy profesional.', 's3://evals/2024_2/prov_31.pdf', '2024-12-05 14:00:00', 2, 2, 1),
(33, 5, 1, 93, 'Planos arquitectónicos entregados antes de fecha.', 's3://evals/2024_2/prov_33.pdf', '2024-12-06 10:00:00', 5, 5, 1),
(35, 1, 2, 70, 'Vehículo de carga en estado regular, pero conductor amable.', 's3://evals/2024_2/prov_35.pdf', '2024-12-07 11:00:00', 1, 1, 1),
(37, 1, 1, 98, 'Desarrollador Full Stack de alto nivel. Código limpio.', 's3://evals/2024_2/prov_37.pdf', '2024-12-10 16:00:00', 1, 1, 1),
(40, 1, 1, 84, 'Jardines en buen estado.', 's3://evals/2024_2/prov_40.pdf', '2024-12-12 09:00:00', 1, 1, 1),
(41, 3, 2, 60, 'Publicaciones en redes con poco alcance. Debe mejorar estrategia.', 's3://evals/2024_2/prov_41.pdf', '2024-12-14 10:00:00', 3, 3, 1),
(43, 1, 1, 91, 'Coordinación de eventos impecable.', 's3://evals/2024_2/prov_43.pdf', '2024-12-15 15:00:00', 1, 1, 1),
(44, 5, 3, 0, 'No evaluable por bloqueo de RUT (Riesgo).', 's3://evals/2024_2/prov_44.pdf', '2024-12-16 08:00:00', 5, 5, 1),
(46, 1, 1, 86, 'Informes de SST completos.', 's3://evals/2024_2/prov_46.pdf', '2024-12-18 11:00:00', 1, 1, 1),
(48, 3, 2, 78, 'Mensajero confiable pero moto suele fallar.', 's3://evals/2024_2/prov_48.pdf', '2024-12-20 09:00:00', 3, 3, 1),
(50, 2, 1, 95, 'Excelente show musical.', 's3://evals/2024_2/prov_50.pdf', '2024-12-22 19:00:00', 2, 2, 1),
-- Calificaciones solicitadas para el proveedor ID 2
(2, 2, 2, 75, 'Calificación de Alternativo según histórico', 's3://evals/2024_2/prov_2.pdf', '2024-12-20 10:00:00', 2, 2, 1),
(2, 2, 1, 85, 'Calificación de Confiable según histórico', 's3://evals/2025_2/prov_2.pdf', '2025-12-20 10:00:00', 2, 2, 1),
(2, 2, 1, 90, 'Calificación de Confiable reciente', 's3://evals/2026_1/prov_2.pdf', '2026-06-18 10:00:00', 2, 2, 1);

INSERT INTO notificacion (
    id_usuario, id_tipo_notificacion, fecha_notificacion, mensaje, 
    creado_por, modificado_por, activo
) VALUES 
-- Enero 2023: Notificaciones de Onboarding (Validación Documental)
(1, 2, '2023-01-16 08:00:00', 'El documento RUT del proveedor Tecnología y Sistemas S.A.S. ha sido APROBADO.', 1, 1, 1),
(1, 2, '2023-01-16 08:05:00', 'El documento Cámara de Comercio del proveedor Tecnología y Sistemas S.A.S. ha sido APROBADO.', 1, 1, 1),
(1, 4, '2023-01-16 09:00:00', 'El proveedor Tecnología y Sistemas S.A.S. ha sido ACTIVADO exitosamente en el sistema.', 1, 1, 1),
(4, 3, '2023-02-21 10:30:00', 'El documento Cámara de Comercio del proveedor Muebles y Diseños Modulares fue RECHAZADO. Motivo: Discrepancia en Razón Social.', 4, 4, 1),
(4, 1, '2023-02-21 10:35:00', 'Se ha enviado una solicitud de corrección de datos al proveedor Muebles y Diseños Modulares.', 4, 4, 1),
(5, 3, '2023-01-23 14:00:00', 'El documento RUT del proveedor Servicios Eléctricos de la Costa fue RECHAZADO. Motivo: NIT errado.', 5, 5, 1),
-- Junio 2023: Notificaciones de Primera Evaluación Semestral
(1, 5, '2023-06-15 10:05:00', 'Nueva evaluación disponible para Tecnología y Sistemas S.A.S. - Calificación: CONFIABLE (98/100).', 1, 1, 1),
(2, 5, '2023-06-16 09:35:00', 'Nueva evaluación disponible para Suministros de Oficina El Punto - Calificación: CONFIABLE (85/100).', 2, 2, 1),
(3, 5, '2023-06-20 11:05:00', 'Nueva evaluación disponible para Distribuidora de Alimentos del Valle - Calificación: ALTERNATIVO (75/100).', 3, 3, 1),
(1, 5, '2023-06-25 16:05:00', 'ALERTA: El proveedor Transportes Rápidos S.A.S. obtuvo una calificación NO CONFIABLE (55/100). Se requiere plan de acción.', 1, 1, 1),
-- Diciembre 2023: Problemas de Riesgo y Segunda Evaluación
(5, 6, '2023-12-29 14:00:00', 'ALERTA DE RIESGO CRÍTICA: El proveedor Distribuidora de Papeles Higiénicos ha sido BLOQUEADO por coincidencias en listas restrictivas (Socio: Pablo Marmol).', 5, 5, 1),
(5, 4, '2023-12-29 14:05:00', 'El estado del proveedor Distribuidora de Papeles Higiénicos cambió a INACTIVO/BLOQUEADO.', 5, 5, 1),
(5, 3, '2023-12-20 08:00:00', 'El proveedor Servicios Eléctricos de la Costa ha sido marcado como NO CONFIABLE tras evaluación deficiente.', 5, 5, 1),
(4, 5, '2023-12-26 11:05:00', 'Nueva evaluación disponible para Inmobiliaria El Roble - Calificación: ALTERNATIVO (60/100). Riesgo de bajar de categoría.', 4, 4, 1),
-- Enero 2024: Campaña de Actualización Anual de Datos
(1, 1, '2024-01-10 08:00:00', 'Recordatorio enviado: Se requiere renovación de Cámara de Comercio para Tecnología y Sistemas S.A.S.', 1, 1, 1),
(2, 1, '2024-01-10 08:00:00', 'Recordatorio enviado: Se requiere renovación de Cámara de Comercio para Suministros de Oficina El Punto.', 2, 2, 1),
(3, 1, '2024-01-10 08:00:00', 'Recordatorio enviado: Se requiere renovación de Cámara de Comercio para Dotaciones Industriales.', 3, 3, 1),
(4, 1, '2024-01-10 08:00:00', 'Recordatorio enviado: Actualización de datos pendiente para Software Contable Eficiente.', 4, 4, 1),
(1, 1, '2024-01-15 09:00:00', 'El proveedor Aseo Total de Colombia S.A. ha cargado nuevos documentos para validación.', 1, 1, 1),
-- Junio 2024: Tercera Evaluación y Gestión
(1, 2, '2024-06-01 10:00:00', 'Validación Exitosa: Renovación de matrícula mercantil aprobada para Tecnología y Sistemas.', 1, 1, 1),
(1, 5, '2024-06-05 10:05:00', 'Nueva evaluación disponible para Tecnología y Sistemas S.A.S. - Calificación: CONFIABLE (99/100).', 1, 1, 1),
(3, 2, '2024-06-10 15:05:00', 'Nueva evaluación disponible para Cerrajería Master Key - Calificación: CONFIABLE (92/100).', 3, 3, 1),
(4, 5, '2024-06-25 10:05:00', 'Nueva evaluación disponible para Ana Beatriz Hernandez (Traductora) - Calificación: NO CONFIABLE (55/100).', 4, 4, 1),
-- Diciembre 2024: Cierre de Año y Nuevos Ingresos
(5, 6, '2024-12-04 08:00:00', 'ALERTA: El proveedor Servicios Eléctricos de la Costa ha sido BLOQUEADO definitivamente por incumplimiento contractual reiterado.', 5, 5, 1),
(5, 6, '2024-12-16 08:00:00', 'ALERTA: El proveedor Manuel Ortega Rojas (Pintor) tiene el RUT en estado CANCELADO. Se suspenden pagos.', 5, 5, 1),
(4, 5, '2024-12-03 09:05:00', 'El proveedor Muebles y Diseños Modulares ha mejorado su calificación a CONFIABLE (82/100).', 4, 4, 1),
(1, 5, '2024-12-10 16:05:00', 'Primera evaluación generada para Camilo Andres Rios (Desarrollador) - Calificación: CONFIABLE (98/100).', 1, 1, 1),
(2, 5, '2024-12-22 19:05:00', 'Nueva evaluación disponible para Tomas Uribe Velez (Músico) - Calificación: CONFIABLE (95/100).', 2, 2, 1),
-- (Variados 2023-2024)
(1, 2, '2023-04-06 11:00:00', 'Documento Certificación Bancaria aprobado para Consultoría Financiera Integral.', 1, 1, 1),
(2, 2, '2023-05-06 14:30:00', 'Documento RUT aprobado para Fumigaciones y Control de Plagas.', 2, 2, 1),
(3, 1, '2023-08-15 09:00:00', 'Solicitud de actualización de correo electrónico enviada a Agencia de Viajes Fly.', 3, 3, 1),
(4, 4, '2024-02-20 10:00:00', 'El proveedor Inversiones Inmobiliarias El Roble ha sido reactivado tras subsanar hallazgos.', 4, 4, 1),
(1, 2, '2024-03-10 16:00:00', 'Aprobación de póliza de cumplimiento para el contrato de Aseo Total.', 1, 1, 1),
(2, 2, '2024-04-12 11:00:00', 'Actualización de dirección aprobada para Laboratorio Clínico Empresarial.', 2, 2, 1),
(5, 1, '2024-07-01 08:00:00', 'Recordatorio automático: Sus documentos vencen en 30 días (Proveedor: Constructora y Mantenimiento Vial).', 5, 5, 1),
(3, 5, '2024-08-20 15:00:00', 'Notificación de puntaje enviada al proveedor Artesanías y Regalos Corporativos.', 3, 3, 1),
(1, 2, '2023-09-10 09:00:00', 'Validación de seguridad social aprobada para Juan Carlos Perez (Abogado).', 1, 1, 1),
(2, 2, '2023-10-05 14:00:00', 'Validación de seguridad social aprobada para Maria Alejandra Rodriguez (Contadora).', 2, 2, 1),
(3, 2, '2023-11-12 10:00:00', 'Validación de seguridad social aprobada para Luis Fernando Martinez (Plomero).', 3, 3, 1),
(4, 2, '2024-02-15 16:00:00', 'Validación de seguridad social aprobada para Ana Beatriz Hernandez (Traductora).', 4, 4, 1),
(1, 2, '2024-03-20 09:00:00', 'Validación de seguridad social aprobada para Jorge Eliecer Ramirez (Electricista).', 1, 1, 1),
(2, 2, '2024-05-10 11:00:00', 'Validación de seguridad social aprobada para Claudia Patricia Vargas (Psicóloga).', 2, 2, 1),
(5, 2, '2024-07-15 08:00:00', 'Validación de seguridad social aprobada para Andres Felipe Castro (Arquitecto).', 5, 5, 1),
(1, 2, '2024-08-22 15:00:00', 'Validación de seguridad social aprobada para Sofia Mendez Valencia (Diseñadora).', 1, 1, 1),
(2, 2, '2024-09-05 10:00:00', 'Validación de seguridad social aprobada para Ricardo Silva Montoya (Transporte).', 2, 2, 1),
(3, 2, '2024-10-10 14:00:00', 'Validación de seguridad social aprobada para Elena Duarte Pineda (Catering).', 3, 3, 1),
(4, 2, '2024-11-01 16:00:00', 'Validación de seguridad social aprobada para Fernando Lopez Osorio (Mantenimiento).', 4, 4, 1),
(1, 2, '2024-11-15 09:00:00', 'Validación de seguridad social aprobada para Gloria Ines Suarez (Aseo).', 1, 1, 1),
(2, 2, '2024-11-20 11:00:00', 'Validación de seguridad social aprobada para Hector Vega Cordoba (Jardinero).', 2, 2, 1),
(3, 2, '2024-11-25 15:00:00', 'Validación de seguridad social aprobada para Isabel Cristina Pena (Community Manager).', 3, 3, 1),
(4, 2, '2024-11-28 10:00:00', 'Validación de seguridad social aprobada para Javier Buitrago Salinas (Fotógrafo).', 4, 4, 1),
(1, 2, '2024-12-01 08:00:00', 'Validación de seguridad social aprobada para Laura Guzman Tovar (Eventos).', 1, 1, 1),
(2, 2, '2024-12-05 14:00:00', 'Validación de seguridad social aprobada para Natalia Cardenas Florez (Coach).', 2, 2, 1),
(3, 2, '2024-12-10 09:00:00', 'Validación de seguridad social aprobada para Oscar Morales Gil (SST).', 3, 3, 1),
(4, 2, '2024-12-15 16:00:00', 'Validación de seguridad social aprobada para Patricia Henao Restrepo (Redacción).', 4, 4, 1),
(1, 2, '2024-12-20 11:00:00', 'Validación de seguridad social aprobada para Roberto Navarro Cruz (Mensajero).', 1, 1, 1);

-- tablas de relacion muchos a muchos

INSERT INTO proveedor_contacto (
    id_proveedor, id_contacto, estado_contacto, creado_por, activo
) VALUES 
-- CASO ESPECIAL: Proveedor 1 (Tecnología y Sistemas)
-- Tiene 1 contacto actual y 2 históricos (inactivos)
(1, 1, 1, 1, 1), -- Camilo Torres (ACTUAL)
(1, 2, 0, 1, 1), -- Roberto Antiguo (INACTIVO - Histórico)
(1, 3, 0, 1, 1), -- Laura Pasada (INACTIVO - Histórico)

-- RELACIÓN 1 A 1 (Proveedores Jurídicos 2-25)
(2, 4, 1, 2, 1),
(3, 5, 1, 1, 1),
(4, 6, 1, 3, 1),
(5, 7, 1, 2, 1),
(6, 8, 1, 1, 1),
(7, 9, 1, 4, 1),
(8, 10, 1, 1, 1),
(9, 11, 1, 2, 1),
(10, 12, 1, 3, 1),
(11, 13, 1, 1, 1),
(12, 14, 1, 5, 1),
(13, 15, 1, 2, 1),
(14, 16, 1, 1, 1),
(15, 17, 1, 4, 1),
(16, 18, 1, 3, 1),
(17, 19, 1, 1, 1),
(18, 20, 1, 2, 1),
(19, 21, 1, 1, 1),
(20, 22, 1, 5, 1),
(21, 23, 1, 3, 1),
(22, 24, 1, 1, 1),
(23, 25, 1, 2, 1),
(24, 26, 1, 4, 1),
(25, 27, 1, 3, 1),

-- RELACIÓN 1 A 1 (Proveedores Naturales 26-50)
(26, 28, 1, 1, 1),
(27, 29, 1, 2, 1),
(28, 30, 1, 1, 1),
(29, 31, 1, 3, 1),
(30, 32, 1, 4, 1),
(31, 33, 1, 2, 1),
(32, 34, 1, 1, 1),
(33, 35, 1, 5, 1),
(34, 36, 1, 2, 1),
(35, 37, 1, 1, 1),
(36, 38, 1, 3, 1),
(37, 39, 1, 1, 1),
(38, 40, 1, 4, 1),
(39, 41, 1, 2, 1),
(40, 42, 1, 1, 1),
(41, 43, 1, 3, 1),
(42, 44, 1, 2, 1),
(43, 45, 1, 1, 1),
(44, 46, 1, 5, 1),
(45, 47, 1, 4, 1),
(46, 48, 1, 1, 1),
(47, 49, 1, 2, 1),
(48, 50, 1, 3, 1),
(49, 51, 1, 1, 1),
(50, 52, 1, 2, 1),
(2, 53, 1, 1, 1),  -- Proveedor 2 tiene un segundo contacto (Mariana Pajon)
(3, 54, 1, 1, 1),  -- Proveedor 3 tiene un segundo contacto (Nairo Quintana)
(4, 55, 1, 1, 1);  -- Proveedor 4 tiene un segundo contacto (Egan Bernal)

INSERT INTO ubicacion (
    id_proveedor, id_municipio, direccion, creado_por, modificado_por, activo
) VALUES 
-- UBICACIONES PRINCIPALES - PROVEEDORES JURÍDICOS (1-25)
(1, 1, 'Calle 100 # 8A-55 Torre C Piso 10 (Sede Administrativa)', 1, 1, 1), -- Bogotá
(2, 2, 'Carrera 43A # 1Sur-188 (El Poblado)', 2, 2, 1), -- Medellín
(3, 3, 'Avenida 6N # 20-30 Oficina 405', 1, 1, 1), -- Cali
(4, 4, 'Via 40 # 73-290 (Zona Industrial)', 3, 3, 1), -- Barranquilla
(5, 1, 'Transversal 23 # 95-12', 2, 2, 1), -- Bogotá
(6, 1, 'Calle 13 # 68-20 (Zona Franca Fontibón)', 1, 1, 1), -- Bogotá (Logística)
(7, 5, 'Carrera 27 # 36-14', 4, 4, 1), -- Bucaramanga
(8, 1, 'Carrera 7 # 72-15 Edificio Skandia', 1, 1, 1), -- Bogotá
(9, 2, 'Calle 33 # 74-10 (Laureles)', 2, 2, 1), -- Medellín
(10, 3, 'Calle 15 # 4-20 (Centro)', 3, 3, 1), -- Cali
(11, 1, 'Avenida Esperanza # 50-10', 1, 1, 1), -- Bogotá
(12, 4, 'Calle 72 # 54-10', 5, 5, 1), -- Barranquilla
(13, 1, 'Carrera 15 # 93-60 (Parque de la 93)', 2, 2, 1), -- Bogotá
(14, 2, 'Loma del Escobero Km 5', 1, 1, 1), -- Medellín (Constructora)
(15, 1, 'Autopista Norte # 120-15', 4, 4, 1), -- Bogotá
(16, 6, 'Bocagrande Carrera 2 # 8-20', 3, 3, 1), -- Cartagena
(17, 1, 'Calle 85 # 11-53', 1, 1, 1), -- Bogotá
(18, 1, 'Diagonal 25G # 95A-85', 2, 2, 1), -- Bogotá
(19, 3, 'Avenida Pasoancho # 66-10', 1, 1, 1), -- Cali
(20, 1, 'Avenida Calle 26 # 68B - 80 (Dirección desactualizada)', 5, 5, 1), -- Bogotá (El proveedor problemático)
(21, 5, 'Calle 45 # 23-10', 3, 3, 1), -- Bucaramanga
(22, 1, 'Calle 53 # 18-25 (Galerías)', 1, 1, 1), -- Bogotá
(23, 2, 'Carrera 70 # 45-12', 2, 2, 1), -- Medellín
(24, 1, 'Carrera 11 # 82-01 (Centro Andino)', 4, 4, 1), -- Bogotá
(25, 6, 'Centro Histórico Calle de la Moneda # 3-10', 3, 3, 1), -- Cartagena
-- UBICACIONES ADICIONALES (SUCURSALES Y BODEGAS) - MULTI-UBICACIÓN
(1, 2, 'Carrera 48 # 10-45 (Sucursal Antioquia)', 1, 1, 1), -- Prov 1 en Medellín
(1, 3, 'Avenida 3N # 40-20 (Sucursal Valle)', 1, 1, 1), -- Prov 1 en Cali
(3, 1, 'Calle 80 # 102-20 (Bodega de Insumos)', 1, 1, 1), -- Prov 3 en Bogotá
(4, 1, 'Carrera 30 # 19-10 (Oficina Comercial Bogotá)', 3, 3, 1), -- Prov 4 en Bogotá
(6, 4, 'Sociedad Portuaria Regional de Barranquilla (Muelle 3)', 1, 1, 1), -- Prov 6 en Barranquilla (Puerto)
(6, 6, 'Mamonal Km 3 (Centro de Distribución Caribe)', 1, 1, 1), -- Prov 6 en Cartagena
(14, 1, 'Sala de Ventas Proyecto Nogal (Calle 170 # 8-10)', 1, 1, 1), -- Prov 14 (Constructora)
(14, 5, 'Campamento de Obra Vía Girón', 1, 1, 1), -- Prov 14 (Obra civil)
(24, 2, 'WeWork El Poblado (Oficina Satélite)', 4, 4, 1), -- Prov 24 (Software)
-- UBICACIONES PRINCIPALES - PERSONAS NATURALES (26-50)
(26, 1, 'Calle 140 # 11-20 Apto 501', 1, 1, 1), -- Abogado (Oficina/Casa)
(27, 2, 'Circular 4 # 70-10', 2, 2, 1), -- Contadora
(28, 1, 'Carrera 50 # 22-10 (Coworking)', 1, 1, 1), -- IT Freelance
(29, 3, 'Calle 5 # 38-15', 3, 3, 1), -- Plomero
(30, 1, 'Calle 127 # 19-20', 4, 4, 1), -- Traductora
(31, 5, 'Carrera 33 # 48-10', 2, 2, 1), -- Electricista
(32, 1, 'Calle 106 # 54-12 Cons 302', 1, 1, 1), -- Psicóloga
(33, 4, 'Carrera 53 # 80-10', 5, 5, 1), -- Arquitecto
(34, 2, 'Calle 10 # 36-10 (Poblado)', 2, 2, 1), -- Diseñadora
(35, 1, 'Diagonal 13 # 45-20', 1, 1, 1), -- Transporte Carga
(36, 6, 'Manga Avenida Jimenez # 20-10', 3, 3, 1), -- Catering
(37, 1, 'Calle 147 # 7-20 (Home Office)', 1, 1, 1), -- Dev Web
(38, 3, 'Calle 9 # 40-10', 4, 4, 1), -- Mantenimiento
(39, 2, 'Carrera 80 # 45-10', 2, 2, 1), -- Aseo
(40, 1, 'Suba Calle 145 # 90-10', 1, 1, 1), -- Jardinero
(41, 5, 'Calle 56 # 27-10', 3, 3, 1), -- Community Manager
(42, 1, 'Carrera 4 # 12-10', 2, 2, 1), -- Fotógrafo
(43, 1, 'Calle 93 # 13-45', 1, 1, 1), -- Eventos
(44, 4, 'Calle 30 # 4-10', 5, 5, 1), -- Pintor (Bloqueado pero tiene dirección)
(45, 2, 'Transversal Inferior # 10-20', 4, 4, 1), -- Coach
(46, 1, 'Calle 26 # 50-20', 1, 1, 1), -- SST
(47, 3, 'Avenida 4N # 10-20', 2, 2, 1), -- Redacción
(48, 1, 'Calle 63 # 15-20', 3, 3, 1), -- Mensajero
(49, 2, 'Carrera 76 # 30-10', 1, 1, 1), -- Flores
(50, 1, 'Calle 80 # 13-10 (Estudio)', 2, 2, 1), -- Músico
-- UBICACIONES SECUNDARIAS - PERSONAS NATURALES
(33, 1, 'Calle 26 # 13-10 (Oficina Bogotá)', 5, 5, 1), -- Arquitecto tiene oficina en otra ciudad
(35, 3, 'Parqueadero de Carga Menga', 1, 1, 1), -- Transportador tiene punto en Cali
(43, 6, 'Centro de Convenciones Cartagena (Punto de Atención)', 1, 1, 1); -- Planner de Eventos

INSERT INTO representante_proveedor 
(id_representante_legal, id_proveedor, cargo, fecha_inicio, fecha_fin, creado_por, activo)
VALUES 
(1, 1, 'Gerente General', '2023-01-10', NULL, 1, TRUE),
(2, 2, 'Representante Legal', '2023-01-11', NULL, 1, TRUE),
(3, 3, 'Gerente General', '2023-01-12', NULL, 1, TRUE),
(4, 4, 'Representante Legal', '2023-01-13', NULL, 1, TRUE),
(5, 5, 'Director Ejecutivo', '2023-01-14', NULL, 1, TRUE),
(6, 6, 'Gerente General', '2023-01-15', NULL, 1, TRUE),
(7, 7, 'Representante Legal', '2023-01-16', NULL, 1, TRUE),
(8, 8, 'Gerente General', '2023-01-17', NULL, 1, TRUE),
(9, 9, 'Representante Legal', '2023-01-18', NULL, 1, TRUE),
(10, 10, 'Gerente General', '2023-01-19', NULL, 1, TRUE),
(11, 11, 'Gerente Administrativo', '2023-02-01', NULL, 1, TRUE),
(12, 12, 'Representante Legal', '2023-02-02', NULL, 1, TRUE),
(13, 13, 'Gerente General', '2023-02-03', NULL, 1, TRUE),
(14, 14, 'Apoderado General', '2023-02-04', NULL, 1, TRUE),
(15, 15, 'Gerente General', '2023-02-05', NULL, 1, TRUE),
(16, 16, 'Representante Legal', '2023-02-06', NULL, 1, TRUE),
(17, 16, 'Gerente Comercial', '2023-02-07', NULL, 1, TRUE),
(18, 17, 'RL Principal', '2022-06-01', NULL, 1, TRUE),
(19, 17, 'RL Suplente', '2022-06-01', NULL, 1, TRUE),
(20, 18, 'Gerente General', '2022-06-01', NULL, 1, TRUE),
(21, 18, 'Subgerente', '2022-06-01', NULL, 1, TRUE),
(22, 19, 'Presidente', '2022-06-01', NULL, 1, TRUE),
(23, 19, 'Vicepresidente', '2022-06-01', NULL, 1, TRUE),
(24, 20, 'RL Principal', '2022-06-01', NULL, 1, TRUE),
(25, 20, 'RL Suplente', '2022-06-01', NULL, 1, TRUE),
(26, 21, 'Gerente', '2022-06-01', NULL, 1, TRUE),
(27, 21, 'Suplente', '2022-06-01', NULL, 1, TRUE),
(28, 22, 'RL Principal', '2022-06-01', NULL, 1, TRUE),
(29, 22, 'RL Suplente', '2022-06-01', NULL, 1, TRUE),
(30, 23, 'Gerente', '2022-06-01', NULL, 1, TRUE),
(31, 23, 'Subgerente', '2022-06-01', NULL, 1, TRUE),
(32, 24, 'Suplente', '2022-06-01', NULL, 1, TRUE),
(33, 24, 'Gerente', '2015-01-01', '2018-01-01', 1, FALSE), -- Viejo
(33, 24, 'Gerente', '2024-02-01', NULL, 1, TRUE),        -- Nuevo y Actual
(34, 25, 'Gerente General', '2020-01-01', '2023-12-31', 1, FALSE), -- Inactivo
(35, 25, 'Gerente General', '2024-01-01', NULL, 1, TRUE);        -- Activo


INSERT INTO documento_socio_proveedor 
(id_socio_proveedor, id_documento, creado_por, activo)
VALUES 
-- =======================================================
-- GRUPO 1: Socios con Documentación Completa (Cédula y RUT)
-- Asumimos Socios del 1 al 20
-- =======================================================
(1, 1, 1, TRUE), (1, 2, 1, TRUE),   -- Socio 1: 2 docs activos
(2, 3, 1, TRUE), (2, 4, 1, TRUE),   -- Socio 2: 2 docs activos
(3, 5, 1, TRUE), (3, 6, 1, TRUE),
(4, 7, 1, TRUE), (4, 8, 1, TRUE),
(5, 9, 1, TRUE), (5, 10, 1, TRUE),
(6, 11, 1, TRUE), (6, 12, 1, TRUE),
(7, 13, 1, TRUE), (7, 14, 1, TRUE),
(8, 15, 1, TRUE), (8, 16, 1, TRUE),
(9, 17, 1, TRUE), (9, 18, 1, TRUE),
(10, 19, 1, TRUE), (10, 20, 1, TRUE),
(11, 21, 1, TRUE), (11, 22, 1, TRUE),
(12, 23, 1, TRUE), (12, 24, 1, TRUE),
(13, 25, 1, TRUE), (13, 26, 1, TRUE),
(14, 27, 1, TRUE), (14, 28, 1, TRUE),
(15, 29, 1, TRUE), (15, 30, 1, TRUE),
(16, 31, 1, TRUE), (16, 32, 1, TRUE),
(17, 33, 1, TRUE), (17, 34, 1, TRUE),
(18, 35, 1, TRUE), (18, 36, 1, TRUE),
(19, 37, 1, TRUE), (19, 38, 1, TRUE),
(20, 39, 1, TRUE), (20, 40, 1, TRUE),
-- =======================================================
-- GRUPO 2: Socios con Documentación Básica (Solo 1 doc)
-- Asumimos Socios del 21 al 40
-- =======================================================
(21, 41, 1, TRUE),
(22, 42, 1, TRUE),
(23, 43, 1, TRUE),
(24, 44, 1, TRUE),
(25, 45, 1, TRUE),
(26, 46, 1, TRUE),
(27, 47, 1, TRUE),
(28, 48, 1, TRUE),
(29, 49, 1, TRUE),
(30, 50, 1, TRUE),
(31, 51, 1, TRUE),
(32, 52, 1, TRUE),
(33, 53, 1, TRUE),
(34, 54, 1, TRUE),
(35, 55, 1, TRUE),
(36, 56, 1, TRUE),
(40, 60, 1, TRUE),
-- =======================================================
-- GRUPO 3: Historial y Casos Inactivos (Soft Delete)
-- Socios del 41 al 50. 
-- Aquí usamos 'activo = FALSE' para indicar documentos vencidos o rechazados
-- =======================================================
-- Socio 41: Tenía un documento (61) que venció, ahora tiene uno nuevo (62)
(41, 61, 1, FALSE), 
(41, 62, 1, TRUE),
-- Socio 38: Solo tiene un documento antiguo inactivo (Pendiente de actualizar)
(38, 63, 1, FALSE),

-- Socio 37: Subió un documento erróneo (65) se desactivó y subió el correcto (66)
(37, 65, 1, FALSE),
(37, 66, 1, TRUE),

-- Socio 39: Tiene 3 documentos asociados (Histórico, Principal, Adicional)
(39, 68, 1, FALSE), -- Vieja cédula
(39, 69, 1, TRUE),  -- Nueva cédula
(39, 70, 1, TRUE);  -- Acta de asamblea


INSERT INTO historial_usuario (id_usuario, id_estado_usuario, comentarios, creado_por, fecha_creado)
VALUES 
-- Usuarios 1 al 10 (Administrativos - Creados hace meses)
(1, 1, 'Creación inicial de cuenta Super Admin', 1, '2023-01-01 08:00:00'),
(2, 1, 'Creación de cuenta usuario', 1, '2023-01-02 09:00:00'),
(3, 1, 'Creación de cuenta usuario', 1, '2023-01-02 09:05:00'),
(4, 1, 'Creación de cuenta usuario', 1, '2023-01-02 09:10:00'),
(5, 1, 'Creación de cuenta usuario', 1, '2023-01-03 10:00:00'),
(6, 1, 'Creación de cuenta usuario', 1, '2023-01-03 10:15:00'),
(7, 1, 'Creación de cuenta usuario', 1, '2023-01-04 11:00:00'),
(8, 1, 'Creación de cuenta usuario', 1, '2023-01-04 11:30:00'),
(9, 1, 'Creación de cuenta usuario', 1, '2023-01-05 08:30:00'),
(10, 1, 'Creación de cuenta usuario', 1, '2023-01-05 09:00:00'),
-- Usuarios 11 al 40 (Operativos - Carga masiva)
(11, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(12, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(13, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(14, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(15, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(16, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(17, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(18, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(19, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
(20, 1, 'Alta masiva de personal operativo', 1, '2023-02-01 08:00:00'),
-- (Simulamos del 21 al 40 con un insert genérico para no hacer la lista eterna, 
-- pero en producción deben ir uno por uno)
(21, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(22, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(23, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(24, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(25, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(26, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(27, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(28, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(29, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(30, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(31, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(32, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(33, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(34, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(35, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(36, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(37, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(38, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(39, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
(40, 1, 'Ingreso personal planta', 1, '2023-03-01 08:00:00'),
-- Usuarios 41 al 50 (Casos especiales: Proveedores externos o temporales)
(41, 1, 'Cuenta creada, pendiente validación email', 1, '2024-01-10 09:00:00'),
(42, 1, 'Cuenta creada, pendiente validación email', 1, '2024-01-10 09:05:00'),
(43, 1, 'Acceso temporal auditoría', 1, '2024-01-15 08:00:00'),
(44, 1, 'Acceso temporal auditoría', 1, '2024-01-15 08:00:00'),
(45, 1, 'Usuario estándar', 1, '2024-01-20 10:00:00'),
(46, 1, 'Usuario estándar', 1, '2024-01-20 10:05:00'),
(47, 1, 'Usuario estándar', 1, '2024-01-20 10:10:00'),
(48, 1, 'Usuario estándar', 1, '2024-01-20 10:15:00'),
(49, 1, 'Usuario estándar', 1, '2024-01-20 10:20:00'),
(50, 1, 'Usuario estándar', 1, '2024-01-20 10:25:00');
-- ==============================================================================
-- 2. HISTORIAL DE CAMBIOS (Eventos posteriores)
-- Simulamos lo que pasa días o meses después de la creación.
-- ==============================================================================
INSERT INTO historial_usuario (id_usuario, id_estado_usuario, comentarios, creado_por, fecha_creado)
VALUES 
-- CASO A: Usuario 41 y 42 validan su correo y pasan a ACTIVO (1)
(41, 1, 'Validación de correo completada exitosamente', 1, '2024-01-10 10:30:00'),
(42, 1, 'Validación de correo completada exitosamente', 1, '2024-01-10 11:15:00'),
-- CASO B: Usuario 5 olvida contraseña muchas veces -> BLOQUEADO (3)
(5, 2, 'Bloqueo automático: 5 intentos fallidos de inicio de sesión', 1, '2024-02-15 14:22:00'),
-- CASO C: Usuario 5 llama a soporte, valida identidad -> ACTIVO (1)
(5, 1, 'Desbloqueo manual tras verificación de identidad con Supervisor', 1, '2024-02-15 15:00:00'),
-- CASO D: Usuario 10 renuncia a la empresa -> INACTIVO (2)
(10, 2, 'Retiro voluntario del empleado. Baja de credenciales.', 1, '2024-03-01 17:00:00'),
-- CASO E: Usuario 43 termina su auditoría -> INACTIVO (2)
(43, 2, 'Fin del contrato de auditoría externa', 1, '2024-03-15 18:00:00'),
-- CASO F: Usuario 20 es detectado haciendo mal uso -> BLOQUEADO (3)
(20, 2, 'Suspensión preventiva por investigación interna RRHH', 1, '2024-04-10 09:30:00'),
-- CASO G: Usuario 20 es declarado inocente -> ACTIVO (1)
(20, 1, 'Reactivación de cuenta. Investigación cerrada sin hallazgos.', 1, '2024-04-12 10:00:00'),
-- CASO H: Usuario 50 (Soporte) sale de vacaciones -> INACTIVO TEMPORAL (2)
(50, 2, 'Desactivación temporal por periodo vacacional', 1, '2024-05-01 08:00:00'),
-- Regresa de vacaciones
(50, 1, 'Reactivación por retorno de vacaciones', 1, '2024-05-15 08:00:00');