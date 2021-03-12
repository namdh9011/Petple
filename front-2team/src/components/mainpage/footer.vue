<template>
<div class="footer_contaier">
    <!-- <v-footer v-bind="localAttrs" :padless="padless">
        <v-card flat tile width="100%" color="red" class="red lighten-1 text-center">
            <v-card-text>
            <v-btn v-for="icon in icons" :key="icon" class="mx-4" icon>
                <v-icon size="24px">{{ icon }}</v-icon>
            </v-btn>
            </v-card-text>
        </v-card>
    </v-footer> -->
    <div class="go_to_main">
        <router-link v-bind:to="{name:'Mainpage'}">
            <img class="myPageImg" src="@/assets/mainpage/home.png" width="50" height="50">
        </router-link>
    </div>
     <div class="go_to_followfeed">
        <router-link v-bind:to="{name:'Mainpage2'}">
           <img class="myPageImgfollow" src="@/assets/mainpage/follow.png" width="70" height="70">
        </router-link>
    </div>
    <div class="go_to_postupload">
        <router-link :to="`/postupload`">
           <img class="myPageImg" src="@/assets/mainpage/upload.png" width="50" height="50">
        </router-link>
    </div>
    <div class="go_to_mypage">
        <router-link :to="`/same_user_detail/${this.user_num}`">
            <img class="myPageImg" src="@/images/mainpage/myPagePurple.png" width="50" height="50">
        </router-link>
    </div>

</div>
</template>

<script>
export default {
     data() {
        return {
            search_value : "",
            user_num: sessionStorage.getItem('user_num'),
        }
    },
    // methods: {
    //     submit : function() {
    //         const searchTerm = `${this.search_value}`;
    //         const url = API_URL + encodeURIComponent(searchTerm);
    //         this.$store.dispatch('SearchResult', url);
    //         this.$router.push({name: 'search'}).catch(err=>{})         
    //     },
    // }
    
    // data () {
    //     return {
    //          icons: [
    //             'mdi-home',
    //             'mdi-email',
    //             'mdi-calendar',
    //             'mdi-delete',
    //         ],
    //         padless: false,
    //         variant: 'default',
    //     }
    // },
    // computed: {
    //   localAttrs () {
    //     const attrs = {}

    //     if (this.variant === 'default') {
    //       attrs.absolute = false
    //       attrs.fixed = false
    //     } else {
    //       attrs[this.variant] = true
    //     }
    //     return attrs
    //   },
    // },
}
</script>

<style scoped>
    .footer_contaier {
        top: 710px;
        width:100%;
        height:70px;
        background-color: blue;
        position: sticky;
        bottom: 0%;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        font-family: 'Nanum Pen Script', cursive;
        font-size: 30px;
        background: -webkit-linear-gradient(to right, #bd93ce, #7e4792);
        background: linear-gradient(to right, #f1cde9, #d3abf3);
    }
    .myPageImgfollow{
        margin-top: 5%;
        margin-left: 20%;
    }
    .myPageImg {
        margin-left: 29%;
        margin-top: 10%;
    }
    .go_to_main_text{
        margin-left: 24%;
    }
    .go_to_mypage_text{
        margin-left: 12%;
    }

    .go_to_postupload_text{
        margin-left: 6%;
    }

    /* .go_to_postupload {
        background-color: red;
    }
    .go_to_mypage {
        background-color: yellow;
    }
    .go_to_main {
        background-color: green;
    } */
</style>