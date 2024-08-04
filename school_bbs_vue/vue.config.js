const IconsResolver = require('unplugin-icons/resolver')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
const { defineConfig } = require('@vue/cli-service')
const Icons = require('unplugin-icons/webpack')

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    // 项目运行时候的端口号
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:9880',
        changeOrigin: true,
        secure: true,//是否https接口
        pathRewrite: {
          ['^' + "/api"]: ''
        },
      },
    },
    client: {
      overlay: false
    }
  },
  configureWebpack: {
    plugins: [
      AutoImport({
        resolvers: [
          // 自动导入图标组件
          IconsResolver({
            prefix: 'Icon',
          }),
          ElementPlusResolver()
        ]
      }),
      Components({
        resolvers: [
          // 自动注册图标组件
          IconsResolver({
            enabledCollections: ['ep'],
          }),
          ElementPlusResolver()
        ]
      }),
      Icons({
        autoInstall: true,
      }),
    ],
  }
})
