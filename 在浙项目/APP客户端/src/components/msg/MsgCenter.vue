<template>
    <div id="msgCenter">
        <div  v-for="(msg,index) in msgs" v-if="show">
            <br>
            <van-cell-group inset>
                <van-row>
                    <van-col span="24">
                        <p class="msg-name">{{msg.aTitle}}</p>
                    </van-col>
                </van-row>
                <van-row>
                    <van-col span="24">
                        <p class="small-font" style="color:black">
                            留言人:{{msg.uName}}
                        </p>
                    </van-col>
                    <van-col span="24">
                        <span class="grey-color small-font">
                            {{msg.mContent}}
                        </span>
                    </van-col>
                </van-row>
                <van-row>
                    <van-col span="12" offset="12" >
                        <p class="grey-color">{{msg.gmtCreate}}</p>
                    </van-col>
                </van-row>
        </van-cell-group>
        <van-cell-group inset v-if="showNull">
            <van-empty description="暂无留言" />
        </van-cell-group>
        </div>
        <div v-if="!show">
            <van-empty image="network" description="请先登录,才能查看个人留言" />
        </div>
    </div>
</template>
<script>
import {listArticleMsgByUId} from '../../api/message'
export default {
    name:'MsgCenter',
    data(){
        return{
            msgs:[],
            show:false,
            showNull:false
        }
    },
    created(){
        if(this.$store.state.userInfo!==''&&this.$store.state.userInfo!==null){
            this.show=true
            listArticleMsgByUId(this.$store.state.userInfo.uId).then(res=>{
                this.msgs=res.data.data
            })
            if(this.msgs.length===''|| this.msgs.length===null){
                this.showNull=true
            }else{
                this.showNull=false
            }
        }else{
            this.show=false
        }
    }
}
</script>
<style scoped>
#msgCenter{
    width: 100%;
    height: 50rem;
    overflow: auto;
    margin-top:5rem ;
    background-color: #F7F8FA;
}
.msg-name{
    font-weight: 600;
}
.grey-color{
    color: grey;
}
.small-font{
    font-size: 12px;
}
</style>