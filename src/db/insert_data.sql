
USE golden_odds;


INSERT INTO pais (codigo, nombre) VALUES 
('COL','Colombia');

INSERT INTO departamento (codigo, nombre, id_pais) VALUES 
('CO-ANT','Antioquia',1), 
('CO-DC','Bogota D.C.',1),
('CO-VAC','Valle del Cauca',1),
('CO-ATL','Atlantico',1);

INSERT INTO municipio (codigo, nombre, id_departamento) VALUES 
('MEDE','Medellin',1), 
('BOG','Bogota',2),
('CALI','Cali',3),
('BARR','Barranquilla',4);


INSERT INTO tipo_identificacion (codigo, descripcion) VALUES 
('NIT','NIT'),
('CC','Cedula de ciudadania'),
('CE','Cedula de extranjeria');

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

INSERT INTO tipo_pago (codigo, descripcion) VALUES 
('TRANSFER','Transferencia'),
('CONTADO','Contado');

INSERT INTO tipo_documento (codigo, descripcion) VALUES 
('RUT','Registro Unico Tributario'),
('CAMARA','Camara de Comercio'),
('CERT','Certificado'),
('ID','Documento de identidad');

INSERT INTO origen_dato (codigo, descripcion) VALUES 
('MANUAL','Carga manual'),
('EXTERNO','Consulta externa');

INSERT INTO roles (rol, descripcion) VALUES 
('ADMIN','Administrador'),
('AUDITOR','Auditor'),
('USUARIO','Usuario del sistema');

INSERT INTO estado_usuario (codigo, descripcion) VALUES 
('ACT','Activo'),
('INA','Inactivo');


INSERT INTO usuarios (nombre_usuario, cargo_usuario, correo_usuario, contrasena, id_rol) VALUES
('Juan Carlos Cadena','Coordinador TI','juan.cadena@example.com','$2y$12$demo',1),
('Adriana Barrios','Analista de calidad','adriana.barrios@example.com','$2y$12$demo',2),
('Sergio Morales','Administrador','sergio.morales@example.com','$2y$12$demo',1);


INSERT INTO campo_validacion (id_tipo_documento, campo, obligatorio) VALUES
(1,'numero_documento',TRUE),
(2,'numero_documento',TRUE),
(3,'fecha_emision',FALSE);


INSERT INTO calificacion (codigo, descripcion) VALUES 
('A','Excelente'),
('B','Aceptable'),
('C','Riesgo');


INSERT INTO tipo_notificacion (codigo, descripcion) VALUES 
('DOC','Documento'),
('EST','Estado');

-
INSERT INTO proveedores (id_tipo_identificacion, numero_identificacion, digito_verificacion, razon_social, nombres, apellidos, id_tipo_persona, telefono_principal, id_tipo_telefono, correo_principal, id_estado_proveedor, descripcion, id_usuario) VALUES
(1,'900123456',0,'Soluciones Andinas S.A.S.',NULL,NULL,2,'6041234567',1,'contacto@soluandinas.com',1,'Proveedor de insumos informaticos',1),
(1,'900223456',0,'Servicios Logitek SAS',NULL,NULL,2,'6047654321',1,'info@logitek.com',1,'Soporte tecnico',1),
(2,'1023456789',NULL,NULL,'Carlos','Mejia',1,'3101234567',1,'cmejia@gmail.com',1,'Tecnico independiente',2),
(2,'1023456790',NULL,NULL,'Maria','Gonzalez',1,'3119876543',1,'maria.g@correo.com',1,'Especialista en redes',2),
(1,'900323456',0,'TecnoSoluciones Ltda',NULL,NULL,2,'6043334444',1,'ventas@tecnosol.com',1,'Distribuidor',1),
(2,'1023456791',NULL,NULL,'Andres','Perez',1,'3155556666',1,'andres.p@example.com',1,'Consultor',3),
(1,'900423456',0,'Comercial Norte S.A.',NULL,NULL,2,'6044445555',1,'contacto@cnorte.com',1,'Suministros generales',1),
(2,'1023456792',NULL,NULL,'Laura','Ramirez',1,'3122223333',1,'laura.r@example.com',1,'Ingeniera de sistemas',2),
(1,'900523456',0,'GlobalTech Colombia',NULL,NULL,2,'6049998888',1,'info@globaltech.co',1,'Hardware y software',1),
(2,'1023456793',NULL,NULL,'Diego','Rojas',1,'3177776666',1,'drojas@example.com',1,'Proveedor independiente',3),
(1,'900623456',0,'Insumos Medicos SAS',NULL,NULL,2,'6041112222',1,'ventas@insumosmed.com',1,'Insumos hospitalarios',1),
(2,'1023456794',NULL,NULL,'Paula','Cardenas',1,'3188887777',1,'paula.c@example.com',1,'Tecnica',2),
(1,'900723456',0,'Soluciones Agro SAS',NULL,NULL,2,'6046665555',1,'agro@soluciones.com',1,'Agricultura',1),
(2,'1023456795',NULL,NULL,'Fernando','Lopez',1,'3196665555',1,'f.lopez@example.com',1,'Tecnico en mantenimiento',3),
(1,'900823456',0,'Servicios Integrales Gomez',NULL,NULL,2,'6047776666',1,'gomez@servicios.com',1,'Servicios varios',1),
(2,'1023456796',NULL,NULL,'Natalia','Silva',1,'3205554444',1,'natalia.s@example.com',1,'Soporte',2),
(1,'900923456',0,'Alpha Sistemas S.A.S.',NULL,NULL,2,'6042221111',1,'alpha@sys.com',1,'Software y consultoria',1),
(2,'1023456797',NULL,NULL,'Ricardo','Martinez',1,'3214443333',1,'ricardo.m@example.com',1,'Freelance',3),
(1,'901023456',0,'Distribuciones Andinas',NULL,NULL,2,'6043332222',1,'dist@andinas.com',1,'Logistica',1),
(2,'1023456798',NULL,NULL,'Sofia','Herrera',1,'3223334444',1,'sofia.h@example.com',1,'Asesora',2);


INSERT INTO ubicacion (id_proveedor, id_municipio, direccion) VALUES
(1,1,'Calle 10 #45-20, Medellin'),
(2,1,'Av. 80 #34-12, Medellin'),
(3,2,'Carrera 5 #12-34, Bogota'),
(4,2,'Calle 12 #4-56, Bogota'),
(5,1,'Parque Industrial Km 4'),
(6,3,'Avenida 6N #23-45, Cali'),
(7,4,'Carrera 54 #68-12, Barranquilla');


INSERT INTO contacto (nombre_contacto, cargo_contacto, id_tipo_telefono, telefono_contacto, correo_contacto) VALUES
('Luis Torres','Jefe de compras',1,'3101002001','luis.torres@soluandinas.com'),
('Mariana Ruiz','Coordinadora',1,'3102003002','mariana.ruiz@logitek.com'),
('Carlos Medina','Soporte',1,'3103004003','c.medina@tecnosol.com'),
('Ana Villamil','Finanzas',1,'3104005004','ana.v@cnorte.com'),
('Pedro Castillo','Representante',1,'3105006005','pedro.castillo@globaltech.co'),
('Diana Mejia','Compras',1,'3106007006','diana.mejia@insumosmed.com'),
('Esteban Cruz','Logistica',1,'3107008007','esteban.cruz@agro.com'),
('Rosa Morales','Asistente',1,'3108009008','rosa.morales@gomez.com'),
('Eduardo Perez','Gerente',1,'3109001009','eduardo.perez@alpha.com'),
('Valentina Soto','Asesora',1,'3110001110','valentina.soto@dist.com'),
('Andres Gil','Tecnico',1,'3111002221','andres.gil@example.com'),
('Catalina Lopez','Analista',1,'3112003332','catalina.lopez@example.com'),
('Javier Nunez','Representante',1,'3113004443','javier.nunez@example.com'),
('Clara Mejia','Coordinadora',1,'3114005554','clara.mejia@example.com'),
('Miguel Angel','Supervisor',1,'3115006665','miguel.angel@example.com'),
('Laura Osorio','Auditor',1,'3116007776','laura.osorio@example.com'),
('Renata Diaz','Jefe RRHH',1,'3117008887','renata.diaz@example.com'),
('Hugo Salazar','Administrador',1,'3118009998','hugo.salazar@example.com'),
('Beatriz Rojas','Secretaria',1,'3119000009','beatriz.rojas@example.com'),
('Oscar Herrera','Comprador',1,'3120001112','oscar.herrera@example.com');


INSERT INTO proveedor_contacto (id_proveedor, id_contacto) VALUES
(1,1),(2,2),(5,3),(7,4),(9,5),(11,6),(13,7),(15,8),(17,9),(19,10),
(3,11),(4,12),(6,13),(8,14),(10,15),(12,16),(14,17),(16,18),(18,19),(20,20);

-
INSERT INTO representante_legal (id_tipo_identificacion, numero_identificacion, nombres, apellidos, id_tipo_telefono, telefono, correo, tiene_usuario) VALUES
(2,'102000111','Fernando','Gomez',1,'3101112222','fernando.g@soluandinas.com',FALSE),
(2,'102000112','Maria','Perez',1,'3102223333','maria.p@logitek.com',FALSE);


INSERT INTO representante_proveedor (id_representante_legal, id_proveedor, cargo) VALUES
(1,1,'Gerente General'),
(2,2,'Representante Legal');


INSERT INTO socios_proveedor (id_proveedor, nombres, apellidos, id_tipo_identificacion, numero_identificacion, id_origen, validado) VALUES
(1,'Pedro','Alvarez',2,'102111222',1,TRUE),
(2,'Lucia','Vega',2,'102111223',1,FALSE),
(3,'Gonzalo','Mora',2,'102111224',1,TRUE);

INSERT INTO documentos (id_proveedor, id_tipo_documento, numero_documento, fecha_emision, url_documento, almacenamiento, fecha_carga, estado_documento, validado, creado_por) VALUES
(1,1,'RUT-900123','2021-03-10','https://s3.aws/goldenodds/documentos/rut_900123.pdf','s3','2021-03-11',TRUE,TRUE,1),
(1,2,'CAM-900123','2021-03-15','https://s3.aws/goldenodds/documentos/cam_900123.pdf','s3','2021-03-16',TRUE,TRUE,1),
(2,1,'RUT-900223','2019-08-10','https://s3.aws/goldenodds/documentos/rut_900223.pdf','s3','2019-08-11',TRUE,TRUE,1),
(3,4,'ID-1023456789','2010-01-01','https://s3.aws/goldenodds/documentos/id_1023456789.pdf','s3','2010-01-02',TRUE,TRUE,2),
(4,4,'ID-1023456790','2012-05-12','https://s3.aws/goldenodds/documentos/id_1023456790.pdf','s3','2012-05-13',TRUE,TRUE,2),
(5,2,'CAM-900323','2020-11-20','https://s3.aws/goldenodds/documentos/cam_900323.pdf','s3','2020-11-21',TRUE,TRUE,1),
(6,4,'ID-1023456791','2018-02-01','https://s3.aws/goldenodds/documentos/id_1023456791.pdf','s3','2018-02-02',TRUE,TRUE,3),
(7,2,'CAM-900423','2017-06-13','https://s3.aws/goldenodds/documentos/cam_900423.pdf','s3','2017-06-14',TRUE,TRUE,1),
(8,4,'ID-1023456792','2015-09-09','https://s3.aws/goldenodds/documentos/id_1023456792.pdf','s3','2015-09-10',FALSE,FALSE,2),
(9,2,'CAM-900523','2022-01-05','https://s3.aws/goldenodds/documentos/cam_900523.pdf','s3','2022-01-06',TRUE,TRUE,1),
(10,4,'ID-1023456793','2016-07-07','https://s3.aws/goldenodds/documentos/id_1023456793.pdf','s3','2016-07-08',TRUE,TRUE,3),
(11,2,'CAM-900623','2019-10-10','https://s3.aws/goldenodds/documentos/cam_900623.pdf','s3','2019-10-11',TRUE,TRUE,1),
(12,4,'ID-1023456794','2014-04-04','https://s3.aws/goldenodds/documentos/id_1023456794.pdf','s3','2014-04-05',TRUE,TRUE,2),
(13,2,'CAM-900723','2020-06-01','https://s3.aws/goldenodds/documentos/cam_900723.pdf','s3','2020-06-02',TRUE,TRUE,1),
(14,4,'ID-1023456795','2013-12-12','https://s3.aws/goldenodds/documentos/id_1023456795.pdf','s3','2013-12-13',TRUE,TRUE,3),
(15,2,'CAM-900823','2021-09-09','https://s3.aws/goldenodds/documentos/cam_900823.pdf','s3','2021-09-10',TRUE,TRUE,1),
(16,4,'ID-1023456796','2011-11-11','https://s3.aws/goldenodds/documentos/id_1023456796.pdf','s3','2011-11-12',TRUE,TRUE,2),
(17,2,'CAM-900923','2020-02-02','https://s3.aws/goldenodds/documentos/cam_900923.pdf','s3','2020-02-03',TRUE,TRUE,1),
(18,4,'ID-1023456797','2010-10-10','https://s3.aws/goldenodds/documentos/id_1023456797.pdf','s3','2010-10-11',FALSE,FALSE,3),
(19,2,'CAM-901023','2018-03-03','https://s3.aws/goldenodds/documentos/cam_901023.pdf','s3','2018-03-04',TRUE,TRUE,1),
(20,4,'ID-1023456798','2019-05-05','https://s3.aws/goldenodds/documentos/id_1023456798.pdf','s3','2019-05-06',TRUE,TRUE,2),
(1,3,'CERT-900123-ISO','2021-07-01','https://s3.aws/goldenodds/documentos/cert_900123_iso.pdf','s3','2021-07-02',TRUE,TRUE,1),
(2,3,'CERT-900223-ISO','2019-09-01','https://s3.aws/goldenodds/documentos/cert_900223_iso.pdf','s3','2019-09-02',TRUE,TRUE,1),
(5,3,'CERT-900323-ISO','2020-12-01','https://s3.aws/goldenodds/documentos/cert_900323_iso.pdf','s3','2020-12-02',TRUE,TRUE,1);


INSERT INTO validacion (id_usuario, id_proveedor, id_campo_validacion, valor_web, valor_documento, id_documento, resultado_validacion, fecha_validacion, comentarios, creado_por) VALUES
(2,1,1,'900123456','900123456',1,TRUE,'2021-03-12','Coincide',2),
(2,2,1,'900223456','900223457',2,FALSE,'2019-08-12','Divergencia en numero',2),
(3,3,1,'1023456789','1023456789',4,TRUE,'2010-01-02','OK',3),
(2,8,1,'1023456792','',8,FALSE,'2015-09-15','Documento no cargado',2);


INSERT INTO evaluacion_proveedor (id_proveedor, id_usuario, id_calificacion, puntaje, observaciones, url_calificacion, creado_por) VALUES
(1,2,1,95,'Proveedor confiable','https://s3.aws/goldenodds/eval/eval1.pdf',2),
(2,3,2,75,'Observaciones en entrega','https://s3.aws/goldenodds/eval/eval2.pdf',3),
(3,2,3,55,'Requiere seguimiento','https://s3.aws/goldenodds/eval/eval3.pdf',2);


INSERT INTO evaluacion_riesgos (id_usuario, id_proveedor, id_validacion, validacion_auditoria, comentarios_auditoria, creado_por) VALUES
(2,1,1,TRUE,'Auditoria OK',2),
(3,2,2,FALSE,'Pendiente de revision',3);


INSERT INTO forma_de_pago (id_proveedor, id_tipo_pago, monto, plazo, creado_por) VALUES
(1,1,1500000,30,2),
(2,2,500000,0,1),
(5,1,2500000,45,1);


INSERT INTO notificaciones (id_usuario, id_tipo_notificacion, mensaje, creado_por) VALUES
(1,1,'Documento RUT cargado para Soluciones Andinas',1),
(2,1,'Documento camara vencido para Servicios Logitek',2);


INSERT INTO proveedor_estado_historial (id_proveedor, id_estado_proveedor, id_usuario, comentarios, creado_por) VALUES
(1,1,1,'Registro inicial',1),
(2,1,2,'Registro inicial',2),
(3,1,3,'Registro inicial',3);


INSERT INTO documentos_socios_proveedor (id_socio_proveedor, id_documento) VALUES 
(1,1),
(2,3);