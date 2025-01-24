import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // Verifique se a URL está correta
  withCredentials: true, // Habilitar cookies/sessões, se necessário
});

export default api;