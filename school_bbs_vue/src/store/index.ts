import { createStore } from 'vuex'
import user from './module/user'
import basic from './module/basic'
import post from './module/post'
import like from './module/like'

export default createStore({
  modules: {
    user,
    basic,
    post,
    like
  },
})