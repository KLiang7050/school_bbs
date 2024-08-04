<template>
    <div class="container">
        <el-row @mouseover="showBtnCol = true" @mouseleave="showBtnCol = false">
            <el-col :span="20">
                <div class="itemBody">
                    <div>
                        <el-text type="primary" size="large">{{ item.parent.user.nickName }}&nbsp; </el-text>
                        <el-text type="info" size="large">回复了我的评论： </el-text>
                        <el-text id="replyContent" class="w-100px">{{ htmlToString(item.content) }}</el-text>
                    </div>
                    <div>
                        <el-divider direction="vertical" />
                        <el-text type="info" size="small">&nbsp;{{ htmlToString(item.parent.content) }}</el-text>
                    </div>
                    <div>
                        <el-text type="info">{{ item.createTime }}</el-text>&nbsp;&nbsp;
                        <el-text id="reply" type="info"
                            @click="isShowReply = true"><el-icon><i-ep-ChatRound /></el-icon>回复</el-text>
                    </div>
                    <div v-show="isShowReply">
                        <el-input style="width: 60%;" v-model="textarea" :rows="3" type="textarea"
                            placeholder="请输入" />&nbsp;
                        <el-button type="primary" @click="hanldeReply2">回复</el-button>
                    </div>
                </div>
            </el-col>
            <el-col :span="4" :class="{ 'look-btn-col-visible': showBtnCol }" class="look-btn-col">
                <el-button type="primary" link @click="goPost()">去看看</el-button>
            </el-col>
        </el-row>
        <el-divider />
    </div>
</template>

<script setup lang="ts">
import { ref, reactive,inject } from 'vue';
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElNotification } from 'element-plus';

import type { MyReply } from "@/type/myReply"

import { htmlToString } from '@/utils/StringUtils'

import { addReply2, getMoreReply } from '@/api/post'

const reload = inject('reload')
const $store = useStore()
const $router = useRouter()

const props = defineProps({
    item: {
        default: <MyReply>{},
        reqiured: true
    }
})
const isShowReply = ref(false)
const showBtnCol = ref(false);
const textarea = ref("")

const hanldeReply2 = async () => {
    let replyData = {
        parentUserName: props.item.parentUserName,
        mainId: props.item.mainId,
        parentId: props.item.id,
        parentUserId: props.item.user.id,
        rootId: props.item.rootId === 0 ? props.item.id : props.item.rootId
    }
    let res = await addReply2(replyData, textarea.value)
    if (res.code == 200) {
        ElNotification({
            type: 'success',
            message: res.msg,
        });
        console.log("---------");
        reload($router.currentRoute.value.path)
    } else {
        ElNotification({
            type: 'error',
            message: res.msg
        })
    }
}

const goPost = () => {
    $router.push("/postinfo/"+props.item.mainId)
}

</script>

<style scoped>
.look-btn-col {
    display: flex;
    justify-content: center;
    align-items: center;
    visibility: hidden;
}

.look-btn-col-visible {
    visibility: visible;
}

#reply:hover {
    color: #409EFF;
    cursor: pointer;
}

.itemBody {
    margin: 20px 30px;
    display: flex;
    flex-direction: column;
    width: 90%;
}

.container {
    width: 100%;
}
</style>

<script lang="ts">
export default {
    name: "ReplyMeItem"
}
</script>