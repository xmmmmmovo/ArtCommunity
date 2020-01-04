import axios from 'axios'
import Qs from 'qs'
import { Message, MessageBox } from 'element-ui'
import { AdminModule } from '@/store/modules/admin'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  timeout: 5000
})

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

// Response interceptors
service.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code !== 200) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      if (res.code === 500 || res.code === 400) {
        MessageBox.confirm(
          '输入信息出错',
          '错误',
          {
            confirmButtonText: '重试',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          location.reload() // To prevent bugs from vue-router
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return response.data
    }
  },
  (error) => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
