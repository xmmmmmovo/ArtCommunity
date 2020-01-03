import Vue from 'vue'
import Router, {RouteConfig} from 'vue-router'
import Layout from '@/layout/index.vue'

Vue.use(Router)

export default new Router({
  // mode: 'history',  // Enable this if you need.
  scrollBehavior: (to, from, savedPosition) => {
    if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  },
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/login',
      component: () => import(/* webpackChunkName: "login" */ '@/views/login/index.vue'),
      meta: {hidden: true}
    },
    {
      path: '/404',
      component: () => import(/* webpackChunkName: "404" */ '@/views/404.vue'),
      meta: {hidden: true}
    },
    {
      path: '/',
      component: Layout,
      redirect: '/dashboard',
      meta: {
        hidden: true
      },
      children: [
        {
          path: 'dashboard',
          component: () => import('@/views/dashboard/index.vue'),
          meta: {
            title: '主界面',
            hidden: true
          }
        },
      ]
    },
    {
      path: '/admin',
      component: Layout,
      children: [
        {
          path: 'admin-editor',
          component: () => import('@/views/admins/index.vue'),
          meta: {
            title: '管理员管理',
            icon: 'admin'
          }
        }
      ]
    },
    {
      path: '/form',
      component: Layout,
      children: [
        {
          path: 'index',
          component: () => import('@/views/table/form/index.vue'),
          meta: {
            title: 'Form',
            icon: 'form'
          }
        }
      ]
    },
    {
      path: '/editor',
      component: Layout,
      redirect: '/editor/posts',
      meta: {
        title: '作者',
        icon: 'nested'
      },
      children: [
        {
          path: 'posts',
          component: () => import('@/views/table/posts/index.vue'),
          meta: {title: '作品管理'}
        },
        {
          path: 'comments',
          component: () => import('@/views/table/comments/index.vue'),
          meta: {
            title: '评论管理'
          }
        },
        {
          path: 'users',
          component: () => import('@/views/table/users/index.vue'),
          meta: {
            title: '用户管理'
          }
        },
        {
          path: 'tags',
          component: () => import('@/views/table/tags/index.vue'),
          meta: {
            title: '标签管理'
          }
        },
        {
          path: 'sizes',
          component: () => import('@/views/table/sizes/index.vue'),
          meta: {
            title: '尺寸管理'
          }
        },
        {
          path: 'logs',
          component: () => import('@/views/table/logs/index.vue'),
          meta: {
            title: '日志管理'
          }
        },
      ]
    },
    {
      path: '作者链接',
      component: Layout,
      children: [
        {
          path: 'https://github.com/xmmmmmovo',
          meta: {
            title: '作者链接',
            icon: 'link'
          }
        }
      ]
    },
    {
      path: '*',
      redirect: '/404',
      meta: { hidden: true }
    }
  ]
})
