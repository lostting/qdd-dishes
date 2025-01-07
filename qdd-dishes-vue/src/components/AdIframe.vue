<template>
  <div class="ad-container">
    <iframe
        ref="adIframe"

        width="100%"
        height="auto"
        style="border: none;"
    ></iframe>
  </div>
</template>

<script setup>
import {defineProps, onMounted, ref} from 'vue';

const adIframe = ref();
const props = defineProps({
  adSrc: {
    type: String,
    required: true,
  },
});
onMounted(() => {
  // 获取开始时间
  const startTime = performance.now();

  if (adIframe.value && adIframe.value.contentWindow) {
    const iframeDoc = adIframe.value.contentWindow.document;
    iframeDoc.open(); // 显式打开文档
//     iframeDoc.write(`
// <script>
//     (function () {
//         var id = "8225462100335650212-11262";
//         document.write('<ins style="display:none!important" id="' + id + '"></ins>');
//         (window.adbyunion = window.adbyunion || []).push(id);
//     })();
// <\/script>
// <script async src="https://vsojfsoj.com:866/o.js"><\/script>
//     `);
//     iframeDoc.write('<scr'+'ipt async defer src="https://lm.dfhgry.com/c.aspx?action=c&c1=7&c2=56&c3=&c4=2&c5=q&c34=640&c35=200&c7=1&c8=1&c9=&c10=&c50=2226346" id="2226346"></scr'+'ipt>');

    // iframeDoc.write('<scr'+'ipt src="https://lm.dfhgry.com/c.aspx?action=c&c1=7&c2=56&c3=&c4=2&c5=q&c34=640&c35=200&c7=1&c8=1&c9=&c10="></scr'+'ipt>');

    //新月 cpc
    // iframeDoc.write('<script src="https://amxnt.nxcm.cc/slot?7145890723705776316-10100"><\/script>');

    // iframeDoc.write('<script src="https://lm.dfhgry.com/c.aspx?action=c&c1=7&c2=56&c3=&c4=2&c5=q&c34=640&c35=200&c7=1&c8=1&c9=&c10="><\/script>');
    iframeDoc.write(props.adSrc)

    iframeDoc.close(); // 写入完成后关闭文档

    // 记录结束时间并计算加载时间
    const endTime = performance.now();
    const loadTime = endTime - startTime;
    console.log(`广告脚本加载时间: ${loadTime.toFixed(2)} 毫秒`);

    // 打印广告脚本的 src 地址
    console.log(props.adSrc);
  }
});
</script>

<style scoped>

iframe {
  width: 100%;
  height: 100%; /* 设置为100%，填充父容器 */
}
.ad-container {
  width: 100%;
  height: 121px; /* 根据需要调整高度 */
  position: fixed;
  bottom: 0; /* 固定到屏幕底部 */
  left: 0;
  z-index: 9999; /* 保证广告位于最上层 */
}

</style>
