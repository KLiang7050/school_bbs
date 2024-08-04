import request from '@/utils/request'

// 查询点赞用户关联列表
export function listLike(query) {
  return request({
    url: '/app/like/list',
    method: 'get',
    params: query
  })
}

// 查询点赞用户关联详细
export function getLike(id) {
  return request({
    url: '/app/like/' + id,
    method: 'get'
  })
}

// 新增点赞用户关联
export function addLike(data) {
  return request({
    url: '/app/like',
    method: 'post',
    data: data
  })
}

// 修改点赞用户关联
export function updateLike(data) {
  return request({
    url: '/app/like',
    method: 'put',
    data: data
  })
}

// 删除点赞用户关联
export function delLike(id) {
  return request({
    url: '/app/like/' + id,
    method: 'delete'
  })
}
