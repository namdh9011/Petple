<template>
    <div class="container">
        <div class="left_blank"></div>

        <div id="followBox">
            <!-- 카테고리 부분 -->
            <div id="followCategory">
                <input type="radio" v-model="dataType" value="1" name="tabmenu" id="follower">
                <!-- <input type="radio" value="1" name="tabmenu" id="follower"> -->
                <label for="follower">팔로워</label>
                <input type="radio" v-model="dataType" value="2" name="tabmenu" id="following">
                <!-- <input type="radio" value="2" name="tabmenu" id="following"> -->
                <label for="following">팔로잉</label>


                <div class="followerInfo" v-if="this.dataType == '1'">
                    <!-- <div class="followerInfo">     -->
                    <div class="con" v-for="followlist in followlists" :key="followlist.unum">
                        <div class="conBox con1">
                            <router-link :to="`/same_user_detail/${followlist.unum}`">
                            <img class="followPhoto" :src="`${followlist.userprofile}`" width="100" height="100">
                            <!-- 이름 :   {{followlist.name}} -->
                            <div class="followNick">{{followlist.nickname}}</div>
                            <!-- 흥미 동물 : {{followlist.interest}}  -->
                            </router-link>
                        </div>
                    </div>
                </div>
                <div class="followingInfo" v-else-if="this.dataType == '2'">
                    <!-- <div class="followingInfo"> -->
                    <div class="con" v-for="followinglist in followinglists" :key="followinglist.unum">
                        <div class="conBox con2">
                            <router-link :to="`/same_user_detail/${followinglist.unum}`">
                            <img class="followPhoto" :src="`${followinglist.userprofile}`" width="100" height="100">
                            <!-- 이름 : {{followinglist.name}} -->
                            <div class="followNick">{{followinglist.nickname}}</div>
                            <!-- 흥미 동물 : {{followinglist.interest}} -->
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div>
            
        </div> -->
        </div>
        <div class="blank_right"></div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                dataType: '',
            }
        },
        created() {
            const unum = String(this.$route.params.unum)
            this.dataType = this.$route.params.getss;

            this.$store.dispatch('userFollow', unum); // user가 Follow한 사람의 리스트 통신
            this.$store.dispatch('userFollowing', unum); // user가 Following한 사람의 리스트 통신
        },
        computed: {
            followlists() {
                return this.$store.state.followlist;
            },
            followinglists() {
                return this.$store.state.followinglist;
            }
        },
    };
</script>
<style scoped src="./follow.css">

</style>