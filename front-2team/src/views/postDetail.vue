<template>
    <div>
        <Navi/>
        <Postdetail/>
        <Footer class="footer_component"></Footer>
    </div>
</template>

<script>
import Navi from '@/components/mainpage/navi.vue';
import Postdetail from '@/components/postdetail/postDetail.vue';
import Footer from '@/components/mainpage/footer.vue';

export default {
  components: {
    Navi,
    Postdetail,
    Footer,
  },
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
