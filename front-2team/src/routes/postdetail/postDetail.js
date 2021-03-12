import postdetail from '@/views/postDetail.vue';
import postupdate from '@/views/postUpdate.vue'

export default [
    {
        path: '/postdetail/:pnum',
        name: 'Postdetail',
        component: postdetail,
    },
    {
        path: '/postupdate/:pnum',
        name: 'Postupdate',
        component: postupdate,
    }
]