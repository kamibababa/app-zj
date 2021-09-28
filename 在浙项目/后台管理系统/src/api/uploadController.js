import request from '@/utils/request'

export function uploadImg(params) {
  return request({
    url: '/oss/upload',
    method: 'post',
    data: params
  })
}
export function deleteImg(params) {
  return request({
    url: '/oss/delete',
    method: 'post',
    data: params
  })
}
