-- vistas_golden_odds.sql

USE golden_odds;

-- 1) Vista: proveedores_activos
CREATE OR REPLACE VIEW proveedores_activos AS
SELECT p.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS nombre,
       p.numero_identificacion, p.telefono_principal, p.correo_principal, p.fecha_creado
FROM proveedores p
JOIN estado_proveedor e ON p.id_estado_proveedor = e.id_estado_proveedor
WHERE e.estado = 'ACTIVO';

-- 2) Vista: contactos_proveedores
CREATE OR REPLACE VIEW contactos_proveedores AS
SELECT pc.id_proveedor, p.razon_social, p.nombres, p.apellidos,
       c.id_contacto, c.nombre_contacto, c.cargo_contacto, c.telefono_contacto, c.correo_contacto
FROM proveedor_contacto pc
JOIN proveedores p ON pc.id_proveedor = p.id_proveedor
JOIN contacto c ON pc.id_contacto = c.id_contacto;

-- 3) Vista: documentos_por_tipo
CREATE OR REPLACE VIEW documentos_por_tipo AS
SELECT td.id_tipo_documento, td.descripcion AS tipo_documento, COUNT(d.id_documento) AS total_documentos
FROM tipo_documento td
LEFT JOIN documentos d ON td.id_tipo_documento = d.id_tipo_documento
GROUP BY td.id_tipo_documento, td.descripcion;

-- 4) Vista: proveedores_recientes
CREATE OR REPLACE VIEW proveedores_recientes AS
SELECT id_proveedor, COALESCE(razon_social, CONCAT(nombres,' ',apellidos)) AS nombre, fecha_creado
FROM proveedores
WHERE fecha_creado >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY);

-- 5) Vista: documentos_pendientes
CREATE OR REPLACE VIEW documentos_pendientes AS
SELECT d.id_documento, d.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       td.descripcion AS tipo_documento, d.numero_documento, d.fecha_emision, d.fecha_carga, d.validado
FROM documentos d
LEFT JOIN proveedores p ON d.id_proveedor = p.id_proveedor
LEFT JOIN tipo_documento td ON d.id_tipo_documento = td.id_tipo_documento
WHERE d.validado = FALSE OR d.estado_documento = FALSE;

-- 6) Vista: proveedores_sin_documentos
CREATE OR REPLACE VIEW proveedores_sin_documentos AS
SELECT p.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor, p.correo_principal
FROM proveedores p
LEFT JOIN documentos d ON p.id_proveedor = d.id_proveedor
WHERE d.id_documento IS NULL;

-- 7) Vista: validaciones_inconsistentes
CREATE OR REPLACE VIEW validaciones_inconsistentes AS
SELECT v.id_validacion, v.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       v.valor_web, v.valor_documento, v.resultado_validacion, v.fecha_validacion, v.comentarios
FROM validacion v
LEFT JOIN proveedores p ON v.id_proveedor = p.id_proveedor
WHERE v.resultado_validacion = FALSE;

-- 8) Vista: evaluaciones_riesgo
CREATE OR REPLACE VIEW evaluaciones_riesgo AS
SELECT er.id_evaluacion_riesgos, er.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       er.validacion_auditoria, er.fecha, er.comentarios_auditoria
FROM evaluacion_riesgos er
LEFT JOIN proveedores p ON er.id_proveedor = p.id_proveedor;

-- 9) Vista: auditoria_detallada
CREATE OR REPLACE VIEW auditoria_detallada AS
SELECT h.id_historial, h.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       e.estado AS estado_anterior, h.comentarios, h.fecha_actualizacion, u.nombre_usuario
FROM proveedor_estado_historial h
LEFT JOIN proveedores p ON h.id_proveedor = p.id_proveedor
LEFT JOIN estado_proveedor e ON h.id_estado_proveedor = e.id_estado_proveedor
LEFT JOIN usuarios u ON h.id_usuario = u.id_usuario;

-- 10) Vista: documentos_vencidos
CREATE OR REPLACE VIEW documentos_vencidos AS
SELECT d.id_documento, d.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       d.id_tipo_documento, td.descripcion AS tipo_documento, d.fecha_emision, d.fecha_carga
FROM documentos d
LEFT JOIN proveedores p ON d.id_proveedor = p.id_proveedor
LEFT JOIN tipo_documento td ON d.id_tipo_documento = td.id_tipo_documento
WHERE d.fecha_emision <= DATE_SUB(CURRENT_DATE, INTERVAL 5 YEAR)
   OR d.estado_documento = FALSE
   OR d.validado = FALSE;