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
                  <el-table-column prop="taskNum" label="任务号" align="center"></el-table-column>
          <el-table-column prop="algorithmModelId" label="模型id" align="center"></el-table-column>
          <el-table-column prop="algorithmModelName" label="模型名称" align="center"></el-table-column>
          <el-table-column prop="customerId" label="客户id" align="center"></el-table-column>
          <el-table-column prop="customerName" label="客户名称" align="center"></el-table-column>
          <el-table-column prop="videoBaseInfo" label="原始视频基本信息(协议，带宽，图像大小)json" align="center"></el-table-column>
          <el-table-column prop="videoPlayUrl" label="原始视频流播放地址" align="center"></el-table-column>
          <el-table-column prop="computingVideoPlayUrl" label="实时计算的视频流播放地址" align="center"></el-table-column>
          <el-table-column prop="firstExecTime" label="首次执行时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.firstExecTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="latestExecTime" label="最近执行时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.latestExecTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="alarmAmount" label="告警次数" align="center"></el-table-column>
          <el-table-column prop="latestAlarmTime" label="最近告警时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.latestAlarmTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="orderNum" label="排序号" align="center"></el-table-column>
          <el-table-column prop="taskStatus" label="任务状态(0=停用,1=启用)" align="center">
            <template slot-scope="scope">
              <span class="tag-js" v-if="scope.row.taskStatus">
                  {{scope.row.taskStatus?'启用':'停用'}}
              </span>
              <span class="tag-js" v-if="!scope.row.taskStatus">
                  {{scope.row.taskStatus?'启用':'停用'}}
              </span>
            </template>
          </el-table-column>
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
        <el-form-item label="任务号" prop="taskNum">
          <el-input v-model="addForm.taskNum"></el-input>
        </el-form-item>
        <el-form-item label="模型id" prop="algorithmModelId">
          <el-input v-model="addForm.algorithmModelId"></el-input>
        </el-form-item>
        <el-form-item label="模型名称" prop="algorithmModelName">
          <el-input v-model="addForm.algorithmModelName"></el-input>
        </el-form-item>
        <el-form-item label="客户id" prop="customerId">
          <el-input v-model="addForm.customerId"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="addForm.customerName"></el-input>
        </el-form-item>
        <el-form-item label="原始视频基本信息(协议，带宽，图像大小)json" prop="videoBaseInfo">
          <el-input v-model="addForm.videoBaseInfo"></el-input>
        </el-form-item>
        <el-form-item label="原始视频流播放地址" prop="videoPlayUrl">
          <el-input v-model="addForm.videoPlayUrl"></el-input>
        </el-form-item>
        <el-form-item label="实时计算的视频流播放地址" prop="computingVideoPlayUrl">
          <el-input v-model="addForm.computingVideoPlayUrl"></el-input>
        </el-form-item>
        <el-form-item label="首次执行时间" prop="firstExecTime">
          <el-date-picker v-model="addForm.首次执行时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最近执行时间" prop="latestExecTime">
          <el-date-picker v-model="addForm.最近执行时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="告警次数" prop="alarmAmount">
          <el-input v-model="addForm.alarmAmount"></el-input>
        </el-form-item>
        <el-form-item label="最近告警时间" prop="latestAlarmTime">
          <el-date-picker v-model="addForm.最近告警时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="排序号" prop="orderNum">
          <el-input v-model="addForm.orderNum"></el-input>
        </el-form-item>
        <el-form-item label="任务状态(0=停用,1=启用)" prop="taskStatus">
          <el-radio-group v-model="addForm.taskStatus">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
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
        <el-form-item label="任务号" prop="taskNum">
          <el-input v-model="addForm.taskNum"></el-input>
        </el-form-item>
        <el-form-item label="模型id" prop="algorithmModelId">
          <el-input v-model="addForm.algorithmModelId"></el-input>
        </el-form-item>
        <el-form-item label="模型名称" prop="algorithmModelName">
          <el-input v-model="addForm.algorithmModelName"></el-input>
        </el-form-item>
        <el-form-item label="客户id" prop="customerId">
          <el-input v-model="addForm.customerId"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="addForm.customerName"></el-input>
        </el-form-item>
        <el-form-item label="原始视频基本信息(协议，带宽，图像大小)json" prop="videoBaseInfo">
          <el-input v-model="addForm.videoBaseInfo"></el-input>
        </el-form-item>
        <el-form-item label="原始视频流播放地址" prop="videoPlayUrl">
          <el-input v-model="addForm.videoPlayUrl"></el-input>
        </el-form-item>
        <el-form-item label="实时计算的视频流播放地址" prop="computingVideoPlayUrl">
          <el-input v-model="addForm.computingVideoPlayUrl"></el-input>
        </el-form-item>
        <el-form-item label="首次执行时间" prop="firstExecTime">
          <el-date-picker v-model="addForm.首次执行时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最近执行时间" prop="latestExecTime">
          <el-date-picker v-model="addForm.最近执行时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="告警次数" prop="alarmAmount">
          <el-input v-model="addForm.alarmAmount"></el-input>
        </el-form-item>
        <el-form-item label="最近告警时间" prop="latestAlarmTime">
          <el-date-picker v-model="addForm.最近告警时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="排序号" prop="orderNum">
          <el-input v-model="addForm.orderNum"></el-input>
        </el-form-item>
        <el-form-item label="任务状态(0=停用,1=启用)" prop="taskStatus">
          <el-radio-group v-model="addForm.taskStatus">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker v-model="addForm.创建时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
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
import { add, deleteById, update, getById,listPage} from "@/api/algorithmTask";
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
        taskNum: "",
        algorithmModelId: "",
        algorithmModelName: "",
        customerId: "",
        customerName: "",
        videoBaseInfo: "",
        videoPlayUrl: "",
        computingVideoPlayUrl: "",
        firstExecTime: "",
        latestExecTime: "",
        alarmAmount: "",
        latestAlarmTime: "",
        orderNum: "",
        taskStatus: "",
      },
      editDialogVisible: false, // 控制-修改对象对话框-是否一进页面显示
      editForm: {
        id: "",
        taskNum: "",
        algorithmModelId: "",
        algorithmModelName: "",
        customerId: "",
        customerName: "",
        videoBaseInfo: "",
        videoPlayUrl: "",
        computingVideoPlayUrl: "",
        firstExecTime: "",
        latestExecTime: "",
        alarmAmount: "",
        latestAlarmTime: "",
        orderNum: "",
        taskStatus: "",
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
