<template>
    <BoardTable :boardList="boardList"/>
</template>

<script>
import {onMounted, ref} from "vue";
import * as noticeBoardApi from '@/api/boardNotice'
import BoardTable from "@/components/BoardTable.vue";

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
