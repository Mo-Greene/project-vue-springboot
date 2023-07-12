<template>
    <v-card>
        <div class="d-flex align-center justify-center" style="height: 100vh">
            <v-sheet width="400" class="mx-auto">
                <v-form fast-fail ref="loginForm">
                    <v-card-title style="text-align: center">
                        Login
                    </v-card-title>
                    <v-card-item>
                        <v-text-field
                            variant="outlined"
                            v-model="username"
                            label="username"
                            :rules="usernameRules"
                        ></v-text-field>

                        <v-text-field
                            variant="outlined"
                            v-model="password"
                            label="password"
                            :rules="passwordRules"
                        ></v-text-field>

                        <v-btn
                            color="primary"
                            block
                            class="mt-2"
                            @click="validation">Log in</v-btn>
                    </v-card-item>
                </v-form>
                <div class="mt-2">
                    <p class="text-body-2">Don't have an account? <router-link to="/signup">Sign Up</router-link></p>
                </div>
            </v-sheet>
        </div>
    </v-card>
</template>


<script setup>
import * as loginApi from '@/api/login/login'
import {ref} from "vue";
import {useLoginStore} from "@/store/login";
import { useRouter } from "vue-router";

const username = ref();
const password = ref();
const loginForm = ref();
const loginStore = useLoginStore();
const router = useRouter();

//login Logic
const login = async () => {
    try {
        // Your login logic here
        const response = await loginApi.login(username.value, password.value);

        if (response.status === 200) {
            await loginStore.setToken(response.data.token);
            localStorage.setItem('access_token', response.data.token)
            await loginStore.getUserInfo();

            await router.push({path: '/'})
        }

    } catch (e) {
        alert(e.response.data.message)
    }
}

//Validation loginForm
async function validation (){
    const { valid } = await loginForm.value.validate();

    if (valid) await login();
    else {
        alert('로그인 양식에 맞지 않습니다.')
    }
}
const usernameRules = ref([
    value => {
        if (value) return true
        return '아이디를 적어주세요.'
    },
])
const passwordRules = ref([
    value => {
        if (value) return true
        return '비밀번호를 적어주세요.'
    },
])
</script>
