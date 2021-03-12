<template>  
<div class=tab >
            <login/>                            
            <signup/>
</div>
</template>

<script>
  import login from '@/components/member/login.vue';
  import signup from '@/components/member/signUp.vue';
  export default {
    components: {
      login,
      signup
    }
  };
</script>
