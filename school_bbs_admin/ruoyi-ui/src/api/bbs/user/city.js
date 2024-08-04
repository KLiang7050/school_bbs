import request from '@/utils/request'

// 查询城市字典表列表
export function listCity(query) {
  return request({
    url: '/app/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市字典表详细
export function getCity(id) {
  return request({
    url: '/app/city/' + id,
    method: 'get'
  })
}

// 新增城市字典表
export function addCity(data) {
  return request({
    url: '/app/city',
    method: 'post',
    data: data
  })
}

// 修改城市字典表
export function updateCity(data) {
  return request({
    url: '/app/city',
    method: 'put',
    data: data
  })
}

// 删除城市字典表
export function delCity(id) {
  return request({
    url: '/app/city/' + id,
    method: 'delete'
  })
}
