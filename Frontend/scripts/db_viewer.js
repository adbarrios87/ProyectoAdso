const mysql = require('mysql2/promise');

const config = {
    host: 'bx3fcwhyvrme9gt1y86m-mysql.services.clever-cloud.com',
    user: 'ui0zsy2f9b3mdipn',
    password: '9S2dPuwXIFQpRAzS03Sj',
    database: 'bx3fcwhyvrme9gt1y86m',
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
