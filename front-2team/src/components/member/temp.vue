<template>
    <div class="LoginTab">
        <input type="radio" name="tabs" id="tab1" checked/>
        <label for="tab1">Login</label>
        <div id="tab-content1" class="tab-content">
            <input type="text" v-model="email" placeholder="Email ID 입력" /> <br/><br/>
            <input type="password" v-model="password" placeholder="비밀번호 입력" /> <br/><br/>
            <br/>
            <button v-on:click="userlogin">로그인</button>
            <br/><br/>
            <hr>
            <br/>
            <button>카카오톡</button>
            <br/><br/>
            <button>네이버</button>
            <br/><br/>
            <button>구글</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
        email: "",
        password: "",
    };
  },
  methods: {
    userlogin () {
        const email = this.email;
        const password = this.password;
        //Axios Bug fix
        axios.post('http://70.12.247.104:9090/userlogin', { email, password })
            .then(res => {
                if (res.status === 200) {
                    alert(res.data)
                    if(res.data==true){
                        this.$router.push({name: 'Mainpage'}) ;
                    }else{
                        alert("입력하신 정보가 올바르지 않습니다.")
                    }
                    // document.cookie = `accessToken=${res.data.data.accessToken}`;
                    // axios.defaults.headers.common['x-access-token'] = res.data.data.accessToken;
                }
            })
            .catch(err => {
                alert("서버 에러")
            }) 
    } 
  } 
};
</script>
<style scoped src='./member.css'/>