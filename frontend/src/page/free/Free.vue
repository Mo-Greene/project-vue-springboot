<template>
    <v-card>
        <v-card-title class="freeTitleColor">자유게시판</v-card-title>
        <v-divider class="border-opacity-75"></v-divider>
        <v-card-item>
            <BoardTable :boardList="boardList"/>
        </v-card-item>
    </v-card>
</template>

<script>
import BoardTable from "@/components/board/BoardTable.vue";
import {onMounted, ref} from "vue";
import * as freeBoardApi from '@/api/boardFree'

export default {
    name: "Free",
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

<style>
.freeTitleColor {
    background-color: bisque;
}
</style>
