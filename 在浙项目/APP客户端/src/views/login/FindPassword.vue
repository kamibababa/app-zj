<template>
    <div id="findPassword">
        <find-password-header></find-password-header>
        <find-password-center></find-password-center>
    </div>
</template>
<script>
import FindPasswordCenter from '../../components/findPassword/FindPasswordCenter'
import FindPasswordHeader from '../../components/findPassword/FindPasswordHeader'
export default {
    name:'FindPassword',
    components: { FindPasswordHeader,FindPasswordCenter },
}
</script>
<style scoped>
#findPassword{
    background-color: #C82518;
    height:100%;
    width:100%;
    position:fixed;
}
</style>