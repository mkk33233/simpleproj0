import ElementPlus from 'element-plus'; // UI 库
import 'element-plus/dist/index.css'; // UI 样式
import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // 路由配置

const app = createApp(App);
app.use(ElementPlus);
app.use(router);  // 使用 Vue Router
   // 使用 ElementPlus UI 库
app.mount('#app');  // 挂载到 #app DOM 元素
