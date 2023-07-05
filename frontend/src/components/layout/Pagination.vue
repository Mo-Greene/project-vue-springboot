<template>
    <div class="text-center">
        <v-pagination
            size="small"
            v-model="page"
            :total-visible="5"
            :length="totalPage"
            rounded="circle"
            @click="pagingHandler(page)"
        ></v-pagination>
    </div>
</template>

<script setup>

import {computed, ref} from "vue";

const props = defineProps({
    pagination: {
        page: Number,
        size: Number,
        startPage: Number,
        endPage: Number,
        total: Number
    }
})

const emit = defineEmits(['pageChange'])

const page = ref(props.pagination.page);

const totalPage = computed(() => props.pagination.endPage)

//페이징 핸들러
const pagingHandler = (page) => {

    emit('pageChange', { page })
}
</script>
