<template>
    <!-- 内容 -->
    <el-row>
        <el-col :span="2"><el-avatar v-if="item.user" shape="square" :src="item.user.avatar" size="large" /></el-col>
        <el-col :span="3" style="text-align: left;"><el-text v-if="item.user.nickName"><el-text type="info">[{{
            item.floor }}楼]</el-text>&nbsp;{{ item.user.nickName
                }}</el-text></el-col>
        <el-col :span="14"></el-col>
        <el-col :span="5" style="text-align: right;"><el-text type="info" size="small">[ {{ item.createTime }}
                ]</el-text></el-col>
    </el-row>
    <el-row style="height: 100%;width: 85%;margin: 0 6%;">
        <div v-html="item.content">
        </div>
    </el-row>
    <!-- 回复框 -->
    <el-row style="padding: 0 8%;">
        <el-divider class="divider" v-show="item.replyList && !isShowNotReplyText" />
        <ReplyBox v-for="(i, index) in item.replyList" :key="index" :i="i" @updateShowReply="updateShowReply" />
        <div v-show="isShowReplyTextBox" id="replyTextBox">
            <el-text type="info" style="flex: none">我 回复 {{ replyData.parentUserName }}：</el-text>
            <el-input style="width: 55%;margin: 0 2%;" v-model="replyTextarea" :rows="3" type="textarea"
                placeholder="请输入" />&nbsp;
            <el-button type="primary" @click="hanldeReply2">回复</el-button>
        </div>
        <div v-show="isShowMoreReply && item.replyCount > 3"
            style="display: flex;align-items: center;width: 97%;justify-content: right;">
            <el-text type="info" style="user-select: none">共{{ item.replyCount }}条评论，
                <el-text type="info" id="moreReply" @click="moreReply">查看更多</el-text>
            </el-text>
        </div>
        <div v-if="!isShowMoreReply" class="example-pagination-block" style="width: 100%;margin-top: 2%;">
            <el-pagination style="float: right;" background layout="prev, pager, next" :page-count="pageCount"
                v-model:current-page="pageNum" @current-change="handleCurrentChange" />
        </div>
        <el-divider class="divider" v-show="item.replyList && !isShowNotReplyText" />
    </el-row>
    <!-- 下方点赞回复 -->
    <el-row justify="center">
        <el-col class="likeCol" :span="2" style="color: red;">
            <i v-show="!isLike" class="el-icon-ali-xihuan2 clicked" @click="handleLike(item.id)"></i>
            <el-text v-show="!isLike" type="info">{{ likeCount }}</el-text>
            <i v-show="isLike" class="el-icon-ali-xihuan1 clicked" @click="handleLike(item.id)"></i>
            <el-text v-show="isLike" type="info">{{ likeCount }}</el-text>
        </el-col>
        <el-col class="likeCol" :span="1">
            <el-divider direction="vertical" />
        </el-col>
        <el-col class="likeCol" :span="2">
            <el-text type="info" id="replyChecked" @click="handleShowReply(item.user.nickName, item.user.id, item.id)"
                style="user-select: none">回复</el-text>
        </el-col>
    </el-row>
    <el-divider />
</template>

<script setup lang="ts">
import ReplyBox from '@/components/PostInfo/ReplyItem/ReplyBox/index.vue';
import { ref, reactive, onMounted, getCurrentInstance, defineProps, computed, inject } from 'vue';
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router';
import type { PostReply } from '@/type/postReplyType';
import { addReply2, getMoreReply } from '@/api/post'
import { ElNotification } from 'element-plus';
import { like } from '@/api/like';
import tokenUtils from '@/utils/tokenUtils';
import { debounce } from 'lodash';
const reload = inject('reload')
const instance = getCurrentInstance()
const $store = useStore()
const $router = useRouter()
const $route = useRoute()
const props = defineProps({
    item: {
        default: <PostReply>{},
        reqiured: true
    }
})

const item = ref<PostReply>(props.item)
let likeCount = ref<number>(item.value.likes)

let isLike = ref<boolean>(false)
let isFavorite = ref<boolean>(false)
let isShowNotReplyText = ref<boolean>(!item.value.replyList)
let isShowReplyTextBox = ref<boolean>(false)
let isShowMoreReply = ref<boolean>(true)

// 回复框属性
const replyTextarea = ref<string>("")
const replyData = reactive({
    parentId: 0,
    parentUserId: 0,
    rootId: 0,
    mainId: 0,
    parentUserName: ""
})

// 分页属性
let orderByColumn = ref<string>("create_time")
let pageNum = ref<number>(1)
let pageSize = ref<number>(7)
let pageCount = ref<number>()

// 当前页触发事件
const handleCurrentChange = (currentPage: number, pageSize: number) => {
    pageNum.value = currentPage
    moreReply()
}

const handleShowReply = (nickName: string, userId: number, replyId: number) => {
    if (tokenUtils.get() === null || tokenUtils.get() === "" || tokenUtils.get() === undefined) {
        ElNotification({
            type: 'info',
            message: "请登录"
        })
    }else{
        isShowNotReplyText.value = !isShowNotReplyText.value
        isShowReplyTextBox.value = !isShowReplyTextBox.value
        replyData.parentId = replyId
        replyData.parentUserId = userId
        replyData.rootId = item.value.id
        replyData.mainId = item.value.mainId
        replyData.parentUserName = nickName
    }
}

const updateShowReply = (flag: boolean, nickName: string, userId: number, replyId: number) => {
    isShowReplyTextBox.value = flag
    replyData.parentUserName = nickName
    replyData.parentId = replyId
    replyData.parentUserId = userId
    replyData.rootId = item.value.id
    replyData.mainId = item.value.mainId
}

const hanldeReply2 = async () => {
    replyData.parentUserName = item.value.parentUserName
    replyData.mainId = item.value.mainId
    replyData.parentId = item.value.id
    replyData.parentUserId = item.value.user.id
    replyData.rootId = item.value.id
    let res = await addReply2(replyData, replyTextarea.value)
    if (res.code == 200) {
        ElNotification({
            type: 'success',
            message: res.msg,
        });
        instance.proxy.$Bus.emit('setPageNum')
        // console.log($router.currentRoute.value);
    }
}

const moreReply = async () => {
    // 关掉回复框
    isShowReplyTextBox.value = false
    replyTextarea.value = ""

    const params = {
        pageNum: pageNum.value,
        pageSize: pageSize.value,
        orderByColumn: orderByColumn.value,
        rootId: item.value.id
    }
    try {
        isShowMoreReply.value = false
        let res = await getMoreReply(params)
        item.value.replyList = res.rows
        pageCount.value = Math.ceil(res.total / pageSize.value)
    } catch (err) {}
}

const handleLike = debounce(async (id) => {
    try {
        const res = await like(id, 1, item.value.mainId, item.value.parentUserId)
        if (res.msg === '取消点赞') {
            isLike.value = false
            likeCount.value--;
        } else {
            isLike.value = true
            likeCount.value++;
        }
    } catch (err) {
        if (err.response.data.code === 401) {
            ElNotification({
                type: 'info',
                message: "请登录"
            })
            isLike.value = false
        }
    }
})

const handleLikeList = async () => {
    const likeList = computed(() => $store.getters.likeList);
    if (likeList) {
        likeList.value.forEach(element => {
            if (element.postId === item.value.id) {
                isLike.value = true;
            }
        });
    }
}

handleLikeList()
</script>

<style scoped>
#moreReply {
    color: #909399;
}

#moreReply:hover {
    color: #409EFF;
    cursor: pointer;
}

#replyText {
    cursor: pointer;
    transition: 0.3s;
}

#replyText:hover {
    cursor: pointer;
    transform: scale(1.2);
}

#replyTextBox {
    display: flex;
    align-items: center;
    width: 100%;
    justify-content: center;
    margin-top: 2%;
}

.likeCol {
    display: flex;
    justify-content: center;
}

#replyChecked {
    transition: 0.4s;
    cursor: pointer;
}

#replyChecked:hover {
    color: #409EFF;
    transform: scale(1.2);
    transition: 0.4s;
}

.clicked {
    cursor: pointer;
    font-size: 22px;
    transition: 0.2s;
    margin: 0 5px;
}

.clicked:hover {
    transform: scale(1.2);
}

#back {
    cursor: pointer;
    color: #909399;
}

#back:hover {
    color: #409EFF;
}

.divider {
    margin: 20px 0 !important;
}
</style>

<script lang="ts">
export default {
    name: "ReplyItem"
}
</script>