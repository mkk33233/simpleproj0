const webpack = require('webpack');

module.exports = {
  configureWebpack: {
    // 使用 define 配置项显式定义特性标志
    plugins: [
      new webpack.DefinePlugin({
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: false, // 或者设置为 true，取决于你是否需要启用水合不匹配的详细信息
      }),
    ],
    resolve: {
      fallback: {
        // 遇到 net 模块的问题时，告诉 Webpack 忽略该模块
        net: false,
        tls: false,
        fs: false,
      }
    }
  },
  devServer: {
    proxy: {
      '/users': {
        target: 'http://localhost:8081',  // 后端 API 的实际地址
        changeOrigin: true,
        pathRewrite: {
          '^/users': ''  // 去掉 /users 前缀
        }
      }
    }
  }
};
