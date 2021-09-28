<template>
    <div id="loginCenter">
        <van-row>
            <van-col offset="2" span="20">
                <van-field v-model="loginDto.uEmail" label-width="50px" >
                    <template #label>
                        <van-icon name="envelop-o" size="10"/>&nbsp;邮箱
                    </template>
                </van-field>
            </van-col>
        </van-row>
        <br>
        <van-form>
            <van-row>
                <van-col offset="2" span="20">
                    <van-field v-model="loginDto.uPassword" type="password"  label-width="50px" @keyup="pwdValidator">
                        <template #label>
                            <van-icon name="lock" size="10"/>&nbsp;密码
                        </template>
                    </van-field>
                </van-col>
            </van-row>
            <br>
            <van-row>
                <van-col offset="2" span="20">
                    <van-button plain hairline type="warning" class="buttonWidth" :disabled="disabledStatus" @click="logining">登录</van-button>
                </van-col>
            </van-row>
            <br>
            <van-row>
                <van-col offset="2" span="20">
                    <van-button plain hairline type="warning" class="buttonWidth" to="/register">新用户注册</van-button>
                </van-col>
            </van-row>
        </van-form>
    </div>
</template>
<script>
import { login } from '../../api/user'
import { Dialog } from 'vant';
export default {
    name:'LoginCenter',
    data(){
        return{
            disabledStatus: true,
            loginDto:{
                uEmail: '',
                uPassword:''
            }
        }
    },
    methods:{
        logining(){
                login(this.loginDto).then(res=>{
                    const jwt=res.headers['authorization'];
                    const userInfo=res.data.data;
                    this.$store.commit("SET_TOKEN",jwt);
                    this.$store.commit("SET_USERINFO",userInfo);
                    this.$router.push({
                        path : 'userMsg'
                    })
                }).catch(err=>{
                    Dialog.alert({
                    title: '错误',
                    message: err,
                    }).then(() => {
                        this.loginDto.uEmail=''
                        this.loginDto.uPassword=''
                    });
                })
        //    }else{
        //        Dialog({ message: '您已登陆,无需重复登录' });
        //    }
        },
        pwdValidator(val){
            if(this.loginDto.uEmail!=null && val !=null){
                this.disabledStatus=false
            }else{
                this.disabledStatus=true
            }
        }
    }
}
</script>
<style scoped>
#loginCenter{
    position: relative;
    top: 100px;
}
.buttonWidth{
    width: 100%;
}
</style>