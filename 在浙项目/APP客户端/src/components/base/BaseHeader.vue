<template>
    <div id="base_header">
        <van-cell-group inset v-for="(article,index) in articles" class="margin-top-10" @click="turnToArticle(article.aId)">
            <van-row v-if="article.aCoverImg !==null ">
                <van-col span="16">
                    <p class="font-size-box">{{article.aTitle}}</p>
                    <p>
                        {{getContentTxt(index,20)}}
                    </p>
                </van-col>
                <van-col span="8">
                    <img :src="article.aCoverImg" style="width:110px;height:110px;margin:0 auto" alt="">
                </van-col>
            </van-row>
            <van-row v-if="article.aCoverImg === null ">
                <van-col span="24" style="height:110px">
                    <p class="font-size-box">{{article.aTitle}}</p>
                    <p>
                        {{getContentTxt(index,30)}}
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
import {listArticleByType2} from '../../api/article'
export default {
    name: 'BaseHeader',
    data() {
    return {
        articles:[]
    };
  },
  methods: {
      getContentTxt(index,num){
          if(this.articles[index].aContentTxt=='' || this.articles[index].aContentTxt == null){
              return this.articles[index].aContentTxt
          }
          if(this.articles[index].aContentTxt.length<num){
              return this.articles[index].aContentTxt
          }else{
              console.log(this.articles[index].aContentTxt.length,'length')
              var text=this.articles[index].aContentTxt.substring(0,num)+'...'
              return text
          }
      },
      turnToArticle(id){
          this.$router.push({
              path: '/article',
              query:{
                  aId:id
              }
          })
      },
      getTime(val){
          return val.substring(0, val.indexOf(' '))
      }
  },
  created(){
      listArticleByType2().then(res=>{          
          this.articles=res.data.data
          console.log(this.articles)
      })
  }
}
</script>
<style scoped>
#base_header{
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