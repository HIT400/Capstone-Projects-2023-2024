import axios from "axios";

const api = axios.create({
    // baseURL:"https://app.smartacademy.co.zw/"
    baseURL:"http://localhost:8822/"
});

export default api;