import request from '@/utils/request'

//**计算任务**

//添加
export function add(data) {
  return request({
    url: '/algorithmTask/addAlgorithmTask',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/algorithmTask/deleteAlgorithmTaskById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/algorithmTask/updateAlgorithmTask',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/algorithmTask/getAlgorithmTaskById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/algorithmTask/getAlgorithmTaskListPageVo',
    method: 'post',
    data
  })
}

