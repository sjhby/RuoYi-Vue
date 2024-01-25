import request from '@/utils/request'

// 查询服务列表
export function listSer_manage(query) {
  return request({
    url: '/cmfg/ser_manage/list',
    method: 'get',
    params: query
  })
}

// 查询服务详细
export function getSer_manage(serId) {
  return request({
    url: '/cmfg/ser_manage/' + serId,
    method: 'get'
  })
}

// 新增服务
export function addSer_manage(data) {
  return request({
    url: '/cmfg/ser_manage',
    method: 'post',
    data: data
  })
}

// 修改服务
export function updateSer_manage(data) {
  return request({
    url: '/cmfg/ser_manage',
    method: 'put',
    data: data
  })
}

// 删除服务
export function delSer_manage(serId) {
  return request({
    url: '/cmfg/ser_manage/' + serId,
    method: 'delete'
  })
}
