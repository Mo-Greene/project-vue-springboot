<template>
    <v-card>
        <v-card-title class="noticeTitleColor">공지게시판</v-card-title>
        <v-divider class="border-opacity-75"></v-divider>
        <v-card>
            <BoardTable :boardList="boardList"/>
        </v-card>
    </v-card>
</template>

<script>
import {onMounted, ref} from "vue";
import * as noticeBoardApi from '@/api/boardNotice'
import BoardTable from "@/components/board/BoardTable.vue";

export default {
    name: "Notice",
    components: {BoardTable},
    setup() {
        const boardList = ref([])

        //공지게시글 get
        const noticeList = async () => {
            const response = await noticeBoardApi.getNoticeList();
            boardList.value = response.data.data
        }

        onMounted(() => {
            noticeList();
        })

        return {
            boardList
        }
    },
}
</script>

<style>
.noticeTitleColor {
    background-color: lightsteelblue
}
</style>
