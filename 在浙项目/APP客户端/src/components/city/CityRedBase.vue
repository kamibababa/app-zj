<template>
    <div id="cityRedBase">
        {{ getPlace(cityPlace) }}
        <van-cell :title="redPlace.rName"  size="large" >
            <template #label >
                <van-tag color="#ee0a24" plain>红色经典景区</van-tag>
                <van-divider />
                <img :src="redPlace.rImg" alt="" class="img-class">
                <h3>基本介绍</h3>
                <p>
                    {{redPlace.rSynopsis}}
                </p>
                <h3>地理位置</h3>
                <p>
                   {{redPlace.rAddress}}
                </p>
                <h3>历史沿革</h3>
                <p>
                   {{redPlace.rDetail}}
                </p>
                <h3>荣誉及其他</h3>
                <p>
                  {{redPlace.rAchievement}}
                </p>
                <!--引入地图-->
            </template>
        </van-cell>
        <br><br>
    </div>
</template>
<script>
export default {
    name: 'CityRedBase',
    props:["cityPlace"],
    data(){
        return{
            redPlace:[]
        }
    },
    methods:{
        getPlace(arr){
            this.redPlace=arr
            console.log(this.redPlace)
        }
    }
}
</script>
<style scoped>
#cityRedBase{
    overflow: auto;
    width: 100%;
    height: 100%;
}
p{
    margin: 0;
    padding: 0;
    word-break:break-all
}
.img-class{
    width: 100%;
    height: 12rem;
}
</style>