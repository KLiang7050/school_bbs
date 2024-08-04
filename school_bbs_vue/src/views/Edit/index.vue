<template>
  <div style="display: flex;justify-content: space-between;align-items: center;">
    <el-text class="mx-1" size="large" style="font-size: 25px;">- 发布文章 -</el-text>
    <div>
      <el-button type="info" @click="goBack">返回</el-button>
      <!-- TODO 保存草稿功能未实现 -->
      <!-- <el-button type="primary">保存草稿</el-button> -->
      <el-button type="primary" @click="handlePublish" v-show="!isUpdate">发布</el-button>
      <el-button type="primary" @click="handleUpdate" v-show="isUpdate">更新</el-button>
    </div>
  </div>
  <el-divider />
  <div style="margin: 20px 0;display: flex;">
    <el-text>文章类型：</el-text>
    <el-select v-model="publishData.typeId" class="m-2" placeholder="请选择文章类型" size="large">
      <el-option v-for="(item, index) in typeList" :key="index" :value="item.id" :label="item.type"
        @click="handleTypeChange(item.type)" />
    </el-select>
    <el-text style="margin-left: 20px;">文章标题：</el-text>
    <el-input v-model="publishData.title" type="text" placeholder="请输入文章标题" style="width: 400px;"></el-input>
  </div>
  <div style="margin: 20px 0;">
  </div>
  <div style="border: 1px solid #ccc;">
    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode" />
    <Editor style="height: 395px; " v-model="publishData.content" :defaultConfig="editorConfig" :mode="mode"
      @onCreated="handleCreated" />
  </div>
</template>

<script setup lang="ts">
import { ElNotification } from 'element-plus'
import { IToolbarConfig, IEditorConfig } from '@wangeditor/editor';
import { Editor, Toolbar } from '@wangeditor/editor-for-vue';
import '@wangeditor/editor/dist/css/style.css';
import { onBeforeUnmount, ref, shallowRef, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';

import ossConfig from '@/config/oss'
import { getUUID } from '@/utils/uuidUtils';

import { getPolicy, uploadFile } from '@/api/thirdparty';
import { getTypeList, addMain, getPostInfo, updatePost } from '@/api/post';

const $store = useStore()
const $router = useRouter()
const $route = useRoute()

const formData = new FormData()
const typeList = reactive([])
const typeId = ref<number>()
const publishData = reactive({
  id: null,
  contentId: '',
  typeId,
  type: '',
  title: '',
  content: '',
  mediaUrls: '',
})
const isUpdate = ref(false)

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()
const mode = ref('default')

const toolbarConfig: Partial<IToolbarConfig> = {
}

toolbarConfig.excludeKeys = [
  'todo',
  'insertTable',
  'blockquote',
  'fullScreen',
  'insertImage',
  'insertVideo'
]

// 初始化 MENU_CONF 属性
const editorConfig: Partial<IEditorConfig> = {
  placeholder: "请输入内容...",
  MENU_CONF: {}

  // 其他属性...
} 

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

const goBack = () => {
  $router.back();
}

const handleTypeChange = (newType) => {
  publishData.type = newType
}

const handleTypeList = async () => {
  try {
    let res = await getTypeList();
    typeList.push(...res.data)
  } catch (err) {}
}

const handlePublish = async () => {
  if (!publishData.typeId || publishData.title === '') {
    ElNotification({
      type: 'error',
      message: '请选择文章类型并填写文章标题',
    });
    return
  }
  let res = await addMain(publishData)
  if (res.code == 200) {
    ElNotification({
      type: 'success',
      message: '发布成功',
    });
    $router.push("/postinfo/" + res.id)
  } else {
    ElNotification({
      type: 'error',
      message: res.msg
    })
  }
}

const handleUpdate = async () => {
  const res = await updatePost(publishData)
  if (res.code === 200) {
    ElNotification({
      type: 'success',
      message: '更新成功',
    });
    goBack()
  } else {
    ElNotification({
      type: 'error',
      message: res.msg
    })
  }
}

// 修改 uploadImage 菜单配置
editorConfig.MENU_CONF['uploadImage'] = {
  server: '/',
  fieldName: 'custom-field-name',
  async customUpload(file, insertFn) {
    try {
      let res1 = await getPolicy()
      let key = res1.data.dir + getUUID() + 'type=images'
      formData.set('key', key)
      formData.set('policy', res1.data.policy)
      formData.set('OSSAccessKeyId', res1.data.accessid)
      formData.set('signature', res1.data.signature)
      formData.set('success_action_status', '200')
      formData.set('file', file)
      await uploadFile(formData);
      let url = ossConfig.ossURL + key
      insertFn(url, key)
      if (publishData.mediaUrls) publishData.mediaUrls += ';;'
      publishData.mediaUrls += url
    } catch (err) {}
  }
}

editorConfig.MENU_CONF['uploadVideo'] = {
  // 自定义上传
  async customUpload(file, insertFn) {
    try {
      let res1 = await getPolicy()
      let key = res1.data.dir + getUUID() + 'type=video'
      formData.set('key', key)
      formData.set('policy', res1.data.policy)
      formData.set('OSSAccessKeyId', res1.data.accessid)
      formData.set('signature', res1.data.signature)
      formData.set('success_action_status', '200')
      formData.set('file', file)
      // formData.set('process', 'video/snapshot,t_1000,f_jpg,w_800,h_600')
      await uploadFile(formData);
      let url = ossConfig.ossURL + key
      insertFn(url, '',url)
      if (publishData.mediaUrls) publishData.mediaUrls += ';;'
      publishData.mediaUrls += url
    } catch (err) {}
  }
}

const init = async () => {
  const id = $route.params.id
  console.log(id);
  if (id !== null && id !== '') {
    isUpdate.value = true;
    const res = await getPostInfo(id)
    const data = res.data
    publishData.id = data.id
    publishData.contentId = data.contentId
    publishData.content = data.content
    publishData.title = data.title
    publishData.typeId = data.typeId
    publishData.type = data.type
  }
  handleTypeList()
}
init()
</script>

<style scoped></style>

<script lang="ts">
export default {
  name: "Edit"
}
</script>