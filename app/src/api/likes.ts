import {ILikeData} from "@/api/types";
import request from "@/utils/request";

export const defaultLikeData: ILikeData = {
    id: BigInt(0),
    userId: BigInt(0),
    artPicUrl: '',
    tagName: '',
    artId: BigInt(0),
    likeTime: BigInt(0),
    artName: ''
}

export const getLikes = (data: any) =>
    request({
        url: '/like/list/list',
        method: 'POST',
        data
    })

export const getIsLikes = (data: any) =>
    request({
        url: '/like/list/find_is_like',
        method: 'POST',
        data
    })

export const getUserLikes = (data: any) =>
    request({
        url: '/like/list/find_user_art_list',
        method: 'POST',
        data
    })

export const getLikesAll = () =>
    request({
        url: '/like/list/all',
        method: 'GET'
    })

export const getLikeDetail = (data: any) =>
    request({
        url: '/like/list/detail',
        method: 'GET',
        data
    })

export const createLike = (data: any) =>
    request({
        url: '/like/list/add',
        method: 'POST',
        data
    })

export const updateLike = (data: any) =>
    request({
        url: '/like/list/update',
        method: 'PATCH',
        data
    })

export const deleteLike = (data: any) =>
    request({
        url: '/like/list/delete',
        method: 'DELETE',
        data
    })
