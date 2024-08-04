<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主键" prop="id">
        <el-input v-model="queryParams.id" placeholder="请输入主键" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="学校名" prop="school">
        <el-input v-model="queryParams.school" placeholder="请输入学校名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="申请明细" prop="description">
        <el-input v-model="queryParams.description" placeholder="请输入申请明细" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable v-model="queryParams.createTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="负责人" prop="auditor">
        <el-input v-model="queryParams.auditor" placeholder="请输入负责人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="结果详情" prop="descriptionRes">
        <el-input v-model="queryParams.descriptionRes" placeholder="请输入结果详情" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-tooltip class="box-item" effect="dark" content="(0：申请，1：审核，2：详谈，3：归档)" placement="top-start">
          <el-input v-model="queryParams.status" placeholder="请输入状态" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item>
      <!-- <el-form-item label="回退状态" prop="backFlag">
        <el-tooltip class="box-item" effect="dark" content="(1：正常，2：回退)" placement="top-start">
          <el-input v-model="queryParams.backFlag" placeholder="请输入回退状态" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['bbs:joinus:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['bbs:joinus:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['bbs:joinus:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['bbs:joinus:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="joinusList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="学校名" align="center" prop="school" />
      <el-table-column label="文件url" align="center" prop="fileUrl">
        <template slot-scope="scope">
          <!-- <DescriptionHTML :msg="scope.row.fileUrl" /> -->
          <div v-html="scope.row.fileUrl"></div>
        </template>
      </el-table-column>
      <el-table-column label="申请明细" align="center" prop="description" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center" prop="auditor" />
      <el-table-column label="结果详情" align="center" prop="descriptionRes">
        <template slot-scope="scope">
          <!-- <DescriptionHTML :msg="scope.row.descriptionRes" /> -->
          <div v-html="scope.row.descriptionRes"></div>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <span>{{scope.row.status=='0'?'申请':scope.row.status=='1'?'审核':scope.row.status=='2'?'详谈':'归档'}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="回退状态" align="center" prop="backFlag" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['bbs:joinus:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['bbs:joinus:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改入驻申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="学校名" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校名" />
        </el-form-item>
        <el-form-item label="文件url" prop="fileUrl">
          <el-input v-model="form.fileUrl" type="textarea" placeholder="请输入内容" :rows="6" />
        </el-form-item>
        <el-form-item label="申请明细" prop="description">
          <el-input v-model="form.description" placeholder="请输入申请明细" />
        </el-form-item>
        <el-form-item label="负责人" prop="auditor">
          <el-input v-model="form.auditor" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="结果详情" prop="descriptionRes">
          <!-- <el-input v-model="form.descriptionRes" placeholder="请输入结果详情" /> -->
          <editor v-model="form.descriptionRes" :min-height="140"></editor>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-tooltip class="box-item" effect="dark" content="(0：申请，1：审核，2：详谈，3：归档)" placement="top-start">
            <el-input v-model="form.status" placeholder="请输入状态" />
          </el-tooltip>
        </el-form-item>
        <!-- <el-form-item label="回退状态" prop="backFlag">
          <el-tooltip class="box-item" effect="dark" content="(1：正常，2：回退)" placement="top-start">
            <el-input v-model="form.backFlag" placeholder="请输入回退状态" />
          </el-tooltip>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listJoinus, getJoinus, delJoinus, addJoinus, updateJoinus } from "@/api/bbs/user/joinus";
import Description from "@/components/Description/index.vue"
import DescriptionHTML from "@/components/DescriptionHTML/index.vue"

export default {
  name: "Joinus",
  components: {
    Description,
    DescriptionHTML
  },
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
      // 入驻申请表格数据
      joinusList: [],
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
        school: null,
        fileUrl: null,
        description: null,
        createTime: null,
        auditor: null,
        descriptionRes: null,
        status: null,
        backFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询入驻申请列表 */
    getList() {
      this.loading = true;
      listJoinus(this.queryParams).then(response => {
        this.joinusList = response.rows;
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
        school: null,
        fileUrl: null,
        description: null,
        createTime: null,
        auditor: null,
        descriptionRes: null,
        status: null,
        backFlag: null
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
      this.title = "添加入驻申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getJoinus(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改入驻申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateJoinus(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJoinus(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除入驻申请编号为"' + ids + '"的数据项？').then(function () {
        return delJoinus(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/joinus/export', {
        ...this.queryParams
      }, `joinus_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
