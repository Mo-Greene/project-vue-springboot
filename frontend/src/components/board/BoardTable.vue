<template>
    <v-container>
        <v-table
                fixed-header
                height="auto"
        >
            <thead>
            <tr>
                <th class="text-center">
                    No
                </th>
                <th class="text-center" style="width: 50%">
                    Title
                </th>
                <th class="text-center">
                    Writer
                </th>
                <th class="text-center">
                    View
                </th>
                <th class="text-center">
                    RegDate
                </th>
                <th class="text-center">
                    ModDate
                </th>
            </tr>
            </thead>
            <tbody>
            <tr
                    v-for="board in boardList"
                    :key="board.boardNo"
                    class="text-center"
                    style="font-size: small"
            >
                <td>{{ board.boardNo }}</td>
                <td>
                    <a
                        @click="$router.push('/' + parseCategory(board.categoryBoard) + '/' + board.boardNo)"
                        style="cursor: pointer">
                        {{ board.boardTitle }}
                    </a>
                </td>
                <td>{{ board.boardWriter }}</td>
                <td>{{ board.boardView }}</td>
                <td>{{ formatDateTime(board.boardRegDate) }}</td>
                <td>{{ formatDateTime(board.boardModDate) }}</td>
            </tr>
            </tbody>
        </v-table>
        <div v-if="boardList.length === 0" class="text-center">
            게시글이 없습니다.
        </div>
    </v-container>
</template>

<script setup>

const props = defineProps(['boardList']);

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

//카테고리 파싱
const parseCategory = (category) => {
    return category.toLowerCase();
}

</script>
