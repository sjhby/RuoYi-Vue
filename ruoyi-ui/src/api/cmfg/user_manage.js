import request from '@/utils/request'

// 查询用户管理列表
export function listUser_manage(query) {
  return request({
    url: '/cmfg/user_manage/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getUser_manage(userId) {
  return request({
    url: '/cmfg/user_manage/' + userId,
    method: 'get'
  })
}

// 新增用户管理
export function addUser_manage(data) {
  return request({
    url: '/cmfg/user_manage',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updateUser_manage(data) {
  return request({
    url: '/cmfg/user_manage',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delUser_manage(userId) {
  return request({
    url: '/cmfg/user_manage/' + userId,
    method: 'delete'
  })
}
