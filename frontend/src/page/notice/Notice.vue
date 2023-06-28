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
            <v-card-title class="noticeTitleColor">공지게시판</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <BoardTable :boardList="boardList"/>
            </v-card-item>
        </v-card>
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
                @click="$router.push('/notice/write')"></v-btn>
        </v-col>
    </v-row>
</template>

<script setup>
import {onMounted, ref} from "vue";
import * as noticeBoardApi from '@/api/board/boardNotice'
import BoardTable from "@/components/board/BoardTable.vue";
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

//공지게시글 get
const noticeList = async () => {
    const response = await noticeBoardApi.getNoticeList();
    boardList.value = response.data.data.noticeList;
    pagination.value = response.data.data.pagination;
}

//검색조건 핸들러
const searchQueryHandler = async (object) => {
    const keyword = object.keyword;
    const startDate = object.startDate;
    const endDate = object.endDate;

    const response = await noticeBoardApi.getNoticeListWithQuery(keyword, startDate, endDate);
    boardList.value = response.data.data.noticeList;

    popupTriggers.value.buttonTrigger = false;
}

onMounted(() => {
    noticeList();
})
</script>

<style>
.noticeTitleColor {
    background-color: lightsteelblue
}
</style>
