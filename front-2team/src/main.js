import Vue from 'vue';
import App from './App.vue';
import store from './vuex/store';
import router from '@/routes/routes';
import InfiniteLoading from 'vue-infinite-loading';

Vue.config.productionTip = false;

new Vue({
  InfiniteLoading,
  router,
  store,
  render: h => h(App),
}).$mount('#app');
