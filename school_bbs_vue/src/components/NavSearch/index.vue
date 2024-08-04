<template>
    <div id="navSearchContainer">
        <div class="wrapper">
            <div>
                {{ school }}
                <!-- <el-popover placement="bottom-start" width="18%" trigger="click" auto-close="false">
                    <template #reference>
                        <el-text id="changeSchool" type="info" @click="handleSchool">[切换学校]</el-text>
                    </template>
                    <div>
                        <el-input v-model="filterText" placeholder="学校名" />
                        <el-text></el-text>
                        <el-tree ref="treeRef" 
                        class="filter-tree" 
                        :data="data" 
                        :props="defaultProps" 
                        :filter-node-method="filterNode" 
                        style="height: 170px;overflow-y: scroll;" 
                        empty-text="加载中......"
                        :highlight-current="true"
                        />
                    </div>
                    <div>
                    </div>
                </el-popover> -->
            </div>
            <Search />
            <el-button type="primary" size="large" @click="publish"><i-ep-EditPen />&nbsp;去发布</el-button>
        </div>
    </div>
</template>
<script setup lang="ts">
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { ElTree, ElNotification } from 'element-plus'
import Search from "@/components/NavSearch/Search/index.vue"
import schoolConf from '@/config/school'
import { reqSchoolList } from '@/api/user'

const router = useRouter()

let school = ref<String>(schoolConf.name)

interface Tree {
    [key: string]: any
}

const filterText = ref('')
const treeRef = ref<InstanceType<typeof ElTree>>()

const hovered = ref(false)
const loading = ref(false)

const defaultProps = {
    id: 'id',
    children: 'children',
    label: 'name',
}

let data = ref<Tree[]>([])

const filterNode = (value: string, data: Tree) => {
    if (!value) return true
    return data.name.includes(value)
}

const handleSchool = async () => {
    if (hovered.value) {
        return
    }
    const res = await reqSchoolList()
    
    if (res.code == 200) {
        data.value = res.data.provinceList
        hovered.value = true
    } else {
        ElNotification({
            type: 'error',
            message: res.msg
        })
    }
}

const publish = () => {
    router.push("/edit")
}


watch(filterText, (val) => {
    treeRef.value!.filter(val)
})
</script>
<style lang="scss" scoped>
#changeSchool:hover {
    color: #409EFF;
    cursor: pointer
}

#navSearchContainer {
    box-sizing: border-box;
    width: 100%;
    height: 80px;
    background-color: #F2F2F2;
    border-radius: 10px;
    padding: 5px;
}

.wrapper {
    display: flex;
    height: 100%;
    justify-content: space-between;
    align-items: center;
    width: 98%;
    margin: 0 auto;
}
</style>

<script lang="ts">
export default {
    name: "NavSearch"
}
</script>