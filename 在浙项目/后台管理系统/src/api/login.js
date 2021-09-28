import request from '@/utils/request'

export function login(uEmail, uPassword) {
  return request({
    url: '/login',
    method: 'post',
    data: {
      uEmail,
      uPassword
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
