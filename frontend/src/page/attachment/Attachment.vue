<template>
    <v-container>
        <v-card>
            <v-card-title class="attachmentTitleColor">자료실</v-card-title>
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
                @click="$router.push('/attachment/write')"></v-btn>
        </v-col>
    </v-row>
</template>

<script>
import {onMounted, ref} from "vue";
import * as attachmentBoardApi from '@/api/board/boardAttachment'
import BoardTable from "@/components/board/BoardTable.vue";

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

<style>
.attachmentTitleColor {
    background-color: darkgoldenrod
}
</style>
