import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout/index.vue'

Vue.use(Router)

/*
  redirect:                      if set to 'noredirect', no redirect action will be trigger when clicking the breadcrumb
  meta: {
    title: 'title'               the name showed in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon showed in the sidebar
    breadcrumb: false            if false, the item will be hidden in breadcrumb (default is true)
    hidden: true                 if true, this route will not show in the sidebar (default is false)
  }
*/

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
          component: () => import(/* webpackChunkName: "dashboard" */ '@/views/dashboard/index.vue'),
          meta: {
            title: '主界面',
            icon: 'user',
            hidden: true
          }
        },
      ]
    },
    {
      path: '/admin',
      component: Layout,
      redirect: '/editor',
      children: [
        {
          path: 'admin-editor',
          component: () => import(/* webpackChunkName: "admins" */ '@/views/admins/index.vue'),
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
          component: () => import(/* webpackChunkName: "form" */ '@/views/form/index.vue'),
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
      redirect: '/editor/menu1',
      meta: {
        title: '作者',
        icon: 'nested'
      },
      children: [
        {
          path: '作品管理',
          component: () => import(/* webpackChunkName: "menu1" */ '@/views/posts/index.vue'),
          redirect: '/posts/menu1/menu1-1',
          meta: {title: 'Menu1'},
        }
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
      meta: {hidden: true}
    }
  ]
})
