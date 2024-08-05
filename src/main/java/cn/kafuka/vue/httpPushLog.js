import request from '@/utils/request'

//**HTTP推送日志**

//添加
export function add(data) {
  return request({
    url: '/httpPushLog/addHttpPushLog',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/httpPushLog/deleteHttpPushLogById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/httpPushLog/updateHttpPushLog',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/httpPushLog/getHttpPushLogById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/httpPushLog/getHttpPushLogListPageVo',
    method: 'post',
    data
  })
}

