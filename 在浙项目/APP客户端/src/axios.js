import axios  from 'axios'
import qs from 'qs';
import router from './router'
import store from './store'


// 请求超时时间
axios.defaults.timeout = 10000;
axios.defaults.baseURL = "http://192.168.43.191:8081";
axios.defaults.crossDomain = true;
// post请求头
axios.defaults.headers.common['Authorization'] = '';

//前置拦截，在发起请求的时候的配置，在config中配置请求头等等的信息
axios.interceptors.request.use(config => {
	var token = localStorage.getItem("token");
	if(token !== null && token!== "undefined"){
		config.headers.Authorization = token
	}else{
        config.headers.Authorization=''
    }
	console.log("前置拦截器");
	console.log(config)
	return config;
},
  error => {
    return Promise.error(error);
  });

  //后置拦截
axios.interceptors.response.use(response => {
    console.log("后置拦截器",response);
    let res = response.data;
    if (res.code === 200) {
        return response;
    } else {
        //结束请求
        return Promise.reject(res);
    }
},
error => {
    //未授权：登录失败 
    if(error.response.status === 401){
        store.commit("REMOBVE_INFO");
        router.push("/login");
    }
    //结束请求
    return Promise.reject(error);
    
}
);

/** 
 * get方法，对应get请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
 export function get(url, params){    
    return new Promise((resolve, reject) =>{        
        axios.get(url, {            
            params: params        
        })        
        .then(res => {            
            resolve(res);        
        })        
        .catch(err => {            
            reject(err)        
        })    
    });
}

export function getWithPath(url){    
    return new Promise((resolve, reject) =>{        
        axios.get(url)
        .then(res => {            
            resolve(res);        
        })        
        .catch(err => {            
            reject(err)        
        })    
    });
}

/** 
 * post方法，对应post请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
export function post(url, params= {}, json = false) {    
    // json格式请求头
    const headerJSON = {
        "Content-Type": "application/json"
    };
    // FormData格式请求头
    const headerFormData = {
        "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
    };
    return new Promise((resolve, reject) => {         
        axios
        .post(url, json ? JSON.stringify(params) : qs.stringify(params), {
          headers: json ? headerJSON : headerFormData
        })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err.response.data.msg);
        });   
    });
}
