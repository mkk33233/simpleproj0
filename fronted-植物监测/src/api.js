// src/api.js
import axios from 'axios';

// 设置 API 的基础路径
const api = axios.create({
  baseURL: 'http://localhost:8081', // 替换为你的后端 API 地址
  timeout: 5000
});

// 设置拦截器等
api.interceptors.response.use(response => response, error => Promise.reject(error));

export default api;
