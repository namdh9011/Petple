<template>
    <div>
        <Navi/>
        <Userdetail/>
        <Footer class="footer_component"></Footer>
    </div>
</template>

<script>
import Navi from '@/components/mainpage/navi.vue';
import Userdetail from '@/components/userdetail/userDetail.vue';
import Footer from '@/components/mainpage/footer.vue';

export default {
  components: {
    Navi,
    Userdetail,
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
