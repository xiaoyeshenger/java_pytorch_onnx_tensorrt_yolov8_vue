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
                  <el-table-column prop="pushTime" label="推送时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.pushTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="pushDate" label="推送时间字符串" align="center"></el-table-column>
          <el-table-column prop="status" label="操作状态(0=失败,1=成功)" align="center">
            <template slot-scope="scope">
              <span class="tag-js" v-if="scope.row.status">
                  {{scope.row.status?'启用':'停用'}}
              </span>
              <span class="tag-js" v-if="!scope.row.status">
                  {{scope.row.status?'启用':'停用'}}
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="latestData" label="是否是最新数据(0=不是,1=是)" align="center"></el-table-column>
          <el-table-column prop="methodName" label="方法名称" align="center"></el-table-column>
          <el-table-column prop="httpReqUrl" label="http请求地址" align="center"></el-table-column>
          <el-table-column prop="httpReqHeader" label="http请求头" align="center"></el-table-column>
          <el-table-column prop="httpReqParam" label="http请求参数" align="center"></el-table-column>
          <el-table-column prop="httpResult" label="http返回结果" align="center"></el-table-column>
          <el-table-column prop="keyWord" label="关键字(比如ID)" align="center"></el-table-column>
          <el-table-column prop="errorMsg" label="错误信息" align="center"></el-table-column>
          <el-table-column prop="taskNo" label="任务号" align="center"></el-table-column>
          <el-table-column prop="modelNo" label="模型序列号" align="center"></el-table-column>
          <el-table-column prop="algorithmModelName" label="模型名称" align="center"></el-table-column>
          <el-table-column prop="customerNo" label="客户号" align="center"></el-table-column>
          <el-table-column prop="customerName" label="客户名称" align="center"></el-table-column>
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
        <el-form-item label="推送时间" prop="pushTime">
          <el-date-picker v-model="addForm.推送时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="推送时间字符串" prop="pushDate">
          <el-input v-model="addForm.pushDate"></el-input>
        </el-form-item>
        <el-form-item label="操作状态(0=失败,1=成功)" prop="status">
          <el-radio-group v-model="addForm.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否是最新数据(0=不是,1=是)" prop="latestData">
          <el-input v-model="addForm.latestData"></el-input>
        </el-form-item>
        <el-form-item label="方法名称" prop="methodName">
          <el-input v-model="addForm.methodName"></el-input>
        </el-form-item>
        <el-form-item label="http请求地址" prop="httpReqUrl">
          <el-input v-model="addForm.httpReqUrl"></el-input>
        </el-form-item>
        <el-form-item label="http请求头" prop="httpReqHeader">
          <el-input v-model="addForm.httpReqHeader"></el-input>
        </el-form-item>
        <el-form-item label="http请求参数" prop="httpReqParam">
          <el-input v-model="addForm.httpReqParam"></el-input>
        </el-form-item>
        <el-form-item label="http返回结果" prop="httpResult">
          <el-input v-model="addForm.httpResult"></el-input>
        </el-form-item>
        <el-form-item label="关键字(比如ID)" prop="keyWord">
          <el-input v-model="addForm.keyWord"></el-input>
        </el-form-item>
        <el-form-item label="错误信息" prop="errorMsg">
          <el-input v-model="addForm.errorMsg"></el-input>
        </el-form-item>
        <el-form-item label="任务号" prop="taskNo">
          <el-input v-model="addForm.taskNo"></el-input>
        </el-form-item>
        <el-form-item label="模型序列号" prop="modelNo">
          <el-input v-model="addForm.modelNo"></el-input>
        </el-form-item>
        <el-form-item label="模型名称" prop="algorithmModelName">
          <el-input v-model="addForm.algorithmModelName"></el-input>
        </el-form-item>
        <el-form-item label="客户号" prop="customerNo">
          <el-input v-model="addForm.customerNo"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="addForm.customerName"></el-input>
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
        <el-form-item label="推送时间" prop="pushTime">
          <el-date-picker v-model="addForm.推送时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="推送时间字符串" prop="pushDate">
          <el-input v-model="addForm.pushDate"></el-input>
        </el-form-item>
        <el-form-item label="操作状态(0=失败,1=成功)" prop="status">
          <el-radio-group v-model="addForm.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否是最新数据(0=不是,1=是)" prop="latestData">
          <el-input v-model="addForm.latestData"></el-input>
        </el-form-item>
        <el-form-item label="方法名称" prop="methodName">
          <el-input v-model="addForm.methodName"></el-input>
        </el-form-item>
        <el-form-item label="http请求地址" prop="httpReqUrl">
          <el-input v-model="addForm.httpReqUrl"></el-input>
        </el-form-item>
        <el-form-item label="http请求头" prop="httpReqHeader">
          <el-input v-model="addForm.httpReqHeader"></el-input>
        </el-form-item>
        <el-form-item label="http请求参数" prop="httpReqParam">
          <el-input v-model="addForm.httpReqParam"></el-input>
        </el-form-item>
        <el-form-item label="http返回结果" prop="httpResult">
          <el-input v-model="addForm.httpResult"></el-input>
        </el-form-item>
        <el-form-item label="关键字(比如ID)" prop="keyWord">
          <el-input v-model="addForm.keyWord"></el-input>
        </el-form-item>
        <el-form-item label="错误信息" prop="errorMsg">
          <el-input v-model="addForm.errorMsg"></el-input>
        </el-form-item>
        <el-form-item label="任务号" prop="taskNo">
          <el-input v-model="addForm.taskNo"></el-input>
        </el-form-item>
        <el-form-item label="模型序列号" prop="modelNo">
          <el-input v-model="addForm.modelNo"></el-input>
        </el-form-item>
        <el-form-item label="模型名称" prop="algorithmModelName">
          <el-input v-model="addForm.algorithmModelName"></el-input>
        </el-form-item>
        <el-form-item label="客户号" prop="customerNo">
          <el-input v-model="addForm.customerNo"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="addForm.customerName"></el-input>
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
import { parseTime } from '@/utils/ruoyi'
import { add, deleteById, update, getById,listPage} from "@/api/httpPushLog";
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
        pushTime: "",
        pushDate: "",
        status: "",
        latestData: "",
        methodName: "",
        httpReqUrl: "",
        httpReqHeader: "",
        httpReqParam: "",
        httpResult: "",
        keyWord: "",
        errorMsg: "",
        taskNo: "",
        modelNo: "",
        algorithmModelName: "",
        customerNo: "",
        customerName: "",
      },
      editDialogVisible: false, // 控制-修改对象对话框-是否一进页面显示
      editForm: {
        pushTime: "",
        pushDate: "",
        status: "",
        latestData: "",
        methodName: "",
        httpReqUrl: "",
        httpReqHeader: "",
        httpReqParam: "",
        httpResult: "",
        keyWord: "",
        errorMsg: "",
        taskNo: "",
        modelNo: "",
        algorithmModelName: "",
        customerNo: "",
        customerName: "",
      },
      multipleSelection: [],
      ids: [],
      fileList: [],
      typelist:[{id:1,value:"吸烟"},{id:2,value:"安全帽"},{id:3,value:"人脸"}]
    };
  },
  created() {
    // 生命周期函数
    this.getListPage();
  },
  methods: {
    //默认显示时分秒，此处传入pattern {y}-{m}-{d}即只显示年月日
    parseTime(timestamp) {
      return parseTime(timestamp,"{y}-{m}-{d}");
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
      add(this.addForm)
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
      update(this.editForm)
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
