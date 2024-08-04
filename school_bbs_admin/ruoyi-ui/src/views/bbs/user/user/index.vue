<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="id" prop="id">
        <el-input v-model="queryParams.id" placeholder="请输入id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input v-model="queryParams.realName" placeholder="请输入真实姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="昵称" prop="nickName">
        <el-input v-model="queryParams.nickName" placeholder="请输入昵称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="queryParams.username" placeholder="请输入用户名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号" prop="tel">
        <el-input v-model="queryParams.tel" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="头像" prop="avatar">
        <el-input v-model="queryParams.avatar" placeholder="请输入头像" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="简介" prop="introduce">
        <el-input v-model="queryParams.introduce" placeholder="请输入简介" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-date-picker clearable v-model="queryParams.birthday" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择生日">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="学号" prop="studentId">
        <el-input v-model="queryParams.studentId" placeholder="请输入学号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="学校" prop="school">
        <el-input v-model="queryParams.school" placeholder="请输入学校" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="queryParams.major" placeholder="请输入专业" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="贴子数量大于" prop="postCount">
        <el-input v-model="queryParams.postCount" placeholder="请输入贴子数量" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="获收藏数大于" prop="favoritesCount">
        <el-input v-model="queryParams.favoritesCount" placeholder="请输入获收藏数" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="获赞数大于" prop="likeCount">
        <el-input v-model="queryParams.likeCount" placeholder="请输入获赞数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="账号状态" prop="isEnable">
        <el-tooltip class="box-item" effect="dark" content="(0：已禁用，1：已启用)" placement="top-start">
          <el-input v-model="queryParams.isEnable" placeholder="请输入账号状态" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item>
      <el-form-item label="逻辑删除标识" prop="delFlag">
        <el-tooltip class="box-item" effect="dark" content="(0：未删除，1：已删除)" placement="top-start">
          <el-input v-model="queryParams.delFlag" placeholder="请输入逻辑删除标识" clearable @keyup.enter.native="handleQuery" />
        </el-tooltip>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable v-model="queryParams.createTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable v-model="queryParams.updateTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="入学时间" prop="enrollmentTime">
        <el-date-picker clearable v-model="queryParams.enrollmentTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择入学时间">
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
          v-hasPermi="['bbs:user:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['bbs:user:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['bbs:user:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['bbs:user:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="真实姓名" align="center" prop="realName" />
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="手机号" align="center" prop="tel" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <span>{{ scope.row.sex === 1 ? '男' : '女' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="头像" align="center" prop="avatar">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" style="width: 50px;height: 50px;border-radius: 50%;" />
        </template>
      </el-table-column>
      <el-table-column label="简介" align="center" prop="introduce" />
      <el-table-column label="生日" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="学校" align="center" prop="school" />
      <el-table-column label="专业" align="center" prop="major" />
      <el-table-column label="贴子数量" align="center" prop="postCount" />
      <el-table-column label="获收藏数" align="center" prop="favoritesCount" />
      <el-table-column label="获赞数" align="center" prop="likeCount" />
      <el-table-column label="账号状态" align="center" prop="isEnable">
        <template slot-scope="scope">
          <span>{{ scope.row.isEnable === 0 ? '已禁用' : '已启用' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除标识" align="center" prop="delFlag">
        <template slot-scope="scope">
          <span>{{ scope.row.delFlag === 0 ? '已删除' : '未删除' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入学时间" align="center" prop="enrollmentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollmentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['bbs:user:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['bbs:user:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="真实姓名" prop="realName">
          <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="手机号" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像" />
        </el-form-item>
        <el-form-item label="简介" prop="introduce">
          <el-input v-model="form.introduce" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable v-model="form.birthday" type="date" value-format="yyyy-MM-dd" placeholder="请选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校" />
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="请输入专业" />
        </el-form-item>
        <el-form-item label="贴子数量" prop="postCount">
          <el-input v-model="form.postCount" placeholder="请输入贴子数量" />
        </el-form-item>
        <el-form-item label="获收藏数" prop="favoritesCount">
          <el-input v-model="form.favoritesCount" placeholder="请输入获收藏数" />
        </el-form-item>
        <el-form-item label="获赞数" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入获赞数" />
        </el-form-item>
        <el-form-item label="账号状态" prop="isEnable">
          <el-tooltip class="box-item" effect="dark" content="(0：已禁用，1：已启用)" placement="top-start">
            <el-input v-model="form.isEnable" placeholder="请输入账号状态" />
          </el-tooltip>
        </el-form-item>
        <el-form-item label="逻辑删除标识" prop="delFlag">
          <el-tooltip class="box-item" effect="dark" content="(0：未删除，1：已删除)" placement="top-start">
            <el-input v-model="form.delFlag" placeholder="请输入逻辑删除标识" />
          </el-tooltip>
        </el-form-item>
        <el-form-item label="入学时间" prop="enrollmentTime">
          <el-date-picker clearable v-model="form.enrollmentTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择入学时间">
          </el-date-picker>
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
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/bbs/user/user";

export default {
  name: "User",
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
      // 用户表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        realName: null,
        nickName: null,
        username: null,
        password: null,
        tel: null,
        sex: null,
        avatar: null,
        introduce: null,
        birthday: null,
        studentId: null,
        school: null,
        schoolId: null,
        college: null,
        collegeId: null,
        major: null,
        majorId: null,
        postCount: null,
        favoritesCount: null,
        likeCount: null,
        isEnable: null,
        delFlag: null,
        createTime: null,
        updateTime: null,
        enrollmentTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
        tel: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        sex: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
        isEnable: [
          { required: true, message: "账号状态不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "逻辑删除标识不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
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
        realName: null,
        nickName: null,
        username: null,
        password: null,
        tel: null,
        sex: null,
        avatar: null,
        introduce: null,
        birthday: null,
        studentId: null,
        school: null,
        schoolId: null,
        college: null,
        collegeId: null,
        major: null,
        majorId: null,
        postCount: null,
        favoritesCount: null,
        likeCount: null,
        isEnable: null,
        delFlag: null,
        createTime: null,
        updateTime: null,
        enrollmentTime: null
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
      this.title = "添加用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户编号为"' + ids + '"的数据项？').then(function () {
        return delUser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('app/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
