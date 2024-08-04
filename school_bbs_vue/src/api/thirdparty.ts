import request from "@/utils/request";

export function getSignedUrl(objectName) {
    return request({
        url: '/thirdparty/oss/signedUrl/' + objectName,
        method: 'get'
    })
}

export function getPolicy() {
    return request({
        url: '/thirdparty/oss/policy',
        method: 'post',
    })
}

export function sendCode(phone) {
    return request({
        url: '/thirdparty/sms/sendcode/' + phone,
        headers: {
            isToken: false
        },
        method: 'get',
    })
}

export function uploadFile(data) {
    return request({
        url: 'http://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com',
        headers: {
            isToken: false
        },
        method: 'post',
        data: data
    })
}