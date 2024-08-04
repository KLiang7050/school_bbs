import { favoriteList, list } from '@/api/like'

const state = {
    likeList: [],
    favorites: [],
}
const getters = {
    likeList: state => state.likeList ? state.likeList:[],
    favorites: state => state.favorites ? state.favorites:[]
}
const actions = {
    async GetLikeList({ commit, state }, mainId) {
        const res = await list(mainId)
        if (res.code == 200) {
            commit("SET_LIKELIST", res.data)
        } else {
            return Promise.reject(res)
        }
    },
    async GetFavoritesList({ commit, state }) {
        const res = await favoriteList()
        if (res.code == 200) {
            commit("SET_FAVORITESLIST", res.data)
        } else {
            return Promise.reject(res)
        }
    }
}
const mutations = {
    SET_LIKELIST(state, data) {
        state.likeList = data == null ? [] : data
    },
    SET_FAVORITESLIST(state, data) {
        state.favorites = data == null ? [] : data
    }
}
export default {
    state,
    getters,
    actions,
    mutations,
}