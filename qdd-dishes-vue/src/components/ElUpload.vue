<template>
<div>  <el-upload
    class="upload-demo"
    action="http://192.168.124.3:8080/cms/upload/uploadFile"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    accept="image/*"
>
  <img v-if="imageUrl" :src="imageUrl" class="avatar" />
  <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
</el-upload></div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

import type { UploadProps } from 'element-plus'

const imageUrl = ref()

const emit = defineEmits<{
  (event: 'update:imageUrl', imageUrl: string): void;
}>();
const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response,
    uploadFile
) => {
  // console.log(imageUrl.value)
  // console.log(response.data.url)
  // console.log(response.data.data)
  // imageUrl.value = URL.createObjectURL(uploadFile.raw!)
  imageUrl.value = response.data.data
  if (imageUrl.value) {
    emit('update:imageUrl', response.data.url);
  }
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  // if (rawFile.type !== 'image/jpeg') {
  //   ElMessage.error('Avatar picture must be JPG format!')
  //   return false
  // } else if (rawFile.size / 1024 / 1024 > 2) {
  //   ElMessage.error('Avatar picture size can not exceed 2MB!')
  //   return false
  // }
  return true
}
</script>



<style>
.upload-demo{
  width: 180px;
  height: 180px;
  margin-bottom: 10px;

}
.avatar-uploader-icon{
  width: 180px;
  height: 180px;
  border: 1px dashed #3e3d3d; /* 设置边框宽度为2px，边框样式为虚线，颜色为黑色 */
  border-radius: 10px;
  margin-bottom: 10px;
}
.ep-upload__input {
  display: none;
}

.avatar {
  width: 181px;
  height: 181px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
