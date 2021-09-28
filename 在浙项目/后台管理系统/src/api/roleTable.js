import request from '@/utils/request'

export function getRoleListPage(params) {
  return request({
    url: '/role/list/with/search',
    method: 'get',
    params: params
  })
}
export function removeRole(params) {
  return request({
    url: '/role/'+params+'/remove',
    method: 'get'
  })
}
export function batchRemoveRole(params) {
  return request({
    url: '/role/batchremove',
    method: 'get',
    params: params
  })
}
export function editRole(params) {
  return request({
    url: '/role/update',
    method: 'post',
    data: params
  })
}
export function addRole(params) {
  return request({
    url: '/role/save',
    method: 'post',
    data: params
  })
}
