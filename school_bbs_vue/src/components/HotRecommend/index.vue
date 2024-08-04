<template>
    <div class="recommentContainer">
        <div class="card" v-for="(i, index) in hots" :key="index"
            :style="{ 'background-image': i.urlList && i.urlList[0] ? `url('${i.urlList[0]}')` : 'url(https://ckl-school-bbs.oss-cn-shenzhen.aliyuncs.com/2024-04-23/88b35732-76ef-4667-b1e8-b893288b9575NaN)' }"
            style="background-size: cover;background-position: center;" @click="goPostInfo(i.id)">
            <div class="card-index"><i class="el-icon-ali-remenhot1"></i></div>
            <div class="cardInfo">
                <div class="cardFont">
                    <p style="color: rgb(255, 255, 255); display: flex; justify-content: space-between;">
                        <span style="
                            font-size: 14px;
                            display: -webkit-box;
                            overflow: hidden;
                            text-overflow: ellipsis;
                            -webkit-line-clamp: 2;
                            -webkit-box-orient: vertical;
                            max-height: 32px;
                            width: 320px;
                        ">
                            {{ htmlToString(i.content) }}
                        </span>
                        <span>
                            <span style="color: #f56c6c;"><i class="el-icon-ali-xihuan1"></i>{{ i.likes }}</span> &nbsp;
                            <span style="color: #7ec55b;"><i class="el-icon-ali-ico_home_reply"></i> {{ i.favorites ?
                                i.favorites : 0 }}</span>
                        </span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { ref, getCurrentInstance, reactive, toRef, onBeforeUnmount } from 'vue';
import { getHots } from '@/api/post';
import type { R } from '@/type/R';
import type { PostMain } from '@/type/postMainType';
import { ElNotification } from 'element-plus';
import { htmlToString } from '@/utils/StringUtils'
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const $store = useStore()
const $route = useRouter()
const instance = getCurrentInstance()

const hots = ref<PostMain[]>()
const urlList = ref<String[]>([])

// 方法================================
const handleHots = async () => {
    const res: R = await getHots();
    if (res.code == 200) {
        hots.value = res.data
        for (let i = 0; i < res.data.length; i++) {
            urlList.value.push(res.data[i].urlList[0] ? res.data[i].urlList[0] : '@/assets/swiper04.webp')
        }
    } else {
        ElNotification({
            type: 'error',
            message: res.msg
        })
    }
}

// 前往详情页
const goPostInfo = async (id) => {
    $route.push({ path: `/postinfo/${id}` })
}

handleHots()


</script>
<style lang="scss" scoped>
p {
    line-height: 1.1;
    margin: 0 0 6% 0;
}

/* 添加新的样式以定位序号容器 */
.card-index {
    position: absolute;
    top: 0px;
    /* 调整为所需的偏移量 */
    right: 0px;
    /* 调整为所需的偏移量 */
    z-index: 1;
    /* 确保序号位于图片之上 */
}

.card-index i {
    font-size: 60px;
    color: red;
}

.recommentContainer {
    display: flex;
    height: 270px;
    margin: 40px 0 60px 0;
    justify-content: space-around;
}

.card {
    height: 300px;
    width: 30%;
    // background-image: url('@/assets/swiper04.webp');
    position: relative;
    overflow: hidden;
    cursor: pointer;
}

.cardInfo {
    // background-image: linear-gradient(to top, #509cffcc, rgba(0, 0, 0, 0));
    background-color: rgba(64, 160, 255, 0.8);
    width: 100%;
    position: absolute;
    bottom: 0px;
}

.card .cardInfo {
    height: 0%;
    transition: 0.5s;
}

.card:hover .cardInfo {
    height: 30%;
    transition: 0.5s;
}

.cardFont {
    margin: 5% 5%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    color: rgb(214, 214, 214);
}

.img {
    height: 100%;
}

.cardTwo {
    height: 270px;
    width: 70px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-image: url("@/assets/swiper03.webp");
    // background-color: rgb(64, 158, 255);
    user-select: none;
    cursor: pointer;
}

.cardTwoFont {
    font-size: 18px;
    font-weight: 600;
    color: white;
}
</style>

<script lang="ts">
export default {
    name: "HotRecommend"
}
</script>