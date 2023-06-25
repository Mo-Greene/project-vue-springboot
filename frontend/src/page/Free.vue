<template>
    <BoardTable/>
</template>

<script>
import BoardTable from "@/components/BoardTable.vue";
import axios from "axios";
export default {
    components: {BoardTable},
    setup() {
        const getFreeList = axios.get('http://localhost:8080/free')
            .then(res => {
                console.log(res.data)
            })

        return {
            getFreeList
        }
    }
}
</script>
