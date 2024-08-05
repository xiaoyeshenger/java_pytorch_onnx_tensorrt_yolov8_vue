import request from '@/utils/request'

//**算法模型**

//添加
export function add(data) {
  return request({
    url: '/algorithmModel/addAlgorithmModel',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/algorithmModel/deleteAlgorithmModelById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/algorithmModel/updateAlgorithmModel',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/algorithmModel/getAlgorithmModelById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/algorithmModel/getAlgorithmModelListPageVo',
    method: 'post',
    data
  })
}

