<template>
    <div style="padding: 0 20px;">
        <div>
            <el-divider content-position="left" class="divider" style="margin-top: 25px;">
                <el-text class="mx-1" type="info" size="large">我的帖子</el-text>
            </el-divider>
        </div>
        <div class="infobody">
            <el-table :data="list" style="width: 100%" table-layout="fixed">
                <el-table-column align="left">
                    <template #default="scope">
                        <el-button link style="margin-left: 10px" @click="goDetail(scope)">
                            <el-text type="info">{{ '[' + scope.row.createTime + '] [' + scope.row.type + ']'
                                }}</el-text>
                            &nbsp;{{ scope.row.title }}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="isEnable" width="120" align="center">
                    <template #default="scope">
                        <span style="margin-left: 10px">状态：{{ handleIsEnable(scope.row.isEnable) }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="likes" width="80" align="center">
                    <template #default="scope">
                        <el-tag type="danger" style="width: 55px;">
                            <i class="el-icon-ali-xihuan"></i>&nbsp;{{ scope.row.likes }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="favorites" width="80" align="center">
                    <template #default="scope">
                        <el-tag type="success" style="width: 55px;">
                            <i class="el-icon-ali-006pinglunhuifu"></i>&nbsp;{{ scope.row.favorites }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column fixed="right" width="200" align="center">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="goDetail(scope)">详情</el-button>
                        <el-button type="success" size="small" @click="goEdit(scope)">编辑</el-button>
                        <el-button type="danger" size="small" @click="del(scope)">删除</el-button>
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
import { getMyPostList, delPost } from '@/api/post'
import { favorite } from '@/api/like'

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
    const res = await getMyPostList({
        pageNum: pageNum.value,
        pageSize: pageSize.value
    })
    list.value = res.rows
    pageCount.value = Math.ceil(res.total / pageSize.value)
}

const goDetail = (scope) => {
    $router.push("/postinfo/" + scope.row.id)
}

const goEdit = (scope) => {
    $router.push("/edit/" + scope.row.id)
}

const del = (scope) => {
    ElMessageBox.confirm('确认删除该贴吗？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
        callback: async (action) => {
            const res = await delPost(scope.row.id)
            if (res) {
                ElMessage({
                    type: 'success',
                    message: `删除成功`,
                })
                init()
            } else {
                ElMessage({
                    type: 'warning',
                    message: `删除失败`,
                })
            }
        },
    })
}

const handleIsEnable = (isEnable) => {
    if (isEnable === '0') {
        return '封禁'
    } else if (isEnable === '1') {
        return '正常'
    } else if (isEnable === '2') {
        return '待审核'
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
    margin: 20px 0px;
}
</style>

<script lang="ts">
export default {
    name: "MyPost"
}
</script>