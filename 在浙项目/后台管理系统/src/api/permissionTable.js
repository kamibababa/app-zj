import request from '@/utils/request'

export function getPermissionListPage(params) {
  return request({
    url: '/permission/list/with/search',
    method: 'get',
    params: params
  })
}
export function removePermission(params) {
  return request({
    url: '/permission/'+params+'/remove',
    method: 'get'
  })
}
export function batchRemovePermission(params) {
  return request({
    url: '/permission/batchremove',
    method: 'get',
    params: params
  })
}
export function editPermission(params) {
  return request({
    url: '/permission/update',
    method: 'post',
    data: params
  })
}
export function addPermission(params) {
  return request({
    url: '/permission/save',
    method: 'post',
    data: params
  })
}
