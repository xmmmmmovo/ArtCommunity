import request from '@/utils/request'

export const getAdmins = (params: any) =>
  request({
    url: '/articles',
    method: 'get',
    params
  })

export const getAdminPageviews = (params: any) =>
  request({
    url: '/articles',
    method: 'get',
    params
  })

export const createAdmin = (params: any) =>
  request({
    url: '/articles',
    method: 'get',
    params
  })


export const defaultAdminData = (params: any) =>
  request({
    url: '/articles',
    method: 'get',
    params
  })

export const deleteAdmin = (params: any) =>
  request({
    url: '/articles',
    method: 'get',
    params
  })
