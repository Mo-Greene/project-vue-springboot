<template>
    <v-container>
        <v-card>
            <v-card-title class="text-center">게시글 수정</v-card-title>
            <v-card>
                <CheckModal
                    v-if="showPopup"
                    :showPopup="showPopup"
                    :modalType="modalType"
                    @confirm="confirmHandler"
                    @cancel="cancelHandler"/>

                <BoardModify
                        :articleBoard="attachmentBoard"
                        @modifyArticleSubmit="modifyArticleHandler"
                        @deleteAttachmentConfirm="deleteAttachmentHandler"/>
            </v-card>
        </v-card>
    </v-container>
</template>

<script setup>
import * as boardAttachmentApi from '@/api/board/boardAttachment'
import * as attachmentApi from '@/api/file/attachment'
import {useRoute, useRouter} from "vue-router";
import BoardModify from "@/components/board/BoardModify.vue";
import {onMounted, ref} from "vue";
import CheckModal from "@/components/modal/CheckModal.vue";

const categoryBoard = 'ATTACHMENT';
const router = useRouter();
const currentRoute = useRoute();
const attachmentBoard = ref();
const boardNo = currentRoute.params.boardNo;
const showPopup = ref(false);
const modalType = ref()
const fileNo = ref()

//게시글 정보 get
const getBoardList = async () => {
    const response = await boardAttachmentApi.getModifyAttachmentArticle(boardNo);
    attachmentBoard.value = response.data.data;
}

//게시글 수정 핸들러
const modifyArticleHandler = async (formData) => {
    const response = await boardAttachmentApi.updateAttachmentArticle(boardNo, formData);

    if (response.status === 200) {
        alert('게시글 수정 성공')
        router.go(-1);
    }
}

//모달 핸들러
const deleteAttachmentHandler = (event) => {
    fileNo.value = event
    modalType.value = 'DELETE'
    showPopup.value = true;
}

//삭제 핸들러
const confirmHandler = () => {
    //삭제처리 과정
    deleteFile();
}

//파일삭제
const deleteFile = async () => {
    const response = await attachmentApi.deleteFile(fileNo.value);
    if (response.status === 204) {

        //todo 수정해야될듯
        await getBoardList()
        showPopup.value = !showPopup.value;
    } else {
        alert('삭제 실패')
        showPopup.value = !showPopup.value;
    }
}

//취소 핸들러
const cancelHandler = () => {
    showPopup.value = !showPopup.value
}

onMounted(() => {
    getBoardList()
})

</script>
