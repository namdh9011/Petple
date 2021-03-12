<template>
  <div>
      <Navi></Navi>
      <div class="user_detail">
          <div></div>
          <div>
              <UserDetail></UserDetail>
          </div>
          <div></div>
      </div>
      <Footer class="footer_component"></Footer>
  </div>
</template>

<script>

import Navi from '@/components/mainpage/navi.vue';
import UserDetail from '@/components/userdetail/sameUserDetail.vue';
import Footer from '@/components/mainpage/footer.vue';

export default {
    components:{
        Navi,
        UserDetail,
        Footer,
    }
}
</script>

<style scoped>
    .user_detail{
        display: grid;
        grid-template-columns: 1fr 11fr 1fr;
    }
</style>
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
