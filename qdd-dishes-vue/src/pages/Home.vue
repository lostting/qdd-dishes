<template>
  <div class="home-container">

    <!-- 全屏遮罩 -->
    <div v-show="isVisible" class="overlay" :class="{ 'fade-out': !isVisible }">
      <div class="loading-container">
        <img src="@/assets/img/loading.jpg" alt="Loading" class="loading-image" />
        <p class="loading-text">Loading...</p>
      </div>
    </div>
    <!-- 顶部标题区域 -->
    <div class="header">
      <div class="store-info">
        <img class="store-img" @click="addDishDialogVisible=true" src="@/assets/img/eat.jpg" alt="钱多多的店铺"/>

      </div>
      <div class="store-details">
        <h2>钱多多的食物小店</h2>
        <p>致那些曾经做过的食物
        </p>
        <p>可能好吃,也可能不好吃</p>
      </div>


    </div>
    <!-- 在主体区域下方添加“我不知道吃什么”按钮 -->
    <div class="choose-random">
      <button @click="openRandomPopup">不知道吃什么，点这里</button>
    </div>
    <!-- 主体区域 -->
    <div class="main-content">
      <!-- 左侧分栏导航 -->
      <div class="sidebar">
        <ul>
          <li
              v-for="(category, index) in categories"
              :key="index"
              :class="{ active: activeIndex === index }"
              @click="changeCategory(index,category.id)"
              :value="category.id"
          >
            {{ category.name }}
          </li>
        </ul>
      </div>

      <!-- 右侧内容展示区域 -->
      <div class="content-right">
        <div v-if="dishList.length > 0" v-for="(dish, index) in dishList" :key="dish.id" class="item"
             @click="openDialog(dish)"
        >
          <div class="dish-left">
            <div class="dish-div-image">
            <img :src="dish.imageUrl" alt="菜品图片" class="dish-image"/>

            </div>
            <div class="dish-info">
              <p class="dish-name">{{ dish.name }}</p>
              <span class="dish-price">{{ dish.price ? `￥${dish.price}` : '0.01¥' }}</span>
            </div>
          </div>
          <div class="dish-right">
            <el-icon @click.stop="addToCart(dish)"
                     class="icon-button"
            ><Plus /></el-icon>
<!--            <Plus @click.stop="addToCart(dish)"-->
<!--                  class="icon-button" />-->
<!--            <el-button  icon="el-icon-plus" circle></el-button>-->
          </div>
        </div>
        <div v-else >
          <p>暂无菜品</p>
        </div>
        <!-- 更多内容区域可以根据分类扩展 -->

        <!-- 弹窗 -->
        <el-dialog class="custom-dialog"
            v-model="dialogVisible"
            :show-close="false" :show-header="false"
            width="80%"
                   :style="{ borderRadius: '15px' }"
        >
          <div class="content-detail">
            <img :src="selectedDish.imageUrl" alt="菜品图片" style="width: 100%;height: 300px; margin-top: -16px; border-radius: 15px"/>
            <h2 class="custom-heading">{{ selectedDish.name }}</h2>
            <p class="custom-description">{{ selectedDish.description || '暂无描述' }}</p>
            <span class="custom-made">已点餐：{{ selectedDish.count }} 次</span>
          </div>
          <span slot="footer" class="dialog-footer">
      </span>
        </el-dialog>
      </div>
    </div>

<!--    每日一抽-->
    <LotteryPopup :isVisible="isPopupVisible"  @update="updateVisibility"  />

    <!--保存 弹窗 -->
    <el-dialog class="custom-dialog"
               v-model="addDishDialogVisible"
               :show-close="false" :show-header="false"
               width="80%"
               :before-close="addDialogBeforeClose"
               :style="{ borderRadius: '15px' }"
    >
      <div class="content-add"  >


        <ElUpload   @update:imageUrl="updateImageUrl" />
        <!-- 表单 -->
        <el-form :model="addDish" >
          <!-- 名称输入框 -->
          <el-form-item prop="name">
            <el-input
                v-model="addDish.name"
                placeholder="请输入菜品名称"
                >
            </el-input>
          </el-form-item>

          <el-form-item prop="categoryId">
            <el-select v-model="addDish.categoryId" placeholder="请选择分类">
              <el-option
                  v-for="category in categories"
                  :key="category.id"
                  :label="category.name"
                  :value="category.id">
              </el-option>
            </el-select>
          </el-form-item>

          <!-- 描述输入框 -->
          <el-form-item  prop="description">
            <el-input
                v-model="addDish.description"
                placeholder="请输入菜品描述"
                >
            </el-input>
          </el-form-item>

          <!-- 已点餐次数输入框 -->
          <el-form-item  prop="count">
            <el-input
                v-model="addDish.count"
                placeholder="请输入已点餐次数"
               >
            </el-input>
          </el-form-item>

          <!-- 提交按钮 -->
          <el-form-item>
            <el-button  type="primary" @click="handleSubmit" >提交</el-button>
          </el-form-item>
        </el-form>
      </div>

    </el-dialog>

  </div>
</template>
<script setup>
import {onMounted, ref} from 'vue';
import LotteryPopup from '@/components/FoodLottery.vue';
import {pageLabel, dishes, addDishes,addDishCount} from '@/api/home'; // 引入接口
import {
  CirclePlus,Plus
} from "@element-plus/icons-vue";
import ElUpload from "@/components/ElUpload.vue";
import {ElMessage} from "element-plus";
const dishList  = ref([]);  // 存储菜品数据
const selectedDish  = ref();  // 当前展开的
const activeIndex = ref(0)
const activeCategory = ref(0)
const categories = ref([])
const addDish = ref({
  name: '',
  description: '',
  count: 1,
  categoryId:1
})
//全屏遮罩
const isVisible = ref(true)
//弹窗遮罩
const dialogVisible = ref(false)
//新增dish遮罩
const addDishDialogVisible = ref(false)
//今天吃什么遮罩
const isPopupVisible = ref(false);
// 打开弹窗并设置选中的菜品
const openDialog=(dish)=> {
  selectedDish.value = dish;
  dialogVisible.value = true;
}
const addDialogBeforeClose=()=> {

    addDish.value={}
  addDishDialogVisible.value=false

}
// 保存dish
const handleSubmit = async () => {
  try {
    // 假设 addDishes 是一个异步函数，返回一个 Promise
    console.log(addDish.value)
    let iResponse = await addDishes(addDish.value);

    // 如果成功，更新状态并提示新增成功
    // addDish.value = dish;
    // dialogVisible.value = true; // 如果需要显示对话框，可以取消注释
    // if (iResponse.code === 200) {
    //   // message.success('新增成功！');
    //   ElMessage.success('新增成功！');  // 显示成功弹窗
    //
    // }
    ElMessage.success('新增成功！');
    // 成功提示
  } catch (error) {
    // 如果出错，提示失败
    ElMessage.error('新增失败，请稍后再试！');
  }
  addDishDialogVisible.value=false
};

// 打开弹窗并设置选中的菜品
const addToCart= async (dish)=> {
  // 这里可以处理添加到购物车的逻辑  addCount
  // console.log(,`已添加到购物车`);
   await addDishCount(dish.id);
  // ElMessage.success(dish);
  await changeCategory(activeIndex.value, activeCategory.value)

  ElMessage.success('点餐次数+1！');

}
const openRandomPopup=()=> {
  // 这里可以处理添加到购物车的逻辑
  console.log(isPopupVisible.value)
  isPopupVisible.value=true
}
// 父组件的事件处理函数
const updateVisibility = (value) => {
  console.log("变动了"+value)
  isPopupVisible.value = value;
};
// 父组件的事件处理函数
const updateImageUrl = (value) => {
  console.log("变动了"+value)
  addDish.value.imageUrl = value;
};
// 分类切换处理函数
const changeCategory = async (index,categoryId) => {
  activeIndex.value = index;
  activeCategory.value = categoryId;



  // 获取当前分类的 ID（假设 categoryId 存储在一个数组或对象中）
  // const categoryId = .id;  // 假设 categories 是你的分类数据数组

  try {
    // 根据分类 ID 请求对应的菜品接口
    const response = await dishes(categoryId);

      dishList.value = response.data;

  } catch (error) {
    console.error('请求菜品数据失败:', error);
    dishList.value = [];  // 请求失败时清空菜品列表
  }
};

async function initPageLabel() {
  const response = await pageLabel(); // 调用接口
  // 假设接口返回的数据结构是 { data: [...] }
  // 更新分类数据
  console.log(response)
  categories.value = response.data

  // 根据分类 ID 请求对应的菜品接口
  const response2 = await dishes(categories.value[0].id);

  dishList.value = response2.data;
  // const response2 = await dishes(); // 调用接口
  // // 假设接口返回的数据结构是 { data: [...] }
  // // 更新分类数据
  // console.log(response2)
}


onMounted(async () => {
  await initPageLabel()

  // categories.value = response.data

});
</script>

<style scoped>
/* 容器布局 */
.home-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 水平居中 */
  text-align: center; /* 文字居中 */
  height: 100vh;
  width: 100%;
  /*margin-left: 1%; */
}

/* 顶部标题区域 */
.header {

  padding: 10px;
  width: 90%;

  display: flex;

  justify-content: center; /* 水平居中 */
  /* align-items: center; /* 垂直居中 */
  text-align: center; /* 文字居中 */
}

.store-info {
  width: 30%;
  display: flex;
  justify-content: flex-end; /* 使内容在主轴方向上右对齐 */
  align-items: flex-end; /* 使内容在交叉轴方向上底部对齐 */
}

.store-details {
  width: 70%;
  justify-content: flex-start; /* 使内容在主轴方向上左对齐 */
  align-items: center; /* 使内容在交叉轴方向上居中对齐 */
}

.store-img {
  width: 70px;
  height: 70px;
  margin-right: 10px;
  border-radius: 50%;
}

.store-details h2 {
  width: 100%;
  margin-bottom: 0px;
  font-size: 18px;
  color: #333;
}

.store-details p {
  margin: 5px 0;
  font-size: 12px;
  color: #888;
}
/* 加载内容容器 */
.loading-container {
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%; /* 确保容器高度为 100% */
  width: 100%; /* 确保容器宽度为 100% */
}
/* 加载图片样式 */
.loading-image {
  width: 100px; /* 图片的宽度 */
  height: 100px; /* 图片的高度 */
  margin-bottom: 10px; /* 图片和文字之间的间距 */
}

/* 主体区域布局 */
.main-content {
  display: flex;
  flex: 1;
  overflow: hidden;
}

/* 左侧分栏导航 */
.sidebar {
  width: 100px;
  background-color: #f8f8f8;
  overflow-y: auto;
}

.sidebar ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.sidebar li {
  padding: 10px;
  margin-bottom: 5px;
  text-align: center;
  cursor: pointer;
  font-size: 14px;
  color: #333;
}

.sidebar li.active {
  background-color: #ff8800;
  color: #fff;
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  border-bottom: 1px solid #e5e5e5;
  cursor: pointer;
}

.dish-left {
  display: flex;
  align-items: center;
  flex-grow: 1; /* 左侧部分占据剩余空间 */

}
.content-right {
  flex: 1;
  width: 80%;
  padding: 10px;
  overflow-y: auto;
  background-color: #fff;
}
.dish-div-image {
  width: 90px;
  max-width: 90px;
  height: 90px;
  object-fit: fill;
  margin-right: 15px;
}
.dish-image {
  width: 90px;
  max-width: 90px;
  height: 90px;
  object-fit: fill;
}

.dish-info {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 左对齐 */
  width: 60%;
}

.dish-name {
  font-size: 16px;
  font-weight: bold;
}

.dish-price {
  font-size: 14px;
  color: #ff5722;
}

.dish-right {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10%; /* 右侧部分占据 10% 宽度 */
}


.icon-button {
  font-size: 20px; /* 增加图标大小 */
  padding: 5px; /* 给图标周围增加间距，确保背景色覆盖 */
  stroke-width: 5;
  background-color: gold; /* 设置背景色为黄色 */
  border-radius: 50%; /* 圆形背景 */
  cursor: pointer; /* 鼠标悬停时变为指针 */
  transition: background-color 0.3s ease; /* 增加背景色变化的过渡效果 */
}
::v-deep .ep-dialog  {
  padding: 0 !important;
}
/* 去掉头部显示但保留其他自定义样式 */
::v-deep .el-dialog__header {

    height: 0 !important;
    padding: 0 !important;

}
.content-detail  span {
  color: #ff5722;
  font-weight: bold;
  font-size: 25px;
  width: 98%;
  padding: 0;

}
.content-add   {
  font-weight: bold;
  font-size: 25px;
  width: 96%;
  padding-left: 2%;
  padding-bottom: 1%;


}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgb(81, 156, 239); /* 全黑遮罩 */
  z-index: 9999; /* 确保遮罩在最上层 */
  animation: fadeLeftToRight 1.5s ease-in-out forwards;  /* 从左到右消失动画 */
  animation-delay: 1.5s;  /* 延迟 1.5 秒后开始动画 */
}

@keyframes fadeLeftToRight {
  0% {
    opacity: 1;
    transform: translateX(0); /* 初始状态，完全覆盖 */
  }
  100% {
    opacity: 1;
    transform: translateX(100%); /* 平移到右侧，完全消失 */
}
}
/* 设置具体的样式 */
.custom-heading {
  line-height: 1; /* 行高 */
  margin-top: 5px; /* 顶部间距 */
}

.custom-description {
  line-height: 1; /* 行高 */
  margin-top: 5px; /* 顶部间距 */
}

.custom-made {
line-height: 3;
  margin-top: 3px; /* 顶部间距 */
  margin-bottom: 5px;
}
.choose-random {
  text-align: center;
  justify-content: center; /* 水平居中 */
  width: 100%;
}

.choose-random button {
  padding: 10px 20px;
  width: 100%;
  font-size: 16px;
  background-color: #5dc4e6;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

</style>
