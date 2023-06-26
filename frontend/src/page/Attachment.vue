<template>
    <BoardTable :boardList="boardList"/>
</template>

<script>
import {onMounted, ref} from "vue";
import * as attachmentBoardApi from '@/api/boardAttachment'
import BoardTable from "@/components/BoardTable.vue";

export default {
    name: "Attachment",
    components: {BoardTable},
    setup() {
        const boardList = ref([])

        //자료실 get
        const attachmentList = async () => {
            const response = await attachmentBoardApi.getAttachmentList();
            boardList.value = response.data.data
        }

        onMounted(() => {
            attachmentList();
        })

        return {
            boardList
        }
    },
}
</script>
