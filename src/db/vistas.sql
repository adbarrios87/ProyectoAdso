-- vistas_golden_odds.sql

USE golden_odds;

-- 1. Vista Maestra de Proveedores
CREATE OR REPLACE VIEW v_perfil_proveedor AS
SELECT 
    p.id_proveedor,
    CASE 
        WHEN p.id_tipo_persona = 2 THEN p.razon_social 
        ELSE CONCAT(COALESCE(p.nombres,''), ' ', COALESCE(p.apellidos,'')) 
    END AS nombre_proveedor,
    ti.codigo AS tipo_doc,
    p.numero_identificacion,
    CASE 
        WHEN p.activo = 1 THEN 'ACTIVO'
        ELSE 'INACTIVO'
    END AS estado_actual,
    mun.nombre AS ciudad,
    dep.nombre AS departamento,
    p.correo_principal,
    p.telefono_principal,
    -- Busca si existe algún hallazgo en la tabla de riesgos
    CASE 
        WHEN EXISTS (SELECT 1 FROM evaluacion_riesgos er WHERE er.id_proveedor = p.id_proveedor AND er.validacion_auditoria = 0) 
        THEN 'ALTO RIESGO'
        ELSE 'NORMAL'
    END AS nivel_riesgo

FROM proveedores p
JOIN tipo_identificacion ti ON p.id_tipo_identificacion = ti.id_tipo_identificacion
LEFT JOIN ubicacion u ON p.id_proveedor = u.id_proveedor
LEFT JOIN municipio mun ON u.id_municipio = mun.id_municipio
LEFT JOIN departamento dep ON mun.id_departamento = dep.id_departamento;

SELECT * FROM v_perfil_proveedor;

-- 2. Vista de Alertas de Riesgo y Listas Restrictivas

CREATE OR REPLACE VIEW v_alertas_riesgo AS
SELECT 
    p.razon_social AS empresa,
    er.fecha AS fecha_alerta,
    v.valor_documento AS dato_analizado,
    cv.campo AS campo_con_error,
    er.comentarios_auditoria AS hallazgo_grave,
    u.nombre_usuario AS auditor_responsable
FROM evaluacion_riesgos er
JOIN proveedores p ON er.id_proveedor = p.id_proveedor
JOIN validacion v ON er.id_validacion = v.id_validacion
JOIN campo_validacion cv ON v.id_campo_validacion = cv.id_campo_validacion
JOIN usuarios u ON er.creado_por = u.id_usuario
WHERE er.validacion_auditoria = 0; -- Solo mostrar los NO aprobados (Riesgos materializados)

SELECT * FROM v_alertas_riesgo;

-- 3. Vista de Representación Legal Vigente
CREATE OR REPLACE VIEW v_representantes_activos AS
SELECT 
    p.numero_identificacion AS nit_proveedor,
    COALESCE(p.razon_social, CONCAT(p.nombres, ' ', p.apellidos)) AS proveedor,
    CONCAT(rl.nombres, ' ', rl.apellidos) AS representante_legal,
    rl.numero_identificacion AS cedula_rl,
    rp.cargo,
    rp.fecha_inicio AS vigencia_desde
FROM representante_proveedor rp
JOIN proveedores p ON rp.id_proveedor = p.id_proveedor
JOIN representante_legal rl ON rp.id_representante_legal = rl.id_representante_legal
WHERE rp.activo = 1 AND (rp.fecha_fin IS NULL OR rp.fecha_fin >= CURDATE());

SELECT * FROM v_representantes_activos;

-- 4. Vista de Desempeño y Calificaciones
CREATE OR REPLACE VIEW v_ranking_proveedores AS
SELECT 
    p.id_proveedor,
    COALESCE(p.razon_social, CONCAT(p.nombres, ' ', p.apellidos)) AS proveedor,
    c.descripcion AS calificacion_cualitativa,
    ep.puntaje,
    ep.observaciones,
    ep.fecha_creado AS fecha_evaluacion
FROM evaluacion_proveedor ep
JOIN proveedores p ON ep.id_proveedor = p.id_proveedor
JOIN calificacion c ON ep.id_calificacion = c.id_calificacion
WHERE ep.fecha_creado = (
    SELECT MAX(ep2.fecha_creado) 
    FROM evaluacion_proveedor ep2 
    WHERE ep2.id_proveedor = ep.id_proveedor
)
ORDER BY ep.puntaje DESC;

SELECT * FROM v_ranking_proveedores;

-- 5. Vista de Socios y Beneficiarios Finales
CREATE OR REPLACE VIEW v_beneficiarios_finales AS
SELECT 
    p.razon_social AS empresa_proveedora,
    sp.nombres AS nombre_socio,
    sp.apellidos AS apellido_socio,
    ti.codigo AS tipo_doc_socio,
    sp.numero_identificacion AS id_socio,
    CASE 
        WHEN sp.resultado_consulta = 1 THEN 'SIN ANTECEDENTES'
        WHEN sp.resultado_consulta = 0 THEN 'CON COINCIDENCIAS'
        ELSE 'PENDIENTE'
    END AS estado_listas
FROM socios_proveedor sp
JOIN proveedores p ON sp.id_proveedor = p.id_proveedor
JOIN tipo_identificacion ti ON sp.id_tipo_identificacion = ti.id_tipo_identificacion
WHERE sp.activo = 1;

SELECT * FROM v_beneficiarios_finales;

-- 6. Vista de Documentación Pendiente o Vencida
CREATE OR REPLACE VIEW v_estado_documental AS
SELECT 
    p.razon_social AS proveedor,
    td.descripcion AS tipo_documento,
    d.fecha_carga,
    CASE 
        WHEN d.validado = 1 THEN 'VALIDADO'
        WHEN d.validado = 0 AND d.estado_documento = 1 THEN 'PENDIENTE VALIDACIÓN'
        ELSE 'RECHAZADO/INACTIVO'
    END AS estado_validacion
FROM documentos d
JOIN proveedores p ON d.id_proveedor = p.id_proveedor
JOIN tipo_documento td ON d.id_tipo_documento = td.id_tipo_documento
WHERE p.activo = 1;

SELECT * FROM v_estado_documental;

-- 7. Vista de Contactos Operativos

CREATE OR REPLACE VIEW v_directorio_contactos AS
SELECT 
    COALESCE(p.razon_social, CONCAT(p.nombres, ' ', p.apellidos)) AS proveedor,
    c.nombre_contacto,
    c.cargo_contacto,
    c.telefono_contacto,
    c.correo_contacto,
    tt.descripcion AS tipo_telefono
FROM proveedor_contacto pc
JOIN proveedores p ON pc.id_proveedor = p.id_proveedor
JOIN contacto c ON pc.id_contacto = c.id_contacto
JOIN tipo_telefono tt ON c.id_tipo_telefono = tt.id_tipo_telefono
WHERE pc.estado_contacto = 1;

SELECT * FROM v_directorio_contactos;

-- 8. Vista Geográfica (Mapa de Calor)

CREATE OR REPLACE VIEW v_distribucion_geografica AS
SELECT 
    d.nombre AS departamento,
    m.nombre AS municipio,
    COUNT(p.id_proveedor) AS cantidad_proveedores
FROM proveedores p
JOIN ubicacion u ON p.id_proveedor = u.id_proveedor
JOIN municipio m ON u.id_municipio = m.id_municipio
JOIN departamento d ON m.id_departamento = d.id_departamento
WHERE p.activo = 1
GROUP BY d.nombre, m.nombre
ORDER BY cantidad_proveedores DESC;

SELECT * FROM v_distribucion_geografica;

-- 9. Vista proveedores_recientes
CREATE OR REPLACE VIEW proveedores_recientes AS
SELECT id_proveedor, COALESCE(razon_social, CONCAT(nombres,' ',apellidos)) AS nombre, fecha_creado
FROM proveedores
WHERE fecha_creado >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY);

SELECT * FROM proveedores_recientes;

-- 10. Vista documentos_vencidos
CREATE OR REPLACE VIEW documentos_vencidos AS
SELECT d.id_documento, d.id_proveedor, COALESCE(p.razon_social, CONCAT(p.nombres,' ',p.apellidos)) AS proveedor,
       d.id_tipo_documento, td.descripcion AS tipo_documento, d.fecha_emision, d.fecha_carga
FROM documentos d
LEFT JOIN proveedores p ON d.id_proveedor = p.id_proveedor
LEFT JOIN tipo_documento td ON d.id_tipo_documento = td.id_tipo_documento
WHERE d.fecha_emision <= DATE_SUB(CURRENT_DATE, INTERVAL 5 YEAR)
   OR d.estado_documento = FALSE
   OR d.validado = FALSE;

SELECT * FROM documentos_vencidos;
   
-- 11. Vista documentos_por_tipo
CREATE OR REPLACE VIEW documentos_por_tipo AS
SELECT td.id_tipo_documento, td.descripcion AS tipo_documento, COUNT(d.id_documento) AS total_documentos
FROM tipo_documento td
LEFT JOIN documentos d ON td.id_tipo_documento = d.id_tipo_documento
GROUP BY td.id_tipo_documento, td.descripcion;

SELECT * FROM documentos_por_tipo;
