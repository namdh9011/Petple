import Vue from 'vue';
import Vuex from 'vuex';
import axios from './axiosCommon.js';
import imgaxios from './axiosImageUpload.js'; 


Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    main_follow_list:{},
    user_interest:{},
    public_token: '',
    public_pnum: {},
    post_detail_unum: '',
    public_petNum: {},
    petlist: {},
    postlist: {},
    mypostlist: {},
    tenpostlist: {},
    onepostlist: {},
    onesameuserlist: {},
    onesameuserlists: {},
    userownanimal: [],
    followlist: {},
    followinglist: {},
    commentlist: {},
    searchlist: [],
    search_nickname: {},
    search_hashtags: {},
    check_user: false,
    check_feedlike: false,
    what_feedlike: 'all',
    emailcheck: '',
    checkfollow: false,
    emailunum: '',
  },
  getters: {
    // followinglist: state => state.followinglist.unum,
    Interest: state => state.user_interest,
    EveryPostList: state => state.postlist,
    petFirstday: state => state.petlist.firstday,
    postDetailHashTags: state => state.onepostlist.tags,
  },
  mutations: {
    getPetOneDetail(state, payload) { // 펫 정보 한 객체
      state.petlist = payload.petlist;
    },
    myPostFeed(state, payload) { // 내가 쓴 모든 게시물 객체
      state.mypostlist = payload.mypostlist;
    },
    mainPostFeed(state, payload) { // 피드의 모든 객체
      state.postlist = payload.postlist;
    },
    onePostFeed(state, payload){ // 피드의 한 객체
      state.onepostlist = payload.onepostlist;
    },
    oneSameUserInformation(state, payload){ // 유저의 한 객체
      state.onesameuserlist = payload.onesameuserlist;
    },
    oneSameUserInformations(state, payload){ // 네비게이션 바 유저 프로필, 닉네임
      state.onesameuserlists = payload.onesameuserlists;
    },
    userOwnAnimal(state, payload) { // 유저가 소유한 모든 반려동물 객체
      state.userownanimal = payload.userownanimal;
    },
    userfollow(state, payload) { // 유저가 팔로우 한 다른유저의 모든 객체
      state.followlist = payload.followlist;
    },
    userfollowing(state, payload) { // 유저가 팔로잉 한 다른유저의 모든 객체
      state.followinglist = payload.followinglist;
    },
    searchlist(state, payload) { // 검색한 모든 피드의 객체
      state.searchlist = payload.searchlist;
    },
    search_nickname(state, payload) { // 닉네임 검색 
      state.search_nickname = payload.search_nickname;
    },
    search_hashtags(state, payload) { // 해시태그 검색
      state.search_hashtags = payload.search_hashtags;
    },
    mainFeedTen(state, payload) { // 메인에서 원하는 수의 피드
      state.tenpostlist = payload.tenpostlist;
    },
    initPostNumber(state, payload){ // 게시물 번호 저장
      state.public_pnum = payload.public_pnum; 
    },
    getToken(state, payload) { // 토큰 저장
      state.public_token = payload.public_token;
    },
    initPetNum(state, payload) { // petNumber 저장
      state.public_petNum = payload.public_petNum;
    },
    getPostComments(state, payload) { // 게시물의 모든 댓글 
      state.commentlist = payload.commentlist;
    },
    postDetail_getUserNum(state, payload) { // 게시물 작성한 사람의 번호
      state.post_detail_unum = String(payload.post_detail_unum);
    },
    LoginCheck(state, payload) { // 로그인 체크!
      state.check_user = payload.check_user;
    },
    likeConfirm(state, payload) { // 좋아요를 눌렀는지 확인
      state.check_feedlike = payload.check_feedlike;
    },
    unum_set(state, payload) {
      state.unum = payload.unum;
    },
    change_whatFeed(state, payload) { // 메인에서 어떤 종류 피드 정할지
      state.what_feedlike = payload.what_feedlike;
    },
    interest(state, payload) { // 흥미동물
      state.interest = payload.interest;
    },
    interestCheck(state, payload) { // 흥미동물 체크 선택했는지 판별
      state.user_interest = payload.user_interest;
    },
    mainFollowList(state, payload) { // 메인 팔로우만 보기 모든 게시물
      state.main_follow_list = payload.main_follow_list;
    },
    emailcheck(state, payload) {
      state.emailcheck = payload.emailcheck;
    },
    checkfollow(state, payload) {
      state.checkfollow = payload.checkfollow;
    },
    emailunum(state, payload) {
      state.emailunum = payload.emailunum;
    }
  },
  actions: {
    mainFollowList: ({commit}, unum) => {
      axios
        .get(`/post/selectfollower/${unum}`)
        .then((res) => {
          // console.log(res.data.data);
          
          commit('mainFollowList',{main_follow_list: res.data.data})
        })
        .catch((err) => {
         // alert(err+'            From store mainFollowList')
        })
    },
    petOneDetail: async ({commit},pnum) => { // 펫 상세
      await axios
        .get(`/petone/${pnum}`)
        .then((response) => {
          store.commit('getPetOneDetail', { petlist: response.data });
        })
        .catch(() => {
        });
    },
    myPostFeed: async ({commit}, unum) => {// 내가 쓴 게시물
      axios
        .get(`/post/selectmine/${unum}`)
        .then((response) => {
          //console.log(response.data);
          commit('myPostFeed', { mypostlist: response.data.data });
        })
        .catch(() => {
        });
    },
    mainPostFeed: (limit) => { // 모든 게시물
      axios
        .get(`/post/selectten/${limit}`)
        .then((response) => {
        //console.log(response.data);
          
          store.commit('mainPostFeed', { postlist: response.data });
        })
        .catch(() => {
        });
    },
    detailPostFeed: async( {state, commit}, postNum ) => { // 게시물 상세
      await axios
        .get(`/post/selectone/${postNum}`)
        .then((response) => {
          //console.log('211212112'+response.data.data);
          
          commit('onePostFeed', { onepostlist: response.data.data});

          state.post_detail_unum = response.data.data.unum;

          //console.log(state.post_detail_unum);
          
          // commit('postDetail_getUserNum', { post_detail_unum: response.data.data.unum})
        })
        .catch((err) => {
         // alert(err+'From store detailPostFeed')
        });
        
        await axios
        .put(`/post/hitup/${postNum}`)
        .then((res) => {
        })
        .catch((err) =>{
            
        })
    },
    interestCheck: async ({commit}, unum) => {
      await axios
        .get(`/member/${unum}`)
        .then((res) => {
          commit('interestCheck',{user_interest: res.data.interest})
        })
        .catch((err) => {
         // alert(err+'      From store interestCheck')
        })
    },
    detailSameUser: async ({commit}, unum) => { // 마이페이지 본인정보상세
      await axios
        .get(`/member/${unum}`)
        .then((response) => {
          // alert(JSON.stringify(response.data.interest))
          if(response.data.userprofile===null) // 사진이 등록되어있지 않다면
            response.data.userprofile = "http://52.78.132.70:8080/petple/static/img/default.png";
          commit('oneSameUserInformation', { onesameuserlist: response.data})
          commit('interest', {user_interest: response.data})
          commit('')
        })
        .catch(()=>{
          sessionStorage.delete('user_tokon')
          sessionStorage.delete('user_num')
          alert('다시 로그인 해주세요')
          this.$router.push({name: 'welcome'})
        });
    },
    detailSameUsers: async ({state,commit}, unum) => { // 마이페이지 본인정보상세
      await axios
        .get(`/member/${unum}`)
        .then((response) => {
          if(response.data.userprofile===null) // 사진이 등록되어있지 않다면
            response.data.userprofile = "http://52.78.132.70:8080/petple/static/img/default.png";
            // console.log(response.data);
          commit('oneSameUserInformations', { onesameuserlists: response.data})
          // console.log(state.onesameuserlists);
          
        })
        .catch(()=>{
          sessionStorage.delete('user_tokon')
          sessionStorage.delete('user_num')
          alert('다시 로그인 해주세요')
          this.$router.push({name: 'welcome'})
        });
    },
    userOwnAnimal: async ({commit}, unum) => { // 본인의 반려동물 리스트
      // console.log("dispatch")
      await axios
        .get(`/petall/${unum}`)
        .then((response) => {
          commit('userOwnAnimal', { userownanimal: response.data})
        })
        .catch(()=>{

        })
    },
    InsertPost: async ({commit},data) => { // 게시물 등록
      // axios
      await axios.
        post('/post/insert', data)
        .then((response) => {
          // alert(response);
         // console.log(JSON.stringify(response));
       //  console.log(response.data.data+'여기는 actions');
          
          commit('initPostNumber',{ public_pnum: response.data.data})
        })
        .catch(() =>{

        });
    },
    addPet: async ({commit},data) => { // 펫 추가
      // axios
      //alert('추가할 데이터'+JSON.stringify(data));
      await axios.
        post('/pet', data)
        .then((response) => {
          commit('initPetNum', { public_petNum: response.data.petnum} )
          //alert('펫 넘버'+public_petNum);
        })
        .catch(() =>{

        });
    },
    addPetImage: async ({state,commit}, file) => {
      //alert("사진 업로드"+JSON.stringify(state.public_petNum));
      // alert("사진 추가하기 전 펫넘버"+`${state.public_petNum}`);
      await imgaxios
      .post(`/petprofileUpload?pnum=${state.public_petNum}`, file)
        .then((res) => {
          // alert("파일추가"+JSON.stringify(res));
         // console.log(JSON.stringify(res));
         // alert('사진 추가 완료')
        })
        .catch(()=> {
        })
    },
    addUserImage: async ({state,commit}, data) => {
      // alert("사진 업로드 전"+data.unum);
      // alert("사진 업로드 전"+data)
      // console.log('store unum = '+unum)
      await imgaxios
      .post(`/userprofileUpload?unum=${sessionStorage.getItem('user_num')}`,data)
      .then((res)=>{
       // alert("파일추가"+JSON.stringify(res))
      })
      .catch(()=>{

      })
    },
    delPet: async ({state},petnum)=> { // 펫 삭제
      await axios.
      delete(`/petdelete/${petnum}`)
        .then((res) => {
        })
        .catch(()=> {
          
        })
    },
    delPost: async({state},num)=> {
      await axios
      .delete(`/post/delete/${num}`)
      .then(res=>{
        alert('삭제완료');
      })
    },
    uptPet: async ({commit},data)=> { // 펫 수정
      
      await axios.
      put(`/petupdate`,data)
        .then((res) => {
     //     console.log('업데이트 성공')
       //   console.log(JSON.stringify(data))

          commit('initPetNum', { public_petNum:data.petnum} )
        })
        .catch(()=> {
          
        })
    },
    uptPost: async ({commit,state},data)=> {
      await axios.
      put(`/post/update`,data)
      .then((response) => {
        // alert("수정 된 뒤 response"+response);
        // commit('initPostNumber',{ public_pnum: data.pnum})
        // alert("수정 된 뒤 pnum 확인"+state.public_pnum);
        //alert('수정 완료')
      })
      .catch(()=>{
      //  console.log('문제발생')
      })
    },
    delUser: async({commit}, unum) => {
      axios
      .delete(`/memberdelete/${unum}`)
      .then((res)=> {
        alert('탈퇴 완료');
      })
      .catch(()=> {
      })
    },
    uptUser: async({commit},data)=> {
      // alert(JSON.stringify(data))
      await axios
      .put(`/member/update`,data)
      .then((res)=>{
        //alert('complete')
        //commit('unum_set', {unum: data.unum});
      })
      .catch(()=>{
        //alert(JSON.stringify(data))
      })
    },
    checkfollow: async ({commit,state}, data) => {
      await axios
      .post(`/followingcheck`,data)
      .then((res)=>{
        commit('checkfollow', {checkfollow: res.data});
      })
      .catch(()=>{

      })
    },
    userfollow: async ({ commit }, unum) => {// 팔로워 불러옴
      await axios
        .get(`/follower/${unum}`)
        .then((res) => {
          commit('userfollow', { followlist: res.data })
        })
        .catch((err)=>{
         // alert(err+'From store userFollow')
        })
    },
    userFollowing: async ({commit}, unum) => {
      await axios
        .get(`/following/${unum}`)
        .then((res) => {
      //    console.log('userFollowing'+res.data.length);
          commit('userfollowing', { followinglist: res.data })
        })
        .catch(()=>{
       //   alert(err+'From store userFollowing')
        })
    },
    SearchResult: async ({commit}, searchTerm) => {
      //alert("통신"+`${searchTerm}`);
      await axios
      .get(`/membersearchNick/${searchTerm}`)
      .then(res=>{
        //alert('닉네임 서치 결과 길이확인')
       // console.log(res.data+'           store 닉네임');
        

      //  alert(len);
          //console.log("닉네임 검색"+JSON.stringify(res.data));
          commit('search_nickname', {search_nickname: res.data});
      });

      await axios
      .get(`/post/tagsearch/${searchTerm}`)
      .then(res=>{
          //alert('해시태그 서치 결과 길이확인')
          console.log(res.data+'        store 태그');
          
          const len = res.data.length;
          if(len!="undefined")
            commit('search_hashtags', {search_hashtags: res.data});
      });
    },
    emailcheck:async({state,commit},email)=>{
      await axios
      .get(`/loginemailcheck/${email}`)
      .then(response=> {
        //alert(JSON.stringify(response.data))
          commit('emailcheck',{emailcheck: response.data});
      })

      await axios
      .get(`/loginsearchUnum/${email}`)
      .then(response=>{
        commit('emailunum',{emailunum: response.data})
      })
    },
    changepw:async({state,commit},data)=> {
      //alert("통신 진입"+JSON.stringify(data.email));
      //alert("통신 진입"+JSON.stringify(data.password));
      await axios
      .put(`/member/updatepassword`,data)
      .then(response=>{
        //alert(JSON.stringify(response.data))
        //alert('비번 변경 완료')
      })
    },
    InsertPostImage: async ({state,commit}, file) => { // 이미지 보내기
      // alert("이미지 올리기 전 pnum"+state.public_pnum);
      await imgaxios
      .post(`/postfileUpload?pnum=${state.public_pnum}`, file)
      .then((res) => {
        // alert(state.public_pnum+"에 사진 업로드");
        // alert(JSON.stringify(file)+"을 올렸습니다.")
        //alert('사진올리기완료')
        //console.log(res);
      })
      .catch(()=> {
        
      })
    },
    mainFeedTen: ({commit}, limit) => { // 메인페이지에서 피드를 limit 만큼 가져옴.
      axios
      .get(`/post/selectten/${limit}`)
      .then(response => {
    //    console.log(response.data)
        commit('mainFeedTen',{ tenpostlist: response.data })
      })
      .catch(err => {
        //alert(err + '    ' + '뭔가 에러가 났어요')
      })
    },
    getToken:async({state, commit}, data) => {
      await axios
        .post('loginsignin',data)
        .then(res => {
            // if (res.status === 200) {
            //     if(res.data==true){
              // console.log(res.data.data.authorization);
              //String(res.data.authorization
              // this.$session.set('user_no', "한솔");
              //console.log(res.data.data.authorization);
              //console.log(res.data.data)

              sessionStorage.setItem('user_token', res.data.data.authorization);
              sessionStorage.setItem('user_num', res.data.data.unum)
              //localStorage.setItem('user_no', res.data.authorization);
            commit('getToken', { public_token: res.data.data.authorization })
            //console.log(state.public_token);
            
                    
                // }else{
                //     alert("입력하신 정보가 올바르지 않습니다.")
                // }
                // document.cookie = `accessToken=${res.data.data.accessToken}`;
                // axios.defaults.headers.common['x-access-token'] = res.data.data.accessToken;
            // }
        })
        .catch(err => {
         //   alert("서버 에러")
        }) 
    },
    addFollow:({}, {unum12,followingnum})=>{
      axios
      .post(`following/${unum12}/${followingnum}`)
      .then((response)=>{
        // alert('팔로우 완료')
      })
      .catch(()=>{
          
      })
    },
    delFollow:({}, {unum12,followingnum})=>{
      axios
      .post(`unfollow/${unum12}/${followingnum}`)
      .then((response)=>{
        // alert('언팔로우 완료')
      })
      .catch(()=>{
          
      })
    },
    getPostComments: async ({state, commit}, postnum) => {
      await axios
        .get(`/comment/selectall/${postnum}`)
        .then((response) => {
          
          commit('getPostComments', { commentlist: response.data.data})
        })
        .catch(()=>{
          
        })
    },
    postComment: async ({state, commit}, data) => {
      await axios
        .post(`/comment/insert`, data)
        .then((response) => {
    //      alert('sdgsdgsdh     '+response)
        //  alert('댓글이 추가 되었습니다.')

        })
        .catch((err) => {
         // alert(err+ '  From store postComment')
        })
    },
    deleteComment:async ({state, commit}, no, pnum) => {
      // console.log("삭제 요청 전"+no)
      await axios
        .delete(`/comment/delete/${no}`)
        .then((response) => {
      //    alert(JSON.stringify(response)+'삭제되었습니다.')
          // this.$router.push({name: 'Postdetail', params:{"pnum": pnum}});
        })
        .catch((err) =>{
         // alert(err+'       From store deleteComment')
        })
    },
    LoginCheck: async({state, commit}, data) => { // 로그인시 이메일 존재 체크
      await axios
        .post(`/loginsignin`, data)
        .then((res) => {
          commit('LoginCheck', {check_user: true})
        })
        .catch((err) => {
         // alert(err+'    From store LoginCheck')
        })  
    },
    likeConfirm: async ({state, commit}, data) => {
      await axios
        .post(`/likepostConfirm`, data)
        .then((res) => {
        //  console.log(data.pnum+'   '+res.data);
          commit('likeConfirm', {check_feedlike: res.data})
        })
        .catch((err) => {
         // alert(err+'    From store likeConfirm')
        })
    },
    unlikeFeed: async ({commit}, data) => {
      await axios
        .post(`/likepostunclick`, data)
        .then((res) => {
          // alert(res+'    From store unlikeFeed')
        })
        .catch((err) => {
       //   alert(err+'    From store unlikeFeed')
        })

    },
    likeFeed: async ({commit}, data) => {
      await axios
        .post(`/likepostclick`, data)
        .then((res) => {
          // alert(res+ '    From store likeFeed')
        })
        .catch((err) => {
          // alert(err+'    From store likeFeed')
        })
    },
    likeDown: async({commit}, pnum) => {
      await axios
        .put(`/post/likedown/${pnum}`)
        .then((res) => {
          // alert(res)
        })
        .catch((err) => {
          // alert(err+'    From store likeDown')
        })
    },
    likeUp: async({commit}, pnum) => {
      await axios
        .put(`/post/likeup/${pnum}`)
        .then((res) => {
          // alert(res)
        })
        .catch((err) => {
         // alert(err+'    From store likeUp')
        })
    },
    commentUp: async({commit}, pnum) => {
      await axios
        .put(`/post/commentsup/${pnum}`)
        .then((res) => {
          // alert(res)
        })
        .catch((err) => {
        //  alert(err+'    From store commentUp')
        })
    },
    commentDown: async({commit}, pnum) => {
      await axios
        .put(`/post/commentsdown/${pnum}`)
        .then((res) => {
          // alert(res)
        })
        .catch((err) => {
       //   alert(err+'    From store commentUp')
        })
    }
  },
});


export default store;
