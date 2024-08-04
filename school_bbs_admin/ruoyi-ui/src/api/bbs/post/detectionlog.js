import request from '@/utils/request'

// 查询待审核贴子
export function getOutList(data) {
  return request({
    url: '/app/detectionlog/getOutList',
    method: 'get',
    params: data
  })
}

// 通过审核
export function pass(data) {
  return request({
    url: '/app/detectionlog/pass',
    method: 'post',
    params: {
      id:data
    }
  })
}

export function ban(data) {
  return request({
    url: '/app/detectionlog/ban',
    method: 'post',
    params: {
      id:data
    }
  })
}