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
  id: BigInt(0),
  adminName: '',
  adminEmail: '',
  adminAvatar: 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg',
  registerTime: '',
  roles: ''
}

export const getAdmins = (data: any) =>
  request({
    url: '/admin/list',
    method: 'GET',
    data
  })

export const getAdminDetail = (data: any) =>
  request({
    url: '/admin/detail',
    method: 'GET',
    data
  })

export const createAdmin = (data: any) =>
  request({
    url: '/admin/add',
    method: 'POST',
    data
  })

export const updateAdmin = (data: any) =>
  request({
    url: '/admin/update',
    method: 'PATCH',
    data
  })

export const deleteAdmin = (data: any) =>
  request({
    url: '/admin/delete',
    method: 'DELETE',
    data
  })
