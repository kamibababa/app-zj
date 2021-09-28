<template>
    <div id="settings">
        <van-sticky :offset-top="0">
            <van-nav-bar
            title="设置"
            left-arrow
            @click-left="onClickLeft"
            />
        </van-sticky>
        <van-cell-group>
            <van-cell title="账号与安全" is-link></van-cell>
        </van-cell-group>
        <br>
        <van-cell-group>
             <van-cell title="留言通知" is-link></van-cell>
            <van-cell title="点赞弹窗" is-link></van-cell>
            <van-cell title="隐私" is-link></van-cell>
            <van-cell title="通用" is-link></van-cell>
        </van-cell-group>
        <br>
        <van-cell-group>
            <van-cell title="关于在浙" is-link></van-cell>
            <van-cell title="帮助与反馈" is-link></van-cell>
        </van-cell-group>
        <br>
        <van-cell-group>
            <van-button color="#C82518" class="login_button" @click="logout">退出登录</van-button> 
        </van-cell-group>
    </div>
</template>
<script>
import { logout } from '../../api/user'
import { Dialog } from 'vant';
export default {
    name: 'Settings',
    data(){

    },
    methods:{
        onClickLeft(){
            this.$router.go(-1)
        },
        logout(){
             logout().then(res=>{
                Dialog.alert({
                    message: res.data.msg,
                    }).then(() => {
                        this.$router.push({
                            path: '/'
                        })
                    });
                 this.$store.commit("REMOVE_INFO");
             })
        }
    }
}
</script>
<style scoped>
#settings{
    width: 100%;
    height: 100%;
    position: fixed;
    background-color: #F7F8FA;
}
.login_button{
    width: 100%;
}
</style>