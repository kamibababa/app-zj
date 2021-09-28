<template>
    <div id="register">
        <register-header></register-header>
        <register-center></register-center>
    </div>
</template>
<script>
import RegisterCenter from '../../components/register/RegisterCenter'
import RegisterHeader from '../../components/register/RegisterHeader'
export default {
    name: 'Register',
    components: { 
        RegisterHeader, 
        RegisterCenter 
    },
}
</script>
<style scoped>
#register{
    background-color: #C82518;
    height:100%;
    width:100%;
    position:fixed;
}
</style>