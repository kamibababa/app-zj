<template>
    <div id="userMsg">
        <van-sticky :offset-top="0">
            <van-nav-bar
            title="我的"
            left-arrow
            @click-left="onClickLeft"
            />
        </van-sticky>
        <br>
        <div v-if="loginStatus">
            <van-cell-group inset>
                <van-card
                desc="一个相聚在浙的浙里人"
                :title="userInfo.uEmail"
                >
                    <template #tags>
                        <van-tag plain type="danger">浙里人</van-tag>
                    </template>
                    <template #thumb>
                        <van-image
                        width="50px"
                        height="50px"
                        :src="userImgSrc"
                        class="userImgBox onlineUserImg"
                        />
                    </template>
                    <template #footer>
                        <van-button size="mini"><van-icon name="star" color="#C82518" /></van-button>
                        <van-button size="mini"><van-icon name="like" color="#C82518"/></van-button>
                    </template>
                </van-card>
            </van-cell-group>
            <br>
            <van-cell-group inset>
                <van-cell title="昵称" :value="userInfo.uUsername" is-link>
                    <template #title>
                        <van-icon name="manager-o" />&nbsp;昵称
                    </template>
                </van-cell>
                <van-cell :value="userInfo.uEmail" is-link>
                    <template #title>
                        <van-icon name="envelop-o" />&nbsp;邮箱
                    </template>
                </van-cell>
                <van-cell :value="userInfo.uTelephone" is-link>
                    <template #title>
                        <van-icon name="phone-o" />&nbsp;手机号
                    </template>
                </van-cell>
                <van-cell title="性别"  is-link>
                    <template #value>
                        <span v-if="userInfo.uSex===1">男</span>
                        <span v-if="userInfo.uSex===0">女</span>
                    </template>
                    <template #title>
                        <van-icon name="smile-comment-o" />&nbsp;性别
                    </template>
                </van-cell>
                <br>
                <van-cell is-link @click="turnToSettings">
                    <template #title>
                        <van-icon name="setting-o" />&nbsp;设置
                    </template>
                </van-cell>
            </van-cell-group>
        </div>
        <div v-if="!loginStatus">
            <van-cell-group inset>
                <van-card
                desc="一个即将在浙的浙里人"
                title="未登陆"
                >
                    <template #tags>
                        <van-tag plain type="default">浙里人</van-tag>
                    </template>
                    <template #thumb>
                        <van-image
                        width="50px"
                        height="50px"
                        :src="userImgSrc"
                        class="userImgBox outlineUserImg"
                        />
                    </template>
                    <template #footer>
                        <van-button size="mini"><van-icon name="star-o" color="#C82518" /></van-button>
                        <van-button size="mini"><van-icon name="like-o" color="#C82518"/></van-button>
                    </template>
                </van-card>
            </van-cell-group>
            <br>
            <van-cell-group inset>
                <van-cell title="注意" size="large" label="欢迎使用我们的在浙APP，一起见证浙江的红色历史与百年发展。我们相聚于此，此APP用于交流与学习，用户登录后才可以使用我们的相关留言、喜欢、收藏等功能，完成我们相关的挑战任务。凡完成我们的挑战任务，相信你一定会对浙江百年发展有着具体又独到的见地。"/>
                <br>
                <van-row>
                    <van-col span="24">
                        <van-button color="#C82518" class="login_button" to="/login">立即登陆</van-button>
                    </van-col>
                </van-row>
                <br>
                <van-row>
                    <van-col span="24">
                        <van-button color="#C82518" plain class="login_button" to="/register">立即注册</van-button>
                    </van-col>
                </van-row>
            </van-cell-group>
        </div>
    </div>
</template>
<script>
export default {
    name: 'UserMsg',
    data(){
        return{
            userImgSrc:require('../../assets/home/logo/logo.png'),
            loginStatus: false,
            userInfo:{
                uId:'',
                uEmail:'',
                uSex:'',
                uTelephone:'',
                uUsername:''
            }
        }
    },
     methods: {
        onClickLeft() {
          this.$router.push({
              path: '/'
          })
        },
        turnToSettings(){
            this.$router.push({
                path: '/settings'
            })
        }
     },
     created(){
         var userInfo=this.$store.state.userInfo
         var token = this.$store.state.token
         console.log(userInfo)
         console.log(token)
         if(token != null && token !=''){
             this.userInfo=userInfo
             this.loginStatus=true
         }
     }
}
</script>
<style scoped>
#userMsg{
    height:100%;
    width:100%;
    position:fixed;
}
.userImgBox{
    position: relative;
    border-radius: 10px;
    top: 20%;
    left: 17%;
}
.onlineUserImg{
    background-color: #C82518;
}
.outlineUserImg{
    background-color: lightgray;
}
.login_button{
    width: 100%;
}
</style>