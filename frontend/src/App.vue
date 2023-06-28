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

<script>
import NavBar from "@/components/NavBar.vue";
import AppBar from "@/components/AppBar.vue";

export default {
    name: "App",
    components: {
        AppBar,
        NavBar
    }
}
</script>

<style scoped>
.v-application {
    background-color: lightgray;
}
</style>
