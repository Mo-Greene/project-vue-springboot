<template>
    <v-card elevation="0" class="pb-3 pt-2">
        <v-card-text style="font-size: small">
            <div class="d-flex justify-space-between" style="font-size: small">
                <div>
                    <table>
                        <tr>
                        </tr>
                        <tr>
                            <td class="pr-4">
                                <v-icon small class="pr-1">
                                    mdi-comment-account
                                </v-icon>
                                {{ item.replyWriter }}
                            </td>
                        </tr>
                    </table>
                </div>

                <div>
                    <v-icon small class="pl-3 pr-1">
                        mdi-clock-edit
                    </v-icon>
                    {{ formatDateTime(item.replyRegDate) }}
                </div>
            </div>
        </v-card-text>

        <!-- 읽기 -->
        <v-card-text class="py-1">
            <div class="pl-6">
                {{ item.replyContent }}
            </div>
            <div class="d-flex justify-end">
                <v-btn class="mr-1" x-small outlined color="#356859"> 수정 </v-btn>
                <v-btn class="mr-1" x-small outlined color="#356859"> 삭제 </v-btn>
            </div>
        </v-card-text>

<!--        &lt;!&ndash; 수정 &ndash;&gt;-->
<!--        <v-card-text class="py-1">-->
<!--            <v-form ref="form">-->
<!--                <div class="d-flex" style="font-size: small">-->
<!--                    <v-textarea style="font-size: small" outlined :disabled="false"/>-->
<!--                    <div class="d-flex flex-column">-->
<!--                        <v-btn type="submit" class="ml-2" outlined small color="#356859"> 등록 </v-btn>-->
<!--                        <v-btn class="ml-2 mt-1" outlined small color="#356859"> 취소 </v-btn>-->
<!--                    </div>-->
<!--                </div>-->
<!--            </v-form>-->
<!--        </v-card-text>-->

    </v-card>

</template>

<script setup>
const props = defineProps(['item'])

//날짜 포맷팅
const formatDateTime = (dateTime) => {
    if (dateTime) {
        const date = new Date(dateTime);

        const year = date.getFullYear() - 2000;
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();

        return year + "/" + padZero(month) + "/" + padZero(day) + ' ' + padZero(hour) + ':' + padZero(minute);
    } else {
        return "-";
    }
};

//날짜 포맷팅
const padZero = (number) => {
    return number < 10 ? "0" + number : number;
};

</script>
