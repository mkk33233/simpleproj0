<template>





  <!-- 当前用户 Info Section -->
  <el-row :gutter="20" justify="center">
    <el-col :span="24" style="text-align: center;">
      <h2>Welcome, {{ username }}!</h2>
      <el-button @click="changePassword"    type="primary">Change Info</el-button>
      <el-button @click="logout" type="danger">Logout</el-button>
    </el-col>
  </el-row>
<br>
 

  
  <el-row :gutter="20" justify="center">
    <!-- Center the row content -->
    <el-col :span="24" style="text-align: center;">
      <el-button type="primary" @click="createUser">Add Farmer</el-button>
    </el-col>
<br><br>
    <!-- 模糊查找 -->
    <el-col :span="24" style="text-align: center;">
      <el-input
        v-model="searchQuery"
        placeholder="Search by username"
        @input="fetchFarmers"
        style="width: 300px; margin-bottom: 20px;"
      />
    </el-col>

    <!-- 显示农民用户列表 -->
    <el-col :span="24">
      <el-table :data="farmers" style="width: 100%" border>
        <el-table-column label="Username" prop="username" min-width="180"></el-table-column>
        <el-table-column label="Role" prop="role" min-width="180"></el-table-column>
        <el-table-column label="Password" prop="password" min-width="180"></el-table-column>
        <el-table-column label="Actions" width="200">
          <template #default="{ row }">
            <el-button @click="editUser(row)" type="warning">Edit</el-button>
            <el-button @click="deleteUser(row)" type="danger">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>









 <!-- Create/Edit User Dialog -->
 <el-dialog v-model="dialogVisible" :title="userForm.id ? 'Edit user' : 'Add user'">
    <el-form :model="userForm" label-width="100px">
      <!-- Username Field -->
      <el-form-item label="Username">
        <el-input
          v-model="userForm.username"
          placeholder="Enter username"
          @input="validateUsername"
         
          @focus="resetUsernameError" 
        />
        <span v-if="usernameError" style="color: red; font-size: 12px;">
          Username already exists.
        </span>
      </el-form-item>

      <!-- Role Field -->
      <el-form-item label="Role">
        <el-select v-model="userForm.role" placeholder="Select role">
          <el-option label="Farmer" value="farmer"></el-option>
          <el-option label="Admin" value="admin"></el-option>
        </el-select>
      </el-form-item>

      <!-- Password Field with Blue Hint -->
      <el-form-item label="Password">
        <el-input
          v-model="userForm.password"
          placeholder="Enter password"
          type="password"
          @input="validatePassword"
          @focus="resetPasswordError"
        />
        <!-- Show blue hint if password is empty or invalid -->
        <span v-if="passwordError" style="color: blue; font-size: 12px;">
          Password must contain at least 8 characters, including letters and numbers.
        </span>
      </el-form-item>
    </el-form>

    <!-- Dialog Footer -->
    <template #footer>
      <el-button @click="dialogVisible = false">Cancel</el-button>
      <!-- Disable Save button if password is invalid or username exists -->
      <el-button :disabled="userForm.password === '' ||userForm.username==='' || usernameError||passwordError " type="primary" @click="saveUser">Save</el-button>
    </template>
  </el-dialog>
 

  <!-- Change Password Dialog -->
  <el-dialog v-model="changePasswordVisible" title="Change Info">
    <el-form :model="passwordForm" label-width="100px">
      <el-form-item label="Username">
      

        <el-input
          v-model="passwordForm.username"
          placeholder="Enter new username"
          @input="validateCCUsername"  
         
          @focus="resetCCUsernameError" 
        />
        <span v-if="CCusernameError" style="color: red; font-size: 12px;">
          Username already exists.
        </span>



      </el-form-item>

      <el-form-item label="Password">
        <el-input
          v-model="passwordForm.password"
          placeholder="Enter new password"
          type="password"
          @input="validatePasswordChange"
          @focus="resetPasswordChangeError"
        />
        <span v-if="passwordChangeError" style="color: blue; font-size: 12px;">
          Password must contain at least 8 characters, including letters and numbers.
        </span>
      </el-form-item>

      <el-form-item label="CCPassword">
        <el-input
          v-model="passwordForm.confirmPassword"
          placeholder="Confirm new password"
          type="password"
            @input="validateCCPasswordChange"
            @focus="resetCCPasswordChangeError"
        />
         <span v-if="CCpasswordChangeError" style="color: blue; font-size: 12px;">
          两次密码应该保持一致.
        </span>
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="changePasswordVisible = false">Cancel</el-button>
      <el-button :disabled="passwordForm.password !== passwordForm.confirmPassword ||CCusernameError|| passwordChangeError||passwordForm.password === '' " type="primary" @click="updatePassword">Save</el-button>
    </template>
  </el-dialog>
</template>

<script>
import api from '../api'; // 引入 API 请求文件

export default {
  data() {
    return {
      username: '', // 初始化 username
      password: '',
      farmers: [], // 存储农民用户列表
      userForm: {
        id: '',
        username: '',
        password: '',
        role: 'farmer', // 默认角色为 farmer
      },
      dialogVisible: false, // 控制 dialog 显示与隐藏
      searchQuery: '', // 存储搜索关键字
       
      //currentUser: {}, // 存储当前登录用户信息
         // Flag to track if username exists
     
     
         //增加  、编辑表单提示
         passwordError: false,  // Flag to track if password is valid
         usernameError: false, // Flag to track if password is valid
      
      
      CCusernameError: false,//当前用户提示
      passwordChangeError: false, // 密码提示Flag to track if new password is valid
      CCpasswordChangeError:false,//确认密码错提示
   
      passwordForm: {
        username: '',
        password: '',
        confirmPassword: '',
        id: this.id,
        role:this.role
      },
      changePasswordVisible: false, // 控制更改密码对话框显示与隐藏
    };
  },

  mounted() {
    const username = this.$route.query.username || ''; // 获取传递的用户名
    this.username = username; // 更新组件中的用户名
    this.fetchFarmers();
    
  },
  

  methods: {
    
   

    // 验证用户名是否已存在
    async validateUsername() {
      if (!this.userForm.username) return;
      try {
        const response = await api.get(
          `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users`,
          { params: { username: this.userForm.username } }
        );
        const users = response.data;
         if      (users.some(user => user.username === this.userForm.username && user.id !== this.userForm.id))
            {this.usernameError = true; }else{this.usernameError = false;}  
      } catch (error) {
        this.$message.error('Error checking username');
      }
    },
    async  validateCCUsername() {
      if (!this.passwordForm.username) return;
      try {
        
        const response = await api.get(
          `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users`,
          { params: { username: this.passwordForm.username } }
        );
        
            
         
        const users = response.data;
       
        if (users.some(user => user.username === this.passwordForm.username && user.id !== this.passwordForm.id))
      { this.CCusernameError = true; }else{this.CCusernameError = false;}
      } catch (error) {
        this.$message.error('Error checking username');
      }   
      
    },

    // Reset the username error when user focuses on input
    resetUsernameError() {
      this.usernameError = false;
    },
    resetCCUsernameError() {
      this.CCusernameError = false;
    },
    // 获取农民用户列表，支持模糊查找
  async fetchFarmers() {
    try {
      const response = await api.get(
        `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users`,
        {
          params: {
            role: 'farmer',
            search: this.searchQuery, // 模糊查询字段，仅支持用户名
          },
        }
      );
      
      // 过滤掉当前登录用户
      this.farmers = response.data.filter(user => user.username !== this.username);

    } catch (error) {
      this.$message.error('Failed to fetch farmers');
    }
  },

    // 创建用户
    createUser() { 
  
      this.userForm = { id: '', username: '', password: '', role: 'farmer' }; // 重置表单数据

      this.passwordError=false;
      this.usernameError=false;
      
      this.dialogVisible = true; // 打开对话框
       
    },

    // 编辑用户
    editUser(row) {
   
      this.passwordError=false;
      this.usernameError=false;
      this.userForm = { ...row }; // 填充表单数据
      this.userForm.password = row.password; // 保留原密码，以便修改时使用
      this.dialogVisible = true; // 打开对话框
      
    },

    // 保存用户信息（新建或编辑）
    async saveUser() {
     
      try {
        if (this.userForm.id) {
          await api.put(
            `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users/${this.userForm.id}`,
            this.userForm
          );
        } else {
          await api.post(
            `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users`,
            this.userForm
          );
        }
        this.dialogVisible = false;
        this.fetchFarmers();
      } catch (error) {
        this.$message.error('Failed to save user');
      }
    },

   
   
   
   
    async changePassword() { 
       this.CCusernameError = false;
      this.passwordChangeError = false;
      this.CCpasswordChangeError = false;
  try {
    // 发送请求，获取当前用户名对应的用户数据
    const response = await api.get(
      `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users/${this.username}`
    );
    
    // 确保获取到的用户数据中有 id 字段
    if (response.data && response.data.id) {
      // 设置 passwordForm 的 id 为返回的用户 id
      this.passwordForm = {
        username: this.username,
        password: response.data.password,
        confirmPassword: response.data.password,
        id: response.data.id,  // 设置 id
        role:response.data.role   // 设置角色
      };
      
      // 设置密码修改弹窗可见
      this.changePasswordVisible = true;
    } else {
      // 处理没有找到用户的情况
      this.$message.error('User not found');
    }
  } catch (error) {
    // 错误处理
    this.$message.error('Error fetching user data');
    console.error(error);
  }
},











    // 密码验证
    validatePassword() {
      const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        this.passwordError = !regex.test(this.userForm.password);
         
    },



    // Reset the password error message
    resetPasswordError() {
      this.passwordError = false;
    },
 

    // Password Change validation
    validatePasswordChange() {
      const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      this.passwordChangeError = !regex.test(this.passwordForm.password);
      if(this.passwordForm.password==this.passwordForm.confirmPassword){this.CCpasswordChangeError=false;}
    },
   // CCPassword Change validation
   validateCCPasswordChange() {
    this.CCpasswordChangeError = this.passwordForm.confirmPassword !== this.passwordForm.password;
}
,
    
     
     // Reset password change error
     resetPasswordChangeError() {
      this.passwordChangeError = false;
    },
    resetCCPasswordChangeError() {
      this.CCpasswordChangeError = false;
    },

// 删除用户
async deleteUser(user) {
  try {
    await api.delete(
      `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users/${user.id}`
    );
    // After successful deletion, refresh the farmers list
    this.fetchFarmers();
    this.$message.success('User deleted successfully');
  } catch (error) {
    this.$message.error('Failed to delete user');
  }
}
,













    // Save the password change
    async updatePassword() {

      //先更新url 
      this.$router.replace({
        path: this.$route.path,  // 保持当前路径不变
                 query: { username: this.passwordForm.username,   password: this.passwordForm.password  }
               });

      try {this.username = this.passwordForm.username; // 更新当前用户名 
        // 执行密码更新操作
        await api.put(
          `${process.env.VUE_APP_API_URL || 'http://localhost:8081'}/api/users/${this.passwordForm.id}/change-password`,
          { password: this.passwordForm.password,
            role:this.passwordForm.role,
            username:this.passwordForm.username
           }
        );
         
        this.changePasswordVisible = false;
        this.$message.success('Password changed successfully');
      } catch (error) {
        this.$message.error('Failed to change password');
      }
    },

    // 用户退出
    logout() {
      this.$router.push('/');
    }
  },
};
</script>
