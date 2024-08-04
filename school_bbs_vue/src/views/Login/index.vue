<template>
  <div class="login-body">
    <div class="login-container">
      <div class="head">
        <div class="name">
          <div class="title">登录</div>
          <div class="tips">{{ schoolConf.name }}</div>
        </div>
      </div>
      <el-form label-position="top" :rules="rules" :model="loginForm" class="login-form" ref="loginFormRef">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model.trim="loginForm.username" autocomplete="off" @keyup.enter="handleLogin"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model.trim="loginForm.password" autocomplete="off" @keyup.enter="handleLogin"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input v-model.trim="loginForm.code" auto-complete="off" style="width: 63%" @keyup.enter="handleLogin">
            <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
          </el-input>
          <div class="login-code" style="position: relative;left: 10px;top: 4px;" @click="getCodeImg">
            <img :src="codeUrl" class="login-code-img" style="width: 100px" />
          </div>
        </el-form-item>
        <el-form-item style="height: 15px">
          <el-text type="info" style="cursor: pointer;" @click="goRegister">还没有账号？去注册...</el-text>
        </el-form-item>
        <el-form-item style="height: 18px">
          <el-text type="info" style="cursor: pointer;" @click="goLogin">游客身份进入...</el-text>
        </el-form-item>
        <el-form-item style="height: 30px">
          <el-button :loading="loading" style="width: 100%;height: 40px;" type="primary"
            @click="handleLogin">立即登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reqCodeImg } from '@/api/user'
import { reactive, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage, ElNotification } from 'element-plus'
import schoolConf from '@/config/school'

const $router = useRouter()
const $store = useStore()

// 验证码图片地址
let codeUrl = ref()
// 定义变量控制按钮加载效果
let loading = ref(false);
// 登录数据
let loginForm = reactive({
  username: '',
  password: '',
  code: '',
  uuid: ''
})
// 是否【下次自动登录】
let loginFormRef = ref()

// 校验规则对象
const rules = {
  username: [
    { required: true, message: '账户不能为空', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' }
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" }
  ]
}

const goRegister = () => {
  $router.push({ path: '/register' })
}

const goLogin = () => {
  $router.push({ path: '/' })
}

// 获取验证码请求
const getCodeImg = () => {
  reqCodeImg().then(res => {
    codeUrl.value = "data:image/gif;base64," + res.img;
    loginForm.uuid = res.uuid;
  });
}

// 登录
const handleLogin = async () => {
  // 等待校验
  await loginFormRef.value.validate();
  // 加载效果
  loading.value = true;
  // 执行登录操作
  try {
    await $store.dispatch("Login", loginForm);
    $router.push('/')
    ElNotification({
      type: 'success',
      message: '欢迎回来',
    });
  } catch (err) {
    ElNotification({
      type: 'error',
      message: err.msg,
    });
  }
  loading.value = false;
}

// created执行
getCodeImg()
</script>

<style lang="scss" scoped>
.login-body {
  background-image: url("@/assets/5.jpg");
  background-size: 100% auto;
  height: 100%;
  width: 100%;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-container {
  width: 420px;
  height: 530px;
  background-color: #fcfcfc;
  border-radius: 20px;
  box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
}

.head {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 0 20px 0;
}

.head img {
  width: 100px;
  height: 100px;
  margin-right: 20px;
}

.head .name {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.head .title {
  font-size: 28px;
  color: #409EFF;
  font-weight: bold;
}

.head .tips {
  font-size: 12px;
  color: #999;
}

.login-form {
  width: 70%;
  margin: 0 auto;
}
</style>

<script lang="ts">
export default {
  name: "Login"
}
</script>
