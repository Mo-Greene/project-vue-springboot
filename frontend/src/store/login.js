import {defineStore} from "pinia";
import {ref} from "vue";

export const useLoginStore = defineStore("login", () => {
    //state
    const token = ref();
    const username = ref();
    const isLogin = ref(false);

    //actions
    function setToken(newToken) {
        token.value = newToken;
    }

    function setUser(payload) {
        username.value = payload;
    }

    return {
        token,
        username,
        setToken,
        setUser,
        isLogin
    }
})
