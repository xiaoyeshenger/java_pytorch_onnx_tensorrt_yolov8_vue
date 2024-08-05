<template>
  <div>
    <!--Layout布局-->
    <el-row>
      <el-col :span="24">
        <el-row :gutter="20">
          <el-col :span="6">
            <!--搜索区域-->
            <el-input
              placeholder="请输入内容"
              v-model="queryInfo.searchKey"
              clearable
              @clear="getListPage"
            >
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="getListPage"
              ></el-button>
            </el-input>
          </el-col>
          <el-col :span="2.5">
            <el-button type="primary" @click="addDialogVisible = true">添加</el-button>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24">
        <!--表格-->
        <el-table
          :data="pageList"
          border
          stripe
        >
          <el-table-column type="index" label="序号" width="55" align="center"></el-table-column>
                    <el-table-column prop="name" label="模型名称" align="center"></el-table-column>
          <el-table-column prop="modelKey" label="模型key" align="center"></el-table-column>
          <el-table-column prop="modelSerial" label="模型序列号" align="center"></el-table-column>
          <el-table-column prop="algorithmType" label="算法类型" align="center"></el-table-column>
          <el-table-column prop="coreTech" label="核心技术" align="center"></el-table-column>
          <el-table-column prop="latestTrainingTime" label="最近训练时间" align="center"></el-table-column>
          <el-table-column prop="onlineTime" label="上线时间" align="center"></el-table-column>
          <el-table-column prop="labelList" label="标签列表" align="center"></el-table-column>
          <el-table-column prop="oosUrl" label="oos访问地址" align="center"></el-table-column>
          <el-table-column prop="orderNum" label="排序号" align="center"></el-table-column>
          <el-table-column prop="status" label="状态" align="center"></el-table-column>
          <el-table-column label="操作">
            <!-- 作用域插槽 -->
            <template slot-scope="scope">
              <!--修改按钮-->
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row)"
              ></el-button>
              <!--删除按钮-->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteById(scope.row.id)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-row>
    <!--添加对象的对话框-->
    <el-dialog title="添加" :visible.sync="addDialogVisible" width="30%" @close="addDialogClosed">
      <!--内容主体区域-->
      <el-form :model="addForm" label-width="110px">
        <el-form-item label="模型名称" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="模型key" prop="modelKey">
          <el-input v-model="addForm.modelKey"></el-input>
        </el-form-item>
        <el-form-item label="算法类型" prop="algorithmType">
          <el-input v-model="addForm.algorithmType"></el-input>
        </el-form-item>
        <el-form-item label="核心技术" prop="coreTech">
          <el-input v-model="addForm.coreTech"></el-input>
        </el-form-item>
        <el-form-item label="最近训练时间" prop="latestTrainingTime">
          <el-input v-model="addForm.latestTrainingTime"></el-input>
        </el-form-item>
        <el-form-item label="上线时间" prop="onlineTime">
          <el-input v-model="addForm.onlineTime"></el-input>
        </el-form-item>
        <el-form-item label="标签列表" prop="labelList">
          <el-input v-model="addForm.labelList"></el-input>
        </el-form-item>
        <el-form-item label="文件上传" required>
          <el-upload class="upload-demo" action="1" :on-preview="handlePreview" :multiple="false"
            :limit="1" accept="*" :auto-upload="false" :on-remove="handleRemove" :on-change="handleChange"
            :before-remove="beforeRemove" :on-exceed="handleExceed" :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addObj">确 定</el-button>
      </span>
    </el-dialog>
    <!--修改用户的对话框-->
    <el-dialog title="修改" :visible.sync="editDialogVisible" width="30%">
      <!--内容主体区域-->
      <el-form :model="editForm" label-width="110px">
        <el-form-item label="模型名称" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="模型key" prop="modelKey">
          <el-input v-model="editForm.modelKey"></el-input>
        </el-form-item>
        <el-form-item label="算法类型" prop="algorithmType">
          <el-input v-model="editForm.algorithmType"></el-input>
        </el-form-item>
        <el-form-item label="核心技术" prop="coreTech">
          <el-input v-model="editForm.coreTech"></el-input>
        </el-form-item>
        <el-form-item label="最近训练时间" prop="latestTrainingTime">
          <el-input v-model="editForm.latestTrainingTime"></el-input>
        </el-form-item>
        <el-form-item label="上线时间" prop="onlineTime">
          <el-input v-model="editForm.onlineTime"></el-input>
        </el-form-item>
        <el-form-item label="标签列表" prop="labelList">
          <el-input v-model="editForm.labelList"></el-input>
        </el-form-item>
        <el-form-item label="排序号" prop="orderNum">
          <el-input v-model="editForm.orderNum"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="editForm.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="文件上传" required>
          <el-upload class="upload-demo" action="1" :on-preview="handlePreview" :multiple="false"
            :limit="1" accept="*" :auto-upload="false" :on-remove="handleRemove" :on-change="handleChange"
            :before-remove="beforeRemove" :on-exceed="handleExceed" :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateObj">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { add, deleteById, update, getById,listPage} from "@/api/algorithmModel";
export default {
  data() {
    return {
      pageList: [], // 列表
      total: 0, // 总数
      // 获取列表的参数对象
      queryInfo: {
        searchKey: "", // 查询参数
        pageNum: 1, // 当前页码
        pageSize: 10, //页面大小
      },
      addDialogVisible: false, //控制-添加对象对话框-是否一进页面就显示
      addForm: {
        name: "",
        modelKey: "",
        algorithmType: "",
        coreTech: "",
        latestTrainingTime: "",
        onlineTime: "",
        labelList: "",
        targetFileData: ""
      },
      editDialogVisible: false, // 控制-修改对象对话框-是否一进页面显示
      editForm: {
        id: "",
        name: "",
        modelKey: "",
        algorithmType: "",
        coreTech: "",
        latestTrainingTime: "",
        onlineTime: "",
        labelList: "",
        orderNum: "",
        status: "",
        targetFileData: ""
      },
      multipleSelection: [],
      ids: []
    };
  },
  created() {
    // 生命周期函数
    this.getListPage();
  },
  methods: {
    handleChange (file, fileList) {
      console.log(file, fileList, 'success');
      this.targetFileData = file.raw
    },
    handleRemove (file, fileList) {
      console.log(file, fileList);
    },
    handlePreview (file) {
      console.log(file);
    },
    handleExceed (files, fileList) {
      this.$message.warning(`您已添加了一个文件，如需替换，请先删除已添加的文件！`);
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除？`);
    },
    getListPage() {
      listPage(this.queryInfo)
        .then((res) => {
          if (res.data.code === 200) {
            this.pageList = res.data.data.list;
            this.total = res.data.data.total;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 监听 pageSize 改变的事件
    handleSizeChange(newSize) {
      // console.log(newSize)
      this.queryInfo.pageSize = newSize;
      // 重新发起请求列表
      this.getListPage();
    },
    // 监听 当前页码值 改变的事件
    handleCurrentChange(newPage) {
      // console.log(newPage)
      this.queryInfo.pageNum = newPage;
      // 重新发起请求列表
      this.getListPage();
    },
    //添加对象
    addObj() {
      let formData = new FormData();
      for (let key in this.addForm) {
        formData.append(key, this.addForm[key]);
      }
      formData.append("targetFile", this.targetFileData);
      add(formData)
        .then((res) => {
          if (res.data.code === 200) {
            this.addDialogVisible = false;
            this.getListPage();
            this.$message({
              message: "添加成功",
              type: "success",
            });
          } else {
            this.$message.error("添加失败");
          }
        })
        .catch((err) => {
          this.$message.error("添加异常");
          console.log(err);
        });
    },

    // 监听添加对话框的关闭事件
    addDialogClosed() {
      // 表单内容重置为空
      this.$refs.addFormRef.resetFields();
    },

    // 监听修改状态
    showEditDialog(obj) {
      this.editDialogVisible = true;
      //console.log("请求后接收到的响应结果:"+obj);
      this.editForm = obj;
    },
    //修改
    updateObj() {
      let formData = new FormData();
      for (let key in this.editForm) {
        formData.append(key, this.editForm[key]);
      }
      formData.append("targetFile", this.targetFileData);
      update(formData)
        .then((res) => {
          if (res.data.code === 200) {
            this.editDialogVisible = false;
            this.getListPage();
            this.$message({
              message: "修改成功",
              type: "success",
            });
          } else {
            this.$message.error("修改失败");
          }
        })
        .catch((err) => {
          this.$message.error("修改异常");
          console.loge(err);
        });
    },
    // 根据ID删除对应的信息
    async deleteById(id) {
      // 弹框 询问用户是否删除
      const confirmResult = await this.$confirm(
        "此操作将永久删除该数据, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      // 如果用户确认删除，则返回值为字符串 confirm
      // 如果用户取消删除，则返回值为字符串 cancel
      // console.log(confirmResult)
      if (confirmResult == "confirm") {
        //删除
        deleteById(id)
          .then((res) => {
            if (res.data.code === 200) {
              this.getListPage();
              this.$message({
                message: "删除成功",
                type: "success",
              });
            } else {
              this.$message.error("删除失败");
            }
          })
          .catch((err) => {
            this.$message.error("删除异常");
            console.log(err);
          });
      }
    },
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
  height: 60pt;
}
</style>
