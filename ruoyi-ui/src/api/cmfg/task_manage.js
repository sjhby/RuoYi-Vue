import request from '@/utils/request'

// 查询任务管理列表
export function listTask_manage(query) {
  return request({
    url: '/cmfg/task_manage/list',
    method: 'get',
    params: query
  })
}

// 查询任务管理详细
export function getTask_manage(taskId) {
  return request({
    url: '/cmfg/task_manage/' + taskId,
    method: 'get'
  })
}

// 新增任务管理
export function addTask_manage(data) {
  return request({
    url: '/cmfg/task_manage',
    method: 'post',
    data: data
  })
}

// 修改任务管理
export function updateTask_manage(data) {
  return request({
    url: '/cmfg/task_manage',
    method: 'put',
    data: data
  })
}

// 删除任务管理
export function delTask_manage(taskId) {
  return request({
    url: '/cmfg/task_manage/' + taskId,
    method: 'delete'
  })
}
