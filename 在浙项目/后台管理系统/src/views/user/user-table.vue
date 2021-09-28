<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users" highlight-current-row @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="uUsername" label="姓名" width="120" :formatter="formatName">
			</el-table-column>
			<el-table-column prop="uSex" label="性别" width="120" :formatter="formatSex">
			</el-table-column>
			<el-table-column prop="uEmail" label="邮箱" width="120">
			</el-table-column>
      <el-table-column prop="rName" label="角色" width="100">
			</el-table-column>
			<el-table-column prop="uAge" label="年龄" width="100" :formatter="formatAge">
			</el-table-column>
			<el-table-column prop="uTelephone" label="手机号" width="120"  :formatter="formatTelephone">
			</el-table-column>
      <el-table-column prop="status" label="状态" width="100" :formatter="formatStatus"></el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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

		<!--编辑界面-->
		<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="姓名" prop="uUsername">
					<el-input v-model="editForm.uUsername" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-radio-group v-model="editForm.uSex">
						<el-radio class="radio" :label=1>男</el-radio>
						<el-radio class="radio" :label=0>女</el-radio>
					</el-radio-group>
				</el-form-item>
        <el-form-item label="邮箱" prop="uEmail">
					<el-input v-model="editForm.uEmail" type="email" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="角色">
					<el-select v-model="editForm.uRoleId" :placeholder="editForm.rName" >
           <el-option :label="item.rname" :value="item.rid" :key="item.rid" v-for="item in roles" ></el-option>
          </el-select>
				</el-form-item>
				<el-form-item label="年龄">
					<el-input-number v-model="editForm.uAge" :min="0" :max="120"></el-input-number>
				</el-form-item>
        <el-form-item label="手机" prop="uEmail">
					<el-input v-model="editForm.uTelephone" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="状态">
					<el-radio-group v-model="editForm.status">
						<el-radio class="radio" :label=1>在线</el-radio>
						<el-radio class="radio" :label=0>离线</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
			 <el-button @click.native="dialogFormVisible=false">取消</el-button>
			  <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
import util from '@/utils/table.js'
import {
  getUserListPage,
  removeUser,
  batchRemoveUser,
  editUser,
  addUser,
  getUserList
} from '@/api/userTable'
//import func from 'vue-editor-bridge'
//import roleTableVue from '../role/role-table.vue'

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
      roles:[],
      users: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: [], // 列表选中列
      editFormRules: {
        uUsername: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
      },
      // 编辑界面数据
      editForm: {
        uId: '0',
        uUsername: '',
        uSex: 1,
        uAge: 0,
        uEmail:'',
        uRoleId:1,
        rName:'',
        uTelephone: '',
        status: 0
      },
      addFormVisible: false, // 新增界面是否显示
      addFormRules: {
        uUsername: [{ required: true, message: '请输入姓名', trigger: 'blur' }]
      }
    }
  },
  methods: {
    //姓名是否为空
    formatName:function(row,column){
      return row.uUsername ? row.uUsername : '未录入'
    },
    // 性别显示转换
    formatSex: function(row, column) {
      return row.uSex === 1 ? '男' : row.uSex === 0 ? '女' : '未知'
    },
    //年龄是否为空
    formatAge:function(row,column){
        return row.uAge ? row.uAge : '未录入'
    },
    //手机号是否为空
    formatTelephone:function(row,column){
        return row.uTelephone ? row.uTelephone : '未录入'
    },
    // 在线状态转换
    formatStatus: function(row, column) {
      return row.status === 0 ? '离线' : row.status === 1 ? '在线' : '未知'
    },
    handleCurrentChange(val) {
      this.page = val
      this.getUsers()
    },
    // 获取用户列表
    getUsers() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getUserListPage(para).then(res => {
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.users = res.data.data.records
        console.log(this.users)
      })
    },
    // 删除
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          removeUser(row.uId).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getUsers()
          })
        })
        .catch(() => {})
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      getUserList().then(res=>{
        this.roles=res.data.data
        console.log(this.roles)
      })

    },
    // 显示新增界面
    handleAdd() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.editForm = {
        uId: '0',
        uUsername: '',
        uSex: 1,
        rId:1,
        uAge: 0,
        uEmail:'',
        uTelephone: '',
        status: 0
      }
    },
    // 编辑
    updateData() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {})
            .then(() => {
              const para = Object.assign({}, this.editForm)
              console.log('para',para)
              editUser(para).then(res => {
                // console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getUsers()
              })
            })
            .catch(e => {
              // 打印一下错误
              console.log(e)
            })
        }
      })
    },
    // 新增
    createData: function() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {})
            .then(() => {
             // this.editForm.id = (parseInt(Math.random() * 100)).toString() // mock a id
              //console.log('id',this.editForm)
              const para = Object.assign({}, this.editForm)
              //console.log(para)
              addUser(para).then(res => {
                //console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getUsers()
              })
            })
            .catch(e => {
              // 打印一下错误
              console.log(e)
            })
        }
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
          batchRemoveUser(para).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getUsers()
          })
        })
        .catch(() => {})
    }
  },
  mounted() {
    this.getUsers()
  }
}
</script>

<style scoped>

</style>