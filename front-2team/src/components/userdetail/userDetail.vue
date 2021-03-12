<template>
    <!-- 전체 wrap -->
    <div id="userBox">

        <!-- 프로필사진영역 -->
        <div id="faceBox">
            <div id="userId"></div>
            <!-- 프사 -->
            <!-- <input type="text" class="form-control" placeholder="클립을 눌러 이미지 선택" v-model="PostImage"
                        @dragover.prevent @dragenter.prevent @drop.prevent="onDrop"> -->
                
            <div class="facebox">
                <span class="input-group-text" @click="onClickFile">
                    <i class="fa fa-paperclip"></i>  클립을 눌러 사진을 등록하세요
                </span>
                <input type=file class="file-input" accept="image/*" ref="fileInput" @change="onFileChange">
                <div v-show="imageSrc2" class="upload-image" v-if="aaa===1">
                    <img :src="imageSrc2" class="facePhoto">
                </div>
                <div v-show="imageSrc" class="upload-image" v-else>
                    <img :src="imageSrc" class="facePhoto">
                </div>
            </div>
        </div>

        <!-- 유저 정보 영역 -->
        <div id="inforBox">
            <div class="inforContent">
                <!-- <div class="inforCon"> -->
                <div class="userInforGroup">
                    <label class="conText" for="userName">닉네임</label>
                    <input class="inforContentBox" placeholder="유저의 닉네임" v-model="nickname">
                    <br>
                </div>
                <div class="userInforGroup">
                    <label class="conText" for="userEmail">이름</label>
                    <input class="inforContentBox" placeholder="유저의 이름" v-model="name">
                    <br>
                </div>
                <div class="userInforGroup">
                    <label class="conText" for="userEmail">이메일</label>
                    <input class="inforContentBox" placeholder="유저의 이메일" v-model="email">
                    <br>
                </div>

                <div class="userInforGroup">
                    <label class="conText" for="userPhoneNum">폰번호</label>
                    <input class="inforContentBox" placeholder="유저의 핸드폰번호" v-model="phone">
                </div>
                
                <br>
                <div class="conText1" style="text-align: center">
                    <label class="userInforGroup" for="userLikeAnimal">관심동물</label>
                    <br>
                    <br>
                    <div class="conText" v-for="(item, index) in interest" :key="index"  >
                        <div  class="conText" v-if="index%3==2">
                            <input class="inforContentBox" type="checkbox" v-model="item.checked">  
                            <label for="checkbox"> {{item.pet}} </label>
                            <br>
                        </div>
                        <div class="conText" v-else>
                        <input class="inforContentBox" type="checkbox" v-model="item.checked">  
                            <label for="checkbox"> {{item.pet}} </label>
                        </div>
            
                    </div>
                </div>
                
                <!-- </div> -->
            </div>

        </div>

        <div id="blank">
        </div>

        <!-- 탈퇴 수정 버튼 -->
        <div id="setBox">
            <button class="bye" @click="confirm">
                탈퇴하기
            </button>
            <button class="sujung" @click="startUpload">
                수정
            </button>
        </div>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                aaa : 1,
                imageSrc: '',
                name: this.$store.state.onesameuserlist.name,
                imageSrc2: this.$store.state.onesameuserlist.userprofile,
                nickname: this.$store.state.onesameuserlist.nickname,
                email: this.$store.state.onesameuserlist.email,
                unum: '',
                cpw: '',
                npw: '',
                npw1: '',
                interest:[
                    {pet:'강아지', checked: false},
                    {pet:'고양이', checked: false},
                    {pet:'햄스터', checked: false},
                    {pet:'돼지', checked: false},
                    {pet:'새', checked: false},
                    {pet:'고슴도치', checked: false},
                    {pet:'거미', checked: false},
                    {pet:'거북이', checked: false},
                    {pet:'물고기', checked: false},
                ],
                //this.$store.state.onesameuserlist.interest,
                phone: this.$store.state.onesameuserlist.phone,
                PostPhoto: {
                    
                }
            }
        },
        async created() {
            const public_unum = sessionStorage.getItem('user_num');
            var obj = this.$store.state.onesameuserlist.interest.split(' ');
            for(var i in obj) {
                for(var j=0; j<this.interest.length; j++) {
                    if(this.interest[j].pet==obj[i]) {
                        this.interest[j].checked = true;
                    }
                }
            }
            //alert(public_unum);
            await this.$store.dispatch('detailSameUser', public_unum);
        },
        computed: {
            userInfos() {
                // alert(JSON.stringify(this.$store.state.onesameuserlist))
                
                return this.$store.state.onesameuserlist;
            }
        },
        methods: {
            onDrop(event) {
                // console.log('onDrop     '+event.target.files[0]);
                this.inputImageFile(event.dataTransfer.files)
            },
            onClickFile(event) {
                this.$refs.fileInput.click()
            },
            onFileChange(event) {
                // console.log('onFileChange     '+event.target.files[0]);
                this.aaa = 12;
                this.inputImageFile(event.target.files)
            },
            inputImageFile(files) {
                if (files.length) {
                    let file = files[0]
                    this.PostPhoto = file;
                    
                    // console.log(this.PostPhoto);

                    // if (!/^image\//.test(file.type)) {
                    //     alert('이미지 파일만 등록이 가능합니다')
                    //     return false
                    // }
                    this.PostImage = file.name
                    this.preview(file)
                }
            },

            onClickUpload() {
                this.preview(this.filename)
            },
            preview(file) {
                if (typeof file === 'string') {
                    this.imageSrc = file
                } else {
                    let vm = this
                    let reader = new FileReader()
                    reader.onload = () => {
                        vm.imageSrc = reader.result
                    }
                    reader.readAsDataURL(file)
                }
            },
            async confirm() {
                const public_unum = sessionStorage.getItem('user_num');
                // alert(public_unum)
                const msg = "탈퇴하시겠습니까?";
                if (confirm(msg)!=0) {
                    const public_unum = sessionStorage.getItem('user_num');
                    await this.$store.dispatch('delUser',public_unum);
                    this.$router.push({name:'/'});
                } else {
                    // no click
                }
            },
             async startUpload() {
                

                if (this.petName == false || this.petAge == false || this.petMeet == false || this.petDiv == false || this.petDivDt == false || this.sex == false) {
                    alert('모든 내용을 입력하고 시도해주세요.');
                }
                
                let temp = "";
                for(var i=0; i<this.interest.length; i++) {
                    if(this.interest[i].checked==true)
                        temp = temp+this.interest[i].pet+" ";
                }

                //alert(temp);
                let data = {   
                    'email': this.email,
                    'interest' : temp,
                    'name' : this.name,
                    'nickname': this.nickname,
                    'password' : this.npw,
                    'unum': sessionStorage.getItem('user_num'),
                    'phone' : this.phone,
                    'userprofile' : this.imageSrc,                    
                }
                
                const formData = new FormData();
                formData.append('file', this.PostPhoto);
                await this.$store.dispatch('uptUser', data); // 게시물 업로드
                let unum = sessionStorage.getItem('user_num');
                await this.$store.dispatch('addUserImage',formData);
                this.$router.push({name: 'Mainpage'});
                
            }
        }

    };
</script>
<style scoped src='./userDetail.css' />
