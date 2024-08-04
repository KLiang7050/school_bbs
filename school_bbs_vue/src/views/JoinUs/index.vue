<template>
    <div>
        <div style="display: flex;justify-content: space-between;align-items: center;">
            <el-text class="mx-1" size="large" style="font-size: 25px;">- 入驻记录 -</el-text>
            <div>
                <el-button type="info" @click="goBack">返回</el-button>
                <el-button type="danger" @click="goDetail">申请入驻</el-button>
            </div>
        </div>
    </div>
    <el-divider />
    <div>
        <el-table :data="tableData" style="width: 100%" >
            <el-table-column prop="id" label="主键" align="center"/>
            <el-table-column prop="nickname" label="申请人" align="center"/>
            <el-table-column prop="createTime" label="创建时间" align="center"/>
            <el-table-column prop="result" label="状态" align="center">
                <template #default="scope">
                    <el-text :type="scope.row.backFlag === '1'?'danger':'default'">{{ scope.row.backFlag === '1'?'回退':workflows[scope.row.status] }}</el-text>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="success"
                        @click="$router.push('/joinus/detail/' + scope.row.id + '')">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ElMessage, ElNotification } from 'element-plus'

import { getJoinList } from '@/api/user'

const $router = useRouter()
const $store = useStore()

const tableData = ref([])

const goDetail = () => {
    $router.push({ path: "/joinus/detail" })
}

const goBack = () => {
    $router.back();
}

const workflows = ref(['申请', '审核', '详谈', '归档'])

const handleList = async () => {
    const res = await getJoinList()
    if (res.code == 200) {
        tableData.value = res.data
    } else {

    }
}

handleList()
</script>

<style lang="scss" scoped>
.el-divider {
    margin: 15px 0 !important;
}
</style>

<script lang="ts">
export default {
    name: "JoinUs"
}
</script>