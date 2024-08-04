<template>
    <div id="myHeader">
        <div id="headerLeft">
            <el-upload :show-file-list="false" :http-request="handleUpload" :on-success="handleUploadChange">
                <el-tooltip content="上传头像" placement="right" effect="light">
                    <el-avatar :size="80" :src="info.avatar" style="margin-right: 20px;" />
                </el-tooltip>
            </el-upload>
            <div style="margin-top: 20px;">
                <el-text class="mx-1" style="font-size: 30px;">{{ info.nickName }}</el-text><br />
                <el-text class="mx-1" type="info">{{ info.introduce }}</el-text>
            </div>
        </div>
        <div id="headerRight">
            <div class="num">
                <p class="numTitle">发帖数</p>
                <p>{{ info.postCount }}</p>
            </div>
            <div class="num">
                <p class="numTitle">贴子获赞数</p>
                <p>{{ info.likeCount }}</p>
            </div>
            <div class="num">
                <p class="numTitle">贴子获收藏数</p>
                <p>{{ info.favoritesCount }}</p>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { getPolicy, uploadFile } from '@/api/thirdparty'
import { studentVerification, reqUserInfoById } from '@/api/user'
import { reactive, ref, toRefs, computed } from 'vue'
import { getUUID } from '@/utils/uuidUtils';
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router';
import { ElNotification } from 'element-plus'
const $store = useStore()
const $router = useRouter()
const $route = useRoute()

const uploadUrl = ref();
const uploadData = reactive({
    policy: '',
    OSSAccessKeyId: '',
    signature: '',
    key: '',
    success_action_status: 200,
    // file:''
})

const formData = new FormData()

// 用户信息
const info = computed(() => $store.state.user.user)

const handleUpload = async (uploadRequestOptions) => {
    // const res1 = await getPolicy()
    // formData.set('key', res1.data.dir + "/" + getUUID())
    // formData.set('policy', res1.data.policy)
    // formData.set('OSSAccessKeyId', res1.data.accessid)
    // formData.set('signature', res1.data.signature)
    // formData.set('success_action_status', '200')
    // formData.set('file', uploadRequestOptions.file)
    // await uploadFile(formData);
}

const handleUploadChange = async (response, uploadFile, uploadFiles) => {
    info.value.avatar = URL.createObjectURL(uploadFile.raw);
    try {
        await $store.dispatch("UpdateAvatar", uploadFile.raw);
        ElNotification({
            type: 'success',
            message: '上传成功',
        });
    } catch (err) {}
}


const init = async () => {
    $store.state.user.user = {}
    const userId = $route.params.id ? $route.params.id : null
    if (userId === null || userId === '') {
        $store.state.user.user = $store.state.user.info
    } else {
        const res = await reqUserInfoById(userId)
        if (res.code == 200) {
            $store.state.user.user = res.data
        } else {
            ElNotification({
                type: 'error',
                message: res.msg
            })
        }
    }
}

init()
</script>

<style lang="scss" scoped>
// .avatar {
//     width: 100px;
//     height: 100px;
//     border-radius: 50%;
//     margin-right: 15px;
// }

#myHeader {
    background-color: #F2F2F2;
    height: 120px;
    padding: 10px 30px 10px 40px;
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    border-radius: 20px;
}

#headerLeft {
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
}

#headerRight {
    font-size: 14px;
    display: flex;
    float: left;
    text-align: center;
}

.numTitle {
    color: #909399;
}

.num {
    width: 100px;
}

p {
    line-height: 0.5;
}
</style>

<script lang="ts">
export default {
    name: "UserInfoHeader"
}
</script>