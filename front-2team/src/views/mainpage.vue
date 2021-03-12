<template>
    <div>
      <Navi/>
      <Content/>
      <Footer class="footer_component"></Footer>
      <!-- <Footer class="footer_component"></Footer> -->
    </div>
</template>

<script>

import Navi from '@/components/mainpage/navi.vue';
// import Tab2 from '@/components/mainpage/tab2.vue';
import Content from '@/components/mainpage/content.vue';
import Footer from '@/components/mainpage/footer.vue';
// import Footer from '@/components/mainpage/footer.vue'

export default {
  components: {
    Content,
    // Tab2,
    Navi,
    Footer,
    // Footer,
  },
  created() {
    
  }
};
</script>
<style scoped>
  .footer_component {
    visibility: hidden;
  }

  @media screen and (max-width: 375px) {  
    .footer_component {
      visibility: visible;
      
    }
  }
</style>
