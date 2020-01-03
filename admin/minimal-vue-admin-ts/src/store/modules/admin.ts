import { VuexModule, Module, Action, Mutation, getModule } from 'vuex-module-decorators'
import { login, logout, getUserInfo } from '@/api/admins'
import { getToken, setToken, removeToken } from '@/utils/cookies'
import store from '@/store'

// admin接口
export interface IAdminState {
  id: bigint
  token: string
  name: string
  avatar: string
  roles: string[]
}

@Module({ dynamic: true, store, name: 'user' })
class Admin extends VuexModule implements IAdminState {
  public id = BigInt(0)
  public token = getToken() || ''
  public name = ''
  public avatar = ''
  public roles: string[] = []

  @Mutation
  private SET_ID(id: bigint) {
    this.id = id
  }

  @Mutation
  private SET_TOKEN(token: string) {
    this.token = token
  }

  @Mutation
  private SET_NAME(name: string) {
    this.name = name
  }

  @Mutation
  private SET_AVATAR(avatar: string) {
    this.avatar = avatar
  }

  @Mutation
  private SET_ROLES(roles: string[]) {
    this.roles = roles
  }

  @Action
  public async Login(userInfo: { adminName: string, adminPassword: string }) {
    let { adminName, adminPassword } = userInfo
    adminName = adminName.trim()
    let formData = new FormData()
    formData.append("adminName", adminName)
    formData.append("adminPassword", adminPassword)
    const { data } = await login(formData)
    setToken(data.token)
    this.SET_TOKEN(data.token)
    this.SET_NAME(data.adminName)
    this.SET_AVATAR(data.avatar)
    this.SET_ID(data.id)
  }

  @Action
  public ResetToken() {
    removeToken()
    this.SET_TOKEN('')
    this.SET_ROLES([])
  }

  @Action
  public async GetUserInfo() {
    if (this.token === '') {
      throw Error('GetUserInfo: token is undefined!')
    }
    const { data } = await getUserInfo({ /* Your params here */ })
    if (!data) {
      throw Error('Verification failed, please Login again.')
    }
    const { roles, name, avatar, introduction } = data.user
    // roles must be a non-empty array
    if (!roles || roles.length <= 0) {
      throw Error('GetUserInfo: roles must be a non-null array!')
    }
    this.SET_ROLES(roles)
    this.SET_NAME(name)
    this.SET_AVATAR(avatar)
  }

  @Action
  public async LogOut() {
    if (this.token === '') {
      throw Error('LogOut: token is undefined!')
    }
    await logout()
    removeToken()
    this.SET_TOKEN('')
    this.SET_ROLES([])
  }
}

export const AdminModule = getModule(Admin)
