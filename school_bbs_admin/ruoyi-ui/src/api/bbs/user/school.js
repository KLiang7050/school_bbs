import request from '@/utils/request'

// 查询学校字典表列表
export function listSchool(query) {
  return request({
    url: '/app/school/list',
    method: 'get',
    params: query
  })
}

// 查询学校字典表详细
export function getSchool(id) {
  return request({
    url: '/app/school/' + id,
    method: 'get'
  })
}

// 新增学校字典表
export function addSchool(data) {
  return request({
    url: '/app/school',
    method: 'post',
    data: data
  })
}

// 修改学校字典表
export function updateSchool(data) {
  return request({
    url: '/app/school',
    method: 'put',
    data: data
  })
}

// 删除学校字典表
export function delSchool(id) {
  return request({
    url: '/app/school/' + id,
    method: 'delete'
  })
}
