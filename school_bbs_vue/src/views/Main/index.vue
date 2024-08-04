<template>
  <div class="common-layout">
    <el-container>
      <el-aside>
        <el-affix :offset="39" style="height: 86vh">
          <div class="aside">
            <div>
              <i v-if="Object.keys(user).length === 0" class="el-icon-ali-weidenglu-touxiang"
                style="font-size: 80px;cursor: pointer;" @click="goLogin"></i>
              <MyAvatar v-if="Object.keys(user).length !== 0" />
              <div style="align-items: flex-end;margin: 20px;">
                <el-text v-if="Object.keys(user).length === 0" type="primary" id="goLogin"
                  @click="goLogin">去登录</el-text>
                <span v-if="Object.keys(user).length !== 0"
                  style="font-size: 10px;color: rgb(141, 141, 141);">当前账户：<span style="color: gray;font-size: 14px;">{{
          user.nickName? user.nickName : user.username }}</span></span>
              </div>
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
              <el-menu-item index="/joinus/detail">
                <el-icon><i-ep-PhoneFilled /></el-icon><span>入驻我们</span>
              </el-menu-item>
            </el-menu>
          </div>
        </el-affix>
      </el-aside>
      <el-main style="overflow-y: hidden;">
        <router-view />
      </el-main>
      <el-backtop target=".el-scrollbar__wrap" :right="40" :bottom="130"
        style="background-color: #409eff;color: white;width: 50px;height: 50px;" />
    </el-container>
  </div>
</template>

<script setup lang="ts">
import MyAvatar from "@/components/NavMenu/MyAvatar/index.vue";
import { ref, reactive, getCurrentInstance, onBeforeUnmount, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
const instance = getCurrentInstance()
const $router = useRouter();
const $store = useStore();

const user = reactive($store.state.user.info)

// const dialogVisible = ref(false)

const goLogin = () => {
  $router.push({ path: "/login" })
}


</script>

<style lang="scss" scoped>
body {
  overflow-y: hidden;
  overflow-x: hidden;
}

#goLogin {
  text-decoration: underline;
  cursor: pointer;
}

.el-main {
  margin: 0;
  padding: 0;
}

.aside {
  width: 90%;
  padding: 20px 0;
  text-align: center;
  border-radius: 20px;
  background-color: #F2F2F2;
  height: 100%;
}

.navMenu {
  border: 0;
}

:deep(.el-divider--horizontal) {
  margin: 10px 0;
}
</style>

<script lang="ts">
export default {
  name: "Main"
}
</script>