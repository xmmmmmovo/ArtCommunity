import {IUserData} from "@/api/types";
import request from "@/utils/request";

export const defaultUserData: IUserData = {
  id: BigInt(0),
  userName: '',
  userAvatar: 'https://sqldesign-1258573901.cos.ap-beijing.myqcloud.com/pic_null.jpg',
  userBio: '这个人什么都没写',
  userSex: '男',
  userEmail: '',
  registerTime: ''
}

export const getUsers = (data: any) =>
  request({
    url: '/user/list',
    method: 'GET',
    data
  })

export const getUserDetail = (data: any) =>
  request({
    url: '/user/detail',
    method: 'GET',
    data
  })

export const createUser = (data: any) =>
  request({
    url: '/user/add',
    method: 'POST',
    data
  })

export const updateUser = (data: any) =>
  request({
    url: '/user/update',
    method: 'PATCH',
    data
  })

export const deleteUser = (data: any) =>
  request({
    url: '/user/delete',
    method: 'DELETE',
    data
  })
