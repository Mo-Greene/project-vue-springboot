<template>
    <v-container>
        <v-form ref="replyForm">
            <v-row class="text-center">
                <v-col cols="12" class="d-flex justify-center align-center">
                    <v-textarea
                        prepend-inner-icon="mdi-comment"
                        counter
                        no-resize
                        class="mr-2"
                        rows="3"
                        label="댓글"
                        v-model="reply"
                        :rules="replyWriteRules"
                    />
                    <v-btn
                        icon="mdi-pencil"
                        color="success"
                        @click="validation"
                    ></v-btn>
                </v-col>
            </v-row>
        </v-form>
    </v-container>
</template>

<script setup>
import {ref} from "vue";

const reply = ref();
const replyForm = ref();
const emit = defineEmits(['postReply'])

//Vuetify Validation
async function validation () {
    const { valid } = await replyForm.value.validate();

    if (valid) {
        emit ('postReply', { reply })
    }
}

const replyWriteRules = ref([
    value => {
        if (value) return true
        return '내용을 적어주세요.'
    },
    value => {
        if (value?.length <= 2000) return true
        return '내용은 2000자 이하로 적어주세요.'
    }
])
</script>
