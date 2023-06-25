<template>
    <v-card>
        <v-layout style="height: 0;">
            <v-navigation-drawer
                    v-model="drawer"
                    :rail="rail"
                    permanent
                    @click="rail = false"
            >
                <v-list-item
                        prepend-avatar="https://img1.daumcdn.net/thumb/C428x428/?scode=mtistory2&fname=https%3A%2F%2Ftistory1.daumcdn.net%2Ftistory%2F5349579%2Fattach%2F4338f0f2b6fc4a0b914efdd430c4c5b9"
                        title="Mo-Greene"
                        nav
                >
                    <template v-slot:append>
                        <v-btn
                                variant="text"
                                icon="mdi-chevron-left"
                                @click.stop="rail = !rail"
                        ></v-btn>
                    </template>
                </v-list-item>

                <v-divider></v-divider>

                <v-list density="compact" nav>
                    <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        :prepend-icon="item.icon"
                        :title="item.title"
                        :value="item.value"
                        :to="item.to"
                    ></v-list-item>
                </v-list>
            </v-navigation-drawer>
            <v-main style="height: 250px"></v-main>
        </v-layout>
    </v-card>
</template>


<script>
import { ref } from 'vue';
export default {
    name: Headers,
    setup() {

        const drawer = ref(true);
        const rail = ref(true);

        const items = [
            {icon: 'mdi-head-snowflake-outline', title: 'Free', value: 'Free', to: 'free'},
            {icon: 'mdi-bulletin-board', title: 'Notice', value: 'Notice', to: 'notice'},
            {icon: 'mdi-content-save-outline', title: 'Attachment', value: 'Attachment', to: 'attachment'}
        ];

        return {
            drawer,
            rail,
            items
        }
    }
}
</script>
