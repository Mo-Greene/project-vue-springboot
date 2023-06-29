<template>
    <v-container>
        <v-row justify="center">
            <v-col
                    cols="12"
                    md="8"
            >

                <v-form
                        ref="form"
                >
                    <v-container>
                        <v-row>
                            <v-col
                                    cols="12"
                                    md="6">
                                <v-text-field
                                        label="Title"
                                        v-model="boardTitle"
                                        :rules="boardTitleRules"
                                />
                            </v-col>
                            <!--todo : 후에 작성자 삭제 시 md=6만 제거-->
                            <v-col cols="12"
                                   md="6">
                                <v-text-field
                                        label="Writer"
                                        v-model="boardWriter"
                                />
                            </v-col>

                            <v-col cols="12">
                                <v-textarea
                                        label="Content"
                                        v-model="boardContent"
                                        :rules="boardContentRules"
                                />
                            </v-col>

                            <v-col cols="12" v-if="categoryBoard === 'ATTACHMENT'">
                                <v-file-input
                                        multiple
                                        label="File input"
                                        v-model="files"
                                        :rules="fileInputRules"
                                ></v-file-input>
                            </v-col>

                            <v-row justify="end">
                                <v-col
                                    cols="auto"
                                    class="text-right"
                                >
                                    <v-btn
                                        color="success"
                                        icon="mdi-pen-plus"
                                        class="mr-0"
                                        @click="validation"
                                    >
                                    </v-btn>
                                </v-col>

                                <v-col
                                        cols="auto"
                                        class="text-right"
                                >
                                    <v-btn
                                            icon="mdi-format-list-bulleted-type"
                                            @click="viewPage"
                                    >
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-row>
                    </v-container>
                </v-form>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
import { ref } from "vue";
import {useRouter} from "vue-router";

const props = defineProps(['articleBoard'])
const emit = defineEmits(['modifyArticleSubmit'])

const router = useRouter();
const boardTitle = ref('');
const boardContent = ref('');
const boardWriter = ref('');
const files = ref([]);
const form = ref();

//상세조회 페이지 이동
const viewPage = () => {
    const categoryBoard = props.articleBoard.categoryBoard.toLowerCase()
    const boardNo = props.articleBoard.boardNo
    router.push('/' + categoryBoard + '/' + boardNo)
}

//게시글 수정
const modifyArticle = () => {
    const boardDto = {
        boardTitle: boardTitle.value,
        boardContent: boardContent.value,
        boardWriter: boardWriter.value
    };

    emit('modifyArticleSubmit', boardDto)
}

//Vuetify Validation
async function validation () {
    const { valid } = await form.value.validate();

    if (valid) modifyArticle();
    else {
        alert('양식을 지켜주세요.');
    }
}
const boardTitleRules = ref([
    value => {
        if (value) return true
        return '제목을 적어주세요.'
    },
    value => {
        if (value?.length > 3 && value?.length <= 100) return true
        return '제목은 4자 이상 100자 이하로 적어주세요.'
    }
])
const boardContentRules = ref([
    value => {
        if (value) return true
        return '내용을 적어주세요.'
    },
    value => {
        if (value?.length > 3 && value?.length <= 2000) return true
        return '내용은 4자 이상 2000자 이하로 적어주세요.'
    }
])
const fileInputRules = ref([
    value => {
        if (value.length > 0) return true
        return '파일을 첨부해주세요.'
    },
    value => {
        if (value.length < 4) return true
        return '파일은 최대 3개까지 가능합니다.'
    }
])
</script>
