import request from '@/utils/request'

// 查询需求管理列表
export function listRequisition(query) {
  return request({
    url: '/app/requisition/list',
    method: 'get',
    params: query
  })
}

// 查询需求管理详细
export function getRequisition(id) {
  return request({
    url: '/app/requisition/' + id,
    method: 'get'
  })
}

// 新增需求管理
export function addRequisition(data) {
  return request({
    url: '/app/requisition',
    method: 'post',
    data: data
  })
}

// 修改需求管理
export function updateRequisition(data) {
  return request({
    url: '/app/requisition',
    method: 'put',
    data: data
  })
}

// 删除需求管理
export function delRequisition(id) {
  return request({
    url: '/app/requisition/' + id,
    method: 'delete'
  })
}
