<template>
  <div class="app-container">
  
    <el-button style="margin:0 0 20px 0" type="primary" round @click="back">返回上一页</el-button>

  
    <nx-tree-table :data="data" :columns="columns" border></nx-tree-table>

  </div>
</template>

<script>
import nxTreeTable from '@/components/nx-tree-table'
import {
  getRoleToPermission,
  getRoleToPermissionById,
  getRoleToPermissionByPid
} from '@/api/rolePermissionTable.js'

export default {
  name: 'treeTableDemo',
  components: { nxTreeTable },
  data() {
    return {
      columns: [
       
      ],
      data: [],
      role: [
        {
          text: '角色',
          value: 'rName'
        },
        {
          text: '权限',
          value: 'pname'
        }
      ],
      permission:[
          {
          text: '权限',
          value: 'pName'
        },
        {
          text: '角色',
          value: 'rname'
        }
      ]
    }
  },
  methods:{
    listRoleToPermission(){
        getRoleToPermission().then(res=>{
          this.data=res.data.data
          this.columns=this.role
          this.$message({
            showClose: true,
            message: '角色-权限树型关系表',
            type: 'success'
          })
        })
    },
    getRoleToPermission(){
      getRoleToPermissionById(this.$route.query.rid).then(res=>{
        this.data=res.data.data
        this.columns=this.role
        //console.log(this.data)
        this.$message({
            showClose: true,
            message: '角色('+this.data.rName+')树型关系表',
            type: 'success'
          })
      })
    },
    getRoleToPermissionByPermission(){
      getRoleToPermissionByPid(this.$route.query.pid).then(res=>{
        //console.log(res)
        this.data=res.data.data
        this.columns=this.permission
        this.$message({
            showClose: true,
            message: '权限('+this.data.pName+')树型关系表',
            type: 'success'
          })
      })
    },
    list(){
      //如果路径有参数的话就是单独查询，没有则是全部查询
      if((this.$route.query.rid==null || this.$route.query.rid==undefined) && (this.$route.query.pid==null || this.$route.query.pid==undefined)){
        this.listRoleToPermission()
      }else if(this.$route.query.rid!=null && this.$route.query.rid!=undefined && (this.$route.query.pid==null || this.$route.query.pid==undefined)){
        this.getRoleToPermission()
      }else if((this.$route.query.rid==null || this.$route.query.rid==undefined) && this.$route.query.pid!=null && this.$route.query.pid!=undefined){
        this.getRoleToPermissionByPermission()
      }else{
        this.$message({
            showClose: true,
            message: '参数异常',
            type: 'error'
          })
      }
    },
    //返回上一页
    back(){
       this.$router.go(-1)
    }
  },
  created(){
    this.list()
  }
}
</script>
