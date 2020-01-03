import request from '@/utils/request'

export const getUserInfo = (data: any) =>
  request({
    url: '/admin/token_detail',
    method: 'POST',
    data
  })

export const login = (data: any) =>
  request({
    url: '/admin/login',
    method: 'POST',
    data
  })
