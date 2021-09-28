import { createStore } from 'vuex'

export default createStore({
  state: {
    token: localStorage.getItem("token"),
	userInfo: JSON.parse(localStorage.getItem("userInfo"))
  },
  mutations: {
    //==Java里的set方法
	  SET_TOKEN:(state,token)=>{
		  state.token=token;
		  localStorage.setItem("token",token);
	  },
	  SET_USERINFO:(state,userInfo)=>{
		  state.userInfo=userInfo;
		  //浏览器关闭状态会丢失，但是token还在
		  localStorage.setItem("userInfo",JSON.stringify(userInfo));
	  },
	  REMOVE_INFO:(state)=>{
		  state.token='';
		  state.userInfo={};
		  localStorage.setItem("token","");
		  localStorage.setItem("userInfo",JSON.stringify(""));
	  } 
  },
  actions: {
     //==Java里的get方法
	  getUserInfo:(state)=>{
		  return state.userInfo;
	  },
	  getToken:(state)=>{
		  return state.token;
	  }
  },
  modules: {
  }
})
