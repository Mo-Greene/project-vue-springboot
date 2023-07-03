<template>
    <v-card elevation="0">
        <v-card elevation="0" color="#e1f0eb">
            <v-card-text class="px-12 py-4" style="font-size: small">
                <v-icon small class="pr-1">
                    mdi-chat-processing
                </v-icon>

                <!--todo pinia로 해결해야할 부분같음-->
                {{ commentList && commentList.length }} 개의 댓글이 달렸습니다.
            </v-card-text>
        </v-card>

        <!-- 각각 게시글 -->
        <ReplyDetailForm
                class="px-8"
                v-for="item in commentList"
                :key="item.replyNo"
                :item="item"/>

    </v-card>
</template>

<script setup>
import ReplyDetailForm from "@/components/reply/ReplyDetailForm.vue";

const props = defineProps(['commentList'])

</script>
