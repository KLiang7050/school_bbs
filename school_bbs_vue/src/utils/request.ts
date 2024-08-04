import axios from "axios"
//引入进度条插件
import nProgress from "nprogress"
//引入样式
import "nprogress/nprogress.css"
import tokenUtils from "./tokenUtils"
import { ElNotification,ElMessageBox } from 'element-plus';

declare module 'axios' {
    interface AxiosInstance {
        (config: AxiosRequestConfig): Promise<any>
    }
}

//创建axois实例
const request = axios.create({
    baseURL: "/api",
    timeout: 10000,
    withCredentials:false //跨域时不发送cookie资源
})
//请求拦截器
request.interceptors.request.use((config) => {
    //进度条开始
    nProgress.start()
    // 是否需要token
    let isToken = false;
    if (config.headers != null) {
        isToken = config.headers.isToken === false
    }
    if (tokenUtils.get() && !isToken) {
        config.headers['Authorization'] = tokenUtils.get() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    //获取token
    let token = localStorage.getItem("TOKEN");
    if (token) {
        config.headers.accessToken = token;
    }
    return config
}, error => {
    console.log(error)
    Promise.reject(error)
})
//响应拦截器
request.interceptors.response.use((res) => {
    nProgress.done()
    return res.data
}, (err) => {
    const res = err.response
    const code = res.data.code
    const msg = res.data.msg
    if(code === 401){
        tokenUtils.del()
        ElMessageBox.alert('登录状态已过期或未登录，请前去登录', '提示', {
            confirmButtonText: '去登陆',
            type: 'info'
          }).finally(() => {
            window.location.href = "/login"
          });
    }else if(code === 500){
        ElNotification({
            type: 'error',
            message: '系统异常，请联系管理员'
        })
    }
    return Promise.reject(err)
})
export default request