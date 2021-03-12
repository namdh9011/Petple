<template>
  <div>
    <div class="card-link" v-for="(postlist) in postlists" :key="postlist.pnum" >
      <!-- if 에러 상관없음 -->
      <div class="blog-card">
          <router-link :to="`/postdetail/${postlist.pnum}`">
            <img class="post-image" :src="`${postlist.img}`"/>   
          </router-link>
          <div class="article-details">
            <h4 class="post-category">{{ postlist.privacy }}</h4>
            <h3 class="post-title">{{ postlist.title }}</h3>
            <p class="post-description">{{ postlist.contents }}</p>

            <div class="post-author">
              <div class="likes_and_comments">
                <img src="../../assets/postdetail/purpleheart2.png" class="likes-image" alt="" style="cursor:pointer"> {{ postlist.likes }}  개
                <img src="../../assets/postdetail/purplecomment.png" class="comments-image" alt="" style="cursor:pointer"> <span class="post-comments-num"> {{postlist.comments}} </span>
              </div>
            </div>
          </div>
      </div> <!-- class="blog-card" -->
    </div> <!-- class="card-link" -->
    <!-- <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading> -->
  </div>
</template>

<script type="text/x-template" id="blog-card">
// import InfiniteLoading from 'vue-infinite-loading'

export default {
    // components:{
    //     InfiniteLoading,
    // },
  //   data () {
  //       return {
  //           limit:10,
  //           likes:1,
  //           count:0,
  //       }
  // },
   computed: {
    postlists() {
      return this.$store.state.main_follow_list;
    },
    everypostlist() {
      return this.$store.getters.EveryPostList;
    },
    // like_or_unlike() {
    //   return this.$store.state.check_feedlike;
    // },
    // whatfeed() {
    //   return this.$store.state.what_feedlike;
    // },
  },
  methods: {
    // followuser() {
    //   let followinglists = this.$store.getters.followinglist;
    //   for(var i=0; i<followinglists.length;i++){
    //     if(followinglists[i] == )
    //   }
    // },
    // infiniteHandler($state) {
    //   // console.log('action 가기 전');
      
    //   this.$store.dispatch('mainFeedTen', this.limit);

    //   // console.log('action 간 후');
      
    //   setTimeout(() => {
    //     if(this.$store.state.tenpostlist.data.length){
    //       // console.log(this.count);
    //         if(this.count != this.$store.state.tenpostlist.data.length){
    //            this.count = this.$store.state.tenpostlist.data.length;
    //           $state.loaded();
    //           this.limit += 10;
    //           // console.log('sdgsdhsdhsdh'+this.limit);  
    //         } else {
    //           $state.complete();
    //         }
    //     } else {
    //         $state.complete();
    //     }
    //     }, 1000)
    // },
    async likeCheck(param_pnum){
      let user_num = sessionStorage.getItem('user_num');
      let data = {
        'unum' : user_num,
        'pnum' : param_pnum,
      }
      await this.$store.dispatch('likeConfirm', data)
      // console.log('likeCheck....   '+this.$store.state.check_feedlike);
      // console.log(this.$store.state.check_feedlike  )
      
      return this.$store.state.check_feedlike;
    },
    async unlikeFeed(param_pnum) {
      let user_num = sessionStorage.getItem('user_num');
      let data = {
        'unum' : user_num,
        'pnum' : param_pnum,
      }
      await this.$store.dispatch('unlikeFeed', data)
      await this.$store.dispatch('likeDown', param_pnum)
      await this.$store.dispatch('detailPostFeed', param_pnum);
      await this.$store.dispatch('likeConfirm', data)
    },
    async likeFeed(param_pnum) {
      let user_num = sessionStorage.getItem('user_num');
      let data = await {
        'unum' : user_num,
        'pnum' : param_pnum,
      }
      await this.$store.dispatch('likeFeed', data)
      await this.$store.dispatch('likeUp', param_pnum)
      await this.$store.dispatch('detailPostFeed', param_pnum);
      await this.$store.dispatch('likeConfirm', data)
    }
  },
  created() {
    this.$store.dispatch('mainFollowList', sessionStorage.getItem('user_num'));
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Roboto:400,700");
* {
  box-sizing: border-box;
}
*::before, *::after {
  box-sizing: border-box;
}

body {
  display: flex;
  font-family: 'Roboto', sans-serif;
  font-weight: 400;
  color: #777;
  background: #eedfcc;
  font-size: 0.9375rem;
  min-height: 100vh;
  margin: 0;
  line-height: 1.6;
  align-items: center;
  justify-content: center;
  text-rendering: optimizeLegibility;
}

#container {
  width: 30rem;
  height: 13.625rem;
}

.blog-card {
  margin-top: 5%;
  height: auto;
  display: flex;
  flex-direction: row;
  background: #fff;
  box-shadow: 0 0.1875rem 1.5rem rgba(0, 0, 0, 0.2);
  border-radius: 0.375rem;
  overflow: hidden;
}

.card-link {
  position: relative;
  display: block;
  color: inherit;
  text-decoration: none;
}
/* .post-title:hover {
  transition: color 0.3s ease !important;
  color: #e04f62;
}

.post-image:hover {
  transition: opacity 0.3s ease;
  opacity: 0.9;
} */

.blog-card:hover .post-title{
  transition: color 0.3s ease !important;
  color: #e04f62;
  cursor: pointer;
}

.blog-card:hover .post-image{
  transition: opacity 0.3s ease;
  opacity: 0.9;
  cursor: pointer;
}

.un_like:hover , .like:hover {
  cursor: pointer;
}

.post-image {
  transition: opacity 0.3s ease;
  display: block;
  width: 200px;
  height: 200px;
  object-fit: cover;
}

.article-details {
  padding: 1.5rem;
}

.post-category {
  display: inline-block;
  text-transform: uppercase;
  font-size: 0.75rem;
  font-family: 'Poor Story', cursive;
  font-weight: 700;
  line-height: 1;
  letter-spacing: 0.0625rem;
  margin: 0 0 0.75rem 0;
  padding: 0 0 0.25rem 0;
  border-bottom: 0.125rem solid #ebebeb;
}

.post-title {
  transition: color 0.3s ease;
  font-size: 1.125rem;
  font-family: 'Poor Story', cursive;
  line-height: 1.4;
  color: #121212;
  font-weight: 700;
  margin: 0 0 0.5rem 0;
}

.post-author {
  font-size: 0.875rem;
  line-height: 1;
  margin: 1.125rem 0 0 0;
  padding: 1.125rem 0 0 0;
  border-top: 0.0625rem solid #ebebeb;
}

.comments-image, .post-comments-num {
  display: none;
}

@media (max-width: 40rem) {
  #container {
    width: 18rem;
    height: 27.25rem;
  }

  .blog-card {
    flex-wrap: wrap;
  }
}
@supports (display: grid) {
  body {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-gap: 0.625rem;
    grid-template-areas: ". main main ." ". main main .";
  }

  #container {
    grid-area: main;
    align-self: center;
    justify-self: center;
  }

  .post-image {
    height: 100%;
  }

  .blog-card {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-rows: 1fr;
  }

  @media (max-width: 40rem) {
    .blog-card {
      grid-template-columns: auto;
      grid-template-rows: 12rem 1fr;
    }
  }

  @media screen and (max-width: 375px) {
    .card-link {
        width: 375px;
    }
    .blog-card .post-image {
      width: 375px;
      height: 300px;
    }
    .post-category, .post-title, .post-description {
      visibility: hidden;
    }

    .post-author {
      border-top: 0;
    }

    .comments-image, .post-comments-num {
      display: inline;
    }
    .likes-image{
      width: 20px;
      height: 20px;
      margin-left: 110px;
    }
    .comments-image {
      width: 20px;
      height: 20px;
      margin-left: 20px;
    }
}

}
</style>
