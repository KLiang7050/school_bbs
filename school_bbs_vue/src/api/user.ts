import request from "@/utils/request";

// export function reqCode() {
//     return request({
//         url: '/user/user/code',
//         headers: {
//             isToken: false
//         },
//         method: 'get',
//         timeout: 20000
//     })
// }

export function reqCodeImg() {
    return request({
        url: '/user/user/codeimg',
        headers: {
            isToken: false
        },
        method: 'get',
        timeout: 20000
    })
}

export function reqLogin(data) {
    return request({
        url: '/user/user/login',
        data: data,
        headers: {
            isToken: false
        },
        method: 'post',
        timeout: 20000
    })
}

export function logout() {
    return request({
        url: '/user/user/logout',
        method: 'post'
    })
}

// 注册方法
export function reqRegister(data) {
    return request({
        url: '/user/user/register',
        headers: {
            isToken: false
        },
        method: 'post',
        data: data
    })
}

export function reqUserInfo() {
    return request({
        url: '/user/user/info',
        method: 'get'
    })
}

export function reqUserInfoById(id) {
    return request({
        url: `/user/user/infobyid`,
        method: 'get',
        params: {
            id
        }
    })
}

export function update(data) {
    return request({
        url: '/user/user/update',
        method: 'post',
        data: data
    })
}

export function updateAvatar(data) {
    return request({
        url: '/user/user/updateavatar',
        method: 'post',
        data: {
            avatar: data
        },
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}

export function uploadFile(data, filename) {
    return request({
        url: '/user/joinus/upload',
        method: 'post',
        data: {
            file: data
        },
        params: {
            filename
        },
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}

export function changePWD(data) {
    return request({
        url: '/user/user/changepwd',
        method: 'post',
        data: {
            newValue: data.new,
            oldValue: data.old,
            code: data.code,
            uuid: data.uuid
        }
    })
}

export function changeTel(data) {
    return request({
        url: '/user/user/changetel',
        method: 'post',
        data: {
            newValue: data.new,
            oldValue: data.old,
            code: data.code,
            uuid: data.uuid
        }
    })
}

export function studentVerification(code) {
    return request({
        url: '/user/user/studentVerification',
        method: 'post',
        params: { code }
    })
}

export function reqSchoolList() {
    return request({
        url: '/user/school/list',
        method: 'get'
    })
}

export function getJoinList() {
    return request({
        url: `/user/joinus/list`,
        method: 'get',
    })
}

export function getJoin(id) {
    return request({
        url: `/user/joinus/one/${id}`,
        method: 'get',
    })
}


export function saveJoinus(datas) {
    return request({
        url: `/user/joinus/save`,
        method: 'post',
        data: datas
    })
}

export function commitJoinus(datas) {
    return request({
        url: `/user/joinus/commit`,
        method: 'post',
        data: datas
    })
}
