<template>
  <!-- 전체 wrap -->
  <div id="postBox">

    <!-- 사진 영역 -->
    <div id="photoBox">
      <!-- 통신 -->
      <div>
        <img :src="`${onepostlist.img}`" alt="" class="photo">
      <div v-show="pdata==cdata || adminNum == 4">
        <v-btn large color="#FFFFFF" @click="upt" style="margin: 15px 15px 15px 15px">수정</v-btn>
        <v-btn large color="#FFFFFF" @click="del" style="margin: 15px 15px 15px 15px">삭제</v-btn>
      </div>
      </div>

    </div>

    <!-- 제목 내용 등등 -->
    <div id="textBox">

      <!-- 게시자 정보 -->
      <div id="postUser">
        
        <!-- 통신 -->
        <!-- <img class="postUserPs" :src="`http://70.12.246.112:9090/img/${onesameuserlist.userprofile}`"/> -->

        <!-- test -->
        <router-link :to="`/same_user_detail/${onesameuserlist.unum}`">
          <img class="postUserPs" :src="`${onesameuserlist.userprofile}`" width="65px" height="65px">
        </router-link>
        <!-- </div> -->

        <!-- 게시자 닉네임 -->
        <!-- <router-link :to="`/same_user_detail/${onesameuserlist.unum}`"> -->
          <div id="postUserName">
            <!-- <a href="/same_user_detail/${onesameuserlist.unum}"> -->
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{onesameuserlist.nickname}}
            <!-- </a> -->
          </div>
        <!-- </router-link> -->
      </div>

      <!-- 컨텐츠(제목, 내용, 해시태그) -->
      <div id="postConBox">
        <div id="postTitle">{{onepostlist.title}}</div>
        <div id="postCon">{{onepostlist.contents}}</div>
        <div id="hashtag">{{onepostlist.tags}}</div>
      </div> 

      <!-- 좋아요 댓글 공유 버튼 -->
      <div id="postButtons">
        <div id="likeButton">
          <!-- 좋아요 버튼 눌림 -->
          <div class="likeButton" v-if="like_or_unlike == true"  @click="unlikeFeed(onepostlist.pnum)">
            <img src="../../assets/postdetail/purpleheart2.png" alt="" style="cursor:pointer"> {{ onepostlist.likes }}  개
          </div>

              <!-- 좋아요 버튼 안눌림 -->
          <div class="unlikeButton" v-else  @click="likeFeed(onepostlist.pnum)" >
            <img src="../../assets/postdetail/heartPurple.png" alt="" style="cursor:pointer"> {{ onepostlist.likes }}  개
          </div>
        </div>
        <div id="commentButton">
        
          <img src="../../assets/postdetail/commentPurple.png" alt="" style="cursor:pointer">
          <!-- <img src="../../assets/postdetail/purplecomment.png" alt="" style="cursor:pointer"> -->
          {{onepostlist.comments}} 개
        </div>
        <!-- <div id="shareButton">
          <img src="../../assets/postdetail/sharePurple.png" alt="" style="cursor:pointer">
          <img src="../../assets/postdetail/purpleshare.png" alt="" style="cursor:pointer">
        </div> -->
      </div>

      <!-- 좋아요 수 -->
      <!-- <div id="likeNum"></div> -->



      <!-- 댓글들 -->
      <!-- <v-form id="manyComments" @submit="move"> -->
        <div class="comments" v-for="(postcomment,index) in postcomments" :key="index" style="text-align:center;">
          <div class="comment_user_profile" v-if="postcomment.userprofile != null">
            <router-link :to="`/same_user_detail/${postcomment.usernum}`">
              <img :src="`${postcomment.userprofile}`" class="commentImage">        
            </router-link>
          </div>
          <div class="comment_user_profile" v-else>
            <router-link :to="`/same_user_detail/${postcomment.usernum}`">
              <img :src="`http://i02a202.p.ssafy.io:8080/petple/static/img/default.png`" class="commentImage">        
            </router-link>
          </div>
          <div class="comment_user_nickname">
            {{postcomment.nickname}}   
          </div>
          <div class="comment_user_contents">
            {{postcomment.contents}}
          </div>

          <v-col class="text-center" id="deleteButton" cols="12" sm="4">
            <div class="my-2">
              <v-btn large color="#FF0000" @click="deleteComment(postcomment.no, postcomment.usernum)">delete</v-btn>
            </div>
          </v-col>

        </div>
      <!-- </v-form> -->
      


      <!-- 댓글 달기 -->
      <!-- <form id="commentWrite" @submit="move"> -->
        <!-- <form id="commentWrite"> -->
        <!-- <label class="myComment">댓글</label> -->
        <textarea class="myCommentWrite" v-model="comments" placeholder="댓글 달기.."></textarea>
        <button class="commentSend" @click="postComment">
          <div>
            <img class="commentSendButton" src="../../assets/postdetail/pawPurple.png" alt="" width="60px"
              style="cursor:pointer">
            <img class="commentSendButton" src="../../assets/postdetail/pawPurple2.png" alt="" width="60px"
              style="cursor:pointer">
          </div>
          <p class="arrowBox">댓글 게시</p>
        </button>
      <!-- </form> -->



    </div>

  </div>
</template>


<script>
  export default {
    data() {
      return {
        cdata:'',
        pdata:'',
        comments:'',
        // comment : {},
        hashtags: {},
        loading: false,
        adminNum: sessionStorage.getItem('user_num'),
      }
    },
    methods: {
      async postComment() {
        if(this.comments==''){
          alert('댓글을 입력해 주세요')
        } else {
          if(!this.loading){
            this.loading = true;
            let data = await {
              'contents' : this.comments,
              'postnum' : this.$store.state.public_pnum,
              'usernum' : sessionStorage.getItem('user_num')
            }
            // console.log(data);
            await this.$store.dispatch('postComment', data);
            await this.$store.dispatch('commentUp', this.$store.state.public_pnum); // 댓글 카운트 내림
            await this.$store.dispatch('detailPostFeed', this.$store.state.public_pnum); // 포스트 정보 한 번 더 받아오기
            await this.$store.dispatch('getPostComments', this.$store.state.public_pnum); // 댓글 받아오기
            this.comments = '';
            this.loading = false;
          }
        }
      },
      async likeCheck(param_pnum){
        let user_num = sessionStorage.getItem('user_num');
        let data = {
          'unum' : user_num,
          'pnum' : param_pnum,
        }
        await this.$store.dispatch('likeConfirm', data)
        return this.$store.state.check_feedlike;
      },
      async deleteComment(param,param_un) {
        if(sessionStorage.getItem('user_num') == param_un){
          if(!this.loading){
            this.loading = true;
            await this.$store.dispatch('deleteComment', param, this.$store.state.public_pnum); // 댓글 삭제
            await this.$store.dispatch('commentDown', this.$store.state.public_pnum); // 댓글 카운트 내림
            await this.$store.dispatch('detailPostFeed', this.$store.state.public_pnum); // 포스트 정보 한 번 더 받아오기
            await this.$store.dispatch('getPostComments', this.$store.state.public_pnum); // 댓글 리스트 불러오기
          //  alert('삭제 완료!')
            this.loading = false;
          }
        }
        else
          alert('권한이 없습니다.')
        // console.log('actions갔다 옴');
        // this.$router.push({name: 'Postdetail', params:{"pnum": this.$store.state.public_pnum}});
      },
      async unlikeFeed(param_pnum) {
        if(!this.loading){
          let user_num = sessionStorage.getItem('user_num');
          this.loading = true;
          let data = {
            'unum' : user_num,
            'pnum' : param_pnum,
          }
          await this.$store.dispatch('unlikeFeed', data); // like 테이블 안 좋아요 체크
          await this.$store.dispatch('likeDown', param_pnum); // post테이블 좋아요 --
          await this.$store.dispatch('likeConfirm', data); // 좋아요 체크!
          await this.$store.dispatch('detailPostFeed', param_pnum); // 게시물 상세 리스트 호출
          this.loading = false;
        }
      },
      async likeFeed(param_pnum) {
        if(!this.loading){
          let user_num = sessionStorage.getItem('user_num');
          this.loading = true;
          let data = await {
            'unum' : user_num,
            'pnum' : param_pnum,
          }
            await this.$store.dispatch('likeFeed', data)
            await this.$store.dispatch('likeUp', param_pnum)
            await this.$store.dispatch('detailPostFeed', param_pnum);
            await this.$store.dispatch('likeConfirm', data)
            this.loading = false;
        }
      },

      // async del() {
      //    await this.$store.dispatch('delPet',petnum);
      //    this.$router.push({name:'Sameuserdetail'});
      // }
      async del() {
         await this.$store.dispatch('delPost',this.$store.state.public_pnum);
         //alert('메인으로 이동')
         this.$router.push({name:'Mainpage'});
      },
      async upt() {
         await this.$store.dispatch('uptPost',this.$store.state.public_pnum);
       //  alert('수정페이지으로 이동'+this.$store.state.public_pnum);                
         this.$router.push({name:'Postupdate',params:{"postnum":this.$store.state.public_pnum}});
      }
    },
    
    // },
    computed: {
      onepostlist() {
        // console.log('글쓴이의 unum 받아오기');
        // console.log(this.$store.state.onepostlist.unum);
        // this.postUser = this.$store.state.onepostlist.unum;
        // console.log('로그인 한 unum');
        // console.log(sessionStorage.getItem('user_num'));
        // console.log("글 정보"+JSON.stringify(this.$store.state.onepostlist))
        this.loginUser = sessionStorage.getItem('user_num');
        return this.$store.state.onepostlist;
      },
      onesameuserlist() {
        return this.$store.state.onesameuserlist;
      },
      postcomments(){
        
        return this.$store.state.commentlist;
      },
      changeDate() {
         var firstday = this.$store.state.petlist.firstday;
            //    console.log(firstday.substring(0,10));
               return firstday.substring(0,10);
      },
      postDetailHashTags() {
        let h_tags = this.$store.getters.postDetailHashTags.split(' ');
        // this.hashtags = 
        
      },
      like_or_unlike() {
        return this.$store.state.check_feedlike;
      }
    },
    async created() {
      
        let postNum = await String(this.$route.params.pnum)
        // console.log(postNum);
        
        this.$store.state.public_pnum = postNum;
        // console.log(postNum);

        let user_num = sessionStorage.getItem('user_num');
        let data = {
          'unum' : user_num,
          'pnum' : postNum,
        }
      await this.$store.dispatch('likeConfirm', data)
      
      // const unum = sessionStorage.getItem('is')
      await this.$store.dispatch('detailPostFeed', postNum);
      // console.log('게시글 적은 유저번호'+this.$store.state.post_detail_unum);
      // console.log(this.$store.state.post_detail_unum);
      // console.log(this.$store.state.onepostlist);
      
      // console.log(this.$store.state.onepostlist.unum);
      
      // console.log('로그인 유저 번호'+sessionStorage.getItem('user_num'));
      this.cdata = sessionStorage.getItem('user_num');
      this.pdata = this.$store.state.post_detail_unum;
      // this.$store.dispatch('hitUpPost', postNum);
      await this.$store.dispatch('detailSameUser', this.$store.state.post_detail_unum);
      await this.$store.dispatch('getPostComments', this.$store.state.public_pnum); // 댓글 받아오기
    }
  };
</script>
<style scoped src='./postDetail.css'>
</style>