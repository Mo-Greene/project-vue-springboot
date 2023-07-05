<template>
    <v-app-bar color="" prominent>
        <v-btn
                variant="text"
                icon="mdi-dots-horizontal"
        ></v-btn>

        <div id="nav">
            <router-link to="/free">자유</router-link> |
            <router-link to="/notice">공지</router-link> |
            <router-link to="/attachment">자료실</router-link> |
            <router-link to="/login">로그인</router-link>
        </div>
    </v-app-bar>
</template>

<script>
export default {

}
</script>
