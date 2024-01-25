<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工期" prop="proceTime">
        <el-input
          v-model="queryParams.proceTime"
          placeholder="请输入工期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资源数量" prop="proceVol">
        <el-input
          v-model="queryParams.proceVol"
          placeholder="请输入资源数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否完成" prop="succs">
        <el-input
          v-model="queryParams.succs"
          placeholder="请输入是否完成"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目ID" prop="projId">
        <el-input
          v-model="queryParams.projId"
          placeholder="请输入项目ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['cmfg:task_manage:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cmfg:task_manage:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cmfg:task_manage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cmfg:task_manage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="task_manageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="服务类型" align="center" prop="serType" />
      <el-table-column label="工期" align="center" prop="proceTime" />
      <el-table-column label="资源数量" align="center" prop="proceVol" />
      <el-table-column label="是否完成" align="center" prop="succs" />
      <el-table-column label="项目ID" align="center" prop="projId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cmfg:task_manage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cmfg:task_manage:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改任务管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工期" prop="proceTime">
          <el-input v-model="form.proceTime" placeholder="请输入工期" />
        </el-form-item>
        <el-form-item label="资源数量" prop="proceVol">
          <el-input v-model="form.proceVol" placeholder="请输入资源数量" />
        </el-form-item>
        <el-form-item label="是否完成" prop="succs">
          <el-input v-model="form.succs" placeholder="请输入是否完成" />
        </el-form-item>
        <el-form-item label="项目ID" prop="projId">
          <el-input v-model="form.projId" placeholder="请输入项目ID" />
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
import { listTask_manage, getTask_manage, delTask_manage, addTask_manage, updateTask_manage } from "@/api/cmfg/task_manage";

export default {
  name: "Task_manage",
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
      // 任务管理表格数据
      task_manageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serType: null,
        proceTime: null,
        proceVol: null,
        succs: null,
        projId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        serType: [
          { required: true, message: "服务类型不能为空", trigger: "change" }
        ],
        proceTime: [
          { required: true, message: "工期不能为空", trigger: "blur" }
        ],
        proceVol: [
          { required: true, message: "资源数量不能为空", trigger: "blur" }
        ],
        succs: [
          { required: true, message: "是否完成不能为空", trigger: "blur" }
        ],
        projId: [
          { required: true, message: "项目ID不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务管理列表 */
    getList() {
      this.loading = true;
      listTask_manage(this.queryParams).then(response => {
        this.task_manageList = response.rows;
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
        taskId: null,
        serType: null,
        proceTime: null,
        proceVol: null,
        succs: null,
        projId: null
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getTask_manage(taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateTask_manage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask_manage(this.form).then(response => {
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
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除任务管理编号为"' + taskIds + '"的数据项？').then(function() {
        return delTask_manage(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cmfg/task_manage/export', {
        ...this.queryParams
      }, `task_manage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
