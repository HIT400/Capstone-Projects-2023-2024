const mysql = require("mysql2");
require("dotenv").config();


let pool = mysql.createPool({
    host:process.env.DATABASE_HOST,
    user:process.env.DATABASE_USER,
    password:process.env.DATABASE_PASSWORD,
    queueLimit:process.env.DATABASE_LIMIT,
    database:process.env.DATABASE_NAME
});


module.exports = pool;