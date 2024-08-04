<template>
    <template v-for="(i, index) in postItemList" :key="index">
        <div class="container">
            <div class="itemBody" @click="goPostInfo(i.id)">
                <div style="width: 5%;display: flex;flex-wrap: wrap;align-content: space-between;height: 55px;">
                    <el-tag type="danger" style="width: 55px;"><i class="el-icon-ali-xihuan"></i>&nbsp;{{ i.likes
                        }}</el-tag>
                    <el-tag type="success" style="width: 55px;"><i class="el-icon-ali-006pinglunhuifu"></i>&nbsp;{{
                        i.favorites ? i.favorites : 0 }}</el-tag>
                </div>
                <div class="itemCenter" style="width: 78%">
                    <p style="display: flex;align-items: center;height: 0px;">
                        <el-text type="info" size="small">[{{ i.type }}]</el-text>&nbsp;
                        <el-text id="title" type="primary" size="large" truncated>{{ i.title }}</el-text>
                    </p>
                    <p>
                        <el-text type="info" size="small" truncated>{{ htmlToString(i.content) }}</el-text>
                    </p>
                    <div style="display: flex;width: 100%;">
                        <div v-for="(url, index) in i.urlList" :key="index"
                            style="position: relative;display: flex;justify-content: center;align-items: center;"
                            class="test" v-show="url.indexOf('type=video') < 0">
                            <el-image style="max-width: 140px;height:auto;object-fit: cover;margin-left: 1%;"
                                :src="url" />
                            <!-- <i v-show="url.indexOf('type=video') >= 0" class="el-icon-ali-shipin shipinIcon"></i> -->
                        </div>
                    </div>
                </div>
                <div class="itemRight" style="width: 13%">
                    <p><el-text type="info" size="small">{{ i.user.nickName }}</el-text></p>
                    <p><el-text type="info" size="small">{{ i.createTime }}</el-text></p>
                </div>
            </div>
        </div>
        <el-divider />

    </template>
    <div style="display: flex;justify-content: center;margin-top: 20px;">
        <el-text v-show="isShow && isBottom" type="info">已到底部......</el-text>
        <el-text v-show="isLoading" type="info">正在加载中......</el-text>
    </div>
</template>

<script setup lang="ts">
import { ref, getCurrentInstance, defineProps, watch, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import { ElNotification } from 'element-plus';
import { htmlToString } from '@/utils/StringUtils'
const instance = getCurrentInstance()
const $store = useStore()
const $router = useRouter()
const $route = useRoute()

let isBottom = ref<boolean>(false)//是否到达底部
let isLoading = ref<boolean>(false)//是否正在加载

const props = defineProps({
    typeId: {
        type: Number,
        default: 0,
        reqiured: false,
    },
    orderByColumn: {
        type: String,
        default: 'create_time',
        reqiured: false,
    },
    title: {
        type: String,
        default: '',
        reqiured: false,
    },
    pageNum: {
        type: Number,
        default: 1,
        reqiured: false,
    },
    pageSize: {
        type: Number,
        default: 10,
        reqiured: false,
    },
    isShow: { //某些场景下，有些元素不显示
        type: Boolean,
        default: true,
        reqiured: false,
    }
})

// 分页
let pageNum = ref<number>(props.pageNum)
let pageSize = ref<number>(props.pageSize)

let postItemList = computed(() => $store.state.post.postItemList)
// 绑定触发事件
instance.proxy.$Bus.on('getPostList', function () {
    pageNum.value += 1
    getPostList(true);
})

// 前往详情页
const goPostInfo = async (id) => {
    $router.push({ path: `/postinfo/${id}` })
}

// 获取列表数据
const getPostList = async (isPush: boolean, title: String = null) => {
    if (isBottom.value) {
        return
    }
    try {
        isBottom.value = false;
        isLoading.value = true;
        let num = await $store.dispatch('GetPostList', {
            pageNum: pageNum.value,
            pageSize: pageSize.value,
            typeId: props.typeId,
            orderByColumn: props.orderByColumn,
            isPush,
            title
        });
        // 当前分类无数据
        if (num == null || num == 0 || num == '') {
            isBottom.value = true;
        }
    } catch (err) { }
    isLoading.value = false;
}

watch(
    () => props.typeId,
    () => {
        pageNum.value = 1
        isBottom.value = false;
        getPostList(false)
    },
    { immediate: false }
)
watch(
    () => props.orderByColumn,
    () => {
        pageNum.value = 1
        isBottom.value = false;
        getPostList(false, props.title)
    },
    { immediate: false }
)
watch(
    () => props.title,
    () => {
        pageNum.value = 1
        isBottom.value = false;
        getPostList(false, props.title)
    },
    { immediate: false }
)
watch(
    () => props.pageNum,
    () => {
        pageNum.value = props.pageNum;
        isBottom.value = false;
        getPostList(false, props.title)
    },
    { immediate: true }
)
</script>

<style scoped>
.test .shipinIcon {
    position: absolute;
    color: white;
    font-size: 40px;
    /* top: 35%;
    left: 35%; */
    opacity: 0.7;
    transition: transform 0.6s ease-in-out;
    display: flex;
    justify-content: center;
    align-items: center;
}

.test:hover .shipinIcon {
    color: rgb(64, 158, 255);
    transform: scale(1.4);
    transition: 0.6s;
}

/* .shipinIcon {
    position: absolute;
    color: white;
    font-size: 30px;
    top: 40%;
    left: 40%;
    transition: transform 0.4s ease-in-out; 
}

.shipinIcon:hover {
    color: #409EFF;
    transform: scale(1.4);
    transition: 0.2s;
} */

.el-divider {
    margin: 0;
}

#title {
    width: 500px;
}

#title:hover {
    text-decoration: underline;
}

.itemBody {
    margin: 20px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    /* top: 10px; */
}

.container {
    width: 100%;
    overflow-y: hidden;
}

.container:hover {
    background-color: aliceblue;
    cursor: pointer;
}

.itemCenter {
    width: 78%;
}

.itemCenter p {
    line-height: 1;
}

.itemRight {
    text-align: center;
    width: 15%;
}

.itemRight p {
    line-height: 0.5;
}
</style>

<script lang="ts">
export default {
    name: "PostList"
}
</script>