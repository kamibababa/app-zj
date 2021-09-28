<template>
    <div id="city">
        <van-sticky>
            <van-nav-bar 
            left-arrow  
            @click-left="onClickLeft"
            @click-right="onClickRight">
                <template #title>
                    {{cityMsg.cname}}
                </template>
                <template #right>
                    <van-icon name="more-o" size="18"  />
                </template>
            </van-nav-bar>
        </van-sticky>
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh" style="width: 100%;height:100vh;overflow: auto;">
            <br>
            <!--头部介绍-->
            <van-cell-group inset>
                <van-cell :title="cityMsg.cname"  size="large" @click="showDescribes" is-link>
                    <template #label >
                        <van-tag color="#ee0a24" plain>地级市</van-tag>
                        <van-divider />
                        <img :src="cityMsg.cshapeImg" class="img-class">
                        <br><br>
                        <p>
                            {{showCityDescribe()}}
                        </p>
                    </template>
                </van-cell>
            </van-cell-group>
            <br>
            <!--详细文章介绍-->
            <van-cell-group  inset>
                <van-cell title="红色基地"  size="large"></van-cell>
                <van-swipe class="my-swipe"  indicator-color="white" :loop="false" :width="250" stop-propagation="false">
                      <van-swipe-item v-for="(place,index) in cityPlace" :key="index" >
                          <p class="flex-p">{{place.rName}}</p>
                          <img :src="place.rImg" alt="" class="img-style" @click="showRedBaseById(index)">
                      </van-swipe-item>
                </van-swipe>
                <br>
            </van-cell-group>
            <!--红色人物-->
            <br>
            <van-cell-group  inset>
                 <van-cell title="红色人物"  size="large"></van-cell>
                 <van-cell v-for="(hero,index) in cityHero" @click="showPeople(index)">
                     <van-row v-if="index%2==0"  class="center-box">
                        <van-col span="8">
                            <van-image
                            width="100"
                            height="100"
                            :src="hero.cimg"
                            class="hero-img"
                            />
                            <p class="hero-name">{{hero.chName}}</p>
                        </van-col>
                        <van-col span="1"></van-col>
                        <van-col span="15">{{getHeroContentTxt(index,60)}}</van-col>
                    </van-row>
                    <van-row v-if="index%2!=0">
                        <van-col span="15">
                            {{getHeroContentTxt(index,60)}}
                        </van-col>
                        <van-col span="1"></van-col>
                        <van-col span="8">
                            <van-image
                                width="100"
                                height="100"
                                :src="hero.cimg"
                                class="hero-img"
                                />
                            <p class="hero-name">{{hero.chName}}</p>
                        </van-col>
                    </van-row>
                 </van-cell>
            </van-cell-group>
            <br>
            <van-cell-group inset>
                 <van-cell title="百年成就"  size="large"></van-cell>
                 <div v-if="articles.length !== 0">
                     <van-cell title="" v-for="(article,index) in articles" @click="turnToArticle(article.aId)">
                     <template #label>
                         <div v-if="article.aCoverImg=='' || article.aCoverImg== null">
                             <h3>
                                 {{article.aTitle}}
                             </h3>
                             <p>
                                 {{article.gmtCreate}}
                             </p>
                            <p>
                                {{getArticleTxt(index,60)}}
                            </p>
                         </div>
                         <div v-if="article.aCoverImg!=='' && article.aCoverImg!== null">
                             <van-row>
                                 <van-col span="8">
                                     <img :src="article.aCoverImg" alt="" class="article-img-class">
                                 </van-col>
                                 <van-col span="15" offset="1">
                                     <h3>
                                        {{article.aTitle}}
                                    </h3>
                                    <p class="time">
                                        在浙APP&nbsp;{{getTime(article.gmtCreate)}}
                                    </p>
                                    <p>
                                        {{getArticleTxt(index,45)}}
                                    </p>
                                 </van-col>
                             </van-row>
                             <br><br>
                         </div>
                         <van-row>
                             <van-col span="8">
                                 <van-button plain hairline type="default" icon="browsing-history">{{ article.num }}</van-button>
                             </van-col>
                             <van-col span="8" offset="8">
                                 <van-row>
                                     <van-col span="12">
                                         <van-button plain hairline type="default" icon="like">{{article.star}}</van-button>
                                     </van-col>
                                     <van-col span="12">
                                        <van-button plain hairline type="default" icon="star">{{getRandom()}}</van-button>
                                     </van-col>
                                 </van-row>
                             </van-col>
                         </van-row>
                     </template>
                 </van-cell>
                 </div>
                 <div v-if="articles.length ==0 ">
                     <van-empty description="暂无文章" />
                 </div>
            </van-cell-group>
        </van-pull-refresh>
        <van-popup v-model:show="showDescribe" round position="bottom" lock-scroll="false" closeable close-icon="close" :style="{ height: '90%' }" >
             <br><br>
            <city-describe :cityMsg="cityMsg" ></city-describe>
        </van-popup>
        <van-popup v-model:show="showRedBase" round position="bottom" lock-scroll="false" closeable close-icon="close" :style="{ height: '90%' }" >
            <br><br>
            <city-red-base :cityPlace="place"></city-red-base>
        </van-popup>
        <van-popup v-model:show="showRedPeople" round position="bottom" lock-scroll="false" closeable close-icon="close" :style="{ height: '90%' }" >
            <br><br>
            <city-people :cityHero="hero"></city-people>
        </van-popup>
        <van-action-sheet v-model:show="showActiveBar" :actions="actions" @select="onSelect" v-show="showActiveBar"/>
    </div>
</template>
<script>
import CityDescribe from '../../components/city/CityDescribe';
import CityRedBase from '../../components/city/CityRedBase';
import CityPeople from '../../components/city/CityPeople';
import { getCityById,listCityPlase,listCityHero,listCityArticle } from '../../api/city'
import { getArticleRedisById,getArticleStar} from '../../api/article'
export default {
    name:"City",
    components:{
        CityDescribe,
        CityRedBase,
        CityPeople
    },
    data(){
        return{
            showDescribe: false,
            showRedBase:false,
            showRedPeople:false,
            showActiveBar:false,
            actions: [
                { name: '杭州市',path:'1'},
                { name: '湖州市',path:'2'}, 
                { name: '嘉兴市',path:'3'},
                { name: '金华市',path:'4'},
                { name: '丽水市',path:'5'},
                { name: '宁波市',path:'6'},
                { name: '衢州市',path:'7'},
                { name: '绍兴市',path:'8'},
                { name: '台州市',path:'9'},
                { name: '温州市',path:'10'},
                { name: '舟山市',path:'11'}
            ],
            cityMsg:{},
            isLoading: false,
            cityPlace:[],
            place:{},
            cityHero:[],
            hero:{},
            articles:[],
            articleGoodJob:"good-job-o",
            articleStar: "star-o",
            looking:[]
        }
    },
    methods: {
        showDescribes() {
            this.showDescribe = !this.showDescribe
        },
        showRedBaseById(index){
            this.showRedBase=!this.showRedBase
            this.place=this.cityPlace[index]
        },
        showPeople(index){
            this.showRedPeople=!this.showRedPeople
            this.hero=this.cityHero[index]
        },
        onClickLeft() {
            this.$router.push({
                path: '/'
            });
        },
        onClickRight() {
            this.showActiveBar=!this.showActiveBar
        },
        onRefresh() {
            setTimeout(() => {
                this.isLoading = false;
            }, 1000);
        },
        getArticleTxt(index,num){
            if( this.articles[index].aContentTxt == null ||  this.articles[index].aContentTxt == ''){
                return  this.articles[index].aContentTxt
            }else if(this.articles[index].aContentTxt.length<num){
                return  this.articles[index].aContentTxt
            }else{
                return  this.articles[index].aContentTxt.substring(0,num)+'...'
            }
        },
        clickArticleJob(){
            if(this.articleGoodJob=="good-job-o"){
                this.articleGoodJob="good-job"
            }else{
                this.articleGoodJob="good-job-o"
            }
        },
        clickArticleStar(){
            if(this.articleStar=="star-o"){
                this.articleStar="star"
            }else{
                this.articleStar="star-o"
            }
        },
        onSelect(item) {
            this.$router.replace({
                path: '/city',
                query:{
                    cId: item.path
                }
            })
            this.getFlush()
        },
        getLooking(index){
            return this.looking[index].num
        },
         getRandom(){
           return Math.floor(Math.random()*100+1)
        },
        getHeroContentTxt(index,num){
          if(this.cityHero[index].cdeedsSynopsis=='' || this.cityHero[index].cdeedsSynopsis == null){
              return this.cityHero[index].cdeedsSynopsis
          }
          if(this.cityHero[index].cdeedsSynopsis.length<num){
              return this.cityHero[index].cdeedsSynopsis
          }else{
              var text=this.cityHero[index].cdeedsSynopsis.substring(0,num)+'...'
              return text
          }
      },
      getTime(val){
          return val.substring(0, val.indexOf(' '))
      },
        turnToArticle(index){
            this.$router.push({
                path: '/article',
                query: {
                    aId: index
                }
            })
        },
        showCityDescribe(){
            if(this.cityMsg.cheaderIntroduce=='' || this.cityMsg.cheaderIntroduce==null){
                return this.cityMsg.cheaderIntroduce
            }else if(this.cityMsg.cheaderIntroduce.length<=61){
                    return this.cityMsg.cheaderIntroduce
            }else{
                return this.cityMsg.cheaderIntroduce.substring(0,61)+'...'
            }
        },
        getFlush(){
            getCityById(this.$route.query.cId).then(res=>{
                this.cityMsg=res.data.data
            })
            listCityPlase(this.$route.query.cId).then(res=>{
                this.cityPlace=res.data.data
            })
            listCityHero(this.$route.query.cId).then(res=>{
                this.cityHero=res.data.data
            })
            listCityArticle(this.$route.query.cId).then(res=>{
                if(res.data.data=='查询条目为空'){
                    this.articles=[]
                }else{
                    this.articles=res.data.data
                    for(let i=0;i<this.articles.length;i++){
                        getArticleRedisById(this.articles[i].aId).then(res=>{
                            this.articles[i].num=res.data.data
                        })
                        getArticleStar(this.articles[i].aId,this.$store.state.uId).then(res=>{
                            this.articles[i].star=res.data.data
                        })
                    }
                }
            })
        }
    },
    created(){
        this.getFlush()
    }
}
</script>

<style scoped>
#city{
    background-color: #F7F8FA;
}
.img-style{
    width: 250px;
    height: 150px;
}
.my-swipe .van-swipe-item {
    text-align: center;
    background-color: #F7F8FA;
}
.van-swipe-item{
    margin-left: 1px;
}
.img-class{
    width: 100%;
    height: 12rem;
}
.flex-p{
    width: 100%;
    height: 20px;
    line-height: 20px;
    font-size: 15px;
    position: absolute;
    bottom: 5%;
    text-align: center;
    margin: 0;
    padding: 0;
    color:white; 
    text-shadow: 1px 1px #000,-1px -1px #000, 1px -1px #000, -1px 1px #000;
}
p{
    margin: 0;
    padding: 0;
    word-break:break-all
}
.article-img-class{
    width: 100px;
    height: 100px;
}
.hero-img{
    margin: 0 auto;
}
.hero-name{
    text-align: center;
}
.center-box{
    padding: 10px 0;
}
h3{
    margin: 0;
    padding: 0;
}
p .time{
    font-size: 8px;
}
</style>