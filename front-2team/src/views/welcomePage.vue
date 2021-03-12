<template>
    <div class="adbody">
        <div class="wrapper fadeInDown">
            <div class="form">
                <Header></Header>
                <Section></Section>
                <Footer></Footer>
            </div>
        </div>
    </div>
</template>

<script>
import Header from "@/components/member/Header.vue";
import Section from "@/components/member/Login1.vue";
import Footer from "@/components/member/Footer.vue";
export default {    
    components: {
        Header,
        Section,
        Footer
    }
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    width: 100%;
    font-family: 'Trebuchet MS', sans-serif;
}

li {
    list-style: none;
}

a {
    text-decoration: none;
}


.adbody {
    /* 투명도 */
    opacity: 0.9;

    /* 위치 설정 */
    position: absolute;
    top: 50%; left: 50%;
    margin-left: -480px;
    margin-top: -275px;
    text-align: center;
    /* 크기 설정 */
    width: 960px;
    height: 550px;
    /* 이미지 설정 */
    /* background: url('images/Center_Background.png'); */

}

.wrapper {
  display: -webkit-inline-flex;
  position: relative;
  text-align: center;
  flex-direction: column; 
  justify-content: center;
  max-width: 450px;
  min-height: 100%;
  padding: 20px;
  background: #fff;
  border-radius: 0px 10px 10px 10px;
  -webkit-border-radius: 10px 10px 10px 10px;
}

#form {
  padding: 0px;
  width: 100%;
  max-width: 450px;
  position: relative;
  text-align: center;
}


/* ANIMATIONS */

/* Simple CSS3 Fade-in-down Animation */
.fadeInDown {
  -webkit-animation-name: fadeInDown;
  animation-name: fadeInDown;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

@-webkit-keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

/* Simple CSS3 Fade-in Animation */
@-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

.fadeIn {
  opacity:0;
  -webkit-animation:fadeIn ease-in 1;
  -moz-animation:fadeIn ease-in 1;
  animation:fadeIn ease-in 1;

  -webkit-animation-fill-mode:forwards;
  -moz-animation-fill-mode:forwards;
  animation-fill-mode:forwards;

  -webkit-animation-duration:1s;
  -moz-animation-duration:1s;
  animation-duration:1s;
}




/* Simple CSS3 Fade-in Animation */
.underlineHover:after {
  display: block;
  left: 0;
  bottom: -10px;
  width: auto;
  height: 2px;
  background-color: #56baed;
  content: "";
  transition: width 0.2s;
}

.underlineHover:hover {
  color: #0d0d0d;
}

.underlineHover:hover:after{
  width: 100%;
}

@media (max-width: 375px) {
        * {
    margin: 0;
    padding: 0;
    width: 100%;
    font-family: 'Trebuchet MS', sans-serif;
}

li {
    list-style: none;
}

a {
    text-decoration: none;
}


.adbody {
    /* 투명도 */
    opacity: 0.9;

    /* 위치 설정 */
    position: absolute;
    top: 50%; left: 50%;
    margin-left: -187.5px;
    margin-top: -406px;
    text-align: center;
    /* 크기 설정 */
    width: 375px;
    height: 812px;
    /* 이미지 설정 */
    /* background: url('images/Center_Background.png'); */

}

.wrapper {
  display: -webkit-inline-flex;
  position: relative;
  text-align: center;
  flex-direction: column; 
  justify-content: center;
  max-width: 375px;
  min-height: 100%;
  padding: 20px;
  background: #fff;
  border-radius: 0px 10px 10px 10px;
  -webkit-border-radius: 10px 10px 10px 10px;
}

#form {
  padding: 0px;
  width: 100%;
  max-width: 450px;
  position: relative;
  text-align: center;
}


/* ANIMATIONS */

/* Simple CSS3 Fade-in-down Animation */
.fadeInDown {
  -webkit-animation-name: fadeInDown;
  animation-name: fadeInDown;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

@-webkit-keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

/* Simple CSS3 Fade-in Animation */
@-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

.fadeIn {
  opacity:0;
  -webkit-animation:fadeIn ease-in 1;
  -moz-animation:fadeIn ease-in 1;
  animation:fadeIn ease-in 1;

  -webkit-animation-fill-mode:forwards;
  -moz-animation-fill-mode:forwards;
  animation-fill-mode:forwards;

  -webkit-animation-duration:1s;
  -moz-animation-duration:1s;
  animation-duration:1s;
}

/* Simple CSS3 Fade-in Animation */
.underlineHover:after {
  display: block;
  left: 0;
  bottom: -10px;
  width: auto;
  height: 2px;
  background-color: #56baed;
  content: "";
  transition: width 0.2s;
}

.underlineHover:hover {
  color: #0d0d0d;
}

.underlineHover:hover:after{
  width: 100%;
}
}

</style>