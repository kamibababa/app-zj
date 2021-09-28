<template>
    <div id="cityPeople">
        {{ getHero(cityHero) }}
         <van-cell title="" size="large" >
            <template #label >
                <van-row>
                        <van-col span="8">
                            <van-image
                            width="110"
                            height="220"
                            :src="people.cimg"
                            class="hero-img"
                            />
                        </van-col>
                        <van-col span="16">
                            <h2 style="margin:0;padding:0">{{people.chName}}</h2>
                            <br>
                                <div  class="div-box">
                                    {{people.cdeedsSynopsis}}
                                </div>
                        </van-col>
                    </van-row>
                    <van-row>
                        <van-col span="24">
                            <h2>烈士生平</h2>
                        </van-col>
                    </van-row>
                    <van-row>
                        <van-col span="24">
                           <div class="div-box">
                               {{people.cdeedsDetail}}
                           </div>
                        </van-col>
                    </van-row>
                    <van-row>
                        <van-col span="24">
                            <h2>烈士成就</h2>
                        </van-col>
                    </van-row>
                    <van-row>
                        <van-col span="24">
                          <div  class="div-box">
                              {{ people.cachievement }}
                          </div>
                        </van-col>
                    </van-row>
                    <van-row>
                        <van-col span="24">
                            <h2>烈士评价</h2>
                        </van-col>
                        <van-col span="24">
                            <div  class="div-box">
                                {{people.cevaluate}}
                            </div>
                        </van-col>
                    </van-row>
                    <br><br>
            </template>
         </van-cell>
    </div>
</template>
<script>
export default {
    name:'CityPeople',
    props:['cityHero'],
    data(){
        return{
            people:{}
        }
    },
    methods:{
        getHero(val){
            this.people=val
        }
    }
}
</script>
<style scoped>
#cityPeople{
    overflow: auto;
    width: 100%;
    height: 100%;
}
.div-box{
    word-break:break-all
}
</style>