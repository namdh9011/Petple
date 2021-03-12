<template>
    <div class="container">
      <!-- <MainPageTab :isAction="true"/>
      <MainPageTab :isAction="true"/> -->
      <div>
        <div class="box" v-for="postlist in postlists.data" :key="postlist.privacy">
            <!-- <div class="inner_box" v-if="postlist.privacy === 'all' "> -->
                <h2>{{postlist.title}}</h2>
                <router-link v-bind:to="{name:'Petdetail'}">
                    <img src="../../images/mainpage/img4.jpg">
                </router-link>
                <h2></h2>
                <p>{{postlist.contents}}</p>
                <!-- <div class="date">{{postlist.date}}</div>
                <div class="likes">좋아요 {{postlist.likes}} 개</div>
                <div class="comments">댓글 {{postlist.comments}} 개</div> -->
            <!-- </div> -->

        </div>
      </div>
    </div>
</template>

<script>

import './content.css';
import MainPageTab from '../common/mainpageTab.vue';
// 링크 잡고 들어가야 함
export default {
  components: {
    MainPageTab,
  },
  created() {
    this.$store.dispatch('mainPostFeed');
  },
  computed: {
    postlists() {
      return this.$store.state.postlist;
    },
    everypostlist() {
      return this.$store.getters.EveryPostList;
    },
  },
};

</script>
