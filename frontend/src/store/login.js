import {defineStore} from "pinia";
import {ref} from "vue";

export const useLoginStore = defineStore("login", () => {
    //state
    const token = ref();

    //actions
    function setToken(newToken) {
        token.value = newToken;
    }

    //getters

    return {
        token,
        setToken
    }
})
