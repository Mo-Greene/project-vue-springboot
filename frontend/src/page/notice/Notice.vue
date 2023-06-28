<template>
    <v-container>
        <v-card>
            <v-card-title class="noticeTitleColor">공지게시판</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card>
                <BoardTable :boardList="boardList"/>
            </v-card>
        </v-card>
    </v-container>
    <v-row justify="end">
        <v-col cols="2" offset="2">
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

const boardList = ref([])
const pagination = ref([])

//공지게시글 get
const noticeList = async () => {
    const response = await noticeBoardApi.getNoticeList();
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
