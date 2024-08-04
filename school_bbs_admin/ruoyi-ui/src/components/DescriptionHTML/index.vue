<template>
    <div><el-button type="text" @click="open">查看详情</el-button></div>
</template>

<script>
import { MessageBox } from 'element-ui'
export default {
    name: 'DescriptionHTML',
    props: {
        msg: {
            type: String
        }
    },
    data() {
        return {

        }
    },
    methods: {
        open() {
            MessageBox.alert(this.$props.msg, '详情', {
                confirmButtonText: '关闭',
                dangerouslyUseHTMLString:true
            })
        }
    }
}
</script>

<style scoped></style>