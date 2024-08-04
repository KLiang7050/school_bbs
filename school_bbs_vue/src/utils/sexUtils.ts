class sexUtils{
    getStr(code){
        if(code === 1){
            return "男"
        }else if(code ===2){
            return "女"
        }else{
            return "未知"
        }
    }

    getCode(str){
        if(str === "男"){
            return 1
        }else if(str === "女"){
            return 2
        }else{
            return 0
        }
    }
}

export default new sexUtils;