import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8081', // api的base_url
  headers:{
    'Authorization':''
  },
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  const token = window.localStorage.getItem("token");
	if(token!=null){
		config.headers.Authorization = token
	}
	console.log("前置拦截器",config);
	return config;
}, error => {
  // Do something with request error
  console.log('前置拦截器error',error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    console.log("后置拦截器",response);
    const res = response.data
    if (res.code !== 200) {
      Message({
        message: res.msg,
        type: 'error',
        duration: 5 * 1000
      })
      // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      if (res.code === 400 || res.code === 401 || res.code === 404 || res.code == 405 || res.code == 500) {
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()// 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      return Promise.reject('error')
    } else {
      return response
    }
  },
  error => {
    console.log('失败')
    //未授权：登录失败 
		if(error.response.status === 401){
			router.push("/login");
		}
    console.log('err' + error)// for debug
    Message({
      message: error.msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
