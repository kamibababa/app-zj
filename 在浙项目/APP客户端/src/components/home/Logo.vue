<template>
    <div id="home_logo">
        <div class="home_logo_box">
            <van-image
                fit="contain"
                width="8rem"
                height="8rem"
                :src="logoImg"
                class="home_logo_box_vant_image"
                />
        </div>
    </div>
</template>
<script>
import logoImg from '../../assets/home/logo/logo.png'
import anime from 'animejs/lib/anime.es.js';
export default {
    name: 'Logo',
    data(){
        return{
            logoImg:logoImg
        }
    },
    methods:{
        action(){
            anime({
                targets: '.home_logo_box_vant_image',
                duration: 5000,
                opacity:1
            });
        }
    },
    mounted(){
        this.action()
    }
}
</script>
<style scoped>
#home_logo{
    width: 100%;
    height: 30%;
    position:fixed;
    top: 30%;
}
.home_logo_box{
   position: relative;
}
.home_logo_box_vant_image{
    border-radius: 2rem;
    opacity: 0;
}
</style>