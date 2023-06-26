<template>
    <v-container>
        <v-card>
            <v-card-title class="freeTitleColor">자유게시판 - 상세보기</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <BoardCard :boardArticle="freeArticle"/>
            </v-card-item>
        </v-card>
    </v-container>

    <v-row justify="center">
        <v-btn variant="elevated" color="primary" @click="goBack">목록</v-btn>
    </v-row>
</template>

<script>
import BoardCard from "@/components/board/BoardCard.vue";
import {onMounted, ref} from "vue";
import * as freeBoardApi from '@/api/boardFree'
import {useRoute, useRouter} from "vue-router";

export default {
    name: "FreeView",
    components: {
        BoardCard
    },
    setup() {
        const currentRoute = useRoute();
        const router = useRouter();
        const freeArticle = ref([])

        //getArticle
        const viewArticle = async () => {
            const boardNo = currentRoute.params.boardNo;
            const response = await freeBoardApi.getFreeArticle(boardNo);
            freeArticle.value = response.data.data
        }

        //뒤로가기
        const goBack = () => {
            router.go(-1);
        }

        onMounted(() => {
            viewArticle();
        })

        return {
            freeArticle,
            goBack,
        }
    }
}
</script>
