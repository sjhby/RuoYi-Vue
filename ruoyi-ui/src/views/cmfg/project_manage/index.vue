<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工期" prop="rlsTime">
        <el-date-picker clearable v-model="queryParams.rlsTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="数量" prop="numT">
        <el-input v-model="queryParams.numT" placeholder="请输入数量" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item v-hasRole="['admin']" label="需求方ID" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入需求方ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="是否完成" prop="finish">
        <el-input v-model="queryParams.finish" placeholder="请输入是否完成" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['cmfg:project_manage:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['cmfg:project_manage:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['cmfg:project_manage:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['cmfg:project_manage:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="project_manageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目ID" align="center" prop="projId" />
      <el-table-column label="工期" align="center" prop="rlsTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rlsTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center" prop="numT" />
      <el-table-column label="需求方ID" align="center" prop="userId" />
      <el-table-column label="是否完成" align="center" prop="finish" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['cmfg:project_manage:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['cmfg:project_manage:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改项目管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工期" prop="rlsTime">
          <el-date-picker clearable v-model="form.rlsTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-hasRole="['admin']" label="数量" prop="numT">
          <el-input v-model="form.numT" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item v-hasRole="['admin']" label="需求方ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入需求方ID" />
        </el-form-item>
        <el-form-item v-hasRole="['admin']" label="是否完成" prop="finish">
          <el-input v-model="form.finish" placeholder="请输入是否完成" />
        </el-form-item>
        <el-divider content-position="center">任务管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddTask">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteTask">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="taskList" :row-class-name="rowTaskIndex" @selection-change="handleTaskSelectionChange"
          ref="task">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="服务类型" prop="serType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.serType" placeholder="请选择服务类型">
                <el-option v-for="dict in dict.type.service_type" :key="dict.value" :label="dict.label"
                  :value="dict.value" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="工期" prop="proceTime" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.proceTime" placeholder="请输入工期" />
            </template>
          </el-table-column>
          <el-table-column label="资源数量" prop="proceVol" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.proceVol" placeholder="请输入资源数量" />
            </template>
          </el-table-column>
          <el-table-column v-hasRole="['admin']" label="是否完成" prop="succs" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.succs" placeholder="请输入是否完成" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listProject_manage,
    getProject_manage,
    delProject_manage,
    addProject_manage,
    updateProject_manage
  } from "@/api/cmfg/project_manage";
  import {
    getUserProfile
  } from "@/api/system/user";
  import {
    Logger
  } from "runjs/lib/common";

  export default {
    name: "Project_manage",
    dicts: ['service_type'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 子表选中数据
        checkedTask: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 项目管理表格数据
        project_manageList: [],
        // 任务管理表格数据
        taskList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          rlsTime: null,
          numT: null,
          userId: null,
          finish: null
        },
        // 用户信息
        user: {},
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          rlsTime: [{
            required: true,
            message: "工期不能为空",
            trigger: "blur"
          }],
          numT: [{
            required: true,
            message: "数量不能为空",
            trigger: "blur"
          }],
          userId: [{
            required: true,
            message: "需求方ID不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getUser();
    },
    methods: {
      /** 查询项目管理列表 */
      getList() {
        this.loading = true;
        listProject_manage(this.queryParams).then(response => {
          this.project_manageList = response.rows;
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
          projId: null,
          rlsTime: null,
          numT: null,
          userId: null,
          finish: null
        };
        this.taskList = [];
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
        this.ids = selection.map(item => item.projId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加项目管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const projId = row.projId || this.ids
        getProject_manage(projId).then(response => {
          this.form = response.data;
          this.taskList = response.data.taskList;
          this.open = true;
          this.title = "修改项目管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.taskList = this.taskList;
            this.form.numT = this.taskList.length;
            if (this.form.projId != null) {
              updateProject_manage(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addProject_manage(this.form).then(response => {
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
        const projIds = row.projId || this.ids;
        this.$modal.confirm('是否确认删除项目管理编号为"' + projIds + '"的数据项？').then(function() {
          return delProject_manage(projIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 任务管理序号 */
      rowTaskIndex({
        row,
        rowIndex
      }) {
        row.index = rowIndex + 1;
      },
      /** 任务管理添加按钮操作 */
      handleAddTask() {
        let obj = {};
        obj.serType = "";
        obj.proceTime = "";
        obj.proceVol = "";
        obj.succs = "";
        this.taskList.push(obj);
      },
      /** 任务管理删除按钮操作 */
      handleDeleteTask() {
        if (this.checkedTask.length == 0) {
          this.$modal.msgError("请先选择要删除的任务管理数据");
        } else {
          const taskList = this.taskList;
          const checkedTask = this.checkedTask;
          this.taskList = taskList.filter(function(item) {
            return checkedTask.indexOf(item.index) == -1
          });
        }
      },
      /** 复选框选中数据 */
      handleTaskSelectionChange(selection) {
        this.checkedTask = selection.map(item => item.index)
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('cmfg/project_manage/export', {
          ...this.queryParams
        }, `project_manage_${new Date().getTime()}.xlsx`)
      },
      /**查询登录用户信息**/
      getUser() {
        getUserProfile().then(response => {
          this.user = response.data;
          this.queryParams.userId = this.user.userId;
          this.form.userId = this.user.userId;
          this.getList();
        });
      }
    }
  };
</script>
