import {ICommentData} from "@/api/types";
import request from "@/utils/request";

export const defaultCommentData: ICommentData = {
  id: BigInt(0),
  commentBy: BigInt(0),
  userName: '',
  commentContent: '',
  parentId: BigInt(0),
  artId: BigInt(0),
  artName: '',
  commentTime: BigInt(0),
  commentLikeCount: BigInt(0)
}

export const getComments = (data: any) =>
  request({
    url: '/comment/list',
    method: 'GET',
    data
  })

export const getCommentsAll = () =>
  request({
    url: '/comment/all',
    method: 'GET'
  })

export const getCommentDetail = (data: any) =>
  request({
    url: '/comment/detail',
    method: 'GET',
    data
  })

export const createComment = (data: any) =>
  request({
    url: '/comment/add',
    method: 'POST',
    data
  })

export const updateComment = (data: any) =>
  request({
    url: '/comment/update',
    method: 'PATCH',
    data
  })

export const deleteComment = (data: any) =>
  request({
    url: '/comment/delete',
    method: 'DELETE',
    data
  })
