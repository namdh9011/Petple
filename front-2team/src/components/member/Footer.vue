<template>
    <div class="formFooter">
        <div>
        <div class="garo">
            <input type="button" style="font-family: 'Poor Story', cursive;" v-on:click="signup" class="fadeIn fifth" value="회원가입" />
        </div>

        <div class="garo">
            <input type="button" style="font-family: 'Poor Story', cursive;" @click="findpass" class="fadeIn sixth" value="비밀번호 찾기" />
        </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        signup() {
            this.$router.push({name: 'welcome2'}) ;
        },
        findpass() {
            this.$router.push({name: 'findpass'});
        }
    }
}
</script>

<style scoped>
    .formFooter {
        display: -webkit-inline-flex;
        position: relative;
        align-items: center;
        flex-direction: column; 
        justify-content: center;
        max-width: 500px;
        padding: 0px;
        background: #fff;
        border-radius: 0px 10px 10px 10px;
        -webkit-border-radius: 10px 10px 10px 10px;
    }

    .garo {
        float: left;
        text-align: center;
        margin: 10px;
    }

    input[type=button] {
        cursor: pointer;
        background-color: #56baed;
        border: none;
        color: white;
        padding: 20px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        text-transform: uppercase;
        font-size: 13px;
        width: 110px;
        -webkit-box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
        box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
        -webkit-border-radius: 5px 5px 5px 5px; 
        border-radius: 5px 5px 5px 5px;
        margin: 5px;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        -ms-transition: all 0.3s ease-in-out;
        -o-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out;
    }

    input[type=button]:hover {
        background-color: white !important;
        color: black !important;
    }

    input[type=button]:active {
        -moz-transform: scale(0.95);
        -webkit-transform: scale(0.95);
        -o-transform: scale(0.95);
        -ms-transform: scale(0.95);
        transform: scale(0.95);
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

    .fadeIn.fifth {
  -webkit-animation-delay: 1.2s;
  -moz-animation-delay: 1.2s;
  animation-delay: 1.2s;
}

    .fadeIn.sixth {
  -webkit-animation-delay: 1.5s;
  -moz-animation-delay: 1.5;
  animation-delay: 1.5s;
}
</style>