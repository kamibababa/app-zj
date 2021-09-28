import request from '@/utils/request'

export function getCityListPage(params) {
  return request({
    url: '/city/list/with/search',
    method: 'get',
    params: params
  })
}

export function removeCity(params) {
  return request({
    url: '/city/'+params+'/remove',
    method: 'get'
  })
}
export function batchRemoveCity(params) {
  return request({
    url: '/city/batchremove',
    method: 'get',
    params: params
  })
}
export function editCity(params) {
  return request({
    url: '/city/update',
    method: 'post',
    data: params
  })
}
export function addCity(params) {
  return request({
    url: '/city/save',
    method: 'post',
    data: params
  })
}
export function listCityFoot(){
  return request({
    url: '/city/look/all',
    method: 'get'
  })
}
export function removeCityFoot(){
  return request({
    url: '/city/look/clear',
    method: 'get'
  })
}

export function getAllCity(){
  return request({
    url: '/city/list',
    method: 'get'
  })
}