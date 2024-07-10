const axios = require("axios");

const api = axios.create({
    baseURL:"http://192.168.226.126:5030/"  //rasp server
});

module.exports = api;