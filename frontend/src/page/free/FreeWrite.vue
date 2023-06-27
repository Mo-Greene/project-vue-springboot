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
import * as boardFreeApi from '@/api/board/boardFree'
import {useRouter} from "vue-router";
import BoardWrite from "@/components/board/BoardWrite.vue";

export default {
    name: "FreeWrite",
    components: {
      BoardWrite
    },
    setup() {
        const categoryBoard = 'FREE';
        const router = useRouter();

        //todo vuex로 빼기
        const goList = () => {
            router.push('/free')
        };

        const postArticleHandler = async (object) => {
            const response = await boardFreeApi.postArticle(object);

            if (response.status === 201) {
                alert('등록 성공')
                goList()
            }
        }

        return {
            categoryBoard,
            postArticleHandler
        }
    },
}
</script>
