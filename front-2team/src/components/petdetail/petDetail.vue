<template>
    <!-- 전체 wrap -->
    <div id="petBox">
        <!-- 프로필사진영역 -->
        <div id="faceBox">
            <!-- 프로필사진 -->
            <img class="facePhoto" :src="`${petlist.img}`" id="profile" width="200" height="200">
        </div>

        <!-- 펫 정보 영역 -->
        <div id="inforBox">
            <div id="inforDiv">
                <!-- 펫 정보 목록-->
                <div class="inforTitle">
                    <div class="inforName">이름</div>
                    <div class="inforName">처음만난날</div>
                    <div class="inforName">나이</div>
                    <div class="inforName">종</div>
                    <div class="inforName">품종</div>
                    <div class="inforName">중성화 여부</div>
                </div>
                <!-- 펫 정보 상세 -->
                <div class="inforContent">
                    <div class="inforName">{{petlist.name}}</div>
                    <div class="inforName">{{petlist.firstday.substring(0,4)}} 년 {{petlist.firstday.substring(5,7)}} 월 {{petlist.firstday.substring(8,10)}} 일</div>
                    <div class="inforName">{{petlist.age}}살</div>
                    <div class="inforName">{{petlist.animal}}</div>
                    <div class="inforName">{{petlist.breed}}</div>
                    <div class="inforName">{{petlist.gender}}</div>
                </div>
            </div>
        </div>

        <div id="blank">
            <!-- 버튼이 정보 밑에 가게하기 위한 빈공간 -->
        </div>

        <!-- 수정 삭제 버튼 -->
        <div id="setBox">
            <router-link :to="`/petUpdate/${petlist.petnum}`">
                <button class="sujung" @click="update" v-if="a==1">
                    수정
                </button>
            </router-link>
            <button class="bye" @click="deletePet" v-if="a==1 || adminNum==4">
                다음에 봐
            </button>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                a: 0,
                date: '',
                adminNum: sessionStorage.getItem('user_num'),
            }
        },
        async created() {
            const pnum = await String(this.$route.params.public_unum)
            await this.$store.dispatch('petOneDetail', pnum);
            const pusernum = await this.$store.state.petlist.unum;
            const cuser = sessionStorage.getItem('user_num');
            if(pusernum==cuser) {
                this.a=1;
            }
            else
                this.a=0;
        },
        computed: {
            petlist() {
               // alert(JSON.stringify(this.$store.state.petlist));
                return this.$store.state.petlist;
            },
            async petfirstday() {
                var firstday = await this.$store.state.petlist.firstday;
                   console.log(firstday.substring(0,10));
                var year = await firstday.substring(0,4);
                var month = await firstday.substring(5,7);
                var day = await firstday.substring(8,10);
                this.date = await year+'년'+month+'월'+day+'일';
                return firstday.substring(0, 10);
            }

        },
        methods: {
            update() {
                // alert('수정페이지');
                // alert(this.$store.state.petlist.petnum);
                this.$router.push({
                    name: 'PetUpdate',
                    params: {
                        "petnum": this.$store.state.petlist.petnum
                    }
                });
            },
            async deletePet() {

                const petnum = String(this.$store.state.petlist.petnum)
                await this.$store.dispatch('delPet', petnum);
                await this.$router.push({name: 'Sameuserdetail',  
                    params: {
                        "public_unum": sessionStorage.getItem('user_num')
                    }
                });
            }
        }
    };
</script>
<style scoped src='./petDetail.css' />