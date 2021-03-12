<template>
  <div class="JoinUsTab">
        <div id="tab-content2" class="tab-content">
            <input type="text" id="check" v-model="check" placeholder="인증번호 입력"> <br><br>
            <input type="text" id="phone"  placeholder="휴대폰 번호 입력"> <br><br>
            <input type="text" id="interest" placeholder="관심동물 입력"> <br><br>
            <button v-on:click="signup">회원가입</button>
        </div>
    </div>
</template>

<script>  
import axios from 'axios'

export default {
  methods: {        
    signup() {
          const check = document.getElementById("check").value;
          const email = this.$route.params.email;
          const interest = this.interest;
          const nickname = this.nickname;
          const phone = this.phone;

          if(this.$route.params.Auth!=check) {
              alert("인증번호가 다릅니다.")  
          } else {
              axios.put('http://70.12.247.104:9090/user/updateinterest', {email, interest, phone} )
                    .then(res=> {
                        alert('회원가입 완료')
                         this.$router.push({name: 'member'});
                    })
                    .catch(err=> {
                        alert("서버 에러")
                    })
          }
      }
    }

}
</script>