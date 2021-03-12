import welcome from '@/views/welcomePage.vue';
import welcome2 from '@/views/welcomePage2.vue';
import welcome3 from '@/views/welcomePage3.vue';
import findpass from '@/views/findpass.vue';

export default [
    {
        path: '/',
        name: 'welcome',
        component: welcome
    },
    {
        path: '/sign',
        name: 'welcome2',
        component: welcome2
    },
    {
        path: '/auth',
        name: 'auth',
        component: welcome3
    },
    {
        path:'findpass',
        name: 'findpass',
        component: findpass
    }

]