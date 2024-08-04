<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain size="mini" @click="getList"><i class="el-icon-refresh-left"> 刷新</i></el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="贴子内容id" align="center" prop="contentId" />
      <el-table-column label="贴子内容" align="center" prop="content">
        <template slot-scope="scope">
          <el-button type="text" @click="showHandle(scope.row.content)">查看内容</el-button>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="isHandle">
        <template slot-scope="scope">
          <el-tag type="danger">{{ handleSHType(scope.row.isHandle) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核信息" align="center" prop="msg" />
      <el-table-column label="违规词" align="center" prop="words" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="success" @click="handleSwichChange(scope.row.id, 'pass')">通过</el-button>
          <el-button type="danger" @click="handleSwichChange(scope.row.id, 'ban')">封禁</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="70%" center>
      <div v-html="content"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关 闭</el-button>
      </span>
    </el-dialog>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
  </div>
</template>

<script>
import { getOutList, pass, ban } from "@/api/bbs/post/detectionlog";

export default {
  name: "Unaudited",
  dicts: ['dict_post_type'],
  data() {
    return {
      defaultValue: '0',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 贴子主贴表格数据
      list: [],
      listTemp: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      dialogVisible: false,
      content: ""
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询贴子主贴列表 */
    getList() {
      this.loading = true;
      getOutList(this.queryParams).then(response => {
        this.list = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        type: null,
        typeId: null,
        title: null,
        contentId: null,
        replyCount: null,
        likes: null,
        favorites: null,
        isEnable: null,
        delFlag: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMain(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改贴子主贴";
      });
    },
    showHandle(content) {
      this.dialogVisible = true
      this.content = content
    },
    async handleSwichChange(id, type) {
      let res = null
      if (type === 'pass') {
        res = await pass(id)
      } else {
        res = await ban(id)
      }
      if (res.code === 200) {
        const indexToRemove = this.list.findIndex(row => row.id === id);
        if (indexToRemove !== -1) {
          // 从 list 中移除该行
          this.list.splice(indexToRemove, 1);

          // 更新表格视图
          this.$forceUpdate();

          this.$message({
            message: '更新成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '未找到要移除的行',
            type: 'warning'
          });
        }
      } else {
        this.$message({
          message: '更新失败',
          type: 'warning'
        });
      }
    },
    handleSHType(isHandle) {
      if (isHandle === '2') {
        return '不合规'
      } else if (isHandle === '3') {
        return '疑似'
      } else if (isHandle === '4') {
        return '审核失败'
      }
      return '未知'
    }
  }
};
</script>
