import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import mitt from 'mitt'
import Cookies from 'js-cookie'
import UUID from 'vue3-uuid'

import '@/assets/css/allstyle.scss'//引入公共样式
import '@/assets/css/iconfont.css'//阿里图库

import Element from 'element-plus'
import 'element-plus/dist/index.css'



const Mit = mitt()
const app = createApp(App)

// 类型声明
declare module 'vue' {
  export interface ComponentCustomProperties {
    $Bus: typeof Mit
  }
}


app.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})
app.use(store)
app.use(router)
app.use(UUID)
app.mount('#app')
app.config.globalProperties.$Bus = Mit
