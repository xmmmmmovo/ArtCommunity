export interface IArticleData {
  id: number
  status: string
  title: string
  abstractContent: string
  fullContent: string
  sourceURL: string
  imageURL: string
  timestamp: string | number
  platforms: string[]
  disableComment: boolean
  importance: number
  author: string
  reviewer: string
  type: string
  pageviews: number
}

export interface IAdminData {
  id: bigint
  adminName: string
  adminEmail: string
  adminAvatar: string
  registerTime: string | number
  roles: string
}

export interface ISizeData {
  id: bigint,
  length: number,
  height: number
}

export interface ITagData {
  id: bigint,
  tagName: string,
  usageCount: number,
  tagDescribe: string
}

export interface IUserData {
  id: bigint
  userName: string
  userEmail: string
  userAvatar: string
  registerTime: string | number
  userBio: string
  userSex: string
}
