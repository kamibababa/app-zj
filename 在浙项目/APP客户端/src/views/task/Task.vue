<template>
    <div id="task">
        <van-sticky :offset-top="0">
            <van-nav-bar
            title="任务"
            left-arrow
            @click-left="onClickLeft"
            />
        </van-sticky>
        <br>
        <div v-if="!loginStatus">
            <van-cell-group inset @click="turnToLogin">
                <van-row class="header_box">
                    <van-col span="8">
                        <van-image
                            width="50px"
                            height="50px"
                            :src="userImgSrc"
                            class="userImgBox outlineUserImg"
                            />
                    </van-col>
                    <van-col span="16">
                        <h4>未登录</h4>
                        <p>段位:{{DuanWei}}</p>
                    </van-col>
                </van-row>
                <van-row>
                    <van-col span="8" class="box-center">
                        <van-cell>
                            <template #title>
                                <van-icon name="star" />&nbsp;收藏  
                            </template>
                        </van-cell>
                    </van-col>
                    <van-col span="8" class="box-center">
                        <van-cell>
                            <template #title>
                                <van-icon name="like" />&nbsp;喜欢
                            </template>
                        </van-cell>
                    </van-col>
                    <van-col span="8" class="box-center">
                        <van-cell >
                            <van-icon name="coupon" />&nbsp;历史
                        </van-cell>
                    </van-col>
                </van-row>
            </van-cell-group>
        </div>
        <div v-if="loginStatus">
            <van-cell-group inset>
                <van-row class="header_box">
                    <van-col span="8" >
                        <van-image
                            width="50px"
                            height="50px"
                            :src="userImgSrc1"
                            class="userImgBox outlineUserImg  white-color"
                            />
                    </van-col>
                    <van-col span="16">
                        <h4>{{userMsg.rName}}</h4>
                        <p style="color:#C82518">段位:{{duanWei}}</p>
                    </van-col>
                </van-row>
                <van-row>
                    <van-col span="8" class="box-center">
                        <van-cell>
                            <template #title>
                                <van-icon name="star" color="#C82518"/>&nbsp;收藏  
                            </template>
                        </van-cell>
                    </van-col>
                    <van-col span="8" class="box-center">
                        <van-cell>
                            <template #title>
                                <van-icon name="like" color="#C82518"/>&nbsp;喜欢
                            </template>
                        </van-cell>
                    </van-col>
                    <van-col span="8" class="box-center">
                        <van-cell >
                            <van-icon name="coupon" color="#C82518"/>&nbsp;历史
                        </van-cell>
                    </van-col>
                </van-row>
            </van-cell-group>
        </div>
        <br>
        <van-cell-group inset>
            <van-cell>
                <template #title>
                    <h2 class="font-color">&nbsp;&nbsp;{{scoreNum}}积分</h2>
                </template>
                <template #value>
                    <van-image
                    width="50"
                    height="50"
                    :src="scoreImg"
                    style="background-color:red;border-radius:10px"
                    />
                </template>
            </van-cell>
        </van-cell-group>
        <br>
        <van-cell-group inset>
            <van-cell title="任务记录" class="font-color"/>
            <van-cell v-for="city in cities">
                <template #title>
                    {{city.name}}
                </template>
                <template #value>
                    {{city.learnBefore}}/{{city.learnAfter}}
                </template>
                <template #label>
                    <van-progress stroke-width="5px" pivot-text="" :percentage="city.learnBefore/city.learnAfter*100 | numFilter" color="#ee0a24"/>
                </template>
            </van-cell>
        </van-cell-group>
        <br/>
         <van-cell-group inset>
            <van-cell title="文章学习" class="font-color"/>
            <van-cell v-for="article in articles">
                <template #title>
                    {{article.name}}
                </template>
                <template #value>
                    {{article.learnBefore}}/{{article.learnAfter}}
                </template>
                <template #label>
                    <van-progress stroke-width="5px" pivot-text="" :percentage="article.learnBefore/article.learnAfter*100 | numFilter" color="#ee0a24"/>
                </template>
            </van-cell>
        </van-cell-group>
        <br/>
         <van-cell-group inset>
            <van-cell title="视频学习" class="font-color"/>
            <van-cell>
                <template #title>
                    {{video.name}}
                </template>
                <template #value>
                    {{video.learnBefore}}/{{video.learnAfter}}
                </template>
                <template #label>
                    <van-progress stroke-width="5px" pivot-text="" :percentage="video.learnBefore/video.learnAfter*100 | numFilter" color="#ee0a24"/>
                </template>
            </van-cell>
        </van-cell-group>
        <br/>
        <br/>
    </div>
</template>
<script>
export default {
    name:'Task',
    data(){
        return{
            userImgSrc:require('../../assets/home/zj/zj1.png'),
            userImgSrc1:require('../../assets/home/zj/zj.png'),
            loginStatus:false,
            duanWei:'未知段位',
            scoreNum:0,
            scoreImg:require('../../assets/home/logo/logo.png'),
            userMsg:{},
            cities:[
                {name: "杭州市",learnBefore:0,learnAfter:5,path:'1'},
                {name: "湖州市",learnBefore:0,learnAfter:5,path:'2'},
                {name: "嘉兴市",learnBefore:0,learnAfter:5,path:'3'},
                {name: "金华市",learnBefore:0,learnAfter:5,path:'4'},
                {name: "丽水市",learnBefore:0,learnAfter:5,path:'5'},
                {name: "宁波市",learnBefore:0,learnAfter:5,path:'6'},
                {name: "衢州市",learnBefore:0,learnAfter:5,path:'7'},
                {name: "绍兴市",learnBefore:0,learnAfter:5,path:'8'},
                {name: "台州市",learnBefore:0,learnAfter:5,path:'9'},
                {name: "温州市",learnBefore:0,learnAfter:5,path:'10'},
                {name: "舟山市",learnBefore:0,learnAfter:5,path:'11'}
            ],
            articles:[
                {name:"红色基地",learnBefore:0,learnAfter:8},
                {name:"革命英雄",learnBefore:0,learnAfter:8},
                {name:"要闻",learnBefore:0,learnAfter:8}
            ],
            video:{
                name: "视频学习",learnBefore:0,learnAfter:3
            }
        }
    },
    methods:{
        onClickLeft(){
            this.$router.go(-1)
        },
        getUserMsg(){
            this.userMsg=this.$store.state.userInfo
            if(this.userMsg==null || this.userMsg==''){
                this.loginStatus=false
            }else{
                this.loginStatus=true
                if(this.userMsg.rName==''||this.userMsg.rName==null){
                    this.userMsg.rName = '暂无昵称'
                }
                this.getCityScore()
                this.getBaseScore()
                this.getPeopleScore()
                this.getMoreScore()
                this.getVideoScore()
                this.getAllscore()
                this.getPart()
            }
        },
        getCityScore(){
            for(let i=0;i<this.cities.length;i++){
                if(window.localStorage.getItem('city'+this.cities[i].path)==null || window.localStorage.getItem('city'+this.cities[i].path)==''){
                    window.localStorage.setItem('city'+this.cities[i].path,0)
                }else{
                    if(window.localStorage.getItem('city'+this.cities[i].path)>5){
                        this.cities[i].learnBefore=5
                    }else{
                        this.cities[i].learnBefore=window.localStorage.getItem('city'+this.cities[i].path)
                    }
                }
            }
        },
        getBaseScore(){
            if(window.localStorage.getItem('base')==null || window.localStorage.getItem('base')==''){
                 window.localStorage.setItem('base',0)
            }else{
                if(window.localStorage.getItem('base')>8){
                    this.articles[0].learnBefore=8
                }else{
                    this.articles[0].learnBefore=window.localStorage.getItem('base')
                }
            }
        },
        turnToLogin(){
            this.$router.push({
                path: '/login'
            })
        },
        getPeopleScore(){
             if(window.localStorage.getItem('people')==null || window.localStorage.getItem('people')==''){
                 window.localStorage.setItem('people',0)
            }else{
                if(window.localStorage.getItem('people')>8){
                    this.articles[1].learnBefore=8
                }else{
                    this.articles[1].learnBefore=window.localStorage.getItem('people')
                }
            }
        },
        getMoreScore(){
            if(window.localStorage.getItem('more')==null || window.localStorage.getItem('more')==''){
                 window.localStorage.setItem('more',0)
            }else{
                if(window.localStorage.getItem('more')>8){
                    this.articles[2].learnBefore=8
                }else{
                     this.articles[2].learnBefore=window.localStorage.getItem('more')
                }
            }
        },
        getVideoScore(){
            if(window.localStorage.getItem('video')==null || window.localStorage.getItem('video')==''){
                 window.localStorage.setItem('video',0)
            }else{
                if(window.localStorage.getItem('video')>3){
                    this.video.learnBefore=3
                }else{
                     this.video.learnBefore=window.localStorage.getItem('video')
                }
            }
        },
        getAllscore(){
            for(let i=0;i<this.cities.length;i++){
                this.scoreNum+=Number(this.cities[i].learnBefore)*5
            }
            for(let i=0;i<this.articles.length;i++){
                this.scoreNum+=Number(this.articles[i].learnBefore)*5
            }
            this.scoreNum+=Number(this.video.learnBefore)*5
        },
        getPart(){
            if(this.scoreNum<=40){
                this.duanWei='热血青铜'
            }else if(this.scoreNum>40 &&this.scoreNum<=50){
                this.duanWei='不屈白银'
            }else if(this.scoreNum>50 &&this.scoreNum<=60){
                this.duanWei='英勇黄金'
            }else if(this.scoreNum>60 &&this.scoreNum<=70){
                this.duanWei='坚韧铂金'
            }else if(this.scoreNum>70 &&this.scoreNum<=80){
                this.duanWei='不朽星钻'
            }else if(this.scoreNum>80 &&this.scoreNum<=90){
                this.duanWei='荣耀皇冠'
            }else if(this.scoreNum>90){
                this.duanWei='超级王牌'
            }
        }
    },
    filters:{
        numFilter(value){
            let realVal= ' '
            if (!isNaN(value) && value !== ' '){
                realVal = parseFloat(value).toFixed(1)
            }else{
                realVal = '--'
            }
            return realVal
        }
    },
    created(){
        this.getUserMsg()

    }
}
</script>
<style scoped>
#task{
    overflow: auto;
    height:100%;
    width:100%;
    position:fixed;
    background-color: #F7F8FA;
}
.userImgBox{
    position: relative;
    border-radius: 10px;
    top: 26%;
    left: 23%;
}
.outlineUserImg{
    background-color: lightgray;
}
p{
    padding: 0;
    margin: 4px;
}
h4{
    padding: 0;
    margin: 5px;
    margin-top: 25px;
}
.header_box{
    height: 100px;
}
.box-center{
    text-align: center;
}
.white-color{
    background-color: white;
    border: 1px solid lightgray;
}
.font-color{
    color: #C82518;
}
</style>