import request from '@/utils/request'

// 查询贴子主贴列表
export function listMain(query) {
  return request({
    url: '/app/main/list',
    method: 'get',
    params: query
  })
}

// 查询贴子主贴详细
export function getMain(id) {
  return request({
    url: '/app/main/' + id,
    method: 'get'
  })
}

// 新增贴子主贴
export function addMain(data) {
  return request({
    url: '/app/main',
    method: 'post',
    data: data
  })
}

// 修改贴子主贴
export function updateMain(data) {
  return request({
    url: '/app/main',
    method: 'put',
    data: data
  })
}

// 删除贴子主贴
export function delMain(id) {
  return request({
    url: '/app/main/' + id,
    method: 'delete'
  })
}




// 查询待审核贴子
export function unauditedList(data) {
  return request({
    url: '/app/main/unaudited',
    method: 'get',
    params: data
  })
}