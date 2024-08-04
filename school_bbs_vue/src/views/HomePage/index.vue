<template>
    <NavSearch></NavSearch>
    <div style="padding-top: 16px;">
        <TypeNav style="display: flex;align-items: center;">
            <el-icon class="typeIcon">
                <i-ep-Menu style="color: red;"></i-ep-Menu>
            </el-icon>
            <span>热</span>
            <el-divider direction="vertical"></el-divider>
            <span>门</span>
            <el-divider direction="vertical"></el-divider>
            <span>推</span>
            <el-divider direction="vertical"></el-divider>
            <span>荐</span>
        </TypeNav>
        <HotRecommend></HotRecommend>
        <TypeNav style="display: flex;align-items: center;justify-content: space-between;">
            <div style="display: flex;align-items: center;">
                <el-icon class="typeIcon">
                    <i-ep-Menu></i-ep-Menu>
                </el-icon>
                <span>全</span>
                <el-divider direction="vertical"></el-divider>
                <span>部</span>
                <el-divider direction="vertical"></el-divider>
                <span>帖</span>
                <el-divider direction="vertical"></el-divider>
                <span>子</span>
            </div>
            <div style="margin-right: 1%;display: flex;align-items: center;">
                <el-text type="info" class="orderFont" :class="orderByColumn === 'create_time' ? classChecked : null"
                    @click="changeChecked('create_time')">[最新]
                </el-text>
                <el-divider direction="vertical" />
                <el-text type="info" class="orderFont" :class="orderByColumn === 'likes' ? classChecked : null"
                    @click="changeChecked('likes')">[最热]
                </el-text>
            </div>
        </TypeNav>
        <el-tabs class="demo-tabs" v-model="typeId">
            <el-tab-pane v-for="item in editableTabs" :key="item.id" :label="item.type" :name="item.id">
            </el-tab-pane>
        </el-tabs>
        <div class="affix-container"
            style="display: flex;width: 100%;margin-top: 15px;display: flex;justify-content: center;">
            <div class="allPost">
                <PostList :typeId="typeId" :orderByColumn="orderByColumn" />
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import HotRecommend from "@/components/HotRecommend/index.vue";
import { getTypeList } from '@/api/post';
import NavSearch from "@/components/NavSearch/index.vue";
import PostList from "@/components/PostList/index.vue";
import { useRouter } from "vue-router";
import { ref, reactive, computed, getCurrentInstance, onBeforeUnmount, onMounted } from "vue";
const router = useRouter();
const instance = getCurrentInstance()

// 属性=================================
const typeId = ref<number>(0);
const editableTabs = reactive([])

// 排序的动态样式
let classChecked = reactive({
    orderChecked: true
})
let orderByColumn = ref("create_time")


// 计算属性==============================
let isPostBar = computed(() => {
    return router.currentRoute.value.fullPath === '/homepage'
})

// 方法==================================
const changeChecked = (newOrder: string) => {
    orderByColumn.value = newOrder
}



const handleTypeList = async () => {
    try {
        let res = await getTypeList();
        editableTabs.push({
            id: 0,
            type: "全部贴子"
        })
        editableTabs.push(...res.data)
    } catch (err) {}
}

// 全局事件=============================


// =============================
handleTypeList()
</script>
<style lang="scss" scoped>
.orderFont {
    cursor: pointer;
    user-select: none;
    font-size: 13px;
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
    font-size: 11px;
}

:deep(.el-tabs__nav-wrap) {
    background-color: white;
}

:deep(.el-tabs__nav-prev) {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

:deep(.el-tabs__nav-next) {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

:deep(.el-tabs__item) {
    width: 150px;
    height: 65px;
}

.allPost {
    width: 95%;
    height: 100%;
}

.typeIcon {
    margin: 0 10px;
    font-size: 20px;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>

<script lang="ts">
export default {
    name: "HomePage"
}
</script>