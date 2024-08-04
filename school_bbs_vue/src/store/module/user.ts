import { reqLogin, reqUserInfo, logout, update, changePWD, changeTel, updateAvatar } from '@/api/user'
import { getPolicy } from '@/api/thirdparty'
import type { User } from '@/type/userType';
import tokenUtils from '@/utils/tokenUtils';

const state = {
    info: ():User=>{return},
    user: ():User=>{return}
}

// getters
const getters = {
    info: state => state.info
}

// actions
const actions = {
    async Login({ commit }, loginForm) {
        let res = await reqLogin(loginForm);

        if (res.code == 200) {
            commit("SET_USER_INFO", res.data.user)
            tokenUtils.set(res.data.token)
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async GetUserInfo({ commit }) {
        let res = await reqUserInfo()

        if (res.code == 200) {
            commit("SET_USER_INFO", res.data)
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async Logout({ commit }) {
        let res = await logout()

        if (res.code == 200) {
            commit("SET_USER_INFO", {})
            tokenUtils.del()
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async Update({ commit }, data) {
        let res = await update(data)
        if (res.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async ChangePWD({ commit }, data) {
        let res = await changePWD(data)
        if (res.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async ChangeTel({ commit }, data) {
        let res = await changeTel(data)
        if (res.code == 200) {
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },

    async getPolicy({ commit }) {
        let res = await getPolicy()
        if (res.code == 200) {
            return res.data
        } else {
            return Promise.reject(res)
        }
    },

    async UpdateAvatar({ commit }, avatar) {
        let res = await updateAvatar(avatar)
        if (res.code == 200) {
            return res.data
        } else {
            return Promise.reject(res)
        }
    }
}

// mutations
const mutations = {
    SET_USER_INFO(state, info) {
        state.info = info;
    },
}

export default {
    state,
    getters,
    actions,
    mutations,
}