import request from '@/utils/request'

export function getComparisonMerchant(query) {
  return request({
    // herders: { 'Content-Type': 'application/json;charset=UTF-8' },
    url: '/priceComparison/comparison/merchant',
    method: 'POST',
    data: query
  })
}

export function getComparisonHistoryData(query) {
  return request({
    url: '/priceComparison/comparison/history_price/' + query,
    method: 'GET'
  })
}
