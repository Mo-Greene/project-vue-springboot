<template>
    <v-container>
        <v-row>
            <v-col>
                <h2>{{ boardArticle.boardTitle }}</h2>
            </v-col>
        </v-row>
        <v-divider></v-divider>
        <div>
            <v-row>
                <v-col cols="4">
                    <strong>작성자 :</strong> {{ boardArticle.boardWriter }}
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="4">
                    <strong>조회수 :</strong> {{ boardArticle.boardView }}
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <strong>생성일 :</strong> {{ formatDateTime(boardArticle.boardRegDate) }}
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <strong>수정일 :</strong> {{ formatDateTime(boardArticle.boardModDate) }}
                </v-col>
            </v-row>
        </div>
        <v-divider></v-divider>
        <div>
            <v-card-title>내용</v-card-title>
            <v-card>
                <v-card-text>{{ boardArticle.boardContent }}</v-card-text>
            </v-card>
        </div>
    </v-container>
</template>

<script>
export default {
    name: "BoardCard",
    props: ['boardArticle'],
    setup() {

        //날짜 포맷팅
        const formatDateTime = (dateTime) => {
            if (dateTime) {
                const date = new Date(dateTime);

                const year = date.getFullYear() - 2000;
                let month = date.getMonth() + 1;
                let day = date.getDate();
                let hour = date.getHours();
                let minute = date.getMinutes();

                return year + "-" + padZero(month) + "-" + padZero(day) + ' ' + padZero(hour) + ':' + padZero(minute);
            } else {
                return "-";
            }
        };

        //날짜 포맷팅
        const padZero = (number) => {
            return number < 10 ? "0" + number : number;
        };

        return {
            formatDateTime,
        }
    }
}
</script>
