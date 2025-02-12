<template>
  <div id="app">
    <router-view /> <!-- 在这里渲染路由匹配的组件 -->
  </div>
</template>

<script>
// 不需要再引入 HelloWorld 组件了，因为它会通过路由来动态加载
export default {
  name: 'App'
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
