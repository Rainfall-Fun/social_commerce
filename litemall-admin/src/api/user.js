import request from '@/utils/request'
export function fetchList(query) {
  return request({
    url: '/user/SearchUserInfo',
    method: 'get',
    params: query
  })
}
export function searchAll(query) {
  return request({
    url: '/user/SearchAllUserInfo',
    method: 'get',
    params: query
  })
}
export function update(one) {
  return request({
    url: '/user/UpdateUserInfo',
    method: 'get',
    params: one
  })
}

export function deleteuserinfo(one) {
  return request({
    url: '/user/DeleteUserInfo',
    method: 'get',
    params: one
  })
}

export function searchuseradress(query) {
  return request({
    url: '/user/SearchUserAdressById',
    method: 'get',
    params: query
  })
}
export function searchAlluseradress(query) {
  return request({
    url: '/user/SearchAllUserAdress',
    method: 'get',
    params: query
  })
}

export function searchusercollect(query) {
  return request({
    url: '/user/SearchUserCollect',
    method: 'get',
    params: query
  })
}

export function searchAllusercollect(query) {
  return request({
    url: '/user/SearchAllUserCollect',
    method: 'get',
    params: query
  })
}
export function SearchSuggest(query) {
  return request({
    url: '/user/SearchSuggest',
    method: 'get',
    params: query
  })
}
export function SearchAllSuggest(query) {
  return request({
    url: '/user/SearchAllSuggest',
    method: 'get',
    params: query
  })
}
export function listFootprint(query) {
  return request({
    url: '/footprint/list',
    method: 'get',
    params: query
  })
}

export function searchHistoryBYid(query) {
  return request({
    url: '/user/searchHistory',
    method: 'get',
    params: query
  })
}

export function searchAllUserHistory(query) {
  return request({
    url: '/user/searchAllUserHistory',
    method: 'get',
    params: query
  })
}
