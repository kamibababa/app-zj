<template>
    <div id="header_box">
        <div class="header_logo">
            <van-image
                width="70%"
                height="60%"
                fit="contain"
                :src="logoImg"
            />
            <p>在浙</p>
        </div>
        <div class="header_search">
            <input type="text" placeholder="我在'浙'里听风" >
        </div>
        <div class="header_task">
            <router-link to="/task">任务记录</router-link>
        </div>
    </div>
</template>

<script>
import logoImg from '../assets/home/logo/logo.png'
export default {
    name: 'header',
    data(){
        return{
            logoImg: logoImg
        }
    },
    methods:{
         
    }
}
</script>

<style scoped>
#header_box{
    width: 100%;
    height: 4rem;
    background-color: #C82518;
    display: flex;
    position: fixed;
    top: 0;
    z-index: 100;
}
.header_logo{
    width: 10%;
    height: 70%;
    position: relative;
    left: 2%;
    top: 20%;
    text-align: center;
}
@font-face {
		font-family:'huawen';
		src: url('../assets/home/font/huawen.ttf');
}
.header_logo p{
    line-height: 20%;
    margin: 0;
    padding: 0;
    font-size: 0.4rem;
    font-family: huawen;
    color: white;
}
.header_search{
    width: 70%;
    height: 90%;
    position: relative;
    left: 5%;
    top: 5%;
	padding:0;
}
input {
	width:90%;
	height:70%;
    border:none;
	outline:none;
    text-indent: 1.7em;
    display: inline-block;
    position: absolute;
    top: 7%;
    border:2px solid #c5464a;
	border-radius:5px;
    background-image:url('../assets/img/search.png');
    background-size: 8%;
    background-repeat: no-repeat;
    background-position:3% center;
}
.header_task{
    width: 70px;
    height: 80%;
    display: inline-block;
    font-size: 14px;
    text-align: center;
    font-weight: 600;
    border-radius: 15px;
    position: relative;
    top: 18%;
    left: 2%;
}
.header_task a{
    width: 50%;
    height: 100%;
    padding: 0;
    margin: 0;
    display: inline-block;
}
.header_task a:link,.header_task a:visited{
    color: white;
    text-decoration: none;
}
</style>