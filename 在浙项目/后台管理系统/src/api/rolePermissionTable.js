import request from '@/utils/request'

export function getRoleToPermission() {
    return request({
      url: '/role-to-permission/list/tree',
      method: 'get'
    })
  }

  export function getRoleToPermissionById(params) {
    return request({
      url: '/role-to-permission/'+params+'/get/tree',
      method: 'get'
    })
  }  

  export function getRoleToPermissionByPid(params) {
    return request({
      url: '/role-to-permission/'+params+'/get/permission/tree',
      method: 'get'
    })
  }  