<template>
    <div id="index">
        <van-sticky :offset-top="0">
            <headers></headers>
        </van-sticky>
        <indexTab></indexTab>
        <van-sticky :offset-bottom="0">
            <footers></footers>
        </van-sticky>
    </div>
</template>
<script>
import headers from '../../components/Header'
import footers from '../../components/Footer'
import indexTab from '../../components/index/IndexTab'

export default {
    name: 'Index',
    components:{
        headers,
        footers,
        indexTab
    }
}
</script>
<style>
#index{
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
}
</style>