# 智慧农业系统

## 项目描述

利用传感器模拟和 IoT 技术，实时监测植物的生长状态，包括土壤PH、温度、湿度、水质等数据，并通过设备卡片和echart展示。

## 项目功能
 用户角色分为管理员两类，管理员负责修改其他人员的信息。
  农户：
- 实时监测植物的生长数据并在卡片上展示检测的时间以及评判后的结果。
- 支持多个传感器设备的数据采集。
- 设备卡片控制设备开启/关闭检测。
  管理员：
- 对其他人员的信息增删改查。
## 技术栈

- 前端：Vue.js，Element-Plus
- 后端：Spring Boot
- 数据库：MySQL

## 如何运行

### 1. 配置数据库 

mysql -u root -p
CREATE DATABASE agriculture;
### 运行sql文件导入数据库表结构
### 驼峰表内有外键关联，要先执行单词表再执行驼峰表 
 mysql -u username -p agriculture < crop.sql
 mysql -u username -p agriculture < user.sql
 mysql -u username -p agriculture < device.sql
 mysql -u username -p agriculture < usercrop.sql
 mysql -u username -p agriculture < userdevice.sql
 mysql -u username -p agriculture < sensor_data.sql
 ### 2.MySql账户替换
打开后端项目中的 src/main/resources/application.properties 文件，
修改 application.properties 配置文件：
your_username：替换为您的 MySQL 用户名。
your_password：替换为您的 MySQL 密码。

 ### 3. 启动后端服务
构建并启动后端应用：

确保已经安装了 Java 17 和 Maven，然后进入后端项目根目录，执行以下命令启动项目：
mvn install
### 4.启动前端

前端部分使用 Vue3 和 Element-Plus，请确保你已经安装了 Node.js 和 npm。在前端项目目录下执行以下命令启动前端：

安装依赖：
npm install
启动前端应用：
npm run serve
在浏览器中访问：http://localhost:8081 来查看前端页面。
