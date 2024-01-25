import request from '@/utils/request'

// 查询资源列表
export function listRes_manage(query) {
  return request({
    url: '/cmfg/res_manage/list',
    method: 'get',
    params: query
  })
}

// 查询资源详细
export function getRes_manage(resId) {
  return request({
    url: '/cmfg/res_manage/' + resId,
    method: 'get'
  })
}

// 新增资源
export function addRes_manage(data) {
  return request({
    url: '/cmfg/res_manage',
    method: 'post',
    data: data
  })
}

// 修改资源
export function updateRes_manage(data) {
  return request({
    url: '/cmfg/res_manage',
    method: 'put',
    data: data
  })
}

// 删除资源
export function delRes_manage(resId) {
  return request({
    url: '/cmfg/res_manage/' + resId,
    method: 'delete'
  })
}
