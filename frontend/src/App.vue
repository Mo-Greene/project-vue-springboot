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

const loginStore = useLoginStore();

loginStore.getUserInfo();

</script>

<style scoped>
.v-application {
    background-color: lightgray;
}
</style>
