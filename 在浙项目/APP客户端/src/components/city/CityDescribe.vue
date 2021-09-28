<template>
    <div id="cityDescribe">
        {{getCity(cityMsg)}}
        <van-cell :title="cityMsg.cname"  size="large" >
            <template #label >
                <van-tag color="#ee0a24" plain>地级市</van-tag>
                <van-divider />
                <div id="mse"></div>
                <h3>城市简介</h3>
                <p>
                    {{ city.cheaderIntroduce}}
                </p>
                <h3>地理环境</h3>
                <p>
                    {{ city.cintroduceDetail }}
                </p>
                <h3>自然资源</h3>
                <p>
                    {{ city.cnature }}
                </p>
                <h3>风景名胜</h3>
                <p>
                   {{ city.csights }}
                </p>
                <h3>红色百年</h3>
                <p>
                   {{ city.chistory }}
                </p>
            </template>
        </van-cell>
        <br><br>
    </div>
</template>
<script>
import Player from 'xgplayer'
export default {
    name: 'CityDescribe',
    props:['cityMsg'],
    data(){
        return{
            city:{}
        }
    },
    methods:{
        getCity(val){
            this.city=val
        },
        getVideo(url,img){
            const player = new Player({
                id: 'mse',
                autoplay: false,
                width:320,
                height:200,
                url: url,
                poster:img,
                playbackRate: [0.5, 0.75, 1, 1.5, 2] //传入倍速可选数组
            })
        },
        initVideo(){
            this.getVideo(this.city.cvideo,this.city.cshapeImg)
        }
    },
    mounted(){
        const that=this
        that.initVideo()
    }
}
</script>
<style scoped>
#cityDescribe{
    overflow: auto;
    width: 100%;
    height: 100%;
}
p{
    margin: 0;
    padding: 0;
    word-break:break-all
}
</style>