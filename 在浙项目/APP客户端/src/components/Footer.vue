<template>
    <div id="footer">
      <van-tabbar v-model="active" active-color="#ee0a24" inactive-color="#000" >
            <van-tabbar-item icon="chat-o" replace to="/message">留言</van-tabbar-item>
            <van-tabbar-item icon="home-o" replace to="/">主页</van-tabbar-item>
            <van-tabbar-item icon="user-o" replace to="/userMsg">我的</van-tabbar-item>
     </van-tabbar>
    </div>
</template>

<script>
export default {
    name: 'Footer',
    data() {
        return {
            active:1
        }
    }
}
</script>

<style scoped>

</style>