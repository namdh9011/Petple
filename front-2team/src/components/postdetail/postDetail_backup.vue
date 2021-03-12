<template>
  <div id="wrap">
    <div class="post_box">
      <section class="post photo_box">
        <img class="post_photo" src="../../assets/postdetail/chiva.png" alt="">
      </section>

      <section class="post text_box">
        <header class="profile">
          <div class="profile_photo"><img class="profile_photo_circle" src="../../assets/postdetail/seolyuGitHub.png"
              alt=""></div>
                    <div class="profile_name">사용자 이름</div>
          <!-- 유저의 사진과 이름 넣어야 함 -->
        </header>

        <div class="posting">
          <section class="post_content">{{onepostlist.contents}}</section>
          <section class="hashtags">#강아지 #산책</section>
          <!-- 태그도 추가되면 넣어야 함 -->
        </div>

        <div class="like_comment">
          <div class="like">
            <img class="like_button" src="../../assets/postdetail/pawTrans.png" alt="">
          </div>
          <div class="comment">
            <img class="comment_button" src="../../assets/postdetail/comment.png" alt="">
            <!-- <div class="commentnum">댓글 3개</div> -->
          </div>
          <div class="likenum">좋아요 {{onepostlist.likes}}개   댓글 {{onepostlist.comments}}개</div>
        </div>

        <div class="post_time">{{onepostlist.date}}</div>

        <div class="comment_box">
          
          <div class="user_info">
            <div class="user_photos">댓글러프사</div>
            <div class="user_names">댓글러이름</div>
          </div>
          <div class="user_comments">댓글내용</div>
        </div>

        <div class="comment_write_box">
          <div class="comment_write">
            <div class="comment_writing">댓글 달기</div>
          </div>
          <i class="far fa-smile fa-2x"></i>
        </div>
      </section>
    </div>
  </div>
</template>


<script>
export default {
  computed: {
    onepostlist() {
      // console.log('객체받아오기');
      
      // console.log(this.$store.state.onepostlist);
      return this.$store.state.onepostlist;
    }
  },
  created() {
    const postNum = String(this.$route.params.pnum)
    
    this.$store.dispatch('detailPostFeed', postNum);
  }
};
</script>
<style scoped src='./postDetail.css'>
</style>
