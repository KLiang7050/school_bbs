<template>
    <div style="padding: 0 20px;">
        <el-divider content-position="left" class="divider">
            <el-text class="mx-1" type="info" size="large">个人信息</el-text>
        </el-divider>
        <div style="width: 100%;">
            <el-form inline="true">
                <el-form-item label="真实姓名" label-width="80px" class="form-intem">
                    <el-text class="mx-1" type="info">{{ info.realName ? info.realName : '需学籍认证' }}</el-text>
                </el-form-item>
                <el-form-item label="我的学校" label-width="80px" class="form-intem">
                    <el-text class="mx-1" type="info">{{ info.school ? info.school : '需学籍认证' }}</el-text>
                </el-form-item>
                <!-- <el-form-item label="我的学院" label-width="80px" class="form-intem">
                    <el-text class="mx-1" type="info">{{ info.college }}</el-text>
                </el-form-item> -->
                <el-form-item label="我的昵称" label-width="80px" class="form-intem">
                    <el-input ref="nickNameRef" v-show="isChangeNickName" style="width: 160px" maxlength="12"
                        v-model="info.nickName" />
                    <el-text class="mx-1" type="info" v-show="!isChangeNickName">
                        {{ info.nickName ? info.nickName : '未设置' }}
                    </el-text>
                    <a @click="handleNickName" class="changeInfo changeButton" v-show="!isChangeNickName">[修改昵称]</a>
                    <el-button class="changeButton" @click="isChangeNickName = false"
                        v-show="isChangeNickName">取消</el-button>
                    <el-button class="changeButton" v-show="isChangeNickName" type="primary"
                        @click="submitChange('nickName')">提交</el-button>
                </el-form-item>
                <el-form-item label="我的性别" label-width="80px" class="form-intem">
                    <el-radio-group v-model="info.sex" class="ml-4" @change="submitChange('sex')">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="2">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="我的生日" label-width="80px" class="form-intem">
                    <el-date-picker v-model="info.birthday" @change="submitChange('birthday')"
                        value-format="YYYY-MM-DD" placeholder="未设置"/>
                </el-form-item>
                <el-form-item label="个人简介" label-width="80px" class="form-intem">
                    <el-text class="mx-1" type="info" v-show="!isChangeIntroduce">
                        {{ info.introduce ? info.introduce : '无' }}
                    </el-text>
                    <a @click="handleIntroduce" class="changeInfo changeButton" v-show="!isChangeIntroduce">[修改简介]</a>
                    <el-input ref="introduceRef" type="textarea" v-model="info.introduce"
                        @input="isChangeIntroduce = true" style="width: 190px" v-show="isChangeIntroduce" maxlength="50"></el-input>
                    <el-button class="changeButton" @click="isChangeIntroduce = false"
                        v-show="isChangeIntroduce">取消</el-button>
                    <el-button class="changeButton" v-show="isChangeIntroduce" type="primary"
                        @click="submitChange('introduce')">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
        <el-divider content-position="left" class="divider" style="margin-top: 25px;">
            <el-text class="mx-1" type="info" size="large">账号信息</el-text>
        </el-divider>
        <div style="width: 100vh;display: flex;">
            <el-form style="width: 55%;">
                <el-form-item label="绑定手机" label-width="80px">
                    <el-text type="info">{{ maskedTel }}</el-text>
                    <a @click="handleShowChange('tel')" class="changeInfo changeButton"
                        v-show="!isChangeTel">[改绑手机号]</a>
                </el-form-item>
                <el-form-item label="设置密码" label-width="80px">
                    <span style="color: green;">
                        已设置
                    </span>
                    <a @click="handleShowChange('pwd')" class="changeInfo changeButton" v-show="!isChangePWD">[修改密码]</a>
                </el-form-item>
                <el-form-item label="学籍认证" label-width="80px">
                    <el-input v-if="isChangeSchoolRegister" style="width: 180px" maxlength="16" v-model="schoolCode" />
                    <span v-show="info.school && !isChangeSchoolRegister" style="color: green;" v-else>
                        已认证
                    </span>
                    <span v-show="!info.school && !isChangeSchoolRegister" style="color: red;">未认证</span>
                    <a @click="isChangeSchoolRegister = !isChangeSchoolRegister" class="changeInfo changeButton"
                        v-show="!isChangeSchoolRegister">[修改学籍]</a>&nbsp;
                    <el-text v-show="isChangeSchoolRegister" type="warning">请输入学信网在线验证码</el-text>
                </el-form-item>
                <el-form-item label-width="80px" v-show="isChangeSchoolRegister">
                    <el-button class="changeButton" type="info" @click="isChangeSchoolRegister = false">取消</el-button>
                    <el-button class="changeButton" type="primary"
                        @click="submitChange('schoolRegister')">提交</el-button>
                </el-form-item>
            </el-form>
            <el-divider direction="vertical" style="height: 180px;margin: 0 25px;"
                v-show="isChangeTel || isChangePWD" />
            <div style="width: 45%;">
                <!-- 修改手机号form -->
                <el-form label-position="left" :rules="rulesTel" :model="form" ref="formTelRef" status-icon
                    class="demo-ruleForm" label-width="80px" v-show="isChangeTel">
                    <el-form-item label="旧手机号" prop="old">
                        <el-input type="text" v-model.trim="form.old" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="新手机号" prop="new">
                        <el-input type="text" v-model.trim="form.new" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="验证码" prop="code">
                        <el-input type="text" style="width: 80px" v-model.trim="form.code" autocomplete="off" />
                        <el-button class="changeButton" type="primary" @click="sendPhoneCodeHandle">获取验证码</el-button>
                    </el-form-item>
                    <el-form-item style="text-align: center;">
                        <el-button class="changeButton" type="info" @click="isChangeTel = false">取消</el-button>
                        <el-button class="changeButton" type="primary" @click="submitChange('tel')">提交</el-button>
                    </el-form-item>
                </el-form>
                <!-- 修改密码form -->
                <el-form label-position="left" :rules="rulesPWD" :model="form" ref="formPWDRef" status-icon
                    class="demo-ruleForm" label-width="80px" v-show="isChangePWD">
                    <el-form-item label="旧密码" prop="old">
                        <el-input type="text" v-model.trim="form.old" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new">
                        <el-input type="password" v-model.trim="form.new" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="code">
                        <el-input type="text" style="width: 80px" v-model.trim="form.code" autocomplete="off" />
                        <el-button class="changeButton" type="primary" @click="sendPhoneCodeHandle">获取验证码</el-button>
                        <el-text>验证码将发送到{{ maskedTel }}</el-text>
                    </el-form-item>
                    <el-form-item style="text-align: center;">
                        <el-button class="changeButton" type="info" @click="isChangePWD = false">取消</el-button>
                        <el-button class="changeButton" type="primary" @click="submitChange('pwd')">提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { sendCode } from '@/api/thirdparty'
import { studentVerification, reqUserInfoById } from '@/api/user'
import { ref, reactive, getCurrentInstance, nextTick, inject, computed } from "vue";
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import { ElNotification, FormInstance } from 'element-plus';
const $store = useStore()
const $router = useRouter()
const $route = useRoute()
const { proxy } = getCurrentInstance();
const reload = inject('reload')
// 是否修改
let isChangeNickName = ref<boolean>(false);
let isChangePWD = ref<boolean>(false);
let isChangeSchoolRegister = ref<boolean>(false);
let isChangeTel = ref<boolean>(false);
let isChangeIntroduce = ref<boolean>(false);
let isChangeSex = ref<boolean>(false);
// 用户信息handleNickName
const info = computed(() => $store.state.user.user)
const form = reactive({
    new: '',
    old: '',
    code: '',
    uuid: '',
})
const schoolCode = ref<Number>()
const formTelRef = ref<FormInstance>()
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


// 自定义校验规则
const validateTel = (rule, value, callback) => {
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    if (value === null || value === '' || value === undefined) {
        callback(new Error('手机号不能为空'));
    } else if (reg.test(value)) {
        callback();
    } else {
        callback(new Error('请输入正确的手机号'));
    }
};

// 校验规则对象——手机号
const rulesTel = reactive({
    code: [
        { required: true, message: "请输入验证码", trigger: "blur" }
    ],
    old: [
        { required: true, validator: validateTel, trigger: 'blur' }
    ],
    new: [
        { required: true, validator: validateTel, trigger: 'blur' }
    ],
})

// 校验规则对象——密码
const rulesPWD = reactive({
    code: [
        { required: true, message: "请输入验证码", trigger: "blur" }
    ],
    old: [
        { required: true, message: "请输入旧密码", trigger: "blur" }
    ],
    new: [
        { required: true, message: "请输入新密码", trigger: 'blur' }
    ],
})

// 提交修改
const submitChange = async (type: string) => {
    try {
        if (type === "schoolRegister") {
            await studentVerification(schoolCode.value)
        } else if (type === "tel") {
            await $store.dispatch("ChangeTel", form)
        } else if (type === "pwd") {
            await $store.dispatch("ChangePWD", form)
        } else {
            if(type==='nickName'){
                if(info.value.nickName === '' || info.value.nickName === undefined || info.value.nickName === null){
                    info.value.nickName = info.value.username
                    ElNotification({
                        type: 'warning',
                        message: '昵称不能为空',
                    });
                }
            }
            await $store.dispatch("Update", info.value)
        }
        ElNotification({
            type: 'success',
            message: '修改成功',
        });
        reload($router.currentRoute.value.path)
        isChangeNickName.value = false
        isChangeIntroduce.value = false
        isChangeSex.value = false
        isChangeIntroduce.value = false
        isChangeTel.value = false
        isChangeSchoolRegister.value = false
    } catch (err) {
        ElNotification({
            type: 'error',
            message: err.msg,
        });
    }
};

const handleNickName = () => {
    isChangeNickName.value = !isChangeNickName.value
    nextTick(() => {
        proxy.$refs["nickNameRef"].focus()
    })
}

const handleIntroduce = () => {
    isChangeIntroduce.value = !isChangeIntroduce.value
    nextTick(() => {
        proxy.$refs["introduceRef"].focus()
    })
}

const handleShowChange = (type) => {
    isChangeTel.value = false
    isChangePWD.value = false
    if (type === "tel") {
        isChangeTel.value = true
    } else if (type === "pwd") {
        isChangePWD.value = true
    } else {
    }
}

// 获取验证码请求
const sendPhoneCodeHandle = () => {
    startCountdown()
    sendCode(info.value.tel).then(res => {
        form.uuid = res.uuid;
    });
}

const maskedTel = computed(() => {
  if (!info.value.tel) return '';

  const maskedNumber = info.value.tel.replace(/(\d{3})(\d{4})(\d{4})/, '$1****$3');
  return maskedNumber;
});

</script>

<style lang="scss" scoped>
.divider {
    margin: 25px 0;
}

.form-intem {
    width: 40%;
}

.changeButton {
    margin: 0 1vh;
}

.changeInfo {
    color: #909399;
    user-select: none;
}

.changeInfo:hover {
    color: #409eff;
    cursor: pointer;
}
</style>

<script lang="ts">
export default {
    name: "Info"
}
</script>