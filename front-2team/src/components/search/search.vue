<template>
<div>
<div class="w3-container" style="text-align:center">
    <div id="second_box" class="w3-third" style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
    </div>
    <div id="second_box" class="w3-third" v-if="nick_len>0" style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
        <h1 style="font-family: 'Poor Story', cursive;">닉네임 검색결과 </h1>
    </div>
    <div id="second_box" class="w3-third" v-else style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
        <h1 style="font-family: 'Poor Story', cursive;">닉네임 검색결과가<br> 없습니다. ㅠ_ㅠ</h1>
    </div>
</div>  
<div class="w3-row-padding">
    <div id="second_box" class="w3-third" v-for="(search, index) in search_nickname" :key="index" >
        <div class="w3-card" style="text-align: center; display: block; margin: 0 auto; margin-bottom: 15px;" width="50%">
            <router-link :to="`/same_user_detail/${search.unum}`">
                <img :src="`${search.userprofile}`" width="100%" height="378px"/>
            </router-link>
        </div>
    </div>
</div>
<div class="w3-container" style="text-align:center">
    <div id="second_box" class="w3-third" style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
    </div>
    <div id="second_box" class="w3-third" v-if="hash_len>0" style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
        <h1 style="font-family: 'Poor Story', cursive;">해시태그 검색결과 </h1>
    </div>
    <div id="second_box" class="w3-third" v-else style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
        <h1 style="font-family: 'Poor Story', cursive;">해시태그 검색결과가<br> 없습니다 ㅠ_ㅠ</h1>
    </div>
</div>  
<div class="w3-row-padding powjhpwoehgj">
    <div></div>

    <div id="second_box" class="w3-third" v-for="(search, index) in search_hashtags" :key="index" >
        <div class="w3-card" style="text-align: center; display: block; margin: 0 auto; margin-bottom: 15px;" width="50%">
            <router-link :to="`/postdetail/${search.pnum}`">
                <img :src="`${search.img}`" width="100%" height="378px"/>
            </router-link>
            <div class="w3-container">
                <!-- <h3 style="font-family: 'Poor Story', cursive;"><b>{{search.title}}</b></h3>
                <h5 style="font-family: 'Poor Story', cursive;">{{search.tags}}</h5>
                <h5 style="font-family: 'Poor Story', cursive;">좋아요 : {{search.likes}} 개</h5>
                <h5 style="font-family: 'Poor Story', cursive;">조회수 : {{search.hits}} 번</h5>
                <h5 style="font-family: 'Poor Story', cursive;">작성일자 : {{search.date.substring(0,4)}} 년 {{search.date.substring(5,7)}} 월 {{search.date.substring(8,10)}} 일</h5>
                <div id="intro"> 공개여부 : {{search.privacy}} </div>
                <br> -->
                <hr>
            </div>            
        </div>
    </div>

    <div></div>
</div>
</div>
</template>

<script>


export default {
    data() {
        return {
            nick_len : 0,
            hash_len : 0,
        }
    },
    computed: {
        search_nickname()  {
            const len = this.$store.state.search_nickname.length;
            //console.log("닉네임 길이"+len)
            this.nick_len = len;
            return this.$store.state.search_nickname;
        },
        search_hashtags() {
            const len = this.$store.state.search_hashtags.data.length;
            //console.log("해시태그 길이"+len)
            this.hash_len = len;
            if(len>0)
                return this.$store.state.search_hashtags.data;
        }
    }
}
</script>
<style scoped src='./search.css'>
    @import url(https://www.w3schools.com/w3css/4/w3.css);
    .powjhpwoehgj{
        display: grid;
        grid-template-columns: 1fr 11fr 1fr;
    }
</style>
