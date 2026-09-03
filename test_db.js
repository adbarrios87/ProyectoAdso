const mysql = require('mysql2');

function testConnection() {
    console.log('Intentando conectar a Clever Cloud MySQL...');
    const connection = mysql.createConnection({
        host: 'bx3fcwhyvrme9gt1y86m-mysql.services.clever-cloud.com',
        user: 'ui0zsy2f9b3mdipn',
        password: '9S2dPuwXIFQpRAzS03Sj',
        database: 'bx3fcwhyvrme9gt1y86m',
        port: 3306
    });

    connection.connect((err) => {
        if (err) {
            console.error('❌ Error de conexión:', err.message);
        } else {
            console.log('✅ Conexión exitosa a la base de datos.');
            connection.query('SELECT * FROM usuarios WHERE correo_usuario = ?', ['adbarrios87@gmail.com'], (err, results) => {
                if (err) {
                    console.error('❌ Error al consultar usuarios:', err.message);
                } else {
                    console.log('Usuario encontrado:', results);
                }
                connection.end();
            });
        }
    });
}

testConnection();
