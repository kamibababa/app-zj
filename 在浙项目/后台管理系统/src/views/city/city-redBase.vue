<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRedBases">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="redbases" highlight-current-row @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="rName" label="基地名" width="120" >
			</el-table-column>
            <el-table-column prop="rImg" label="图片" width="100">
                <template slot-scope="scope">
                    <a @click="showPicture(scope.row.rImg)"><img :src="scope.row.rImg" alt="" style="width:100px;height:100px"></a>
                </template>
			</el-table-column>
			<el-table-column prop="cName" label="城市" width="120">
			</el-table-column>
			<el-table-column prop="rSynopsis" label="基地简介" width="200">
                <template slot-scope="scope">
                    <span >{{ getRSynopsis(scope.row.rSynopsis) }}</span>
                </template>
			</el-table-column>
            <el-table-column prop="rDetail" label="基地详情" width="200">
                <template slot-scope="scope">
                    <span >{{ getRDetail(scope.row.rDetail) }}</span>
                </template>
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
		<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="90%">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="基地名" prop="rName">
					<el-input v-model="editForm.rName" auto-complete="off" ></el-input>
				</el-form-item>
                <el-form-item label="城市" >
					<el-select v-model="editForm.rCityId" :placeholder="editForm.cName" >
                  <el-option :label="item.cname" :value="item.cid" :key="item.cid" v-for="item in cities" ></el-option>
          </el-select>
				</el-form-item>
        <el-upload
        class="upload-demo"
        action="http://localhost:8081/oss/upload"
        :file-list="fileList"
        :on-success="handleUpload"
        :on-remove="handleRemove"
        list-type="picture">
        <h4 style="text-align:center;width:100px;display:inline-block;">图片</h4>
        <el-button size="small" type="primary" @click="handleUpload">点击上传</el-button>
        <span slot="tip" class="el-upload__tip"  style="text-align:center;">只能上传jpg/png文件，且不超过500kb</span>
      </el-upload>
       <h4 style="text-align:center;width:100px;display:inline-block;">基地简介</h4>
      <el-input type="textarea" v-model="editForm.rSynopsis"></el-input>
      <h4 style="text-align:center;width:100px;display:inline-block;">基地详情</h4>
         <editor v-model="editForm.rDetail" id="editor5" @val="keyValue">  </editor>
			</el-form>
			<div slot="footer" class="dialog-footer">
			 <el-button @click.native="dialogFormVisible=false">取消</el-button>
			  <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
        <el-button v-else type="primary" @click="updateData">修改</el-button>
			</div>
		</el-dialog>

        <!--图片放大镜-->
     <el-dialog
      title="图片放大镜"
      :visible.sync="dialogVisible"
      width="80%"
      :before-close="handleClose">
      <img :src="imgData" style="width:100%;">
       <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
	</section>
</template>

<script>
import util from '@/utils/table.js'
import editor from '../../components/editor/index.vue'
import {
uploadImg,
deleteImg
} from '@/api/uploadController'
import {
  getRedBaseListPage,
  removeRedBase,
  batchRemoveRedBase,
  editRedBase,
  addRedBase
} from '@/api/redBaseTable'
import{
    getAllCity
}from '@/api/cityTable'
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
      dialogVisible: false,
      dialogFormVisible: false,
      filters: {
        name: ''
      },
      fileList: [],
      redbases: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: [], // 列表选中列
      editFormRules: {
    
      },
      // 编辑界面数据
      editForm: {
      },
      addFormVisible: false, // 新增界面是否显示
      addFormRules: {
      },
      imgData:'',
      cities:[]
    }
  },
    components:{
        editor
    },
  methods: {
    // 在线状态转换
    // formatStatus: function(row, column) {
    //   return row.status === 0 ? '离线' : row.status === 1 ? '在线' : '未知'
    // },
    handleCurrentChange(val) {
      this.page = val
      this.getRedBases()
    },
     //接收自组件富文本的传值
    keyValue(msg,id){
         if(id =='editor5'){
          this.editForm.rDetail=msg
        }        
    },
     //上传
    handleUpload(file,fileList){
      this.editForm.rImg=file.data
    },
     //压缩字段
    getRSynopsis(row){
        if(row!=null &&row.length > 40){
             return row.substring(0,40) + '...'
        }else if(row == null || row.length == 0){
            return '文字不存在,请输入...'
        }else if(row !== null && row.length<=40){
            return row
        }
    },
    getRDetail(row){
        if(row!=null &&row.length > 40){
             return row.substring(0,40) + '...'
        }else if(row == null || row.length == 0){
            return '文字不存在,请输入...'
        }else if(row !== null && row.length<=40){
            return row
        }
    },
    // 获取人物列表
    getRedBases() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getRedBaseListPage(para).then(res => {
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.redbases = res.data.data.records
        console.log(this.redbases)
      })
    },
    //删除上传的文件
    handleRemove(file,fileList){
      deleteImg({
        key: file.response.data
      }).then(res=>{
        this.editForm.cImg=''
        this.$message({
              message: res.data.data,
              type: 'success'
            })
      })
    },
     //显示图片的动作
    showPicture(row){
      this.imgData=row
      this.dialogVisible = true
    },
     //显示图片放大框
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    // 删除
    handleDel(index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          removeRedBase(row.rId).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getRedBases()
          })
        })
        .catch(() => {})
    },
    // 显示编辑界面
    handleEdit(index, row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.editForm = Object.assign({}, row)
      getAllCity().then(res=>{
            this.cities=res.data.data
        })
    },
    // 显示新增界面
    handleAdd() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.editForm = {
      }
        getAllCity().then(res=>{
            this.cities=res.data.data
        })
    },
    // 编辑
    updateData() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {})
            .then(() => {
              const para = Object.assign({}, this.editForm)
              console.log('form',this.editForm)
              editRedBase(para).then(res => {
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getRedBases()
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
              const para = Object.assign({}, this.editForm)
              addRedBase(para).then(res => {
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getRedBases()
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
          batchRemoveRedBase(para).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getRedBases()
          })
        })
        .catch(() => {})
    }
  },
  mounted() {
    this.getRedBases()
  }
}
</script>

<style scoped>

</style>