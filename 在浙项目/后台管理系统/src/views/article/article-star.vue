<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getArticles">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="articles" highlight-current-rowstyle="width: 100%;">
			<el-table-column type="index" width="50">
			</el-table-column>
			<el-table-column prop="aTitle" label="标题" width="200">
                <template slot-scope="scope">
                <el-button type="text" size="small" @click="turnToArticle(scope.row)">{{scope.row.aTitle}}</el-button>
                </template>
			</el-table-column>
            <el-table-column prop="cName" label="城市" width="100">
			</el-table-column>
            <el-table-column prop="aAuthor" label="作者" width="100">
			</el-table-column>
            <el-table-column prop="star" label="点赞量" width="100">
			</el-table-column>
            <el-table-column prop="look" label="浏览量" width="100">
			</el-table-column>
			<el-table-column prop="gmtCreate" label="创建时间" width="200">
			</el-table-column>
      <el-table-column prop="gmtModified" label="修改时间" width="200">
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

	</section>
</template>

<script>
import util from '@/utils/table.js'
import {
  getArticleAllMsg
} from '@/api/articleTable'

export default {
  data() {
    return {
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogFormVisible: false,
      filters: {
        name: ''
      },
      articles: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: [], // 列表选中列
      editFormRules: {
        pName: [{ required: true, message: '请输入权限', trigger: 'blur' }]
      },
      // 编辑界面数据
      editForm: {
        
      }
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.page = val
      this.getArticles()
    },
    // 点击文章跳转详细权限
    turnToArticle(row){
      this.$router.push({ name: 'article-manage',query:{aId:row.aId}})
    },
    // 获取文章列表
    getArticles() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getArticleAllMsg(para).then(res => {
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.articles = res.data.data.records
        console.log(this.articles)
      })
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
    },
  mounted() {
    this.getArticles()
  }
},
  created(){
      this.getArticles()
  }}
</script>

<style scoped>

</style>