import request from '@/utils/request'

// 查询入驻申请列表
export function listJoinus(query) {
  return request({
    url: '/app/joinus/list',
    method: 'get',
    params: query
  })
}

// 查询入驻申请详细
export function getJoinus(id) {
  return request({
    url: '/app/joinus/' + id,
    method: 'get'
  })
}

// 新增入驻申请
export function addJoinus(data) {
  return request({
    url: '/app/joinus',
    method: 'post',
    data: data
  })
}

// 修改入驻申请
export function updateJoinus(data) {
  return request({
    url: '/app/joinus',
    method: 'put',
    data: data
  })
}

// 删除入驻申请
export function delJoinus(id) {
  return request({
    url: '/app/joinus/' + id,
    method: 'delete'
  })
}
