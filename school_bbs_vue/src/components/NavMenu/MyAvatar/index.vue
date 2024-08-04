<template>
  <div style="align-items: center;justify-content: center;display: flex;">
    <!-- 头像 -->
    <router-link :to="{ path: '/userinfo/info' }">
      <el-row class="demo-avatar demo-basic" id="myavatar">
        <el-col :span="3">
          <div class="demo-basic--circle">
            <div class="block">
              <el-popover placement="right-start" width="400" trigger="hover">
                <template #default>
                  <div class="info-detail">
                    <div class="info-editor">
                      <router-link :to="{ path: `/userinfo/info` }">
                        <span>编 辑</span>
                      </router-link>
                    </div>
                    <div class="info-avatar">
                      <el-avatar :size="80" :src="user.avatar" shape="square" />
                    </div>
                    <div class="info-nickname">
                      <h3>{{ user.nickName }}</h3>
                    </div>
                    <div class="info-text">获赞数 {{ user.likeCount }}</div>
                    <div class="info-text">发帖数 {{ user.postCount }}</div>
                    <div class="info-text">个人简介: {{ user.introduce? user.introduce : '无' }}</div>
                    <div style="margin-top: 10px;">
                      <el-button @click="logoutVisible = true" type="primary" size="large">退出登录</el-button>
                    </div>
                  </div>
                </template>
                <template #reference>
                  <el-avatar :size="80" :src="user.avatar" />
                </template>
              </el-popover>
            </div>
          </div>
        </el-col>
      </el-row>
    </router-link>

    <el-dialog v-model="logoutVisible" title="提示" width="20%">
      <span style="font-size: 18px;">确认退出登录吗？</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="logoutVisible = false">取消</el-button>
          <el-button type="primary" @click="handleLogout">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, inject } from "vue";
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { ElMessage, ElNotification } from 'element-plus'
const $router = useRouter()
const $store = useStore()
const reload = inject("reload")

// 是否显示dialog
let dialogVisible = ref<boolean>(false);
// 是否显示退出登录提示dialog
let logoutVisible = ref(false);

const user = reactive($store.state.user.info)

// 退出登录
const handleLogout = async () => {
  logoutVisible.value = false;
  try {
    await $store.dispatch("Logout")
    reload()
  } catch (err) {}
}

</script>

<style lang="scss" scoped>
.changeInfo {
  text-decoration: underline #409eff;
  color: #409eff;
  user-select: none;
}

.changeInfo:hover {
  cursor: pointer;
}

#myavatar {
  cursor: pointer
}

.info-detail {
  display: flex;
  width: 100%;
  padding: 10px;
  flex-direction: column;
  align-items: center;

  .info-editor {
    width: 100%;
    text-align: right;
    margin-right: 50px;
    font-size: 14px;
    color: #409eff;

    span {
      cursor: pointer;
    }
  }

  .info-nickname,
  .info-text {
    margin-bottom: 10px;
  }
}

.avatar-uploader {
  margin-bottom: 25px;
}

.avatar-uploader .el-upload {
  cursor: pointer;
  position: relative;
  // overflow: hidden;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
</style>

<script lang="ts">
export default {
  name: "MyAvatar"
}
</script>