<template>
    <div style="width: 100%;">
        <div style="width: 100%;display: flex;margin-bottom: 15px;">
            <div style="display:flex;padding: 0 6%;width:100%" @mouseenter="isShowReplyText = true"
                @mouseleave="isShowReplyText = false">
                <div id="leftDiv">
                    <el-avatar v-if="i.user" shape="square" :src="i.user.avatar" style="flex:none" />
                    <el-text type="info" style="margin: 0 7px 0 12px;flex:none" v-if="i.user.nickName">{{
                        i.user.nickName
                        }}</el-text>
                    <el-text style="width: auto;white-space:nowrap;" type="primary" v-if="i.rootId != 0">@{{
                        i.parentUserName }}:&nbsp;</el-text>
                    <div v-html="i.content" style="overflow-wrap: break-word;width: 80%;word-break: break-all;">
                    </div>
                </div>
                <div id="rightDiv">
                    <el-text class="rightItem" type="primary" v-show="isShowReplyText" id="replyText"
                        @click="handleShowReply(i.user.nickName, i.user.id, i.id)" style="user-select: none">回复</el-text>
                    <div class="rightItem" style="display: flex;">
                        <i id="like" class="el-icon-ali-dianzan" v-show="!isLike" @click="handleLike(i.id)"></i>
                        <i id="like" class="el-icon-ali-dianzan_kuai" style="color: red;" v-show="isLike"
                            @click="handleLike(i.id)"></i>
                        <el-text id="likeCount" type="info" size="small">{{ likeCount }}</el-text>
                    </div>
                    <el-text class="rightItem" id="createTime" type="info" size="small">{{ i.createTime }}</el-text>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive, defineProps, getCurrentInstance, computed, defineEmits, watch } from 'vue';
import type { PostReply } from '@/type/postReplyType';
import { useStore } from 'vuex'
import { like } from '@/api/like';
import { ElNotification } from 'element-plus';
import tokenUtils from '@/utils/tokenUtils';
import { debounce } from 'lodash';
const instance = getCurrentInstance()
const $store = useStore()

const props = defineProps({
    i: {
        default: <PostReply>{},
        reqiured: true
    },
})

let isShowReplyText = ref<boolean>(false)
let isLike = ref<boolean>(false)
let likeCount = ref<number>(props.i.likes)

const emit = defineEmits(['updateShowReply']);
const handleShowReply = (nickName: String, userId: number, id: number) => {
    if (tokenUtils.get() === null || tokenUtils.get() === "" || tokenUtils.get() === undefined) {
        ElNotification({
            type: 'info',
            message: "请登录"
        })
    } else {
        emit('updateShowReply', true, nickName, userId, id)
    }
}

const handleLike = debounce(async (id) => {
    try {
        const res = await like(id, 1, props.i.mainId, props.i.parentUserId)
        if (res.msg === '取消点赞') {
            isLike.value = false
            likeCount.value = likeCount.value - 1;
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
            if (element.postId === props.i.id) {
                isLike.value = true;
            }
        });
    }
}

watch(
    () => props.i,
    () => {
        isLike.value = false;
        handleLikeList()
    },
    { immediate: true }
)
</script>

<style scoped>
#replyText {
    cursor: pointer;
    transition: 0.3s;
}

#replyText:hover {
    cursor: pointer;
    transform: scale(1.2);
}

#leftDiv {
    display: flex;
    width: 65%;
    align-items: center;
    text-align: left;
}

#rightDiv {
    display: flex;
    width: 35%;
    align-items: center;
    justify-content: flex-end;
}

#createTime {
    margin: 0 0 0 5%;
}

#likeCount {
    flex: none;
    margin-left: 8%;
}

#like {
    font-size: 20px;
    margin: 0 2% 0 10%;
    cursor: pointer;
    transition: 0.3s;
}

#like:hover {
    margin: 0 2% 0 10%;
    cursor: pointer;
    transform: scale(1.2);
}

.rightItem {
    margin-right: 8%;
}
</style>

<script lang="ts">
export default {
    name: "ReplyBox"
}
</script>