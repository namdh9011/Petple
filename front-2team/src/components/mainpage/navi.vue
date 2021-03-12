<template>
<!-- blank1, blank2 팀원과 상의없이 수정 금지  -->
<div class="nav_outer_container">
    <div class="nav_container">
        <!-- <div class="outerblank1"></div> -->


        <div class="nav_box">
            <div class="blank1">
                <div class="blank1_inner_blank"></div>
                <div class="real_nav_logo">
                    <router-link v-bind:to="{name:'Mainpage'}">
                        <!-- <img src="http://70.12.246.112:8082/img/default.png" alt=""> -->
                        <!-- <img class="logoImg" src="@/assets/mainpage/logo.png" >
                        <img class="logoImg" src="@/assets/mainpage/logoPurple.png" > -->
                        <!-- <img src="@/assets/mainpage/logo.png" > -->
                        <img class="logoImg" src="@/assets/mainpage/logo.png" >
                        <img class="logoImg" src="@/assets/mainpage/logoPurple.png" >
                    </router-link>
                </div>
            </div>


            <div class="nav_logo"></div>


            <!-- <div class="nav_search">
                <form ref="form">
                    <div class="search-box">
                        <input class="search-txt" v-model="search_value" type="text" name="" placeholder="Type to search">
                        <a href="#" v-on:click="submit" class="search-btn">
                            <i class="fas fa-search"></i>
                        </a>
                    </div>
                </form>
            </div> -->

            <div class="nav_search">
                <div class="search">
                    <input @keyup.enter="submit" type="text" class="searchTerm" placeholder="검색어를 입력해주세요." v-model="search_value">
                    <button  type="submit" @click="submit" class="searchButton" >
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
            
            <div class="blank2">
               
            <!-- <div class="nav_usernickname"> 요놈이 업로드 버튼
                <router-link :to="`/postupload`">
                    <img src="@/assets/postupload/pawPink.png" width="60" height="60"><br>Upload
                </router-link>
            </div> -->

                 <!-- <div class="nav_search">
                <div class="search-box">
                <input class="search-txt" type="text" name="" placeholder="Type to search">
                <a href="#" class="search-btn">
                    <i class="fas fa-search"></i>
                </a>
                </div>
            </div> -->
            </div>

            <div class="nav_userprofile">
                <router-link :to="`/same_user_detail/${this.user_num}`">
                    <img class="myPageImg" src="@/images/mainpage/myPagePurple.png" width="50" height="50">
                    <img class="myPageImg" src="@/images/mainpage/myPagePurpleHover.png" width="50" height="50">
                </router-link>
            </div>

            <div class="innerblank3">
                <!-- 업로드 버튼을 sideMenu.vue의 가장 하단으로 내릴 예정<br>
                columns를 1fr 11fr 1fr로 변경하고<br>
                로고의 왼쪽과 피드카드의 왼쪽을 맞춰주어야 한다. -->
                <v-btn large color="#F5FFFA" @click="logoutUser()" class="logoutButton" style="font-family: 'Poor Story', cursive;">로그아웃</v-btn>
                <img class="userProfile_in_navibar" :src="`${oneSameUserInformations.userprofile}`" width="60px" height="60px">
            </div>
                <span class="userNickname_in_navibar">{{oneSameUserInformations.nickname}}</span>
        </div>

        <div class="outerblank2">
             
        </div>
    </div>


    <div class="nav_role_tab">
        <div class="inner_outer nav_role_tab_left_blank"></div>

        <div class="nav_role_tab_welcome">
            
            <div class="search">
                <input @keyup.enter="submit" type="text" class="searchTerm" placeholder="검색어를 입력해주세요." v-model="search_value">
                <button  type="submit"  @click="submit" class="searchButton" >
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </div>

        <div class="inner_outer nav_role_tab_right_blank"></div> 
    </div>
</div>
    <!-- <div>
        <nav class="main_nav">
            <router-link v-bind:to="{name:'Mainpage'}">
                <img src="../../assets/mainpage/logo.png" width="170" height="80">
            </router-link>
            <div class="search-box">
                <input class="search-txt" type="text" name="" placeholder="Type to search">
                <a href="#" class="search-btn">
                    <i class="fas fa-search"></i>
                </a>
            </div>
            <div class="menus">
                <ul>
                    <li><a href="/postupload"><i class="fas fa-plus"></i>Upload</a></li>
                    <li><a href="/notice"><i class="fas fa-paw"></i>Alarm</a></li>
                    <li>
                        <router-link :to="`/same_user_detail/${$store.state.public_unum}`">
                            <img src="../../images/mainpage/seolyuGitHub.png" width="80" height="80">MyPage
                        </router-link>
                    </li>
                </ul>
            </div>
        </nav>
    </div> -->
</template>

<script>

const API_URL = 'https://omdb-api.now.sh/?type=movie&s=';

export default {
    data() {
        return {
            search_value : "",
            user_num: '',
        }
    },
    methods : {
        async logoutUser () {
            await sessionStorage.removeItem('user_token')
            await sessionStorage.removeItem('user_num')
            await this.$router.push({name: 'welcome'}).catch(err=>{})   
        },
        async submit() {
           const searchTerm = `${this.search_value}`;
           console.log(searchTerm);
           
          //  const url = API_URL + encodeURIComponent(searchTerm);
       //  alert(searchTerm)
            await this.$store.dispatch('SearchResult', searchTerm);
            this.$router.push({name: 'search'}).catch(err=>{});            
        },
        
    },
    computed:{
        oneSameUserInformations() {
            return this.$store.state.onesameuserlists;
        }
    },
    created () {   //current_user_token
        let unum = sessionStorage.getItem('user_num');
        this.$store.dispatch('detailSameUsers', unum);
        this.user_num = sessionStorage.getItem('user_num');
        // let utoken = sessionStorage.getItem('user_token');
        // this.$store.dispatch('currentUserToken', unum);
    }
};
</script>
<style scoped src='./navi.css'>
</style>