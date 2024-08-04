<template>
    <el-container class="container">
        <el-header class="header">
            <div style="display: flex;align-items: center;width: 400px;">
                <el-input v-model="keyword" size="large" class="w-50 m-2" placeholder="请输入帖子标题" :prefix-icon="Search"
                    @keyup.enter="handleSearch" />
                <el-button type="primary" style="margin-left: 10px;" @click="handleSearch">搜索一下</el-button>
            </div>
            <div style="display: flex;align-items: center;">
                <el-text type="info" class="orderFont" :class="orderByColumn === 'create_time' ? classChecked : null"
                    @click="changeChecked('create_time')">[最新]
                </el-text>
                <el-divider direction="vertical" />
                <el-text type="info" class="orderFont" :class="orderByColumn === 'likes' ? classChecked : null"
                    @click="changeChecked('likes')">[最热]
                </el-text>
            </div>
        </el-header>
        <el-main>
            <el-divider />
            <PostList :title="title" :pageNum="pageNum" :pageSize="pageSize" :isShow="false"
                :orderByColumn="orderByColumn" />
            <div style="" v-show="!$store.state.post.postItemList.length">
                <el-text type="info">暂无数据......</el-text>
            </div>
        </el-main>
        <!-- 分页 -->
        <div class="example-pagination-block" style="float: right" v-show="$store.state.post.postItemList.length">
            <el-pagination background layout="prev, pager, next" :page-count="pageCount" v-model:current-page="pageNum"
                :current-change="handleCurrentChange" />
        </div>
    </el-container>
</template>
<script setup lang="ts">
import PostList from '@/components/PostList/index.vue'
import { ref, reactive, computed } from "vue";
import { Search } from "@element-plus/icons-vue"
import { useRouter,useRoute } from "vue-router"
import { useStore } from "vuex";
const $router = useRouter()
const $route = useRoute()
const $store = useStore()
let orderByColumn = ref("create_time")
let classChecked = reactive({
    orderChecked: true
})

$store.state.post.postItemList = []

// 搜索关键字
let keyword = ref()
let title = ref<string>()

// 分页
let pageNum = ref<number>(1)
let pageSize = ref<number>(5)
let pageCount = computed(() => Math.ceil($store.state.post.total / pageSize.value))

let changeChecked = (newOrderName) => {
    orderByColumn.value = newOrderName
}

const handleSearch = () => {
    title.value = keyword.value
}

// 当前页触发事件
const handleCurrentChange = (value: number) => {
    pageNum.value = value
}

const init = () => {
    const temp = $route.query.keyword
    if(temp != null && temp !== ""){
        keyword.value = temp
        title.value = keyword.value
    }
}

init()
</script>
<style lang="scss" scoped>
.orderFont {
    cursor: pointer;
    user-select: none;
}

.orderFont:hover {
    color: #409eff;
}

.orderChecked {
    color: red;
}

.orderIcon {
    position: relative;
    top: 3px;
}

.container {
    margin: 10px 10px;
}

.header {
    display: flex;
    justify-content: space-between;
}
</style>