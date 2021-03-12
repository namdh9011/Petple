import petdetail from '@/views/petDetail.vue';
import petcreate from '@/views/petCreate.vue';
import petupdate from '@/views/petUpdate.vue';

export default [
    {
        path: '/petdetail/:public_unum',
        name: 'Petdetail',
        component: petdetail,
    },
    {
        path: '/petadd/:public_unum', 
        name: 'PetAdd',
        component: petcreate,
    },
    {
        path: '/petupdate/:public_petNum',
        name: 'PetUpdate',
        component: petupdate,
    }
]