<template>
  <div class="dashboard-editor-container">

  <panel-group v-if="!isMaster"/>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;height: 500px;">
      <!-- <line-chart :chart-data="lineChartData" /> -->
      <h2 style="text-align: center;">公告</h2>
      <div style="text-align: center;">
        <p>{{ notice.title }}</p>
        <p v-html="notice.content"></p>
      </div>
      <div class="creator-info">
        <p class="created-by">{{ notice.createBy }}</p>
        <p class="create-time">{{ notice.createTime }}</p>
      </div>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import { getNew } from '@/api/system/notice'

export default {
  name: 'Index',
  components: {
    PanelGroup,
  },
  data() {
    return {
      notice: {
        title: "",
        content: "",
        createBy: "",
        createTime: ""
      },
      isMaster: this.$store.state.user.isMaster
    }
  },
  methods: {
    getNew() {
      getNew().then(response => {
        this.notice.title = response.data.noticeTitle
        this.notice.content = response.data.noticeContent
        this.notice.createBy = response.data.createBy
        this.notice.createTime = response.data.createTime
      })
    }
  },
  created() {
    this.getNew();
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

.creator-info {
  position: absolute;
  bottom: 16px; /* 调整以适应所需距离底部的距离 */
  right: 16px; /* 调整以适应所需距离右侧的距离 */
  display: flex;
  flex-direction: column; /* 确保子元素垂直排列 */
  justify-content: flex-end; /* 子元素沿主轴（垂直方向）居下对齐 */
  align-items: flex-end; /* 子元素沿交叉轴（水平方向）居右对齐 */
}

.created-by,
.create-time {
  margin: 0;
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
