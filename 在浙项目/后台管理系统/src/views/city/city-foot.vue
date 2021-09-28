<template>
	<section class="app-container">
        
		<!--列表-->
		<el-table :data="cityFoot" highlight-current-row  style="width: 100%;">
			<el-table-column type="selection" width="120">
			</el-table-column>
			<el-table-column type="index" width="150">
			</el-table-column>
			<el-table-column prop="cname" label="城市" width="200">
			</el-table-column>
            <el-table-column prop="cityFoot" label="足迹总计" width="200">
			</el-table-column>
			<el-table-column prop="gmtCreate" label="创建时间" width="200">
			</el-table-column>
            <el-table-column prop="gmtModified" label="修改时间" width="200">
			</el-table-column>
		</el-table>

        <el-col :span="24" class="toolbar">
			<el-button type="danger"  @click="removeFoot">清空所有足迹</el-button>
		</el-col>
	</section>
</template>

<script>
import util from '@/utils/table.js'
import {
    listCityFoot,
    removeCityFoot
} from '@/api/cityTable'

export default {
  data() {
    return {
      dialogStatus: '',
      cityFoot: [],
    }
  },
  methods: {
    // 获取用户列表
    getCity() {
      listCityFoot().then(res=>{
          this.cityFoot=res.data.data
      })
    },
    mounted() {
        this.getCity()
    },
    removeFoot(){
        removeCityFoot().then(res=>{
             this.$message({
              message: res.data.data,
              type: 'success'
            })
        })
    }
  },
  created(){
       this.getCity()
  }
}
</script>

<style scoped>

</style>