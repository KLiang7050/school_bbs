import request from '@/utils/request'

// 查询贴子内容列表
export function listContent(query) {
  return request({
    url: '/app/content/list',
    method: 'get',
    params: query
  })
}

// 查询贴子内容详细
export function getContent(id) {
  return request({
    url: '/app/content/' + id,
    method: 'get'
  })
}

// 新增贴子内容
export function addContent(data) {
  return request({
    url: '/app/content',
    method: 'post',
    data: data
  })
}

// 修改贴子内容
export function updateContent(data) {
  return request({
    url: '/app/content',
    method: 'put',
    data: data
  })
}

// 删除贴子内容
export function delContent(id) {
  return request({
    url: '/app/content/' + id,
    method: 'delete'
  })
}
