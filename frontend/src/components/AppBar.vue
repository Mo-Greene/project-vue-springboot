<template>
    <v-app-bar prominent>
        <router-link
                to="/login"
        >
            <v-btn
                v-if="!loginStore.username"
                variant="text"
                icon="mdi-login"
                class="login"
                color="black"
            ></v-btn>
        </router-link>
        <v-btn
            v-if="loginStore.username"
            icon="mdi-logout"
            class="logout"
            @click="logout()"
        ></v-btn>

    </v-app-bar>
</template>

<script setup>
import {useLoginStore} from "@/store/login";

const loginStore = useLoginStore();

/**
 * 로그아웃
 */
const logout = () => {
    console.log('logout event!')
}
</script>
