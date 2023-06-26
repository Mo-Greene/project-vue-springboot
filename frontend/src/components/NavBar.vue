<template>
    <v-navigation-drawer
            v-model="drawer"
            rail
            expand-on-hover
    >
        <v-list-item
                prepend-avatar="https://img1.daumcdn.net/thumb/C428x428/?scode=mtistory2&fname=https%3A%2F%2Ftistory1.daumcdn.net%2Ftistory%2F5349579%2Fattach%2F4338f0f2b6fc4a0b914efdd430c4c5b9"
                title="Mo-Greene"
                nav
        >
        </v-list-item>

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

<script>
import {ref} from 'vue';

export default {
    name: "NavBar",
    setup() {

        const drawer = ref(true);

        const items = [
            {icon: 'mdi-head-snowflake-outline', title: 'Free', value: 'Free', link: '/free'},
            {icon: 'mdi-bulletin-board', title: 'Notice', value: 'Notice', link: '/notice'},
            {icon: 'mdi-content-save-outline', title: 'Attachment', value: 'Attachment', link: '/attachment'}
        ];

        return {
            drawer,
            items
        }
    }
}
</script>
