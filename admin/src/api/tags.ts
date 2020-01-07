import {ITagData} from "@/api/types";
import request from "@/utils/request";

export const defaultTagData: ITagData = {
  id: BigInt(0),
  tagName: '',
  tagDescribe: '',
  usageCount: 0
}

export const getTags = (data: any) =>
  request({
    url: '/tag/list',
    method: 'POST',
    data
  })

export const getTagsAll = () =>
  request({
    url: '/tag/all',
    method: 'GET'
  })

export const getTagDetail = (data: any) =>
  request({
    url: '/tag/detail',
    method: 'GET',
    data
  })

export const createTag = (data: any) =>
  request({
    url: '/tag/add',
    method: 'POST',
    data
  })

export const updateTag = (data: any) =>
  request({
    url: '/tag/update',
    method: 'PATCH',
    data
  })

export const deleteTag = (data: any) =>
  request({
    url: '/tag/delete',
    method: 'DELETE',
    data
  })
