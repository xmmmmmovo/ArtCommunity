import {ISizeData} from "@/api/types";
import request from "@/utils/request";

export const defaultSizeData: ISizeData = {
  id: BigInt(0),
  length: 0,
  height: 0
}

export const getSizes = (data: any) =>
  request({
    url: '/size/list',
    method: 'GET',
    data
  })

export const getSizesAll = () =>
  request({
    url: '/size/all',
    method: 'GET'
  })

export const getSizeDetail = (data: any) =>
  request({
    url: '/size/detail',
    method: 'GET',
    data
  })

export const createSize = (data: any) =>
  request({
    url: '/size/add',
    method: 'POST',
    data
  })

export const updateSize = (data: any) =>
  request({
    url: '/size/update',
    method: 'PATCH',
    data
  })

export const deleteSize = (data: any) =>
  request({
    url: '/size/delete',
    method: 'DELETE',
    data
  })
