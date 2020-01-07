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
    },
    {
        path: '/my-profile',
        name: '个人界面',
        meta: {
            title: '个人界面'
        },
        component: () => import('@/views/SelfProfile.vue')
    },
    {
        path: '/add-article',
        name: '添加文章',
        meta: {
            title: '添加文章'
        },
        component: () => import('@/views/AddArticle.vue')
    },
    {
        path: '/add-art',
        name: '添加作品',
        meta: {
            title: '添加作品'
        },
        component: () => import('@/views/AddArt.vue')
    },
    {
        path: '/test',
        name: '测试',
        meta: {
            title: '测试'
        },
        component: () => import('@/views/test.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
