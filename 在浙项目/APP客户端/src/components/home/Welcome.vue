<template>
    <div id="home_welcome">
        <div class="home_welcome_describe">
            {{welcome}}
        </div>
    </div>
</template>
<script>
import anime from 'animejs/lib/anime.es.js';
export default {
    name: 'Welcome',
    data(){
        return{
            welcome:'我们一直在\'浙\'里'
        }
    },
    methods:{
        action(){
            anime({
                targets: '.home_welcome_describe',
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
#home_welcome{
    width: 100%;
    height: 30%;
    position:fixed;
    top: 90%;
}
@font-face {
		font-family:'huawen';
		src: url('../../assets/home/font/huawen.ttf');
}
.home_welcome_describe{
    font-family: huawen;
    font-size: 1.5rem;
    color: white;
    opacity: 0;
}
</style>