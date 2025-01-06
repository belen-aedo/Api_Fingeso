// src/axios.js
import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // URL de tu backend
    headers: {
        'Content-Type': 'application/json',
    },
});

export default axiosInstance;
