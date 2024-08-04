import request from '@/utils/request'

// 查询收藏用户关联列表
export function listFavorites(query) {
  return request({
    url: '/app/favorites/list',
    method: 'get',
    params: query
  })
}

// 查询收藏用户关联详细
export function getFavorites(id) {
  return request({
    url: '/app/favorites/' + id,
    method: 'get'
  })
}

// 新增收藏用户关联
export function addFavorites(data) {
  return request({
    url: '/app/favorites',
    method: 'post',
    data: data
  })
}

// 修改收藏用户关联
export function updateFavorites(data) {
  return request({
    url: '/app/favorites',
    method: 'put',
    data: data
  })
}

// 删除收藏用户关联
export function delFavorites(id) {
  return request({
    url: '/app/favorites/' + id,
    method: 'delete'
  })
}
