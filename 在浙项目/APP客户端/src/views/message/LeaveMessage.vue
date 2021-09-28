<template>
    <van-sticky :offset-top="0">
        <headers></headers>
    </van-sticky>
    <msg-center></msg-center>
    <van-sticky :offset-bottom="0">
        <footers></footers>
    </van-sticky>
</template>
<script>
import headers from '../../components/Header'
import footers from '../../components/Footer'
import MsgCenter from '../../components/msg/MsgCenter'
export default {
    name: 'LeaveMessage',
    components:{
        headers,
        footers,
        MsgCenter
    }
}
</script>
<style scoped>

</style>