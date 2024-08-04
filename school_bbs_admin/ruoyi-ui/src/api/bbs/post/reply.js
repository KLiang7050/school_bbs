import request from '@/utils/request'

// 查询贴子回复列表
export function listReply(query) {
  return request({
    url: '/app/reply/list',
    method: 'get',
    params: query
  })
}

// 查询贴子回复详细
export function getReply(id) {
  return request({
    url: '/app/reply/' + id,
    method: 'get'
  })
}

// 新增贴子回复
export function addReply(data) {
  return request({
    url: '/app/reply',
    method: 'post',
    data: data
  })
}

// 修改贴子回复
export function updateReply(data) {
  return request({
    url: '/app/reply',
    method: 'put',
    data: data
  })
}

// 删除贴子回复
export function delReply(id) {
  return request({
    url: '/app/reply/' + id,
    method: 'delete'
  })
}
