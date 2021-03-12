<template>
        <div>
            <input type="text" id="check" v-model="check" placeholder="인증번호 입력"> <br><br>
            <!-- <input type="text" id="interest" v-model="interest" placeholder="관심동물 입력"> <br><br> -->
            <input type="button" class="sdhwleihgwileghjwg" v-on:click="signup" value="인증번호 확인"/>
        </div>
</template>

<script>  
import axios from 'axios'

export default {
  data() {
        return {
            check:"",
            // interest: "",
        };
    },
  methods: {        
    signup() {
          const check = document.getElementById("check").value;
          const email = this.$route.params.email;
          const interest = this.interest;
          
          if(this.$route.params.Auth!=check) {
              alert("인증번호가 다릅니다.")  
          } else {
            //   axios.put('http://i02a202.p.ssafy.io:8080/petple/member/updateinterest', {email, interest} )
            //         .then(res=> {
            alert('회원가입 완료 로그인 해주세요')
            this.$router.push({name: 'welcome'});
                    // })
                    // .catch(err=> {
                        // alert("서버 에러")
                    // })
          }
      }
    }

}
</script>


<style scoped src='./member.css'>
.sdhwleihgwileghjwg:hover{
    cursor: pointer;
}
</style>
