const mysql = require('mysql2/promise');

const config = {
    host: 'bv97bs4agdi6xnhijsf4-mysql.services.clever-cloud.com',
    user: 'ueiv0etteujbrrep',
    password: 'Mvw7U4Yj9xY23N77Uj9R',
    database: 'bv97bs4agdi6xnhijsf4',
    port: 3306
};

async function query(sql) {
    let connection;
    try {
        connection = await mysql.createConnection(config);
        const [rows] = await connection.execute(sql);
        console.log(JSON.stringify(rows, null, 2));
    } catch (error) {
        console.error('❌ Error:', error.message);
    } finally {
        if (connection) await connection.end();
    }
}

const sql = process.argv[2];
if (!sql) {
    console.error('Por favor, proporciona una consulta SQL entre comillas.');
    process.exit(1);
}

query(sql);
