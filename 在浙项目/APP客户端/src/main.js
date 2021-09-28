import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

import './axios'

//局部引入即可引入动画anime.js 
//import anime from 'animejs/lib/anime.es.js';

//引入Vant组件
import '@vant/touch-emulator';
import { Image as VanImage } from 'vant'
import { Search } from 'vant'
import { Sticky } from 'vant'
import { Icon } from 'vant'
import { Tab, Tabs } from 'vant';
import { Dialog } from 'vant';
import { Tabbar, TabbarItem } from 'vant';
import { Lazyload } from 'vant';
import { Swipe, SwipeItem } from 'vant';
import { Cell, CellGroup } from 'vant';
import { Grid, GridItem } from 'vant';
import { Step, Steps } from 'vant';
import { List } from 'vant';
import { Card } from 'vant';
import { Divider } from 'vant';
import { Tag } from 'vant';
import { Button } from 'vant';
import { NavBar } from 'vant';
import { PullRefresh } from 'vant';
import { Col, Row } from 'vant';
import { Popup } from 'vant';
import { ShareSheet } from 'vant';
import { Empty } from 'vant';
import { Field} from 'vant';
import { ActionSheet } from 'vant';
import { Progress } from 'vant';
import { Form } from 'vant';


const app=createApp(App)

//配置全局vuex和axios
app.config.globalProperties.$store=store
app.config.globalProperties.$axios=axios

//引入vuex
app.use(store)
//引入router路由
app.use(router)
//引入图片组件
app.use(VanImage)
//引入搜索框组件
app.use(Search)
//引入固定位置的粘性布局
app.use(Sticky)
//引入图标组件
app.use(Icon)
//引入Tab标签页
app.use(Tab)
app.use(Tabs)
//引入提示框
app.use(Dialog)
//标签栏
app.use(Tabbar);
app.use(TabbarItem);
//懒加载
app.use(Lazyload);
//轮播
app.use(Swipe);
app.use(SwipeItem);
//单元格
app.use(Cell);
app.use(CellGroup);
//宫格
app.use(Grid);
app.use(GridItem);
//步骤条
app.use(Step);
app.use(Steps);
//列表
app.use(List);
//卡片
app.use(Card);
//分隔符
app.use(Divider);
//标签
app.use(Tag);
//按钮
app.use(Button);
//导航栏
app.use(NavBar);
//下拉刷新
app.use(PullRefresh)
//布局
app.use(Col);
app.use(Row);
//弹出层
app.use(Popup);
//分享面板
app.use(ShareSheet);
//空状态
app.use(Empty);
//输入框
app.use(Field);
//动作面板
app.use(ActionSheet);
//进度条
app.use(Progress);
//表单
app.use(Form);



app.mount('#app')
