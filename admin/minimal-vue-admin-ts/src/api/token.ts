import request from '@/utils/request'

export const getToken = () =>
  request({
    url: '/token/token',
    method: 'GET'
  })
