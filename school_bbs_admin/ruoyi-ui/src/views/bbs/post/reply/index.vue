<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="评论id" prop="id">
        <el-input v-model="queryParams.id" placeholder="请输入评论id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="父评论id" prop="parentId">
        <el-input v-model="queryParams.parentId" placeholder="请输入父评论id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="父评论的用户id" prop="parentUserId">
        <el-input v-model="queryParams.parentUserId" placeholder="请输入父评论的userid" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="父评论的用户名" prop="parentUserName">
        <el-input v-model="queryParams.parentUserName" placeholder="请输入父评论的username" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="回复id" prop="contentId">
        <el-input v-model="queryParams.contentId" placeholder="请输入回复id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="根贴子id (0：自身为根贴)" prop="rootId">
        <el-input v-model="queryParams.rootId" placeholder="请输入根贴子id (0：自身为根贴)" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="主贴子id(0：自身为主贴)" prop="mainId">
        <el-input v-model="queryParams.mainId" placeholder="请输入主贴子id(0：自身为主贴)" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="总回复数大于" prop="replyCount">
        <el-input v-model="queryParams.replyCount" placeholder="请输入总回复数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼层" prop="floor">
        <el-input v-model="queryParams.floor" placeholder="请输入楼层" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="点赞总数大于" prop="likes">
        <el-input v-model="queryParams.likes" placeholder="请输入点赞总数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="isEnable">
        <el-tooltip class="box-item" effect="dark" content="(0：已禁用，1：已启用)" placement="top-start">
          <el-input v-model="queryParams.isEnable" placeholder="请输入状态" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item>
      <el-form-item label="逻辑删除" prop="delFlag">
        <el-tooltip class="box-item" effect="dark" content="(0：未删除，1：已删除)" placement="top-start">
          <el-input v-model="queryParams.delFlag" placeholder="请输入逻辑删除" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable v-model="queryParams.createTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable v-model="queryParams.updateTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['bbs:reply:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['bbs:reply:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['bbs:reply:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['bbs:reply:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="replyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论id" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="父评论id" align="center" prop="parentId" />
      <el-table-column label="父评论的用户id" align="center" prop="parentUserId" />
      <el-table-column label="父评论的用户名" align="center" prop="parentUserName" />
      <el-table-column label="回复id" align="center" prop="contentId" />
      <el-table-column label="根贴子id (0：自身为根贴)" align="center" prop="rootId" />
      <el-table-column label="主贴子id(0：自身为主贴)" align="center" prop="mainId" />
      <el-table-column label="总回复数" align="center" prop="replyCount" />
      <el-table-column label="楼层" align="center" prop="floor" />
      <el-table-column label="点赞总数" align="center" prop="likes" />
      <el-table-column label="状态" align="center" prop="isEnable">
        <template slot-scope="scope">
          <span>{{ scope.row.isEnable === 0 ? '已禁用' : '已启用' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除" align="center" prop="delFlag">
        <template slot-scope="scope">
          <span>{{ scope.row.delFlag === 0 ? '已删除' : '未删除' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['bbs:reply:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['bbs:reply:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改贴子回复对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="父评论id" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父评论id" />
        </el-form-item>
        <el-form-item label="父评论的用户id" prop="parentUserId">
          <el-input v-model="form.parentUserId" placeholder="请输入父评论的userid" />
        </el-form-item>
        <el-form-item label="父评论的用户名" prop="parentUserName">
          <el-input v-model="form.parentUserName" placeholder="请输入父评论的username" />
        </el-form-item>
        <el-form-item label="回复id" prop="contentId">
          <el-input v-model="form.contentId" placeholder="请输入回复id" />
        </el-form-item>
        <el-form-item label="根贴子id (0：自身为根贴)" prop="rootId">
          <el-input v-model="form.rootId" placeholder="请输入根贴子id (0：自身为根贴)" />
        </el-form-item>
        <el-form-item label="主贴子id(0：自身为主贴)" prop="mainId">
          <el-input v-model="form.mainId" placeholder="请输入主贴子id(0：自身为主贴)" />
        </el-form-item>
        <el-form-item label="总回复数" prop="replyCount">
          <el-input v-model="form.replyCount" placeholder="请输入总回复数" />
        </el-form-item>
        <el-form-item label="楼层" prop="floor">
          <el-input v-model="form.floor" placeholder="请输入楼层" />
        </el-form-item>
        <el-form-item label="点赞总数" prop="likes">
          <el-input v-model="form.likes" placeholder="请输入点赞总数" />
        </el-form-item>
        <el-form-item label="状态" prop="isEnable">
          <el-tooltip class="box-item" effect="dark" content="(0：已禁用，1：已启用)" placement="top-start">
            <el-input v-model="form.isEnable" placeholder="请输入状态" />
          </el-tooltip>
        </el-form-item>
        <el-form-item label="逻辑删除" prop="delFlag">
          <el-tooltip class="box-item" effect="dark" content="(0：未删除，1：已删除)" placement="top-start">
            <el-input v-model="form.delFlag" placeholder="请输入逻辑删除" />
          </el-tooltip>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReply, getReply, delReply, addReply, updateReply } from "@/api/bbs/post/reply";

export default {
  name: "Reply",
  data() {
    return {
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
      // 贴子回复表格数据
      replyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        userId: null,
        parentId: null,
        parentUserId: null,
        parentUserName: null,
        contentId: null,
        rootId: null,
        mainId: null,
        replyCount: null,
        floor: null,
        likes: null,
        isEnable: null,
        delFlag: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "父评论id不能为空", trigger: "blur" }
        ],
        parentUserId: [
          { required: true, message: "父评论的userid不能为空", trigger: "blur" }
        ],
        parentUserName: [
          { required: true, message: "父评论的username不能为空", trigger: "blur" }
        ],
        contentId: [
          { required: true, message: "回复id不能为空", trigger: "blur" }
        ],
        rootId: [
          { required: true, message: "根贴子id (0：自身为根贴)不能为空", trigger: "blur" }
        ],
        mainId: [
          { required: true, message: "主贴子id(0：自身为主贴)不能为空", trigger: "blur" }
        ],
        floor: [
          { required: true, message: "楼层不能为空", trigger: "blur" }
        ],
        isEnable: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "逻辑删除不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询贴子回复列表 */
    getList() {
      this.loading = true;
      listReply(this.queryParams).then(response => {
        this.replyList = response.rows;
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
        parentId: null,
        parentUserId: null,
        parentUserName: null,
        contentId: null,
        rootId: null,
        mainId: null,
        replyCount: null,
        floor: null,
        likes: null,
        isEnable: null,
        delFlag: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加贴子回复";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReply(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改贴子回复";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReply(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除贴子回复编号为"' + ids + '"的数据项？').then(function () {
        return delReply(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/reply/export', {
        ...this.queryParams
      }, `reply_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
