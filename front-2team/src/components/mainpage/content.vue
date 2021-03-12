<template>
    <!-- blank1, blank2 팀원과 상의없이 수정 금지  -->
    <div class="mainpage_container">
      <!-- <div class="blankfirstrow" style="background-color: rgb(255,255,222)"></div> -->
      <div class="inner_mainpage_container">
        <div class="blank1">
        </div>
        <!-- 3fr 6fr 4fr columns로 자르기  -->
        <div class="mainpage_feed">
          <div class="blank3 left_mainpage_feed"></div>
          <div class="mainpage_feed_spac">
            <blogcard></blogcard>
          </div>
          <!-- 여기에 최신피드 or 팔로워 피드 버튼 생성 
               유저의 팔로워등 컨텐츠 들어가야함  -->
          <div class="blank3 right_mainpage_feed">
            <div class="temp_left">
            </div>
            <div class="temp_right">
               <sidemenu></sidemenu>
            </div>
               <!-- {{sessionStorage.getItem('user_no')}} -->
               <!-- <a class="aaaa">{{this.$store.state.public_token}}</a> -->
          </div>
        </div>
        <div class="blank2">
        </div>
      </div><!-- class="inner_mainpage_container" -->
    </div><!-- class="mainpage_container" -->
</template>

<script>
import blogcard from '@/components/mainpage/blogCard.vue'
import sidemenu from '@/components/mainpage/sideMenu.vue'


export default {
  data () {
    return {
      interest: '',
    }
  },
  components:{
    sidemenu,
    blogcard,
    
  },
  async created() {
    // this.$store.dispatch('mainPostFeed');
    // this.$store.dispatch('mainPostFeed', this.limit);
    // console.log(this.$store.state.public_token);
    // let sss = this.$store.state.public_token
    // this.$session.set('user_nsghsdhsdhsdgo', sss);
    // console.log('유저number : '+sessionStorage.getItem('user_num')); // <---- 유저넘버 사용할 떄 sessionStorage에서 빼서 쓰기
    // var link = document.location.href; 
    // console.log(link);
    // var para = document.location.href.split("="); 
    // console.log(para[1]);
    var unum = sessionStorage.getItem('user_num');
    await this.$store.dispatch('interestCheck', unum)
    // console.log(this.$store.state.user_interest);
    
    if(this.$store.state.user_interest == null){
      alert('관심동물이 선택되지 않았습니다 선택해주세요!!!')
      await this.$router.push({name: 'Userdetail', params:{"unum": unum}});
    }
  }
};

</script>
<style scoped src='./content.css'>
</style>
