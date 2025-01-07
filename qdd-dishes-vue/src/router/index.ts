import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
// import Home from '@/pages/Home.vue';
// import MovieDetail from '@/pages/MovieDetail.vue';
// import Player from '@/pages/MoviePlayer.vue';

// 定义路由配置的类型
const routes: Array<RouteRecordRaw> = [

    {
        path: '/',
        name: 'Home',
        component: () => import('@/pages/Home.vue'),
        props: true,
    },
    {
        path: '/update',
        name: 'update',
        component: () => import('@/components/ElUpload.vue'),
        props: true,
    },


    {
        path: '/:catchAll(.*)', // 匹配所有未定义路由
        redirect: '/',
    },
];

const router = createRouter({
    history: createWebHashHistory(), // 修改这里为 hash 模式
    routes,
});

export default router;
