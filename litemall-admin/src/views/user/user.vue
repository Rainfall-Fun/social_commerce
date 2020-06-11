<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.user_id" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户id" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSarchUserBYId">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchAllUser">查找全部</el-button>
      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >导出
      </el-button>
    </div>
    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="用户ID" prop="userId" />
      <el-table-column align="center" label="用户名" prop="username" />
      <el-table-column align="center" label="手机号码" prop="phoneNumber" />
      <el-table-column align="center" label="性别" prop="userSexy">
        <template slot-scope="scope">
          <span v-if="scope.row.userSexy">男</span>
          <span v-else>女</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="邀请人" prop="invitor" />
      <el-table-column align="center" label="银行卡号" prop="bankNumber" />
      <el-table-column align="center" label="是否有效" prop="isValid">
        <template slot-scope="scope">
          <span v-if="scope.row.isValid">有效</span>
          <span v-else>无效</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="等级" prop="grade" />
      <el-table-column align="center" label="会员积分" prop="score" />
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleDelete (scope)">删除</el-button>
          <el-button size="mini" @click="handleUpdate(scope)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination
      v-show="total>1"
      :current-page="listQuery.page"
      :page-sizes="[10, 20,40, 100]"
      :page-size="listQuery.limit"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!--  弹窗-->
    <el-dialog title="会员信息" :visible.sync="dialogTableVisible">
      <el-form :model="one">
        <el-form-item label="用户ID">
          <el-input v-model="one.userId" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="one.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="one.phoneNumber" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="one.userSexy" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邀请人">
          <el-input v-model="one.invitor" autocomplete="off" />
        </el-form-item>
        <el-form-item label="银行卡号">
          <el-input v-model="one.bankNumber" autocomplete="off" />
        </el-form-item>
        <el-form-item label="是否有效">
          <el-input v-model="one.isValid" autocomplete="off" />
        </el-form-item>
        <el-form-item label="等级">
          <el-input v-model="one.grade" autocomplete="off" />
        </el-form-item>
        <el-form-item label="会员积分">
          <el-input v-model="one.score" autocomplete="off" />
        </el-form-item>
        <el-button size="mini" @click="updateAllUserINfo">修改</el-button>
      </el-form>
    </el-dialog>
    <el-dialog title="删除成功请点击对应查询按钮确认删除情况" :visible.sync="resultTable" />
  </div>
</template>

<script>
import { fetchList } from '@/api/user'
import { deleteuserinfo, searchAll, update } from '../../api/user'

export default {
  name: 'User',
  data() {
    return {
      dialogTableVisible: false,
      resultTable: false,
      list: [],
      one: '',
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        user_id: undefined,
        sort: 'userId',
        order: 'desc'
      },
      downloadLoading: false
    }
  },
  created() {
    this.sarchUserBYId()
  },
  methods: {
    handleSarchUserBYId() {
      this.listQuery.page = 1
      this.sarchUserBYId()
    },
    sarchUserBYId: function() {
      fetchList(this.listQuery).then(response => {
        this.list = response.data.data.data.list
        this.listLoading = false
        this.total = response.data.data.data.total
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleSearchAllUser() {
      this.listQuery.user_id = ''
      this.searchAllUser()
    },
    searchAllUser: function() {
      searchAll(this.listQuery).then(response => {
        this.list = response.data.data.data.list
        this.total = response.data.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.searchAllUser()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.searchAllUser()
    },
    handleUpdate(item) {
      this.one = item.row
      this.dialogTableVisible = true
    },
    updateAllUserINfo() {
      update(this.one)
      this.dialogTableVisible = false
      this.searchAll()
    },
    handleDelete: function(item) {
      this.$confirm('确认删除吗？')
        .then(_ => {
          this.one = item.row
          deleteuserinfo(this.one)
          this.resultTable = true
        })
        .catch(_ => {
        })
    },
    handleDownload() {
      this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['用户ID', '用户名', '手机号码', '性别', '邀请人', '银行卡号', '是否有效', '等级', '会员积分']
          const filterVal = ['userId', 'username', 'phoneNumber', 'userSexy', 'invitor', 'bankNumber', 'isValid', 'grade', 'score']
          excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户信息')
          this.downloadLoading = false
        })
    }
  }
}
</script>
