import request from '@/utils/request'
import { IAdminData } from './types'

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

export const defaultAdminData: IAdminData = {
  id: 0,
  adminName: '',
  adminEmail: '',
  adminAvatar: '',
  registerTime: '',
  roles: ''
}

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

export const updateAdmin = (params: any) =>
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
