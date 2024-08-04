<template>
    <div style="padding: 0 20px;">
        <div>
            <el-divider content-position="left" class="divider" style="margin-top: 25px;">
                <el-text class="mx-1" type="info" size="large">回复我的</el-text>
            </el-divider>
        </div>
        <div class="infobody">
            <ReplyMeItem v-for="(item, index) in reply" :key="index" :item="item" />
            <!-- 分页 -->
            <div class="example-pagination-block" style="float: right" v-show="true">
                <el-pagination background layout="prev, pager, next" :page-count="pageCount" v-model:current-page="pageNum"/>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import ReplyMeItem from '@/components/ReplyMeItem/index.vue'
import { ref, watch, getCurrentInstance,inject } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import { ElNotification } from 'element-plus';
import { getMyReply } from '@/api/post'

const reload = inject('reload')
const instance = getCurrentInstance()
const $store = useStore()
const $router = useRouter()
const $route = useRoute()
let isLoading = ref<boolean>(false) //是否加载
// 分页属性
let pageNum = ref<number>(1)
let pageSize = ref<number>(5)
let pageCount = ref<number>(10)

let reply = ref()

// 当前页触发事件
const handleCurrentChange = (value: number) => {
    initReply()
}

// 初始化回复列表
const initReply = async () => {
    isLoading.value = true
    let data = {
        pageNum: pageNum.value,
        pageSize: pageSize.value
    }

    try {
        const res = await getMyReply(data)
        reply.value = res.rows
        pageCount.value = Math.ceil(res.total / pageSize.value)
    } catch (err) {}
    isLoading.value = false
}

initReply()

watch(
    () => pageNum.value,
    () => {
        initReply()
    }
)
</script>

<style lang="scss" scoped>
.infobody {
    margin: 20px 35px;
}
</style>

<script lang="ts">
export default {
    name: "ReplyMe"
}
</script>