import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/login/Login.vue'
import Index from '../views/index/Index.vue'
import City from '../views/city/City.vue'
import Article from '../views/article/Article.vue'
import Register from '../views/login/Register.vue'
import FindPassword from '../views/login/FindPassword.vue'
import UserMsg from '../views/user/UserMsg.vue'
import Settings from '../views/user/Settings.vue'
import Task from '../views/task/Task.vue'
import LeaveMsg from '../views/message/LeaveMessage.vue'
const routes = [
  {
    path:'/login',
    name: 'Login',
    meta:{
      title: '登录',
    },
    component: Login
  },
  {
    path:'/register',
    name: 'Register',
    meta:{
      title: '注册',
    },
    component: Register
  },
  {
    path:'/findPassword',
    name: 'FindPassword',
    meta:{
      title: '找回密码',
    },
    component: FindPassword
  },
  {
    path:'/',
    name: 'Index',
    meta:{
      title: '首页',
    },
    component: Index
  },
  {
    path:'/message',
    name: 'Message',
    meta:{
      title: '留言',
    },
    component: LeaveMsg
  },
  {
    path:'/city',
    name: 'City',
    meta:{
      title: '城市详情',
    },
    component: City
  },
  {
    path:'/article',
    name: 'Article',
    meta:{
      title: '文章',
    },
    component: Article
  },
  {
    path:'/userMsg',
    name: 'UserMsg',
    meta:{
      title: '我的',
    },
    component: UserMsg
  },
  {
    path:'/settings',
    name: 'Settings',
    meta:{
      title: '设置',
    },
    component: Settings
  },
  {
    path:'/task',
    name: 'Task',
    meta:{
      title: '任务',
    },
    component: Task
  },
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()

})


export default router
