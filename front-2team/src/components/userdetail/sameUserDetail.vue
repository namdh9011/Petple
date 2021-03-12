<template>
    <div>

        <div class="w3-container" style="text-align:center">
            <div id="second_box" class="w3-third"
                style="display: flex; height: 20vh; justify-content: center; align-items: center;">
                <div id="profilePhoto">
                    <img class="myProfilePhoto" :src="this.$store.state.onesameuserlist.userprofile" width="100px" height="100px" />
                </div>
            </div>
            <div id="second_box" class="w3-third"
                style="display: flex; height: 20vh; justify-content: center; align-items: center;">
                <div id="followHelloBox">
                    <div class="postFollow">
                        <button class="postNum">게시물 {{myPostFeed.length}}</button>
                        <button @click="follower" class="follower"  style="cursor: pointer">팔로워 {{userFollows.length}}</button>
                        <button @click="following" class="following"  style="cursor: pointer">팔로잉 {{userFollowings.length}}</button>
                    </div>
                    <div class="hello" v-if="aaa==1">안녕하세요 {{this.$store.state.onesameuserlist.nickname}}님 <br> 반갑습니다 ^-^</div>
                    <div class="hello" v-else> 팔로우를 하여 소식을 전해들으세요! </div>
                </div>
            </div>
            <div id="second_box" class="w3-third"
                style="display: flex; height: 20vh; justify-content: center; align-items: center;" v-if="aaa==1">
                <div id="profileEdit" >
                        <button @click="userInfo" class="w3-button w3-purple w3-round-xlarge" style="cursor: pointer">
                            프로필 편집
                        </button>
                </div>
            </div>
            <div id="second_box" class="w3-third" style="display: flex; height: 20vh; justify-content: center; align-items: center;" v-else>
                <div id="profileEdit">
                    <h3> 팔로우 하기 </h3>
                    <label class="switch">
                    <input type="checkbox" @click="follow" v-model="checks">
                    <span class="slider round"></span>
                    </label>
                </div>
            </div>
            <br>
        </div>
        <div class="w3-container" style="text-align:center">
            <div id="second_box" class="w3-third"
                style="display: flex; height: 1vh; justify-content: center; align-items: center;">
                <h1> </h1>
            </div>
            <div id="second_box" class="w3-third"
                style="display: flex; height: 20vh; justify-content: center; align-items: center;">
                <h1 style="font-family: 'Poor Story', cursive;"> 같이 사는 친구들 </h1>
            </div>
            <div id="second_box" class="w3-third"
                style="display: flex; height: 20vh; justify-content: center; align-items: center;"  v-if="aaa==1">
                <button class="w3-button w3-purple w3-round-xlarge" @click="PetAdd">펫 추가 </button>
            </div>
        </div>
        <div class="w3-row-padding">
            <!-- <div class="w3-row-padding_left_blank"></div> -->


            <div id="second_box" class="w3-third" v-for="(userownanimal, index) in userownanimals" :key="index">
                <div class="w3-card" style="text-align: center; display: block; margin: 0 auto; margin-bottom: 15px;"
                    width="50%">
                    <router-link :to="`/petdetail/${userownanimal.petnum}`">
                        <img :src="`${userownanimal.img}`" width="100%" height="378px" />
                    </router-link>
                    <div class="w3-container">
                        <!-- <h2 style="font-family: 'Poor Story', cursive;"><b>{{userownanimal.name}}</b> {{userownanimal.animal}}({{userownanimal.gender}}) </h2> -->
                        <!-- <h3 style="font-family: 'Poor Story', cursive;">{{userownanimal.breed}} {{userownanimal.age}}살</h3>
                        <h3 style="font-family: 'Poor Story', cursive;">첫 만남 : {{userownanimal.firstday.substring(0,4)}} 년 {{userownanimal.firstday.substring(5,7)}} 월 {{userownanimal.firstday.substring(8,10)}} 일</h3> -->
                        <hr>
                        <br>
                    </div>
                </div>
            </div>

            <!-- <div class="w3-row-padding_right_blank"></div> -->
        </div>
<div class="w3-container" style="text-align:center">
    <div id="second_box" class="w3-third" style="display: flex; height: 20vh; justify-content: center; align-items: center;" >
        <h1 style="font-family: 'Poor Story', cursive;"> 내 게시물 </h1>
    </div>
</div>  
<div class="w3-row-padding">
    <div id="second_box" class="w3-third" v-for="(myfeed, index) in myPostFeed" :key="index" >
        <div class="w3-card" style="text-align: center; display: block; margin: 0 auto; margin-bottom: 15px;" width="50%">
            <router-link :to="`/postdetail/${myfeed.pnum}`">
                <img :src="`${myfeed.img}`" width="100%" height="378px"/>
            </router-link>
            <div class="w3-container">
                <!-- <h2 style="font-family: 'Poor Story', cursive;"><b> 제목 : {{myfeed.title}}</b></h2> -->
                <!-- <h3 style="font-family: 'Poor Story', cursive;">{{myfeed.tags}}</h3>
                <h3 style="font-family: 'Poor Story', cursive;">좋아요 : {{myfeed.likes}} 조회수 : {{myfeed.hits}}</h3>
                <h3 style="font-family: 'Poor Story', cursive;">작성일자 : {{myfeed.date.substring(0,4)}}년 {{myfeed.date.substring(5,7)}}월 {{myfeed.date.substring(8,10)}}일</h3> 
                <div id="intro" style="font-family: 'Poor Story', cursive;"> 공개여부 : {{myfeed.privacy}} </div>
                -->
                <hr>
                <br>
            </div>            
        </div>
    </div>
</div>

</div>
    

</template>


<script>
export default {
    data() {
        return {
            aaa:0,
            fcheck : 0,
            checks: '',
        }
    },
    async created() {
        // console.log("created")
        const public_unum = String(this.$route.params.public_unum)
        const unum = sessionStorage.getItem('user_num');
        if(public_unum==unum) {
            this.aaa=1;
        }

        // this.$store.dispatch('detailSameUser', public_unum);
        //async처리
        await this.$store.dispatch('userOwnAnimal', public_unum);
        await this.$store.dispatch('userfollow', public_unum);
        await this.$store.dispatch('userFollowing', public_unum);
        await this.$store.dispatch('detailSameUser', public_unum);
        await this.$store.dispatch('myPostFeed', public_unum);
        let data = {
            "follower": "",
            "following": public_unum,
            "folnum": "string",
            "unum": unum
        }

        await this.$store.dispatch('checkfollow', data);
        this.checks = this.$store.state.checkfollow;

    },
    computed: {
        myPostFeed() {
            return this.$store.state.mypostlist;
        } ,
        userownanimals() {
            return this.$store.state.userownanimal;
        } ,
        detailSameUser() {
            return this.$store.state.oneSameUserInformation;
        } ,
        userFollows() {
            return this.$store.state.followlist;
        } ,
        userFollowings() {
            return this.$store.state.followinglist;
        },
        checked() {
            return this.$store.state.checkfollow;
        },
    },
    methods: {
        follow() {
            // alert(this.fcheck)
            if(this.fcheck==0) {
                const unum12 = sessionStorage.getItem('user_num');
                const followingnum = (this.$route.params.public_unum);
                this.$store.dispatch('addFollow',{unum12, followingnum});   
                this.fcheck = 1;
            } else {
                const unum12 = sessionStorage.getItem('user_num');
                const followingnum = (this.$route.params.public_unum);
                this.$store.dispatch('delFollow',{unum12, followingnum});   
                this.fcheck = 0;
            }
        },
        PetAdd() {
            this.$router.push({name:'PetAdd'});
        },
        userInfo() {
            this.$router.push( {name:'Userdetail'});
        },
        follower() {
            const unum = (this.$route.params.public_unum);
            this.$router.push({name:'Follow',params:{"unum":unum, "getss":1}});
        },
        following() {
            const unum = (this.$route.params.public_unum);
            this.$router.push({name:'Follow',params:{"unum":unum, "getss":2}});
        }
    },  
    
}
</script>

<style scoped> 
    @import url(https://www.w3schools.com/w3css/4/w3.css);
    @import './sameUserDetail.css';
        /* The switch - the box around the slider */
    .switch {
    position: relative;
    display: inline-block;
    width: 60px;
    height: 34px;
    }

    /* Hide default HTML checkbox */
    .switch input {
    opacity: 0;
    width: 0;
    height: 0;
    }

    /* The slider */
    .slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: .4s;
    transition: .4s;
    }

    .slider:before {
    position: absolute;
    content: "";
    height: 26px;
    width: 26px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
    }

    input:checked + .slider {
    background-color: #2196F3;
    }

    input:focus + .slider {
    box-shadow: 0 0 1px #2196F3;
    }

    input:checked + .slider:before {
    -webkit-transform: translateX(26px);
    -ms-transform: translateX(26px);
    transform: translateX(26px);
    }

    /* Rounded sliders */
    .slider.round {
    border-radius: 34px;
    }

    .slider.round:before {
    border-radius: 50%;
    }
    </style>
