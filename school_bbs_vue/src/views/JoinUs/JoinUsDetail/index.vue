<template>
    <div>
        <div style="display: flex;justify-content: space-between;align-items: center;">
            <el-text class="mx-1" size="large" style="font-size: 25px;">- 入驻详情 -</el-text>
            <div>
                <el-button type="info" @click="goBack">返回</el-button>
                <el-button type="primary" @click="handleCommit(1)">提交</el-button>
            </div>
        </div>
    </div>
    <el-divider />
    <div style="margin: 2% 0">
        <el-steps align-center :active="active">
            <el-step title="申请" />
            <el-step title="已发送" />
        </el-steps>
    </div>
    <div style="width: 96%;">
        <el-form ref="formRef" :model="formData" :inline="true" class="demo-form-inline"
            label-position="right" label-width="120px" :rules="rules">
            <el-form-item label="申请学校" prop="school">
                <el-input v-model="formData.school"></el-input>
            </el-form-item>
            <el-form-item label="申请人账号">
                <el-text type="info">{{ user.username }}</el-text>
            </el-form-item>
            <el-form-item label="申请明细" prop="description" style="width: 70%">
                <el-input v-model="formData.description" :rows="8" type="textarea" />
            </el-form-item>
            <el-form-item label="上传附件" style="width: 100%">
                <el-upload class="upload-demo" :multiple="true" :before-upload="handleBeforeUpload"
                    :http-request="handleUpload" limit="5" :file-list="fileList">
                    <el-button type="primary">点击上传</el-button>
                    <template #tip>
                        <div class="el-upload__tip">
                            请上传相关资质证明，要求单个文件小于20MB
                        </div>
                    </template>
                </el-upload>
            </el-form-item>
        </el-form>

        <div v-if="isShow" style="display: flex; justify-content: center; align-items: center;">
            <el-card style="margin-left: 35px;width: 480px;margin-top: 3%;text-align: center;" shadow="always">
                <p>已受理您的申请，3个工作日内，相关负责人将会联系您。</p>
                <p>您也可以拨打15888888888联系我们</p>
            </el-card>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { computed, reactive, ref, toRef, inject } from 'vue'
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage, ElNotification, UploadUserFile, ElMessageBox } from 'element-plus'

import { saveJoinus, getJoin, uploadFile, commitJoinus } from '@/api/user'

import TimeUtils from '@/utils/dateUtils'
import { getUUID } from '@/utils/uuidUtils'
import { htmlToString } from '@/utils/StringUtils'

import ossURL from '@/config/oss'
const reload = inject("reload")
const $router = useRouter()
const $route = useRoute()
const $store = useStore()

const workflows = ref([
    {
        status: '申请',
        description: "提交申请"
    },
    {
        status: '已发送',
        description: "负责人审核"
    }
])

const user = computed(() => $store.getters.info)

// 创建一个formRef引用以便进行表单验证
const formRef = ref();

const formData = ref({
    id: '',
    school: '',
    description: '',
    fileUrl: '',
    auditor: '',
    descriptionRes: '',
    backFlag: '0'
})

let isShow = ref(false)
let active = ref(1)

const fileList = ref<UploadUserFile[]>([])

const goBack = () => {
    $router.back();
}

const handleBeforeUpload = (rawFile) => {
    const isLt2M = rawFile.size / 1024 / 1024 < 20
    if (!isLt2M) {
        ElNotification({
            type: 'error',
            message: '上传文件大小不能超过 20MB!'
        })
        return isLt2M
    }
}

const handleUpload = async (options) => {
    let filename = TimeUtils.Jh_timeStampToTime(new Date().getTime(), '{y}-{m}-{d}') + '/' + getUUID() + '&filename=' + options.file.name
    const res = await uploadFile(options.file, filename)
    if (res.code === 200) {
        // fileList.value.push(ossURL.ossURL + filename)
        fileList.value.push({
            name: options.file.name,
            url: ossURL.ossURL + filename
        })
    } else {
        ElNotification({
            type: 'error',
            message: res.msg
        })
    }
}

const rules = {
    school: [
        { required: true, message: '', trigger: 'blur' }
    ],
    description: [
        { required: true, message: '', trigger: 'blur' }
    ]
}

const handleCommit = async (num) => {
    formRef.value.validate(async (valid, fields) => {
        if (valid) {
            let urlList = ''
            if (fileList.value.length != 0) {
                for (let i = 0; i < fileList.value.length; i++) {
                    urlList += ';;' + fileList.value[i].url;
                }
                urlList = urlList.substring(2)
            }
            formData.value.fileUrl = urlList
            let res = { code: 500, msg: '异常' }
            if (num === 0) {
                res = await save()
            } else {
                await ElMessageBox.confirm(
                    '是否确认发送?',
                    '提示',
                    {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        type: 'info',
                    }).then(async () => {
                        res = await commit()
                        // reload($router.currentRoute.value.path)
                    })
            }

            if (res.code == 200) {
                ElNotification({
                    type: 'success',
                    message: '提交成功'
                })
                isShow.value = true
                active.value = 2
            } else {
                ElNotification({
                    type: 'error',
                    message: res.msg
                })
            }
        } else {
            ElMessage.error("请输入全部信息")
        }
    })
}

const save = async () => {
    return await saveJoinus(formData.value)
}

const commit = async () => {
    return await commitJoinus(formData.value)
}

const init = async () => {
    const id = $route.params.id
    if (id !== null && id !== '') {
        const res = await getJoin(id)
        if (res.code == 200) {
            formData.value = res.data
            if (res.data.fileUrl !== null && res.data.fileUrl !== '') {
                let urlList = res.data.fileUrl.split(";;")
                urlList.forEach(item => {
                    fileList.value.push({
                        name: item.substring(item.indexOf("&filename=") + 10),
                        url: item
                    })
                });
            }
        } else {
            ElNotification({
                type: 'error',
                message: '异常'
            })
        }
    }
}

init()
</script>

<style lang="scss" scoped>
.el-form-item {
    margin: 20px 20px;
}

.card {
    border: 2px solid #dcdfe6;
    border-radius: 15px;
    text-align: center;
    padding: 2px 18px;
    margin-right: 2%;
    margin-bottom: 2%;
    color: #606266;
}

.card:hover {
    background-color: #ecf5ff;
    color: #409eff;
    border-color: #c6e2ff;
    cursor: pointer;
}

.card-select {
    border-color: #409eff;
    background-color: #ecf5ff;
    color: #409eff;
}

.el-divider {
    margin: 15px 0 !important;
}
</style>

<script lang="ts">
export default {
    name: "JoinUsDetail"
}
</script>