<template>
 <div class="tab-component" :class="{active : isActive}">
        {{tabTitle}}
        <span v-if="count" class="count">
            {{count}}
        </span>
    </div>
</template>

<script>
    import './mainpageTab.css';

    export default {
        name: "tab",
        props : ['최신피드', '팔로워 피드'],
    }
</script>