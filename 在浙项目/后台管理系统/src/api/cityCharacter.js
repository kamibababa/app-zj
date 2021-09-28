import request from '@/utils/request'

export function getCharacterListPage(params) {
  return request({
    url: '/character/list/with/search',
    method: 'get',
    params: params
  })
}

export function getCharacterListByCityIdPage(params) {
    return request({
      url: '/character/by/cityId',
      method: 'get',
      params: params
    })
  }

export function removeCharacter(params) {
  return request({
    url: '/character/'+params+'/remove',
    method: 'get'
  })
}

export function batchRemoveCharacter(params) {
  return request({
    url: '/character/batchremove',
    method: 'get',
    params: params
  })
}

export function editCharacter(params) {
  return request({
    url: '/character/update',
    method: 'post',
    data: params
  })
}

export function addCharacter(params) {
  return request({
    url: '/character/save',
    method: 'post',
    data: params
  })
}
