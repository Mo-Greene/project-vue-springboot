<template>
    <v-card>
        <div class="d-flex align-center justify-center" style="height: 100vh">
            <v-sheet width="400" class="mx-auto">
                <v-form fast-fail ref="loginForm">
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
                    <a href="#" class="text-body-2 font-weight-regular">Forgot Password?</a>

                    <v-btn
                        color="primary"
                        block
                        class="mt-2"
                        @click="validation">Sign in</v-btn>
                </v-form>
                <div class="mt-2">
                    <p class="text-body-2">Don't have an account? <a href="#">Sign Up</a></p>
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
import { useCookies } from "vue3-cookies";

const username = ref();
const password = ref();
const loginForm = ref();
const loginStore = useLoginStore();
const router = useRouter();
const { cookies } = useCookies();

//login Logic
const login = async () => {
    try {
        // Your login logic here
        const response = await loginApi.login(username.value, password.value);

        if (response.status === 200) {
            console.log('login : ' + response.data.token)
            loginStore.setToken(response.data.token);
            // loginStore.setUser(response.data.user.username)

            //쿠키저장
            cookies.set('token', response.data.token)

            await router.push({path: '/'})
        }

    } catch (e) {
        console.log(e)
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
