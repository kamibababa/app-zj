import request from '@/utils/request'

export function getRedBaseListPage(params) {
  return request({
    url: '/redBase/list/with/search',
    method: 'get',
    params: params
  })
}
export function removeRedBase(params) {
  return request({
    url: '/redBase/'+params+'/remove',
    method: 'get'
  })
}
export function batchRemoveRedBase(params) {
  return request({
    url: '/redBase/batchremove',
    method: 'get',
    params: params
  })
}
export function editRedBase(params) {
  return request({
    url: '/redBase/update',
    method: 'post',
    data: params
  })
}
export function addRedBase(params) {
  return request({
    url: '/redBase/save',
    method: 'post',
    data: params
  })
}
