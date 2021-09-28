<template>
    <div id="index_cj">
        <br>
        <van-cell-group>
            <van-swipe :autoplay="3000" >
                <van-swipe-item v-for="(data, index) in hotData" :key="index" @change="onChange"  stop-propagation="false">
                    <img v-lazy="data.img" style="width:90%;height:10rem;margin-left:5%"/>
                    <van-cell :title="data.title" label="在浙江部门单位的“服务指数”正在有效转化为群众企业基层的“幸福指数”。党史学习教育开展以来，我省坚持..." />
                    <p style="height:1px"></p>
                </van-swipe-item>
            </van-swipe>
        </van-cell-group>
    </div>
</template>
<script>
import { Toast } from 'vant';
export default {
    name: 'IndexZj',
    data() {
        return {
            hotData: [
                {img:'http://www.zj.gov.cn/picture/0/2107210929319312803.png',title:'我省不断深化党史学习教育“三为”专题实践活动'},
                {img:'http://www.zj.gov.cn/picture/0/2108191404147316092.png',title:'我省以高质量公共文化服务推动人民精神富足'},
            ],
            current: 0,
            observeParents:true,
            observer:true
        };
     },
     methods: {
        onChange(index) {
            Toast('当前 Swipe 索引：' + index);
        },
    },
}
</script>
<style scoped>
#index_cj{
    width: 100%;
    height: 20rem;
    overflow: hidden;
}
</style>