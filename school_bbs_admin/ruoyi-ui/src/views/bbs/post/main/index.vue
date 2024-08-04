<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="评论id" prop="id">
        <el-input v-model="queryParams.id" placeholder="请输入评论id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="类型id" prop="typeId">
        <el-select v-model="queryParams.typeId" placeholder="请选择类型id" clearable>
          <el-option v-for="dict in dict.type.dict_post_type" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="回复id" prop="contentId">
        <el-input v-model="queryParams.contentId" placeholder="请输入回复id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="总回复数大于" prop="replyCount">
        <el-input v-model="queryParams.replyCount" placeholder="请输入总回复数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="点赞总数大于" prop="likes">
        <el-input v-model="queryParams.likes" placeholder="请输入点赞总数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="收藏数大于" prop="favorites">
        <el-input v-model="queryParams.favorites" placeholder="请输入收藏数" clearable @keyup.enter.native="handleQuery" />
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['bbs:main:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['bbs:main:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['bbs:main:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['bbs:main:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mainList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论id" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="类型id" align="center" prop="typeId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dict_post_type" :value="scope.row.typeId" />
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="回复id" align="center" prop="contentId" />
      <el-table-column label="总回复数" align="center" prop="replyCount" />
      <el-table-column label="点赞总数" align="center" prop="likes" />
      <el-table-column label="收藏数" align="center" prop="favorites" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['bbs:main:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['bbs:main:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改贴子主贴对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="类型id" prop="typeId">
          <el-select v-model="form.typeId" placeholder="请选择类型id">
            <el-option v-for="dict in dict.type.dict_post_type" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="回复id" prop="contentId">
          <el-input v-model="form.contentId" placeholder="请输入回复id" />
        </el-form-item>
        <el-form-item label="总回复数" prop="replyCount">
          <el-input v-model="form.replyCount" placeholder="请输入总回复数" />
        </el-form-item>
        <el-form-item label="点赞总数" prop="likes">
          <el-input v-model="form.likes" placeholder="请输入点赞总数" />
        </el-form-item>
        <el-form-item label="收藏数" prop="favorites">
          <el-input v-model="form.favorites" placeholder="请输入收藏数" />
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
import { listMain, getMain, delMain, addMain, updateMain } from "@/api/bbs/post/main";

export default {
  name: "Main",
  dicts: ['dict_post_type'],
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
      // 贴子主贴表格数据
      mainList: [],
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
        typeId: null,
        title: null,
        contentId: null,
        replyCount: null,
        likes: null,
        favorites: null,
        isEnable: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "类型id不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        contentId: [
          { required: true, message: "回复id不能为空", trigger: "blur" }
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
    /** 查询贴子主贴列表 */
    getList() {
      this.loading = true;
      listMain(this.queryParams).then(response => {
        this.mainList = response.rows;
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
      this.title = "添加贴子主贴";
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMain(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMain(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除贴子主贴编号为"' + ids + '"的数据项？').then(function () {
        return delMain(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/main/export', {
        ...this.queryParams
      }, `main_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
