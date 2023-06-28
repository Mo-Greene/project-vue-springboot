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
            <v-card-title class="attachmentTitleColor">자료실</v-card-title>
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
                @click="$router.push('/attachment/write')"></v-btn>
        </v-col>
    </v-row>
</template>

<script setup>
import {onMounted, ref} from "vue";
import * as attachmentBoardApi from '@/api/board/boardAttachment'
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

//자료실 get
const attachmentList = async () => {
    const response = await attachmentBoardApi.getAttachmentList();
    boardList.value = response.data.data.attachmentList;
    pagination.value = response.data.data.pagination;
}

//검색조건 핸들러
const searchQueryHandler = async (object) => {
    const keyword = object.keyword;
    const startDate = object.startDate;
    const endDate = object.endDate;

    const response = await attachmentBoardApi.getAttachmentListWithQuery(keyword, startDate, endDate);
    boardList.value = response.data.data.attachmentList;

    popupTriggers.value.buttonTrigger = false;
}

onMounted(() => {
    attachmentList();
})
</script>

<style>
.attachmentTitleColor {
    background-color: darkgoldenrod
}
</style>
