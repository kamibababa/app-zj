<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getMessages">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="messages" highlight-current-rowstyle="width: 100%;">
			<el-table-column type="index" width="100">
			</el-table-column>
			<el-table-column prop="mContent" label="留言" width="200">
                <template slot-scope="scope">
                <el-button type="text" size="small" @click="turnToArticle(scope.row)">{{scope.row.mContent}}</el-button>
                </template>
			</el-table-column>
            <el-table-column prop="uName" label="留言人" width="200">
			</el-table-column>
            <el-table-column prop="star" label="点赞量" width="200">
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
import {
  getMessageStar
} from '@/api/messageTable'

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
      messages: [],
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
      this.getMessages()
    },
    // 点击文章跳转详细权限
    turnToArticle(row){
      this.$router.push({ name: 'message-manage',query:{aId:row.mId}})
    },
    // 获取文章列表
    getMessages() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getMessageStar(para).then(res => {
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.messages = res.data.data.records
        console.log(this.messages)
      })
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
    },
  mounted() {
    this.getMessages()
  }
},
created(){
    this.getMessages()
}
}
</script>

<style scoped>

</style>