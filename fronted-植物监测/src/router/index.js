import { createRouter, createWebHistory } from 'vue-router';

const AdminVue = () => import('../components/AdminVue.vue'); // 管理员组件
const Dashboard = () => import('../components/DashboardVue.vue'); // 仪表盘组件
const devices = () => import('../components/DevicesVue.vue'); // 设备管理组件
const Login = () => import('../components/LoginVue.vue'); // 登录组件
const Userdevices = () => import('../components/UserDevicess.vue'); // 用户设备组件


const routes = [
  {
    path: '/aa',
    name: 'Login',
    component: Login  // 映射到 Login 组件
  },
  {
    path: '/',  //自定义访问到这个组件需要的路径
    name: 'DevicesVue',
    component: devices  // 映射到 Login 组件
  },




  // {
  //   path: '/qqw',  //自定义访问到这个组件需要的路径
  //   name: 'qqw',
  //   component: qqw  // 映射到 Login 组件
  // },





  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard  // 映射到 Dashboard 组件
  },
  {
    path: '/userdevices',
    name: 'userdevices',
    component:Userdevices
  },
  {
    path: '/admin',
    name: 'Admin',
    component: AdminVue
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
