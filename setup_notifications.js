const mysql = require('mysql2/promise');

async function run() {
    const connection = await mysql.createConnection({
        host: 'bv97bs4agdi6xnhijsf4-mysql.services.clever-cloud.com',
        user: 'ueiv0etteujbrrep',
        password: 'Mvw7U4Yj9xY23N77Uj9R',
        database: 'bv97bs4agdi6xnhijsf4',
        port: 3306
    });

    try {
        console.log('✅ Conexión exitosa a la base de datos.');

        // 1. Validar e insertar tipo_notificacion
        const tipos = [
            { codigo: 'CRE', descripcion: 'Creación de usuario' },
            { codigo: 'APR', descripcion: 'Aprobación Final' },
            { codigo: 'REC', descripcion: 'Rechazo Final' },
            { codigo: 'EVA', descripcion: 'Evaluación de Desempeño' },
            { codigo: 'PRX', descripcion: 'Próximo a Vencer' },
            { codigo: 'VEN', descripcion: 'Documentación Vencida' },
            { codigo: 'EST', descripcion: 'Cambio de Estado en Flujo' },
            { codigo: 'REG', descripcion: 'Expediente Completo' }
        ];

        for (const tipo of tipos) {
            const [rows] = await connection.execute('SELECT * FROM tipo_notificacion WHERE codigo = ?', [tipo.codigo]);
            if (rows.length === 0) {
                await connection.execute(
                    'INSERT INTO tipo_notificacion (codigo, descripcion, activo) VALUES (?, ?, ?)',
                    [tipo.codigo, tipo.descripcion, true]
                );
                console.log(`Insertado tipo_notificacion: ${tipo.codigo}`);
            } else {
                console.log(`tipo_notificacion ya existe: ${tipo.codigo}`);
            }
        }

        // 2. Insertar notificaciones de prueba
        // Primero obtener IDs de los tipos de notificación
        const [tiposRows] = await connection.execute('SELECT id_tipo_notificacion, codigo FROM tipo_notificacion');
        const tiposMap = {};
        tiposRows.forEach(row => {
            tiposMap[row.codigo] = row.id_tipo_notificacion;
        });

        // Insertar notificaciones para un proveedor (usuario ID 4)
        const idProveedor = 4; // Camilo Mendoza
        const idComprador = 7; // Carlos Ruiz
        const idAdmin = 2; // Adriana Barrios

        const notificacionesPrueba = [
            // Para el proveedor
            { id_usuario: idProveedor, id_tipo_notificacion: tiposMap['VEN'], mensaje: 'Tu documentación ha superado los 365 días y tu cuenta está suspendida.', activo: true },
            { id_usuario: idProveedor, id_tipo_notificacion: tiposMap['PRX'], mensaje: 'Tu documentación está próxima a vencer. Actualízala.', activo: true },
            { id_usuario: idProveedor, id_tipo_notificacion: tiposMap['APR'], mensaje: '¡Felicidades! Tu cuenta ha sido aprobada.', activo: true },
            // Para el comprador
            { id_usuario: idComprador, id_tipo_notificacion: tiposMap['REG'], mensaje: 'El proveedor Insumos Delta finalizó la carga de documentos y está listo para revisión.', activo: true },
            { id_usuario: idComprador, id_tipo_notificacion: tiposMap['EST'], mensaje: 'El Analista de Riesgos finalizó la revisión de TechnoParts.', activo: true },
            // Para admin (solo por si acaso)
            { id_usuario: idAdmin, id_tipo_notificacion: tiposMap['EST'], mensaje: 'El Comprador derivó la validación del proveedor X.', activo: true }
        ];

        for (const notif of notificacionesPrueba) {
            if (notif.id_tipo_notificacion) {
                await connection.execute(
                    'INSERT INTO notificaciones (id_usuario, id_tipo_notificacion, mensaje, fecha_notificacion, fecha_creado, creado_por, activo) VALUES (?, ?, ?, NOW(), NOW(), 1, ?)',
                    [notif.id_usuario, notif.id_tipo_notificacion, notif.mensaje, notif.activo]
                );
            }
        }
        console.log('Notificaciones de prueba insertadas correctamente.');

    } catch (err) {
        console.error('❌ Error:', err.message);
    } finally {
        await connection.end();
    }
}

run();
