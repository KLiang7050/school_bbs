<template>
    <div>
        <!-- 标题 -->
        <el-row style="margin: 20px 20px 0 20px;">
            <el-col :span="1" id="back" @click="goBack()"><i class="el-icon-ali-fanhui"
                    style="font-size: 28px;"></i></el-col>
            <el-col :span="1"><el-divider direction="vertical" /></el-col>
            <el-col :span="2"><el-text type="info">[ {{ main.type }} ]</el-text></el-col>
            <el-col :span="10" style="text-align: left"><el-text style="font-size: 21px" truncated>&nbsp;&nbsp;{{
                main.title }}</el-text></el-col>
            <el-col :span="3"><el-text type="info" size="small">[ {{ main.createTime }} ]</el-text></el-col>
            <el-col :span="7" style="">
                <div class="example-pagination-block" style="float: right">
                    <el-pagination background layout="prev, pager, next" :page-count="pageCount"
                        v-model:current-page="pageNum" />
                </div>
            </el-col>
        </el-row>
        <el-divider />
        <!-- 楼主 -->
        <el-row>
            <el-col :span="2"><el-avatar v-if="main.user" shape="square" :src="main.user.avatar" size="large"
                    @click="goUser(main.user.id)" style="cursor: pointer;" /></el-col>
            <el-col :span="3" style="text-align: left;"><el-text type="danger">[楼主]</el-text>&nbsp;<el-text
                    v-if="main.user">{{ main.user.nickName }}</el-text></el-col>
            <el-col :span="14"></el-col>
            <el-col :span="5" style="text-align: right;"><el-text type="info" size="small">[ 上次修改时间：{{
                    main.updateTime}}]</el-text></el-col>
        </el-row>
        <el-row style="height: 100%;width: 85%;margin: 0 6%;">
            <div v-html="main.content">
            </div>
        </el-row>
        <el-row justify="center">
            <el-col class="likeCol" :span="2" style="color: red;">
                <i v-show="!isLike" class="el-icon-ali-xihuan2 clicked" @click="handleLike(main.id)"></i>
                <el-text v-show="!isLike" type="info">{{ likeCount }}</el-text>
                <i v-show="isLike" class="el-icon-ali-xihuan1 clicked" @click="handleLike(main.id)"></i>
                <el-text v-show="isLike" type="info">{{ likeCount }}</el-text>
            </el-col>
            <el-col class="likeCol" :span="1">
                <el-divider direction="vertical" />
            </el-col>
            <el-col class="likeCol" :span="2" style="color: #409EFF;">
                <i v-show="!isFavorite" class="el-icon-ali-shoucang clicked" @click="handleFavorite"></i>
                <el-text v-show="!isFavorite" type="info">{{ favoriteCount }}</el-text>
                <i v-show="isFavorite" class="el-icon-ali-shoucang1 clicked" @click="handleFavorite"></i>
                <el-text v-show="isFavorite" type="info">{{ favoriteCount }}</el-text>
            </el-col>
        </el-row>
        <el-divider />
        <!-- 中间 -->
        <el-row>
            <div v-show="replyList.length > 0"
                style="display: flex;align-items: center;justify-content: space-between;width: 100%;">
                <el-text type="info">回复</el-text>
                <div>
                    <el-text type="info" class="orderFont"
                        :class="orderByColumn === 'create_time' ? classChecked : null"
                        @click="changeChecked('create_time')">[最新]
                    </el-text>
                    <el-divider direction="vertical" />
                    <el-text type="info" class="orderFont" :class="orderByColumn === 'likes' ? classChecked : null"
                        @click="changeChecked('likes')">[最热]
                    </el-text>
                </div>
            </div>
        </el-row>
        <el-divider v-show="replyList.length > 0" />
        <!-- 回复 -->
        <div v-show="!isLoading" v-for="(item, index) in replyList" :key="index">
            <ReplyItem :item="item" />
        </div>
        <div style="display: flex;justify-content: center;align-items: top;height: 2000px;" v-show="isLoading">
            <el-text type="info">正在加载中......</el-text>
        </div>
        <!-- 分页 -->
        <div class="example-pagination-block" style="float: right">
            <el-pagination background layout="prev, pager, next" :page-count="pageCount"
                v-model:current-page="pageNum" />
        </div>
        <el-affix :offset="0" @click="handleShowReply" z-index="9999"
            style="position: fixed;bottom: 70px;right: 40px;width: 50px;">
            <div style="
            height: 45px; 
            width: 50px; 
            background-color: rgb(64, 158, 255); 
            box-shadow: var(--el-box-shadow-lighter); 
            text-align: center; 
            line-height: 45px;
            font-size: 17px;
            color: rgb(255, 255, 255);
            border-radius: 10px;
            cursor: pointer;
        ">
                回复
            </div>
        </el-affix>
    </div>
    <el-dialog v-model="dialogFormVisible" title="回复" width="1200" style="height: 620px;margin: 2% 10%;">
        <div style="border: 1px solid #ccc;">
            <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
                :mode="mode" />
            <Editor style="height: 360px;" v-model="replyDatas.content" :defaultConfig="editorConfig" :mode="mode"
                @onCreated="handleCreated" />
        </div>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="handleReply">
                    回复
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup lang="ts">
import ReplyItem from '@/components/PostInfo/ReplyItem/index.vue';

import { ref, reactive, computed, shallowRef, onBeforeUnmount, watch, getCurrentInstance, inject } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';

import { ElNotification } from 'element-plus';

import { Editor, Toolbar } from '@wangeditor/editor-for-vue';
import { IToolbarConfig, IEditorConfig } from '@wangeditor/editor';

import '@wangeditor/editor/dist/css/style.css';

import ossConfig from '@/config/oss'

import type { PageR } from '@/type/pageR'

import tokenUtils from '@/utils/tokenUtils';
import { getUUID } from '@/utils/uuidUtils';

import { addReply } from '@/api/post'
import { getPolicy, uploadFile } from '@/api/thirdparty'
import { like, favorite } from '@/api/like'
import { debounce } from 'lodash';
const reload = inject('reload')
const instance = getCurrentInstance()
const $store = useStore()
const $router = useRouter()
const $route = useRoute()

// 列表数据
const main = computed(() => $store.getters.postMainInfo);
const replyList = computed(() => $store.getters.replyList);

const favorits = computed(() => $store.getters.favorites);

const dialogFormVisible = ref(false) //是否显示回复窗口
let isLoading = ref<boolean>(false) //是否加载
const isLike = ref<boolean>(false) //是否喜欢
const isFavorite = ref(false) //是否点赞
let likeCount = ref<number>()
let favoriteCount = ref<number>()

// 排序的动态样式
let classChecked = reactive({
    orderChecked: true
})

// 分页属性
let orderByColumn = ref("create_time")
let pageNum = ref($route.query.pageNum || 1)
let pageSize = ref(5)
let pageCount = ref()

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()
const mode = ref('default')

// 富文本编辑器
const valueHtml = ref<string>()// 内容 HTML
const formDatas = new FormData()
const replyDatas = reactive({
    content: '',
    mediaUrls: '',
})

// 富文本编辑器
const toolbarConfig: Partial<IToolbarConfig> = {
}
toolbarConfig.excludeKeys = [
    'todo',
    'insertTable',
    'blockquote',
    'fullScreen'
]
// 初始化 MENU_CONF 属性
const editorConfig: Partial<IEditorConfig> = {
    placeholder: "请输入内容...",
    MENU_CONF: {}

    // 其他属性...
}
// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
    const editor = editorRef.value
    if (editor == null) return
    editor.destroy()
})
// 修改 uploadImage 菜单配置
editorConfig.MENU_CONF['uploadImage'] = {
    server: '/',
    fieldName: 'custom-field-name',
    async customUpload(file, insertFn) {
        try {
            let res1 = await getPolicy()
            let key = res1.data.dir + getUUID() + + 'type=images'
            formDatas.set('key', key)
            formDatas.set('policy', res1.data.policy)
            formDatas.set('OSSAccessKeyId', res1.data.accessid)
            formDatas.set('signature', res1.data.signature)
            formDatas.set('success_action_status', '200')
            formDatas.set('file', file)
            await uploadFile(formDatas);
            let url = ossConfig.ossURL + key
            insertFn(url, key)
            if (replyDatas.mediaUrls) replyDatas.mediaUrls += ';;'
            replyDatas.mediaUrls += url
        } catch (err) {}
    }
}
editorConfig.MENU_CONF['uploadVideo'] = {
    // 自定义上传
    async customUpload(file, insertFn) {
        try {
            let res1 = await getPolicy()
            let key = res1.data.dir + getUUID() + 'type=video'
            formDatas.set('key', key)
            formDatas.set('policy', res1.data.policy)
            formDatas.set('OSSAccessKeyId', res1.data.accessid)
            formDatas.set('signature', res1.data.signature)
            formDatas.set('success_action_status', '200')
            formDatas.set('file', file)
            await uploadFile(formDatas);
            let url = ossConfig.ossURL + key
            insertFn(ossConfig.ossURL, key)
            if (replyDatas.mediaUrls) replyDatas.mediaUrls += ';;'
            replyDatas.mediaUrls += url
        } catch (err) {}
    }
}
const handleCreated = (editor) => {
    editorRef.value = editor // 记录 editor 实例，重要！
}

// 添加回复
const handleReply = async () => {
    let res: PageR = await addReply(replyDatas, main.value.id, main.value.user.id)
    if (res.code == 200) {
        ElNotification({
            type: 'success',
            message: res.msg,
        });
        dialogFormVisible.value = false
        instance.proxy.$Bus.emit('setPageNum')
    } else {
        ElNotification({
            type: 'error',
            message: res.msg
        })
    }
}

// 修改排序方法
let changeChecked = (newOrderName) => {
    orderByColumn.value = newOrderName
    initReply()
}

//初始化主贴方法
const infoInit = async () => {
    try {
        $store.state.post.postMainInfo = {}
        $store.state.post.replyList = []
        await $store.dispatch('GetPostInfo', $route.params.id)
        likeCount.value = main.value.likes
        favoriteCount.value = main.value.favorites
        await handleLikeList()
        await handleFavoritesList()
        await initReply()
    } catch (err) {}
}

// 初始化回复列表
const initReply = async () => {
    isLoading.value = true
    let data = {
        mainId: $route.params.id,
        pageNum: pageNum.value,
        pageSize: pageSize.value,
        orderByColumn: orderByColumn.value
    }

    try {
        let total = await $store.dispatch("GetReplyList", data)
        pageCount.value = Math.ceil(total / pageSize.value)
    } catch (err) {}
    isLoading.value = false
}

instance.proxy.$Bus.on('setPageNum', function () {
    const y = window.scrollY;
    initReply()
    window.scrollY = y
})

// 返回上一页
const goBack = () => {
    $router.back();
}

const goUser = (id) => {
    $router.push({
        path: `/userinfo/info/${id}`,
    })
}

// 显示回复框
const handleShowReply = () => {
    if (tokenUtils.get() === null || tokenUtils.get() === "" || tokenUtils.get() === undefined) {
        ElNotification({
            type: 'info',
            message: "请登录"
        })
    } else {
        const token = tokenUtils.get()
        if (!token) {
        } else {
            dialogFormVisible.value = true
        }
    }
}

const handleLike = debounce(async (id) => {
    try {
        const res = await like(id, 0, main.value.id, main.value.user.id)
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
},300)

const handleLikeList = async () => {
    try {
        if (tokenUtils.get()) {
            await $store.dispatch("GetLikeList", main.value.id)
            const likeList = computed(() => $store.getters.likeList);
            if (likeList) {
                likeList.value.forEach(element => {
                    if (element.postId === main.value.id && element.userId === $store.getters.info.id) {
                        isLike.value = true;
                    }
                });
            }
        }
    } catch (err) {}
}

const handleFavoritesList = async () => {
    try {
        if (tokenUtils.get()) {
            await $store.dispatch("GetFavoritesList")
            if (favorits) {
                favorits.value.forEach(element => {
                    if (element.postId === main.value.id && element.userId === $store.getters.info.id) {
                        isFavorite.value = true;
                    }
                });
            }
        }
    } catch (err) {}
}

const handleFavorite = debounce(async () => {
    try {
        const res = await favorite(main.value.id, main.value.user.id)
        if (res.msg === '取消收藏') {
            isFavorite.value = false
            favoriteCount.value--;
        } else {
            isFavorite.value = true
            favoriteCount.value++;
            await $store.dispatch("GetFavoritesList")
        }
    } catch (err) {
        if (err.response.data.code === 401) {
            ElNotification({
                type: 'info',
                message: "请登录"
            })
            isFavorite.value = false
        }
    }
})

watch(
    () => pageNum.value,
    () => {
        initReply()
    }
)

infoInit()
</script>

<style scoped>
.orderIcon {
    position: relative;
    top: 3px;
}

.orderFont:hover {
    color: #409eff;
}

.orderChecked {
    color: red;
}

.orderFont {
    cursor: pointer;
    user-select: none;
}

.orderFont:hover {
    color: #409eff;
}

.likeCol {
    display: flex;
    justify-content: center;
}

.clicked {
    cursor: pointer;
    font-size: 22px;
    transition: 0.5s;
    margin: 0 5px;
}

.clicked:hover {
    transform: scale(1.2);
}

#back {
    cursor: pointer;
    color: #909399;
    transition: 0.4s;
}

#back:hover {
    color: #409EFF;
    transform: scale(1.1);
}

.el-divider {
    margin: 15px 0 !important;
}

.el-row .el-divider {
    margin: 0 0 !important;
}

:deep(.el-row) {
    text-align: center;
    display: flex;
    align-items: center;
    margin: 20px 20px;
}
</style>

<script lang="ts">
export default {
    name: "PostInfo"
}
</script>