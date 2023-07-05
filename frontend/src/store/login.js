import {defineStore} from "pinia";
import {ref} from "vue";

export const loginStore = defineStore("login", () => {
    //state
    const username = ref();
    const isLogin = ref(false);

    return {
        username,
        isLogin
    }
})
