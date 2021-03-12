<template>
  <!-- 전체 wrap -->
  <div id="uploadBox">

    <!-- 사진 업로드 -->
    <div id="photoBox">
      <div id="photo">
        <div class="post_image_upload">
          <input type="text" class="form-control" placeholder="클립을 눌러 이미지 선택" v-model="PostImage" @dragover.prevent @dragenter.prevent @drop.prevent="onDrop">
          <div class="input-group-append">
            <span class="input-group-text" @click="onClickFile">
              <i class="fa fa-paperclip"></i>
            </span>
            <input type=file class="file-input" accept="image/*" ref="fileInput" @change="onFileChange">
            <div v-show="imageSrc" class="upload-image">
              <img :src="imageSrc">
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 제목, 내용 등등 -->
    <div id="textBox">

      <div id="title">
        <label for="postTitle"></label>
        <input type="text" id="postTitle" v-model="PostTitle" placeholder="제목">
      </div>

      <div id="content">
        <label for="postContent"></label>
        <input type="text" id="postContent" v-model="PostContent" placeholder="내용">
      </div>

      <div id="hashtags">
        <label for="hashtag"></label>
        <input type="text" id="hashtag" v-model="PostTag" placeholder="해시태그">
      </div>

      <div id="public">
        <input type="radio" value="all" v-model="PostPrivacy">전체공개
        <input type="radio" value="friend" v-model="PostPrivacy">친구공개
        <input type="radio" value="private" v-model="PostPrivacy">비공개
      </div>

      <div id="upload">
        <button><img class="saveButton" src="../../assets/postupload/pawPurple2.png" @click="startUpload" style="cursor:pointer"></button>
      </div>

    </div>

  </div>
</template>

<script>
/*
  사진 데이터 제외한 것들 먼저 upload하고
  사진 데이터를 저장해보기.
*/
export default {
  data() {
    return {
    PostImage: this.$store.state.onepostlist.img,
    PostTitle: this.$store.state.onepostlist.title,
    PostContent: this.$store.state.onepostlist.contents,
    PostTag: this.$store.state.onepostlist.tags,
    PostPrivacy: this.$store.state.onepostlist.privacy,
    imageSrc: this.$store.state.onepostlist.img,
    PostPhoto: {

    },
  }
  },
  created() {
      this.$store.dispatch('detailPostFeed', this.$route.params.postnum);
      

     //alert(JSON.stringify(this.$store.state.onepostlist));
  },
  computed: {
    postlist() {
      return this.$store.state.onepostlist;
      // this.PostImage = ,
      // this.PostTitle =,
      // this.PostContent = ,
      // this.PostTag = ,
      // this.PostPrivacy = ,
      // this.imageSrc = ,
    }
  },
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
            // console.log("인풋이미지파일"+this.PostPhoto);

            // if (!/^image\/.test(file.type)) {
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
    async startUpload () {
      // console.log(this.PostImage);
      

      let data = {
        //'postImage' : this.PostPhoto,      
        'unum' : sessionStorage.getItem('user_num'),
        'title' : this.PostTitle,
        'img' :  this.PostImage,
        'contents' : this.PostContent,
        'tags' :  this.PostTag,
        'privacy' : this.PostPrivacy,
        'pnum' : this.$route.params.postnum,
      }
      // alert("현재 데이터"+JSON.stringify(data));
      const formData = new FormData();
      
      formData.append('file', this.PostPhoto)      
      await this.$store.dispatch('uptPost', data); // 게시물 업로드
      
      // console.log(JSON.stringify(this.PostPhoto)+"파일 올리기 전 확인")

      this.$store.dispatch('InsertPostImage', formData);//게시물 사진 DB에 넣기
      // console.log(this.$route.params.postnum+"글 업데이트 완료"+formData);
      
      // console.log(sessionStorage.getItem('user_num')+'을 받았습니다.');
      
      this.$router.push({name: 'Mainpage'});
    },
    
    // FileSelect() {
    //     this.$store.dispatch('InsertPost', data);
    // }
  }
};
</script>
<style scoped src='../postupload/postUpload.css' />