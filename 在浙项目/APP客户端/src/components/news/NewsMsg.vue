<template>
    <div id="newsMsg">
         <van-cell-group inset v-for="(article,index) in articles" class="margin-top-10" @click="getArticle(article.aId)">
            <van-row v-if="article.aCoverImg !== null">
                <van-col span="10">
                    <img :src="article.aCoverImg" style="width:110px;height:110px;margin:0 auto" alt="">
                </van-col>
                <van-col span="14">
                    <p class="font-size-box">{{article.aTitle}}</p>
                    <p>
                        {{getContentTxt(index,19)}}
                    </p>
                </van-col>
            </van-row>
            <van-row v-if="article.aCoverImg == null">
                <van-col span="24" style="height:110px">
                    <p class="font-size-box">{{article.aTitle}}</p>
                    <p>
                        {{getContentTxt(index,50)}}
                    </p>
                </van-col>
            </van-row>
           <van-row  class="font-color-gery">
                    <van-col span="6">
                        在浙APP
                    </van-col>
                    <van-col span="13">
                        {{getTime(article.gmtCreate)}}
                    </van-col>
                    <van-col  span="4" offset="1">
                        <van-icon name="eye-o" />13
                    </van-col>
            </van-row>
        </van-cell-group>
    </div>
</template>
<script>
import {listArticleByType4} from '../../api/article'
export default {
    name:'NewsMsg',
    data(){
        return{
            articles:[]
        }
    },
    methods:{
         getContentTxt(index,num){
          if(this.articles[index].aContentTxt=='' || this.articles[index].aContentTxt == null){
              return this.articles[index].aContentTxt
          }
          if(this.articles[index].aContentTxt.length<num){
              return this.articles[index].aContentTxt
          }else{
              //console.log(this.articles[index].aContentTxt.length,'length')
              var text=this.articles[index].aContentTxt.substring(0,num)+'...'
              return text
          }
      },
      getArticle(val){
          this.$router.push({
              path: '/article',
              query:{
                  aId: val
              }
          })
      },
      getTime(val){
          return val.substring(0, val.indexOf(' '))
      }
    },
    created(){
        listArticleByType4().then(res=>{
            this.articles=res.data.data
        })
    }
}
</script>
<style scoped>
#newsMsg{
     width: 100%;
    height: 100%;
    overflow: auto;
    background-color: #F7F8FA;
}
p{
    margin: 0;
    padding: 0;
}
.font-size-box{
    margin-top: 10px;
    font-size: 18px;
    font-weight: 600;
}
.font-color-gery{
    color: grey;
}
.margin-top-10{
    margin-top: 1rem;
    padding: 0.7rem 1.5rem;
}
div,p{
    word-wrap:break-word;
}
</style>