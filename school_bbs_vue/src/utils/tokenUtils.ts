const k = "token"

class tokenUtils {
    // 存储token
    set(v: string) {
        window.localStorage.setItem(k, "Bearer " + v)
    }

    // 获取token
    get() {
        return window.localStorage.getItem(k)
    }

    // 删除token
    del() {
        window.localStorage.removeItem(k)
    }
}

export default new tokenUtils;