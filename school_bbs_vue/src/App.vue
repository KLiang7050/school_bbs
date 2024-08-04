<template>
  <div>
    <el-scrollbar ref="scrollbar" height="100vh" @scroll="handleScroll">
      <router-view v-if="isRouter" style="width: 95%;padding: 2% 2%;" />
    </el-scrollbar>
    <!-- <GoLogin /> -->
  </div>
</template>

<script setup lang="ts">
import { ref, provide, nextTick, getCurrentInstance, onBeforeUnmount, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import tokenUtils from './utils/tokenUtils';
import { ElNotification, ElMessageBox } from 'element-plus';
import * as _ from 'lodash';
// import GoLogin from './components/goLogin/index.vue'
const instance = getCurrentInstance()
const $router = useRouter()
const $store = useStore()

let loading = ref<boolean>(false); // 初始化加载状态为 false
const isRouter = ref<boolean>(true)
const reload = (address) => {
  isRouter.value = false
  nextTick(() => {
    isRouter.value = true
    $router.push(address?address:"/")
  })
}
provide("reload", reload)

const scrollbar = ref(null)

const handleScroll = async () => {
  let wrap = scrollbar.value.wrapRef;
  let routeName =  $router.currentRoute.value.name
  if (routeName !== 'homepage' || loading.value) return;
  // 获取滚动区域的实际滚动高度
  const scrollTop = wrap.scrollTop;
  // 获取滚动区域的总高度
  const scrollHeight = wrap.scrollHeight;
  // 获取滚动区域可视区域的高度
  const clientHeight = wrap.clientHeight;

  // 判断是否滑到底部
  if (scrollTop + clientHeight + 5 >= scrollHeight) {
    loading.value = true;
    // 在这里执行到达底部时的操作
    instance.proxy.$Bus.emit('getPostList')
    // 等待2秒后再进行下一步操作
    await new Promise(resolve => setTimeout(resolve, 1000));
    loading.value = false;
  }
}
const handleScrollDebounced = _.debounce(handleScroll, 100);

// 在组件卸载时清除防抖函数并重置 loading 状态
onBeforeUnmount(() => {
  handleScrollDebounced.cancel();
  loading.value = false;
});
</script>

<style lang="scss" scoped></style>

<style>
body {
  overflow-y: hidden;
  overflow-x: hidden;
}
</style>

<script lang="ts">
export default {
  name: "App"
}
</script>