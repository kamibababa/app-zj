<template>
    <div id="findPasswordCenter">
         <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="user.userEmail" label-width="40px" >
                    <template #label>
                        <van-icon name="envelop-o" size="10"/>邮箱
                    </template>
                    <template #button>
                        <van-button size="small" type="default" @click="sendEmail">发送验证码</van-button>
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
         <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="user.code"  label-width="80px">
                    <template #label>
                        <van-icon name="scan" size="10"/>验证码
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
        <van-row>
            <van-col offset="2" span="20">
                <van-button plain hairline type="warning" class="buttonWidth" @click="findPasswordByCode">找回密码</van-button>
            </van-col>
        </van-row>
        <br>
        <van-row>
            <van-col offset="2" span="20">
                <van-button plain hairline type="warning" class="buttonWidth" to="/login">已有账号，立即登录</van-button>
            </van-col>
        </van-row>
    </div>
</template>
<script>
import {sentEmailForPassword,findPassword} from '../../api/user'
import { Toast } from 'vant';
export default {
    name:'FindPasswordCenter',
    data(){
        return{
            user:{
                userEmail:'',
                code:''
            }
        }
    },
    methods:{
        sendEmail(){
            sentEmailForPassword(this.user).then(res=>{
                Toast(res.data.data);
            })
        },
        findPasswordByCode(){
            findPassword(this.user).then(res=>{
                 Toast(res.data.data);
            })
        }
    }
}
</script>
<style scoped>
#findPasswordCenter{
    position: relative;
    top: 100px;
}
.buttonWidth{
    width: 100%;
}
</style>