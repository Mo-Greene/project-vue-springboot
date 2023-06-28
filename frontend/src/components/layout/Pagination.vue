<template>
    <div class="text-center">
        <v-pagination
            v-model="page"
            :length="endPage"
            :total-visible="5"
            rounded="circle"
        ></v-pagination>
    </div>
</template>

<script setup>

import {computed, ref} from "vue";

const props = defineProps(['pagination'])

//props
//현재 페이지
const page = ref(props.pagination.page);
//총 페이지
const endPage = computed(() => {
    props.pagination.endPage
});

</script>
