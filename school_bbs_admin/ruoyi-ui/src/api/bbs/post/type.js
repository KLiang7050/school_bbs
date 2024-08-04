import request from '@/utils/request'

// 查询贴子类型字典列表
export function listType(query) {
  return request({
    url: '/app/type/list',
    method: 'get',
    params: query
  })
}

// 查询贴子类型字典详细
export function getType(id) {
  return request({
    url: '/app/type/' + id,
    method: 'get'
  })
}

// 新增贴子类型字典
export function addType(data) {
  return request({
    url: '/app/type',
    method: 'post',
    data: data
  })
}

// 修改贴子类型字典
export function updateType(data) {
  return request({
    url: '/app/type',
    method: 'put',
    data: data
  })
}

// 删除贴子类型字典
export function delType(id) {
  return request({
    url: '/app/type/' + id,
    method: 'delete'
  })
}
