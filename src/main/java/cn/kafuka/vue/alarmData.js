import request from '@/utils/request'

//**告警数据**

//添加
export function add(data) {
  return request({
    url: '/alarmData/addAlarmData',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/alarmData/deleteAlarmDataById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/alarmData/updateAlarmData',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/alarmData/getAlarmDataById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/alarmData/getAlarmDataListPageVo',
    method: 'post',
    data
  })
}

