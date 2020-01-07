import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

// 路由
const routes = [
    {
        path: '/',
        name: '首页',
        meta: {
            title: '首页入口'
        },
        component: () => import('@/views/Main.vue')
    },
    {
        path: '/login',
        name: '登录界面',
        meta: {
            title: '登录界面'
        },
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/register',
        name: '注册界面',
        meta: {
            title: '注册界面'
        },
        component: () => import('@/views/Register.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
