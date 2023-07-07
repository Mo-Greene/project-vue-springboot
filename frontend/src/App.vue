<template>
    <v-app>
        <NavBar/>
        <AppBar/>
        <v-main>
            <v-container>
                <router-view/>
            </v-container>
        </v-main>
    </v-app>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import AppBar from "@/components/AppBar.vue";
import {useLoginStore} from "@/store/login";
import {useCookies} from "vue3-cookies";

const loginStore = useLoginStore();
const { cookies } = useCookies();

if (cookies.get('token')) {
    console.log('token event!')
    loginStore.setToken(cookies.get('token'))
}
console.log('cookie : ' + cookies.get('token'));
console.log('store : ' + loginStore.token)

</script>

<style scoped>
.v-application {
    background-color: lightgray;
}
</style>
