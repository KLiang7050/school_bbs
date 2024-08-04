<template>
    <div>
        <el-dialog v-model="dialogVisible" title="提示" width="500">
            <span>当前未登录，无法使用该功能</span>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="goLogin">去登录</el-button>
                    <el-button @click="handleGoLogin">
                        取消
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, watch } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
const $router = useRouter()
const $store = useStore()

const dialogVisible = ref($store.state.basic.isShowLogin);

const goLogin = () => {
    dialogVisible.value = false
    $router.push("/login")
}

const handleGoLogin = () => {
    dialogVisible.value = false
    // $router.push("/")
}

watch(
    () => $store.state.basic.isShowLogin,
    (val) => {
        dialogVisible.value = val
    },
)
</script>

<style scoped></style>

<script lang="ts">
export default {
    name: "GoLogin"
}
</script>