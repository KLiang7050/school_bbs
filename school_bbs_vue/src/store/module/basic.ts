const state = {
    postType: [],
    // isShowLogin: false,
}
const getters = {
    //读取性别名称
    GetPostType: (state) => (code) => {
        if (code != null || code != undefined) {
            let arr = state.postType.filter(item => item.code == parseInt(code));
            return arr[0].value;
        } else {
            return '-';
        }
    },
    
}
const actions = {
    // ChangeShowFlag: ({ commit }, flag) => {
    //     commit('CHANGESHOWFLAG', flag);
    // },
}
const mutations = {
    // CHANGESHOWFLAG:(state,data) =>{
    //     state.isShowLogin = data;
    // },
}
export default {
    state,
    getters,
    actions,
    mutations,
}