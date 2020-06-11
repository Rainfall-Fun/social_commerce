<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户ID" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSerchUserCollect">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchAllUserCollect">查找全部</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" min-width="100px" label="收藏ID" prop="collectId" />
      <el-table-column align="center" min-width="100px" label="用户ID" prop="userId" />
      <el-table-column align="center" min-width="100px" label="商品ID" prop="valueId" />
      <el-table-column align="center" min-width="100px" label="添加时间" prop="addTime" />
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
import { searchAllusercollect, searchusercollect } from '../../api/user' // Secondary package based on el-pagination

export default {
  name: 'Collect',
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        userId: undefined,
        valueId: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      downloadLoading: false,
      isAll: true
    }
  },
  created() {
    this.SerchUserCollect()
  },
  methods: {
    handleSerchUserCollect() {
      this.isAll = false
      this.listQuery.page = 1
      this.SerchUserCollect()
    },
    SerchUserCollect() {
      this.listLoading = true
      searchusercollect(this.listQuery).then(response => {
        this.list = response.data.data.data.list
        this.total = response.data.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleSearchAllUserCollect() {
      this.listQuery.userId = ''
      this.isAll = true
      this.listQuery.page = 1
      this.searchAllUserCollect()
    },
    searchAllUserCollect: function() {
      searchAllusercollect(this.listQuery).then(response => {
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
        this.searchAllUserCollect()
      } else {
        this.SerchUserCollect()
      }
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      if (this.isAll) {
        this.searchAllUserCollect()
      } else {
        this.SerchUserCollect()
      }
    },

    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['用户ID', '商品ID', '添加时间']
        const filterVal = ['userId', 'valueId', 'addTime']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '用户收藏信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
