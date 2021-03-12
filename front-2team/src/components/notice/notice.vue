<template>
  <div id="notiBox">

    <!-- 카테고리 부분 -->
    <div id="notiCategory">
      <input type="radio" name="tabmenu" id="all">
      <label for="all">전체</label>
      <input type="radio" name="tabmenu" id="follow">
      <label for="follow">새 팔로워</label>
      <input type="radio" name="tabmenu" id="like">
      <label for="like">좋아요</label>
      <input type="radio" name="tabmenu" id="comment">
      <label for="comment">댓글</label>

      <div class="con">
        <div class="conBox con1">전체 알림</div>
        <div class="conBox con2">새 팔로워 알림</div>
        <div class="conBox con3">좋아요</div>
        <div class="conBox con4">댓글</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data: () => ({}),
  };
</script>
<style scoped src='./notice.css' />