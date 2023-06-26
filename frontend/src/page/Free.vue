<template>
    <BoardTable :boardList="boardList"/>
</template>

<script>
import BoardTable from "@/components/BoardTable.vue";
import {onMounted, ref} from "vue";
import * as freeBoardApi from '@/api/boardFree'

export default {
    components: {BoardTable},
    setup() {
        const boardList = ref([])

        //자유게시글 get
        const freeList = async () => {
            const response = await freeBoardApi.getFreeList();
            boardList.value = response.data.data
        }

        onMounted(() => {
            freeList();
        })

        return {
            boardList,
        }
    }
}
</script>
