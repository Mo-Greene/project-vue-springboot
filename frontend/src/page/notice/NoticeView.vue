<template>
    <v-container>
        <v-card>
            <v-card-title class="noticeTitleColor">공지게시판 - 상세보기</v-card-title>
            <v-divider class="border-opacity-75"></v-divider>
            <v-card-item>
                <BoardCard :boardArticle="noticeArticle"/>
            </v-card-item>
        </v-card>
    </v-container>
</template>

<script>
import BoardCard from "@/components/board/BoardCard.vue";
import {onMounted, ref} from "vue";
import * as noticeBoardApi from '@/api/board/boardNotice'
import {useRoute, useRouter} from "vue-router";

export default {
    name: "NoticeView",
    components: {
        BoardCard
    },
    setup() {
        const currentRoute = useRoute();
        const router = useRouter();
        const noticeArticle = ref([])

        //getArticle
        const viewArticle = async () => {
            const boardNo = currentRoute.params.boardNo;
            const response = await noticeBoardApi.getNoticeArticle(boardNo);
            noticeArticle.value = response.data.data
        }

        //뒤로가기
        const goBack = () => {
            router.go(-1);
        }

        onMounted(() => {
            viewArticle();
        })

        return {
            noticeArticle,
            goBack,
        }
    }
}
</script>
