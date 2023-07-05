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

        <Pagination
            :pagination="pagination" @pageChange="pageChangeHandler"/>

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
                @click="$router.push('/notice/write')"
            ></v-btn>
        </v-col>
    </v-row>
</template>

<script setup>
import {onMounted, ref} from "vue";
import * as noticeBoardApi from '@/api/board/boardNotice'
import BoardTable from "@/components/board/BoardTable.vue";
import SearchModal from "@/components/modal/SearchModal.vue";
import Pagination from "@/components/layout/Pagination.vue";

const boardList = ref([])
const pagination = ref([])
const params = ref();
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
    params.keyword = object.keyword;
    params.startDate = object.startDate;
    params.endDate = object.endDate;

    const response = await noticeBoardApi.getNoticeListWithQuery(params);
    boardList.value = response.data.data.noticeList;
    pagination.value = response.data.data.pagination;

    popupTriggers.value.buttonTrigger = false;
}

//페이지처리 핸들러
const pageChangeHandler = async (event) => {
    params.page = event.page;

    const response = await noticeBoardApi.getNoticeListWithQuery(params);
    boardList.value = response.data.data.noticeList;
    pagination.value = response.data.data.pagination;
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
