<template>
    <div id="videoNews">
        <van-cell-group inset v-for="(video,index) in videos" @click="addScore">
            <van-row>
                <van-col span="24">
                    <video :src="video.src" class="videoClass" controls></video>
                </van-col>
            </van-row>
            <van-row>
                <van-col span="24">
                     <p class="font-size-box">
                         {{video.title}}
                     </p>
                </van-col>
            </van-row>
            <van-row   class="font-color-gery">
                <van-col span="5">在浙APP</van-col>
                <van-col span="12">{{video.time}}</van-col>
            </van-row>
            <br>
        </van-cell-group>
    </div>
</template>
<script>
export default {
    name: 'videoNews',
    data(){
        return{
            videos:[
                {src:'https://v-cdn.zjol.com.cn/278652.mp4?userId=8d94ef24-c166-4031-af0e-a542de96340d',title:'红色的力量 爱国主义教育基地宣传短视频',time:'2021年6月8日'},
                {src:'https://v-cdn.zjol.com.cn/277263.mp4?userId=374b5399-3511-4f06-a868-d888289fcdc9',title:'嘉兴平湖市林埭镇保丰村文化礼堂迎国庆活动',time:'2021年7月14日'},
                {src:'https://v-cdn.zjol.com.cn/277178.mp4?userId=11381f4e-8b8d-4d0b-9f8a-2f47420410b7',title:'台州市黄岩文化礼堂迎国庆学生剪纸活动',time:'2021年7月25日'},
                {src:'https://v-cdn.zjol.com.cn/277258.mp4?userId=403654bc-e0a0-4538-82c2-697905ed8d64',title:'金华东阳市红军长征博览城升国旗',time:'2021年8月23日'},
                {src:'https://v-cdn.zjol.com.cn/277166.mp4?userId=e9ecc7ab-4aa3-45e0-bb93-f872b3d90614',title:'舟山岱山金维映故居群众合唱《我和我的祖国》',time:'2021年9月15日'}
            ],
            vId:1,
            show: false,
            showVideo:'',
            showTitle:''
        }
    },
    methods:{
       addScore(){
           if(this.articleDetail.aType==1){
                let score=window.localStorage.getItem('video')
                if(score==null || score==''){
                    window.localStorage.setItem('video',0)
                }else{
                    let num=Integer.parseInt(score)+1
                     window.localStorage.setItem('video',num)
                }
            }
       }
    }
}
</script>
<style scoped>
#videoNews{

}
.videoClass{
    width: 100%;
    height: 200px;
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
.big-box{
     display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}

</style>