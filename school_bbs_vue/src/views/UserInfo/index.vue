<template>
    <dev>
        <UserInfoHeader />
        <router-view></router-view>
    </dev>
</template>

<script setup lang="ts">
import UserInfoHeader from "@/components/UserInfoHeader/index.vue"

</script>

<style scoped></style>
<style></style>

<script lang="ts">
export default {
    name: "UserInfo"
}
</script>
