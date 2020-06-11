<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.user_id" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户id" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchUserAdressBYId">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchAllUserAdress">查找全部</el-button>
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
      <el-table-column align="center" label="收货用户名" prop="username" />
      <el-table-column align="center" label="收货地址编码" prop="adressId" />
      <el-table-column align="center" label="手机号码" prop="tel" />
      <el-table-column align="center" label="省" prop="province" />
      <el-table-column align="center" label="市" prop="city" />
      <el-table-column align="center" label="区" prop="country" />
      <el-table-column align="center" label="详细地址" prop="adress" />
      <el-table-column align="center" label="是否为默认地址" prop="isDefault">
        <template slot-scope="scope">
          <span v-if="scope.row.isDefault">是</span>
          <span v-else>否</span>
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
  </div>
</template>

<script>
import { searchAlluseradress, searchuseradress } from '../../api/user'
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
      isAll: true,
      downloadLoading: false
    }
  },
  created() {
    this.searchUserAdressBYId()
  },
  methods: {
    handleSearchUserAdressBYId() {
      this.isAll = false
      this.listQuery.page = 1
      this.searchUserAdressBYId()
    },
    searchUserAdressBYId: function() {
      searchuseradress(this.listQuery).then(response => {
        this.list = response.data.data.data.list
        this.listLoading = false
        this.total = response.data.data.data.total
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleSearchAllUserAdress() {
      this.listQuery.user_id = ''
      this.isAll = true
      this.searchAllUserAdress()
    },
    searchAllUserAdress: function() {
      searchAlluseradress(this.listQuery).then(response => {
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
      if (this.isAll) {
        this.searchAllUserAdress()
      } else {
        this.searchUserAdressBYId()
      }
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      if (this.isAll) {
        this.searchAllUserAdress()
      } else {
        this.searchUserAdressBYId()
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户ID', '收货用户名', '收货地址编码', '手机号码', '省', '市', '区', '详细地址', '是否为默认地址']
        const filterVal = ['userId', 'username', 'adressId', 'tel', 'province', 'city', 'country', 'adress', 'isDefault']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户地址信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
