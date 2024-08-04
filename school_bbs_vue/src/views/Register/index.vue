<template>
  <div class="register-body">
    <div class="register-container">
      <div class="head">
        <div class="name">
          <div class="title">注册</div>
          <div class="tips">XXX校园论坛</div>
        </div>
      </div>
      <el-form label-position="top" :rules="rules" :model="registerForm" ref="registerFormRef" class="register-form"
        label-width="80px">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model.trim="registerForm.username" autocomplete="off" maxlength="18"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model.trim="registerForm.password" autocomplete="off" maxlength="18"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPwd">
          <el-input type="password" v-model.trim="registerForm.confirmPwd" autocomplete="off" maxlength="18"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="tel">
          <el-input type="text" v-model.trim="registerForm.tel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input v-model.trim="registerForm.code" auto-complete="off" style="width: 55%;" />
          <!-- <el-button :loading="loading" style="position: relative;left: 10px;width: 42%;"
            @click="sendPhoneCodeHandle">获取验证码</el-button> -->
          <el-button :disabled="countdown < 60" style="position: relative; left: 10px; width: 42%;"
            @click="sendPhoneCodeHandle">
            {{ countdown < 60 ? `重新发送(${countdown}s)` : '获取验证码' }} </el-button>
        </el-form-item>
        <el-form-item>
          <router-link to="/login" style="text-decoration:none;color: #777;">已有账号？去登录</router-link>
          <el-button :loading="loading" style="position: relative;top: 15px;width: 100%;height: 40px;" type="primary"
            @click="handleRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reqRegister } from '@/api/user'
import { sendCode } from '@/api/thirdparty'
import { reactive, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage, FormInstance } from 'element-plus'
const router = useRouter()
const store = useStore()

// 是否【下次自动登录】
const checked = ref(false)
// 验证码图片地址
let codeUrl = ref()
// 定义变量控制按钮加载效果
let loading = ref(false);
// 注册对象
let registerForm = reactive({
  username: '',
  password: '',
  confirmPwd: '',
  tel: '',
  code: '',
  uuid: "",
})
let registerFormRef = ref<FormInstance>()
const countdown = ref(60); // 倒计时变量
let timer: NodeJS.Timeout; // 用于存储定时器

const startCountdown = () => {
  timer = setInterval(() => {
    countdown.value--;
    if (countdown.value <= 0) {
      clearInterval(timer);
      countdown.value = 60;
    }
  }, 1000);
};

const sendPhoneCodeHandle = () => {
  if (registerForm.tel !== null && registerForm.tel !== '') {
    startCountdown();
    sendCode(registerForm.tel).then(res => {
      registerForm.uuid = res.uuid;
    });
  }
};

const validateConfirmPwd = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请确认密码'))
  } else if (value !== registerForm.password) {
    callback(new Error("两次密码不一致"))
  } else {
    callback()
  }
}

// 校验规则对象
const rules = {
  nickname: [
    { required: true, message: '昵称不能为空', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '账号不能为空', trigger: 'blur' }
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" }
  ],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' }
  ],
  confirmPwd: [
    { required: true, validator: validateConfirmPwd, trigger: 'blur' }
  ],
  tel: [
    { required: true, message: '手机号不能为空', trigger: 'blur' }
  ],
}


// 注册
const handleRegister = () => {
  loading.value = true;
  registerFormRef.value.validate(valid => {
    if (valid) {//校验成功
      reqRegister({
        username: registerForm.username,
        tel: registerForm.tel,
        password: registerForm.password,
        code: registerForm.code,
        uuid: registerForm.uuid
      }).then(res => {
        if (res.code == 200) {
          router.push({ path: '/login' })
        } else {//注册失败
          ElMessage({
            message: res.msg,
            type: 'warning',
          })
        }
      }).catch(() => {
        loading.value = false;
      })
    } else {//校验失败
      loading.value = false;
    }
  })
  loading.value = false;
}

</script>

<style lang="scss" scoped>
.register-body {
  position: absolute;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: white;
  background-image: url("@/assets/5.jpg");
  background-size: 100% auto;
}

.register-container {
  width: 420px;
  height: 620px;
  background-color: #fcfcfc;
  border-radius: 20px;
  box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
}

.register-foot {
  position: relative;
  top: 10px;
}

.register-form {
  width: 70%;
  margin: 0 auto;
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

.el-input {
  margin-top: 0;
}

.el-form-item {
  margin-bottom: 12px;
}
</style>

<script lang="ts">
export default {
  name: "Login"
}
</script>