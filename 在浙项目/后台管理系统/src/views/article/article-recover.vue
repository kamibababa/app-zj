<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getAllArticle">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="article" highlight-current-row @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="aTitle" label="标题" width="120" >
			</el-table-column>
      <el-table-column prop="aCoverImg" label="封面" width="100">
        <template slot-scope="scope">
            <a @click="showPicture(scope.row.aCoverImg)"><img :src="scope.row.aCoverImg" alt="" style="width:100px;height:100px"></a>
        </template>
			</el-table-column>
			<el-table-column prop="cName" label="城市" width="50">
			</el-table-column>
			<el-table-column prop="aAuthor" label="作者" width="50">
			</el-table-column>
      <el-table-column prop="aContent" label="内容" width="200">
          <template slot-scope="scope">
              <span >{{ getAContant(scope.row.aContent) }}</span>
          </template>
      </el-table-column>
        <el-table-column prop="aStatus" label="审核" width="80"  :formatter="formatStatus"></el-table-column>
        <el-table-column prop="gmtCreate" label="创建时间" width="90"></el-table-column>
        <el-table-column prop="gmtModified" label="修改时间" width="90"></el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="recoverData(scope.$index, scope.row)">撤回</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize" :total="total" style="float:right;">
			</el-pagination>
		</el-col>
	</section>
</template>

<script>
import {
  getArticlePageDeleted,
  clearArticle,
  batchRemoveArticle,
  recoverArticle
} from '@/api/articleTable'

export default {
  data() {
    return {
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      filters: {
        name: ''
      },
      article: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: []// 列表选中列
    }
  },
  methods: {
        // 在线状态转换
    formatStatus: function(row, column) {
      return row.aStatus === 0 ? '审核中' : row.aStatus === 1 ? '已审核' : '未知'
    },
    handleCurrentChange(val) {
      this.page = val
      this.getAllArticle()
    },
     //压缩字段
    getAContant(row){
        if(row!=null &&row.length > 40){
             return row.substring(0,40) + '...'
        }else if(row == null || row.length == 0){
            return '文字不存在,请输入...'
        }else if(row !== null && row.length<=40){
            return row
        }
    },
    // 获取用户列表
    getAllArticle() {
      const para = {
        page: this.page,
        name: this.filters.name
        }
      getArticlePageDeleted(para).then(res => {
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.article = res.data.data.records
      })
    },
    // 删除
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          clearArticle(row.aId).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getAllArticle()
          })
        })
        .catch(() => {})
    },
    recoverData(index,row) {
          this.$confirm('确认恢复文章吗？', '提示', {})
            .then(() => {
              recoverArticle(row.uId).then(res => {
                // console.log('res',res)
                this.$message({
                  message: '文章恢复成功',
                  type: 'success'
                })
                this.getAllArticle()
              })
            })
            .catch(e => {
              // 打印一下错误
              console.log(e)
            })
    },
    // 全选单选多选
    selsChange(sels) {
      this.sels = sels
    },
    // 批量删除
    batchRemove() {
      var ids = this.sels.map(item => item.id).toString()
      this.$confirm('确认删除选中记录吗？', '提示', {
        type: 'warning'
      })
        .then(() => {
          const para = { ids: ids }
          batchRemoveArticle(para).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getAllArticle()
          })
        })
        .catch(() => {})
    }
  },
  created(){
      this.getAllArticle()
  }
}
</script>

<style scoped>

</style>