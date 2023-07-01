<template>
    <v-container>
        <v-card>
            <v-card-title class="freeTitleColor">자유게시판 - 상세보기</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <CheckModal
                    v-if="showPopup"
                    :showPopup="showPopup"
                    :modalType="modalType"
                    @confirm="confirmHandler"
                    @cancel="cancelHandler"/>

                <BoardCard
                    :boardArticle="freeArticle"/>

                <v-col
                    cols="auto"
                    class="text-right"
                >
                    <v-btn
                        icon="mdi-pencil-plus"
                        class="mr-2"
                        color="blue"
                        @click="modifyArticle(freeArticle.boardNo)"
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

                <v-divider></v-divider>

                <ReplyWrite
                    @postReply="postReplyHandler"/>

            </v-card-item>
        </v-card>
    </v-container>
</template>

<script setup>
import BoardCard from "@/components/board/BoardCard.vue";
import {onMounted, ref} from "vue";
import * as freeBoardApi from '@/api/board/boardFree'
import * as replyApi from '@/api/reply/reply';
import {useRoute, useRouter} from "vue-router";
import CheckModal from "@/components/modal/CheckModal.vue";
import ReplyWrite from "@/components/reply/ReplyWrite.vue";

const currentRoute = useRoute();
const router = useRouter();
const boardNo = currentRoute.params.boardNo;
const freeArticle = ref([]);
const showPopup = ref(false);
const modalType = ref()

//getArticle
const viewArticle = async () => {
    const response = await freeBoardApi.getFreeArticle(boardNo);
    freeArticle.value = response.data.data
}

//뒤로가기
const goList = () => {
    router.push('/free');
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

const modifyArticle = (boardNo) => {
    router.push('/free/modify/' + boardNo)
}

//컨펌 핸들러
const confirmHandler = (event) => {
    if (event.modalType === 'DELETE') {
        // 삭제 처리 callback
        deleteArticle(freeArticle.value.boardNo)
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
    const response = await freeBoardApi.deleteArticle(boardNo);

    if (response.status === 204) {
        alert('삭제 완료')
        goList();
    }
}

//댓글 핸들러
const postReplyHandler = async (event) => {
    const replyDto = {};
    replyDto.replyContent = event.reply.value;

    //todo 작성자 필요! 로그인 세션처리
    const response = await replyApi.postReply(boardNo, replyDto);
    console.log(response)

}

onMounted(() => {
    viewArticle();
})

</script>
