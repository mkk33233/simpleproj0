<template>
  <el-form :model="form" @submit.prevent="handleLogin">
    <el-form-item label="Username" :rules="[{ required: true, message: 'Please input username', trigger: 'blur' }]">
      <el-input v-model="form.username" placeholder="Enter your username" />
    </el-form-item>
    <el-form-item label="Password" :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]">
      <el-input v-model="form.password" type="password" placeholder="Enter your password" />
    </el-form-item>
     


    <el-form-item
  v-show="qq !== false"   
  label="Role"
  :rules="[{ required: true, message: '请输入令牌（正确则会注册）', trigger: 'blur' }]"
>
  <el-input v-model="mkk" placeholder="Enter your role" />
</el-form-item>

    <el-form-item>
      <el-button type="primary" v-show="qq !== false"   @click="k()">  返回登录</el-button>
    </el-form-item>




    <el-form-item  v-show="zc == false">
      <el-button type="primary" native-type="submit">reg</el-button>
    </el-form-item>
    <el-form-item  v-show="zc" v-model="zc">
      <el-button type="primary" native-type="submit">Login</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import api from '../api'; // 引入 API 请求文件

export default {
  data() {
    return {zc:true,
      mkk:"",
      qq:false,
      form: {
        username: '',
        password: '',
        role:''
      }
    };
  },
  methods: {

    k(){
        this.qq=false
      this.zc=true;
    },
    async handleLogin() {
      try {
        if(!(this.qq))
    {
        const response = await api.post('http://localhost:8081/api/users/login', {
          username: this.form.username,  // 使用 form.username 和 form.password
          password: this.form.password,
         });
        
   
         
       
       
 
 
        if (response.data.role === 'admin') {
          console.log('Login successful as adminss:', response.data);
          // 跳转到管理员页面
          this.$router.push({
            path: '/admin',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else if (response.data.role === 'farmer') {
          console.log('Login successful as farmer:', response.data);
          // 跳转到普通用户页面
          this.$router.push({
            path: '/userdevices',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else {
          this.$message.error('规则错误没有规则');
       
       
        }
  
      
      
      
      }
else if (this.qq){ 
          if(this.mkk!=="mkk"){alert("令牌错误，  注册失败");return;}
        api.post('http://localhost:8081/api/users/reg', {
          username: this.form.username,  // 使用 form.username 和 form.password
          password: this.form.password,
          });  alert("注册成功即将登陆"); 
          const response = await api.post('http://localhost:8081/api/users/login', {
          username: this.form.username,  // 使用 form.username 和 form.password
          password: this.form.password,
         });
        
   
         
       
       
 
 
        if (response.data.role === 'admin') {
          console.log('Login successful as adminss:', response.data);
          // 跳转到管理员页面
          this.$router.push({
            path: '/admin',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else if (response.data.role === 'farmer') {
          console.log('Login successful as farmer:', response.data);
          // 跳转到普通用户页面
          this.$router.push({
            path: '/userdevices',
            query: { username: this.form.username, password: this.form.password } // 传递用户名和密码
          });
        } else {
          this.$message.error('规则错误没有规则');
       
       
        }
        
        
        }



          

  
}


       catch (error) {   
         if  (!this.qq)
           {alert("用户不存在请注册"); { this.qq=true;this.zc=false} 
 
        // 错误处理
        console.error('Login failed:', error);
        this.$message.error('账号密码错误');
      }
         else if(this.qq=="mkk"){alert("令牌错误注册失败")}


    }
 
 
 
 
 
 
  },

},
}
</script>
