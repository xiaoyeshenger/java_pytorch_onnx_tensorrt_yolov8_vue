import request from '@/utils/request'

//**${pojoCnName}**

//添加
export function add(data) {
  return request({
    url: '/${pojoNameLowerCamel}/add${pojoNameUpperCamel}',
    method: 'post',
    data
  })
}

//删除
export function deleteById(id) {
  return request({
    url: '/${pojoNameLowerCamel}/delete${pojoNameUpperCamel}ById/' + id,
    method: 'get'
  })
}

//修改
export function update(data) {
  return request({
    url: '/${pojoNameLowerCamel}/update${pojoNameUpperCamel}',
    method: 'post',
    data
  })
}

//详情
export function getById(id) {
  return request({
    url: '/${pojoNameLowerCamel}/get${pojoNameUpperCamel}ById/' + id,
    method: 'get'
  })
}

//列表
export function listPage(data) {
  return request({
    url: '/${pojoNameLowerCamel}/get${pojoNameUpperCamel}ListPageVo',
    method: 'post',
    data
  })
}

