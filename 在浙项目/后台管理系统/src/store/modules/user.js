import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'

const user = {
  state: {
    token: localStorage.getItem("token"),
    name: '',
    avatar: '',
    roles: [],
    userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem("token",token);
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_USERINFO:(state,userInfo)=>{
		  state.userInfo=userInfo;
		  //浏览器关闭状态会丢失，但是token还在
		  sessionStorage.setItem("userInfo",JSON.stringify(userInfo));
	  },
    REMOVE_INFO:(state)=>{
		  state.token='';
		  state.userInfo={};
		  localStorage.setItem("token","");
		  sessionStorage.setItem("userInfo",JSON.stringify(""));
	  } 

  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      //console.log('userInfo',userInfo)
      const uEmail = userInfo.uEmail.trim()
      return new Promise((resolve, reject) => {
        login(uEmail, userInfo.uPassword).then(response => {
          const data = response
          setToken(data.headers['authorization'])
          //console.log('执行1')
          commit('SET_TOKEN', data.headers['authorization'])
          commit("SET_USERINFO",response.data.data)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        const userInfo=JSON.parse(sessionStorage.getItem("userInfo"))
          //console.log('信息',userInfo)
          if (userInfo.rName !== '' && userInfo.rName !== null) { // 验证返回的roles是否是一个非空数组
            const roles=[]
            roles.push(userInfo.rName)
            commit('SET_ROLES', roles)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }
          commit('SET_NAME', userInfo.uUsername)
          //console.log('userInfo',state.name)
          resolve(userInfo)
        }).catch(error => {
          reject(error)
        })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },
    // 动态修改权限
    ChangeRoles({ commit }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getInfo(role).then(response => {
          const data = response
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          resolve()
        })
      })
    }
  }
}

export default user
