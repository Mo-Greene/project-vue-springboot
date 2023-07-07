<template>
    <v-navigation-drawer
            v-model="drawer"
            rail
            expand-on-hover
            permanent="true"
    >
        <v-list-item
                prepend-avatar="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfIJrUhPALWv647IYdyMMfMVhU3ExHuKfaCAYvqdqDgjhvQCUylKLy7XZmsDMcZMrVcgg&usqp=CAU"
                nav
                style="font-size: small"
        >Anonymous</v-list-item>

        <v-list-item
            prepend-avatar="https://img1.daumcdn.net/thumb/C428x428/?scode=mtistory2&fname=https%3A%2F%2Ftistory1.daumcdn.net%2Ftistory%2F5349579%2Fattach%2F4338f0f2b6fc4a0b914efdd430c4c5b9"
            nav
            style="font-size: small"
        >username들어가야됨</v-list-item>

        <v-divider></v-divider>

        <v-list density="compact" nav>
            <v-list-item
                    v-for="item in items"
                    :key="item.title"
                    :prepend-icon="item.icon"
                    :title="item.title"
                    :value="item.value"
                    :to="item.link"
            ></v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script setup>
import {ref} from 'vue';
import {useLoginStore} from "@/store/login";

const drawer = ref(true);
const loginStore = useLoginStore();
const { user, token } = loginStore;

const items = [
    {icon: 'mdi-head-snowflake-outline', title: 'Free', value: 'Free', link: '/free'},
    {icon: 'mdi-bulletin-board', title: 'Notice', value: 'Notice', link: '/notice'},
    {icon: 'mdi-content-save-outline', title: 'Attachment', value: 'Attachment', link: '/attachment'}
];
</script>
