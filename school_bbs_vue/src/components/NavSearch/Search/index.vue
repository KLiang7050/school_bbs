<template>
    <div id="searchContainer" style="display: flex;align-items: center">
        <el-input v-model="keyword" size="large" class="w-50 m-2" placeholder="请输入帖子标题" :prefix-icon="Search"
            @keyup.enter="handleSearch" />
        <el-button type="primary" style="margin-left: 10px;" @click="handleSearch">搜索一下</el-button>
    </div>
</template>
<script setup lang="ts">
import { ref, reactive } from "vue";
import { Search } from "@element-plus/icons-vue"
import { useRouter } from "vue-router"
const router = useRouter()

// 搜索关键字
let keyword = ref<string>()

const handleSearch = () => {
    router.push({ name: "search", query: { keyword: keyword.value } })
}

</script>
<style lang="scss" scoped>
#searchContainer {
    display: inline;
    width: 400px;
}
</style>

<script lang="ts">
export default {
    name: "Search"
}
</script>