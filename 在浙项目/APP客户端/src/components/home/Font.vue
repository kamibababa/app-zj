<template>
    <div id="home_font">
        <div class="home_font_text">
            {{fontText}}
        </div>
    </div>
</template>
<script>
import anime from 'animejs/lib/anime.es.js';
export default {
    name: 'Font',
    data(){
        return{
            fontText:'在 浙'
        }
    }, 
    methods:{
        action(){
            anime({
                targets: '.home_font_text',
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
<style>
#home_font{
    width: 100%;
    height: 300px;
    position:fixed;
    top: 50%;
}
@font-face {
		font-family:'huawen';
		src: url('../../assets/home/font/huawen.ttf');
	}
.home_font_text{
    font-size: 3rem;
    font-weight: 600;
    color: white;
    font-family: huawen;
    margin:0 auto;
    position: relative;
    text-align: center;
    opacity: 0;
}
</style>