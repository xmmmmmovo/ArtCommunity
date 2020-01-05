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

export interface IArticleData {
  id: bigint
  articleContent: string
  articleFront: string
  articleTitle: string
  createTime: string
  articleAuthor: bigint
  userName: string
}

export interface IArtData {
  id: bigint;
  artName: string;
  createTime: bigint;
  modifiedTime: bigint;
  artAuthor: bigint;
  userName: string;
  artSize: bigint;
  length: string;
  height: string;
  artTag: bigint;
  tagName: string;
  artLikeNum: bigint;
  artCommentNum: bigint;
  artContent: string;
  artPicUrl: string;
}
