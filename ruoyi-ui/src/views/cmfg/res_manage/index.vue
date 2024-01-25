<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="最大数量" prop="maxVol">
        <el-input
          v-model="queryParams.maxVol"
          placeholder="请输入最大数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成本" prop="cost">
        <el-input
          v-model="queryParams.cost"
          placeholder="请输入成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质量" prop="quality">
        <el-input
          v-model="queryParams.quality"
          placeholder="请输入质量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供商ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入提供商ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属服务ID" prop="serId">
        <el-input
          v-model="queryParams.serId"
          placeholder="请输入所属服务ID"
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
          v-hasPermi="['cmfg:res_manage:add']"
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
          v-hasPermi="['cmfg:res_manage:edit']"
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
          v-hasPermi="['cmfg:res_manage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cmfg:res_manage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="res_manageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资源ID" align="center" prop="resId" />
      <el-table-column label="所需服务类型" align="center" prop="serType" />
      <el-table-column label="最大数量" align="center" prop="maxVol" />
      <el-table-column label="成本" align="center" prop="cost" />
      <el-table-column label="质量" align="center" prop="quality" />
      <el-table-column label="提供商ID" align="center" prop="userId" />
      <el-table-column label="所属服务ID" align="center" prop="serId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cmfg:res_manage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cmfg:res_manage:remove']"
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

    <!-- 添加或修改资源对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="最大数量" prop="maxVol">
          <el-input v-model="form.maxVol" placeholder="请输入最大数量" />
        </el-form-item>
        <el-form-item label="成本" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入成本" />
        </el-form-item>
        <el-form-item label="质量" prop="quality">
          <el-input v-model="form.quality" placeholder="请输入质量" />
        </el-form-item>
        <el-form-item label="提供商ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入提供商ID" />
        </el-form-item>
        <el-form-item label="所属服务ID" prop="serId">
          <el-input v-model="form.serId" placeholder="请输入所属服务ID" />
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
import { listRes_manage, getRes_manage, delRes_manage, addRes_manage, updateRes_manage } from "@/api/cmfg/res_manage";

export default {
  name: "Res_manage",
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
      // 资源表格数据
      res_manageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serType: null,
        maxVol: null,
        cost: null,
        quality: null,
        userId: null,
        serId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        serType: [
          { required: true, message: "所需服务类型不能为空", trigger: "change" }
        ],
        maxVol: [
          { required: true, message: "最大数量不能为空", trigger: "blur" }
        ],
        cost: [
          { required: true, message: "成本不能为空", trigger: "blur" }
        ],
        quality: [
          { required: true, message: "质量不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "提供商ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资源列表 */
    getList() {
      this.loading = true;
      listRes_manage(this.queryParams).then(response => {
        this.res_manageList = response.rows;
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
        resId: null,
        serType: null,
        maxVol: null,
        cost: null,
        quality: null,
        userId: null,
        serId: null
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
      this.ids = selection.map(item => item.resId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资源";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const resId = row.resId || this.ids
      getRes_manage(resId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资源";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.resId != null) {
            updateRes_manage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRes_manage(this.form).then(response => {
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
      const resIds = row.resId || this.ids;
      this.$modal.confirm('是否确认删除资源编号为"' + resIds + '"的数据项？').then(function() {
        return delRes_manage(resIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cmfg/res_manage/export', {
        ...this.queryParams
      }, `res_manage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
