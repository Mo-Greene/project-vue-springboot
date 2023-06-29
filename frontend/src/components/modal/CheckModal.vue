<template>
    <div class="popup-overlay">
        <div v-if="modalType === 'DELETE'" class="popup-content">
            <h3>삭제 확인</h3>
            <p>정말로 삭제하시겠습니까?</p>
            <div class="popup-buttons">
                <v-btn
                        icon="mdi-delete"
                        class="mr-2"
                        color="red"
                        @click="confirmDelete"
                ></v-btn>
                <v-btn
                        icon="mdi-cancel"
                        @click="cancelDelete"
                ></v-btn>
            </div>
        </div>
        <div v-if="modalType === 'MODIFY'" class="popup-content">
            <h3>수정 확인</h3>
            <p>정말로 수정하시겠습니까?</p>
            <div class="popup-buttons">
                <v-btn
                        icon="mdi-check"
                        class="mr-2"
                        color="blue"
                        @click="confirmModify"
                ></v-btn>
                <v-btn
                        icon="mdi-cancel"
                        @click="cancelDelete"
                ></v-btn>
            </div>
        </div>
    </div>
</template>

<script setup>

const props = defineProps([
    'showPopup',
    'modalType'
])
const emit = defineEmits([
    'confirm',
    'cancel'
])

const confirmDelete = () => {
    const modalType = props.modalType
    emit('confirm', { modalType })
};

const confirmModify = () => {
    const modalType = props.modalType
    emit('confirm', { modalType })
}


const cancelDelete = () => {
    emit('cancel')
};
</script>

<style scoped>
.popup-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.popup-content {
    background-color: white;
    padding: 20px;
    border-radius: 5px;
}

.popup-buttons {
    display: flex;
    justify-content: flex-end;
    margin-top: 10px;
}

.popup-buttons button {
    margin-left: 10px;
}
</style>
