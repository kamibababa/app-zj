import request from '@/utils/request'

export function getMessageListPage(params) {
  return request({
    url: '/message/list/with/search',
    method: 'get',
    params: params
  })
}

export function getMessageStar(params) {
    return request({
      url: '/message/list/star',
      method: 'get',
      params: params
    })
  }

export function removeMessage(params) {
  return request({
    url: '/message/'+params+'/remove',
    method: 'get'
  })
}

export function batchRemoveMessage(params) {
  return request({
    url: '/message/batchremove',
    method: 'get',
    params: params
  })
}

export function editMessage(params) {
  return request({
    url: '/message/update',
    method: 'post',
    data: params
  })
}

export function addMessage(params) {
  return request({
    url: '/message/save',
    method: 'post',
    data: params
  })
}
