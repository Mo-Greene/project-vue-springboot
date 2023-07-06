import http from "@/api/http";
import {useLoginStore} from '@/store/login'

/**
 * 로그인 api
 * @param username
 * @param password
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export async function login(username, password) {
    const loginDto = {
        username: username,
        password: password
    }
    const response = await http.post('/api/login', loginDto);

    //pinia 적용
    const loginStore = useLoginStore();

    if (response.status === 200) {
        loginStore.setToken(response.data.data);
    }

    return response;
}
