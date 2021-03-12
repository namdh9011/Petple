<template>

    <div>
        <input v-model="email" @keyup.enter="signup" id="email" placeholder="이메일을 입력하세요." type="text" />
        <input type="button" v-on:click="signup" class="fadeIn fourth" value="임시 비밀번호 전송" />
    </div>

</template>
​
<script>
import axios from 'axios';
import '@/apis/smtp.js';

var password = Math.floor(Math.random() * 1000000) + '100ab';
export default {
    
    data() {
        return {
            email: '',
            emailcheck: this.$store.state.emailcheck,
        };
    },
    methods: {
        async signup() {
            const email = this.email;
            //commit('emailcheck',{emailcheck: false});
            
            //this.emailcheck = this.$store.state.emailcheck;
            //alert(email)
            await this.$store.dispatch('emailcheck',email);
            
            if(this.$store.state.emailcheck==true) {
                alert("임시 비밀번호가 발송되었습니다. 이메일을 확인해주세요.")
                
               Email.send("snspetple@gmail.com",//보내는 이메일 계정
					        this.email,// 받는 이메일 계정
					        "안녕하세요 고객님 PetPle 입니다.",//제목
                            "<img src="+"'`http://i02a202.p.ssafy.io:8080/petple/static/img/default.png`'>"+"<br>"+"<h1>안녕하세요 PetPle입니다</h1><br>"+"<h1>임시 비밀번호를 확인해주세요.</h1><br>"+"<h1>임시 비밀번호는 "+password+" 입니다</h1>", //인증번호
					        "smtp.gmail.com",//구글 smtp사용
					        "snspetple@gmail.com",//구글이메일전체
                            "petsns0460");

                const data = {
                    unum: this.$store.state.emailunum,
                    password: password,
                }

                //alert("data확인"+JSON.stringify(data))
                await this.$store.dispatch('changepw',data);  //구현안됨.
                this.$router.push({name: 'welcome'});
                // this.$store.dispatch(); //임시번호로 비밀번호 변경
            } else {
                alert("존재하지 않는 회원입니다.");
                this.$router.push({name: 'welcome'});
            }
        }
    }
};
</script>
​
<!-- 회원가입 CSS import 방식 변경-->
<style scoped src='./member.css'/> 
