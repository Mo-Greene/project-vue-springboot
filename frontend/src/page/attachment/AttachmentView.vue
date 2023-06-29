<template>
    <v-container>
        <v-card>
            <v-card-title class="attachmentTitleColor">자료실 - 상세보기</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <CheckModal
                    v-if="showPopup"
                    :showPopup="showPopup"
                    :modalType="modalType"
                    @confirm="confirmHandler"
                    @cancel="cancelHandler"/>

                <BoardCard :boardArticle="attachmentArticle"/>

                <v-col
                    cols="auto"
                    class="text-right"
                >
                    <v-btn
                        icon="mdi-pencil-plus"
                        class="mr-2"
                        color="blue"
                        @click="modifyArticleConfirm"
                    ></v-btn>
                    <v-btn
                        icon="mdi-delete"
                        class="mr-2"
                        color="red"
                        @click="deleteArticleConfirm"
                    ></v-btn>
                    <v-btn
                        icon="mdi-format-list-bulleted-type"
                        @click="goList"
                    ></v-btn>
                </v-col>
            </v-card-item>
        </v-card>
    </v-container>
</template>

<script setup>
import BoardCard from "@/components/board/BoardCard.vue";
import {onMounted, ref} from "vue";
import * as attachmentBoardApi from '@/api/board/boardAttachment'
import {useRoute, useRouter} from "vue-router";
import CheckModal from "@/components/modal/CheckModal.vue";

const currentRoute = useRoute();
const router = useRouter();
const attachmentArticle = ref([])
const showPopup = ref(false);
const modalType = ref()

//getArticle
const viewArticle = async () => {
    const boardNo = currentRoute.params.boardNo;
    const response = await attachmentBoardApi.getAttachmentArticle(boardNo);
    attachmentArticle.value = response.data.data
}

//뒤로가기
const goList = () => {
    router.push('/attachment');
}


//팝업표시
const deleteArticleConfirm = () => {
    modalType.value = 'DELETE'
    showPopup.value = true;
}

const modifyArticleConfirm = () => {
    modalType.value = 'MODIFY'
    showPopup.value = true;
}

//컨펌 핸들러
const confirmHandler = (event) => {
    if (event.modalType === 'DELETE') {
        // 삭제 처리 callback
        deleteArticle(attachmentArticle.value.boardNo)
    } else if (event.modalType === 'MODIFY') {
        // 수정 처리
        console.log('Event Modify!!')
    }
    showPopup.value = !showPopup.value
}

//취소 핸들러
const cancelHandler = () => {
    showPopup.value = !showPopup.value
}

//게시글 삭제
const deleteArticle = async (boardNo) => {
    const response = await attachmentBoardApi.deleteArticle(boardNo);

    if (response.status === 204) {
        alert('삭제 완료')
        goList();
    }
}


onMounted(() => {
    viewArticle();
})

</script>
