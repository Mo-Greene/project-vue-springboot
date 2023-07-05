<template>
    <v-container>
        <v-card>
            <v-card-title class="text-center">게시글 수정</v-card-title>
            <v-card>
                <BoardModify
                    :articleBoard="freeBoard"
                    @modifyArticleSubmit="modifyArticleHandler"/>
            </v-card>
        </v-card>
    </v-container>
</template>

<script setup>
import * as boardFreeApi from '@/api/board/boardFree'
import {useRoute, useRouter} from "vue-router";
import BoardModify from "@/components/board/BoardModify.vue";
import {onMounted, ref} from "vue";

const categoryBoard = 'FREE';
const router = useRouter();
const currentRoute = useRoute();
const freeBoard = ref();
const boardNo = currentRoute.params.boardNo;

//게시글 정보 get
const getBoardList = async () => {

    const response = await boardFreeApi.getModifyFreeArticle(boardNo);
    freeBoard.value = response.data.data;
}

//게시글 수정 핸들러
const modifyArticleHandler = async (boardDto) => {
    const response = await boardFreeApi.updateFreeArticle(boardNo, boardDto);

    if (response.status === 200) {
        alert('게시글 수정 성공')
        router.go(-1);
    }
}

onMounted(() => {
    getBoardList()
})

</script>
