import request from '@/utils/request'

//**客户**

//添加
export function add(data) {
  return request({
    url: '/customer/addCustomer',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/customer/deleteCustomerById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/customer/updateCustomer',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/customer/getCustomerById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/customer/getCustomerListPageVo',
    method: 'post',
    data
  })
}

