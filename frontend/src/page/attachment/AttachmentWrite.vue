<template>
    <v-container>
        <v-card>
            <v-card-title class="text-center">게시글 등록</v-card-title>
            <v-card>
                <BoardWrite
                        :categoryBoard="categoryBoard"
                        @postArticle="postArticleHandler"/>
            </v-card>
        </v-card>
    </v-container>
</template>

<script>
import BoardWrite from "@/components/board/BoardWrite.vue";
import * as boardAttachmentApi from '@/api/board/boardAttachment'
import {useRouter} from "vue-router";

export default {
    name: "AttachmentWrite",
    components: {
        BoardWrite,
    },
    setup() {
        const categoryBoard = 'ATTACHMENT';
        const router = useRouter();

        //todo vuex로 빼기
        const goList = () => {
            router.push('/attachment')
        };

        //게시글 등록
        const postArticleHandler = async (object) => {

            const response = await boardAttachmentApi.postArticle(object);

            if (response.status === 201) {
                alert('등록 성공');
                goList();
            }
        }

        return {
            categoryBoard,
            postArticleHandler
        }
    }
}
</script>
