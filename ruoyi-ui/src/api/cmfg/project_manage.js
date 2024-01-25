import request from '@/utils/request'

// 查询项目管理列表
export function listProject_manage(query) {
  return request({
    url: '/cmfg/project_manage/list',
    method: 'get',
    params: query
  })
}

// 查询项目管理详细
export function getProject_manage(projId) {
  return request({
    url: '/cmfg/project_manage/' + projId,
    method: 'get'
  })
}

// 新增项目管理
export function addProject_manage(data) {
  return request({
    url: '/cmfg/project_manage',
    method: 'post',
    data: data
  })
}

// 修改项目管理
export function updateProject_manage(data) {
  return request({
    url: '/cmfg/project_manage',
    method: 'put',
    data: data
  })
}

// 删除项目管理
export function delProject_manage(projId) {
  return request({
    url: '/cmfg/project_manage/' + projId,
    method: 'delete'
  })
}
