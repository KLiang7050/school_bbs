<template>
  <div class="navContainer">
    <el-aside width="240px" class="aside navContainer">
      <MyAvatar />
      <div style="align-items: flex-end;margin: 20px;">
        <span style="font-size: 10px;color: rgb(141, 141, 141);">当前账户：<span
            style="color: gray;font-size: 14px;">{{ info.username }}</span></span>
      </div>
      <el-menu :default-active="$router.currentRoute.value.fullPath" :default-openeds="['/userinfo']"
        class="el-menu-vertical-demo navMenu" background-color="#F2F2F2" :router=true>
        <el-menu-item index="/homepage">
          <el-icon><i-ep-Menu /></el-icon><span>进入贴吧</span>
        </el-menu-item>
        <el-divider class="navMenuDivider" />
        <el-sub-menu index="/userinfo">
          <template #title>
            <el-icon><i-ep-UserFilled /></el-icon><span>个人中心</span>
          </template>
          <el-menu-item index="/userinfo/info">
            <el-icon><i-ep-User /></el-icon><span>个人信息</span>
          </el-menu-item>
          <el-menu-item index="/userinfo/myfavorite">
            <el-icon><i-ep-Star /></el-icon><span>我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/userinfo/replyMe">
            <el-icon><i-ep-ChatDotSquare /></el-icon><span>回复我的</span>
          </el-menu-item>
          <el-menu-item index="/userinfo/mypost">
            <el-icon><i-ep-Tickets /></el-icon><span>我的贴子</span>
          </el-menu-item>
        </el-sub-menu>
        <el-divider />
        <el-menu-item index="/linkme">
          <el-icon><i-ep-PhoneFilled /></el-icon><span>联系我们</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
  </div>
</template>
<script setup lang="ts">
import MyAvatar from "@/components/NavMenu/MyAvatar/index.vue";
import { ref, reactive, computed } from "vue";
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import { ElNotification, FormInstance } from 'element-plus';
const $store = useStore()
const $router = useRouter()
const $route = useRoute()

const info = computed(() => $store.state.user.info)
</script>
<style lang="scss" scoped>
.aside {
  padding-top: 20px;
  text-align: center;
  border-radius: 20px 20px 20px 20px;
  background-color: #F2F2F2;
  height: 100%;
}

.navMenu {
  border: 0;
}

.navContainer {
  height: 645px;
}

:deep(.el-divider--horizontal) {
  margin: 10px 0;
}
</style>
<style></style>

<script lang="ts">
export default {
  name: "NavMenu"
}
</script>