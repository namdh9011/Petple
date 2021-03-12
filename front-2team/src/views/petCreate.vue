<template>
    <div>
        <Navi/>
        <PetAdd/>
        <Footer class="footer_component"></Footer>
        <!-- <ButtonComponent text="승인" :isBackground="true"/>
        <ButtonComponent text="거절" :isBackground="false"/> -->
    </div>
</template>
/
<script>
//localhost:8080/PetAdd
import Navi from '@/components/mainpage/navi.vue';
import PetAdd from '@/components/petdetail/petInfo.vue';
import Footer from '@/components/mainpage/footer.vue';
// import ButtonComponent from '../components/pet_detail/Button.vue';

export default {
  components: {
    Navi,
    PetAdd,
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
