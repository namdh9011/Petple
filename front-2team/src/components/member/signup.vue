<template>

    <div>
        <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                       @keyup.enter="login"
                       id="email" placeholder="이메일을 입력하세요."
                       type="text" />
        <div v-if="error.email">
            {{error.email}}
        </div>
        <input v-model="password" type="password"
                       v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                       id="password"
                       @keyup.enter="login"
                       placeholder="비밀번호를 입력하세요." />
        <div v-if="error.password">
            {{error.password}}
        </div>
        <input type="text" class="fadeIn third" v-model="name" placeholder="이름 입력" />
        <input type="text" class="fadeIn third" v-model="nickname" placeholder="닉네임 입력" />
        <input type="text" class="fadeIn third" v-model="phone" placeholder="핸드폰 번호 입력"  />
        <div v-if="error.phone">
            {{error.phone}}
        </div>
        <input type="button" v-on:click="signup" class="fadeIn fourth" value="회원가입" />
    </div>

</template>
​
<script>
import axios from 'axios';
import '@/apis/smtp.js';
import * as EmailValidator from 'email-validator';
import PV from 'password-validator'

var auth = Math.floor(Math.random() * 100000) + 10000
export default {
    
    data() {
        return {
            email: "",
            nickname: "",
            name: "",
            password: "",
            phone: "",
              passwordSchema: new PV(),
            error: {
                email: false,
                passowrd: false,
            },
            isSubmit: false,
            component: this
        };
    },
    created() {
      this.passwordSchema
        .is().min(8)
        .is().max(100)
        .has().digits()
        .has().letters();
    },
    watch: {
      password: function (v) {
        this.checkForm();
      },
      email: function (v) {
        this.checkForm();
      },
      phone: function(v) {
          this.checkForm();
      }
    },
    methods: {
        checkForm() {
            if (this.email.length >= 0 && !EmailValidator.validate(this.email))
            this.error.email = "이메일 형식이 아닙니다.";
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
            
            let phone= true
            if(!phone.value.match( /^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$/ ))
                this.error.phone = '휴대폰 번호를 정확하게 입력해주세요'
            else this.error.phone = false;

        },
        signup() {
             if(this.isSubmit) {
            const email = this.email;
            const name = this.name;
            const nickname = this.nickname;
            const password = this.password;
            const phone = this.phone;
            
            axios.get('http://i02a202.p.ssafy.io:8080/petple/loginemailcheck/'+ email)
            .then(res=>{
                if(res.data===false) {
                    axios.post('http://i02a202.p.ssafy.io:8080/petple/loginmember', {email, name, nickname, password, phone,} )
                    .then(res=> {
                        if(res.status === 200) {
                            alert("이메일이 발송되었습니다. 인증번호를 입력해주세요.")

                            Email.send("snspetple@gmail.com",//보내는 이메일 계정
                            this.email,// 받는 이메일 계정
					        "안녕하세요 고객님 PetPle 입니다.",
                            "<img src="+"'`http://i02a202.p.ssafy.io:8080/petple/static/img/default.png`'>"+"<br>"+"<h1>안녕하세요 PetPle입니다</h1><br>"+"<h1>인증번호를 확인해주세요.</h1><br>"+"<h1>인증번호는 "+auth+" 입니다</h1>", //인증번호
					        "smtp.gmail.com",//구글 smtp사용
					        "snspetple@gmail.com",//구글이메일전체
                            "petsns0460");

                            this.$router.push({name: 'auth', params:{"email": email, "Auth": auth}});
                        }
                    })
                    .catch(err=> {
                        alert("서버 에러1")
                    })    
                } else alert("이미 존재하는 이메일입니다.")
            })
            .catch(err => {
                alert("서버 에러2")
            }) 
             }else {
                alert('이메일과 패스워드는 필수입니다. \n양식을 채워주세요')
            }
        }
    }
};
</script>
​
<!-- 회원가입 CSS import 방식 변경-->
<style scoped src='./member.css'/> 
