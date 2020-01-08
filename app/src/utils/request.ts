import axios from 'axios'
import Qs from 'qs'
import {Message} from "element-ui";

// 初始化axios并将其注册为service
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    timeout: 5000
})

// 跨域请求使用的请求拦截器 但是我在后端做了 就不用前端了
// Request interceptors
// service.interceptors.request.use(
//   (config) => {
//     config.headers['Content-Type'] = 'multipart/form-data'
//     return config
//   },
//   (error) => {
//     Promise.reject(error)
//   }
// )

// 返回的拦截器 用于拦截返回码之类的 将纯数据返回
service.interceptors.response.use(
    (response) => {
        const res = response.data
        if (res.code !== 200) {
            // 如果状态码不是200 则说明有错误
            if (res.code === 500 || res.code === 400) {
                Message({
                    message: res.message || 'Error',
                    type: 'error',
                    duration: 5 * 1000,
                    offset: 100
                })
                location.reload() // To prevent bugs from vue-router
            }
            // 拒绝本次请求
            return Promise.reject(new Error(res.message || 'Error'))
        } else {
            // 如果没有拒绝(请求码是200
            // 则将有用的data数据返回
            return response.data
        }
    },
    // 同时 我们也需要判断是否超时 是否拒绝访问了
    (error) => {
        Message({
            message: error.message,
            type: 'error',
            duration: 5 * 1000,
            offset: 100
        })
        return Promise.reject(error)
    }
)

export default service
