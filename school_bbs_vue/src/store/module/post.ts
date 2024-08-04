import { getPostList, getPostInfo, getReplyList } from '@/api/post'
import type { PostInfo } from '@/type/postInfoType'
import type { PageR } from '@/type/pageR'
import { favoriteInfoList } from '@/api/like'

const state = {
    postItemList: [],
    postMainInfo: (): PostInfo => { return },
    replyList: [],
    total: 0
}
const getters = {
    postItemList: state => state.postItemList,
    postMainInfo: state => state.postMainInfo,
    replyList: state => state.replyList
}
const actions = {
    async GetPostList({ commit,state }, datas: any) {
        const res: PageR = await getPostList({
            pageNum: datas.pageNum,
            pageSize: datas.pageSize,
            orderByColumn: datas.orderByColumn,
            typeId: datas.typeId,
            title: datas.title
        });
        if (res.code !== 200) {
            return Promise.reject(res.msg);
        }
        if (datas.isPush) {
            commit('PUSHPOSTITEMLIST', res);
        } else {
            commit('SETPOSTITEMLIST', res)
        }
        return res.rows.length;
    },

    async GetPostListByFavorite({ commit,state },datas) {
        state.postItemList = [];
        const res = await favoriteInfoList(datas);
        if (res.code !== 200) {
            return Promise.reject(res.msg);
        }
        commit('SETPOSTITEMLIST', res)
        return res.rows.length;
    },

    async GetPostInfo({ commit, state }, id) {
        state.postMainInfo = {} //初始化数据
        let res = await getPostInfo(id)
        if (res.code == 200) {
            commit("SET_POST_INFO", res.data)
            return 'ok'
        } else {
            return Promise.reject(res)
        }
    },
    async GetReplyList({ commit, state }, data) {
        state.replyList = [] //初始化数据
        const res: PageR = await getReplyList(data)

        if (res.code == 200) {
            commit("SET_REPLY", res.rows)
            return res.total;
        } else {
            return Promise.reject(res)
        }
    }
}
const mutations = {
    PUSHPOSTITEMLIST(state, { rows, total }) {
        if (rows != null || rows.length > 0) {
            state.postItemList.push(...rows)
            state.total = total
        }
    },
    SETPOSTITEMLIST(state, { rows, total }) {
        state.postItemList = []
        if (rows != null || rows.length > 0) {
            state.postItemList = rows
            state.total = total
        }
    },
    SET_POST_INFO(state, info) {
        if (info != null) {
            state.postMainInfo = info;
        }
    },
    SET_REPLY(state, info) {
        if (info != null) {
            state.replyList = info;
        }
    },
}
export default {
    state,
    getters,
    actions,
    mutations,
}