<template>
    <div id="article">
        <van-sticky :offset-top="0">
            <van-nav-bar
                left-arrow
                @click-left="onClickLeft"
                @click-right="onClickRight"
            >
                <template #title>
                    <div style="width:32px;height:31px;background-color:red">
                        <img src="../../assets/home/logo/logo.png" width="30" alt="">
                    </div>
                </template>
                <template #right>
                    <van-icon name="share-o" size="18" />
                </template>
            </van-nav-bar>
        </van-sticky>
        <van-cell-group>
            <van-cell :title="articleDetail.aTitle" size="large" label="来源：'在浙'APP"/> 
            <van-cell  title="">
                <template #label>
                    <div style="color:black;" v-html="articleDetail.aContent"></div>
                    <van-row>
                        <van-col offset="10" span="14">
                            <span>更新时间:</span>{{articleDetail.gmtCreate}}
                        </van-col>
                    </van-row>
                </template>
            </van-cell>
            <van-cell title="">
                <template #label>
                    <van-row>
                        <van-col span="10" offset="1" style="background-color:#F7F8FA;height:30px;text-align:center;line-height:30px">
                            <span>阅读:</span><span style="color:black">{{look}}</span>
                        </van-col>
                        <van-col span="10" offset="2" style="background-color:#F7F8FA;height:30px;text-align:center;line-height:30px" @click="changeStar">
                            <span>支持:</span><span  style="color:black">{{star}}</span><span>&nbsp;&nbsp;&nbsp;<van-icon :name="starImg" color="red"/></span>
                        </van-col>
                    </van-row>
                </template>
            </van-cell>
             <van-cell title="">
                <template #label>
                    <h4><span style="display:inline-block;width:5px;height:12px;background-color:red;line-height:12px"></span>&nbsp;观点</h4>
                </template>
            </van-cell>
            <van-cell v-if="messages.length==0" title="">
                <template #label>
                    <!--空数据显示-->
                    <van-empty description="暂无观点，快来发表观点"/>
                </template>
            </van-cell>
        </van-cell-group>
        <van-cell-group inset v-if="messages.length!==0" >
            <van-row v-for="(msg,index) in messages" style="border:2px solid lightgrey;margin-top:10px;border-radius:10px">
                <van-col span="24">
                    <van-cell>
                        <template #title>
                            <span style="font-size:10px;color:grey">
                                用户:{{msg.uName}}
                                </span>
                        </template>
                        <template #value>
                            <span style="font-size:10px;color:grey">
                                {{index+1}}楼
                            </span>
                        </template>
                        <template #label>
                            <div style="width:100%">
                                <h3>
                                    {{msg.aTitle}}
                                </h3>
                                <p>
                                    {{msg.mContent}}
                                </p>
                            </div>
                        </template>
                    </van-cell>
                    <van-cell title="">
                            <template #title>
                                <p style="margin:0;padding:0;font-size:8px;color:grey">
                                    {{msg.gmtCreate}}
                                </p>
                            </template>
                            <template #value>
                                <van-icon name="like" color="red" />{{msg.num}}
                            </template>
                    </van-cell>
                </van-col>
            </van-row>
        </van-cell-group>
        <!--分享面板-->
        <van-share-sheet
            v-model:show="showShare"
            title="立即分享给好友"
            :options="options"
            @select="onSelect"
            />
        <!--底部-->
        <van-row class="box-bootem">
            <van-col span="18">
                <van-field
                v-model="setMsg.mContent"
                type="text"
                placeholder="欢迎发表你的观点"
                show-word-limit
                class="inputContent"
                />
            </van-col>
            <van-col span="3">
                <van-button  type="default" size="small" @click="saveMsg()">发布</van-button>
            </van-col>
            <van-col span="3">
                <van-button  icon="star-o" size="small" type="default" ></van-button>
            </van-col>
        </van-row>
       
    </div>
</template>
<script>
import {getArticleById, getArticleRedisLookById,getArticleStar,getArticleAddStar,removeArticleStar} from '../../api/article'
import {listArticleMsg,saveMsgByArticle,getMessageWithoutClickStar} from '../../api/message'
import { Toast } from 'vant';
export default {
    name:'article',
    data(){
        return{
            showShare:false,
            options:[
                { name: '微博', icon: 'weibo' },
                { name: 'QQ', icon: 'qq' }
            ],
            starImg:"like-o",
            talk:[],
            articleDetail:{},
            messages:[],
            setMsg:{
                mArticleId:'',
                mContent:'',
                mUserId:''
            },
            look:0,
            star:0,
            index:true
        }
    },
    methods:{
        onClickLeft(){
            this.$router.go(-1);
        },
        onClickRight(){
            this.showShare=!this.showShare
        },
        onSelect(option){
            let path=this.$axios.defaults.baseURL+this.$route.path
            if(option.name==='微博'){
                window.open('http://service.weibo.com/share/share.php?url='+path+'&title=在浙APP')
            }else if(option.name==='QQ'){
                window.open('http://connect.qq.com/widget/shareqq/index.html?url='+path+'&sharesource=qzone&title=在浙APP')
            }
        },
        changeStar(){
            if(this.index){
                if(this.starImg=="like-o"){
                    this.starImg="like"
                }
                getArticleAddStar(this.$route.query.aId,this.$store.state.userInfo.uId).then(res=>{
                    this.star=res.data.data
                    this.index=false
                })
            }else{
                if(this.starImg == "like"){
                    this.starImg="like-o"
                }
                removeArticleStar(this.$route.query.aId,this.$store.state.userInfo.uId).then(res=>{
                    this.star=res.data.data
                    this.index=true
                })
            }
        },
        saveMsg(){
            const that=this
            if(this.$store.state.userInfo!==null && this.$store.state.userInfo!==''){
                that.setMsg.mUserId=that.$store.state.userInfo.uId
                saveMsgByArticle(that.setMsg).then(res=>{
                    Toast(res.data.data);
                })
                this.$router.go(0)
            }else{
                 that.$dialog.confirm({
                message: '您未登录，请前往登录',
                })
                .then(() => {
                    that.$router.push({
                        path:'/login'
                    })
                })
                .catch(() => {
                    // on cancel
                });
            }
        }
    },
    created(){
        if(this.$route.query.aId!==null && this.$route.query.aId!==''){
            this.setMsg.mArticleId=this.$route.query.aId
        }
        if(this.$store.state.userInfo.uId !==''&&this.$store.state.userInfo.uId !==null){
            getArticleRedisLookById(this.$route.query.aId,this.$store.state.userInfo.uId).then(res=>{
                this.look=res.data.data
            })
        }
        getArticleById(this.$route.query.aId).then(res=>{
            this.articleDetail=res.data.data
            if(this.articleDetail.aType==1){
                let score=window.localStorage.getItem('city'+this.articleDetail.aCityId)
                if(score==null || score==''){
                    window.localStorage.setItem('city'+this.articleDetail.aCityId,0)
                }else{
                    let num=Number(score)+1
                     window.localStorage.setItem('city'+this.articleDetail.aCityId,num)
                }
            }else if(this.articleDetail.aType==2){
                 let baseScore=window.localStorage.getItem('base')
                 if(baseScore==null|| baseScore ==''){
                     window.localStorage.setItem('base',0)
                 }else{
                     let num=Number(baseScore)+1
                     window.localStorage.setItem('base',num)
                 }
            }else if(this.articleDetail.aType==3){
                let peopleScore=window.localStorage.getItem('people')
                 if(peopleScore==null|| peopleScore ==''){
                     window.localStorage.setItem('people',0)
                 }else{
                     let num=Number(peopleScore)+1
                     window.localStorage.setItem('people',num)
                 }
            }else if(this.articleDetail.aType==4){
                let moreScore=window.localStorage.getItem('more')
                 if(moreScore==null|| moreScore ==''){
                     window.localStorage.setItem('more',0)
                 }else{
                     let num=Number(moreScore)+1
                     window.localStorage.setItem('more',num)
                 }
            }
        })
        getArticleStar(this.$route.query.aId).then(res=>{
            this.star=res.data.data
        })
        listArticleMsg(this.$route.query.aId).then(res=>{
            this.messages=res.data.data
        })
    }
}
</script>
<style scoped>
#article{

}
@font-face {
		font-family:'huawen';
		src: url('../../assets/home/font/huawen.ttf');
}
.inputContent{
    display: inline-block;
    width: 70%;
    height: 2rem;
    line-height: 2rem;
    overflow: hidden;
    font-family: huawen;
}
.box-bootem{
    position:fixed;
    width: 100%;
    height: 30px;
    bottom: 0;
    background-color: white;
}
</style>