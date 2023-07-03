<template>
    <v-container>
        <v-card-text class="py-1">
            <v-form ref="replyForm">
                <div class="d-flex" style="font-size: small">
                    <v-textarea
                        prepend-inner-icon="mdi-comment"
                        counter
                        no-resize
                        style="font-size: small"
                        class="mr-2"
                        rows="2"
                        label="Comments"
                        v-model="reply"
                        :rules="replyWriteRules"
                    />
                    <div class="d-flex flex-column">
                        <v-btn
                            icon="mdi-pencil"
                            color="success"
                            @click="validation"
                        ></v-btn>
                    </div>
                </div>
            </v-form>
        </v-card-text>
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
        replyForm.value.reset();
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
