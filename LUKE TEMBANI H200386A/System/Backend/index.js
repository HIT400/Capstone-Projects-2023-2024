require("dotenv").config();
const express = require("express");
const router = require("./Routes/routes");
const cors = require("cors");
const PORT = process.env.APP_PORT;
const app = express();
app.use(express.json());
app.use(cors());
app.use(express.urlencoded({extended:true}));
app.use("/", router);
app.listen(PORT,()=>{
    console.log(`Server up on ${PORT}`);
});

module.exports = app;