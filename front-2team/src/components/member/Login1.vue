<template>
  <div id=login>
    <form @submit.prevent="userlogin">
      <!-- <input type="text" class="fadeIn second" @keyup.enter="submit" v-model="email1" placeholder="E-mail 입력" />
        <input type="password" class="fadeIn third" @keyup.enter="submit" v-model="password" placeholder="비밀번호 입력" /><br> -->

      <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
        @keyup.enter="login" id="email" placeholder="이메일을 입력하세요." type="text" class="fadeIn second" />
      <div class="error-text" v-if="error.email">
        {{error.email}}
      </div>
      <input v-model="password" type="password"
        v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}" id="password"
        @keyup.enter="login" placeholder="비밀번호를 입력하세요." class="fadeIn third" />
      <div class="error-text" v-if="error.password">
        {{error.password}}
      </div>
      <input type="submit" @keyup.enter="submit" class="fadeIn fourth" value="로그인" />
    </form>
    <a href="http://i02a202.p.ssafy.io:8080/petple/oauth2/authorization/google">
      <input type="button" class="google fadeIn fourth" value="구글 로그인" />
    </a>
    <a href="http://i02a202.p.ssafy.io:8080/petple/oauth2/authorization/naver">
      <input type="button" class="naver fadeIn fourth" value="네이버 로그인" />
    </a>
  </div>


</template>

<script>
  // import '@/apis/naveridlogin_js_sdk_2.0.0.js';
  import axios from 'axios';
  import * as EmailValidator from 'email-validator';
  import PV from 'password-validator'

  // var naverLogin = new naver.LoginWithNaverId(
  // {
  //     clientId: "개발자센터에 등록한 ClientID",
  //     callbackUrl: "개발자센터에 등록한 callback Url",
  //     isPopup: false, /* 팝업을 통한 연동처리 여부 */
  //     loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
  // }
  // );

  /* 설정정보를 초기화하고 연동을 준비 */
  // naverLogin.init();

  export default {
    data() {
      return {
        email: '',
        password: '',
        passwordSchema: new PV(),
        error: {
          email: false,
          passowrd: false,
        },
        isSubmit: false,
        component: this
        // errmessage: "",
      };
    },
    created() {
      this.passwordSchema
        .is().min(8)
        .is().max(100)
        .has().digits()
        .has().letters();

        let unum = sessionStorage.getItem('user_num')
        let token = sessionStorage.getItem('user_token')
        // console.log(unum+'             '+token);
        
        if(unum != null){
          sessionStorage.removeItem('user_num')
          sessionStorage.removeItem('user_token')
        }
    },
    watch: {
      password: function (v) {
        this.checkForm();
      },
      email: function (v) {
        this.checkForm();
      },
    },
    methods: {
      checkForm() {
        if (this.email.length >= 0 && !EmailValidator.validate(this.email))
          this.error.email = "이메일 형식이 아닙니다."
        else this.error.email = false;

        if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
          this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
        else
          this.error.password = false;


        let isSubmit = true;
        Object.values(this.error).map(v => {
          if (v) isSubmit = false;
        })
        this.isSubmit = isSubmit;


      },
      async userlogin() {
        const email = this.email;
        const password = this.password;
        // console.log(this.email);
        // console.log(this.password);
        
        // console.log(email);
        let data = {
          'email': email,
          'password': password
        }
        await this.$store.dispatch('LoginCheck', data)
          // .then((res) => {
          //   this.$store.dispatch('getToken', data)
          //     .then(() => {
          //       alert('로그인 성공')
          //       this.$router.push({
          //         name: 'Mainpage'
          //       });
          //     })
          // })
          // .catch((err) => {
          //   alert('아이디 혹은 비밀번호를 확인해주세요.')
          //   this.email1 = '';
          //   this.password = '';
          // })
          // console.log(this.$store.state.check_user);
          
        if (this.$store.state.check_user == true) {

          await this.$store.dispatch('getToken', data)
            .then(() => {
              this.$router.push({
                name: 'Mainpage'
              })
            })
        } else {
          alert('아이디 비밀번호를 확인해주세요.')
          this.email1 = '';
          this.password = '';
        }
        //Axios Bug fix 
      },
    }
  }
</script>

<style scoped src='./member.css' />