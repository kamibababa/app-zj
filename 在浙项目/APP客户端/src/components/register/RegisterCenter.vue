<template>
    <div id="registerCenter">
         <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="send.userEmail" label-width="50px" >
                    <template #label>
                        <van-icon name="envelop-o" size="10"/>&nbsp;邮箱
                    </template>
                    <template #button>
                        <van-button size="small" type="default" @click="sendEmail()">发送验证码</van-button>
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
        <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="user.uPassword" type="password"  label-width="50px" @keyup="pwdValidator">
                    <template #label>
                        <van-icon name="lock" size="10"/>&nbsp;密码
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
         <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="user.code"  label-width="80px">
                    <template #label>
                        <van-icon name="scan" size="10"/>&nbsp;验证码
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
        <van-row>
            <van-col offset="2" span="20">
                <van-button plain hairline type="warning" class="buttonWidth" :disabled="disabledStatus" @click="toRegister()">立即注册</van-button>
            </van-col>
        </van-row>
        <br>
        <van-row>
            <van-col offset="2" span="20">
                <van-button plain hairline type="warning" class="buttonWidth" to="/login">已有账号,立即登陆</van-button>
            </van-col>
        </van-row>
    </div>
</template>
<script>
import {sengEmail,register} from '../../api/user'
import { Toast } from 'vant';
export default {
    name: 'RegisterCenter',
    data(){
        return{
            user:{
                uEmail:'',
                code:'',
                uPassword:''
            },
            disabledStatus:true,
            send:{
                userEmail:''
            }
        }
    },
    methods:{
        sendEmail(){
            sengEmail(this.send).then(res=>{
                Toast(res.data.data)
                this.user.uEmail=this.send.userEmail
            })
        },
        toRegister(){
            register(this.user).then(res=>{
                if(res.data.msg){
                    this.$router.push({
                        path: '/login'
                    })
                }else{
                      Toast(res.data.msg)
                }
            })
        },
        pwdValidator(val){
            if(this.user.uEmail!=null && val !=null){
                this.disabledStatus=false
            }else{
                this.disabledStatus=true
            }
        }
    }
}
</script>
<style scoped>
#registerCenter{
    position: relative;
    top: 100px;
}
.buttonWidth{
    width: 100%;
}
</style>