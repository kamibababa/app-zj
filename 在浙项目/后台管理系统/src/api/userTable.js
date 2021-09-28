import request from '@/utils/request'

export function getUserListPage(params) {
  return request({
    url: '/user/list/with/search',
    method: 'get',
    params: params
  })
}

export function getUserList() {
  return request({
    url: '/user/list',
    method: 'get'
  })
}


export function getUserListPageDeleted(params) {
  return request({
    url: '/user/list/with/search/deleted',
    method: 'get',
    params: params
  })
}

export function removeUser(params) {
  return request({
    url: '/user/'+params+'/remove',
    method: 'get'
  })
}

export function clearUser(params) {
  return request({
    url: '/user/'+params+'/clear',
    method: 'get'
  })
}

export function recoverUser(params) {
  return request({
    url: '/user/'+params+'/recover',
    method: 'get'
  })
}

export function batchRemoveUser(params) {
  return request({
    url: '/user/batchremove',
    method: 'get',
    params: params
  })
}
export function editUser(params) {
  return request({
    url: '/user/update',
    method: 'post',
    data: params
  })
}
export function addUser(params) {
  return request({
    url: '/user/save',
    method: 'post',
    data:params
  })
}
