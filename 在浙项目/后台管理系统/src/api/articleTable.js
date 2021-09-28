import request from '@/utils/request'

export function getArticleListPage(params) {
  return request({
    url: '/article/list/with/search',
    method: 'get',
    params: params
  })
}

export function getArticlePageDeleted(params) {
  return request({
    url: '/article/list/with/search/deleted',
    method: 'get',
    params: params
  })
}

export function getArticleAllMsg(params) {
  return request({
    url: '/article/list/msg',
    method: 'get',
    params: params
  })
}

export function getArticleListByCityIdPage(params) {
    return request({
      url: '/article/by/cityId',
      method: 'get',
      params: params
    })
  }


export function removeArticle(params) {
  return request({
    url: '/article/'+params+'/remove',
    method: 'get'
  })
}

export function clearArticle(params) {
  return request({
    url: '/article/'+params+'/clear',
    method: 'get'
  })
}

export function recoverArticle(params) {
  return request({
    url: '/article/'+params+'/recover',
    method: 'get'
  })
}

export function batchRemoveArticle(params) {
  return request({
    url: '/article/batchremove',
    method: 'get',
    params: params
  })
}

export function editArticle(params) {
  return request({
    url: '/article/update',
    method: 'post',
    data: params
  })
}

export function addArticle(params) {
  return request({
    url: '/article/save',
    method: 'post',
    data: params
  })
}
