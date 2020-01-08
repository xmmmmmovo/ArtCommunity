import {IArtData} from "@/api/types";
import request from "@/utils/request";

export const defaultArtData: IArtData = {
  id: BigInt(0),
  artName: '',
  createTime: BigInt(0),
  modifiedTime: BigInt(0),
  artAuthor: BigInt(0),
  userName: '',
  artSize: BigInt(0),
  length: '',
  height: '',
  artTag: BigInt(0),
  tagName: '',
  artLikeNum: BigInt(0),
  artCommentNum: BigInt(0),
  artContent: '',
  artPicUrl: ''
}

export const getArts = (data: any) =>
    request({
        url: '/art/list',
        method: 'POST',
        data
    })

export const getUserArts = (data: any) =>
    request({
        url: '/art/find_user_art_list',
        method: 'POST',
        data
    })

export const getArtsAll = () =>
  request({
    url: '/art/all',
    method: 'GET'
  })

export const getArtDetail = (data: any) =>
  request({
    url: '/art/detail',
    method: 'POST',
    data
  })

export const createArt = (data: any) =>
  request({
    url: '/art/add',
    method: 'POST',
    data
  })

export const updateArt = (data: any) =>
  request({
    url: '/art/update',
    method: 'PATCH',
    data
  })

export const deleteArt = (data: any) =>
  request({
    url: '/art/delete',
    method: 'DELETE',
    data
  })
