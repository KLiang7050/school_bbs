import tokenUtils from '@/utils/tokenUtils'
import $store from '@/store'; // 导入 store 实例
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { inject } from "vue";


const reload = inject("reload")



const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: () => import('@/views/Main/index.vue'),
    redirect: '/homepage',
    children: [
      {
        path: '/homepage',
        name: 'homepage',
        component: () => import('@/views/HomePage/index.vue'),
      },
      {
        name: "postinfo",
        path: "/postinfo/:id",
        component: () => import('@/components/PostInfo/index.vue'),
      },
      {
        name: "userinfo",
        path: "/userinfo",
        component: () => import('@/views/UserInfo/index.vue'),
        children: [
          {
            name: "myinfo",
            path: "/userinfo/info/:id?",
            component: () => import('@/views/UserInfo/Info/index.vue')
          },
          {
            name: "myfavorite",
            path: "/userinfo/myfavorite",
            component: () => import('@/views/UserInfo/MyFavorite/index.vue')
          },
          {
            name: "replyme",
            path: "/userinfo/replyme",
            component: () => import('@/views/UserInfo/ReplyMe/index.vue')
          },
          {
            name: "mypost",
            path: "/userinfo/mypost",
            component: () => import('@/views/UserInfo/MyPost/index.vue')
          },
        ]
      },
      {
        name: "search",
        path: "/search",
        component: () => import('@/views/SearchPage/index.vue')
      },
      {
        name: "edit",
        path: "/edit/:id?",
        component: () => import('@/views/Edit/index.vue')
      },
      {
        name: "joinus",
        path: "/joinus",
        component: () => import('@/views/JoinUs/index.vue')
      },
      {
        name: "detail",
        path: "/joinus/detail/:id?",
        component: () => import('@/views/JoinUs/JoinUsDetail/index.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login/index.vue'),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/Register/index.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

const blackList = ['/edit', '/joinus', '/joinusdetail', '/userinfo']
router.beforeEach(async (to, from, next) => {
  window.document.title = '校园论坛'
  let token: string = tokenUtils.get()
  // 是否有token
  if (token !== null && token !== undefined && token !== '' && token.length !== 0) {
    // 有token是否是登录
    if (to.path == '/login') {
      next("/")
    } else {
      // 是登录是否有用户信息
      if ($store.getters.info && Object.keys($store.getters.info).length != 0) {
        next()
      } else {
        // 没有用户信息去请求
        try {
          await $store.dispatch("GetUserInfo")
          next()
        } catch (err) {
          // 如果没获取到用户信息就手动退出
          tokenUtils.del()
          next("/login")
        }
      }
    }
  } else {
    let flag = false;
    for (let item of blackList) {
      if (to.path.indexOf(item) === 0) {
        flag = true;
      }
    }

    let urlToken = to.query.token
    if (urlToken != null && urlToken != undefined && urlToken != '') {
      tokenUtils.set(urlToken+"")
      try {
        await $store.dispatch("GetUserInfo")
        next()
      } catch (err) {
        // 如果没获取到用户信息就手动退出
        tokenUtils.del()
        next("/login")
      }
    }

    if (flag) {
      // await $store.dispatch("ChangeShowFlag", true)
      // next(false)
      next({ path: '/login', query: { redirect: to.path } })
    } else {
      next()
    }
  }
})

//全局后置守卫：初始化时执行、每次路由切换后执行
router.afterEach((to, from) => {

})

export default router
