<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRole">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="roles" highlight-current-row @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="120">
			</el-table-column>
			<el-table-column type="index" width="150">
			</el-table-column>
			<el-table-column prop="rname" label="角色" width="200" cell-click="">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="turnToRoleToPermission(scope.row)">{{scope.row.rname}}</el-button>
        </template>
			</el-table-column>
			<el-table-column prop="gmtCreate" label="创建时间" width="200">
			</el-table-column>
      <el-table-column prop="gmtModified" label="修改时间" width="200">
			</el-table-column>
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
				<el-form-item label="角色" prop="rname">
					<el-input v-model="editForm.rname" auto-complete="off"></el-input>
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
  getRoleListPage,
  removeRole,
  batchRemoveRole,
  editRole,
  addRole
} from '@/api/roleTable'

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
      roles: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: [], // 列表选中列
      editFormRules: {
        rName: [{ required: true, message: '请输入角色', trigger: 'blur' }]
      },
      // 编辑界面数据
      editForm: {
        rname: ''
      },
      addFormVisible: false, // 新增界面是否显示
      addFormRules: {
        rName: [{ required: true, message: '请输入角色', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.page = val
      this.getRole()
    },
    // 点击角色跳转详细权限
    turnToRoleToPermission(row){
      this.$router.push({ name: 'role-permission',query:{rid:row.rid}})
    },
    // 获取用户列表
    getRole() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getRoleListPage(para).then(res => {
        //console.log('getRole',res)
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.roles = res.data.data.records
      })
    },
    // 删除
    handleDel(index, row) {
      //console.log('row',row.rid)
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          removeRole(row.rid).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getRole()
          })
        })
        .catch(() => {})
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
    },
    // 显示新增界面
    handleAdd() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.editForm = {
        rname:''
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
              editRole(para).then(res => {
                console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getRole()
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
              console.log(para)
              addRole(para).then(res => {
                //console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getRole()
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
          batchRemoveRole(para).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getRole()
          })
        })
        .catch(() => {})
    }
  },
  mounted() {
    this.getRole()
  }
}
</script>

<style scoped>

</style>