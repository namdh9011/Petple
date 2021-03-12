<template>
    <!-- 전체 wrap -->
    <div id="petUpdateBox">

        <!-- 사진 업로드 -->
        <div id="faceBox">
            <div id="photo">
                <div class="post_image_upload">
                    <input type="text" class="form-control" placeholder="클립을 눌러 이미지 선택" @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
                    <div class="input-group-append">

                        <span class="input-group-text" @click="onClickFile">
                            <i class="fa fa-paperclip"></i> 클립을 눌러 사진을 등록하세요
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
            </div>
        </div>

        <!-- 제목, 내용 등등 -->
        <div id="inforBox">
            <div class="inforTitle">
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="이름" v-model="petName" >
                </div>                
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="나이" v-model="petAge">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="처음만난날" v-model="petMeet">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="종" v-model="petDiv">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox dogInfor" placeholder="품종" v-model="petDivDt">
                </div>
                <div class="userInforGroup3">
                    <label class="infoContentBox3">중성화여부 </label>
                </div>
                <div class="userInforGroup2">
                    <input type="radio" value="암컷"  v-model="test"  class="inforContentBox2">
                    <label class="selectBox">암컷 </label>
                    <input type="radio" value="수컷" v-model="test" class="inforContentBox2">
                    <label class="selectBox">수컷 </label>
                    <input type="radio" value="암컷(중)" v-model="test" class="inforContentBox2">
                    <label class="selectBox">암컷(중) </label>
                    <input type="radio"  value="수컷(중)" v-model="test" class="inforContentBox2">
                    <label class="selectBox">수컷(중) </label>
                    <br>
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox IntroInfor" placeholder="소개" v-model="intro">
                </div>
                <!-- 탈퇴 수정 버튼 -->
                <div id="setBox">
                    <button class="sujung"  @click="startUpload">
                        수정
                    </button>
                    <button  class="bye">
                        취소
                    </button>
                </div>
            </div>
        </div>

        

    </div>
</template>

<script>
    export default {
        data() {
            return {
                aaa : 1,
                imageSrc: '',
                imageSrc2: this.$store.state.petlist.img,
                petName: this.$store.state.petlist.name,
                test: this.$store.state.petlist.gender,
                petDivDt: this.$store.state.petlist.breed,
                petAge: this.$store.state.petlist.age,
                petMeet:this.$store.state.petlist.firstday,
                PostImage: this.$store.state.petlist.img,
                intro: this.$store.state.petlist.intro,
                petDiv:this.$store.state.petlist.animal,
                unum: sessionStorage.getItem('user_num'),
                PostPhoto: {

                },
            }
        },
        methods: {
            onDrop(event) {
                console.log('onDrop     '+event.target.files[0]);
                this.inputImageFile(event.dataTransfer.files)
            },
            onClickFile(event) {
                this.$refs.fileInput.click()
            },
            onFileChange(event) {
                console.log('onFileChange     '+event.target.files[0]);
                this.aaa = 12;
                this.inputImageFile(event.target.files)
            },
            inputImageFile(files) {
                if (files.length) {
                    let file = files[0]
                    this.PostPhoto = file;
                    
                    console.log(this.PostPhoto);

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
            async startUpload() {

                if (this.petName == false || this.petAge == false || this.petMeet == false || this.petDiv == false || this.petDivDt == false || this.sex == false) {
                    alert('모든 내용을 입력하고 시도해주세요.');
                }
                
                let data = {   
                    'petnum' : this.$store.state.petlist.petnum,
                    'unum': sessionStorage.getItem('user_num'),
                    'name': this.petName,
                    'animal': this.petDiv,
                    'gender': this.test,
                    'breed': this.petDivDt,
                    'age' : this.petAge,
                    'firstday': this.petMeet,
                    'img': this.PostImage,
                    'intro' : this.intro,
                }

                const formData = new FormData();
                formData.append('file', this.PostPhoto);
                await this.$store.dispatch('uptPet', data); // 게시물 업로드

                await this.$store.dispatch('addPetImage', formData);
                alert("사진 올리기 완료"+this.unum)
                
                this.$router.push(`/same_user_detail/${this.unum}`);
                
            }
        },
        created() {
            //alert("생성")
            alert(JSON.stringify(this.$store.state.petlist));
            //const pnum = String(this.$route.params.pnum)
            this.$store.dispatch('petOneDetail', this.$store.state.petlist.petnum);
            //alert(this.$route.params.petnum)
            //this.petName = petOneDetail.petName;
            
        },
    };
</script>
<style scoped src='./petDetailUpdate.css' />