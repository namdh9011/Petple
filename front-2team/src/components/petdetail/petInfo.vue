<template>
    <!-- 전체 wrap -->
    <div id="petAddBox">

        <!-- 사진 업로드 -->
        <div id="faceBox">
            <div id="photo">
                <div class="post_image_upload">
                    <input type="text" class="form-control" placeholder="클립을 눌러 이미지 선택" v-model="PostImage"
                        @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
                    <div class="input-group-append">
                        <span class="input-group-text" @click="onClickFile">
                            <i class="fa fa-paperclip"></i> 클립을 눌러 사진을 등록하세요
                        </span>
                        <input type=file class="file-input" style="display:none" accept="image/*" ref="fileInput"
                            @change="onFileChange">
                        <div v-show="imageSrc" class="upload-image">
                            <img :src="imageSrc" class="facePhoto">
                        </div>
                    </div>
                </div>
            </div>
            <div id="photoChange">
                <div></div>
            </div>
        </div>

        <!-- 제목, 내용 등등 -->
        <div id="inforBox">
            <div class="inforTitle">
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="이름 ex) 펫플이" v-model="petName">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="나이  ex) 1살 -> 1" v-model="petAge">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="처음만난날  ex) 2020-01-01" v-model="petMeet">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox" placeholder="종 ex) 고양이 , 강아지 등등" v-model="petDiv">
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox dogInfor" placeholder="품종 ex) 웰시코기, 스코티쉬 폴드 등" v-model="petDivDt">
                </div>
                <div class="userInforGroup3">
                    <label class="infoContentBox3">중성화여부 </label>
                </div>
                <div class="userInforGroup2">
                    <input type="radio" value="암컷" v-model="sex" class="inforContentBox2">
                    <label class="selectBox">암컷 </label>
                    <input type="radio" value="수컷" v-model="sex" class="inforContentBox2">
                    <label class="selectBox">수컷 </label>
                    <input type="radio" value="암컷(중성화)" v-model="sex" class="inforContentBox2">
                    <label class="selectBox">암컷(중) </label>
                    <input type="radio" value="수컷(중성화)" v-model="sex" class="inforContentBox2">
                    <label class="selectBox">수컷(중) </label>
                    <br>
                </div>
                <div class="userInforGroup">
                    <input type="text" class="inforContentBox introInfor" placeholder="소개 ex) 안녕하세요~" v-model="intro">
                </div>
            </div>
        </div>

        <div id="blank"></div>

        <!-- 탈퇴 수정 버튼 -->
        <div id="setBox">
            <button class="sujung" @click="startUpload">
                추가
            </button>
            <button class="bye">
                취소
            </button>
        </div>

    </div>
</template>

<script>
    /*
  사진 데이터 제외한 것들 먼저 upload하고
  사진 데이터를 저장해보기.
*/
    export default {
        data: () => ({
            PostImage: '', //이미지 선택
            petName: '', //이름
            petAge: '', //나이
            petMeet: '', //처음 만난날
            petDiv: '', //종
            petDivDt: '', //품종
            sex: '', //성별
            imageSrc: '', //이미지 경로
            intro: '',
            PostPhoto: { //?
            },
        }),
        methods: {
            onDrop(event) {
                this.inputImageFile(event.dataTransfer.files)
            },
            onClickFile(event) {
                this.$refs.fileInput.click()
            },
            onFileChange(event) {
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

                if (this.PostImage == false || this.petName == false || this.petAge == false || this.petMeet ==
                    false || this.petDiv == false || this.petDivDt == false || this.sex == false) {
                    alert('모든 내용을 입력하고 시도해주세요.');
                    return;
                }

               // alert(this.PostImage)

                let data = {
                    'unum': sessionStorage.getItem('user_num'),
                    'name': this.petName,
                    'animal': this.petDiv,
                    'gender': this.sex,
                    'breed': this.petDivDt,
                    'age': this.petAge,
                    'firstday': this.petMeet,
                    'img': this.PostImage,
                    'intro': this.intro
                }

                const formData = new FormData();

                formData.append('file', this.PostPhoto);
                await this.$store.dispatch('addPet', data); // 게시물 업로드
            //    alert(data.unum);
                await this.$store.dispatch('addPetImage', formData);
                this.$router.push({
                    name: 'Sameuserdetail'
                });

            }
        }
    };
</script>
<style scoped src='./petInfo.css' />