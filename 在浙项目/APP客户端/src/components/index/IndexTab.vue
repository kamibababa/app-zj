<template>
    <div id="index_tab" class="big_box">
        <van-tabs  v-model="active" swipeable scrollspy="true">
            <van-tab title="首页">
               <div class="index_tab_main">
                    <index-zj></index-zj>
                    <index-header></index-header>
               </div>
            </van-tab>
            <van-tab title="基地" scrollspy="true">
               <div class="index_tab_main">
                    <base-header></base-header>
               </div>
            </van-tab>
            <van-tab title="烈士" scrollspy="true">
                 <div class="index_tab_main">
                    <red-people></red-people>
                </div>
            </van-tab>
            <van-tab title="要闻" scrollspy="true">
                <div class="index_tab_main">
                     <news-msg></news-msg>
                </div>
            </van-tab>
            <van-tab title="漫画党史" scrollspy="true">
                <div class="index_tab_main">
                     <red-history></red-history>
                </div>
            </van-tab>
            <van-tab title="纪实" scrollspy="true">
                <div class="index_tab_main">
                     <video-news></video-news>
                </div>
            </van-tab>
        </van-tabs>  
    </div>
</template>
<script>
import indexZj from './IndexZj'
import indexHeader from './IndexHeader'
import baseHeader from '../base/BaseHeader'
import RedPeople from '../people/RedPeople'
import RedHistory from '../history/RedHistory'
import VideoNews from '../video/VideoNews'
import NewsMsg from '../news/NewsMsg'
export default {
    name: 'IndexTab',
    data() {
        return {
            active: 0,
        };
    },
    components:{
        indexZj,
        indexHeader,
        baseHeader,
        RedPeople,
        RedHistory,
        VideoNews,
        NewsMsg
    }
}
</script>
<style scoped>
#index_tab{
     width: 100%;
     position: fixed;
     top: 4rem;
}
.index_tab_main{
	width: 100%;
	height:80vh;
	overflow: auto;
}
</style>