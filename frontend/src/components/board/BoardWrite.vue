<template>
    <v-container>
        <v-row justify="center">
            <v-col
                    cols="12"
                    md="8"
            >

                <v-form>
                    <v-container>
                        <v-row>
                            <v-col
                                cols="12"
                                md="6">
                                <v-text-field
                                        label="Title"
                                        v-model="boardTitle"
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

                            <v-col
                                    cols="12"
                                    md="6"
                            >
                                <v-text-field
                                        label="Password"
                                />
                            </v-col>
                            <v-col
                                    cols="12"
                                    md="6"
                            >
                                <v-text-field
                                        label="Password Check"
                                />
                            </v-col>

                            <v-col cols="12">
                                <v-textarea
                                        label="Content"
                                        v-model="boardContent"
                                />
                            </v-col>

                            <v-col cols="12" v-if="props.categoryBoard === 'ATTACHMENT'">
                                <v-file-input
                                    multiple
                                    label="File input"
                                ></v-file-input>
                            </v-col>

                            <v-row justify="end">
                                <v-col
                                        cols="auto"
                                        class="text-right"
                                >
                                    <v-btn
                                            icon="mdi-format-list-bulleted-type"
                                            @click="$router.go(-1)"
                                    >
                                    </v-btn>
                                </v-col>

                                <v-col
                                        cols="auto"
                                        class="text-right"
                                >
                                    <v-btn
                                            color="success"
                                            icon="mdi-pen-plus"
                                            class="mr-0"
                                            @click="postArticle"
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
import { ref, defineEmits} from "vue";

const props = defineProps(['categoryBoard'])
const emit = defineEmits(['postArticle']);

const boardTitle = ref('');
const boardContent = ref('');
const boardWriter = ref('');

const postArticle = () => {
    const boardDto = {
        boardTitle: boardTitle.value,
        boardContent: boardContent.value,
        boardWriter: boardWriter.value,
        categoryBoard: props.categoryBoard
    }

    emit('postArticle', boardDto)
}
</script>
