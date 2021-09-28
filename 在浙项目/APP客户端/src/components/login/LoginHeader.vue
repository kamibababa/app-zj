<template>
    <div id="loginHeader">
        <van-row>
            <van-col span="8" offset="8">
                <van-image
                width="100%"
                fit="cover"
                :src="imgSrc"
                />
            </van-col>
        </van-row>
        <van-row>
            <van-col span="8" offset="8">
                <p class="logo_title">在浙登陆</p>
            </van-col>
        </van-row>
    </div>
</template>
<script>
export default {
    name: 'LoginHeader',
    data(){
        return{
            imgSrc:require('../../assets/home/logo/logo.png')
        }
    }
}
</script>
<style scoped>
#loginHeader{
    position: relative;
    top: 100px;
}
@font-face {
    font-family:'huawen';
    src: url('../../assets/home/font/huawen.ttf');
}
.logo_title{
    text-align: center;
    font-family: huawen;
    color: white;
    font-weight: 600;
    font-size: 25px;
}
</style>