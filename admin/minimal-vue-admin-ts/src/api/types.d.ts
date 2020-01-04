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
