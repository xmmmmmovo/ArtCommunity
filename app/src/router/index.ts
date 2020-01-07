import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

// 路由
const routes = [
    {
        path: '/',
        name: '首页',
        component: () => import('@/views/Main.vue')
    },
    {
        path: '/login',
        name: '登录界面',
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/register',
        name: '注册界面',
        component: () => import('@/views/Register.vue')
    },
    {
        path: '/about',
        name: '关于',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/About.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
