<template>
  <!-- 弹窗容器 -->
<!--  <div  class="popup">-->
    <el-dialog  v-model="props.isVisible"
                :show-close="false"
                :before-close="beforeClose"
                width="80%" class="popup"
                :style="{ borderRadius: '15px' }">
    <div class="popup-content">
      <h2>今天吃什么?!!</h2>
      <div class="image-container">
        <!-- 随机变动的菜品照片展示 -->
        <img :src="currentImage" alt="随机菜品" />
        <h3  >{{ currentFoods.name }}</h3>
      </div>
      <button @click="startLottery" class="start-button">听天由命</button>
    </div>
    </el-dialog>
<!--  </div>-->
</template>

<script setup>
import { ref, onMounted,defineProps, defineEmits } from 'vue';
// 导入静态图片
import staticImage from '@/assets/img/ready.jpeg';
// 食品图片列表
const foodImages = ref([])
const foods = ref([])
const currentFoods=ref({
  name:'准备好了吗?'
})
// 当前显示的图片
const currentImage = ref(staticImage);
// 定义 emit 事件
const emit = defineEmits(['update']);

// before-close 钩子事件
const beforeClose = () => {
  // 触发父组件事件并传递数据
  // console.log("开始关闭")
  emit('update', false);
  currentFoods.value.name='准备好了吗?'
   currentImage.value = staticImage

};
// 抽奖进行中状态
const isLotteryInProgress = ref(false);

// 定义父组件传递的 isVisible prop
const props = defineProps({
  isVisible: {
    type: Boolean,
    default: true // 默认显示
  }
});
// 弹窗是否可见
// const isVisible = ref(props.isVisible);
// 开始抽奖
const startLottery = () => {
  if (isLotteryInProgress.value) return; // 防止重复点击
  isLotteryInProgress.value = true;
  currentFoods.value.name=''
  let count = 0;
  const interval = setInterval(() => {
    // 随机选择菜品图片
    const randomIndex = Math.floor(Math.random() * foodImages.value.length);
    currentImage.value = foodImages.value[randomIndex];

    // 每秒更新一次，持续2秒
    count++;
    if (count >=25) {
      clearInterval(interval);
      // 最终确定一个图片
      const finalIndex = Math.floor(Math.random() * foodImages.value.length);
      console.log(finalIndex)
      console.log(foodImages.value)
      console.log(foodImages.value[finalIndex])
      currentImage.value = foodImages.value[finalIndex];
      isLotteryInProgress.value = false;
// 找出 foods 中所有 imageUrl 与 currentImage.imageUrl 相同的数据

       currentFoods.value = foods.value.filter(item => item.imageUrl === currentImage.value)[0];

    }
  }, 100); // 每0.2秒切换一次图片
};
import {dishes} from '@/api/home'; // 引入接口
// 组件挂载后显示弹窗
onMounted(async () => {

  var res = await dishes(0);
  // 取出 res.data 中的 imageUrl，并将其存放到 foodImages 数组中
  foods.value=res.data
   foodImages.value = res.data.map(item => item.imageUrl);
  // const randomIndex = Math.floor(Math.random() * foodImages.value.length);
  // currentImage.value = foodImages.value[randomIndex];

});
</script>

<style scoped>
::v-deep .ep-dialog  {
  padding: 0 !important;
  border-radius: 10px;
}
/* 弹窗基本样式 */
.popup {
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  justify-content: center;
  align-items: center;
}

.popup-content {
  background: #fff;
  margin-top: -20px;
  border-radius: 10px;
  text-align: center;

}

h2 {
  font-size: 24px;
  margin-bottom: 10px;
}
h3 {
  font-size: 18px;
  line-height: 1.0;
}

.image-container {

  width: 100%;
  height: 350px;
  margin: 20px auto;
  overflow: hidden;
  position: relative;
}

img {
  width: 100%;
  height: 88%;
  object-fit: fill;
  transition: transform 0.2s ease-in-out;
}

.start-button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  margin-bottom: 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 18px;
}

.start-button:hover {
  background-color: #45a049;
}
</style>
