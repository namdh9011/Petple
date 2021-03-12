import Vue from 'vue';
import Router from 'vue-router';
import member from '@/routes/member/member.js'
import mainpage from '@/routes/mainpage/mainpage'
import notice from '@/routes/notice/notice'
import pet_detail from '@/routes/petdetail/petDetail'
import post_detail from '@/routes/postdetail/postDetail'
import post_upload from '@/routes/postupload/postUpload'
import same_user_detail from '@/routes/sameuserdetail/sameUserDetail'
import follow from '@/routes/follow/follow'
import search from '@/routes/search/search'
import user_detail from '@/routes/userdetail/userDetail'
import loadong from '@/routes/loading/loading'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    ...member,
    ...mainpage,
    ...notice,
    ...pet_detail,
    ...post_detail,
    ...post_upload,
    ...same_user_detail,
    ...follow,
    ...search,
    ...user_detail,
    ...loadong
  ],
  scrollBehavior (to, from, savedPosition) { return { x: 0, y: 0 } }
}); 