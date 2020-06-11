<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="请输入用户反馈ID" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchSuggestById">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearchAllSuggest">查找全部</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="200px" height="200px" label="反馈ID" prop="userId" />
      <el-table-column align="center" label="反馈类型" prop="suggestionType" />
      <el-table-column align="center" label="反馈内容" prop="Suggestion" />
      <el-table-column align="center" label="时间" prop="time" />
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
import { SearchAllSuggest, SearchSuggest } from '../../api/user' // Secondary package based on el-pagination
export default {
  name: 'Feedback',
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        sort: 'add_time',
        order: 'desc',
        id: undefined
      },
      downloadLoading: false,
      isAll: true
    }
  },
  created() {
    this.handleSearchSuggestById()
  },
  methods: {
    handleSearchSuggestById() {
      this.isAll = false
      this.listQuery.page = 1
      this.SearchSuggestBYid()
    },
    SearchSuggestBYid() {
      this.listLoading = false
      SearchSuggest(this.listQuery).then(response => {
        this.list = response.data.data.data.list
        this.total = response.data.data.data.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    handleSearchAllSuggest() {
      this.listQuery.id = ''
      this.isAll = true
      this.listQuery.page = 1
      this.SearchAllSuggest()
    },
    SearchAllSuggest() {
      this.listLoading = true
      SearchAllSuggest(this.listQuery).then(response => {
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
        this.SearchAllSuggest()
      } else {
        this.SearchSuggestBYid()
      }
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      if (this.isAll) {
        this.SearchAllSuggest()
      } else {
        this.SearchSuggestBYid()
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['反馈ID', '反馈类型', '反馈内容', '时间']
        const filterVal = ['userId', 'suggestionType', 'Suggestion', 'time']
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '意见反馈信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
