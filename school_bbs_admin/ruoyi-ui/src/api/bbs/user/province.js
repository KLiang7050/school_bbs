import request from '@/utils/request'

// 查询省份字典表列表
export function listProvince(query) {
  return request({
    url: '/app/province/list',
    method: 'get',
    params: query
  })
}

// 查询省份字典表详细
export function getProvince(id) {
  return request({
    url: '/app/province/' + id,
    method: 'get'
  })
}

// 新增省份字典表
export function addProvince(data) {
  return request({
    url: '/app/province',
    method: 'post',
    data: data
  })
}

// 修改省份字典表
export function updateProvince(data) {
  return request({
    url: '/app/province',
    method: 'put',
    data: data
  })
}

// 删除省份字典表
export function delProvince(id) {
  return request({
    url: '/app/province/' + id,
    method: 'delete'
  })
}
