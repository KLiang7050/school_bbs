<template>
    <div style="padding: 0 20px;">
        <div>
            <el-divider content-position="left" class="divider" style="margin-top: 25px;">
                <el-text class="mx-1" type="info" size="large">我的收藏</el-text>
            </el-divider>
        </div>
        <div class="infobody">
            <el-table :data="list" table-layout="fixed">
                <el-table-column  align="left">
                    <template #default="scope">
                        <el-button link style="margin-left: 10px" @click="goDetail(scope)">
                            <el-text type="info">{{ '[' + scope.row.createTime + '] [' + scope.row.type + ']'
                                }}</el-text>
                            &nbsp;{{ scope.row.title }}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column fixed="right" width="200" align="center">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="goDetail(scope)">详情</el-button>
                        <el-button type="danger" size="small" @click="del(scope)">取消收藏</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <div class="example-pagination-block" style="float: right;margin-top: 30px;" v-show="list.length">
                <el-pagination background layout="prev, pager, next" :page-count="pageCount"
                    v-model:current-page="pageNum" />
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, getCurrentInstance, defineProps, watch, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import { ElNotification, ElMessageBox, ElMessage } from 'element-plus';
import { favoriteInfoList,favorite } from '@/api/like'

const instance = getCurrentInstance()
const $store = useStore()
const $router = useRouter()
const $route = useRoute()

const list = ref([])

// 分页
let pageNum = ref<number>(1)
let pageSize = ref<number>(10)
let pageCount = ref(0)

const dialogVisible = ref(false)

const init = async () => {
    const res = await favoriteInfoList({
        pageNum: pageNum.value,
        pageSize: pageSize.value
    })
    list.value = res.rows
    pageCount.value = Math.ceil(res.total / pageSize.value)
}

const goDetail = (scope) => {
    $router.push("/postinfo/" + scope.row.id)
}

const del = async (scope) => {
    const res = await favorite(scope.row.id,scope.row.userId)
    if (res) {
        ElMessage({
            type: 'success',
            message: `取消成功`,
        })
        init()
    } else {
        ElMessage({
            type: 'warning',
            message: `取消失败`,
        })
    }
}

watch(
    () => pageNum.value,
    () => {
        init()
    }
)

init()
</script>

<style lang="scss" scoped>
.infobody {
    margin: 20px 0;
}
</style>

<script lang="ts">
export default {
    name: "MyFavorite"
}
</script>