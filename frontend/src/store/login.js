import {defineStore} from "pinia";
import {ref} from "vue";
import * as loginApi from '@/api/login/login'
import router from "@/router";

export const useLoginStore = defineStore("login", () => {
    //state
    const token = ref(null);
    const userNo = ref(null);
    const username = ref(null);
    const role = ref(null);

    //actions
    async function setToken(newToken) {
        token.value = newToken;
    }

    //유저 정보
    async function getUserInfo() {
        try {
            const localToken = localStorage.getItem('access_token');
            const response = await loginApi.getUserInfo(localToken);

            token.value = localToken;
            userNo.value = response.data.data.userNo;
            username.value = response.data.data.username;
            role.value = response.data.data.role;
        } catch (e) {
            alert(e);
            localStorage.removeItem('access_token');
            await router.push('/login');
        }
    }

    return {
        token,
        userNo,
        username,
        role,
        setToken,
        getUserInfo,
    }
})
