<template>
    <div id="login">
        <login-header></login-header>
        <login-center></login-center>
        <login-footer></login-footer>
    </div>
</template>

<script>
import LoginCenter from '../../components/login/LoginCenter'
import LoginFooter from '../../components/login/LoginFooter'
import LoginHeader from '../../components/login/LoginHeader'
export default {
    name: 'Login',
    components: { LoginHeader,LoginCenter,LoginFooter },
}
</script>

<style scoped>
#login{
    background-color: #C82518;
    height:100%;
    width:100%;
    position:fixed;
}
</style>