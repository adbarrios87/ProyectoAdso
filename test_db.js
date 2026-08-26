const mysql = require('mysql2');

function testConnection() {
    console.log('Intentando conectar a Clever Cloud MySQL...');
    const connection = mysql.createConnection({
        host: 'bv97bs4agdi6xnhijsf4-mysql.services.clever-cloud.com',
        user: 'ueiv0etteujbrrep',
        password: 'Mvw7U4Yj9xY23N77Uj9R',
        database: 'bv97bs4agdi6xnhijsf4',
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
