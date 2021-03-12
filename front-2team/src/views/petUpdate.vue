<template>
    <div>
        <Navi/>
        <PetUpdate/>
        <!-- <ButtonComponent text="승인" :isBackground="true"/>
        <ButtonComponent text="거절" :isBackground="false"/> -->
        <Footer class="footer_component"></Footer>
    </div>
</template>

<script>
import Navi from '@/components/mainpage/navi.vue';
import PetUpdate from '@/components/petdetail/petDetailUpdate.vue';
// import ButtonComponent from '../components/pet_detail/Button.vue';
import Footer from '@/components/mainpage/footer.vue';

export default {
  components: {
    Navi,
    PetUpdate,
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
