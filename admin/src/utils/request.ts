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
