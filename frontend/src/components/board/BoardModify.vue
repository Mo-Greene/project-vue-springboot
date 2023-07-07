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
                            >
                                <v-text-field
                                        label="Title"
                                        v-model="boardTitle"
                                        :rules="boardTitleRules"
                                />
                            </v-col>

                            <v-col cols="12">
                                <v-textarea
                                        label="Content"
                                        v-model="boardContent"
                                        :rules="boardContentRules"
                                />
                            </v-col>

                            <v-col cols="12" v-if="articleBoard && articleBoard.fileList !== null">
                                <v-col v-for="(file, i) in articleBoard.fileList"
                                     :key="i">
                                    <strong class="mr-2">{{ file.fileOriginalName }}</strong>
                                    <v-btn @click="deleteFile(file.fileNo)"
                                           icon="mdi-delete"
                                           size="x-small"
                                           color="red">
                                    </v-btn>
                                </v-col>
                            </v-col>

                            <v-col cols="12" v-if="articleBoard && articleBoard.categoryBoard === 'ATTACHMENT'">
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
const emit = defineEmits([
    'modifyArticleSubmit',
    'deleteAttachmentConfirm'
])

const router = useRouter();
const boardTitle = ref('');
const boardContent = ref('');
const boardWriter = ref('');
const form = ref();
const files = ref([]);


//상세조회 페이지 이동
const viewPage = () => {
    const categoryBoard = props.articleBoard.categoryBoard.toLowerCase()
    const boardNo = props.articleBoard.boardNo
    router.push('/' + categoryBoard + '/' + boardNo)
}

//게시글 수정
const modifyArticle = () => {
    if (props.articleBoard.categoryBoard === 'ATTACHMENT') {

        const formData = new FormData();

        formData.append('boardDTO',
            new Blob([JSON.stringify({
                boardTitle: boardTitle.value,
                boardContent: boardContent.value,
                boardWriter: boardWriter.value,
                categoryBoard: props.articleBoard.categoryBoard
            })], {type: "application/json"})
        );

        for (let i = 0; i < files.value.length; i++) {
            formData.append('file', files.value[i])
        }

        emit('modifyArticleSubmit', formData)
    } else {
        const boardDto = {
            boardTitle: boardTitle.value,
            boardContent: boardContent.value
        };

        emit('modifyArticleSubmit', boardDto)
    }
}

//파일 삭제
const deleteFile = (fileNo) => {
    emit('deleteAttachmentConfirm', fileNo)
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
        if (value.length < 4) return true
        return '파일은 최대 3개까지 가능합니다.'
    },
    value => {
        if (props.articleBoard.fileList.length + value.length < 4) return true
        return '기존 파일 포함해 최대 3개까지 가능합니다.'
    },
    value => {
        if (props.articleBoard.fileList.length + value.length > 0) return true
        return '파일을 추가해주세요.'
    }
])
</script>
