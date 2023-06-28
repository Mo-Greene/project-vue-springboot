<template>
    <div class="popup">
        <div class="popup-inner">
            <slot/>
            <v-container>
                <v-card-title>Search</v-card-title>
                <v-divider></v-divider>
                <v-card-text>
                    <v-row justify="center">
                        <span>Start : </span>
                        <DatePicker v-model="startDate"
                                    :locale="locale"
                                    :week-starts-on="0"
                                    :clearable="true"
                        />
                        <span>~ End :</span>
                        <DatePicker v-model="endDate"
                                    :locale="locale"
                                    :week-starts-on="0"
                                    :clearable="true"
                        />
                    </v-row>
                    <v-row>
                        <v-text-field
                            variant="underlined"
                            label="Keyword"
                            v-model="keyword"
                        />
                    </v-row>
                </v-card-text>
            </v-container>
            <div class="text-center">
                <v-btn
                        icon="mdi-magnify"
                        class="mr-2"
                        @click="search"
                ></v-btn>
                <v-btn
                        color="blue"
                        icon="mdi-close"
                        @click="togglePopup()">
                </v-btn>
            </div>
        </div>
    </div>
</template>

<script setup>
import DatePicker from 'vue3-datepicker';
import {ko} from 'date-fns/locale';
import {reactive, ref, defineEmits } from "vue";

const startDate = ref(new Date());
const endDate = ref(new Date());
const locale = reactive(ko);
const keyword = ref('');
const emit = defineEmits(['searchQuery'])
const props = defineProps(['togglePopup'])

//검색조건
const search = () => {

    const searchQuery = {
        startDate: startDate.value,
        endDate: endDate.value,
        keyword: keyword.value
    }

    emit('searchQuery', searchQuery)
}
</script>

<style lang="scss" scoped>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 99;
  background-color: rgba(0, 0, 0, 0.2);

  display: flex;
  align-items: center;
  justify-content: center;

  .popup-inner {
    background: #FFFFFF;
    padding: 50px;
  }
}
</style>
