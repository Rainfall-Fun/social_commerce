<template>
  <div class="app-container calendar-list-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户ID" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchHistoryById">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchAllUserHistory">查找全部</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="搜索ID" prop="searchId" sortable />
      <el-table-column align="center" min-width="100px" label="用户ID" prop="userId" />
      <el-table-column align="center" min-width="100px" label="关键字" prop="keyWords" />
      <el-table-column align="center" min-width="100px" label="添加时间" prop="addTime" />
    </el-table>
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

import { searchAllUserHistory, searchHistoryBYid } from '../../api/user'

export default {
  name: 'History',
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        userId: undefined,
        keyword: undefined,
        sort: 'add_time',
        order: 'desc',
        isAll: true
      },
      downloadLoading: false
    }
  },
  created() {
    this.SearchHistoryBYid()
  },
  methods: {
    handleSearchHistoryById() {
      this.isAll = false
      this.listQuery.page = 1
      this.SearchHistoryBYid()
    },
    SearchHistoryBYid() {
      this.listLoading = true
      searchHistoryBYid(this.listQuery)
        .then(response => {
          this.list = response.data.data.data.list
          this.total = response.data.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleSearchAllUserHistory() {
      this.listQuery.userId = ''
      this.isAll = true
      this.listQuery.page = 1
      this.SearchAllUserHistory()
    },
    SearchAllUserHistory() {
      this.listLoading = true
      searchAllUserHistory(this.listQuery)
        .then(response => {
          this.list = response.data.data.data.list
          this.total = response.data.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      if (this.isAll) {
        this.SearchAllUserHistory()
      } else {
        this.SearchHistoryBYid()
      }
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      if (this.isAll) {
        this.SearchAllUserHistory()
      } else {
        this.SearchHistoryBYid()
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['搜索ID', '用户ID', '关键字', '添加时间']
        const filterVal = ['searchId', 'userId', 'keyWords', 'addTime']
        excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          '用户搜索历史信息'
        )
        this.downloadLoading = false
      })
    }
  }
}
</script>
