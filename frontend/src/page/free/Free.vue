<template>
    <main>
        <SearchModal
            v-if="popupTriggers.buttonTrigger"
            :togglePopup="() => togglePopup('buttonTrigger')"
            @searchQuery="searchQueryHandler"
        />
    </main>
    <v-container>
        <v-card>
            <v-card-title class="freeTitleColor">자유게시판</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <BoardTable :boardList="boardList"/>
            </v-card-item>
        </v-card>
        <Pagination
            :pagination="pagination"/>
    </v-container>
    <v-row justify="end">
        <v-col cols="2" offset="2">
            <v-btn
                icon="mdi-magnify"
                class="mr-2"
                @click="() => togglePopup('buttonTrigger')"
            ></v-btn>
            <v-btn
                icon="mdi-pencil"
                color="success"
                @click="$router.push('/free/write')"
            ></v-btn>
        </v-col>
    </v-row>
</template>

<script setup>
import BoardTable from "@/components/board/BoardTable.vue";
import {onMounted, ref} from "vue";
import * as freeBoardApi from '@/api/board/boardFree'
import Pagination from "@/components/layout/Pagination.vue";
import SearchModal from "@/components/modal/SearchModal.vue";

const boardList = ref([])
const pagination = ref([])
const page = ref();
const popupTriggers = ref({
    buttonTrigger: false,
})

//팝업 모듈
const togglePopup = (trigger) => {
    popupTriggers.value[trigger] = !popupTriggers.value[trigger]
}

//자유게시글 get
const freeList = async () => {
    const response = await freeBoardApi.getFreeList();
    boardList.value = response.data.data.freeList
    pagination.value = response.data.data.pagination
}

//검색조건 핸들러
const searchQueryHandler = async (object) => {
    const keyword = object.keyword;
    const startDate = object.startDate;
    const endDate = object.endDate;

    const response = await freeBoardApi.getFreeListWithQuery(keyword, startDate, endDate);
    boardList.value = response.data.data.freeList;

    popupTriggers.value.buttonTrigger = false;
}

onMounted(() => {
    freeList();
})

</script>

<style>
.freeTitleColor {
    background-color: bisque;
}
</style>
