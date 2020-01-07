import {VuexModule, Module, Action, Mutation, getModule} from "vuex-module-decorators";
import { getToken, setToken, removeToken } from '@/utils/cookies'
import store from '@/store'
import {getUserDetail, getUserInfo, login} from "@/api/users";

export interface IUserState {
    id: bigint
    token: string
    name: string
    avatar: string
    bio: string
}

@Module({ dynamic: true, store, name: 'user' })
class User extends VuexModule implements IUserState{
    public id = BigInt(0)
    public token = getToken() || ''
    public name = ''
    public avatar = ''
    public bio = ''

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
    private SET_BIO(bio: string) {
        this.bio = bio
    }

    @Action
    public async Login(userInfo: { userEmail: string, userPassword: string }) {
        let { userEmail, userPassword } = userInfo
        userEmail = userEmail.trim()
        let formData = new FormData()
        formData.append("userEmail", userEmail)
        formData.append("userPassword", userPassword)
        const { data } = await login(formData)
        setToken(data.token)
        this.SET_TOKEN(data.token)
        this.SET_NAME(data.userName)
        this.SET_AVATAR(data.userAvatar)
        this.SET_ID(data.id)
        this.SET_BIO(data.userBio)
    }

    @Action
    public ResetToken() {
        removeToken()
        this.SET_TOKEN('')
    }

    @Action
    public async GetUserInfo() {
        if (this.token === '') {
            throw Error('token未验证！')
        }
        let formData = new FormData()
        formData.append("token", this.token)
        const { data } = await getUserInfo(formData)
        if (!data) {
            throw Error('验证失败！请重新登录')
        }
    }

    @Action
    public async LogOut() {
        if (this.token === '') {
            throw Error('LogOut: token is undefined!')
        }
        removeToken()
        this.SET_TOKEN('')
    }

}

export const UserModule = getModule(User)
