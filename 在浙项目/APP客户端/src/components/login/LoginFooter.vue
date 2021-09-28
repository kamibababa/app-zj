<template>
    <div id="loginFooter">
        <van-row>
            <van-col offset="10" span="5">
                <router-link to="/findPassword">找回密码</router-link>
            </van-col>
        </van-row>
    </div>
</template>
<script>
export default {
    name: 'LoginFooter'
}
</script>
<style scoped>
#loginFooter{
    width: 100%;
    position: absolute;
    bottom: 10px;
}
a{
    color: white;
    font-family: '微软雅黑';
}
</style>