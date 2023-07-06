import http from "@/api/http";

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
    return await http.post('/api/login', loginDto);
}
