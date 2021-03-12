<template>
  
</template>

<script>
export default {
    async created() {
        // var link = document.location.href;
        const para = document.location.href.split("=");
        // const para = para.split("%40");
        console.log(para[1]);
        const pass = '1q2w3e4r';
        let data = {
            'email' : decodeURIComponent(para[1]),
            'password' : pass
        }

        await this.$store.dispatch('getToken', data).then( ()=>{
            alert('로그인 성공')
            this.$router.push({name: 'Mainpage'}) ;
        })
    }
}
</script>

<style>

</style>