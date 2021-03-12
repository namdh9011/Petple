<template>
<!-- <div class="wrap"> -->
  <div class="ele2">
    <div class="vertical-menu">
      <div class="newsfeed" @click="newsFeed">최신 순 보기</div>
      <div class="interest" @click="followFeed">팔로우만 보기</div>
      <div class="interest" @click="uploadPage">게시물 업로드</div>
      <!-- <router-link :to="`/postupload`">
        <div class="letsUploadPost">게시물 업로드</div>
      </router-link> -->
      <a href="#">맨 위로</a>
    </div>
</div>
<!-- </div> -->
</template>

<script>
export default {
    // data () {
    //   return {
        
    //   }
    // },
    methods: {
       newsFeed() { // this.$store.state.what_feedlike 가 'all'이면 최신피드
        //  this.$store.state.what_feedlike = false;
        //  this.$store.commit('change_whatFeed', {what_feedlike: 'all'});
        //  console.log(this.$store.state.what_feedlike);
         this.$router.push({name: 'Mainpage'}).catch(err=>{});
       }, 
       followFeed() { // this.$store.state.what_feedlike 가 'friend'이면 팔로우 피드
        //  this.$store.commit('change_whatFeed', {what_feedlike: 'friend'});
        //  console.log(this.$store.state.what_feedlike);
        this.$router.push({name: 'Mainpage2'}).catch(err=>{});
       },
       uploadPage() {
         this.$router.push({name: 'Postupload'}).catch(err=>{});
       }
    },
    // created () {
    //     // window.addEventListener('scroll', this.scrollFunction);
    // },
    // detroyed () {
    //     // window.removeEventListener('scroll', this.scrollFunction);
    // }
}
</script>

<style scorped>

/* .wrap {
  position: absolute;
  width: 100%;
  height: 10000px;
} */

  .ele2 {
  /* display: inline-block; */
  position: sticky;
  top: 130px;
  /* background: blue; */
}

.vertical-menu {
  /* position: absolute; */
  width: 200px; 
  margin-left: 10%;
}

.vertical-menu div {
  background-color: #eee; /* Grey background color */
  color: black; /* Black text color */
  display: block; /* Make the links appear below each other */
  padding: 12px; /* Add some padding */
  text-decoration: none; /* Remove underline from links */
}

.vertical-menu div:hover {
  background-color: #bd93ce; /* Dark grey background on mouse-over */
  cursor: pointer;
}

.vertical-menu div.active {
  background-color: #bd93ce; /* Add a green color to the "active/current" link */
  color: white;
}

.vertical-menu a {
  background-color: #eee; /* Grey background color */
  color: black; /* Black text color */
  display: block; /* Make the links appear below each other */
  padding: 12px; /* Add some padding */
  text-decoration: none; /* Remove underline from links */
}

.vertical-menu a:hover {
  background-color: #bd93ce; /* Dark grey background on mouse-over */
  cursor: pointer;
}

.vertical-menu a.active {
  background-color: #bd93ce; /* Add a green color to the "active/current" link */
  color: white;
}
</style>