<template>
	<section class="app-container">
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters" @submit.native.prevent>
				<el-form-item>
					<el-input v-model="filters.name" placeholder="查询内容"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getCity">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="cities" highlight-current-row @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="50">
			</el-table-column>
			<el-table-column type="index" width="50">
			</el-table-column>
			<el-table-column prop="cname" label="城市" width="80" >
			</el-table-column>
            <el-table-column prop="cshapeImg" label="市图" width="120" >
                 <template slot-scope="scope">
                    <a @click="showPicture(scope.row.cshapeImg)"><img :src="scope.row.cshapeImg" alt="" style="width:100px;height:100px"></a>
                </template>
			</el-table-column>
            <el-table-column  prop="cintroduceDetail" label="城市简介" width="200" >
                <template slot-scope="scope">
                    <span >{{ getCintroduceDetail(scope.row.cintroduceDetail) }}</span>
                </template>
			</el-table-column>
            <el-table-column prop="cheaderIntroduce" label="百年发展头部介绍" width="200" >
                <template slot-scope="scope">
                    <span >{{ getCheaderIntroduce(scope.row.cintroduceDetail) }}</span>
                </template>
			</el-table-column>
			<el-table-column prop="gmtCreate" label="创建时间" width="100">
			</el-table-column>
      <el-table-column prop="gmtModified" label="修改时间" width="100">
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
				<el-form-item label="城市" prop="cname">
					<el-input v-model="editForm.cname" auto-complete="off" ></el-input>
				</el-form-item>
        <el-upload
        class="upload-demo"
        action="http://localhost:8081/oss/upload"
        :file-list="fileList"
        :on-success="handleUpload"
        :on-remove="handleRemove"
        list-type="picture">
        <h4 style="text-align:center;width:100px;display:inline-block;">市图</h4>
        <el-button size="small" type="primary" @click="handleUpload">点击上传</el-button>
        <span slot="tip" class="el-upload__tip"  style="text-align:center;">只能上传jpg/png文件，且不超过500kb</span>
      </el-upload>
       <h4 style="text-align:center;width:100px;display:inline-block;">城市简介</h4>
      <editor v-model="editForm.cintroduceDetail" id="editor1" @val="keyValue"></editor>
      <h4 style="text-align:center;width:150px;display:inline-block;">百年发展头部介绍</h4>
         <editor v-model="editForm.cheaderIntroduce" id="editor2" @val="keyValue">  </editor>
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
//import util from '@/utils/table.js'
import editor from '../../components/editor/index.vue'
import {
  getCityListPage,
  removeCity,
  batchRemoveCity,
  editCity,
  addCity
} from '@/api/cityTable'

import {
uploadImg,
deleteImg
} from '@/api/uploadController'
export default {
  data() {
    return {
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogFormVisible: false,
      dialogVisible: false,
      filters: {
        name: ''
      },
      fileList: [],
      imgData:'',
      cities: [],
      total: 0,
      page: 1,
      pageSize: 1,
      sels: [], // 列表选中列
      editFormRules: {
        cName: [{ required: true, message: '请输入角色', trigger: 'blur' }]
      },
      // 编辑界面数据
      editForm: {
        rname: ''
      },
      addFormVisible: false, // 新增界面是否显示
      addFormRules: {
        cName: [{ required: true, message: '请输入角色', trigger: 'blur' }]
      }
    }
  },
  components:{
        editor
    },
  methods: {
    //接收自组件富文本的传值
    keyValue(msg,id){
        if(id == 'editor2'){
          this.editForm.cheaderIntroduce=msg
          //console.log('editor2',this.editForm.cheaderIntroduce)
        }else if(id =='editor1'){
          this.editForm.cintroduceDetail=msg
          //console.log('editor1',this.editForm.cintroduceDetail)
        }        
    },
    handleCurrentChange(val) {
      this.page = val
      this.getCity()
    },
    // 获取用户列表
    getCity() {
      const para = {
        page: this.page,
        name: this.filters.name
      }
      getCityListPage(para).then(res => {
        //console.log('getCity',res)
        this.pageSize=res.data.data.size
        this.total = res.data.data.total
        this.cities = res.data.data.records
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
    //上传
    handleUpload(file,fileList){
      this.editForm.cshapeImg=file.data
    },
    //删除上传的文件
    handleRemove(file,fileList){
      deleteImg({
        key: file.response.data
      }).then(res=>{
        this.editForm.cshapeImg=''
        this.$message({
              message: res.data.data,
              type: 'success'
            })
      })
    },
    //压缩字段
    getCintroduceDetail(row){
        if(row!=null &&row.length > 40){
             return row.substring(0,40) + '...'
        }else if(row == null || row.length == 0){
            return '文字不存在,请输入...'
        }else if(row !== null && row.length<40){
            return row
        }
    },
    getCheaderIntroduce(row){
        if(row!=null &&row.length > 40){
             return row.substring(0,40) + '...'
        }else if(row == null || row.length == 0){
            return '文字不存在,请输入...'
        }else if(row !== null && row.length<40){
            return row
        }
    },
    // 删除
    handleDel(index, row) {
      //console.log('row',row.rid)
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          removeCity(row.cid).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getCity()
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
        cname:''
      }
    },
    // 编辑
    updateData() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {})
            .then(() => {
              const para = Object.assign({}, this.editForm)
              //console.log('para',para)
              editCity(para).then(res => {
                //console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getCity()
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
             // console.log(para)
              addCity(para).then(res => {
                //console.log('res',res)
                this.$message({
                  message: res.data.data,
                  type: 'success'
                })
                this.$refs['editForm'].resetFields()
                this.dialogFormVisible = false
                this.getCity()
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
          batchRemoveCity(para).then(res => {
            this.$message({
              message: res.data.data,
              type: 'success'
            })
            this.getCity()
          })
        })
        .catch(() => {})
    }
  },
  mounted() {
    this.getCity()
  }
}
</script>

<style scoped>

</style>