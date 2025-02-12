<template>
  <el-form :model="form" @submit.prevent="handleLogin">
    <el-form-item label="Username" :rules="[{ required: true, message: 'Please input username', trigger: 'blur' }]">
      <el-input v-model="form.username" placeholder="Enter your username" />
    </el-form-item>
    <el-form-item label="Password" :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]">
      <el-input v-model="form.password" type="password" placeholder="Enter your password" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" native-type="submit">Login</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import api from '../api'; // 引入 API 请求文件

export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    async handleLogin() {
      try {
        alert("response.data");    

        
        const response = await api.post('http://localhost:8081/api/users/login', {
          username: this.form.username,  // 使用 form.username 和 form.password
          password: this.form.password,
           
        });
        localStorage.setItem('token',response.data.token)
     alert( "===role sdsadadad"+"=======================================>"+"resoktlkkr"+response.data.token);  
     alert(localStorage.getItem("token")+"localtoken")
 
 
        if (response.data.role === 'admin') {
          console.log('Login successful as admin:', response.data);
          // 跳转到管理员页面
          this.$router.push({
            path: '/admin',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else if (response.data.role === 'farmer') {
          console.log('Login successful as farmer:', response.data);
          // 跳转到普通用户页面
          this.$router.push({
            path: '/DEVS',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else {
          this.$message.error('规则错误没有规则');
        }
      } catch (error) {
        // 错误处理
        console.error('Login failed:', error);
        this.$message.error('账号密码错误');
      }
    }
  }
};
</script>
