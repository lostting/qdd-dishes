package cn.qdd.foods.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 东方曜
 */
@Component
public class DictUtils {

    public static HashMap<String, String> osMap = new HashMap<>();
    public static HashMap<String, String> pidMap = new HashMap<>();
    public static HashMap<String, String> hoursMap = new HashMap<>();
    public static HashMap<String, String> videoTypeMap = new HashMap<>();
    public static HashMap<String, String> adSrcMap = new HashMap<>();
    public static HashMap<String, String> adPosMap = new HashMap<>();
    public static HashMap<String, String> cityMap = new HashMap<>();
    public static HashMap<String, String> refMap = new HashMap<>();
    public static HashMap<String, String> v2Id = new HashMap<>();
    public static HashMap<String, String> adCategoryMap = new HashMap<>();
    public static HashMap<String, String> temidMap = new HashMap<>();
    public static HashMap<String, String> typeMap = new HashMap<>();

    static {

        typeMap.put("all", "整体");
        typeMap.put("play", "曾播放");
        typeMap.put("play_is_not", "未曾播放");

        adCategoryMap.put("0", "策略广告");
        adCategoryMap.put("1", "兜底广告");
        adCategoryMap.put("100", "策略+兜底");

        temidMap.put("0", "0");
        temidMap.put("focus", "焦点图");
        temidMap.put("drawer", "抽屉");
        temidMap.put("guide", "快速导航");
        temidMap.put("more", "更多");

        cityMap.put("1", "一线城市");
        cityMap.put("2", "二线城市");
        cityMap.put("3", "三线城市");
        cityMap.put("4", "四线城市");
        cityMap.put("5", "五线城市");
        v2Id.put("0_launch", "jinri-kaiping");
        v2Id.put("2_launch", "daquan-kaiping");
        v2Id.put("1_launch", "hanju-kaiping");
        v2Id.put("3_launch", "zhima-kaiping");
        v2Id.put("2_PreRolls", "daquan-qiantie");
        v2Id.put("0_PreRolls", "jinri-qiantie");
        v2Id.put("3_PreRolls", "hanju-qiantie");
        v2Id.put("1_PreRolls", "zhima-qiantie");


        refMap.put("home_index_page", "首页-推荐");
        refMap.put("home_index_episode_page", "首页-电视剧");
        refMap.put("home_index_movie_page", "首页-电影");
        refMap.put("home_index_variety_page", "首页-综艺");
        refMap.put("home_index_cartoon_page", "首页-动漫");
        refMap.put("topic", "专题列表页");
        refMap.put("topic_detail", "专题详情页");
        refMap.put("ranking", "排行榜首页");
        refMap.put("ranking_detail", "排行榜详情页");
        refMap.put("user", "我的");
        refMap.put("user_login", "我的-登录页面");
        refMap.put("user_login_wechat", "我的-微信登录页");
        refMap.put("user_login_weibo", "我的-微博登录页");
        refMap.put("user_login_qq", "我的-qq登录页");
        refMap.put("user_history", "我的-观看历史");
        refMap.put("user_download", "我的-离线缓存");
        refMap.put("user_download_multi", "我的-多剧集缓存页");
        refMap.put("user_downloading", "我的-正在缓存");
        refMap.put("user_collect", "我的收藏");
        refMap.put("user_playlist", "我的播单");
        refMap.put("search", "搜索");
        refMap.put("search_result", "搜索结果页");
        refMap.put("search_noresult", "搜索无结果页");
        refMap.put("search_result_episode", "搜索结果页-电视剧");
        refMap.put("search_result_movie", "搜索结果页-电影");
        refMap.put("search_result_cartoon", "搜索结果页-卡通");
        refMap.put("page_index_episode", "首页-电视剧");
        refMap.put("search_result_variety", "搜索结果页-综艺");
        refMap.put("page_index_variety", "首页-综艺");
        refMap.put("page_index", "首页");
        refMap.put("bind_phone_number_page", "手机号绑定页面");
        refMap.put("page_index_film", "首页-电影");
        refMap.put("search_result_documentary", "搜索结果页-纪录片");
        refMap.put("page_index_cartoon", "首页-动漫");
        refMap.put("page_index_hanjudaquan", "首页-韩剧推荐");
        refMap.put("channel", "筛选页");
        refMap.put("channel_detail", "筛选详情页");
        refMap.put("halfscreen", "半屏页");
        refMap.put("fullscreen", "全屏页");
        refMap.put("html", "H5页面");
        refMap.put("clips_feed", "看点页面");
        refMap.put("halfscreen_sv", "单视频半屏页");


        pidMap.put("0", "今日影视");
        pidMap.put("1", "韩剧大全");
        pidMap.put("2", "影视大全");
        pidMap.put("3", "芝麻影视大全");


        adSrcMap.put("gdt", "广点通-节节开花");
        adSrcMap.put("baidu", "百度SDK");
        adSrcMap.put("adx", "美数api");
        adSrcMap.put("jssp", "极光");
        adSrcMap.put("xunf", "讯飞");
        adSrcMap.put("toutiao", "头条");
        adSrcMap.put("tuia", "推啊");
        adSrcMap.put("vivo", "ov聚合");
        adSrcMap.put("jsdeeplink", "js无感唤醒");
        adSrcMap.put("ds618", "618口令");
        adSrcMap.put("hongshun", "弘顺");
        adSrcMap.put("sougou_search", "搜狗搜索");
        adSrcMap.put("sougou_add", "搜狗补效果");

        adPosMap.put("launch", "开屏");
        adPosMap.put("detail", "半屏图文");
        adPosMap.put("rec", "半屏页相关推荐");
        adPosMap.put("semantic", "搜索泛query");
        adPosMap.put("filter", "筛选页");
        adPosMap.put("filter2", "筛选结果页");
        adPosMap.put("homeFloat", "首页悬浮");
        adPosMap.put("focusBanner", "焦点图轮播");
        adPosMap.put("homeBanner", "首页中通1");
        adPosMap.put("homeBanner2", "首页中通2");
        adPosMap.put("topic", "专题列表");
        adPosMap.put("ranking", "排行榜");
        adPosMap.put("download", "缓存列表");
        adPosMap.put("ucenter1", "个人中心1");
        adPosMap.put("ucenter2", "个人中心2");
        adPosMap.put("exit", "退出");
        adPosMap.put("PreRolls", "前贴片");
        adPosMap.put("zybanner", "综艺频道通栏");
        adPosMap.put("dybanner", "电影频道通栏");
        adPosMap.put("dsjbanner", "电视剧频道通栏");
        adPosMap.put("dmbanner", "动漫频道通栏");
        adPosMap.put("pause", "暂停");
        adPosMap.put("screen", "插屏");
        adPosMap.put("read", "阅读器");
        adPosMap.put("LocalPreRolls", "本地前贴");
        adPosMap.put("search", "搜索");
        adPosMap.put("PatchVideo", "贴片激励视频");
        adPosMap.put("in_launch", " app内开屏");


        osMap.put("0", "安卓");
        osMap.put("1", "iphone");
        osMap.put("2", "ipad");
        osMap.put("100", "ios");


        hoursMap.put("0", "0-1点");
        hoursMap.put("1", "1-2点");
        hoursMap.put("2", "2-3点");
        hoursMap.put("3", "3-4点");
        hoursMap.put("4", "4-5点");
        hoursMap.put("5", "5-6点");
        hoursMap.put("6", "6-7点");
        hoursMap.put("7", "7-8点");
        hoursMap.put("8", "8-9点");
        hoursMap.put("9", "9-10点");
        hoursMap.put("10", "10-11点");
        hoursMap.put("11", "11-12点");
        hoursMap.put("12", "12-13点");
        hoursMap.put("13", "13-14点");
        hoursMap.put("14", "14-15点");
        hoursMap.put("15", "15-16点");
        hoursMap.put("16", "16-17点");
        hoursMap.put("17", "17-18点");
        hoursMap.put("18", "18-19点");
        hoursMap.put("19", "19-20点");
        hoursMap.put("20", "20-21点");
        hoursMap.put("21", "21-22点");
        hoursMap.put("22", "22-23点");
        hoursMap.put("23", "23-00点");

        videoTypeMap.put("0", "PP视频");
        videoTypeMap.put("1", "电视剧");
        videoTypeMap.put("2", "电影");
        videoTypeMap.put("3", "动漫");
        videoTypeMap.put("4", "综艺");
        videoTypeMap.put("6", "少儿");
        videoTypeMap.put("7", "游戏");
        videoTypeMap.put("16", "纪录片");


    }


    public static HashMap<String, String> getPageNames() {
        HashMap<String, String> map = new HashMap<>();
        map.put("home_index_page", "首页-推荐");
        map.put("home_index_episode_page", "首页-电视剧");
        map.put("home_index_movie_page", "首页-电影");
        map.put("home_index_variety_page", "首页-综艺");
        map.put("home_index_cartoon_page", "首页-动漫");
        map.put("topic", "专题列表页");
        map.put("topic_detail", "专题详情页");
        map.put("ranking", "排行榜首页");
        map.put("ranking_detail", "排行榜详情页");
        map.put("user", "我的");
        map.put("user_login", "我的-登录页面");
        map.put("user_login_wechat", "我的-微信登录页");
        map.put("user_login_weibo", "我的-微博登录页");
        map.put("user_login_qq", "我的-qq登录页");
        map.put("user_history", "我的-观看历史");
        map.put("user_download", "我的-离线缓存");
        map.put("user_download_multi", "我的-多剧集缓存页");
        map.put("user_downloading", "我的-正在缓存");
        map.put("user_collect", "我的收藏");
        map.put("user_playlist", "我的播单");
        map.put("search", "搜索");
        map.put("search_result", "搜索结果页");
        map.put("search_noresult", "搜索无结果页");
        map.put("search_result_episode", "搜索结果页-电视剧");
        map.put("search_result_movie", "搜索结果页-电影");
        map.put("search_result_cartoon", "搜索结果页-卡通");
        map.put("page_index_episode", "首页-电视剧");
        map.put("search_result_variety", "搜索结果页-综艺");
        map.put("page_index_variety", "首页-综艺");
        map.put("page_index", "首页");
        map.put("bind_phone_number_page", "手机号绑定页面");
        map.put("page_index_film", "首页-电影");
        map.put("search_result_documentary", "搜索结果页-纪录片");
        map.put("page_index_cartoon", "首页-动漫");
        map.put("page_index_hanjudaquan", "首页-韩剧推荐");
        map.put("channel", "筛选页");
        map.put("channel_detail", "筛选详情页");
        map.put("halfscreen", "半屏页");
        map.put("fullscreen", "全屏页");
        map.put("html", "H5页面");
        map.put("clips_feed", "看点页面");
        map.put("halfscreen_sv", "单视频半屏页");
        return map;
    }

    public static String[] getToUserEmail(String[] name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("姜姿含", "jiangzihan@shisutong.com.cn");
        map.put("张子彦", "zhangziyan@shisutong.com.cn");
        map.put("唐正", "tangzheng@shisutong.com.cn");
        map.put("林芳晨", "linfangchen@taishimei.com");
        map.put("高文娜", "gaowenna@shisutong.com.cn");
        map.put("李明", "liming@shisutong.com.cn");
        map.put("彭婷", "pengting@shisutong.com.cn");
        map.put("陈亮", "chenliang@shisutong.com.cn");
        map.put("孙砚", "sunyan@shisutong.com.cn");
        map.put("姜涛", "jiangtao@shisutong.com.cn");
        map.put("李晓晓", "lixiaoxiao@shisutong.com.cn");
        map.put("高守帅", "gaoshoushuai@shisutong.com.cn");
        map.put("郝亚杰", "haoyajie@shisutong.com.cn");
        map.put("xx", "yudewen@shisutong.com.cn");
        map.put("柯华仔", "kehuazai@nowyingshi.com");
        map.put("刘卓", "liuzhuo@shisutong.com.cn");
        map.put("杨勇", "yangyong@shisutong.com.cn");
        map.put("邓婉丽", "dengwanli@shisutong.com.cn");
        map.put("刘利珍", "liulizhen@shisutong.com.cn");

        ArrayList<String> list = new ArrayList<>();

        for (String s : name) {
            list.add(map.get(s));
        }
        String[] emails = list.toArray(new String[list.size()]);
        return emails;
    }

    public static HashMap<String, String> getStates() {
        HashMap<String, String> map = new HashMap<>();
        map.put("5", "(state=5) Android 服务端流播放失败(共计%s条)");
        map.put("15", "(state=15) Android 服务端返回空的流播放地址(共计%s条)");
        map.put("17", "(state=17) Android 服务端反盗链拒绝返回流播放地址(共计%s条)");
        map.put("21", "(state=21) Android 服务端JIEXI请求失败-播放（适用于云盘与305播放(共计%s条)");
        map.put("24", "(state=24) Android 服务端截流播放请求失败(共计%s条)");
        map.put("26", "(state=26) Android 客户端网络错误导致截流播放失败(共计%s条)");
        map.put("28", "(state=28) Android 客户端JS解析失败，服务端截流播放失败(共计%s条)");
        map.put("32", "(state=32) Android 客户端JS解析成功播放失败，未重试服务端流(共计%s条)");
        return map;
    }

    public static HashMap<String, String> getPlayColumns() {
        HashMap<String, String> map = new HashMap<>();
        map.put("subsrc", "播放来源");
        map.put("play_amount", "播放总数");
        map.put("play_success", "播放成功数");
        map.put("success_play_rate", "播放成功率");
        map.put("success_play_dod", "日环比");
        map.put("play_fail", "播放失败数");
        map.put("fail_play_rate", "播放失败率");
        map.put("fail_play_dod", "日环比");
        map.put("play_no_address", "播放无流地址数");
        map.put("no_address_play_rate", "播放无流地址率");
        map.put("no_address_play_dod", "日环比");
        map.put("play_drop_out", "播放前退出数");
        map.put("drop_out_play_rate", "播放前退出率");
        map.put("drop_out_play_dod", "日环比");
        return map;
    }

    public static HashMap<String, String> getCacheColumns() {
        HashMap<String, String> map = new HashMap<>();
        map.put("subsrc", "缓存来源");
        map.put("cache_amount", "缓存总数");
        map.put("cache_success", "缓存成功数");
        map.put("success_cache_rate", "缓存成功率");
        map.put("success_cache_dod", "日环比");
        map.put("cache_fail", "缓存失败数");
        map.put("fail_cache_rate", "缓存失败率");
        map.put("fail_cache_dod", "日环比");
        map.put("cache_no_address", "缓存无流地址数");
        map.put("no_address_cache_rate", "缓存无流地址率");
        map.put("no_address_cache_dod", "日环比");
        return map;
    }

    public static HashMap<Integer, String> getVideoTypeNames() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "PP视频");
        map.put(1, "电视剧");
        map.put(2, "电影");
        map.put(3, "动漫");
        map.put(4, "综艺");
        map.put(6, "少儿");
        map.put(7, "游戏");
        map.put(16, "纪录片");
        return map;
    }

    public static HashMap<String, String> getTmeidNames() {
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "0");
        map.put("focus", "焦点图");
        map.put("drawer", "抽屉");
        map.put("guide", "快速导航");
        return map;
    }

    public static HashMap<String, String> getPids() {
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "今日影视");
        map.put("1", "韩剧大全");
        map.put("2", "影视大全");
        map.put("3", "芝麻影视大全");
        return map;
    }

    public static HashMap<String, String> getHook() {
        HashMap<String, String> map = new HashMap<>();
        map.put("dingding", "https://oapi.dingtalk.com/robot/send?access_token=9a027f4293c61f636fd788250fef486370a3a42c59b798fee7d8664a9cbf0467");
        map.put("ad_ding", "https://oapi.dingtalk.com/robot/send?access_token=a58026066e91c041217f5cb0baf22b2c8739a1d1a423a5820f9e0bd235c425f2");
        map.put("zk_ad_ding", "https://oapi.dingtalk.com/robot/send?access_token=d2a4afd2a1bea8f7f2395cde2b1a9026ec3f6e23523af0d0677f6fdcb501a3d3");
        map.put("ad_rate_ding", "https://oapi.dingtalk.com/robot/send?access_token=5fda601859a8df4db23c1043b6dcf5862515df0b068cb111d8a0b6cd75df8dd2");
        map.put("cache_quality", "https://oapi.dingtalk.com/robot/send?access_token=2ff628c8aed9b0826feaf78eb52b539cd6daf8ce6ac737b8123e1038248780de");
//        map.put("black_list_ding","https://oapi.dingtalk.com/robot/send?access_token=c581349dd2dbb1305107d9714b5a696ad701794cf9523040f9e6e954fff2d62d");
//        map.put("ad_success_ding","https://oapi.dingtalk.com/robot/send?access_token=9b95743d0abe573617ed6438d0b481f0416d5efcfcb38d2429204f08ef8c0470");
        //监控数据带宽使用情况
        map.put("BandWidthUsage", "https://oapi.dingtalk.com/robot/send?access_token=a2e7181d3108519c9230b80334974777123603524873f080f6713090331dbd1e");
        //广告运行时监控);定量广告启用停用);渠道广告停用启用
        map.put("ad_running_status", "https://oapi.dingtalk.com/robot/send?access_token=fe7fe2d0bc96200bf05cab445b04a151808446484a42d03ad36b4144ae55b29e");
        map.put("ad_success_ding", "https://oapi.dingtalk.com/robot/send?access_token=ea124e13fb47122c64328cf42691193c4e6a337c7f0ecdd0ba6958eadd2ca244");
        map.put("ad_homeBanner", "https://oapi.dingtalk.com/robot/send?access_token=606f980bcf0f1f7c7c6ed2718a29fab82c40682a343d67f8f8f99b36689eba16");
        /**
         *以下是新申请的通知
         */
        //广告自动开启通知群
        map.put("ad_channel_is_alive", "https://oapi.dingtalk.com/robot/send?access_token=fcf4286ac7f25cb8b3bbe3a1e69a94d17405c19454075025dbb90641370f22e7");
        //拉黑用户列表群
        map.put("black_list_ding", "https://oapi.dingtalk.com/robot/send?access_token=da206602493b9cbea9a05c6e22f4d24546d752f39f61deb8e3338bddc0a5cdbf");
        //临时sdk广告监控
        map.put("tmp_sdk_ad_moniter", "https://oapi.dingtalk.com/robot/send?access_token=48d2defd2c8a987dc0719b70d987c13f88e7a4312d28a3d5e9dfcd38a98d207c");


        //测试
        map.put("test", "https://oapi.dingtalk.com/robot/send?access_token=4b283e2659582a575ebf37c614490b924344b1d6a50901cc39e0f3481bb4a677");

        return map;
    }

    public static HashMap<String, String> getAdPos() {
        HashMap<String, String> map = new HashMap<>();
        map.put("launch", "开屏");
        map.put("detail", "半屏详情页");
        map.put("rec", "半屏页相关推荐");
        map.put("semantic", "搜索泛query");
        map.put("filter", "筛选页");
        map.put("filter2", "筛选结果页");
        map.put("homeFloat", "首页悬浮");
        map.put("focusBanner", "焦点图轮播");
        map.put("homeBanner", "首页中通1");
        map.put("homeBanner2", "首页中通2");
        map.put("topic", "专题列表");
        map.put("ranking", "排行榜");
        map.put("download", "缓存列表");
        map.put("ucenter1", "个人中心1");
        map.put("ucenter2", "个人中心2");
        map.put("exit", "退出");
        map.put("PreRolls", "前贴片");
        map.put("zybanner", "综艺频道通栏");
        map.put("dybanner", "电影频道通栏");
        map.put("dsjbanner", "电视剧频道通栏");
        map.put("dmbanner", "动漫频道通栏");
        map.put("pause", "暂停");
        map.put("screen", "插屏");
        map.put("read", "阅读器");
        map.put("LocalPreRolls", "本地前贴");
        return map;
    }


}
